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

public class View extends JPanel {

    int s = 0, count = 0;

    double x, y, z;
    double x2, y2, z2;
    private ArrayList<Point3D> list3D1;
    private ArrayList<Point3D> list3D2;
    Kinematics3D kinematics3D;


    public View() {
        kinematics3D=new Kinematics3D();
        list3D1 = new ArrayList<Point3D>();
        list3D2 = new ArrayList<Point3D>();
        setLayout(new BorderLayout());
        GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3D = new Canvas3D(gc);
        add("Center", canvas3D);



        BranchGroup Robot= createRobot(0,-90,0,0,90,0);
        Robot.compile();
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
        simpleU.getViewingPlatform().setNominalViewingTransform();
        simpleU.addBranchGraph(Robot);

    }

    public BranchGroup createRobot(double fi1,double fi2,double fi3, double fi4, double fi5, double fi6)
    {
        BranchGroup lineGroup = new BranchGroup();
        Appearance app = new Appearance();
        Appearance app2 = new Appearance();
        TransformGroup objRot= new TransformGroup();
        objRot.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);



        Point3f[] plaPts = new Point3f[7];


        plaPts[0] = new Point3f(0, 0, 0);
        plaPts[1] = new Point3f((float) (kinematics3D.points.get(0).getX() / 1500.0f), (float) (kinematics3D.points.get(0).getY() / 1500.0f), (float) (kinematics3D.points.get(0).getZ() / 1500.0f));
        plaPts[2] = new Point3f((float) (kinematics3D.points.get(1).getX() / 1500.0f), (float) (kinematics3D.points.get(1).getY() / 1500.0f), (float) (kinematics3D.points.get(1).getZ() / 1500.0f));
        plaPts[3] = new Point3f((float) (kinematics3D.points.get(2).getX() / 1500.0f), (float) (kinematics3D.points.get(2).getY() / 1500.0f), (float) (kinematics3D.points.get(2).getZ() / 1500.0f));
        plaPts[4] = new Point3f((float) (kinematics3D.points.get(3).getX() / 1500.0f), (float) (kinematics3D.points.get(3).getY() / 1500.0f), (float) (kinematics3D.points.get(3).getZ() / 1500.0f));
        plaPts[5] = new Point3f((float) (kinematics3D.points.get(4).getX() / 1500.0f), (float) (kinematics3D.points.get(4).getY() / 1500.0f), (float) (kinematics3D.points.get(4).getZ() / 1500.0f));
        plaPts[6] = new Point3f((float) (kinematics3D.points.get(5).getX() / 1500.0f), (float) (kinematics3D.points.get(5).getY() / 1500.0f), (float) (kinematics3D.points.get(5).getZ() / 1500.0f));

        PointArray pla = new PointArray(7, GeometryArray.COORDINATES);

        Draw3D(pla, plaPts, fi1,fi2,fi3,fi4,fi5,fi6);

        ColoringAttributes ca = new ColoringAttributes(new Color3f(1, 0, 0), ColoringAttributes.NICEST);
        app.setColoringAttributes(ca);

        ColoringAttributes la = new ColoringAttributes(new Color3f(0, 0, 1), ColoringAttributes.NICEST);
        app2.setColoringAttributes(la);
        ColoringAttributes fl = new ColoringAttributes(new Color3f(1, 1, 1), ColoringAttributes.NICEST);/////////////////

        int vertexCounts[] = {7};
        LineStripArray lines = new LineStripArray(7, GeometryArray.COORDINATES, vertexCounts);
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


        TransformGroup objRotate = new TransformGroup();


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


