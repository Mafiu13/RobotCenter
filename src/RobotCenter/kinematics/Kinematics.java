package RobotCenter.kinematics;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by YapYap on 2016-01-26.
 */
public class Kinematics {
    public ArrayList<Point3D> points;
    public ArrayList<Point3D> points2;
    Matrix m1;// wykonywanie oblicznen  na macierzach

    private double fi1;
    private double fi2;
    private double fi3;
    private double fi4;
    private double fi5;
    private double fi6;

    private double fi1_2;
    private double fi2_2;
    private double fi3_2;
    private double fi4_2;
    private double fi5_2;
    private double fi6_2;

    private double[][] table; // pomocnicza zmienna dla liczenia transformacji


    //transformacja macierzy dla pierwszego robota
    private double[][] matrix02;
    private double[][] matrix03;
    private double[][] matrix04;
    private double[][] matrix05;
    private double[][] matrix06;

    //transformacja macierzy dla drugiego robota
    private double[][] matrix02_2;
    private double[][] matrix03_2;
    private double[][] matrix04_2;
    private double[][] matrix05_2;
    private double[][] matrix06_2;

    //obiekty typu macierz transformacji
    Transformation matrix01;
    Transformation matrix12;
    Transformation matrix23;
    Transformation matrix34;
    Transformation matrix45;
    Transformation matrix56;

    Transformation matrix01_2;
    Transformation matrix12_2;
    Transformation matrix23_2;
    Transformation matrix34_2;
    Transformation matrix45_2;
    Transformation matrix56_2;

    //Transformation tablica_x;


    public Kinematics() {
        double x;
        double y;
        double z;


        points = new ArrayList<Point3D>();
        points2 = new ArrayList<Point3D>();

        Point3D previous = null;
        Point3D new_one = null;

        for (int i = 0; i < 6; i++) {

            if (i == 0) {
                previous = new_one;
                new_one = new Point3D(previous, 0, 0, 0);
                points.add(new_one);
            } else {
                previous = new_one;
                new_one = new Point3D(previous, 0, 0, 0);
                points.add(new_one);
            }
        }


        for (int i = 0; i < 6; i++) {

            if (i == 0) {
                previous = new_one;
                new_one = new Point3D(previous, 0, 0, 0);
                points2.add(new_one);
            } else {
                previous = new_one;
                new_one = new Point3D(previous, 0, 0, 0);
                points2.add(new_one);
            }
        }

        m1 = new Matrix(table);
        matrix02 = new double[3][3];
        matrix03 = new double[3][3];
        matrix04 = new double[3][3];
        matrix05 = new double[3][3];
        matrix06 = new double[3][3];

        matrix02_2 = new double[3][3];
        matrix03_2 = new double[3][3];
        matrix04_2 = new double[3][3];
        matrix05_2 = new double[3][3];
        matrix06_2 = new double[3][3];

        //tablice tworzenie- tabele wypełniane parametrami DH

        matrix01 = new Transformation(0, -90, 75, 0);
        matrix12 = new Transformation(-90, 0, 300, 0);
        matrix23 = new Transformation(0, -90, 75, 0);
        matrix34 = new Transformation(0, 90, 0, 320);
        matrix45 = new Transformation(0, -90, 0, 0);
        matrix56 = new Transformation(0, 0, 0, 80);

        matrix01_2 = new Transformation(180, 90, 150, 0);
        matrix12_2 = new Transformation(90, 180, 600, 140);
        matrix23_2 = new Transformation(0, -90, 200, 140);
        matrix34_2 = new Transformation(0, -90, 0, 640);
        matrix45_2 = new Transformation(0, 90, 0, 0);
        matrix56_2 = new Transformation(0, 0, 0, 100);


    }

