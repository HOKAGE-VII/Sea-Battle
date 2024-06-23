package src;

public class Game {
    private Player player1;
    private Player player2;
    private boolean singlePlayerMode;       // флаг режима одиночной игры
    private boolean adminMode;              // флаг режима администратора
    private boolean gameStarted;
    private boolean gameOver;

    public Game(String playerName, boolean singlePlayerMode, boolean adminMode) {
        this.singlePlayerMode = singlePlayerMode;
        this.adminMode = adminMode;
        this.gameStarted = false;
        this.gameOver = false;
        initializePlayers(playerName);
    }

    private void initializePlayers(String playerName) {
        player1 = new Player(playerName, false);
        if (!singlePlayerMode)
        {
            // Реализация для режима с напарником
        }
        else
        {
            player2 = new Player("Bot", true);
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
            player2.autoShipSetup();  // Автоматическая расстановка для бота
        }
    }

    private void playGame() {
        while (!gameOver) {
            playerTurn(player1, player2);
            if (checkGameOver(player2)) {
                gameOver = true;
                break;
            }
            if (!singlePlayerMode) {
                playerTurn(player2, player1);
                if (checkGameOver(player1)) {
                    gameOver = true;
                    break;
                }
            }
        }
        // Отобразить результаты игры
        //displayGameResults();
    }

    private void playerTurn(Player currentPlayer, Player opponent) {
        currentPlayer.makeMove(opponent);
    }

    private boolean checkGameOver(Player player) {
        //return player.allShipsSunk();
        return true;
    }

//    private void displayGameResults() {
//        // Отображение результатов игры
//        // Например, сколько кораблей у каждого осталось, количество ходов и т.д.
//    }
}