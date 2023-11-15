import java.util.ArrayList;
import java.util.Random;

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

    public Move getMove(Board board){
        ArrayList<Move> moves = board.findPossibleMoves(this.playerLetter);
        Random rand = new Random();
        return moves.get(rand.nextInt(moves.size()));
    }

    private int utility_Funtion(Board board) { //to be used in minimax algorithm after checking for terminal states
        int playerLetter = board.getLastPlayer();
        int opponent;
        if (playerLetter == -1) {opponent = 1;}
        else {opponent = -1;}

        int playerPieces = 0;
        int opponentPieces = 0;
        
        //utility function can only be used if we are on a terminal state of the board
        if (board.isTerminal()) {
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

        //evaluate function is used when the board is at a non terminal state
        else {
            return board.evaluate();
        }
    }
    
	
	public Move MiniMax(Board board) { //ΜΙΝΙΜΑΧ ΧΩΡΙΣ ΠΡΙΟΝΙΣΜΑ Α-Β
        int maxScore = -1000;
        Move bestMove = null;
        int playerSymbol = this.playerLetter;
        ArrayList<Move> possibleMoves = board.findPossibleMoves(playerLetter);

        for (Move move : possibleMoves) {
            Board newBoard = new Board(board); // Create a copy of the current board

            // TODO USE getChildren METHOD TO FIND ALL POSIBLE MOVES 
            ArrayList<Board> children = newBoard.getChildren(playerSymbol);

            newBoard.makeMove(move, playerSymbol); // Creates a new board according to each possible move --> we search all these for the best one
            max(newBoard, maxDepth - 1, playerSymbol); //CALL MIN OR MAX ??? depth is given by the player

            int score = board.getEvaluation();
            if (score > maxScore) { //change the score according to computations made by utility_function() or evaluate()
                maxScore = score;
                bestMove = move;
            }
        }
        return bestMove;
    }
	
	public Move max(Board board, int depth, int playerLetter) 
    {    
        return null;
    }
	
	public Move min(Board board, int depth, int playerLetter) 
    {
        return null;
    }
}