import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Поехали!

        YearReport yearReport = new YearReport();
        MonthReport monthReport = new MonthReport();
        Checker checker = new Checker(yearReport, monthReport);
        Scanner scanner = new Scanner(System.in);

        boolean checkReadMonth = false;
        boolean checkReadYear = false;



        System.out.println("Введите год и период по месяцам, за которые Вы хотели бы получить отчет.");
        System.out.println("На данный момент имеются отчеты по 2021 году." + System.lineSeparator() + "Введите год:");
        int year = scanner.nextInt();
        if (year != 2021) {
            printErrorYear();
            return;
        }

        System.out.println("Введите с какого месяца Вы хотели бы получить отчет:");
        int firstMonth = scanner.nextInt();
        System.out.println("Введите по какой месяц Вы хотели бы получить отчет:");
        int lastMonth = scanner.nextInt();
        if (firstMonth <= 0 || firstMonth > 12 || lastMonth <= 0 || lastMonth > 12) {
            printErrorMonth();
            return;
        }
        else if (firstMonth > lastMonth) {
            System.out.println("Вы перепутали местами первый и последний месяцы." + System.lineSeparator() +
                    "Перезапустите программу.");
            return;
        }


        while (true) {
            printMenu();
            int userInput = scanner.nextInt();

            if (userInput == 1) {
                checkReadMonth = true;
                for (int i = firstMonth; i <= lastMonth; i++) {
                    if (i > 0 && i < 10) {
                        monthReport.monthReportLoad(i, "resources/m." + year + "0" + i + ".csv");
                    } else if (i >= 10) {
                        monthReport.monthReportLoad(i, "resources/m." + year + i + ".csv");
                    }
                }
                System.out.println("Месячные отчеты успешно считаны.");
            }
            else if (userInput == 2) {
                checkReadYear = true;
                yearReport.yearReportLoad("resources/y." + year + ".csv");
                System.out.println("Годовой отчет успешно считан.");
            }
            else if (userInput == 3) {
                if (!checkReadMonth || !checkReadYear) {
                    System.out.println("Сначала необходимо считать годовой и месячные отчеты.");
                }
                else {
                    if (checker.check()) {
                        System.out.println("Сверка прошла успешно. Данные не противоречат друг другу.");
                    } else {
                        checker.check();
                    }
                }
            }
            else if (userInput == 4) {
                if (/*!checkReadMonth ||*/ monthReport.months.isEmpty() || monthReport.months == null) {
                    System.out.println("Сначала необходимо считать месячные отчеты.");
                    }
                else {
                    for (int i = firstMonth; i <= lastMonth; i++) {
                        System.out.println("Информация по " + i + "му месяцу:");
                        monthReport.getMonthReport(i);
                    }
                }
            }
            else if (userInput == 5) {
                if (!checkReadYear) {
                    System.out.println("Сначала необходимо считать годовой отчет.");
                }
                else {
                    System.out.println("Информация по " + year + "му году:");
                    yearReport.getYearReport();
                }
            }
            else if (userInput == 0) {
                System.out.println("Всего хорошего!");
                break;
            }
            else {
                System.out.println("Такой команды пока нет.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("Вас приветствует программа \"Бухгалтер, милый мой бухгалтер\"!" + System.lineSeparator() +
                "Что желаете сделать?");
        System.out.println("1 - Считать месячные отчеты.");
        System.out.println("2 - Считать годовой отчет.");
        System.out.println("3 - Сверка отчетов.");
        System.out.println("4 - Показать информацию о месячных отчетах."); // Я же сделал проверку на true-false.
        // У меня выдает "Сначала необходимо считать месячные отчеты."
        System.out.println("5 - Показать информацию о годовом отчете.");
        System.out.println("0 - Выход.");
    }

    private static void printErrorMonth() {
        System.out.println("Простите, но у нас пока 12 месяцев... Не меньше и не больше.");
    }

    private static void printErrorYear() {
        System.out.println("Данные отсутствуют. Перезапустите программу и выберите другой год");
    }
}

