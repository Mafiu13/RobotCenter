package RobotCenter.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by YapYap on 2015-12-31.
 */
public class MainGui {

    private JPanel mainGuiPanel;
    private JButton createServerButton;
    private JButton closeServerButton;
    private JTextField portTextField;
    private JTabbedPane mainMenuPane;
    private JLabel robotCenterLabel;
    private JLabel informationLabel;
    private JPanel mainMenuPanel;
    private JButton exitButton;
    private JLabel newRobotLabel;
    private JLabel portLabel;
    private JButton configurateRobotButton;
    private JButton robotModelsButton;
    private JFrame frame;

    public MainGui() {

        frame = new JFrame("RobotCenter");
        frame.setContentPane(mainGuiPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public void setEnabledMainGui(boolean flag) {

        frame.setEnabled(flag);
    }

    public void addCreateServerListener(ActionListener listenForCreateServerButton) {

        createServerButton.addActionListener(listenForCreateServerButton);
    }

    public void addCloseServerListener(ActionListener listenForCloseServerButton) {

        closeServerButton.addActionListener(listenForCloseServerButton);
    }

    public void addExitListener(ActionListener listenForExitButton) {

        exitButton.addActionListener(listenForExitButton);
    }

    public void addConfigureRobotListener(ActionListener listenForConfigureRobotButton) {

        configurateRobotButton.addActionListener(listenForConfigureRobotButton);
    }

    public void addRobotModelsListener(ActionListener listenForRobotModelsButton) {

        robotModelsButton.addActionListener(listenForRobotModelsButton);
    }

    public void setPortTextField(String port) {

        portTextField.setText(port);
    }

    public String getPortTextField() {

        String port = portTextField.getText();
        return port;
    }

    public void setEnablePortTextField(boolean flag) {

        portTextField.setEnabled(flag);
    }

    public void setInformationLabel(String information) {

        informationLabel.setText(information);
    }

    public void setNewRobotLabel(String newRobot) {

        newRobotLabel.setText(newRobot);
    }

    public void addTabbedPane(JPanel robotClient, String robotClientName) {

        mainMenuPane.addTab(robotClientName, robotClient);
    }

    public void removeTabbedPane(String tabName) {

        int index = mainMenuPane.indexOfTab(tabName);
        mainMenuPane.removeTabAt(index);
    }

    public void setEnableCreateServerButton() {
        createServerButton.setEnabled(true);
        closeServerButton.setEnabled(false);
    }

    public void setEnableCloseServerButton() {
        createServerButton.setEnabled(false);
        closeServerButton.setEnabled(true);
    }

    public void setEnableConfigureRobotButton(boolean flag) {

        configurateRobotButton.setEnabled(flag);
    }

    public void setEnableRobotModelsButton(boolean flag) {

        robotModelsButton.setEnabled(flag);
    }


}
