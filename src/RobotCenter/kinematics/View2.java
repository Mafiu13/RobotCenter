package RobotCenter.kinematics;

/**
 * Created by Jedi on 2016-02-02.
 */

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;

import com.sun.j3d.utils.image.TextureLoader;
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

        BranchGroup Cube = getCube();
        Cube.compile();

        setLayout(new BorderLayout());
        GraphicsConfiguration gc=SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3D = new Canvas3D(gc);
        add("Center", canvas3D);



        BranchGroup Robot= createRobot(fi1,fi2,fi3,fi4,fi5,fi6);
        Robot.compile();
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
        simpleU.getViewingPlatform().setNominalViewingTransform();
        simpleU.addBranchGraph(Robot);
        simpleU.addBranchGraph(Cube);

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
        rotation3.rotZ(degToRad(180));


        Transform3D rotation = new Transform3D();
        rotation.mul(rotation1, rotation2);
        rotation.mul(rotation, rotation3);

        Transform3D translation = new Transform3D();
        Vector3d vector = new Vector3d(0, 0, -0.2);

        translation.setTranslation(vector);

        rotation.mul(rotation, translation);
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


    private BranchGroup getCube() {

        Point3f p000 = new Point3f(-1 / 2f, 0.1f, -1 / 2f);
        Point3f p100 = new Point3f(1 / 2f, 0.1f, -1 / 2f);
        Point3f p010 = new Point3f(-1 / 2f, 1 / 4f, -1 / 2f);
        Point3f p001 = new Point3f(-1 / 2f, 0.1f, 1 / 2f);

        Point3f p110 = new Point3f(1 / 2f, 1 / 4f, -1 / 2f);
        Point3f p011 = new Point3f(-1 / 2f, 1 / 4f, 1 / 2f);
        Point3f p101 = new Point3f(1 / 2f, 0.1f, 1 / 2f);
        Point3f p111 = new Point3f(1 / 2f, 1 / 4f, 1 / 2f);



        QuadArray topSideGeometry = new QuadArray(4, GeometryArray.COORDINATES | GeometryArray.COLOR_3);
        topSideGeometry.setCoordinate(0, p010);
        topSideGeometry.setCoordinate(1, p110);
        topSideGeometry.setCoordinate(2, p111);
        topSideGeometry.setCoordinate(3, p011);
        for (int a = 0; a < 4; a++) {
            topSideGeometry.setColor(a, (new Color3f(0.3f, 0.6f, 0.5f)));

        }

        Appearance topAppearance = new Appearance();
        PolygonAttributes topAttributes = new PolygonAttributes();

        topAttributes.setCullFace(PolygonAttributes.CULL_NONE);
        topAppearance.setPolygonAttributes(topAttributes);

        Material topMaterial = new Material();
        topAppearance.setMaterial(topMaterial);
        ColoringAttributes topColoringAttributes = new ColoringAttributes();

        topColoringAttributes.setShadeModel(ColoringAttributes.SHADE_GOURAUD);
        topAppearance.setColoringAttributes(topColoringAttributes);


        QuadArray frontSideGeometry = new QuadArray(4, GeometryArray.COORDINATES | GeometryArray.COLOR_3);
        frontSideGeometry.setCoordinate(0, p001);
        frontSideGeometry.setCoordinate(1, p101);
        frontSideGeometry.setCoordinate(2, p111);
        frontSideGeometry.setCoordinate(3, p011);
        for (int a = 0; a < 4; a++) {
            frontSideGeometry.setColor(a, (new Color3f(0.1f, 0.5f, 0.5f)));

        }

        Appearance frontAppearance = new Appearance();
        PolygonAttributes frontAtrributes = new PolygonAttributes();

        frontAtrributes.setCullFace(PolygonAttributes.CULL_NONE);
        frontAppearance.setPolygonAttributes(topAttributes);

        Material frontMaterial = new Material();
        frontAppearance.setMaterial(frontMaterial);
        ColoringAttributes frontColoringAttributes = new ColoringAttributes();

        frontColoringAttributes.setShadeModel(ColoringAttributes.SHADE_GOURAUD);
        frontAppearance.setColoringAttributes(frontColoringAttributes);

        QuadArray bottomSideGeometry = new QuadArray(4, GeometryArray.COORDINATES | GeometryArray.COLOR_3);
        bottomSideGeometry.setCoordinate(3, p000);
        bottomSideGeometry.setCoordinate(2, p100);
        bottomSideGeometry.setCoordinate(1, p101);
        bottomSideGeometry.setCoordinate(0, p001);

        Appearance bottomAppearance = new Appearance();
        Material bottomMaterial = new Material();
        bottomAppearance.setMaterial(bottomMaterial);
        // ustawiamy kolor i rodzaj cieniowania, które w tym wypadku ma niewielkie znaczenie, gdyż ciana ma jednolity kolor
        ColoringAttributes bottomColoringAttributes = new ColoringAttributes(new Color3f(Color.GRAY), ColoringAttributes.SHADE_GOURAUD);
        bottomAppearance.setColoringAttributes(bottomColoringAttributes);


        BranchGroup cubeGroup = new BranchGroup();
        Shape3D topSide = new Shape3D(topSideGeometry, topAppearance);
        Shape3D frontSide = new Shape3D(frontSideGeometry, frontAppearance);
        TransformGroup cubeRot = new TransformGroup();
        Transform3D transform = new Transform3D();

        cubeRot.addChild(topSide);
        cubeRot.addChild(frontSide);
        //cubeRot.addChild(backSide);
        //cubeRot.addChild(leftSide);
        //cubeRot.addChild(bottomSide);





        Transform3D rotation1 = new Transform3D();

        cubeRot.setTransform(rotation1);

        Transform3D translation = new Transform3D();
        Vector3d vector = new Vector3d(0, -0.57, 0);

        translation.setTranslation(vector);

        rotation1.mul(rotation1, translation);
        cubeRot.setTransform(rotation1);


        /*

        /*
        // lewa ciana - wiatło wpływa na jej kolor, który domylnie jest biały.
        QuadArray leftSideGeometry = new QuadArray(4, GeometryArray.COORDINATES | GeometryArray.NORMALS);
        leftSideGeometry.setCoordinate(0, p000);
        leftSideGeometry.setCoordinate(1, p010);
        leftSideGeometry.setCoordinate(2, p011);
        leftSideGeometry.setCoordinate(3, p001);
        for (int a = 0; a < 4; a++) {
            leftSideGeometry.setNormal(a, new Vector3f(-1, 0, 0));
        }

        Appearance leftAppearance = new Appearance();
        PolygonAttributes leftAttributes = new PolygonAttributes();
        // polygony widoczne z obu stron - ale owietlane tylko z jednej!
        leftAttributes.setCullFace(PolygonAttributes.CULL_NONE);
        leftAppearance.setPolygonAttributes(leftAttributes);
        Material leftMaterial = new Material();
        leftAppearance.setMaterial(leftMaterial);

        // ciana tylna - pokryta teksturš, na której wyglšd wpływa wiatło
        QuadArray backSideGeometry = new QuadArray(4, GeometryArray.COORDINATES | GeometryArray.TEXTURE_COORDINATE_2 | GeometryArray.NORMALS);

        backSideGeometry.setCoordinate(0, p000);
        backSideGeometry.setCoordinate(1, p010);
        backSideGeometry.setCoordinate(2, p110);
        backSideGeometry.setCoordinate(3, p100);
        // ustawiamy ręcznie współrzędne teksturowania dla każdego z punktów
        backSideGeometry.setTextureCoordinate(0, 0, new TexCoord2f(0, 0));
        backSideGeometry.setTextureCoordinate(0, 1, new TexCoord2f(0, 1));
        backSideGeometry.setTextureCoordinate(0, 2, new TexCoord2f(1, 1));
        backSideGeometry.setTextureCoordinate(0, 3, new TexCoord2f(1, 0));
        // ustawiamy normalne
        for (int a = 0; a < 4; a++) {
            backSideGeometry.setNormal(a, new Vector3f(0, 0, -1));
        }

        Appearance backAppearance = new Appearance();
        Material backMaterial = new Material();
        backAppearance.setMaterial(backMaterial);
        PolygonAttributes backAttributes = new PolygonAttributes();
        //backAttributes.setBackFaceNormalFlip(true);
        backAttributes.setCullFace(PolygonAttributes.CULL_NONE);
        backAppearance.setPolygonAttributes(backAttributes);

        TextureAttributes ta = new TextureAttributes();
        // sposób obliczania koloru renderowanych powierzchni. Dzięki BLEND wiatło będzie miało wpływ na kolor.
        ta.setTextureMode(TextureAttributes.BLEND);
        ta.setPerspectiveCorrectionMode(TextureAttributes.NICEST);
        backAppearance.setTextureAttributes(ta);

        // wczytujemy obrazek tekstury przy pomocy klasy do tego przeznaczonej


        TextureLoader loader = new TextureLoader("tekstura.png", null);
        ImageComponent2D image = loader.getImage();
        // tworzymy teksturę z wczytanym obrazkiem
        Texture2D backTexture = new Texture2D(Texture.BASE_LEVEL, Texture.RGBA, image.getWidth(), image.getHeight());

        // ustawiamy zachowanie tekstury
        backTexture.setMagFilter(Texture.NICEST);
        backTexture.setMinFilter(Texture.NICEST);
        // ustawiamy obrazek w teksturze
        backTexture.setImage(0, image);
        backAppearance.setTexture(backTexture);


        // prawa ciana - pokryta obróconš teksturš, demonstruje możliwoci ręcznego generowania współrzędnych teksturowania
        QuadArray rightSideGeometry = new QuadArray(4, GeometryArray.COORDINATES | GeometryArray.TEXTURE_COORDINATE_2);
        rightSideGeometry.setCoordinate(0, p100);
        rightSideGeometry.setCoordinate(1, p110);
        rightSideGeometry.setCoordinate(2, p111);
        rightSideGeometry.setCoordinate(3, p101);
        // ustawiamy współrzędne teksturowania tak, by tekstura była obrócona
        rightSideGeometry.setTextureCoordinate(0, 0, new TexCoord2f(.5f, 0f));
        rightSideGeometry.setTextureCoordinate(0, 1, new TexCoord2f(1f, 0.5f));
        rightSideGeometry.setTextureCoordinate(0, 2, new TexCoord2f(.5f, 1f));
        rightSideGeometry.setTextureCoordinate(0, 3, new TexCoord2f(.0f, .5f));


        Appearance rightAppearance = new Appearance();
        Material rightMaterial = new Material();
        rightAppearance.setMaterial(rightMaterial);
        PolygonAttributes rightAttributes = new PolygonAttributes();
        rightAttributes.setCullFace(PolygonAttributes.CULL_NONE);
        rightAppearance.setPolygonAttributes(rightAttributes);

        loader = new TextureLoader("tekstura.png", null);
        image = loader.getImage();
        Texture2D rightTexture = new Texture2D(Texture.BASE_LEVEL, Texture.RGBA, image.getWidth(), image.getHeight());
        rightTexture.setMagFilter(Texture.NICEST);
        rightTexture.setMinFilter(Texture.NICEST);
        rightTexture.setImage(0, image);
        rightAppearance.setTexture(rightTexture);

        Shape3D rightSide = new Shape3D(rightSideGeometry, rightAppearance);
        Shape3D backSide = new Shape3D(backSideGeometry, backAppearance);
        Shape3D leftSide = new Shape3D(leftSideGeometry, leftAppearance);
        Shape3D bottomSide = new Shape3D(bottomSideGeometry, bottomAppearance);



        // tworzymy grupę zawierajšcš wszystkie ciany



        MouseRotate wholeSceneMouseRotator = new MouseRotate();
        wholeSceneMouseRotator.setTransformGroup(cubeRot);
        wholeSceneMouseRotator.setSchedulingBounds(new BoundingBox());

        cubeGroup.addChild(wholeSceneMouseRotator);

        */
        cubeGroup.addChild(cubeRot);
        cubeGroup.compile();
        return cubeGroup;
    }


}