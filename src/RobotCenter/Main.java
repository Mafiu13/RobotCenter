package RobotCenter;

import RobotCenter.controller.MainController;
import RobotCenter.kinematics.Kinematics;
import RobotCenter.kinematics.Temp;
import RobotCenter.kinematics.View;
import RobotCenter.kinematics.View2;
import RobotCenter.view.MainGui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by YapYap on 2015-12-31.
 */
public class Main {

    public static void main(String[] args) {

        MainGui mainGui = new MainGui();
        MainController mainController = new MainController(mainGui);
        //  Temp temp = new Temp();
    }

}
