package RobotCenter.model;

/**
 * Created by YapYap on 2015-12-31.
 */
public class JointPosition {

    private int axis1;
    private int axis2;
    private int axis3;
    private int axis4;
    private int axis5;
    private int axis6;

    public JointPosition(int axis1, int axis2, int axis3, int axis4, int axis5, int axis6) {

        this.axis1 = axis1;
        this.axis2 = axis2;
        this.axis3 = axis3;
        this.axis4 = axis4;
        this.axis5 = axis5;
        this.axis6 = axis6;

    }

    public void setJointPosition(int i, int axis){

        switch(i) {
            case(1):
                this.axis1 = axis;
                break;
            case(2):
                this.axis2 = axis;
                break;
            case(3):
                this.axis3 = axis;
                break;
            case(4):
                this.axis4 = axis;
                break;
            case(5):
                this.axis5 = axis;
                break;
            case(6):
                this.axis6 = axis;
                break;
        }
    }

    public int getJointPosition(int i){

        switch(i) {
            case(1):
                return axis1;
            case(2):
                return axis2;
            case(3):
                return axis3;
            case(4):
                return axis4;
            case(5):
                return axis5;
            case(6):
                return axis6;
            default:
                return 0;
        }
    }

    public int getAxis1() {
        return axis1;
    }

    public int getAxis2() {
        return axis2;
    }

    public int getAxis3() {
        return axis3;
    }

    public int getAxis4() {
        return axis4;
    }

    public int getAxis5() {
        return axis5;
    }

    public int getAxis6() {
        return axis6;
    }

}
