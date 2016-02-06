package RobotCenter.kinematics;

/**
 * Created by Jedi on 2016-02-02.
 */

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;

import com.sun.j3d.utils.universe.SimpleUniverse;


import javax.media.j3d.Background;
import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.*;
import javax.media.j3d.LineStripArray;

import java.awt.*;
import java.util.ArrayList;

public class View2 extends JPanel {

    int s = 0, count = 0;

    double x,y,z;
    double x2,y2,z2;
    private ArrayList<Point3D> list3D1;
    private ArrayList<Point3D> list3D2;
    Kinematics3D kinematics3D;


    public View2(double fi1,double fi2,double fi3, double fi4, double fi5, double fi6) {
         kinematics3D=new Kinematics3D();
        list3D1=new ArrayList<Point3D>();
        list3D2=new ArrayList<Point3D>();


        setLayout(new BorderLayout());
        GraphicsConfiguration gc=SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3D = new Canvas3D(gc);
        add("Center", canvas3D);



        BranchGroup Robot= createRobot(fi1,fi2,fi3,fi4,fi5,fi6);
        Robot.compile();
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
        simpleU.getViewingPlatform().setNominalViewingTransform();
        simpleU.addBranchGraph(Robot);

    }





    public BranchGroup createRobot(double fi1,double fi2,double fi3, double fi4, double fi5, double fi6)
    {

        Point3f[] plaPts = new Point3f[9];
        plaPts[0]=new Point3f(0,0,0);
        plaPts[1] = new Point3f((float)(kinematics3D.points2.get(0).getX()/ 1500.0f),(float) (kinematics3D.points2.get(0).getY() / 1500.0f), (float) (kinematics3D.points2.get(0).getZ() / 1500.0f));
        plaPts[2] = new Point3f((float)(kinematics3D.points2.get(1).getX()/ 1500.0f),(float) (kinematics3D.points2.get(1).getY() / 1500.0f), (float) (kinematics3D.points2.get(1).getZ() / 1500.0f));
        plaPts[3] = new Point3f((float)(kinematics3D.points2.get(1).getX()/ 1500.0f),(float) (kinematics3D.points2.get(1).getY() / 1500.0f), (float) (kinematics3D.points2.get(1).getZ() / 1500.0f));
        plaPts[4] = new Point3f((float)(kinematics3D.points2.get(2).getX()/ 1500.0f),(float) (kinematics3D.points2.get(2).getY() / 1500.0f), (float) (kinematics3D.points2.get(2).getZ() / 1500.0f));
        plaPts[5] = new Point3f((float)(kinematics3D.points2.get(0).getX()/ 1500.0f),(float) (kinematics3D.points2.get(0).getY() / 1500.0f), (float) (kinematics3D.points2.get(0).getZ() / 1500.0f));
        plaPts[6] = new Point3f((float)(kinematics3D.points2.get(3).getX()/ 1500.0f),(float) (kinematics3D.points2.get(3).getY() / 1500.0f), (float) (kinematics3D.points2.get(3).getZ() / 1500.0f));
        plaPts[7] = new Point3f((float)(kinematics3D.points2.get(4).getX()/ 1500.0f),(float) (kinematics3D.points2.get(4).getY() / 1500.0f), (float) (kinematics3D.points2.get(4).getZ() / 1500.0f));
        plaPts[8] = new Point3f((float)(kinematics3D.points2.get(5).getX()/ 1500.0f),(float) (kinematics3D.points2.get(5).getY() / 1500.0f), (float) (kinematics3D.points2.get(5).getZ() / 1500.0f));

        PointArray pla = new PointArray(9, GeometryArray.COORDINATES);


        BranchGroup lineGroup = new BranchGroup();// grupa tworzaca jeden obiekt
        Appearance app = new Appearance(); //cechy punktow
        Appearance app2 = new Appearance(); //cechy punktow
        TransformGroup objRot= new TransformGroup();
        objRot.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);


        Draw3D2(pla, plaPts, fi1, fi2, fi3, fi4, fi5, fi6);
        ColoringAttributes ca = new ColoringAttributes(new Color3f(1, 0, 0), ColoringAttributes.NICEST);
        app.setColoringAttributes(ca);

        ColoringAttributes la = new ColoringAttributes(new Color3f(0, 0, 1), ColoringAttributes.NICEST);
        app2.setColoringAttributes(la);
        ColoringAttributes fl = new ColoringAttributes(new Color3f(1, 1, 1), ColoringAttributes.NICEST);

        int vertexCounts[] = {9};
        LineStripArray lines = new LineStripArray(9, GeometryArray.COORDINATES, vertexCounts);
        lines.setCoordinates(0, plaPts);
///

//atributtes of points

        PointAttributes linkOfarm = new PointAttributes();
        linkOfarm.setPointSize(20.0f);
        linkOfarm.setPointAntialiasingEnable(true);

        app.setPointAttributes(linkOfarm);

        LineAttributes lineatr = new LineAttributes();
        lineatr.setLineWidth(20.0f);
        lineatr.setLineAntialiasingEnable(true);
        app2.setLineAttributes(lineatr);


        TransformGroup objRotate = new TransformGroup();   //TransformGroup jest węzłem, który ma przypisaną


