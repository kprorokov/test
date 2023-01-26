import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class MonthReport {

    public ArrayList<MonthData> months = new ArrayList<>();

    public void monthReportLoad(int month, String path) {
        String content = readFileContents(path);
        assert content != null;
        String[] lines = content.split(System.lineSeparator());  // На лекции в Практикуме, спикер Филипп Воронов
        // сказал, что "\n?\r" - универсальный разделитель. Я последовал его совету :)
        // Исправил тут и в YearReport на System.lineSeparator()
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");
            String titleName = parts[0];
            boolean isExpense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int sumOfOne = Integer.parseInt(parts[3]);

            MonthData monthData = new MonthData(titleName, isExpense, quantity, sumOfOne, month);
            months.add(monthData);
        }
    }


    public void getMonthReport(int month) {
        HashMap<String, Integer> expense = new HashMap<>();
        HashMap<String, Integer> debit = new HashMap<>();
        for (MonthData monthData : months) {
            int finaly = monthData.quantity * monthData.sumOfOne;
            if (monthData.month == month) {
                if (monthData.isExpense) {
                    expense.put(monthData.titleName, expense.getOrDefault(monthData.titleName, 0) + finaly);
                }
                if (!monthData.isExpense) {
                    debit.put(monthData.titleName, debit.getOrDefault(monthData.titleName, 0) + finaly);
                }
            }
        }

        String maxExpenseName = null;
        int maxExpense = 0;
        for (String itemTitle : expense.keySet()) {
            if (maxExpenseName == null) {
                maxExpenseName = itemTitle;
                maxExpense = expense.get(itemTitle);
                continue;
            }
            if (maxExpense < expense.get(itemTitle)) {
                maxExpenseName = itemTitle;
                maxExpense = expense.get(itemTitle);
            }
        }
        System.out.println("Самый большой РАСХОД :" + maxExpense + "р. по товару " + maxExpenseName);



        String maxProfitName = null;
        int maxProfit = 0;
        for (String itemTitle : debit.keySet()) {
            if (maxProfitName == null) {
                maxProfitName = itemTitle;
                maxProfit = debit.get(itemTitle);
                continue;
            }
            if (maxProfit < debit.get(itemTitle)) {
                maxProfitName = itemTitle;
                maxProfit = debit.get(itemTitle);
            }
        }
        System.out.println("Самый большой ДОХОД :" + maxProfit + "р. по товару " + maxProfitName);
    }

    /* List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится " +
                    "в нужной директории.");
            return Collections.emptyList();
        }
    } */
    private String readFileContents(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в " +
                    "нужной директории.");
            return null;
        }
    }
}
