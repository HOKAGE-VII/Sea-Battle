package src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите свое имя: ");
        String player_name = scanner.nextLine();

//        System.out.println("Введите вариант игры:");
//        System.out.println("Одиночная игра - 1");
//        System.out.println("Игра с напарником - 2");
//        String input = scanner.nextLine();
//        boolean singlePlayerMode = convertToBoolean(input);

        Game game = new Game(player_name, true, false);
        game.startGame();
    }

    private static boolean convertToBoolean(String input) {
        return "1".equals(input);
    }
}