    private void Draw3D(PointArray pla, Point3f[] plaPts, double fi1, double fi2, double fi3, double fi4, double fi5, double fi6) {


        kinematics3D.setAngle1(fi1, fi2, fi3, fi4, fi5, fi6);
        plaPts[0].x = (float) (kinematics3D.points.get(0).getX() / 1000.0f);
        plaPts[0].y = (float) (kinematics3D.points.get(0).getY() / 1000.0f);
        plaPts[0].z = (float) ((kinematics3D.points.get(0).getZ() - 100) / 1000.0f);


        plaPts[1].x = (float) (kinematics3D.points.get(1).getX() / 1000.0f);
        plaPts[1].y = (float) (kinematics3D.points.get(1).getY() / 1000.0f);
        plaPts[1].z = (float) (kinematics3D.points.get(1).getZ() / 1000.0f);

        plaPts[2].x = (float) (kinematics3D.points.get(2).getX() / 1000.0f);
        plaPts[2].y = (float) (kinematics3D.points.get(2).getY() / 1000.0f);
        plaPts[2].z = (float) (kinematics3D.points.get(2).getZ() / 1000.0f);

        plaPts[3].x = (float) (kinematics3D.points.get(3).getX() / 1000.0f);
        plaPts[3].y = (float) (kinematics3D.points.get(3).getY() / 1000.0f);
        plaPts[3].z = (float) (kinematics3D.points.get(3).getZ() / 1000.0f);


        plaPts[4].x = (float) (kinematics3D.points.get(4).getX() / 1000.0f);
        plaPts[4].y = (float) (kinematics3D.points.get(4).getY() / 1000.0f);
        plaPts[4].z = (float) (kinematics3D.points.get(4).getZ() / 1000.0f);

        plaPts[5].x = (float) (kinematics3D.points.get(5).getX() / 1000.0f);
        plaPts[5].y = (float) (kinematics3D.points.get(5).getY() / 1000.0f);
        plaPts[5].z = (float) (kinematics3D.points.get(5).getZ() / 1000.0f);

        plaPts[6].x = (float) (kinematics3D.points.get(6).getX() / 1000.0f);
        plaPts[6].y = (float) (kinematics3D.points.get(6).getY() / 1000.0f);
        plaPts[6].z = (float) (kinematics3D.points.get(6).getZ() / 1000.0f);

        pla.setCoordinates(0, plaPts);

    }

    private double degToRad(double deg) {
        return deg * ((2 * Math.PI) / 360);
    }



