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
	
	ArrayList<Board> getChildren(int letter) {return null;}
	
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

        //TODO add more terminal conditions

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
	
    public Boolean IsMoveInBoard(int row, int col) {
        if (row < 0 || row >= dimension || col < 0 || col >= dimension) {return false;}
        else {return true;}
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

    public void makeMove(Move move, int playerLetter) {
        int row = move.getRow();
        int col = move.getCol();
    
        // Check if the move is within the board boundaries
        if (!IsMoveInBoard(row, col)) {
            System.out.println();
            throw new IndexOutOfBoundsException("ERROR: WRONG COORDINATES IN makeMove FUNCTION");
        }
    
        // Check if the cell is empty
        if (gameBoard[row][col] != EMPTY) {
            System.out.println();
            throw new IllegalArgumentException("ERROR: THE CELL IS NOT EMPTY IN makeMove FUNCTION");
        }

        gameBoard[row][col] = playerLetter;
    
        // TODO update the opponent's captured pieces
        
    
        // TODO update the score


        // TODO set the last move
    }


    
    public ArrayList<Move> findPossibleMoves(int playerLetter) { //returns an array list of all the possible moves for a specific player
        ArrayList<Move> possibleMoves = new ArrayList<>();

        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                if (isLegalMove(row, col, playerLetter)) { // Check if making a move in this cell is legal before adding it
                    possibleMoves.add(new Move(row, col));
                } 
            }
        }
        return possibleMoves;
    }



    
    private boolean isLegalMove(int row, int col, int playerLetter) {
        
        if (!IsMoveInBoard(row, col)) {return false;} // Check if the move is in the board boundaries
    
        if (gameBoard[row][col] != 0) {return false;} // Check if the cell is empty
    
        else {return capturePawns(row, col, playerLetter);} // Check if pawns can be captured with this move (if not then it's not legal)
    }

//helper funtion for isLegalMove: checks every direction for opponent pawns that can be captured - WILL ALSE BE USED WHEN A PLAYER'S TURN IS SKIPPED
    private boolean capturePawns(int row, int col, int playerLetter) {
        int opponent = 0;
        //int TotalOpponentPiecesCaptured = 0;
        int player = playerLetter;
        //int TotalPlayerPiecesWon = 0;

        if (player == 1) {opponent = -1;}
        else if (player == -1) {opponent = 1;}

        // Search for Pawns that can be captured DOWN
        if (searchDirection(row, col, player, opponent, 1, 0)) {
            return true;
        }

        // Search for Pawns that can be captured UP
        if (searchDirection(row, col, player, opponent, -1, 0)) {
            return true;
        }

        // Search for Pawns that can be captured LEFT
        if (searchDirection(row, col, player, opponent, 0, -1)) {
            return true;
        }

        // Search for Pawns that can be captured RIGHT
        if (searchDirection(row, col, player, opponent, 0, 1)) {
            return true;
        }

        // Search for Pawns that can be captured DIAGONAL UP RIGHT
        if (searchDirection(row, col, player, opponent, -1, 1)) {
            return true;
        }

        // Search for Pawns that can be captured DIAGONAL UP LEFT
        if (searchDirection(row, col, player, opponent, -1, -1)) {
            return true;
        }

        // Search for Pawns that can be captured DIAGONAL DOWN RIGHT
        if (searchDirection(row, col, player, opponent, 1, 1)) {
            return true;
        }

        // Search for Pawns that can be captured DIAGONAL DOWN LEFT
        if (searchDirection(row, col, player, opponent, 1, -1)) {
            return true;
        }

        return false;
    }

    //helper funtion for capturePawns (for code optimization): changes rows and columns according to the directions we are searching
    private boolean searchDirection(int row, int col, int player, int opponent, int r, int c) {
        boolean OpponentPiecesFound = false;
    
        row += r;
        col += c;
    
        while (row >= 0 && row < dimension && col >= 0 && col < dimension) {
            int cell = getPawn(row, col);
    
            if (cell == opponent) {OpponentPiecesFound = true;} 
            else if (cell == player && OpponentPiecesFound) {return true;} 
            else if (cell == 0) {break;}
    
            row += r;
            col += c;
        }
    
        return false;
    }	

}




/* 
        //search for Pawns that can be captured DOWN 
        for (int r = row + 1; r <= dimension - 1; r++ ) {
            if (getPawn(r, col) == opponent) {
                OpponentPiecesFound = true;
                continue;
            }
            else if (getPawn(r, col) == player && OpponentPiecesFound) {
                return true;
            }
            else if (getPawn(r, col) == 0) {break;}
        }

        //search for Pawns that can be captured UP
        for (int r = row - 1; r <= dimension - 1; r-- ) {
            if (getPawn(r, col) == opponent) {
                OpponentPiecesFound = true;
                continue;
            }
            else if (getPawn(r, col) == player && OpponentPiecesFound) {
                return true;
            }
            else if (getPawn(r, col) == 0) {break;}
        }

        //search for Pawns that can be captured LEFT
        for (int c = col - 1; c <= dimension - 1; c-- ) {
            if (getPawn(row, c) == opponent) {
                OpponentPiecesFound = true;
                continue;
            }
            else if (getPawn(row, c) == player && OpponentPiecesFound) {
                return true;
            }
            else if (getPawn(row, c) == 0) {break;}
        }

        //search for Pawns that can be captured RIGHT
        for (int c = col + 1; c <= dimension - 1; c++ ) {
            if (getPawn(row, c) == opponent) {
                OpponentPiecesFound = true;
                continue;
            }
            else if (getPawn(row, c) == player && OpponentPiecesFound) {
                return true;
            }
            else if (getPawn(row, c) == 0) {break;}
        }

        //search for Pawns that can be captured DIAGONAL UP RIGHT
        int r = row - 1;
        int c = col + 1;
        while (r >= 0 && c < dimension) {
            if (getPawn(r, c) == opponent) {
                OpponentPiecesFound = true;
                continue;
            } 
            else if (getPawn(r, c) == player && OpponentPiecesFound) {return true;} 
            else if (getPawn(r, c) == 0) {break;}
            r--;
            c++;
        }

        //search for Pawns that can be captured DIAGONAL UP LEFT
        r = row - 1;
        c = col - 1;
        while (r >= 0 && c >=0) {
            if (getPawn(r, c) == opponent) {
                OpponentPiecesFound = true;
                continue;
            } 
            else if (getPawn(r, c) == player && OpponentPiecesFound) {return true;} 
            else if (getPawn(r, c) == 0) {break;}
            r--;
            c--;
        }

        //search for Pawns that can be captured DIAGONAL DOWN RIGHT
        r = row + 1;
        c = col + 1;
        while (r < dimension && c < dimension) {
            if (getPawn(r, c) == opponent) {
                OpponentPiecesFound = true;
                continue;
            } 
            else if (getPawn(r, c) == player && OpponentPiecesFound) {return true;} 
            else if (getPawn(r, c) == 0) {break;}
            r++;
            c++;
        }

        //search for Pawns that can be captured DIAGONAL DOWN LEFT
        r = row + 1;
        c = col - 1;
        while (r < dimension && c >=0) {
            if (getPawn(r, c) == opponent) {
                OpponentPiecesFound = true;
                continue;
            } 
            else if (getPawn(r, c) == player && OpponentPiecesFound) {return true;} 
            else if (getPawn(r, c) == 0) {break;}
            r++;
            c--;
        }
*/