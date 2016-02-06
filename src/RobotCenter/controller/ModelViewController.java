package RobotCenter.controller;

import RobotCenter.kinematics.View;
import RobotCenter.kinematics.View2;
import RobotCenter.model.JointPosition;
import RobotCenter.view.ModelViewGui;
import RobotCenter.view.RobotGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by YapYap on 2016-02-06.
 */
public class ModelViewController {

    ModelViewGui modelViewGui;
    RobotGui robotGui;
    JointPosition CJPos;
    View view;
    View2 view2;

    ModelViewController(JointPosition CJPos, RobotGui robotGui, int model) {

        this.robotGui = robotGui;
        this.CJPos = CJPos;

        modelViewGui = new ModelViewGui();
        createModelView(model);

        modelViewGui.addCloseListener(new close());
    }

    class close implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            modelViewGui.closeModelViewGuiGuiFrame();
            robotGui.setEnableModelViewButton(true);

        }
    }

    private void createModelView(int model) {

        switch (model) {

            case 0:
                view2 = new View2(CJPos.getAxis(1), CJPos.getAxis(2), CJPos.getAxis(3), CJPos.getAxis(4), CJPos.getAxis(5), CJPos.getAxis(6));
                modelViewGui.add3DModel(view2);
                break;
            case 1:
                view = new View(CJPos.getAxis(1), CJPos.getAxis(2), CJPos.getAxis(3), CJPos.getAxis(4), CJPos.getAxis(5), CJPos.getAxis(6));
                modelViewGui.add3DModel(view);
                break;
            default:
                break;
        }


    }


}
