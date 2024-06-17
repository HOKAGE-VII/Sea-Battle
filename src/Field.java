package src;

import java.util.Arrays;
import java.util.List;

public class Field
{
    private static final List<String> COLUMN_LABELS = Arrays.asList("А", "Б", "В", "Г", "Д", "Е", "Ж", "З", "И", "К", "Л", "М", "Н", "О", "П", "Р");
    private static final int SIZE = 16;     // размер поля
    private int[][] grid;                   // игровое поле
    public Field()
    {
        grid = new int[SIZE][SIZE];
        resetField();
    }

    public void resetField()
    {
        for (int i = 0; i < SIZE; i++) {
            Arrays.fill(grid[i], 0);
        }
    }

    public void printBoard()
    {
        System.out.print("   ");
        for (String col : COLUMN_LABELS)
        {
            System.out.print(col + " ");
        }
        System.out.println();
        for (int row = 0; row < SIZE; row++)
        {
            System.out.printf("%2d ", row + 1);
            for (int col = 0; col < SIZE; col++)
            {
                System.out.print(grid[row][col] + " ");
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
                if (y + size > SIZE - 1) return false; // Проверка на выход за границы
                for (int i = 0; i < size; i++)
                {
                    if (!isCellValid(x + i, y))
                    {
                        return false;
                    }
                }
                break;
            case 1: // Вниз
                if (x + size > SIZE - 1) return false; // Проверка на выход за границы
                for (int i = 0; i < size; i++)
                {
                    if (!isCellValid(x, y + i))
                    {
                        return false;
                    }
                }
                break;
            case 2: // Влево
                if (y - size < 0) return false; // Проверка на выход за границы
                for (int i = 0; i < size; i++)
                {
                    if (!isCellValid(x - i, y))
                    {
                        return false;
                    }
                }
                break;
            case 3: // Вверх
                if (x - size < 0) return false; // Проверка на выход за границы
                for (int i = 0; i < size; i++)
                {
                    if (!isCellValid(x, y - i))
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
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE)
        {
            return false;
        }
        //  Проверка на занятость клетки
        if (grid[x][y] != 0)
        {
            return false;
        }
        // Проверка соседних клеток на наличие кораблей
        for (int i = Math.max(0, x - 1); i <= Math.min(SIZE - 1, x + 1); i++)
        {
            for (int j = Math.max(0, y - 1); j <= Math.min(SIZE - 1, y + 1); j++)
            {
                if (grid[i][j] != 0)
                {
                    return false;
                }
            }
        }
        return true;
    }



    public void addShipToField()
    {

    }

}
