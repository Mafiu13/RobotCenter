package RobotCenter.model;

/**
 * Created by YapYap on 2016-01-15.
 */
public class TypeConverter {


    public static int convertStrToInt(String str) {

        try {
            int strToInt = Integer.parseInt(str);
            return strToInt;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static String convertIntToStr(Integer intToStr) {

        String str = Integer.toString(intToStr);
        return str;
    }

    public static double convertStrToDouble(String str) {


        double strToDouble = Double.parseDouble(str);
        return strToDouble;
    }

    public static String convertDoubleToStr(Double doubleToStr) {

        String str = Double.toString(doubleToStr);
        return str;
    }

    public static Integer convertLongToInt(Long longToInt) {

        return longToInt.intValue();

    }

}
