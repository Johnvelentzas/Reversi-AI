import java.util.ArrayList;
import java.util.Random;

public class Player
{
	private int maxDepth;
    private int playerLetter;
	
	public Player() {}

    public Player(int maxDepth, int playerLetter)
    {
        this.maxDepth = maxDepth;
        this.playerLetter = playerLetter;
    }

    public int getPlayerLetter()
    {
        return this.playerLetter;
    }

    public Move getMove(Board board)
    {
        ArrayList<Move> moves = board.findPossibleMoves(this.playerLetter);
        Random rand = new Random();
        return moves.get(rand.nextInt(moves.size()));
    }

    private int utility_Funtion(Board board) 
    { //to be used in minimax algorithm after checking for terminal states
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

    /**
    * Implements the MiniMax algorithm to determine the best move for a player at a given depth.
    *
    * @param board The current board state.
    * @param depth The depth limit for the search tree.
    * @param playerLetter The current player's identifier (can be PLAYER_1 or PLAYER_2).
    *                    PLAYER_1 represents the maximizing player seeking to maximize the evaluation score.
    *                    PLAYER_2 represents the minimizing player aiming to minimize the evaluation score.
    * @return The best move based on MiniMax evaluation at the root level.
    *         For PLAYER_1, the returned move is the one with the maximum evaluation score.
    *         For PLAYER_2, the returned move is the one with the minimum evaluation score.
    */
	public Move MiniMax(Board board, int depth, int playerLetter) 
    {
        // Base case: If the depth limit is reached or it's a terminal state, evaluate the board and stop recursion
        if (depth == 0 || board.isTerminal()) {
            board.setEvaluation(board.evaluate()); // Evaluate the current board state
            return null; // Return null as no move needs to be made at this stage
        }
    
        int maxScore = Integer.MIN_VALUE; // Initialize the maximum score for the maximizing player
        int minScore = Integer.MAX_VALUE; // Initialize the minimum score for the minimizing player
        Move bestMove = null; // Keep track of the best move found
    
        ArrayList<Board> children = board.getChildren(playerLetter); // Get children boards for the current player
    
        for (Board childBoard : children) {
            if (playerLetter == Board.PLAYER_1) {
                // If the current player is PLAYER_1 (maximizing player)
                MiniMax(childBoard, depth - 1, Board.PLAYER_2); // Recursively call MiniMax for the opponent (PLAYER_2)
                int score = childBoard.getEvaluation(); // Get the evaluation score from the opponent's move
                // Update the maximum score if a higher score is found
                if (score > maxScore) {
                    maxScore = score; 
                    bestMove = childBoard.getLastMove(); // Get the move associated with the best child board
                }
            } else {
                // If the current player is PLAYER_2 (minimizing player)
                MiniMax(childBoard, depth - 1, Board.PLAYER_1); // Recursively call MiniMax for the opponent (PLAYER_1)
                int score = childBoard.getEvaluation(); // Get the evaluation score from the opponent's move
                // Update the minimum score if a lower score is found
                if (score < minScore) {
                    minScore = score;
                    bestMove = childBoard.getLastMove(); // Get the move associated with the best child board
                }
            }
        }
    
        // Update the board evaluation based on the best move found
        if (playerLetter == Board.PLAYER_1) {
            board.setEvaluation(maxScore); // Set the board's evaluation to the maximum score found
        } else {
            board.setEvaluation(minScore); // Set the board's evaluation to the minimum score found
        }
    
        return bestMove; // Return the best move found at the root level
    }
    
	/* 
	public Move MiniMax(Board board) { //no prunning
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
*/

}