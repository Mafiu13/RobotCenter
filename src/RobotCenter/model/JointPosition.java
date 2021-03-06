package RobotCenter.model;

import java.awt.geom.Arc2D;

/**
 * Created by YapYap on 2015-12-31.
 */
public class JointPosition {

    private double axis1;
    private double axis2;
    private double axis3;
    private double axis4;
    private double axis5;
    private double axis6;

    public JointPosition() {

    }

    public JointPosition(double axis1, double axis2, double axis3, double axis4, double axis5, double axis6) {

        this.axis1 = axis1;
        this.axis2 = axis2;
        this.axis3 = axis3;
        this.axis4 = axis4;
        this.axis5 = axis5;
        this.axis6 = axis6;

    }

    public void setJointPosition(int i, double axis) {

        switch (i) {
            case (1):
                this.axis1 = axis;
                break;
            case (2):
                this.axis2 = axis;
                break;
            case (3):
                this.axis3 = axis;
                break;
            case (4):
                this.axis4 = axis;
                break;
            case (5):
                this.axis5 = axis;
                break;
            case (6):
                this.axis6 = axis;
                break;
        }
    }

    public double getAxis(int i) {

        switch (i) {
            case (1):
                return axis1;
            case (2):
                return axis2;
            case (3):
                return axis3;
            case (4):
                return axis4;
            case (5):
                return axis5;
            case (6):
                return axis6;
            default:
                return 0;
        }
    }

    public String getStrAxis(int i) {

        switch (i) {
            case (1):
                return TypeConverter.convertDoubleToStr(axis1);
            case (2):
                return TypeConverter.convertDoubleToStr(axis2);
            case (3):
                return TypeConverter.convertDoubleToStr(axis3);
            case (4):
                return TypeConverter.convertDoubleToStr(axis4);
            case (5):
                return TypeConverter.convertDoubleToStr(axis5);
            case (6):
                return TypeConverter.convertDoubleToStr(axis6);
            default:
                return "ERROR";
        }
    }

}
