package RobotCenter.controller;

import RobotCenter.model.PanelTexts;
import RobotCenter.model.RobotData;
import RobotCenter.view.MainGui;
import RobotCenter.view.RobotConnectedGui;
import RobotCenter.view.RobotGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.util.List;

/**
 * Created by YapYap on 2016-01-02.
 */
public class RobotConnectedController {

    private List<RobotController> robotControllers;
    private List<String> tabNames;
    private List<RobotData> robotDatas;
    private Socket robotClientSocket;
    private MainGui mainGui;
    private PanelTexts panelTexts;
    private RobotConnectedGui robotConnectedGui;
    private RobotGui robotGui;
    private RobotData robotData;
    String tabName;


    public RobotConnectedController(List<RobotController> robotControllers, List<String> tabNames,List<RobotData> robotDatas, Socket robotClientSocket, MainGui mainGui, PanelTexts panelTexts, RobotConnectedGui robotConnectedGui) {

        this.mainGui = mainGui;
        this.panelTexts = panelTexts;
        this.robotControllers = robotControllers;
        this.robotClientSocket = robotClientSocket;
        this.robotConnectedGui = robotConnectedGui;
        this.tabNames = tabNames;
        this.robotDatas = robotDatas;

        mainGui.setEnabledMainGui(false);
        robotConnectedGui.setAlertLabel(panelTexts.getAlertLabelText1());
        robotConnectedGui.addOKListener(new OK());
        robotConnectedGui.addCancelListener(new cancel());
    }

    class OK implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            tabName = robotConnectedGui.getRobotNameTextField();
            if (!(tabNames.contains(tabName))) {

                if (tabName != null && !tabName.isEmpty()) {

                    int indexRobotData = robotConnectedGui.getRobotModelLabelComboBox();
                    robotData = robotDatas.get(indexRobotData);

                    robotGui = new RobotGui(tabName);
                    mainGui.addTabbedPane(robotGui.getRobotGuiPanel(), tabName);
                    mainGui.setNewRobotLabel(panelTexts.getNewRobotLabelText1());
                    mainGui.setEnableConfigurationRobotButton(false);
                    tabNames.add(tabName);
                    RobotController robotController;
                    (robotController = new RobotController(mainGui, robotGui, robotClientSocket, robotControllers, tabNames, tabName, panelTexts,robotData)).start();
                    robotControllers.add(robotController);
                    closeRobotConnectedController();

                } else {
                    robotConnectedGui.setAlertLabel(panelTexts.getAlertLabelText3());
                }
            } else
                robotConnectedGui.setAlertLabel(panelTexts.getAlertLabelText4());

        }
    }

    class cancel implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            mainGui.setNewRobotLabel(panelTexts.getNewRobotLabelText2());
            mainGui.setEnableConfigurationRobotButton(true);
            closeRobotConnectedController();
        }
    }


    public void closeRobotConnectedController() {

        mainGui.setEnabledMainGui(true);
        robotConnectedGui.closeRobotConnectedGuiFrame();

    }


}
