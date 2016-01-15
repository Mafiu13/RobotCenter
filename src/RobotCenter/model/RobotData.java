package RobotCenter.model;

/**
 * Created by YapYap on 2015-12-31.
 */
public class RobotData {

    private int robotModelID;
    private String robotModel;
    private int axes;
    private JointPosition minJPose, maxJPose;
    private int maxSpeed;

    public RobotData(int robotModelID, String robotModel, int axes, JointPosition minJPose, JointPosition maxJPose, int maxSpeed) {

        this.robotModelID = robotModelID;
        this.robotModel = robotModel;
        this.axes = axes;
        this.maxJPose = maxJPose;
        this.minJPose = minJPose;
        this.maxSpeed = maxSpeed;
    }

    public int getRobotModelID() {
        return robotModelID;
    }

    public String getRobotModel() {
        return robotModel;
    }

    public int getAxes() {
        return axes;
    }

    public JointPosition getMaxJPose() {
        return maxJPose;
    }

    public JointPosition getMinJPose() {
        return minJPose;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }


}
