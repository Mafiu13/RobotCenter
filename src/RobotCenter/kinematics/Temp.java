package RobotCenter.kinematics;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jedi on 2016-02-04.
 */
public class Temp {
    Kinematics kinematics3d;

    public Temp()
    {
    double x;


        kinematics3d= new Kinematics();

        {
            JFrame frame = new JFrame();
            Component add = frame.add(new JScrollPane(new View(0,-90,0,90,45,90)));
            frame.setSize(1000, 800);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        {
            JFrame frame2 = new JFrame();
            Component add = frame2.add(new JScrollPane(new View2(0,90,0,0,-45,90)));
            frame2.setSize(1000, 800);
            frame2.setVisible(true);
            frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }

}
