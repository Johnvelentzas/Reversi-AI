import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Reversi extends JPanel implements ActionListener, Config{

    //Game Images

    public static Image colorImg;
    public static Image downImg;
    public static Image infoImg;
    public static Image menuImg;
    public static Image settingsImg;
    public static Image upImg;
    public static Image backImg;

    public Image PLAYER1PAWN;
    public Image PLAYER2PAWN;
    public Image EMPTY_PAWN;

    //Game Data

    private Board board;

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
    private settingsPanel settingsPanel;

    private JButton goToMenu;
    private JLabel humanScoreLabel2;
    private JLabel AIScoreLabel2;
    private JButton goToInfo2;

    private JButton goBackButton2;
    private JButton goBackButton3;

    private JPanel colorPanel;
    private JButton[][] colourGrid;
    private JLabel explaner;

    private JPanel gamePanel;
    private JPanel gameInnerPanel;
    private JButton[][] gameGrid;
    private JButton place;

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

            this.PLAYER1PAWN = ImageIO.read(new File("img/settings.png"));
            this.PLAYER2PAWN = ImageIO.read(new File("img/up.png"));
            this.EMPTY_PAWN = ImageIO.read(new File("img/back.png"));

        } catch (Exception e) {
            System.out.println("Could not find all image resurces.");
        }

        this.layout = new CardLayout();
        this.setLayout(layout);

        createPanels();

        this.add("menu", menuPanel);
        this.add("info", infoPanel);
        this.add("color", colorPanel);
        this.add("game", gamePanel);
        this.add("settings", settingsPanel);
        layout.show(this, "menu");
    }

    private void createPanels(){
        this.menuPanel = new MainMenu(this);
        this.infoPanel = new InfoPanel(this);
        this.colorPanel = new JPanel(new GridBagLayout());
        this.gamePanel = new JPanel(new GridBagLayout());
        this.settingsPanel = new settingsPanel(this);

        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTH;

        this.goToMenu = new JButton(new ImageIcon(Reversi.menuImg));
        this.goToMenu.addActionListener(this);
        this.goToMenu.setActionCommand("goToMenu");
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.3;
        c.fill = GridBagConstraints.BOTH;
        this.gamePanel.add(this.goToMenu, c);
    }

    private void setupGamePanel(){
        this.board = new Board(this.dim);
        this.gameGrid = new JButton[this.dim][this.dim];

        GridBagConstraints c = new GridBagConstraints();
        this.gameInnerPanel.removeAll();
        this.gamePanel.remove(this.gameInnerPanel);
        this.gameInnerPanel = new JPanel(new GridLayout(this.dim, this.dim));
        this.gameInnerPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 7;
        c.weighty = 1;
        this.gamePanel.add(this.gameInnerPanel, c);

        for (int i = 0; i < this.dim; i++) {
            for (int j = 0; j < this.dim; j++) {
                this.gameGrid[i][j] = new JButton();
                this.gameInnerPanel.add(this.gameGrid[i][j]);
            }
        }
        this.updatePawns();
    }

    private void updatePawns(){
        for (int i = 0; i < this.dim; i++) {
            for (int j = 0; j < this.dim; j++) {
                switch (this.board.getPawn(i, j)) {
                    case -1:
                        this.gameGrid[i][j].setIcon(new ImageIcon(this.PLAYER1PAWN));
                        break;
                    case 0:
                        this.gameGrid[i][j].setIcon(new ImageIcon(this.PLAYER2PAWN));
                        break;
                    case 1:
                        this.gameGrid[i][j].setIcon(new ImageIcon(this.EMPTY_PAWN));
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void updateLabels(){
        this.menuPanel.updateLabels();
    }
    
    private static void createAndShowGui() {
        Reversi mainPanel = new Reversi();

        JFrame frame = new JFrame(Reversi.TITLE);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.setSize(STARTING_WIDTH, STARTING_HEIGHT);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGui();
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
                updateLabels();
                break;
            case "decDim": 
                if (this.dim <= Reversi.MIN_DIM) {
                    break;
                }
                this.dim -= 1;
                updateLabels();
                break;
            case "toggle1": 
                if (this.player1 == 0) {
                    this.player1 = 1;
                    this.player1Name = "AI";
                }else{
                    this.player1 = 0;
                    this.player1Name = "Human";
                }
                updateLabels();
                break;
            case "toggle2": 
                if (this.player2 == 0) {
                    this.player2 = 1;
                    this.player2Name = "AI";
                }else{
                    this.player2 = 0;
                    this.player2Name = "Human";
                }
                updateLabels();
                break;
            case "color1":
                this.colorSelection = 1;
                this.lastPanel = this.currPanel;
                this.currPanel = "color";
                layout.show(this, "color");
                break;
            case "color2":
                this.colorSelection = 2;
                this.lastPanel = this.currPanel;
                this.currPanel = "color";
                layout.show(this, "color");
                break;
            case "start":
                this.lastPanel = this.currPanel;
                this.currPanel = "game";
                setupGamePanel();
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