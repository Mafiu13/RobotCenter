package RobotCenter.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by YapYap on 2015-12-31.
 */
public class RobotGui {

    private JPanel robotGuiPanel;
    private JLabel robotClientLabel;
    private JLabel robotModelLabel;
    private JLabel axis1CJPoseLabel;
    private JLabel axis2CJPoseLabel;
    private JLabel axis3CJPoseLabel;
    private JLabel axis4CJPoseLabel;
    private JLabel axis5CJPoseLabel;
    private JLabel axis6CJPoseLabel;
    private JTextField axis1CJPoseTextField;
    private JTextField axis2CJPoseTextField;
    private JTextField axis3CJPoseTextField;
    private JTextField axis4CJPoseTextField;
    private JTextField axis5CJPoseTextField;
    private JTextField axis6CJPoseTextField;
    private JLabel axis1MJPoseLabel;
    private JLabel axis2MJPoseLabel;
    private JLabel axis3MJPoseLabel;
    private JLabel axis4MJPoseLabel;
    private JLabel axis5MJPoseLabel;
    private JLabel axis6MJPoseLabel;
    private JTextField axis1MJPoseTextField;
    private JTextField axis2MJPoseTextField;
    private JTextField axis3MJPoseTextField;
    private JTextField axis4MJPoseTextField;
    private JTextField axis5MJPoseTextField;
    private JTextField axis6MJPoseTextField;
    private JButton applyButton;
    private JButton stopRobotButton;
    private JButton disconnectRobotButton;
    private JLabel robotMessageLabel;
    private JLabel moveAlertLabel;
    private JButton moveRobotButton;

    public RobotGui(String robotName) {

        setRobotClientLabel(robotName);
       /* JFrame frame = new JFrame("RobotCenter");
        frame.setContentPane(robotGuiPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);*/
    }

    public void addApplyListener(ActionListener listenForApplyButton) {

        applyButton.addActionListener(listenForApplyButton);
    }

    public void addMoveRobotListener(ActionListener listenForMoveRobotButton) {

        moveRobotButton.addActionListener(listenForMoveRobotButton);
    }

    public void addStopRobotListener(ActionListener listenForStopRobotButton) {

        stopRobotButton.addActionListener(listenForStopRobotButton);
    }

    public void addDisconnectRobotListener(ActionListener listenForDisconnectRobotButton) {

        disconnectRobotButton.addActionListener(listenForDisconnectRobotButton);
    }

    public void setEnableMoveRobotButton(boolean flag) {

        moveRobotButton.setEnabled(flag);
    }

    public void setRobotClientLabel(String robotClient) {

        robotClientLabel.setText(robotClient);
    }

    public void setRobotModelLabel(String robotModel) {

        robotModelLabel.setText(robotModel);
    }

    public void setMoveAlertLabel(String moveAlert) {

        moveAlertLabel.setText(moveAlert);
    }

    public void setAxisCJPoseTextField(int i, String axisCJPose) {

        switch (i) {

            case 1:
                axis1CJPoseTextField.setText(axisCJPose);
                break;
            case 2:
                axis2CJPoseTextField.setText(axisCJPose);
                break;
            case 3:
                axis3CJPoseTextField.setText(axisCJPose);
                break;
            case 4:
                axis4CJPoseTextField.setText(axisCJPose);
                break;
            case 5:
                axis5CJPoseTextField.setText(axisCJPose);
                break;
            case 6:
                axis6CJPoseTextField.setText(axisCJPose);
                break;
            default:

        }
    }

    public void setAxis1CJPoseTextField(String axis1CJPose) {

        axis1CJPoseTextField.setText(axis1CJPose);
    }

    public void setAxis2CJPoseTextField(String axis2CJPose) {

        axis2CJPoseTextField.setText(axis2CJPose);
    }

    public void setAxis3CJPoseTextField(String axis3CJPose) {

        axis3CJPoseTextField.setText(axis3CJPose);
    }

