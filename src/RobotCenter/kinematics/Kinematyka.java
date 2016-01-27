package RobotCenter.kinematics;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 * Created by YapYap on 2016-01-26.
 */
public class Kinematyka {
    public ArrayList<Punkt3D> punkty;
    public ArrayList<Punkt3D> punkty2;

    Matrix m1;// wykonywanie oblicznen  na macierzach

    private double fi1;
    double fi2;
    double fi3;
    double fi4;
    double fi5;
    double fi6;

    double fi1_2;
    double fi2_2;
    double fi3_2;
    double fi4_2;
    double fi5_2;
    double fi6_2;

    private double[][] tablica;// skasowac
    //private double[][] tablica2; //skasowac

    //transformacja macierzy dla pierwszego robota
    private double[][] macierz02;
    private double[][] macierz03;
    private double[][] macierz04;
    private double[][] macierz05;
    private double[][] macierz06;

    //transformacja macierzy dla drugiego robota
    private double[][] macierz02_2;
    private double[][] macierz03_2;
    private double[][] macierz04_2;
    private double[][] macierz05_2;
    private double[][] macierz06_2;

    //obiekty typu macierz transformacji
    Transformation macierz01;
    Transformation macierz12;
    Transformation macierz23;
    Transformation macierz34;
    Transformation macierz45;
    Transformation macierz56;

    Transformation macierz01_2;
    Transformation macierz12_2;
    Transformation macierz23_2;
    Transformation macierz34_2;
    Transformation macierz45_2;
    Transformation macierz56_2;

    //Transformation tablica_x;


    public Kinematyka() {
        double x;
        double y;
        double z;


        punkty = new ArrayList<Punkt3D>();
        punkty2 = new ArrayList<Punkt3D>();

        Punkt3D poprzedni = null;
        Punkt3D nowy = null;

        for (int i = 0; i < 6; i++) {

            if (i == 0) {
                poprzedni = nowy;
                nowy = new Punkt3D(poprzedni, 0, 0, 0);
                punkty.add(nowy);
            } else {
                poprzedni = nowy;
                nowy = new Punkt3D(poprzedni, 0, 0, 0);
                punkty.add(nowy);
            }
        }


        for (int i = 0; i < 6; i++) {

            if (i == 0) {
                poprzedni = nowy;
                nowy = new Punkt3D(poprzedni, 0, 0, 0);
                punkty2.add(nowy);
            } else {
                poprzedni = nowy;
                nowy = new Punkt3D(poprzedni, 0, 0, 0);
                punkty2.add(nowy);
            }
        }

        m1 = new Matrix(tablica);
        macierz02 = new double[3][3];
        macierz03 = new double[3][3];
        macierz04 = new double[3][3];
        macierz05 = new double[3][3];
        macierz06 = new double[3][3];

        macierz02_2 = new double[3][3];
        macierz03_2 = new double[3][3];
        macierz04_2 = new double[3][3];
        macierz05_2 = new double[3][3];
        macierz06_2 = new double[3][3];

        //tablice tworzenie- tabele wypełniane parametrami DH

        macierz01 = new Transformation(0, -90, 75, 0);
        macierz12 = new Transformation(-90, 0, 300, 0);
        macierz23 = new Transformation(0, -90, 75, 0);
        macierz34 = new Transformation(0, 90, 0, 320);
        macierz45 = new Transformation(0, -90, 0, 0);
        macierz56 = new Transformation(0, 0, 0, 80);

        macierz01_2 = new Transformation(180, 90, 150, 0);
        macierz12_2 = new Transformation(90, 180, 600, 140);
        macierz23_2 = new Transformation(0, -90, 200, 140);
        macierz34_2 = new Transformation(0, -90, 0, 640);
        macierz45_2 = new Transformation(0, 90, 0, 0);
        macierz56_2 = new Transformation(0, 0, 0, 100);


    }

    public void setAngle1(double f1, double f2, double f3, double f4, double f5, double f6) {
        macierz01.fi = f1;
        macierz12.fi = f2;
        macierz23.fi = f3;
        macierz34.fi = f4;
        macierz45.fi = f5;
        macierz56.fi = f6;


        macierz02 = m1.wymnozTablice(macierz01.table, macierz12.table);
        macierz03 = m1.wymnozTablice(macierz02, macierz23.table);
        macierz04 = m1.wymnozTablice(macierz03, macierz34.table);
        macierz05 = m1.wymnozTablice(macierz04, macierz45.table);
        macierz06 = m1.wymnozTablice(macierz05, macierz56.table);


        punkty.get(0).Przesun(macierz01.table[0][3], macierz01.table[1][3], macierz01.table[2][3]);
        punkty.get(1).Przesun(macierz02[0][3], macierz02[1][3], macierz02[2][3]);
        punkty.get(2).Przesun(macierz03[0][3], macierz03[1][3], macierz03[2][3]);
        punkty.get(3).Przesun(macierz04[0][3], macierz04[1][3], macierz04[2][3]);
        punkty.get(4).Przesun(macierz05[0][3], macierz05[1][3], macierz05[2][3]);
        punkty.get(5).Przesun(macierz06[0][3], macierz06[1][3], macierz06[2][3]);


        for (int i = 0; i < punkty.size(); i++) {
            punkty.get(i).rysujLinie2D();
        }
        for (int i = 0; i < punkty.size(); i++) {

            punkty.get(i).zeruj();
            punkty.get(i).ObrotZ(90);
            punkty.get(i).ObrotX(-90);
            punkty.get(i).ObrotY(270);

        }
    }

    public void setAngle2(double f1, double f2, double f3, double f4, double f5, double f6) {



        macierz01_2.fi = f1;
        macierz12_2.fi = f2;
        macierz23_2.fi = f3;
        macierz34_2.fi = f4;
        macierz45_2.fi = f5;
        macierz56_2.fi = f6;


        macierz02_2 = m1.wymnozTablice(macierz01_2.table, macierz12_2.table);
        macierz03_2 = m1.wymnozTablice(macierz02_2, macierz23_2.table);
        macierz04_2 = m1.wymnozTablice(macierz03_2, macierz34_2.table);
        macierz05_2 = m1.wymnozTablice(macierz04_2, macierz45_2.table);
        macierz06_2 = m1.wymnozTablice(macierz05_2, macierz56_2.table);


        punkty2.get(0).Przesun(macierz01_2.table[0][3], macierz01_2.table[1][3], macierz01_2.table[2][3]);
        punkty2.get(1).Przesun(macierz02_2[0][3], macierz02_2[1][3], macierz02_2[2][3]);
        punkty2.get(2).Przesun(macierz03_2[0][3], macierz03_2[1][3], macierz03_2[2][3]);
        punkty2.get(3).Przesun(macierz04_2[0][3], macierz04_2[1][3], macierz04_2[2][3]);
        punkty2.get(4).Przesun(macierz05_2[0][3], macierz05_2[1][3], macierz05_2[2][3]);
        punkty2.get(5).Przesun(macierz06_2[0][3], macierz06_2[1][3], macierz06_2[2][3]);


        for (int i = 0; i < punkty2.size(); i++) {
            punkty2.get(i).rysujLinie2D();

        }

        for (int i = 0; i < punkty2.size(); i++) {

            punkty2.get(i).zeruj();
            punkty2.get(i).ObrotZ(180);
            punkty2.get(i).ObrotX(-90);
            punkty2.get(i).ObrotY(180);
        }


    }
}
