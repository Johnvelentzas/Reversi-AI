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

    public Move getRandomMove(Board board)
    {
        ArrayList<Move> moves = board.findPossibleMoves(this.playerLetter);
        Random rand = new Random();
        return moves.get(rand.nextInt(moves.size()));
    }

    public Move getMove(Board board){
        return this.MiniMax(board, maxDepth, playerLetter);
        //return this.MiniMaxAB(board, maxDepth, -1000000, -1000000, playerLetter);
    }


    /**
     * function to be used in Minimax algorithm to evaluate a moves outcome 
     * for a player in order to know if it's best to hoose it or not
     * 
     * @param board The current board state
     * @return evaluation of the actual score for the player (playerPieces-opponentPieces) 
     *         or an estimated evaluation using evaluate() function in Board.java
     */
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
            board.setEvaluation(utility_Funtion(board)); // Evaluate the current board state
            return new Move(); // Return new Move() with no value as no move needs to be made at this stage
        }
    
        int maxScore = Integer.MIN_VALUE; // Initialize the maximum score for the maximizing player
        int minScore = Integer.MAX_VALUE; // Initialize the minimum score for the minimizing player
        Move bestMove = new Move(); // Keep track of the best move found
    
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
            } 
            else {
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
        } 
        else {
            board.setEvaluation(minScore); // Set the board's evaluation to the minimum score found
        }
    
        return bestMove; // Return the best move found at the root level
    }

    /**
     * Implements the MiniMax algorithm with alpha-beta pruning to determine the best move for a player at a given depth.
     *
     * @param board         The current board state.
     * @param depth         The depth limit for the search tree.
     * @param alpha         The alpha value representing the best achievable value for the maximizing player.
     * @param beta          The beta value representing the best achievable value for the minimizing player.
     * @param playerLetter  The current player's identifier (can be PLAYER_1 or PLAYER_2).
     *                      PLAYER_1 represents the maximizing player seeking to maximize the evaluation score.
     *                      PLAYER_2 represents the minimizing player aiming to minimize the evaluation score.
     * @return The best move based on MiniMax evaluation at the root level.
     *         For PLAYER_1, the returned move is the one with the maximum evaluation score.
     *         For PLAYER_2, the returned move is the one with the minimum evaluation score.
     */
    public Move MiniMaxAB(Board board, int depth, int alpha, int beta, int playerLetter) {
        // Base case: If the depth limit is reached or it's a terminal state, evaluate the board and stop recursion
        if (depth == 0 || board.isTerminal()) {
            board.setEvaluation(utility_Funtion(board)); // Evaluate the current board state
            return new Move(); // Return null as no move needs to be made at this stage
        }

        Move bestMove = new Move(); // Keep track of the best move found
        ArrayList<Board> children = board.getChildren(playerLetter); // Get children boards for the current player

        for (Board childBoard : children) {
            int score;
            if (playerLetter == Board.PLAYER_1) {
                // Maximizing player's turn (PLAYER_1)
                MiniMaxAB(childBoard, depth - 1, alpha, beta, Board.PLAYER_2);
                score = childBoard.getEvaluation();
                // Update alpha if a higher score is found for the maximizing player (PLAYER_1)
                if (score > alpha) {
                    alpha = score;
                    bestMove = childBoard.getLastMove(); // Get the move associated with the best child board
                }
            } else {
                // Minimizing player's turn (PLAYER_2)
                MiniMaxAB(childBoard, depth - 1, alpha, beta, Board.PLAYER_1);
                score = childBoard.getEvaluation();
                // Update beta if a lower score is found for the minimizing player (PLAYER_2)
                if (score < beta) {
                    beta = score;
                    bestMove = childBoard.getLastMove(); // Get the move associated with the best child board
                }
            }

            // Alpha-beta pruning: If alpha is greater than or equal to beta, prune remaining branches
            if (alpha >= beta) {
                // Alpha-beta cutoff occurred, no need to evaluate further branches
                break;
            }
        }

        // Update the board evaluation based on the best move found
        if (playerLetter == Board.PLAYER_1) {
            board.setEvaluation(alpha); // Set the board's evaluation to the maximum score found
        } else {
            board.setEvaluation(beta); // Set the board's evaluation to the minimum score found
        }

        return bestMove; // Return the best move found at the root level
    }
}