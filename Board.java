import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;


public class Board
{

	public static final int W = 1;
    public static final int B = -1;
    public static final int EMPTY = 0;

    public static final String WHITE = "W";
    public static final String BLACK = "B";
    public static final String UNDEFINED = "?";

    public static final int PLAYER_1 = 1;
    public static final int PLAYER_2 = -1;

    private int player1score = 2;
    private int player2score = 2;

    public int player1edgePawns = 0;
    public int player2edgePawns = 0;

    public int NumberOfMoves1;
    public int NumberOfMoves2;

    public static final int DEFAULT_DIMENTION = 8;
    public static final int DEFAULT_MAX_DEPTH = 8;

    private int[][] gameBoard;

    private int lastPlayer;

    private Move lastMove;
	
	private int dimension;

    private int evaluation;


    /**
     * Default board constructor. Creates an {@code empty} board with the default dimentions
     */
    public Board() 
    {
        create_board(DEFAULT_DIMENTION);
    }
	
    /**
     * Basic board constructor. Creates an {@code empty} board with the given dimentions
     * @param dim   x and y dimention.
     */
	public Board(int dimentions) 
    {
        create_board(dimentions);
    }

    /**
     * Internal constructor.
     * @param dimentions x and y dimentions
     */
    private void create_board(int dimentions) 
    {
        this.dimension = dimentions;
        this.gameBoard = new int[dimentions][dimentions];
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                if (i == dimentions / 2  - 1 && j == dimentions / 2 - 1) {
                    this.gameBoard[i][j] = B;
                    continue;
                }
                if (i == dimentions / 2 && j == dimentions / 2) {
                    this.gameBoard[i][j] = B;
                    continue;
                }
                if (i == dimentions / 2 && j == dimentions / 2 - 1) {
                    this.gameBoard[i][j] = W;
                    continue;
                }
                if (i == dimentions / 2  - 1 && j == dimentions / 2) {
                    this.gameBoard[i][j] = W;
                    continue;
                }
                this.gameBoard[i][j] = EMPTY;
            }
        }
    }
	
	/**
     * Copy constructor.
     */
    public Board(Board board) 
    {
        this.dimension = board.dimension;
        this.gameBoard = new int[this.dimension][this.dimension];
        this.setGameBoard(board.gameBoard);
    }
	
    /**
     * Prints the String representation of the board.
     * e.x. reversi|0|1|2|3|4|5|6|7|
     *            0|E|E|E|E|E|E|E|E|
     *            1|E|E|E|E|E|E|E|E|
     *            2|E|E|E|E|E|E|E|E|
     *            3|E|E|E|B|W|E|E|E|
     *            4|E|E|B|W|B|E|E|E|
     *            5|E|E|W|E|E|E|E|E|
     *            6|E|E|E|E|E|E|E|E|
     *            7|E|E|E|E|E|E|E|E|
     */
	public void print() 
    {
        System.out.println(this.toString());
    }


	/**    
    * Get children of the current board state for a specific player.*
    * @param playerLetter The player for whom to generate children.
    * @return A list of Board objects representing the possible children.
    */
	ArrayList<Board> getChildren(int playerLetter) 
    {
        ArrayList<Board> children = new ArrayList<>();
        // Get all possible moves for the player
        ArrayList<Move> possibleMoves = findPossibleMoves(playerLetter);

        // Generate a new board state for each possible move
        for (Move move : possibleMoves) {
            // Create a copy of the current board
            Board childBoard = new Board(this);

            // Make the move on the copied board
            childBoard.makeMove(move, playerLetter);

            // Add the new board state to the list of children
            children.add(childBoard);
        }

        return children;
    }



	/**    
    * Evaluate function for a non terminal state of the board.
    * Used instad of the utility function to give an approximation of the computation
    * @return An integer to show how much points a player can earn by making a specific move
    * Used in minimax function (Player.java)
    */
    public int evaluate() 
    {
        int h;
        int f1; //total player pawns on board - total opponent pawns
        int f2; //total player pawn safe on corners - the opponent's ones
        int f3; //total pawns not in corners - the opponent's ones
        int f4; //total available moves

        if (this.lastPlayer == 1) {
            f1 = player1score - player2score;
            f2 = player1edgePawns - player2edgePawns;
            f3 = (player1score - player1edgePawns) - (player2score - player2edgePawns);
            f4 = NumberOfMoves1;
        }
        else {
            f1 = player2score - player1score;
            f2 = player2edgePawns - player1edgePawns;
            f3 = (player2score - player2edgePawns) - (player1score - player1edgePawns);
            f4 = NumberOfMoves2;        
        }

        h = f1 + (3*f2) + (int)(java.lang.Math.floor(f3/2)) + (2*f4);
        return h;
    }
	
	public boolean isTerminal() 
    {
        return this.findPossibleMoves(PLAYER_1).isEmpty() && this.findPossibleMoves(PLAYER_2).isEmpty();
    }
	
	public Move getLastMove()
    {
        return this.lastMove;
    }

    public int getLastPlayer()
    {
        return this.lastPlayer;
    }

    public int getPlayer1Score()
    {
        return this.player1score;
    }

    public int getPlayer2Score()
    {
        return this.player2score;
    }

    public int[][] getGameBoard()
    {
        return this.gameBoard;
    }
    
    public int getDimention() 
    {
        return this.dimension;
    }

    public void setEvaluation(int evaluation) 
    {
        this.evaluation = evaluation;
    }

    public int getEvaluation() 
    {
        return this.evaluation;
    }

    /**
     * Checks and compares the scores to find the winner of the game
     * @param score1
     * @param score2
     * @return {@code -1} if player 2 has a higher score, {@code 1} if player 1 has a higher score, {@code 0} if they have the same score
     */
    public int findTheWinner(int score1, int score2)
    {
        if (score1 < score2) {return -1;}
        else if (score1 > score2) {return 1;}
        else {return 0;}   
    }
	
    /**
     * Checks if coordinates are within the bounds of the board.
     * @param row the row.
     * @param col the column.
     * @return {@code true} if the coordinates are within the board else {@code false}.
     */
    public Boolean isMoveInBoard(int row, int col) 
    {
        return !(row < 0 || row >= dimension || col < 0 || col >= dimension);

    }

    /**
     * Checks if the {@link Move move} is within the bounds of the board.
     * @param move the {@link Move Move} object.
     * @return {@code true} if the coordinates are within the board else {@code false}.
     * @see {@link Move the move class}
     */
    public Boolean isMoveInBoard(Move move)
    {
        return !(move.getRow() < 0 || move.getRow() >= dimension || move.getCol() < 0 || move.getCol() >= dimension);
    }

    /**
     * This function returns the pawn in the location specified by the parameters.
     * @param row the row value
     * @param col the column value
     * @return the integer representation of the pawn in the board at the given row and column.
     */
    public int getPawn(int row, int col) 
    {
        if (!isMoveInBoard(row, col)) {
            System.out.println("ERROR: WRONG COORDINATES GIVEN");
            throw new UncheckedIOException(new IOException());
        }
        return this.gameBoard[row][col];
    }

    /**
     * This function returns the pawn in the location specified by the {@link Move move} object.
     * @param move the {@link Move Move} object.
     * @return the integer representation of the pawn in the board at the given {@link Move move} object location.
     * @see {@link Move the move class}
     */
    public int getPawn(Move move) 
    {
        if (!isMoveInBoard(move)) {
            System.out.println("ERROR: WRONG COORDINATES GIVEN");
            System.exit(0);
        }
        return this.gameBoard[move.getRow()][move.getCol()];
    }
	

    /**
     * Internal function. Creates a new 2d array of type {@code int} with the same values as the given array
     * @param gameBoard the array to be copied. Is not modified.
     */
	private void setGameBoard(int[][] gameBoard)
    {
        for(int i = 0; i < this.dimension; i++)
        {
            for(int j = 0; j < this.dimension; j++)
            {
                this.gameBoard[i][j] = gameBoard[i][j];
            }
        }
    }

    void setLastMove(Move lastMove)
    {
        this.lastMove.setRow(lastMove.getRow());
        this.lastMove.setCol(lastMove.getCol());
        this.lastMove.setValue(lastMove.getValue());
    }

    void setLastPlayer(int lastPlayer)
    {
        this.lastPlayer = lastPlayer;
    }

    public String toString()
    {

        String result = "\nReversi|";
        for (int i = 0; i < gameBoard.length; i++) {
            result += i + "|";
        }
        result += "\n";
        for (int i = 0; i < gameBoard.length; i++) {
            result += "      " + i + "|";
            for (int j = 0; j < gameBoard.length; j++) {
                switch (this.gameBoard[i][j]) {
                    case EMPTY:
                        result += " ";
                        break;
                    case B:
                        result += BLACK;
                        break;
                    case W:
                        result += WHITE;
                        break;
                    default:
                        result += UNDEFINED;
                        break;
                }
                result += "|";
            }
            result += "\n";
        }
        return result;
    }

    /**
     * Places the player Pawn in the {@code Board} in the location of the move if its empty and within bounds. Captures enemy pawns.
     * @param move the {@link Move move} object.
     * @param playerLetter the int representation of the pawn of the player.
     */
    public void makeMove(Move move, int playerLetter) 
    {
    
        // Check if the move is within the board boundaries
        if (!isMoveInBoard(move)) {
            System.out.println();
            throw new IndexOutOfBoundsException("ERROR: WRONG COORDINATES IN makeMove FUNCTION");
        }
    
        // Check if the cell is empty
        if (getPawn(move) != EMPTY) {
            System.out.println();
            throw new IllegalArgumentException("ERROR: THE CELL IS NOT EMPTY IN makeMove FUNCTION");
        }

        this.lastMove = move;
        this.lastPlayer = playerLetter;
        ArrayList<Move> capturedPawns = this.capturedPawnsFrom(move, playerLetter);
        /* 
        for (Move move2 : capturedPawns) {
            System.out.println(move2.toString());
        }
        */
        if (playerLetter == PLAYER_1) {
            this.player1score += (capturedPawns.size() + 1);
            this.player2score -= (capturedPawns.size());
        } 
        else if (playerLetter == PLAYER_2) {
            this.player1score -= (capturedPawns.size());
            this.player2score += (capturedPawns.size() + 1);
        }
        for (Move capture : capturedPawns) {
            this.gameBoard[capture.getRow()][capture.getCol()] = playerLetter;
        }
        this.gameBoard[move.getRow()][move.getCol()] = playerLetter;

        //increase counter of edge pawns (used in evaluate() function)
        if (playerLetter == 1 && checkIfOnEdge(move)) {
            player1edgePawns++;
        }
        else if (playerLetter == -1 && checkIfOnEdge(move)) {
            player2edgePawns++;
        }
    }


    /**
     * Applied to every state of the board in order to find every move a player can make
     * @param playerLetter
     * @return An array list with all the available moves for a specific player
     */
    public ArrayList<Move> findPossibleMoves(int playerLetter) //returns an array list of all the possible moves for a specific player
    { 
        ArrayList<Move> possibleMoves = new ArrayList<>();
        this.NumberOfMoves1 = 0;
        this.NumberOfMoves2 = 0;
        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                Move move = new Move(row, col);
                if (isLegalMove(move, playerLetter)) { // Check if making a move in this cell is legal before adding it
                    possibleMoves.add(move);
                } 
            }
        }
        if (playerLetter == 1) {
            this.NumberOfMoves1 = possibleMoves.size(); 
        }
        else if (playerLetter == -1) {
            this.NumberOfMoves2 = possibleMoves.size();
        }
        
        return possibleMoves;
    }
    
    private boolean isLegalMove(Move move, int playerLetter) 
    {
        
        if (!isMoveInBoard(move)) {return false;} // Check if the move is in the board boundaries
    
        if (this.getPawn(move) != EMPTY) {return false;} // Check if the cell is empty
    
        else {return !capturedPawnsFrom(move, playerLetter).isEmpty();} // Check if pawns can be captured with this move (if not then it's not legal)
    }

    //helper funtion for capturePawns (for code optimization): changes rows and columns according to the directions we are searching
    public ArrayList<Move> capturedPawnsFrom(Move move, int playerLetter)
    {
        ArrayList<Move> capturedPawns = new ArrayList<>();
        capturedPawns.addAll(capturedPawnsFrom(move, playerLetter, Move.Direction.East));
        capturedPawns.addAll(capturedPawnsFrom(move, playerLetter, Move.Direction.North));
        capturedPawns.addAll(capturedPawnsFrom(move, playerLetter, Move.Direction.NorthEast));
        capturedPawns.addAll(capturedPawnsFrom(move, playerLetter, Move.Direction.NorthWest));
        capturedPawns.addAll(capturedPawnsFrom(move, playerLetter, Move.Direction.South));
        capturedPawns.addAll(capturedPawnsFrom(move, playerLetter, Move.Direction.SouthEast));
        capturedPawns.addAll(capturedPawnsFrom(move, playerLetter, Move.Direction.SouthWest));
        capturedPawns.addAll(capturedPawnsFrom(move, playerLetter, Move.Direction.West));
        return capturedPawns;
    }


    public ArrayList<Move> capturedPawnsFrom(Move move, int playerLetter, Move.Direction dir)
    {
        ArrayList<Move> capturedPawns = new ArrayList<>();
        while (this.isMoveInBoard(move.getDir(dir))) {
            move = move.getDir(dir);
            if (this.getPawn(move) == EMPTY) {
                break;
            }
            if (this.getPawn(move) == playerLetter) {
                return capturedPawns;
            }else{
                capturedPawns.add(move);
            }
        }
        capturedPawns.clear();
        return capturedPawns;
    }

    public boolean checkIfOnEdge(Move move)
    {
        int row = move.getRow();
        int col = move.getCol();

        //check left edge
        if (col == 0) {return true;}

        //check right edge
        else if (col == this.dimension) {return true;}

        //check up edge
        else if (row == 0) {return true;}

        //check down edge
        else if (row == this.dimension) {return true;}

        else {return false;}
    }
}