    public BranchGroup createSceneGraph() {

        BranchGroup lineGroup = new BranchGroup();// grupa tworzaca jeden obiekt
        Appearance app = new Appearance(); //cechy punktow
        Appearance app2 = new Appearance(); // cechy lacza
        Appearance appFloor = new Appearance(); //cechy podloi ///////////////////////////////////flooor

        // tworzenie tła

        Background backg = new Background(0f, 0f, 0f);
        //  backg.setGeometry(Background);
        backg.setApplicationBounds(new BoundingSphere(new Point3d(), 100.0));
        lineGroup.addChild(backg);

        Point3f[] plaPts = new Point3f[7];

// tworzenie punktow waraz z polozeniem poczatkkowym

        plaPts[0] = new Point3f(0, 0, 0);
        //    plaPts[0] = new Point3f((float)(kinematics3D.points.get(0).getX()/ 1500.0f),(float) (kinematics3D.points.get(0).getY() / 1500.0f), (float) (kinematics3D.points.get(0).getZ() / 1500.0f));
        plaPts[1] = new Point3f((float) (kinematics3D.points.get(0).getX() / 1500.0f), (float) (kinematics3D.points.get(0).getY() / 1500.0f), (float) (kinematics3D.points.get(0).getZ() / 1500.0f));
        plaPts[2] = new Point3f((float) (kinematics3D.points.get(1).getX() / 1500.0f), (float) (kinematics3D.points.get(1).getY() / 1500.0f), (float) (kinematics3D.points.get(1).getZ() / 1500.0f));
        plaPts[3] = new Point3f((float) (kinematics3D.points.get(2).getX() / 1500.0f), (float) (kinematics3D.points.get(2).getY() / 1500.0f), (float) (kinematics3D.points.get(2).getZ() / 1500.0f));
        plaPts[4] = new Point3f((float) (kinematics3D.points.get(3).getX() / 1500.0f), (float) (kinematics3D.points.get(3).getY() / 1500.0f), (float) (kinematics3D.points.get(3).getZ() / 1500.0f));
        plaPts[5] = new Point3f((float) (kinematics3D.points.get(4).getX() / 1500.0f), (float) (kinematics3D.points.get(4).getY() / 1500.0f), (float) (kinematics3D.points.get(4).getZ() / 1500.0f));
        plaPts[6] = new Point3f((float) (kinematics3D.points.get(5).getX() / 1500.0f), (float) (kinematics3D.points.get(5).getY() / 1500.0f), (float) (kinematics3D.points.get(5).getZ() / 1500.0f));
        //plaPts[3] = new Point3f((float)(kinematics3D.points.get(3).getX()/ 10.0f),(float) (kinematics3D.points.get(3).getY() / 10.0f), (float) (kinematics3D.points.get(3).getZ() / 10.0f));

// tworzenie listy punktow
        PointArray pla = new PointArray(7, GeometryArray.COORDINATES);


//ustawianaie w dowolnym polozeniu (bedze wykorzysane przy zaczystywaniu danych)
        Draw3D(pla, plaPts, 90, -90, 0, 0, 40, 40);
//atrybuty koloru dla punktow
        ColoringAttributes ca = new ColoringAttributes(new Color3f(1, 0, 0), ColoringAttributes.NICEST);
        app.setColoringAttributes(ca);
///atry kol dla lini
        ColoringAttributes la = new ColoringAttributes(new Color3f(0, 0, 1), ColoringAttributes.NICEST);
        app2.setColoringAttributes(la);
        ColoringAttributes fl = new ColoringAttributes(new Color3f(1, 1, 1), ColoringAttributes.NICEST);/////////////////
        appFloor.setColoringAttributes(fl);                                         //////////////////////////floor
        //tworzenie linii
        int vertexCounts[] = {7};
        LineStripArray lines = new LineStripArray(7, GeometryArray.COORDINATES, vertexCounts);
        lines.setCoordinates(0, plaPts);
///


//atributtes of points

        PointAttributes linkOfarm = new PointAttributes();
        linkOfarm.setPointSize(20.0f);
        linkOfarm.setPointAntialiasingEnable(true);

        app.setPointAttributes(linkOfarm);
//atrybuty linii
        LineAttributes lineatr = new LineAttributes();
        lineatr.setLineWidth(20.0f);
        lineatr.setLineAntialiasingEnable(true);
        app2.setLineAttributes(lineatr);

        Material mat = new Material();
        mat.setEmissiveColor(new Color3f(0.1f, 0.5f, 0.5f));
        mat.setAmbientColor(new Color3f(0.1f, 0.1f, 0.1f));
        mat.setDiffuseColor(new Color3f(0.2f, 0.3f, 0.4f));
        mat.setSpecularColor(new Color3f(0.6f, 0.3f, 0.2f));
        mat.setLightingEnable(true);
        appFloor.setMaterial(mat);
        //  app2.setMaterial(mat);
        RenderingAttributes ra = new RenderingAttributes();
        ra.setIgnoreVertexColors(true);
//not used
/*
        Material mat=new Material();
        mat.setEmissiveColor(new Color3f(0.5f,0.5f,0.5f));
        mat.setAmbientColor(new Color3f(0.1f,0.1f,0.1f));
        mat.setDiffuseColor(new Color3f(0.2f,0.3f,0.4f));
        mat.setSpecularColor(new Color3f(0.6f,0.3f,0.2f));
        mat.setLightingEnable(true);
        app.setMaterial(mat);
        app2.setMaterial(mat);
        RenderingAttributes ra=new RenderingAttributes();
        ra.setIgnoreVertexColors(true);
*/
        //    ca.setShadeModel(ColoringAttributes.SHADE_GOURAUD);
        //  ca.setColor(new Color3f(0.2f,0.5f,0.9f));


        // app.setRenderingAttributes(ra);


        //wazne

        //line
        TransformGroup objRotate = new TransformGroup();   //TransformGroup jest węzłem, który ma przypisaną
        TransformGroup floorRot= new TransformGroup();  //podloga
        //transformację. Oznacza to, że wszyscy
        //  potomkowie tego węzła mogą zostać przesunięci
        //  i obróceni w dowolny (ale wszyscy w taki
        //  sam) sposób.


/////////////////////////////////////tworzenie podlogi
        Point3f[] myCoords = {new Point3f(1.0f, 1.0f, -0.01f),
                new Point3f(-1.0f, 1.0f, -0.01f),
                new Point3f(-1.0f, -1.0f, -0.01f),
                new Point3f(1.0f, -1.0f, -0.01f)};

        Vector3f[] myNormals = {
                new Vector3f(0.0f, 10.0f, 10.0f),
                //     new Vector3f(  0.0f, 0.0f, -1.0f ),
                // new Vector3f(  0.0f, 1.0f, 0.0f ),
                //       new Vector3f(  0.0f, 1.0f, 0.0f )
        };


        QuadArray myQuads = new QuadArray(
                myCoords.length,
                GeometryArray.COORDINATES |
                        GeometryArray.NORMALS);
        myQuads.setCoordinates(0, myCoords);
        myQuads.setNormals(0, myNormals);


        Shape3D myShape = new Shape3D(myQuads, appFloor);


/////////////////////////////////////////////////////////////////////////
 /*       QuadArray plane = new QuadArray(4, GeometryArray.COORDINATES
                | GeometryArray.NORMALS);

        Point3f A= new Point3f();
        A=plaPts[0];

        Point3f B= new Point3f();
        B=plaPts[0];

        Point3f C= new Point3f();
        C=plaPts[0];


        Point3f D= new Point3f();
        D=plaPts[0];

        plane.setCoordinate(0,A);
        plane.setCoordinate(1,B);
        plane.setCoordinate(2,C);
        plane.setCoordinate(3,D);

        Vector3f a = new Vector3f(A.x, A.y - B.y, A.z - B.z);
        Vector3f b = new Vector3f(C.x - B.x, C.y - B.y, C.z - B.z);
        Vector3f n = new Vector3f();
        n.cross(b, a);

        n.normalize();

        plane.setNormal(0, n);
        plane.setNormal(1, n);
        plane.setNormal(2, n);
        plane.setNormal(3, n);

*/
        /////////////////////////////////////////////////////////////


//points
        Shape3D plShape = new Shape3D(pla, app);
        objRotate.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        //dodaje linie do jednego elem
        objRotate.addChild(new Shape3D(lines, app2));
        //dodaje punkty do jednego elem
        objRotate.addChild(plShape);
        floorRot.addChild(myShape);
        // objRotate.addChild(myShape); /////////////////////////////////////////////flooor


        //obracam element objRotate
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
        floorRot.setTransform(rotation1);
        //  rotation.rotZ(degToRad(0));
        //   objRotate.setTransform(rotation);
        //  rotation.rotX(degToRad(90));
        // objRotate.setTransform(rotation);


/// tworze oswietlenie


// tworzymy światło - kierunkowe, świecące od góry
        DirectionalLight sunlikeLight = new DirectionalLight(new Color3f(Color.RED), new Vector3f(0, -1, 0));
        sunlikeLight.setInfluencingBounds(new BoundingSphere());


        MouseRotate wholeSceneMouseRotator = new MouseRotate();
        wholeSceneMouseRotator.setTransformGroup(objRotate);
//        wholeSceneMouseRotator.setTransformGroup(floorRot);
        wholeSceneMouseRotator.setSchedulingBounds(new BoundingBox());


        lineGroup.addChild(wholeSceneMouseRotator);


        //zachowanie na naciesniecie przycisku- zmienic na wszystkie
        SBehavior myRotationBehavior = new SBehavior(objRotate);
        myRotationBehavior.setSchedulingBounds(new BoundingSphere());
        lineGroup.addChild(myRotationBehavior);


// dodaje element rotate fo jefnego brancha czyli obiektu
        lineGroup.addChild(objRotate);
        lineGroup.addChild(floorRot);
        lineGroup.compile();

        return lineGroup;

    }
}