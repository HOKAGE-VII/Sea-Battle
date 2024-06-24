package src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите свое имя: ");
        String player_name = scanner.nextLine();

        Game game = new Game(player_name, true);
        game.startGame();
    }
}