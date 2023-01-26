import java.util.HashMap;
import java.util.Objects;

public class Checker {

    private YearReport yearReport;
    private MonthReport monthReport;


    public Checker(YearReport yearReport, MonthReport monthReport) {
        this.yearReport = yearReport;
        this.monthReport = monthReport;
    }

    public boolean check() {
        boolean checker = true;
        HashMap<Integer, Integer> expenseYear = new HashMap<>();
        HashMap<Integer, Integer> debitYear = new HashMap<>();
        for (YearData yearData : yearReport.years) {
            if (yearData.isExpense) {
                expenseYear.put(yearData.month, expenseYear.getOrDefault(yearData.month, 0) +
                        yearData.amount);
            } else {
                debitYear.put(yearData.month, debitYear.getOrDefault(yearData.month, 0 ) + yearData.amount);
            }
        }

        HashMap<Integer, Integer> expenseMonth = new HashMap<>();
        HashMap<Integer, Integer> debitMonth = new HashMap<>();
        for (MonthData monthData : monthReport.months) {
            if (monthData.isExpense) {
                expenseMonth.put(monthData.month, expenseMonth.getOrDefault(monthData.month, 0) +
                        monthData.quantity * monthData.sumOfOne);
            } else {
                debitMonth.put(monthData.month, debitMonth.getOrDefault(monthData.month, 0 ) +
                        monthData.quantity * monthData.sumOfOne);
            }
        }

        for (Integer month : expenseYear.keySet()) {
            if (!Objects.equals(expenseMonth.get(month), expenseYear.get(month))) {
                System.out.println("В отчетах по РАСХОДАМ есть несоответствие в " + month + "м месяце:");
                System.out.println("В годовом отчете указано: " + expenseYear.get(month) + "р. А в месячном: " +
                        expenseMonth.get(month) + "р.");
                checker = false;
            }
        }
        for (Integer month : debitYear.keySet()) {
            if (!Objects.equals(debitMonth.get(month), debitYear.get(month))) {
                System.out.println("В отчетах по ДОХОДАМ есть несоответствие в " + month + "м месяце:");
                System.out.println("В годовом отчете указано: " + debitYear.get(month) + "р. А в месячном: " +
                        debitMonth.get(month) + "р.");
                checker = false;
            }
        }
        return checker;
    }
}
