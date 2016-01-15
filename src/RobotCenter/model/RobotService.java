package RobotCenter.model;

/**
 * Created by YapYap on 2016-01-07.
 */
public class RobotService {

    private RobotData robotData;

    public RobotService(RobotData robotData) {

        this.robotData = robotData;
    }

    public boolean checkIfInJPoseRangeAxis(int i, JointPosition moveToJointPosition) {

        switch (i) {

            case 1: {
                if (!(moveToJointPosition.getAxis(1) > robotData.getMinJPose().getAxis(1) && moveToJointPosition.getAxis(1) < robotData.getMaxJPose().getAxis(1))) {
                    return false;
                }
                return true;
            }
            case 2: {
                if (!(moveToJointPosition.getAxis(2) > robotData.getMinJPose().getAxis(2) && moveToJointPosition.getAxis(2) < robotData.getMaxJPose().getAxis(2))) {
                    return false;
                }
                return true;
            }
            case 3: {

                if (!(moveToJointPosition.getAxis(3) > robotData.getMinJPose().getAxis(3) && moveToJointPosition.getAxis(3) < robotData.getMaxJPose().getAxis(3))) {
                    return false;
                }
                return true;
            }
            case 4: {

                if (!(moveToJointPosition.getAxis(4) > robotData.getMinJPose().getAxis(4) && moveToJointPosition.getAxis(4) < robotData.getMaxJPose().getAxis(4))) {
                    return false;
                }
                return true;
            }
            case 5: {

                if (!(moveToJointPosition.getAxis(5) > robotData.getMinJPose().getAxis(5) && moveToJointPosition.getAxis(5) < robotData.getMaxJPose().getAxis(5))) {
                    return false;
                }
                return true;
            }
            case 6: {

                if (!(moveToJointPosition.getAxis(6) > robotData.getMinJPose().getAxis(6) && moveToJointPosition.getAxis(6) < robotData.getMaxJPose().getAxis(6))) {
                    return false;
                }
                return true;
            }
            default:
                return false;
        }
    }

    public boolean checkIfInSpeedRange(int speed) {
        if (!(speed > 100 && speed < robotData.getMaxSpeed())) {
            return false;
        }
        return true;
    }

}
