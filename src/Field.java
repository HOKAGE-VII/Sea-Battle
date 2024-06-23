package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Field
{
    public static final List<String> COLUMN_LABELS =
            Arrays.asList("А", "Б", "В", "Г", "Д", "Е", "Ж", "З", "И", "К", "Л", "М", "Н", "О", "П", "Р");
    public static final int[] SHIP_SIZES =
            {6, 5, 5, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1};    // размеры кораблей
    public static final int FIELD_SIZE = 16;                                   // размер поля

    public static final char HIT_MARK = 'X';                                    // представление подбитой клетки
    public static final char EMPTY_MARK = '.';                                  // представление пустой клетки
    public static final char SHIP_MARK = 'O';                                   // представление пустой клетки

    private char[][] grid;                                                      // игровое поле
    private List<Ship> ships;                                                   // список кораблей
    public Field()
    {
        grid = new char[FIELD_SIZE][FIELD_SIZE];
        ships = new ArrayList<Ship>();
        resetField();
    }

    public void resetField()
    {
        for (int i = 0; i < FIELD_SIZE; i++) {
            Arrays.fill(grid[i], EMPTY_MARK);
        }
    }

    public void printBoard()
    {
        System.out.println();
        System.out.print("   ");
        for (String col : COLUMN_LABELS)
        {
            System.out.print(col + "  ");
        }
        System.out.println();
        for (int row = 0; row < FIELD_SIZE; row++)
        {
            System.out.printf("%2d ", row + 1);
            for (int col = 0; col < FIELD_SIZE; col++)
            {
                System.out.print(grid[row][col] + "  ");
            }
            System.out.println();
        }
    }

    public boolean checkShipFits(Ship ship)
    {
        int x = ship.getX();
        int y = ship.getY();
        int rotation = ship.getRotation();
        int size = ship.getSize();

        switch (rotation)
        {
            case 0: // Вправо
                if (y + size > FIELD_SIZE - 1) return false; // Проверка на выход за границы
                for (int i = 0; i < size; i++)
                {
                    if (!isCellValid(x, y + i))
                    {
                        return false;
                    }
                }
                break;
            case 1: // Вниз
                if (x + size > FIELD_SIZE - 1) return false; // Проверка на выход за границы
                for (int i = 0; i < size; i++)
                {
                    if (!isCellValid(x + i, y))
                    {
                        return false;
                    }
                }
                break;
            case 2: // Влево
                if (y - size < 0) return false; // Проверка на выход за границы
                for (int i = 0; i < size; i++)
                {
                    if (!isCellValid(x, y - i))
                    {
                        return false;
                    }
                }
                break;
            case 3: // Вверх
                if (x - size < 0) return false; // Проверка на выход за границы
                for (int i = 0; i < size; i++)
                {
                    if (!isCellValid(x - i, y))
                    {
                        return false;
                    }
                }
                break;
            default:
                return false;
        }

        return true;
    }

    private boolean isCellValid(int x, int y)
    {
        // Проверка на выход за границы поля
        if (x < 0 || x >= FIELD_SIZE || y < 0 || y >= FIELD_SIZE)
        {
            return false;
        }
        //  Проверка на занятость клетки
        if (grid[x][y] != EMPTY_MARK)
        {
            return false;
        }
        // Проверка соседних клеток на наличие кораблей
        for (int i = Math.max(0, x - 1); i <= Math.min(FIELD_SIZE - 1, x + 1); i++)
        {
            for (int j = Math.max(0, y - 1); j <= Math.min(FIELD_SIZE - 1, y + 1); j++)
            {
                if (grid[i][j] != EMPTY_MARK)
                {
                    return false;
                }
            }
        }
        return true;
    }

    public void addShipToField(Ship ship)
    {
        int x = ship.getX();
        int y = ship.getY();
        int rotation = ship.getRotation();
        int size = ship.getSize();

        switch (rotation)
        {
            case 0: // Вправо
                for (int i = 0; i < size; i++)
                {
                    grid[x][y + i] = SHIP_MARK;
                }
                ships.add(ship);
                break;
            case 1: // Вниз
                for (int i = 0; i < size; i++)
                {
                    grid[x + i][y] = SHIP_MARK;
                }
                ships.add(ship);
                break;
            case 2: // Влево
                for (int i = 0; i < size; i++)
                {
                    grid[x][y - i] = SHIP_MARK;
                }
                ships.add(ship);
                break;
            case 3: // Вверх
                for (int i = 0; i < size; i++)
                {
                    grid[x - i][y] = SHIP_MARK;
                }
                ships.add(ship);
                break;
            default:
        }
    }

    public Ship getShipFromHit(int x, int y)
    {
        for (Ship ship : ships) {
            if (isHit(ship, x, y))
                return ship;
        }
        return null;
    }

    public int receiveAttack(String coordinates) {
        int x = Integer.parseInt(coordinates.substring(1)) - 1;
        int y = COLUMN_LABELS.indexOf(coordinates.substring(0, 1));

        if (grid[x][y] == EMPTY_MARK)
            return 0;   // мимо

        else if (grid[x][y] == SHIP_MARK)
        {
            for (Ship ship : ships) {
                if (isHit(ship, x, y)) {
                    ship.hit();
                    if (ship.isSunk())
                        return 1;   // убил
                    else
                        return 2;   // попал
                }
            }
        }
        return -1;  // ошибка
    }

    private boolean isHit(Ship ship, int x, int y) {
        int shipX = ship.getX();
        int shipY = ship.getY();
        int rotation = ship.getRotation();
        int size = ship.getSize();

        for (int i = 0; i < size; i++) {
            int currentX = shipX;
            int currentY = shipY;

            switch (rotation) {
                case 0:
                    currentY += i;
                    break;
                case 1:
                    currentX += i;
                    break;
                case 2:
                    currentY -= i;
                    break;
                case 3:
                    currentX -= i;
                    break;
            }

            if (currentX == x && currentY == y) {
                return true;
            }
        }
        return false;
    }

    public void markField(int x, int y, char mark)
    {
        grid[x][y] = mark;
    }
}
