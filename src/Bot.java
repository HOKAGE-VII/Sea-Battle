package src;

import java.util.Random;

public class Bot extends Player {

    public Bot(String name) {
        super(name);
    }

    public void setupShips()
    {
        setupShipsAutomatically();
    }
    public boolean makeMove(Player opponent)
    {
        Random random = new Random();
        boolean input = false;
        do {
            int x = random.nextInt(Field.FIELD_SIZE);
            int y = random.nextInt(Field.FIELD_SIZE);

            System.out.printf("Бот выбирал координату: x = %d y = %d\n", x, y);
            System.out.printf("Бот выбирал координату: %s%d\n", Field.COLUMN_LABELS.get(y), x + 1);

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
        } while (!input);
        return false;
    }
}
