package RobotCenter.kinematics;

import javax.media.j3d.Node;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

/**
 * Created by YapYap on 2016-01-26.
 */

public class Point3D {


    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getZ() {
        return z;
    }
    private double x, y, z;
    private double px, py, pz;
    private Point3D previous;
    private static int size;
    private static int sx, sy;
    private static Line2D linia;
    private static Graphics2D g2;
    private static int width;
    private static int height;


    public Point3D(Point3D prv, double x, double y, double z) {

        this.x = x;
        this.y = y;
        this.z = z;
        this.previous = prv;
        reset();
    }


    public static void setLine(Line2D line) {

        Point3D.linia = line;
    }

    public static void setGraphics(Graphics2D graphics) {

        Point3D.g2 = graphics;
    }

    public static void setCentre(int x, int y) {

        sx = x;
        sy = y;
    }

    public static void setSize(int w) {

        size = w;
    }

    public static void setWidth(int w) {

        width = w;
    }

    public static void setHeight(int l) {

        height = l;
    }



    public void reset() {

        this.px = x;
        this.py = y;
        this.pz = z;
    }

    public void RotX(double ang) {
        double angX = degToRad(ang);
        double tempX, tempY, tempZ;
        tempX = px;
        tempY = py * Math.cos(angX) - pz * Math.sin(angX);
        tempZ = pz * Math.cos(angX) + py * Math.sin(angX);
        px = tempX;
        py = tempY;
        pz = tempZ;
    }

    public void Move(double x, double y, double z) {

        this.x = x;
        this.y = y;
        this.z = z;

    }

    public void RotY(double ang) {

        double angY = degToRad(ang);
        double tempX, tempY, tempZ;
        tempX = px * Math.cos(angY) + pz * Math.sin(angY);
        tempY = py;
        tempZ = pz * Math.cos(angY) - px * Math.sin(angY);
        px = tempX;
        py = tempY;
        pz = tempZ;
    }

    public void RotZ(double ang) {

        double angY = degToRad(ang);
        double tempX, tempY, tempZ;
        tempX = px * Math.cos(angY) - py * Math.sin(angY);
        tempY = py * Math.cos(angY) + px * Math.sin(angY);
        tempZ = pz;
        px = tempX;
        py = tempY;
        pz = tempZ;
    }

    public void drawLines2D() {

        if (previous != null) {


            double x1 = (-previous.px * size) / (previous.pz - 600) + sx;
            double y1 = (previous.py * size) / (previous.pz - 600) + sy;
            double x2 = (-this.px * size) / (this.pz - 600) + sx;
            double y2 = (this.py * size) / (this.pz - 600) + sy;
            linia.setLine(x1, y1, x2, y2);
            g2.setColor(Color.blue);
            g2.draw(linia);
            g2.setColor(Color.red);
            g2.fillOval((int) x1 - 5, (int) y1 - 5, 10, 10);
            g2.fillOval((int) x2 - 5, (int) y2 - 5, 10, 10);
        }
    }

    private double degToRad(double deg) {
        return deg * ((2 * Math.PI) / 360);
    }

}
