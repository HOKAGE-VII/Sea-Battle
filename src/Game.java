package src;

public class Game
{
    public static void main(String[] args)
    {
        System.out.println("Hello");

        Player player1 = new Player("Ryan Gosling", false, false);
        Player player2 = new Player("Bot", false, false);

        Field field = new Field();

        field.autoShipSetup();

        field.printBoard();


    }


}
