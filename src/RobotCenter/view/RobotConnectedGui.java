package RobotCenter.view;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by YapYap on 2016-01-02.
 */
public class RobotConnectedGui {
    private JLabel newRobotConLabel;
    private JLabel chooseRobotNameLabel;
    private JTextField robotNameTextField;
    private JLabel robotNameLabel;
    private JComboBox robotModelLabelComboBox;
    private JLabel robotModelLabel;
    private JLabel alertLabel;
    private JPanel robotConnectedGuiPanel;
    private JButton OKButton;
    private JButton cancelButton;
    JFrame frame;

    public RobotConnectedGui() {

        frame = new JFrame("New Robot Connected!");
        frame.setContentPane(robotConnectedGuiPanel);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public void addOKListener(ActionListener listenForOKButton) {

        OKButton.addActionListener(listenForOKButton);
    }

    public void addCancelListener(ActionListener listenForCancelButton) {

        cancelButton.addActionListener(listenForCancelButton);
    }

    public void setAlertLabel(String alert) {

        alertLabel.setText(alert);
    }

    public void setRobotModelLabelComboBox(String robotModelLabel) {

        robotModelLabelComboBox.addItem(robotModelLabel);
    }

    public int getRobotModelLabelComboBox() {

        return robotModelLabelComboBox.getSelectedIndex();
    }

    public String getRobotNameTextField() {

        String robotName = robotNameTextField.getText();
        return robotName;
    }

    public void closeRobotConnectedGuiFrame() {
        frame.dispose();
    }


}

