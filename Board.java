import java.util.ArrayList;

class Board
{

    public static void main(String[] args) {
        Board board = new Board(7);
        board.print();
    }

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

    public static final int DEFAULT_DIMENTION = 7;

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
	
	// copy constructor
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
	
	ArrayList<Board> getChildren(int latter) {return null;}
	
	public int evaluate() {
         return 0;   
    }
	
	public boolean isTerminal() {
        Boolean isFull = true;
        outer:
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                if (this.gameBoard[i][j] == EMPTY) {
                    isFull = false;
                    break outer;
                }
            }
        }
        if (isFull) {
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

    public int[][] getGameBoard()
    {
        return this.gameBoard;
    }

    
    public int getDimention() {
        return this.dimension;
    }
	

    public int getPawn(int row, int col) {
        if (row < 0 || row > this.dimension || col < 0 || col > this.dimension) {
            System.out.println("ERROR: WRONG COORDINATES GIVEN");
            System.exit(0);}
        return this.gameBoard[row][col];
    }
	
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
	

}