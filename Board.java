import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;


class Board
{

	public static final int W = 1;
    public static final int B = -1;
    public static final int EMPTY = 0;

    public static final String WHITE = "W";
    public static final String BLACK = "B";
    public static final String UNDEFINED = "?";

    public static final int PLAYER_1 = 1;
    public static final int PLAYER_2 = -1;

    private int player1score = 0;
    private int player2score = 0;

    public static final int DEFAULT_DIMENTION = 8;
    public static final int DEFAULT_MAX_DEPTH = 8;

    private int[][] gameBoard;

    private int lastPlayer;

    private Move lastMove;
	
	private int dimension;


    /**
     * Default board constructor. Creates an {@code empty} board with the default dimentions
     */
    public Board() {
        create_board(DEFAULT_DIMENTION);
    }
	
    /**
     * Basic board constructor. Creates an {@code empty} board with the given dimentions
     * @param dim   x and y dimention.
     */
	public Board(int dimentions) {
        create_board(dimentions);
    }

    /**
     * Internal constructor.
     * @param dimentions x and y dimentions
     */
    private void create_board(int dimentions) {
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
    public Board(Board board) {
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
	public void print() {
        System.out.println(this.toString());
    }


	
	ArrayList<Board> getChildren(int letter) {return null;}
	// ΤΟΛΗ ΑΥΤΗ ΚΑΝΕ ΟΣΟ ΜΠΟΡΕΙΣ :)
/**
 ΘΑ ΠΡΕΠΕΙ ΝΑ ΕΠΙΣΤΡΕΦΕΙ ΜΙΑ ΛΙΣΤΑ ΑΠΟ ΠΙΝΑΚΕΣ ΠΟΥ ΘΑ ΕΙΝΑΙ ΤΑ ΠΑΙΔΙΑ ΤΟΥ ΚΟΜΒΟΥ ΜΑΣ (ΤΟΥ ΠΙΝΑΚΑ ΣΤΟΝ ΟΠΟΙΟ ΤΗΝ ΕΦΑΡΜΟΖΟΥΜΕ).
 ΤΑ ΠΑΙΔΙΑ ΑΥΤΑ ΘΑ ΕΙΝΑΙ ΚΑΤΑΣΤΑΣΕΙΣ ΠΙΝΑΚΩΝ ΜΕΤΑ ΑΠΟ !!!ΚΑΘΕ!!! ΔΥΝΑΤΗ ΚΙΝΗΣΗ ΠΟΥ ΜΠΟΡΕΙ ΝΑ ΚΑΝΕΙ Ο ΣΥΓΚΕΚΡΙΜΕΝΟΣ ΠΑΙΧΤΗΣ
 (ΤΟΝ ΠΑΙΡΝΟΥΜΕ ΑΠΟ ΤΟ ΟΡΙΣΜΑ ΠΟΥ ΕΧΕΙ Η ΣΥΝΑΡΤΗΣΗ ΚΑΙ ΤΗΝ ΕΦΑΡΜΟΖΟΥΜΕ ΠΑΝΩ ΣΕ ΠΙΝΑΚΑ). ΑΡΑ ΘΑ ΠΡΕΠΕΙ ΝΑ ΕΧΟΥΝ ΑΛΛΑΞΕΙ ΚΑΙ ΤΑ 
 ΠΙΟΝΙΑ ΤΟΥ ΑΝΤΙΠΑΛΟΥ ΠΟΥ ΘΑ ΚΛΕΒΕΙ Ο ΠΑΙΧΤΗΣ ΜΕ ΤΗΝ ΚΑΘΕ ΚΙΝΗΣΗ. ΜΠΟΡΕΙΣ ΒΟΗΘΗΤΙΚΑ ΝΑ ΧΡΗΣΙΜΟΠΟΙΗΣΕΙΣ ΤΗΝ @findPossibleMoves 
 ΠΟΥ ΕΧΕΙ ΥΛΟΠΟΙΗΘΕΙ ΠΙΟ ΚΑΤΩ
 */
    
 //ΕΥΡΕΤΙΚΗ ΣΥΝΑΡΤΗΣΗ --> ΧΡΗΣΙΜΟΠΟΙΕΙΤΑΙ ΑΝΤΙ ΓΙΑ ΤΗΝ UTILITY ΟΤΑΝ Ο ΧΡΗΣΤΗΣ ΔΙΝΕΙ ΜΙΚΡΟΤΕΡΟ ΒΑΘΟΣ ΑΠΟ ΤΟ ΟΛΙΚΟ ΠΟΥ ΧΡΕΙΑΖΕΤΑΙ
 //ΘΑ ΧΡΕΙΑΣΤΕΙ ΝΑ ΠΑΡΕΙ ΤΙΜΕΣ ΜΕ GET ΣΥΝΑΡΤΗΣΗ ΑΠΟ ΤΗΝ MAIN Ή ΤΗ ΔΙΕΠΑΦΗ ΓΙΑ ΝΑ ΕΦΑΡΜΟΣΤΕΙ
	public int evaluate() {
         return 0;   
    }
	
	public boolean isTerminal() {
        if (this.findPossibleMoves(PLAYER_1).isEmpty() && this.findPossibleMoves(PLAYER_2).isEmpty()) {
            return true;
        }
        return false;
    }
	
	public Move getLastMove()
    {
        return this.lastMove;
    }

    public int getLastPlayer()
    {
        return this.lastPlayer;
    }

    public int getPlayer1Score(){
        return this.player1score;
    }

    public int getPlayer2Score(){
        return this.player2score;
    }

    public int[][] getGameBoard()
    {
        return this.gameBoard;
    }

    
    public int getDimention() {
        return this.dimension;
    }
	
    /**
     * Checks if coordinates are within the bounds of the board.
     * @param row the row.
     * @param col the column.
     * @return {@code true} if the coordinates are within the board else {@code false}.
     */
    public Boolean isMoveInBoard(int row, int col) {
        if (row < 0 || row >= dimension || col < 0 || col >= dimension) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the {@link Move move} is within the bounds of the board.
     * @param move the {@link Move Move} object.
     * @return {@code true} if the coordinates are within the board else {@code false}.
     * @see {@link Move the move class}
     */
    public Boolean isMoveInBoard(Move move){
        if (move.getRow() < 0 || move.getRow() >= dimension || move.getCol() < 0 || move.getCol() >= dimension) {
            return false;
        }
        return true;
    }

    /**
     * This function returns the pawn in the location specified by the parameters.
     * @param row the row value
     * @param col the column value
     * @return the integer representation of the pawn in the board at the given row and column.
     */
    public int getPawn(int row, int col) {
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
    public int getPawn(Move move) {
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

    public String toString(){
        String result = "Reversi|";
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
    public void makeMove(Move move, int playerLetter) {
    
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
        if (playerLetter == PLAYER_1) {
            this.player1score += capturedPawns.size() + 1;
            this.player2score -= capturedPawns.size() - 1;
        } else {

        }
        for (Move capture : capturedPawns) {
            this.gameBoard[capture.getRow()][capture.getCol()] = playerLetter;
        }
        this.gameBoard[move.getRow()][move.getCol()] = playerLetter;
    }


    
    public ArrayList<Move> findPossibleMoves(int playerLetter) { //returns an array list of all the possible moves for a specific player
        ArrayList<Move> possibleMoves = new ArrayList<>();

        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                Move move = new Move(row, col);
                if (isLegalMove(move, playerLetter)) { // Check if making a move in this cell is legal before adding it
                    possibleMoves.add(move);
                } 
            }
        }
        return possibleMoves;
    }
    
    private boolean isLegalMove(Move move, int playerLetter) {
        
        if (!isMoveInBoard(move)) {return false;} // Check if the move is in the board boundaries
    
        if (this.getPawn(move) != EMPTY) {return false;} // Check if the cell is empty
    
        else {return !capturedPawnsFrom(move, playerLetter).isEmpty();} // Check if pawns can be captured with this move (if not then it's not legal)
    }

    //helper funtion for capturePawns (for code optimization): changes rows and columns according to the directions we are searching
    public ArrayList<Move> capturedPawnsFrom(Move move, int playerLetter){
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


    public ArrayList<Move> capturedPawnsFrom(Move move, int playerLetter, Move.Direction dir){
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
}