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

    public String getName() {
        return name;
    }

    public boolean isBot() {
        return isBot;
    }

    public boolean isAuto_ship_setup() {
        return auto_ship_setup;
    }

    public Field getPlayer_field() {
        return player_field;
    }

}
