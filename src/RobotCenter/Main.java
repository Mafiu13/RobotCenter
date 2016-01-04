package RobotCenter;

import RobotCenter.controller.MainController;
import RobotCenter.view.MainGui;

/**
 * Created by YapYap on 2015-12-31.
 */
public class Main {

    public static void main(String[] args) {

        MainGui mainGui = new MainGui();
        MainController mainController = new MainController(mainGui);


    }

}
