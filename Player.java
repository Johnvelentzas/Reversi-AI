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
	
	public Move MiniMax(Board board) {return null;}
	
	public Move max(Board board, int depth) {return null;}
	
	public Move min(Board board, int depth) {return null;}
}