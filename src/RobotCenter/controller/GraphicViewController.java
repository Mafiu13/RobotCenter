package RobotCenter.controller;

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


    GraphicViewController(RobotGui robotGui) {

        this.robotGui = robotGui;
        graphicViewGui = new GraphicViewGui();

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


}
