package src;

public class Player
{
    private String name;                // имя игрока
    private boolean isBot;              // является ли игрок ботом
    private boolean auto_ship_setup;    // автоматическая расстановка кораблей
    private Field player_field;         // поле игрока

    public Player(String name, boolean isBot, boolean auto_ship_setup)
    {
        this.name = name;
        this.isBot = isBot;
        this.auto_ship_setup = auto_ship_setup;
        this.player_field = new Field();
    }
}
