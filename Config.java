import java.awt.Color;
import java.awt.Font;

public interface Config {

    public static final int STARTING_WIDTH = 1000;
    public static final int STARTING_HEIGHT = 600;

    public static final int MIN_DIM = 6;
    public static final int MAX_DIM = 18;

    public static final int BORDER_WIDTH = 2;

    public static final String TITLE = "Reversi";

    public static final Font TITLE_FONT = new Font("Serif", Font.BOLD, 56);
    public static final Font TEXT_FONT = new Font("Sans-Serif", Font.PLAIN, 22);
    public static final Font NUM_FONT = new Font("Sans-Serif", Font.BOLD, 60);
    public static final Font SETTINGS_FONT = new Font("Sans-Serif", Font.PLAIN, 26);

    public static final Color BG_COLOR = Color.LIGHT_GRAY;
    public static final Color GUI_COLOR = Color.GREEN;
    public static final Color BORDER_COLOR = Color.GREEN;

    public static final String INFO = "\tReversi is a strategy board game for two players, played on an 8×8 uncheckered board. It was invented in 1883. Othello, a variant with a fixed initial setup of the board, was patented in 1971.\n\n\tTwo players compete, using 64 identical game pieces (\"disks\") that are light on one side and dark on the other. Each player chooses one color to use throughout the game. Players take turns placing one disk on an empty square, with their assigned color facing up. After a play is made, any disks of the opponent's color that lie in a straight line bounded by the one just played and another one in the current player's color are turned over. When all playable empty squares are filled, the player with more disks showing in their own color wins the game.";

}
