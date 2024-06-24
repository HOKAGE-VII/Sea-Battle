package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Player
{
    public static final int[] SHIP_SIZES =
            {6, 5, 5, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1};    // список размеров кораблей
    protected String name;                                                        // имя игрока
    protected Field player_field;                                                 // поле игрока
    protected Field opponent_field;                                               // поле противника
    protected List<Ship> ships;

    public Player(String name)
    {
        this.name = name;
        this.player_field = new Field();
        this.opponent_field = new Field();
        this.ships = new ArrayList<>();
    }

    public String getName()
    {
        return name;
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
        System.out.println(name + ", выберите способ расстановки кораблей: (1) Вручную, (2) Автоматически");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            setupShipsManually();
        } else {
            setupShipsAutomatically();
        }
    }

    public void setupShipsAutomatically()
    {
        Random random = new Random();
        for (int size : SHIP_SIZES)
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
                    ships.add(ship);
                    placed = true;
                }
            }
        }
        player_field.printField();
    }

    public void setupShipsManually() {
        Scanner scanner = new Scanner(System.in);
        for (int size : SHIP_SIZES) {
            boolean placed = false;
            do {
                try {
                    System.out.printf("Введите начальную координату для корабля размера %d\n", size);

                    String coordinates = scanner.nextLine().toUpperCase();
                    int rotation = 0;

                    if (size > 1)
                    {
                        System.out.print("Введите направление (0 - вправо, 1 - вниз, 2 - влево, 3 - вверх): ");
                        rotation = Integer.parseInt(scanner.nextLine());
                    }

                    int x = Integer.parseInt(coordinates.substring(1));
                    int y = Field.COLUMN_LABELS.indexOf(coordinates.substring(0, 1)) + 1;

                    Ship ship = new Ship(x, y, rotation, size);
                    if (player_field.checkShipFits(ship))
                    {
                        player_field.addShipToField(ship);
                        ships.add(ship);
                        placed = true;
                        player_field.printField();
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

    // TODO: подправить логику, чтобы возвращались нормальные значения
    public boolean makeMove(Player opponent) {
        Scanner scanner = new Scanner(System.in);
        boolean input = false;
        do {
            try {
                System.out.println(name + ", ваш ход.");
                System.out.println("Введите координату для атаки:");
                String coordinates = scanner.nextLine().toUpperCase();

                int x = Integer.parseInt(coordinates.substring(1)) - 1;
                int y = Field.COLUMN_LABELS.indexOf(coordinates.substring(0, 1));

                if (opponent_field.isCellValid_2(x, y))
                {
                    int hit = opponent.getPlayer_field().receiveAttack(x, y);
                    if (hit == 0)
                    {
                        opponent_field.markField(x, y, Field.MISS_MARK);
                        System.out.println("Мимо.");
                        return false;
                    }
                    else if (hit == 1)
                    {
                        opponent_field.markField(x, y, Field.HIT_MARK);
                        opponent_field.markShipSunk(opponent.getPlayer_field().getShipFromCoord(x, y));
                        System.out.println("Убил.");
                        return true;
                    }
                    else if (hit == 2)
                    {
                        opponent_field.markField(x, y, Field.HIT_MARK);
                        System.out.println("Попал.");
                        return true;
                    }
                    else if (hit == -1)
                    {
                        System.out.println("Ошибка.");
                        return true;
                    }
                    input = true;
                }
                else
                {
                    System.out.println("Неверная координата. Попробуйте снова.");
                }
            }
            catch (Exception e)
            {
                System.out.println("Ошибка при вводе координат. Попробуйте ещё раз.");
            }
        } while (!input);
        return false;
    }

    public boolean allShipsSunk()
    {
        for (Ship ship : ships) {
            if (ship.getSize() != ship.getHits())
                return false;
        }
        return true;
    }

}
