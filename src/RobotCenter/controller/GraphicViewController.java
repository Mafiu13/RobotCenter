package RobotCenter.controller;

import RobotCenter.kinematics.Kinematyka;
import RobotCenter.kinematics.Punkt3D;
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
    Punkt3D punkt3D;
    Kinematyka kinematyka;


    public GraphicViewController(RobotGui robotGui) {

        this.robotGui = robotGui;
        graphicViewGui = new GraphicViewGui();
        kinematyka = new Kinematyka();

        punkt3D.setLinia(graphicViewGui.getLine2D());
        punkt3D.setSrodek(graphicViewGui.getWidthtRobotViewJPanel()/2, graphicViewGui.getWidthtRobotViewJPanel()/2);
        punkt3D.setWielkosc((graphicViewGui.getWidthtRobotViewJPanel() + graphicViewGui.getHeightRobotViewJPanel()) / 6);

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

        punkt3D.setGrafika(graphicViewGui.getGraphics());

        switch (model){

            case 0:
                kinematyka.setAngle2(CJPos.getAxis(1),CJPos.getAxis(2),CJPos.getAxis(3),CJPos.getAxis(4),CJPos.getAxis(5),CJPos.getAxis(6));
                break;
            case 1:
                kinematyka.setAngle1(CJPos.getAxis(1),CJPos.getAxis(2),CJPos.getAxis(3),CJPos.getAxis(4),CJPos.getAxis(5),CJPos.getAxis(6));
                break;
            case 2:
                kinematyka.setAngle2(50,20,100,20,20,10);
                break;
            default:
                break;
        }

    }


}
