import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class Reversi extends JPanel implements ActionListener, Config{

    //Game Images

    public static Image colorImg;
    public static Image downImg;
    public static Image infoImg;
    public static Image menuImg;
    public static Image settingsImg;
    public static Image upImg;
    public static Image backImg;

    protected Image PLAYER1PAWN;
    protected Image PLAYER2PAWN;
    protected Image EMPTY_PAWN;
    protected Image POSIBLE_MOVE;

    //Game Data

    protected Board board;
    protected ArrayList<Move> posibleMoves;

    protected int dim = 8;
    protected String currPanel = "menu";
    protected String lastPanel = "menu";
    protected int humanScore = 0;
    protected int AIScore = 0;
    protected int player1 = 0;
    protected int player2 = 1;
    protected int activePlayer = 0;
    protected String player1Name = "Human";
    protected String player2Name = "AI";
    protected int colorSelection = 0;

    //Settings

    protected boolean previewMove = false;

    //Game Screens

    private MainMenu menuPanel;
    private InfoPanel infoPanel;
    private SettingsPanel settingsPanel;
    private ColorPanel colorPanel;
    private GamePanel gamePanel;

    public CardLayout layout;

    public Reversi(){

        try {
            Reversi.colorImg = ImageIO.read(new File("img/color.png"));
            Reversi.downImg = ImageIO.read(new File("img/down.png"));
            Reversi.infoImg = ImageIO.read(new File("img/info.png"));
            Reversi.menuImg = ImageIO.read(new File("img/menu.png"));
            Reversi.settingsImg = ImageIO.read(new File("img/settings.png"));
            Reversi.upImg = ImageIO.read(new File("img/up.png"));
            Reversi.backImg = ImageIO.read(new File("img/back.png"));

            this.PLAYER1PAWN = ImageIO.read(new File("Pawns/grey.png"));
            this.PLAYER2PAWN = ImageIO.read(new File("Pawns/green.png"));
            this.EMPTY_PAWN = ImageIO.read(new File("Pawns/empty.png"));
            this.POSIBLE_MOVE = ImageIO.read(new File("img/add.png"));

        } catch (Exception e) {
            System.out.println("Could not find all image resurces.");
        }

        this.layout = new CardLayout();
        this.setLayout(layout);

        this.menuPanel = new MainMenu(this);
        this.infoPanel = new InfoPanel(this);
        this.colorPanel = new ColorPanel(this);
        this.gamePanel = new GamePanel(this);
        this.settingsPanel = new SettingsPanel(this);

        this.add("menu", menuPanel);
        this.add("info", infoPanel);
        this.add("color", colorPanel);
        this.add("game", gamePanel);
        this.add("settings", settingsPanel);
        layout.show(this, "menu");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.out.println("Could not load System UI");
            }

            Reversi mainPanel = new Reversi();
            JFrame frame = new JFrame(Reversi.TITLE);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.getContentPane().add(mainPanel);
            frame.setSize(STARTING_WIDTH, STARTING_HEIGHT);
            frame.setLocationByPlatform(true);
            frame.setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "goToInfo":
                this.lastPanel = this.currPanel;
                this.currPanel = "info";
                layout.show(this, "info");
                break;
            case "goToMenu":
                this.lastPanel = this.currPanel;
                this.currPanel = "menu";
                layout.show(this, "menu");
                break;
            case "goToSettings":
                this.lastPanel = this.currPanel;
                this.currPanel = "settings";
                layout.show(this, "settings");
                break;
            case "incDim": 
                if (this.dim >= Reversi.MAX_DIM) {
                    break;
                }
                this.dim += 1;
                this.menuPanel.updateLabels();
                break;
            case "decDim": 
                if (this.dim <= Reversi.MIN_DIM) {
                    break;
                }
                this.dim -= 1;
                this.menuPanel.updateLabels();
                break;
            case "toggle1": 
                if (this.player1 == 0) {
                    this.player1 = 1;
                    this.player1Name = "AI";
                }else{
                    this.player1 = 0;
                    this.player1Name = "Human";
                }
                this.menuPanel.updateLabels();
                break;
            case "toggle2": 
                if (this.player2 == 0) {
                    this.player2 = 1;
                    this.player2Name = "AI";
                }else{
                    this.player2 = 0;
                    this.player2Name = "Human";
                }
                this.menuPanel.updateLabels();
                break;
            case "color1":
                this.colorSelection = 1;
                this.colorPanel.updateLabel();
                this.lastPanel = this.currPanel;
                this.currPanel = "color";
                layout.show(this, "color");
                break;
            case "color2":
                this.colorSelection = 2;
                this.colorPanel.updateLabel();
                this.lastPanel = this.currPanel;
                this.currPanel = "color";
                layout.show(this, "color");
                break;
            case "start":
                this.lastPanel = this.currPanel;
                this.currPanel = "game";
                this.gamePanel.setupNewGame();
                layout.show(this, "game");
                break;
            case "goBack":
                this.currPanel = this.lastPanel;
                layout.show(this, this.lastPanel);
                break;
            default:
                break;
        }
    }
}