package RobotCenter.kinematics;

/**
 * Created by YapYap on 2016-01-26.
 */
public class Transformation {
    public double[][] table;
    double fi;
    double ali;
    double ai;
    double di;

    public Transformation(double fi_x, double ali_x, double ai, double di) {
        this.fi = degToRad(fi_x);
        this.ali = degToRad(ali_x);
        this.di = di;
        this.ai = ai;


        table = new double[4][4];
        countMatrix(fi);

    }

    private double degToRad(double deg) {
        return deg * ((2 * Math.PI) / 360);
    }


    public void countMatrix(double fi) {
        table[0][0] = Math.cos(fi);
        table[0][1] = -Math.cos(ali) * Math.sin(fi);
        table[0][2] = Math.sin(ali) * Math.sin(fi);
        table[0][3] = Math.cos(fi) * ai;
        table[1][0] = Math.sin(fi);
        table[1][1] = Math.cos(ali) * Math.cos(fi);
        table[1][2] = -Math.cos(fi) * Math.sin(ali);

        table[1][3] = Math.sin(fi) * ai;
        table[2][0] = 0;
        table[2][1] = Math.sin(ali);
        table[2][2] = Math.cos(ali);
        table[2][3] = di;
        table[3][0] = 0;
        table[3][1] = 0;
        table[3][2] = 0;
        table[3][3] = 1;
    }


    public void setFi(double fi)
    {
        this.fi=fi;
    }
}