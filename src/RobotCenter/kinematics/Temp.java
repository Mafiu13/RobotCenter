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

  //      kinematics3d = new Kinematics();
  //      x=kinematics3d.points.get(0).getX();

//        System.out.print(x);

        kinematics3d= new Kinematics();

        {
            JFrame frame = new JFrame();
            Component add = frame.add(new JScrollPane(new View()));
            frame.setSize(1000, 800);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        {
            JFrame frame2 = new JFrame();
            Component add = frame2.add(new JScrollPane(new View2()));
            frame2.setSize(1000, 800);
            frame2.setVisible(true);
            frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }

}
