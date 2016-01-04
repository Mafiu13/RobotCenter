package RobotCenter.controller;

import RobotCenter.model.JointPosition;
import RobotCenter.model.PanelTexts;
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
    private int robotControllerIndex;
    private String tabName;
    private boolean flag = true;
    private byte[] tabBytes = new byte[50];


    public RobotController(MainGui mainGui, RobotGui robotGui, Socket robotClientSocket, List<RobotController> robotControllers, List<String> tabNames, String tabName, PanelTexts panelTexts) {

        this.robotGui = robotGui;
        this.robotClientSocket = robotClientSocket;
        this.robotControllers = robotControllers;
        this.panelTexts = panelTexts;
        this.mainGui = mainGui;
        this.tabName = tabName;
        this.tabNames = tabNames;

        robotControllerIndex = robotControllers.size();
        currentJointPosition = new JointPosition(0,0,0,0,0,0);
        moveToJointPosition = new JointPosition(0,0,0,0,0,0);



        robotGui.addMoveListener(new moveRobot());
        robotGui.addStopRobotListener(new stopRobot());
        robotGui.addDisconnectRobotListener(new disconnectRobot());

    }

    public void run() {



        sendStringMessage("Welcome To RobotCenter Server");
        robotGui.setRobotMessageLabel(receiveStringMessage());

        while(flag){

            for(int i=1; i<7;i++){

                String cJPoseStr = receiveStringMessage();
                robotGui.setAxisCJPoseTextField(i,cJPoseStr);
                System.out.println(i + "\n" + cJPoseStr);
                currentJointPosition.setJointPosition(i,convertStrToInt(cJPoseStr));
                //sendStringMessage("Odebrano JPOS" + i);
            }
            sendStringMessage("JPoseUstawione");

        }


    }


    class moveRobot implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            robotGui.setRobotModelLabel("Move");
        }
    }

    class stopRobot implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            robotGui.setRobotModelLabel("Stop");
        }
    }

    class disconnectRobot implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            closeRobotClient();
        }
    }

    private void sendStringMessage(String messsage) {

        PrintWriter outputStream=null;
        try {
            outputStream = new PrintWriter(robotClientSocket.getOutputStream());
        } catch (IOException e) {
            if(flag)
            e.printStackTrace();
        }



        outputStream.println(messsage);
        outputStream.flush();
    }

    private void sendIntMessage(int message) {

        PrintWriter outputStream=null;
        try {
            outputStream = new PrintWriter(robotClientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


        String strMessage = Integer.toString(message);
        outputStream.println(strMessage);
        outputStream.flush();
    }

    private String receiveStringMessage() {



        BufferedReader inputStream = null;
        try {
            inputStream = new BufferedReader(new InputStreamReader(robotClientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String message = null;

        try {
            message = inputStream.readLine();
            //inputStream.read();
        } catch (IOException e) {
            //e.printStackTrace();

            ////////////ZAMKNIECIE CLIENTSOCKETA JEZELI BRAK ODPOWIEDZI
            if(flag)
                closeRobotClient();
        }


        while(message==null){
        try {
            message = inputStream.readLine();
        } catch (IOException e) {
            //e.printStackTrace();

            ////////////ZAMKNIECIE CLIENTSOCKETA JEZELI BRAK ODPOWIEDZI
            if(flag)
            closeRobotClient();
        }}

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

    public int convertStrToInt(String str){

        try {
            int strToInt = Integer.parseInt(str);
            return strToInt;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public String convertIntToStr(int intToStr){

        String str = Integer.toString(intToStr);
        return  str;
    }

}
