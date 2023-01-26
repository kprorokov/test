
public class StepTracker {

    int[][] calendar = new int[12][30];
    int stepsGoal = 10000;


    void saveSteps(int userMonthChoice, int userDayChoice, int userSteps) {

        calendar[userMonthChoice - 1][userDayChoice - 1] += userSteps;
        System.out.println("Шаги за " + userDayChoice + "." + userMonthChoice + " сохранены! Количество: " + userSteps);
    }

    void stepsInMonth(int userResultChoice) {
        int sum = 0;

        for (int i = 0; i < calendar[userResultChoice - 1].length; i++) {
            sum = sum + calendar[userResultChoice - 1][i];
            System.out.print((i + 1) + " день: " + calendar[userResultChoice - 1][i]);
            if (i < calendar[userResultChoice - 1].length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("\nШагов за " + userResultChoice + " месяц: " + sum);
    }

    int findMaxSteps(int userResultChoice) {
        int maxSteps = 0;
        for (int i = 0; i < calendar[userResultChoice - 1].length; i++) {
            if (calendar[userResultChoice - 1][i] > maxSteps) {
                maxSteps = calendar[userResultChoice - 1][i];
            }
        }
        return maxSteps;
    }

    int findAverSteps(int userResultChoice) {
        int averSteps = 0;
        for (int i = 0; i < calendar[userResultChoice - 1].length; i++) {
            averSteps = averSteps + calendar[userResultChoice - 1][i];
        }
        return averSteps / calendar[userResultChoice - 1].length;
    }

    int sumSteps(int userResultChoice) {
        int sum = 0;

        for (int i = 0; i < calendar[userResultChoice - 1].length; i++) {
            sum = sum + calendar[userResultChoice - 1][i];
        }
        return sum;
    }

    int findStepsSeries(int userResultChoice) {
        int goalCount = 0;
        int curCount = 0;

        for (int i = 0; i < calendar[userResultChoice - 1].length; i++) {
            if (calendar[userResultChoice - 1][i] >= stepsGoal) {
                curCount++;
                if (curCount > goalCount) {
                    goalCount = curCount;
                }
            } else {
                curCount = 0;
            }
        }
        return goalCount;
    }

    void changeGoal(int userChangeStepsGoal) {
        if (stepsGoal > userChangeStepsGoal) {
            System.out.println("Какой вы слабый...");
        } else if (stepsGoal * 2 < userChangeStepsGoal) {
            System.out.println("Ничоси! Как много времени на шаги...");
        } else if (userChangeStepsGoal == 0) {
            System.out.println("Эх, вы... Любитель дивана...");
        } else {
            System.out.println("Движение - это жизнь!");
        }
        stepsGoal = userChangeStepsGoal;
        System.out.println("Шаги успешно сохранены! Теперь, ваша цель - " + stepsGoal + " шагов в день.");
    }
}
