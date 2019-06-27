/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constructionsolution;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Shashiprabha
 */
public class NumbeFormater {

    public static String formatIntegerToDecimal(int number) {
        DecimalFormat df = new DecimalFormat("###,###.00");
        String angleFormated = df.format(number);
        return angleFormated;
    }

    public static String formatDoubleToDecimal(Double number) {
        DecimalFormat df = new DecimalFormat("###,##0.00");
        String angleFormated = df.format(number);
        return angleFormated;
    }

    public static String formatIntegerToThousandSeparator(Integer number) {
        DecimalFormat df = new DecimalFormat("###,###");
        String angleFormated = df.format(number);
        return angleFormated;
    }

    public static double formatDoubleToDouble(Double number) {
        DecimalFormat df = new DecimalFormat("###,###.00");
        String angleFormated = df.format(number);
        return Double.parseDouble(angleFormated);
    }

    public static String FractionDigitsTowFormat(Object value) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);

        return nf.format(value);
    }

    public static String formatDoubleToClean(double d) {
        if (d == (long) d) {
            return String.format("%d", (long) d);
        } else {
            return String.format("%s", d);
        }
    }

    public static String formatScientificDoubleToString(double d) {
        NumberFormat formatter = new DecimalFormat("#0.00");
        String f = formatter.format(d);
        return f;
    }

    public static double formatCleanString(String number) {
        number = number.replaceAll(",", "");
        return Double.parseDouble(number);
    }

    public static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    

      public static String setDateFormat(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String formatedDate = dateFormat.format(date);
        return formatedDate;
    }
}
