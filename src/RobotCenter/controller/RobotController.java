package RobotCenter.controller;

import RobotCenter.model.JointPosition;
import RobotCenter.model.PanelTexts;
import RobotCenter.model.RobotData;
import RobotCenter.model.RobotService;
import RobotCenter.view.MainGui;
import RobotCenter.view.RobotGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.DoubleSummaryStatistics;
import java.util.List;

/**
 * Created by YapYap on 2015-12-31.
 */
public class RobotController extends Thread {

    private MainGui mainGui;
    private RobotGui robotGui;
    private Socket robotClientSocket;
    private List<RobotController> robotControllers;
    private List<String> tabNames;
    private PanelTexts panelTexts;
    private PrintWriter outputStream;
    private BufferedReader inputStream;
    private JointPosition currentJointPosition;
    private JointPosition moveToJointPosition;
    private int robotControllerIndex;
    private String tabName;
    private RobotData robotData;
    private RobotService robotService;
    private boolean flag = true;
    private String command = "0";



    public RobotController(MainGui mainGui, RobotGui robotGui, Socket robotClientSocket, List<RobotController> robotControllers, List<String> tabNames, String tabName, PanelTexts panelTexts, RobotData robotData) {

        this.robotGui = robotGui;
        this.robotClientSocket = robotClientSocket;
        this.robotControllers = robotControllers;
        this.panelTexts = panelTexts;
        this.mainGui = mainGui;
        this.tabName = tabName;
        this.tabNames = tabNames;
        this.robotData = robotData;

        robotGui.setRobotModelLabel(robotData.getRobotModel());
        robotGui.setMoveAlertLabel(panelTexts.getMoveAlertLabelText1());

        robotControllerIndex = robotControllers.size();
        robotService = new RobotService(robotData);
        currentJointPosition = new JointPosition(0, 0, 0, 0, 0, 0);
        moveToJointPosition = new JointPosition(0, 0, 0, 0, 0, 0);


        robotGui.setEnableMoveRobotButton(false);

        robotGui.addApplyListener(new apply());
        robotGui.addMoveRobotListener(new moveRobot());
        robotGui.addStopRobotListener(new stopRobot());
        robotGui.addDisconnectRobotListener(new disconnectRobot());
    }

    public void run() {


        BufferedReader inputStream = null;
        try {
            inputStream = new BufferedReader(new InputStreamReader(robotClientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        sendStringMessage("Welcome To RobotCenter Server");
        robotGui.setRobotMessageLabel(receiveStringMessage(inputStream));
        sendStringMessage("Start communication");

        while (flag) {

            for (int i = 1; i < 7; i++) {

                String cJPoseStr = receiveStringMessage(inputStream);
                robotGui.setAxisCJPoseTextField(i, cJPoseStr);
                System.out.println("Axis" + i + ":" + cJPoseStr);
                currentJointPosition.setJointPosition(i, convertStrToDouble(cJPoseStr));
                //sendStringMessage("Odebrano JPOS" + i);
            }
            sendStringMessage("JPoseSet");
            sendStringMessage(command);

        }


    }


    class apply implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if (checkIfMoveCommandIsNumber()) {
                if (checkMoveCommand()) {

                    robotGui.setMoveAlertLabel(panelTexts.getMoveAlertLabelText4());
                    robotGui.setEnableMoveRobotButton(true);
                }
            } else {
                robotGui.setEnableMoveRobotButton(false);
            }

        }
    }

    class moveRobot implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            robotGui.setRobotModelLabel("Stop");
        }
    }


    class stopRobot implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            command = "1";
        }
    }

    class disconnectRobot implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            closeRobotClient();
        }
    }

    private void sendStringMessage(String messsage) {

        PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(robotClientSocket.getOutputStream());
        } catch (IOException e) {
            if (flag)
                e.printStackTrace();
        }


        outputStream.println(messsage);
        outputStream.flush();
    }

    private void sendIntMessage(int message) {

        PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(robotClientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


        String strMessage = Integer.toString(message);
        outputStream.println(strMessage);
        outputStream.flush();
    }

    private String receiveStringMessage(BufferedReader inputStream) {


        String message = null;

        while (message == null) {
            try {
                message = inputStream.readLine();
            } catch (IOException e) {
                //e.printStackTrace();

                ////////////ZAMKNIECIE CLIENTSOCKETA JEZELI BRAK ODPOWIEDZI
                if (flag)
                    closeRobotClient();
            }
        }

        return message;
    }


    private int receiveIntMessage() {


        BufferedReader inputStream = null;
        try {
            inputStream = new BufferedReader(new InputStreamReader(robotClientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String message = null;
        try {
            message = inputStream.readLine();
        } catch (IOException e) {
            e.printStackTrace();

            ////////////ZAMKNIECIE CLIENTSOCKETA JEZELI BRAK ODPOWIEDZI
            //closeRobotClient();
        }

        try {
            int intMessage = Integer.parseInt(message);
            return intMessage;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private boolean checkIfMoveCommandIsNumber() {

        boolean flag = true;

        for (int i = 1; i <= 6; i++) {

            try {
                double axis = Double.parseDouble(robotGui.getAxisMJPoseTextField(i));
                moveToJointPosition.setJointPosition(i, axis);
                robotGui.setColorAxisMJPoseTextField(i, true);
            } catch (NumberFormatException e) {
                robotGui.setMoveAlertLabel(panelTexts.getMoveAlertLabelText3());
                robotGui.setColorAxisMJPoseTextField(i, false);
                flag = false;
            }


        }


        return flag;

    }


    private boolean checkMoveCommand() {

        boolean flag = true;

        for (int i = 1; i <= 6; i++) {
            if (robotService.checkIfInJPoseRangeAxis(i, moveToJointPosition)) {
                robotGui.setColorAxisMJPoseTextField(i, true);
            } else {
                robotGui.setColorAxisMJPoseTextField(i, false);
                robotGui.setMoveAlertLabel(panelTexts.getMoveAlertLabelText2());
                flag = false;
            }

        }
        return flag;
    }


    public void closeRobotClient() {

        flag = false;
        mainGui.removeTabbedPane(tabName);

        tabNames.remove(tabName);


        robotControllers.remove(this);

        try {
            robotClientSocket.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private int convertStrToInt(String str) {

        try {
            int strToInt = Integer.parseInt(str);
            return strToInt;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private String convertIntToStr(int intToStr) {

        String str = Integer.toString(intToStr);
        return str;
    }

    private double convertStrToDouble(String str) {


        double strToDouble = Double.parseDouble(str);
        return strToDouble;
    }

    private String convertDoubleToStr(double doubleToStr) {

        String str = Double.toString(doubleToStr);
        return str;
    }

}