package RobotCenter.kinematics;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.lang.annotation.Target;
import java.util.Enumeration;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.media.j3d.Behavior;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupOnAWTEvent;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;

/**
 * Created by Jedi on 2016-02-04.
 */

public class SBehavior extends Behavior {

    private TransformGroup targetTG;

    //private Transform3D rotation = new Transform3D();

    private double angle = 0.0;
    //private double angle2 = 0.0;
    //private double angle3 = 0.0;

    // create SimpleBehavior
    SBehavior(TransformGroup targetTG) {
        this.targetTG = targetTG;
    }

    // initialize the Behavior
    //     set initial wakeup condition
    //     called when behavior beacomes live
    public void initialize() {
        // set initial wakeup condition
        this.wakeupOn(new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED));
    }

    public void processStimulus(Enumeration criteria) {
        // decode event

        Transform3D rotation1 = new Transform3D();
        rotation1.rotX(degToRad(90));

        Transform3D rotation2 = new Transform3D();
        rotation2.rotY(degToRad(180));

        Transform3D rotation3 = new Transform3D();
        rotation3.rotZ(degToRad(180));


        Transform3D rotation = new Transform3D();
        rotation.mul(rotation1, rotation2);
        rotation.mul(rotation, rotation3);

        //      targetTG.setTransform(rotation);
        // do what is necessary
        angle += 0.1;


        Transform3D rotation4 = new Transform3D();

        rotation4.rotZ(angle);


        rotation.mul(rotation, rotation4);

        Transform3D translation = new Transform3D();
        Vector3d vector = new Vector3d(0, 0, -0.2);

        translation.setTranslation(vector);

        rotation.mul(rotation, translation);

        targetTG.setTransform(rotation);

        this.wakeupOn(new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED));
    }


    private double degToRad(double deg) {
        return deg * ((2 * Math.PI) / 360);
    }

}


// end of class SimpleBehavior