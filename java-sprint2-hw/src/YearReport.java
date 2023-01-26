import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class YearReport {

    public ArrayList<YearData> years = new ArrayList<>();

    public void yearReportLoad(String path) {
        String content = readFileContents(path).toString();
        String[] lines = content.split(System.lineSeparator());
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);

            YearData yearData = new YearData(month, amount, isExpense);
            years.add(yearData);
        }
    }

    public void getYearReport() {
        HashMap<Integer, Integer> profit = new HashMap<>();
        HashMap<Integer, Integer> expense = new HashMap<>();
        HashMap<Integer, Integer> debit = new HashMap<>();

        for (YearData yearData : years) {
            if (yearData.isExpense) {
                expense.put(yearData.month, expense.getOrDefault(yearData.month, 0) + yearData.amount);
            }
            if (!yearData.isExpense) {
                debit.put(yearData.month, debit.getOrDefault(yearData.month, 0) + yearData.amount);
            }
        }

        for (Integer month : debit.keySet()) {
            Integer profitAmount = debit.get(month) - expense.get(month);
            profit.put(month, profit.getOrDefault(month, 0) + profitAmount);
        }
        for (Integer month : profit.keySet()) {
            System.out.println("Прибыль за " + month + "й месяц: " + profit.get(month));
        }


        int maxExpense = 0;
        int maxExpMonth = 0;
        for (Integer title : expense.keySet()) {
            if (maxExpMonth == 0) {
                maxExpMonth = title;
                maxExpense = expense.get(title);
                continue;
            }
            if (maxExpense < expense.get(title)) {
                maxExpMonth = title;
                maxExpense = expense.get(title);
            }
        }
        System.out.println("Самая большая трата в этом году была в " + maxExpMonth + "м месяце на сумму " +
                maxExpense + "р.");



        Integer maxProfitMonth = 0;
        int maxProfit = 0;
        for (Integer title : debit.keySet()) {
            if (maxProfitMonth == null) {
                maxProfitMonth = title;
                maxProfit = debit.get(title);
                continue;
            }
            if (maxProfit < debit.get(title)) {
                maxProfitMonth = title;
                maxProfit = debit.get(title);
            }
        }
        System.out.println("Самый большой доход в этом году была " + maxProfitMonth + "м месяце на сумму " +
                maxProfit + "р.");



        int expenseSum = 0;
        int debitSum = 0;
        int countExpense = 0;
        int countDebit = 0;
        for (Integer sum : expense.keySet()) {
            expenseSum += expense.get(sum);
            countExpense++;
        }
        for (Integer sum : debit.keySet()) {
            debitSum += debit.get(sum);
            countDebit++;
        }

        int profitSum = debitSum - expenseSum;
        int midProfit = debitSum / countDebit;
        int midExpense = expenseSum / countExpense;

        System.out.println("Средний ДОХОД в этом году составил: " + midProfit + "р.");
        System.out.println("Средний РАСХОД в этом году составил: " + midExpense + "р.");
        System.out.println("Общая ПРИБЫЛЬ за этот год составила: " + profitSum + "р.");
    }

    /* List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с годовым отчётом. Возможно файл не находится " +
                    "в нужной директории.");
            return Collections.emptyList();
        }
    } */
    private String readFileContents(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с годовым отчётом. Возможно файл не находится в " +
                    "нужной директории.");
            return null; // Опять же по примеру спикера Филиппа Воронова на лекции в Практикуме.
        }
    }
}
