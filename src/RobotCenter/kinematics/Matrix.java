package RobotCenter.kinematics;

/**
 * Created by YapYap on 2016-01-26.
 */
public class Matrix {

    private double[][] table;

    Matrix(double[][] m1) {
        this.table = m1;
    }

    public double[][] getTable() {
        return table;
    }

    public void multiply(Matrix matrix) {
        double[][] multiplyMatrix = matrix.getTable();
        double[][] multipliedMatrix = new double[this.table.length][multiplyMatrix[0].length];
        multipliedMatrix = this.multiplyTables(this.table, multiplyMatrix);
        this.table = multipliedMatrix;
    }

    public double[][] multiplyTables(double[][] tab1, double[][] tab2) {
        double[][] multipliedMatrix = new double[tab1.length][tab2[0].length];
        if (tab1[0].length == tab2.length) {
            for (int i = 0; i < tab1.length; i++) {//ilosc wierszy tab1
                for (int j = 0; j < tab2[0].length; j++) { //ilosc kolumn tab2
                    double temp = 0;
                    for (int w = 0; w < tab2.length; w++) { //ilosc wierszy tab2
                        temp += tab1[i][w] * tab2[w][j];
                    }
                    multipliedMatrix[i][j] = temp;
                }
            }
        } else {
            throw new RuntimeException("Incorrect dimension of tables");
        }
        return multipliedMatrix;
    }
}
