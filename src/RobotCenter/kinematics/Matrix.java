package RobotCenter.kinematics;

/**
 * Created by YapYap on 2016-01-26.
 */
public class Matrix {

    private double[][] tablica;

    Matrix(double[][] m1) {
        this.tablica = m1;
    }


    public double[][] getTablice() {
        return tablica;
    }

    public void pomnoz(Matrix macierz) {
        double[][] macierzMnozona = macierz.getTablice();
        double[][] macierzPomnozona = new double[this.tablica.length][macierzMnozona[0].length];
        macierzPomnozona = this.wymnozTablice(this.tablica, macierzMnozona);
        this.tablica = macierzPomnozona;
    }

    public double[][] wymnozTablice(double[][] tab1, double[][] tab2) {
        double[][] macierzPomnozona = new double[tab1.length][tab2[0].length];
        if (tab1[0].length == tab2.length) {
            for (int i = 0; i < tab1.length; i++) {//ilosc wierszy tab1
                for (int j = 0; j < tab2[0].length; j++) { //ilosc kolumn tab2
                    double temp = 0;
                    for (int w = 0; w < tab2.length; w++) { //ilosc wierszy tab2
                        temp += tab1[i][w] * tab2[w][j];
                    }
                    macierzPomnozona[i][j] = temp;
                }
            }
        } else {
            throw new RuntimeException("Podane tablice mają niewłasciwe wymiary");
        }
        return macierzPomnozona;
    }
}
