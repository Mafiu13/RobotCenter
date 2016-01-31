package RobotCenter.controller;

import RobotCenter.kinematics.Kinematics;
import RobotCenter.kinematics.Point3D;
import RobotCenter.model.JointPosition;
import RobotCenter.view.GraphicViewGui;
import RobotCenter.view.RobotGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by YapYap on 2016-01-17.
 */
public class GraphicViewController {

    GraphicViewGui graphicViewGui;
    RobotGui robotGui;
    Point3D point3D;
    Kinematics kinematics;


    public GraphicViewController(RobotGui robotGui) {

        this.robotGui = robotGui;
        graphicViewGui = new GraphicViewGui();
        kinematics = new Kinematics();

        point3D.setLine(graphicViewGui.getLine2D());
        point3D.setCentre(graphicViewGui.getWidthtRobotViewJPanel() / 2, graphicViewGui.getWidthtRobotViewJPanel() / 2);
        point3D.setSize((graphicViewGui.getWidthtRobotViewJPanel() + graphicViewGui.getHeightRobotViewJPanel()) / 6);
        point3D.setHeight(graphicViewGui.getHeightRobotViewJPanel());
        point3D.setWidth(graphicViewGui.getWidthtRobotViewJPanel());

        graphicViewGui.addCloseListener(new close());

    }

    class close implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            setVisibleGraphicViewGui(false);
            robotGui.setEnableGraphicViewButton(true);

        }
    }

    public void setVisibleGraphicViewGui(boolean flag) {

        graphicViewGui.setVisibleFrame(flag);
    }

    public void closeGraphicViewController() {

        graphicViewGui.closeGraphicViewGuiFrame();
    }

    public void paintRobot (int model, JointPosition CJPos){

        point3D.setGraphics(graphicViewGui.getGraphics());

        switch (model){

            case 0:
                kinematics.setAngle2(CJPos.getAxis(1),CJPos.getAxis(2),CJPos.getAxis(3),CJPos.getAxis(4),CJPos.getAxis(5),CJPos.getAxis(6));
                break;
            case 1:
                kinematics.setAngle1(CJPos.getAxis(1),CJPos.getAxis(2),CJPos.getAxis(3),CJPos.getAxis(4),CJPos.getAxis(5),CJPos.getAxis(6));
                break;
            default:
                break;
        }

    }


}