    public void setAngle1(double f1, double f2_t, double f3_t, double f4_t, double f5, double f6) {


        //korekcja

        double f2=f2_t-90;
        double f3=-f3_t;
        double f4=-(f4_t-180);

        matrix01.countMatrix(degToRad(f1));
        matrix12.countMatrix(degToRad(f2));
        matrix23.countMatrix(degToRad(f3));
        matrix34.countMatrix(degToRad(f4));
        matrix45.countMatrix(degToRad(f5));
        matrix56.countMatrix(degToRad(f6));




        matrix02 = m1.multiplyTables(matrix01.table, matrix12.table);
        matrix03 = m1.multiplyTables(matrix02, matrix23.table);
        matrix04 = m1.multiplyTables(matrix03, matrix34.table);
        matrix05 = m1.multiplyTables(matrix04, matrix45.table);
        matrix06 = m1.multiplyTables(matrix05, matrix56.table);


        points.get(0).Move(matrix01.table[0][3], matrix01.table[1][3], matrix01.table[2][3]);
        points.get(1).Move(matrix02[0][3], matrix02[1][3], matrix02[2][3]);
        points.get(2).Move(matrix03[0][3], matrix03[1][3], matrix03[2][3]);
        points.get(3).Move(matrix04[0][3], matrix04[1][3], matrix04[2][3]);
        points.get(4).Move(matrix05[0][3], matrix05[1][3], matrix05[2][3]);
        points.get(5).Move(matrix06[0][3], matrix06[1][3], matrix06[2][3]);


        for (int i = 0; i < points.size(); i++) {
            points.get(i).drawLines2D();
        }
        for (int i = 0; i < points.size(); i++) {

            points.get(i).reset();
            points.get(i).RotZ(90);
            points.get(i).RotX(-90);
            points.get(i).RotY(270);

        }
    }

    public void setAngle2(double f1_t, double f2_t, double f3_t, double f4_t, double f5_t, double f6) {

        double f1=-f1_t;
        double f2=-(f2_t-90);
        double f3=-f3_t;
        double f4=-(f4_t-180);
        double f5=-(f5_t);


        matrix01_2.countMatrix(degToRad(f1));
        matrix12_2.countMatrix(degToRad(f2));
        matrix23_2.countMatrix(degToRad(f3));
        matrix34_2.countMatrix(degToRad(f4));
        matrix45_2.countMatrix(degToRad(f5));
        matrix56_2.countMatrix(degToRad(f6));


        matrix02_2 = m1.multiplyTables(matrix01_2.table, matrix12_2.table);
        matrix03_2 = m1.multiplyTables(matrix02_2, matrix23_2.table);
        matrix04_2 = m1.multiplyTables(matrix03_2, matrix34_2.table);
        matrix05_2 = m1.multiplyTables(matrix04_2, matrix45_2.table);
        matrix06_2 = m1.multiplyTables(matrix05_2, matrix56_2.table);


        points2.get(0).Move(matrix01_2.table[0][3], matrix01_2.table[1][3], matrix01_2.table[2][3]);
        points2.get(1).Move(matrix02_2[0][3], matrix02_2[1][3], matrix02_2[2][3]);
        points2.get(2).Move(matrix03_2[0][3], matrix03_2[1][3], matrix03_2[2][3]);
        points2.get(3).Move(matrix04_2[0][3], matrix04_2[1][3], matrix04_2[2][3]);
        points2.get(4).Move(matrix05_2[0][3], matrix05_2[1][3], matrix05_2[2][3]);
        points2.get(5).Move(matrix06_2[0][3], matrix06_2[1][3], matrix06_2[2][3]);




        for (int i = 0; i < points2.size(); i++) {
            points2.get(i).drawLines2D();

        }

        for (int i = 0; i < points2.size(); i++) {

            points2.get(i).reset();
            points2.get(i).RotZ(180);
            points2.get(i).RotX(-90);
            points2.get(i).RotY(180);
        }


    }



    private double degToRad(double deg) {
        return deg * ((2 * Math.PI) / 360);
    }
}