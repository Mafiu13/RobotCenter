package RobotCenter.model;

/**
 * Created by YapYap on 2016-01-15.
 */
public class TypeConverter {


    public int convertStrToInt(String str) {

        try {
            int strToInt = Integer.parseInt(str);
            return strToInt;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public String convertIntToStr(int intToStr) {

        String str = Integer.toString(intToStr);
        return str;
    }

    public double convertStrToDouble(String str) {


        double strToDouble = Double.parseDouble(str);
        return strToDouble;
    }

    public String convertDoubleToStr(double doubleToStr) {

        String str = Double.toString(doubleToStr);
        return str;
    }






}
