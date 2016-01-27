package RobotCenter.kinematics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

/**
 * Created by YapYap on 2016-01-26.
 */

public class Punkt3D {

    public static void setLinia(Line2D linia) {

        Punkt3D.linia = linia;
    }

    public static void setGrafika(Graphics2D grafika) {

        Punkt3D.g2 = grafika;
    }

    public static void setSrodek(int x, int y) {

        sx = x;
        sy = y;
    }

    public static void setWielkosc(int w) {

        wielkosc = w;
    }

    public Punkt3D(Punkt3D pop, double x, double y, double z) {

        this.x = x;
        this.y = y;
        this.z = z;
        this.poprzedni = pop;
        zeruj();
    }

    public void zeruj() {

        this.px = x;
        this.py = y;
        this.pz = z;
    }

    public void ObrotX(double kat) {
        double katX = degToRad(kat);
        double pomX, pomY, pomZ;
        pomX = px;
        pomY = py * Math.cos(katX) - pz * Math.sin(katX);
        pomZ = pz * Math.cos(katX) + py * Math.sin(katX);
        px = pomX;
        py = pomY;
        pz = pomZ;
    }

    public void Przesun(double x, double y, double z) {

        this.x = x;
        this.y = y;

        this.z = z;

    }

    public void ObrotY(double kat) {

        double katY = degToRad(kat);
        double pomX, pomY, pomZ;
        pomX = px * Math.cos(katY) + pz * Math.sin(katY);
        pomY = py;
        pomZ = pz * Math.cos(katY) - px * Math.sin(katY);
        px = pomX;
        py = pomY;
        pz = pomZ;
    }

    public void ObrotZ(double kat) {

        double katZ = degToRad(kat);
        double pomX, pomY, pomZ;
        pomX = px * Math.cos(katZ) - py * Math.sin(katZ);
        pomY = py * Math.cos(katZ) + px * Math.sin(katZ);
        pomZ = pz;
        px = pomX;
        py = pomY;
        pz = pomZ;
    }

    public void rysujLinie2D() {

        if (poprzedni != null) {

            double x1 = (-poprzedni.px * wielkosc) / (poprzedni.pz - 600) + sx;
            double y1 = (poprzedni.py * wielkosc) / (poprzedni.pz - 600) + sy;
            double x2 = (-this.px * wielkosc) / (this.pz - 600) + sx;
            double y2 = (this.py * wielkosc) / (this.pz - 600) + sy;
            linia.setLine(x1, y1, x2, y2);
            g2.setColor(Color.blue);
            g2.draw(linia);
            g2.setColor(Color.red);
            g2.fillOval((int) x1 - 5, (int) y1 - 5, 10, 10);
            g2.fillOval((int) x2 - 5, (int) y2 - 5, 10, 10);

            g2.drawRect(100,100,100,100);

        }
    }

    private double degToRad(double deg) {
        return deg * ((2 * Math.PI) / 360);
    }

    private double x, y, z;
    private double px, py, pz;
    private Punkt3D poprzedni;
    private static int wielkosc;
    private static int sx, sy;
    private static Line2D linia;
    private static Graphics2D g2;
}
