package RobotCenter.controller;

import RobotCenter.model.JSONParse;
import RobotCenter.model.PanelTexts;
import RobotCenter.model.RobotData;
import RobotCenter.model.TypeConverter;
import RobotCenter.view.MainGui;
import RobotCenter.view.RobotConnectedGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

/**
 * Created by YapYap on 2015-12-31.
 */
public class MainController {

    private MainGui mainGui;
    private PanelTexts panelTexts = new PanelTexts();
    private TypeConverter typeConverter;
    private static ServerSocket serverSocket;
    private static Socket robotClientSocket;
    private int maxRobotClientsCount;
    private List<RobotController> robotControllers;
    private List<String> tabNames;
    private List<RobotData> robotDatas;
    private static final String defaultPort = "50000";
    private static final int maxPort = 60000;
    private static final int minPort = 4000;

    public MainController(MainGui mainGui) {

        this.mainGui = mainGui;

        maxRobotClientsCount = 3;
        robotControllers = new ArrayList(maxRobotClientsCount);
        tabNames = new ArrayList(maxRobotClientsCount);
        tabNames.add("Main Menu");

        JSONParse jsonParse = new JSONParse();
        this.robotDatas = jsonParse.getRobotDatasList();

        mainGui.setInformationLabel(panelTexts.getInformationLabelText1());
        mainGui.setNewRobotLabel(panelTexts.getNewRobotLabelText3());
        mainGui.setPortTextField(defaultPort);
        mainGui.setEnableCreateServerButton();
        mainGui.setEnablePortTextField(true);
        mainGui.setEnableConfigureRobotButton(false);

        mainGui.addCreateServerListener(new createServer());
        mainGui.addCloseServerListener(new closeServer());
        mainGui.addConfigureRobotListener(new configureRobot());
        mainGui.addRobotModelsListener(new robotModels());
        mainGui.addExitListener(new exit());
    }

    class createServer implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            int port = convertPortToInt();

            if (port != 0) {
                if (checkPort(port)) {

                    try {
                        serverSocket = new ServerSocket(port);
                    } catch (IOException e1) {
                        mainGui.setInformationLabel(panelTexts.getInformationLabelText3() + port);
                    }
                    mainGui.setInformationLabel(panelTexts.getInformationLabelText2() + port);
                    mainGui.setNewRobotLabel(panelTexts.getNewRobotLabelText1());
                    mainGui.setEnablePortTextField(false);
                    mainGui.setEnableCloseServerButton();

                    connectNewRobotClient(serverSocket);

                } else {
                    mainGui.setInformationLabel(panelTexts.getInformationLabelText3() + port);
                }

            } else {
                mainGui.setInformationLabel(panelTexts.getInformationLabelText4());
            }
        }

    }

    class closeServer implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            closeRobotSockets();
            mainGui.setInformationLabel(panelTexts.getInformationLabelText1());
            mainGui.setNewRobotLabel(panelTexts.getNewRobotLabelText3());
            mainGui.setEnablePortTextField(true);
            mainGui.setEnableCreateServerButton();
            mainGui.setEnableConfigureRobotButton(false);

        }

    }

    class configureRobot implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            RobotConnectedGui robotConnectedGui = new RobotConnectedGui();
            addLabelsToConnectedGuiComboBox(robotConnectedGui);
            RobotConnectedController robotConnectedController = new RobotConnectedController(robotControllers, tabNames, robotDatas, robotClientSocket, mainGui, panelTexts, robotConnectedGui);

        }

    }

    class robotModels implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            RobotModelsController robotModelsController = new RobotModelsController(mainGui,robotDatas, typeConverter);

        }
    }

    class exit implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            System.exit(0);
        }
    }

    private void connectNewRobotClient(ServerSocket serverSocket) {


        Thread serverThread = new Thread() {
            public void run() {
                boolean flag = true;
                while (flag)

                {
                    try {
                        robotClientSocket = serverSocket.accept();

                        if (robotControllers.size() <= maxRobotClientsCount) {

                            RobotConnectedGui robotConnectedGui = new RobotConnectedGui();
                            addLabelsToConnectedGuiComboBox(robotConnectedGui);
                            RobotConnectedController robotConnectedController = new RobotConnectedController(robotControllers, tabNames, robotDatas, robotClientSocket, mainGui, panelTexts, robotConnectedGui);

                        } else {
                            robotClientSocket.close();
                        }

                    } catch (IOException e) {
                        flag = false;

                    }

                }
            }
        };
        serverThread.start();
    }

    ///NIEPOTRZEBNE ZAMKNIECIE ZAKLADEK WEWNATRZ KORELORERA ROBOTA
    private void clearTabPanel() {

        for (int i = tabNames.size() - 1; i > 0; i--) {

            mainGui.removeTabbedPane(tabNames.get(i));
            tabNames.remove(i);
        }
    }
    /////////////////////////////

    private void closeRobotSockets() {

        for (int i = robotControllers.size() - 1; i >= 0; i--) {

            robotControllers.get(i).closeRobotClient();
        }
    }

    private int convertPortToInt() {

        String strPort = mainGui.getPortTextField();
        try {
            int intPort = Integer.parseInt(strPort);
            return intPort;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private boolean checkPort(int port) {

        if (port < maxPort && port > minPort) {
            return true;
        } else {
            return false;
        }
    }

    private void addLabelsToConnectedGuiComboBox(RobotConnectedGui robotConnectedGui) {

        for (int i = 0; i < robotDatas.size(); i++) {
            robotConnectedGui.setRobotModelLabelComboBox(robotDatas.get(i).getRobotModel());
        }
    }

}
