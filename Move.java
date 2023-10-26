/**
 * This class represents a move in a board game
 * where a move consists of the placement of a piece
 * (represented by an Integer value) in a grid (board)
 */
public class Move
{
    private int row;
    private int col;
    private int value;

    Move()
    {
        this.row = -1;
        this.col = -1;
        this.value = 0;
    }

    Move(int row, int col)
    {
        this.row = row;
        this.col = col;
        this.value = -1;
    }

    Move(int value)
    {
        this.row = -1;
        this.col = -1;
        this.value = value;
    }

    /**
     * Creates a move object with row, column and value attributes.
     * @param row Int value of the column
     * @param col Int value of the row
     * @param value Int value to be given to the board square determined by the row and column atributes
     */

    Move(int row, int col, int value)
    {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    int getRow()
    {
        return this.row;
    }

    int getCol()
    {
        return this.col;
    }

    int getValue()
    {
        return this.value;
    }

    void setRow(int row)
    {
        this.row = row;
    }

    void setCol(int col)
    {
        this.col = col;
    }

    void setValue(int value)
    {
        this.value = value;
    }
}