    public void setAxis4CJPoseTextField(String axis4CJPose) {

        axis4CJPoseTextField.setText(axis4CJPose);
    }

    public void setAxis5CJPoseTextField(String axis5CJPose) {

        axis5CJPoseTextField.setText(axis5CJPose);
    }

    public void setAxis6CJPoseTextField(String axis6CJPose) {

        axis6CJPoseTextField.setText(axis6CJPose);
    }

    public void setRobotMessageLabel(String robotMessage) {

        robotMessageLabel.setText(robotMessage);
    }

    public String getAxisMJPoseTextField(int i) {

        String axisMJPose;

        switch (i) {

            case (1): {
                axisMJPose = axis1MJPoseTextField.getText();
                return axisMJPose;
            }
            case (2): {
                axisMJPose = axis2MJPoseTextField.getText();
                return axisMJPose;
            }
            case (3): {
                axisMJPose = axis3MJPoseTextField.getText();
                return axisMJPose;
            }
            case (4): {
                axisMJPose = axis4MJPoseTextField.getText();
                return axisMJPose;
            }
            case (5): {
                axisMJPose = axis5MJPoseTextField.getText();
                return axisMJPose;
            }
            case (6): {
                axisMJPose = axis6MJPoseTextField.getText();
                return axisMJPose;
            }
            default:
                return "0";
        }
    }

    public void setColorAxisMJPoseTextField(int i, boolean color) {

        switch (i) {

            case (1): {
                if (color) {
                    axis1MJPoseTextField.setForeground(Color.BLACK);
                } else {
                    axis1MJPoseTextField.setForeground(Color.RED);
                }
            }
            case (2): {
                if (color) {
                    axis2MJPoseTextField.setForeground(Color.BLACK);
                } else {
                    axis2MJPoseTextField.setForeground(Color.RED);
                }
            }
            case (3): {
                if (color) {
                    axis3MJPoseTextField.setForeground(Color.BLACK);
                } else {
                    axis3MJPoseTextField.setForeground(Color.RED);
                }
            }
            case (4): {
                if (color) {
                    axis4MJPoseTextField.setForeground(Color.BLACK);
                } else {
                    axis4MJPoseTextField.setForeground(Color.RED);
                }
            }
            case (5): {
                if (color) {
                    axis5MJPoseTextField.setForeground(Color.BLACK);
                } else {
                    axis5MJPoseTextField.setForeground(Color.RED);
                }
            }
            case (6): {
                if (color) {
                    axis6MJPoseTextField.setForeground(Color.BLACK);
                } else {
                    axis6MJPoseTextField.setForeground(Color.RED);
                }
            }
        }
    }

    public String getAxis1MJPoseTextField() {

        String axis1MJPose = axis1MJPoseTextField.getText();
        return axis1MJPose;
    }

    public String getAxis2MJPoseTextField() {

        String axis2MJPose = axis2MJPoseTextField.getText();
        return axis2MJPose;
    }

    public String getAxis3MJPoseTextField() {

        String axis3MJPose = axis3MJPoseTextField.getText();
        return axis3MJPose;
    }

    public String getAxis4MJPoseTextField() {

        String axis4MJPose = axis4MJPoseTextField.getText();
        return axis4MJPose;
    }

    public String getAxis5MJPoseTextField() {

        String axis5MJPose = axis5MJPoseTextField.getText();
        return axis5MJPose;
    }

    public String getAxis6MJPoseTextField() {

        String axis6MJPose = axis6MJPoseTextField.getText();
        return axis6MJPose;
    }

    public JPanel getRobotGuiPanel() {

        return robotGuiPanel;
    }


    ///////////////////////////////////////
    /////Pomoc
    public void setJPose(String JPose) {

        axis1CJPoseTextField.setText(JPose);
    }
////////////////////////////////////////////////


}
