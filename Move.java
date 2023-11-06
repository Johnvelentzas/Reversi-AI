/**
 * This class represents a <strong>move</strong> in a board game
 * where a move consists of the placement of a piece
 * (represented by an Integer value) in a grid (board)
 */
public class Move
{
    private int row;
    private int col;
    private int value;

    enum Direction{
        West,
        NorthWest,
        North,
        NorthEast,
        East,
        SouthEast,
        South,
        SouthWest
    }

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
        this.value = 0;
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

    public Move getDir(Direction dir){
        switch (dir) {
            case East:
                return this.east();
            case North:
                return this.north();
            case NorthEast:
                return this.northEast();
            case NorthWest:
                return this.northWest();
            case South:
                return this.south();
            case SouthEast:
                return this.southEast();
            case SouthWest:
                return this.southEast();
            case West:
                return this.west();
            default:
                return this;
        }
    }

    public Move west(){
        return new Move(this.row, this.col - 1);
    }

    public Move northWest(){
        return new Move(this.row + 1, this.col - 1);
    }

    public Move north(){
        return new Move(this.row + 1, this.col);
    }

    public Move northEast(){
        return new Move(this.row + 1, this.col + 1);
    }

    public Move east(){
        return new Move(this.row, this.col + 1);
    }
    public Move southEast(){
        return new Move(this.row - 1, this.col + 1);
    }
    public Move south(){
        return new Move(this.row - 1, this.col);
    }
    public Move southWest(){
        return new Move(this.row - 1, this.col - 1);
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
