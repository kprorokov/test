import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StepTracker stepTracker = new StepTracker();
        Converter converter = new Converter(0.00075, 0.05);

        while (true) {
            printMenu();
            int userInput = scanner.nextInt();

            if (userInput == 1) {
                System.out.println("Введите месяц от 1 до 12");
                int userMonthChoice = scanner.nextInt();
                System.out.println("Выберите день от 1 до 30");
                int userDayChoice = scanner.nextInt();
                System.out.println("Сколько шагов вы прошли?");
                int userSteps = scanner.nextInt();
                if (userMonthChoice > 0 && userDayChoice > 0 && userSteps >= 0) {
                stepTracker.saveSteps(userMonthChoice, userDayChoice, userSteps);
                } else {
                    printError();
                }
            } else if (userInput == 2) {
                System.out.println("За какой месяц вы хотите увидеть ваши шикарные результаты?");
                int userResultChoice = scanner.nextInt();
                    if (userResultChoice <= 0) {
                        printError();
                    } else {
                        stepTracker.stepsInMonth(userResultChoice);
                        System.out.println("Самое большое количество шагов: " + stepTracker.findMaxSteps(userResultChoice));
                        System.out.println("В среднем вы проходите " + stepTracker.findAverSteps(userResultChoice) + " шагов в день.");
                        System.out.println("Ваша лучшая серия по преодолению цели по дням: " + stepTracker.findStepsSeries(userResultChoice));
                        converter.convert(stepTracker.sumSteps(userResultChoice));
                        }
            } else if (userInput == 3) {
                System.out.println("Сколько вы планируете проходить шагов в день?");
                int userChangeStepsGoal = scanner.nextInt();
                    if (userChangeStepsGoal < 0) {
                        printError();
                    } else {
                        stepTracker.changeGoal(userChangeStepsGoal);
                    }
            } else if (userInput == 0) {
                System.out.println("Программа завершена");
                break;
            } else {
                System.out.println("Нет такой команды.");
            }
        }
    }

    public static void printMenu() {
        System.out.println("Выберите, что Вы хотите сделать: ");
        System.out.println("1. Ввести количество шагов за определенный день");
        System.out.println("2. Статистика за определенный месяц");
        System.out.println("3. Новая цель по количеству шагов в день");
        System.out.println("0. Выход");
    }

    public static void printError() {
        System.out.println("Отрицательное значение не допустимо!");
    }
}

