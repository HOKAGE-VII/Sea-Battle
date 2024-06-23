package src;

import java.util.Random;
import java.util.Scanner;

public class Player
{
    private String name;                // имя игрока
    private boolean isBot;              // является ли игрок ботом
    private Field player_field;         // поле игрока
    private Field opponent_field;       // поле противника

    public Player(String name, boolean isBot)
    {
        this.name = name;
        this.isBot = isBot;
        this.player_field = new Field();
        this.opponent_field = new Field();
    }

    public String getName()
    {
        return name;
    }

    public boolean isBot() {
        return isBot;
    }

    public Field getPlayer_field() {
        return player_field;
    }

    public Field getOpponent_field() {
        return opponent_field;
    }

    public void setupShips()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите способ расстановки кораблей:");
        System.out.println("Ручная - 1");
        System.out.println("Автоматическая - 2");
        int shipSetup = Integer.parseInt(scanner.nextLine());

        if (shipSetup == 1)
            manualShipSetup();
        else if (shipSetup == 2) {
            autoShipSetup();
            player_field.printBoard();
        }
    }

    public void autoShipSetup()
    {
        Random random = new Random();
        for (int size : Field.SHIP_SIZES)
        {
            boolean placed = false;
            while (!placed) {
                int x = random.nextInt(Field.FIELD_SIZE);
                int y = random.nextInt(Field.FIELD_SIZE);
                int horizontal = random.nextInt(2);

                Ship ship = new Ship(x, y, horizontal, size);

                if (player_field.checkShipFits(ship))
                {
                    player_field.addShipToField(ship);
                    placed = true;
                }
            }
        }
    }

    public void manualShipSetup() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < Field.SHIP_SIZES.length; i++) {
            int shipSize = Field.SHIP_SIZES[i];
            boolean placed = false;
            do {
                try {
                    System.out.printf("Введите координаты для корабля размера %d\n", shipSize);
                    String coordinates = scanner.nextLine().toUpperCase();
                    int rotation = 0;
                    if (shipSize != 1)
                    {
                        System.out.print("Введите направление (0 - вправо, 1 - вниз, 2 - влево, 3 - вверх): ");
                        rotation = Integer.parseInt(scanner.nextLine());
                    }

                    int x = Integer.parseInt(coordinates.substring(1));
                    int y = Field.COLUMN_LABELS.indexOf(coordinates.substring(0, 1)) + 1;

                    Ship ship = new Ship(x, y, rotation, shipSize);
                    if (player_field.checkShipFits(ship))
                    {
                        player_field.addShipToField(ship);
                        placed = true;
                        player_field.printBoard();
                    }
                    else
                    {
                        System.out.println("Неверные координаты или корабль пересекается с другими. Попробуйте ещё раз.");
                    }
                } catch (Exception e) {
                    System.out.println("Ошибка при вводе координат. Попробуйте ещё раз.");
                }
            } while (!placed);
        }
    }

    public void botMakeMove(Player opponent) {

    }
    public void makeMove(Player opponent) {
        Scanner scanner = new Scanner(System.in);
        String coordinates;
        int result;

        System.out.println("\nХод игрока " + name);

        do {
            System.out.print("Введите координаты для атаки: ");
            coordinates = scanner.nextLine().toUpperCase();
            int x = Integer.parseInt(coordinates.substring(1));
            int y = Field.COLUMN_LABELS.indexOf(coordinates.substring(0, 1)) + 1;
            result = opponent.getOpponent_field().receiveAttack(coordinates);

            switch (result) {
                case 0:
                    System.out.println("Мимо!");
                    opponent_field.markField(x, y, Field.EMPTY_MARK);
                    opponent.getOpponent_field().markField(x, y, Field.EMPTY_MARK);
                    break;
                case 1:
                    System.out.println("Убил!");
                    //opponent.getOpponent_field().
                    //opponent.getOpponent_field().;
                    break;
                case 2:
                    System.out.println("Ранил!");
                    opponent_field.markField(x, y, Field.HIT_MARK);
                    opponent.getOpponent_field().markField(x, y, Field.HIT_MARK);
                    break;
                default:
                    System.out.println("Ошибка при обработке хода.");
                    break;
            }
        } while (result == -1);

        player_field.printBoard();
        opponent.getOpponent_field().printBoard();
    }

}