        Shape3D plShape = new Shape3D(pla, app);
        objRotate.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);


        objRotate.addChild(new Shape3D(lines, app2));
        objRotate.addChild(plShape);


        Transform3D rotation1 = new Transform3D();
        rotation1.rotX(degToRad(90));

        Transform3D rotation2 = new Transform3D();
        rotation2.rotY(degToRad(180));

        Transform3D rotation3 = new Transform3D();
        rotation3.rotZ(degToRad(0));


        Transform3D rotation = new Transform3D();
        rotation.mul(rotation1, rotation2);
        rotation.mul(rotation, rotation3);

        objRotate.setTransform(rotation);





        MouseRotate wholeSceneMouseRotator = new MouseRotate();
        wholeSceneMouseRotator.setTransformGroup(objRotate);
        wholeSceneMouseRotator.setSchedulingBounds(new BoundingBox());


        lineGroup.addChild(wholeSceneMouseRotator);


        SBehavior myRotationBehavior = new SBehavior(objRotate);
        myRotationBehavior.setSchedulingBounds(new BoundingSphere());
        lineGroup.addChild(myRotationBehavior);

        lineGroup.addChild(objRotate);
        lineGroup.compile();
        return lineGroup;

    }




    public BranchGroup createMainScene()
    {
        BranchGroup lineGroup = new BranchGroup();
        Appearance app = new Appearance();
        TransformGroup floorRot= new TransformGroup();
        floorRot.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        Point3f[] myCoords = {new Point3f(1.0f, 1.0f, -0.01f),
                new Point3f(-1.0f, 1.0f, -0.01f),
                new Point3f(-1.0f, -1.0f, -0.01f),
                new Point3f(1.0f, -1.0f, -0.01f)};

        Vector3f[] myNormals = {
                new Vector3f(0.0f, 10.0f, 10.0f)
        };

        QuadArray myQuads = new QuadArray(
                myCoords.length,
                GeometryArray.COORDINATES |
                        GeometryArray.NORMALS);
        myQuads.setCoordinates(0, myCoords);
        myQuads.setNormals(0, myNormals);
        Shape3D myShape = new Shape3D(myQuads, app);
        floorRot.addChild(myShape);

        Transform3D rotation1 = new Transform3D();
        rotation1.rotX(degToRad(90));
        floorRot.setTransform(rotation1);

        MouseRotate wholeSceneMouseRotator = new MouseRotate();
        wholeSceneMouseRotator.setTransformGroup(floorRot);
        wholeSceneMouseRotator.setSchedulingBounds(new BoundingBox());


        lineGroup.addChild(wholeSceneMouseRotator);
        lineGroup.addChild(floorRot);

        lineGroup.compile();
        return lineGroup;

    }


    public void Draw3D2(PointArray pla ,Point3f[] plaPts,double fi1,double fi2, double fi3, double fi4, double fi5, double fi6 )
    {


        kinematics3D.setAngle2(fi1, fi2, fi3, fi4, fi5, fi6);
        plaPts[0].x=(float) (kinematics3D.points2.get(0).getX()/ 1500.0f);
        plaPts[0].y=(float) (kinematics3D.points2.get(0).getY() / 1500.0f);
        plaPts[0].z=(float) ((kinematics3D.points2.get(0).getZ()-100 )/ 1500.0f);


        plaPts[1].x=(float) (kinematics3D.points2.get(1).getX()/ 1500.0f);
        plaPts[1].y=(float) (kinematics3D.points2.get(1).getY() / 1500.0f);
        plaPts[1].z=(float) (kinematics3D.points2.get(1).getZ() / 1500.0f);

        plaPts[2].x=(float) (kinematics3D.points2.get(2).getX()/ 1500.0f);
        plaPts[2].y=(float) (kinematics3D.points2.get(2).getY() / 1500.0f);
        plaPts[2].z=(float) (kinematics3D.points2.get(2).getZ() / 1500.0f);

        plaPts[3].x=(float) (kinematics3D.points2.get(3).getX()/ 1500.0f);
        plaPts[3].y=(float) (kinematics3D.points2.get(3).getY() / 1500.0f);
        plaPts[3].z=(float) (kinematics3D.points2.get(3).getZ() / 1500.0f);

        plaPts[4].x=(float) (kinematics3D.points2.get(4).getX()/ 1500.0f);
        plaPts[4].y=(float) (kinematics3D.points2.get(4).getY() / 1500.0f);
        plaPts[4].z=(float) (kinematics3D.points2.get(4).getZ() / 1500.0f);

        plaPts[5].x=(float) (kinematics3D.points2.get(5).getX()/ 1500.0f);
        plaPts[5].y=(float) (kinematics3D.points2.get(5).getY() / 1500.0f);
        plaPts[5].z=(float) (kinematics3D.points2.get(5).getZ() / 1500.0f);


        plaPts[6].x=(float) (kinematics3D.points2.get(6).getX()/ 1500.0f);
        plaPts[6].y=(float) (kinematics3D.points2.get(6).getY() / 1500.0f);
        plaPts[6].z=(float) (kinematics3D.points2.get(6).getZ() / 1500.0f);

        plaPts[7].x=(float) (kinematics3D.points2.get(7).getX()/ 1500.0f);
        plaPts[7].y=(float) (kinematics3D.points2.get(7).getY() / 1500.0f);
        plaPts[7].z=(float) (kinematics3D.points2.get(7).getZ() / 1500.0f);

        plaPts[8].x=(float) (kinematics3D.points2.get(8).getX()/ 1500.0f);
        plaPts[8].y=(float) (kinematics3D.points2.get(8).getY() / 1500.0f);
        plaPts[8].z=(float) (kinematics3D.points2.get(8).getZ() / 1500.0f);

        pla.setCoordinates(0, plaPts);

    }
    private double degToRad(double deg) {
        return deg * ((2 * Math.PI) / 360);
    }


}