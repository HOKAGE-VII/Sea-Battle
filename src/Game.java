package src;

public class Game {
    private static final String ADMIN_NAME = "Ryan Gosling";
    private Player player1;
    private Player player2;
    private boolean singlePlayerMode;       // флаг режима одиночной игры
    private boolean gameStarted;            // флаг старта игры
    private boolean gameOver;               // флаг конца игры

    public Game(String playerName, boolean singlePlayerMode) {
        this.singlePlayerMode = singlePlayerMode;
        this.gameStarted = false;
        this.gameOver = false;
        initializePlayers(playerName);
    }

    private void initializePlayers(String playerName) {
        player1 = new Player(playerName);
        if (!singlePlayerMode)
        {
            // Реализация для режима с напарником
        }
        else
        {
            player2 = new Bot("Bot");
        }
    }

    public void startGame() {
        if (!gameStarted) {
            setupGame();
            gameStarted = true;
            playGame();
        } else {
            System.out.println("Игра уже начата.");
        }
    }

    private void setupGame()
    {
        player1.setupShips();
        if (!singlePlayerMode)
        {
            // Расстановка для второго игрока, если игра не в одиночном режиме
        }
        else
        {
            player2.setupShips();  // Автоматическая расстановка для бота
        }
    }

    private void playGame() {
        while (!gameOver) {

            do {
                player1.player_field.printField();
                player1.opponent_field.printField();
            }
            while (playerTurn(player1, player2));



            if (checkGameOver(player2)) {
                gameOver = true;
                break;
            }

            do
            {
                player1.player_field.printField();
                player1.opponent_field.printField();
            }
            while (playerTurn(player2, player1));



            if (checkGameOver(player1)) {
                gameOver = true;
                break;
            }
        }
        // Отобразить результаты игры
        //displayGameResults();
    }

    private boolean playerTurn(Player currentPlayer, Player opponent) {
        return currentPlayer.makeMove(opponent);
    }

    private boolean checkGameOver(Player player) {
        return player.allShipsSunk();
    }

//    private void displayGameResults() {
//        // Отображение результатов игры
//        // Например, сколько кораблей у каждого осталось, количество ходов и т.д.
//    }
}