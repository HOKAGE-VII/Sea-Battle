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
}
