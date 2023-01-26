import java.text.DecimalFormat;

public class Converter {

    DecimalFormat dFDistance = new DecimalFormat("###,###.##");
    DecimalFormat dFCcal = new DecimalFormat("###,###");
    double rateDistance;
    double rateCalories;

    Converter(double distance, double calories) {
        rateDistance = distance;
        rateCalories = calories;
    }

    void convert(int stepsInMonth) {
        double km = rateDistance * stepsInMonth;
        double ccal = rateCalories * stepsInMonth;

        System.out.println("В этом месяце вы прошли " + dFDistance.format(km) + " километров!");
        System.out.println("В этом месяце вы сожгли " + dFCcal.format(ccal) + " килокалорий!");
    }
}
