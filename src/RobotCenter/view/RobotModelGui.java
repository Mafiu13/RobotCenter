package RobotCenter.view;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by YapYap on 2016-01-15.
 */
public class RobotModelGui {
    private JComboBox robotModelComboBox;
    private JLabel axesInfoLabel;
    private JLabel axis1MinJPoseLabel;
    private JTextField axis1MinJPoseTextField;
    private JLabel axis2MinJPoseLabel;
    private JTextField axis2MinJPoseTextField;
    private JLabel axis3MinJPoseLabel;
    private JLabel axis4MinJPoseLabel;
    private JLabel axis5MinJPoseLabel;
    private JTextField axis3MinJPoseTextField;
    private JTextField axis4MinJPoseTextField;
    private JTextField axis5MinJPoseTextField;
    private JLabel axis6MinJPoseLabel;
    private JTextField axis6MinJPoseTextField;
    private JLabel axis1MaxJPoseLabel;
    private JLabel axis2MaxJPoseLabel;
    private JLabel axis3MaxJPoseLabel;
    private JLabel axis4MaxJPoseLabel;
    private JLabel axis5MaxJPoseLabel;
    private JLabel axis6MaxJPoseLabel;
    private JTextField axis1MaxJPoseTextField;
    private JTextField axis2MaxJPoseTextField;
    private JTextField axis3MaxJPoseTextField;
    private JTextField axis4MaxJPoseTextField;
    private JTextField axis5MaxJPoseTextField;
    private JTextField axis6MaxJPoseTextField;
    private JTextField maxSpeedTextField;
    private JLabel speedLabel;
    private JButton OKButton;
    private JPanel robotModelGuiJPanel;
    private JFrame frame;

    public RobotModelGui() {

        frame = new JFrame("Robot Model Informations");
        frame.setContentPane(robotModelGuiJPanel);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public void addOKListener(ActionListener listenForOKButton) {

        OKButton.addActionListener(listenForOKButton);
    }

    public void addRobotModelListener(ActionListener listenForRobotModelComboBox) {

        robotModelComboBox.addActionListener(listenForRobotModelComboBox);
    }

    public void setRobotModelComboBox(String robotModel) {

        robotModelComboBox.addItem(robotModel);
    }

    public int getRobotModelIdxComboBox() {

        return robotModelComboBox.getSelectedIndex();
    }

    public void setAxesInfoLabel(String axesInfo) {

        axesInfoLabel.setText(axesInfo + " - Axis Robot");
    }

    public void setAxisMinJPoseTextField(int i, String axisMinJPose) {

        switch (i) {

            case 1:
                axis1MinJPoseTextField.setText(axisMinJPose);
                break;
            case 2:
                axis2MinJPoseTextField.setText(axisMinJPose);
                break;
            case 3:
                axis3MinJPoseTextField.setText(axisMinJPose);
                break;
            case 4:
                axis4MinJPoseTextField.setText(axisMinJPose);
                break;
            case 5:
                axis5MinJPoseTextField.setText(axisMinJPose);
                break;
            case 6:
                axis6MinJPoseTextField.setText(axisMinJPose);
                break;
            default:

        }
    }

    public void setAxisMaxJPoseTextField(int i, String axisMaxJPose) {

        switch (i) {

            case 1:
                axis1MaxJPoseTextField.setText(axisMaxJPose);
                break;
            case 2:
                axis2MaxJPoseTextField.setText(axisMaxJPose);
                break;
            case 3:
                axis3MaxJPoseTextField.setText(axisMaxJPose);
                break;
            case 4:
                axis4MaxJPoseTextField.setText(axisMaxJPose);
                break;
            case 5:
                axis5MaxJPoseTextField.setText(axisMaxJPose);
                break;
            case 6:
                axis6MaxJPoseTextField.setText(axisMaxJPose);
                break;
            default:

        }
    }

    public void setMaxSpeedTextField(String maxSpeed) {

        maxSpeedTextField.setText(maxSpeed);
    }

    public void closeRobotModelsGuiFrame() {

        frame.dispose();
    }
}
