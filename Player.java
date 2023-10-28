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

    private int utility_Funtion(Board board) {
        int playerLetter = board.getLastPlayer();
        int opponent;
        if (playerLetter == -1) {opponent = 1;}
        else {opponent = -1;}

        int playerPieces = 0;
        int opponentPieces = 0;
    
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
	
	public Move MiniMax(Board board) {return null;}
	
	public Move max(Board board, int depth) {return null;}
	
	public Move min(Board board, int depth) {return null;}
}