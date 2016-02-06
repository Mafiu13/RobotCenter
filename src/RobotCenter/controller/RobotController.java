package RobotCenter.controller;

import RobotCenter.model.*;
import RobotCenter.view.MainGui;
import RobotCenter.view.RobotGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
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
    private String tabName;
    private RobotData robotData;
    private RobotService robotService;
    private Command command;
    private boolean continueFlag;
    private boolean moveFlag;
    private boolean stopFlag;
    private boolean abortFlag;
    private RobotControllerValidator robotControllerValidator;
    private GraphicViewController graphicViewController;
    private ModelViewController modelViewController;
    private int speed;


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

        moveToJointPosition = new JointPosition(0, 0, 0, 0, 0, 0);
        currentJointPosition = new JointPosition(0, 0, 0, 0, 0, 0);

        command = Command.CONTINUE;
        setStartFlags();

        robotService = new RobotService(robotData);
        robotControllerValidator = new RobotControllerValidator(moveToJointPosition, robotGui, panelTexts, robotService);
        graphicViewController = new GraphicViewController(robotGui);


        robotGui.setEnableMoveRobotButton(false);
/*        robotGui.setEnableStopRobotButton(false);*/

        robotGui.addApplyListener(new apply());
        robotGui.addMoveRobotListener(new moveRobot());
        /*robotGui.addStopRobotListener(new stopRobot());*/
        robotGui.addDisconnectRobotListener(new disconnectRobot());
        robotGui.addGraphicViewListener(new graphicView());
        robotGui.addModelViewListener(new modelView());
        robotGui.addMoveRobotListener(new modelView());
    }

    public void run() {


        //BufferedReader inputStream = null;
        try {
            inputStream = new BufferedReader(new InputStreamReader(robotClientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        sendStringMessage("Welcome To RobotCenter Server");
        robotGui.setRobotMessageLabel(receiveStringMessage(inputStream));
        sendStringMessage("Start communication");


        while (continueFlag) {

            synchronized (this) {
                for (int i = 1; i < 7; i++) {

                    String cJPoseStr = receiveStringMessage(inputStream);
                    robotGui.setAxisCJPoseTextField(i, cJPoseStr);
                    System.out.println("Axis" + i + ":" + cJPoseStr);
                    currentJointPosition.setJointPosition(i, TypeConverter.convertStrToDouble(cJPoseStr));
                }

                paintRobotInCurrentPosition(currentJointPosition);
                sendStringMessage("JPoseSet");
            }

            synchronized (this) {

                sendStringMessage(command.getCommandValue());

                if (moveFlag) {
                    moveRobot(inputStream);
                }
               /* if(stopFlag){
                    stopRobot(inputStream);
                }*/

            }
        }

    }

    class apply implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if (robotControllerValidator.checkIfMoveCommandIsNumber()) {
                if (robotControllerValidator.checkMoveCommand()) {

                    robotControllerValidator.correctMoveToJointPosition();
                    moveToJointPosition = robotControllerValidator.getMoveToJointPosition();
                    speed = robotControllerValidator.getSpeed();
                    System.out.println(speed);
                    robotGui.setMoveAlertLabel(panelTexts.getMoveAlertLabelText4());
                    robotGui.setEnableMoveRobotButton(true);
                } else {
                    robotGui.setEnableMoveRobotButton(false);
                }
            } else {
                robotGui.setEnableMoveRobotButton(false);
            }

        }
    }

    class moveRobot implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            command = Command.MOVE;
            moveFlag = true;
            robotGui.setRobotMessageLabel("Robot is moving");
            robotGui.setEnableMoveRobotButton(false);
            robotGui.setEnableApplyButton(false);
        }
    }

    class disconnectRobot implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            closeRobotClient();
        }
    }

    class graphicView implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            graphicViewController.setVisibleGraphicViewGui(true);
            robotGui.setEnableGraphicViewButton(false);
        }
    }

    class modelView implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            modelViewController = new ModelViewController(currentJointPosition, robotGui, robotData.getRobotModelID());
            robotGui.setEnableModelViewButton(false);
        }

    }

    private void moveRobot(BufferedReader inputStream) {

        command = Command.CONTINUE;
        moveFlag = false;


        for (int i = 1; i < 7; i++) {

            sendStringMessage(robotGui.getAxisMJPoseTextField(i));
            System.out.println(robotGui.getAxisMJPoseTextField(i));
            moveToJointPosition.setJointPosition(i, TypeConverter.convertStrToDouble(robotGui.getAxisMJPoseTextField(i)));
        }
        robotGui.setRobotMessageLabel(receiveStringMessage(inputStream));
        robotGui.setEnableApplyButton(true);
    }

    private void sendStringMessage(String message) {

        //PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(robotClientSocket.getOutputStream());
        } catch (IOException e) {
            if (continueFlag)
                e.printStackTrace();
        }

        outputStream.println(message);
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
                if (continueFlag)
                    closeRobotClient();
            }
        }
        return message;
    }

    private void paintRobotInCurrentPosition(JointPosition currentJointPosition) {

        graphicViewController.paintRobot(robotData.getRobotModelID(), currentJointPosition);

        // graphicViewController.paintRobot(2,currentJointPosition);

    }

    public void closeRobotClient() {

        graphicViewController.closeGraphicViewController();

        continueFlag = false;
        mainGui.removeTabbedPane(tabName);

        tabNames.remove(tabName);

        robotControllers.remove(this);

        try {
            robotClientSocket.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void setStartFlags() {

        continueFlag = true;
        moveFlag = false;
        stopFlag = false;
        abortFlag = false;
    }

    ////////////STOPOWANIE ROBOTA ////////////

    /*class stopRobot implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            command = Command.STOP;
            stopFlag = true;
            robotGui.setRobotMessageLabel("Robot is stopping");
            robotGui.setEnableStopRobotButton(false);
            robotGui.setEnableMoveRobotButton(false);
            robotGui.setEnableApplyButton(false);
        }
    }*/


    /*private void stopRobot(BufferedReader inputStream){

        command = Command.CONTINUE;
        stopFlag = false;

        robotGui.setRobotMessageLabel(receiveStringMessage(inputStream));
        robotGui.setEnableApplyButton(true);
        robotGui.setEnableMoveRobotButton(true);
        robotGui.setEnableStopRobotButton(true);

    }*/

}