import java.util.ArrayList;

class Board
{
	public static final int W = 1;
    public static final int B = -1;
    public static final int EMPTY = 0;

    private int[][] gameBoard;

    private int lastPlayer;

    private Move lastMove;
	
	private int dimension;
	
    /**
     * Basic board constructor. Creates an {@code empty} board with the given dimentions
     * @param dim   x and y dimention.
     */
	public Board(int dim) {
        this.dimension = dim;
        this.gameBoard = new int[dim][dim];
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
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
	
	public void print() {}
	
	ArrayList<Board> getChildren(int letter) {return null;}
	
	public int evaluate () {return 0;}
	
	public boolean isTerminal() {return true;}
	
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
	
	void setGameBoard(int[][] gameBoard)
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
	
	
}