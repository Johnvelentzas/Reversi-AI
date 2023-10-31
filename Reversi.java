import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Reversi extends JPanel implements ActionListener{

    public static final int STARTING_WIDTH = 600;
    public static final int STARTING_HEIGHT = 400;

    public static final int MIN_DIM = 7;
    public static final int MAX_DIM = 17;

    public static final String TITLE = "Reversi";
    public static final Font TITLE_FONT = new Font("Serif", Font.BOLD, 56);
    public static final Font TEXT_FONT = new Font("Sans-Serif", Font.PLAIN, 22);
    public static final Font NUM_FONT = new Font("Sans-Serif", Font.BOLD, 60);

    public static final Color BG_COLOR = Color.LIGHT_GRAY;
    public static Color PLAYER1_COLOR = Color.WHITE;
    public static Color PLAYER2_COLOR = Color.BLACK;
    public static final Color GUI_COLOR = Color.GREEN;
    public static final Color BORDER_COLOR = Color.GREEN;

    public static Image colorImg;
    public static Image downImg;
    public static Image infoImg;
    public static Image menuImg;
    public static Image settingsImg;
    public static Image upImg;
    public static Image backImg;

    private Board board;

    private int dim = 7;
    private String currPanel = "menu";
    private String lastPanel = "menu";
    private int humanScore = 0;
    private int AIScore = 0;
    private int player1 = 0;
    private int player2 = 1;
    private String player1Name = "Human";
    private String player2Name = "AI";
    private int colorSelection = 0;

    private JPanel menuPanel;
    private JLabel title;
    private JLabel dimDisplay;
    private JButton inc;
    private JButton dec;
    private JLabel player1Label;
    private JLabel player2Label;
    private JButton player1Button;
    private JButton player2Button;
    private JButton player1ColorButton;
    private JButton player2ColorButton;
    private JButton starButton;

    private JButton goToMenu;
    private JLabel humanScoreLabel1;
    private JLabel humanScoreLabel2;
    private JLabel AIScoreLabel1;
    private JLabel AIScoreLabel2;
    private JButton goToSettings;
    private JButton goToInfo1;
    private JButton goToInfo2;
    private JButton goBackButton1;
    private JButton goBackButton2;
    private JButton goBackButton3;

    private JPanel infoPanel;
    private JLabel info;

    private JPanel colorPanel;
    private JButton[][] colourGrid;
    private JLabel explaner;

    private JPanel gamePanel;
    private JButton[][] gameGrid;
    private JButton place;

    private JPanel settingsPanel;
    private JLabel settingsLabel;

    public CardLayout layout = new CardLayout();

    public Reversi(){

        try {
            Reversi.colorImg = ImageIO.read(new File("img/color.png"));
            Reversi.downImg = ImageIO.read(new File("img/down.png"));
            Reversi.infoImg = ImageIO.read(new File("img/info.png"));
            Reversi.menuImg = ImageIO.read(new File("img/menu.png"));
            Reversi.settingsImg = ImageIO.read(new File("img/settings.png"));
            Reversi.upImg = ImageIO.read(new File("img/up.png"));
            Reversi.backImg = ImageIO.read(new File("img/back.png"));
        } catch (Exception e) {
            System.out.println("Could not find all image resurces.");
        }

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
        this.menuPanel = new JPanel(new GridBagLayout());
        this.menuPanel.setBackground(Reversi.BG_COLOR);
        this.infoPanel = new JPanel(new GridBagLayout());
        this.infoPanel.setBackground(Reversi.BG_COLOR);
        this.colorPanel = new JPanel(new GridBagLayout());
        this.colorPanel.setBackground(Reversi.BG_COLOR);
        this.gamePanel = new JPanel(new GridBagLayout());
        this.gamePanel.setBackground(Reversi.BG_COLOR);
        this.settingsPanel = new JPanel(new GridBagLayout());
        this.settingsPanel.setBackground(Reversi.BG_COLOR);

        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTH;

        this.goToInfo1 = new JButton(new ImageIcon(Reversi.infoImg));
        this.goToInfo1.addActionListener(this);
        this.goToInfo1.setActionCommand("goToInfo");
        this.goToInfo2 = new JButton(new ImageIcon(Reversi.infoImg));
        this.goToInfo2.addActionListener(this);
        this.goToInfo2.setActionCommand("goToInfo");
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 6;
        c.gridy = 0;
        c.weightx = 0.3;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.WEST;
        this.menuPanel.add(this.goToInfo1, c);
        this.gamePanel.add(this.goToInfo2, c);

        this.goBackButton1 = new JButton(new ImageIcon(Reversi.backImg));
        this.goBackButton1.addActionListener(this);
        this.goBackButton1.setActionCommand("goBack");
        this.goBackButton2 = new JButton(new ImageIcon(Reversi.backImg));
        this.goBackButton2.addActionListener(this);
        this.goBackButton2.setActionCommand("goBack");
        this.goBackButton3 = new JButton(new ImageIcon(Reversi.backImg));
        this.goBackButton3.addActionListener(this);
        this.goBackButton3.setActionCommand("goBack");
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0;
        c.weighty = 0;
        this.infoPanel.add(this.goBackButton1, c);
        this.colorPanel.add(this.goBackButton2, c);
        this.settingsPanel.add(this.goBackButton3, c);

        this.humanScoreLabel1 = new JLabel("Human: " + this.humanScore);
        this.humanScoreLabel1.setFont(Reversi.TEXT_FONT);
        this.humanScoreLabel2 = new JLabel("Human: " + this.humanScore);
        this.humanScoreLabel2.setFont(Reversi.TEXT_FONT);
        c.insets = new Insets(0, 10, 0, 0);
        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.menuPanel.add(this.humanScoreLabel1, c);
        this.gamePanel.add(this.humanScoreLabel2, c);

        this.AIScoreLabel1 = new JLabel(this.AIScore + " :AI", JLabel.RIGHT);
        this.AIScoreLabel1.setFont(Reversi.TEXT_FONT);
        this.AIScoreLabel2 = new JLabel(this.AIScore + " :AI", JLabel.RIGHT);
        this.AIScoreLabel2.setFont(Reversi.TEXT_FONT);
        c.insets = new Insets(0, 0, 0, 10);
        c.gridx = 4;
        c.gridy = 0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.menuPanel.add(this.AIScoreLabel1, c);
        this.gamePanel.add(this.AIScoreLabel2, c);

        this.goToMenu = new JButton(new ImageIcon(Reversi.menuImg));
        this.goToMenu.addActionListener(this);
        this.goToMenu.setActionCommand("goToMenu");
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.3;
        c.fill = GridBagConstraints.BOTH;
        this.gamePanel.add(this.goToMenu, c);


        this.goToSettings = new JButton(new ImageIcon(Reversi.settingsImg));
        this.goToSettings.addActionListener(this);
        this.goToSettings.setActionCommand("goToSettings");
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.3;
        c.fill = GridBagConstraints.BOTH;
        this.menuPanel.add(this.goToSettings, c);

        this.title = new JLabel(Reversi.TITLE);
        this.title.setFont(Reversi.TITLE_FONT);
        c.insets = new Insets(10, 0, 10, 0);
        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 3;
        c.gridheight = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.menuPanel.add(this.title, c);

        this.dimDisplay = new JLabel("" + this.dim);
        this.dimDisplay.setFont(Reversi.NUM_FONT);
        c.insets = new Insets(0, 10, 0, 10);
        c.gridx = 2;
        c.gridy = 2;
        c.weightx = 0.3;
        c.weighty = 0.3;
        c.gridwidth = 1;
        c.gridheight = 2;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.NONE;
        this.menuPanel.add(this.dimDisplay, c);

        this.inc = new JButton(new ImageIcon(Reversi.upImg));
        this.inc.addActionListener(this);
        this.inc.setActionCommand("incDim");
        c.insets = new Insets(0, 10, 0, 10);
        c.gridx = 4;
        c.gridy = 2;
        c.weightx = 0.3;
        c.weighty = 0.3;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        this.menuPanel.add(this.inc, c);

        this.dec = new JButton(new ImageIcon(Reversi.downImg));
        this.dec.addActionListener(this);
        this.dec.setActionCommand("decDim");
        c.insets = new Insets(0, 10, 0, 10);
        c.gridx = 4;
        c.gridy = 3;
        c.weightx = 0.3;
        c.weighty = 0.3;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        this.menuPanel.add(this.dec, c);

        this.player1Label = new JLabel("Player 1");
        this.player1Label.setFont(Reversi.TEXT_FONT);
        c.insets = new Insets(0, 10, 0, 10);
        c.gridx = 2;
        c.gridy = 4;
        c.weightx = 1.0;
        c.weighty = 0.3;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.SOUTH;
        c.fill = GridBagConstraints.NONE;
        this.menuPanel.add(this.player1Label, c);

        this.player2Label = new JLabel("Player 2");
        this.player2Label.setFont(Reversi.TEXT_FONT);
        c.insets = new Insets(0, 10, 0, 10);
        c.gridx = 4;
        c.gridy = 4;
        c.weightx = 1.0;
        c.weighty = 0.3;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.SOUTH;
        c.fill = GridBagConstraints.NONE;
        this.menuPanel.add(this.player2Label, c);

        this.player1Button = new JButton(this.player1Name);
        this.player1Button.setFont(Reversi.TEXT_FONT);
        this.player1Button.addActionListener(this);
        this.player1Button.setActionCommand("toggle1");
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 2;
        c.gridy = 5;
        c.weightx = 1.0;
        c.weighty = 0.3;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        this.menuPanel.add(this.player1Button, c);

        this.player2Button = new JButton(this.player2Name);
        this.player2Button.setFont(Reversi.TEXT_FONT);
        this.player2Button.addActionListener(this);
        this.player2Button.setActionCommand("toggle2");
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 4;
        c.gridy = 5;
        c.weightx = 1.0;
        c.weighty = 0.3;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        this.menuPanel.add(this.player2Button, c);

        this.player1ColorButton = new JButton(new ImageIcon(Reversi.colorImg));
        this.player1ColorButton.addActionListener(this);
        this.player1ColorButton.setActionCommand("color1");
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 1;
        c.gridy = 5;
        c.weightx = 0.3;
        c.weighty = 0.3;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        this.menuPanel.add(this.player1ColorButton, c);

        this.player2ColorButton = new JButton(new ImageIcon(Reversi.colorImg));
        this.player2ColorButton.addActionListener(this);
        this.player2ColorButton.setActionCommand("color2");
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 5;
        c.gridy = 5;
        c.weightx = 0.3;
        c.weighty = 0.3;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        this.menuPanel.add(this.player2ColorButton, c);

        this.starButton = new JButton("Start");
        this.starButton.setFont(Reversi.NUM_FONT);
        this.starButton .addActionListener(this);
        this.starButton .setActionCommand("start");
        c.insets = new Insets(20, 0, 20, 0);
        c.gridx = 2;
        c.gridy = 6;
        c.weightx = 0.3;
        c.weighty = 0.3;
        c.gridwidth = 3;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.SOUTH;
        c.fill = GridBagConstraints.BOTH;
        this.menuPanel.add(this.starButton , c);


    }

    private void setupGamePanel(){
        this.board = new Board(this.dim);

        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTH;
    }

    public void updateLabels(){
        this.humanScoreLabel1.setText("Human: " + this.humanScore);
        this.AIScoreLabel1.setText(this.AIScore + " :AI");
        this.humanScoreLabel2.setText("Human: " + this.humanScore);
        this.AIScoreLabel2.setText(this.AIScore + " :AI");
        this.dimDisplay.setText("" + this.dim);
        this.player1Button.setText(this.player1Name);
        this.player2Button.setText(this.player2Name);
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