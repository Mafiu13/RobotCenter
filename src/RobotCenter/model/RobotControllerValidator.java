package RobotCenter.model;

import RobotCenter.view.RobotGui;

/**
 * Created by YapYap on 2016-01-15.
 */
public class RobotControllerValidator {

    private JointPosition moveToJointPosition;
    private RobotGui robotGui;
    private PanelTexts panelTexts;
    private RobotService robotService;
    private int speed;


    public RobotControllerValidator(JointPosition moveToJointPosition, RobotGui robotGui, PanelTexts panelTexts, RobotService robotService) {

        this.moveToJointPosition = moveToJointPosition;
        this.robotGui = robotGui;
        this.panelTexts = panelTexts;
        this.robotService = robotService;

    }

    public boolean checkIfMoveCommandIsNumber() {

        boolean flag = true;

        for (int i = 1; i <= 6; i++) {

            try {
                double axis = TypeConverter.convertStrToDouble(robotGui.getAxisMJPoseTextField(i));
                moveToJointPosition.setJointPosition(i, axis);
                robotGui.setColorAxisMJPoseTextField(i, true);
            } catch (NumberFormatException e) {
                robotGui.setMoveAlertLabel(panelTexts.getMoveAlertLabelText3());
                robotGui.setColorAxisMJPoseTextField(i, false);
                flag = false;
            }

        }
        try {
            speed = TypeConverter.convertStrToInt(robotGui.getSpeedTextField());
            robotGui.setColorSpeedTextField(true);
        } catch (Exception e) {
            robotGui.setMoveAlertLabel(panelTexts.getMoveAlertLabelText3());
            robotGui.setColorSpeedTextField(false);
            flag = false;
        }

        return flag;

    }

    public boolean checkMoveCommand() {

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
        if (robotService.checkIfInSpeedRange(speed)) {
            robotGui.setColorSpeedTextField(true);
        } else {
            robotGui.setColorSpeedTextField(false);
            robotGui.setMoveAlertLabel(panelTexts.getMoveAlertLabelText2());
            flag = false;
        }

        return flag;
    }

    public void correctMoveToJointPosition() {

        for (int i = 1; i <= 6; i++) {

            String strJPose = robotGui.getAxisMJPoseTextField(i);
            int strLength = strJPose.length();

            while (strLength < 7) {

                if (strJPose.contains(".")) {
                    strJPose = strJPose + "0";
                    strLength = strJPose.length();
                } else
                    strJPose = strJPose + ".";
            }
            while (strLength > 7) {
                strJPose = strJPose.substring(0, strJPose.length() - 1);
                strLength = strJPose.length();
            }

            robotGui.setAxisMJPoseTextField(i, strJPose);
            moveToJointPosition.setJointPosition(i, TypeConverter.convertStrToDouble(robotGui.getAxisMJPoseTextField(i)));

        }

    }

    public JointPosition getMoveToJointPosition() {

        return moveToJointPosition;
    }

    public int getSpeed() {

        return speed;
    }

}
