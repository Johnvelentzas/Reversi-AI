import java.util.ArrayList;

class Player
{
	private int maxDepth;
    private int playerLetter;
	
	public Player() {}

    public Player(int maxDepth, int playerLetter)
    {
        this.maxDepth = maxDepth;
        this.playerLetter = playerLetter;
    }

    private int utility_Funtion(Board board) { //to be used in minimax algorithm after checking for terminal states
        int playerLetter = board.getLastPlayer();
        int opponent;
        if (playerLetter == -1) {opponent = 1;}
        else {opponent = -1;}

        int playerPieces = 0;
        int opponentPieces = 0;
    
        for (int row = 0; row < board.getDimention(); row++) {
            for (int col = 0; col < board.getDimention(); col++) {
                if (board.getPawn(row, col) == playerLetter) {
                    playerPieces++;
                } 
                else if (board.getPawn(row, col) == opponent) {
                    opponentPieces++;
                }
            }
        }
        return playerPieces-opponentPieces;
    }
    
	
	public Move MiniMax(Board board) {
        int maxScore = -1000;
        Move bestMove = null;
        int playerSymbol = this.playerLetter;
        ArrayList<Move> possibleMoves = board.findPossibleMoves(playerLetter);

        for (Move move : possibleMoves) {
            Board newBoard = new Board(board); // Create a copy of the current board
            newBoard.makeMove(move, playerLetter); // Creates a new board according to each possible move --> we search all these for the best one
            //int score = max(newBoard, maxDepth - 1); CALL MIN OR MAX ??? depth is given by the player

            /* 
            if (score > maxScore) { //change the score according to computations made by utility_function() or evaluate()
                maxScore = score;
                bestMove = move;
            }
            */
        }

        return bestMove;
    }
	
	public Move max(Board board, int depth) {return null;}
	
	public Move min(Board board, int depth) {return null;}
}