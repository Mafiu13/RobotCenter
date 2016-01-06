package RobotCenter.model;

/**
 * Created by YapYap on 2015-12-31.
 */
public class RobotData {

    private int robotModelID;
    private String robotModel;
    private double minJPose, maxJPose;
    private int minSpeed, maxSpeed;

    public RobotData(int robotModelID, String robotModel, double minJPose, double maxJPose) {

        this.robotModelID = robotModelID;
        this.robotModel = robotModel;
        this.maxJPose = maxJPose;
        this.minJPose = minJPose;
        //this.maxSpeed = maxSpeed;
        //this.minSpeed = minSpeed;

    }

    public int getRobotModelID() {
        return robotModelID;
    }

    public String getRobotModel() {
        return robotModel;
    }

    public double getMaxJPose() {
        return maxJPose;
    }

    public double getMinJPose() {
        return minJPose;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getMinSpeed() {
        return minSpeed;
    }


}
