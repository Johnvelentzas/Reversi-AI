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

    private static int dim = 7;
    private static int humanScore = 0;
    private static int AIScore = 0;
    private static int player1 = 0;
    private static int player2 = 1;
    private static String player1Name = "Human";
    private static String player2Name = "AI";
    private static int colorSelection = 0;

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
    private JLabel humanScoreLabel;
    private JLabel AIScoreLabel;
    private JButton goToSettings;
    private JButton goToInfo;

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

        this.goToInfo = new JButton(new ImageIcon(Reversi.infoImg));
        this.goToInfo.addActionListener(this);
        this.goToInfo.setActionCommand("goToInfo");
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.3;
        c.fill = GridBagConstraints.BOTH;
        this.menuPanel.add(this.goToInfo, c);

        this.humanScoreLabel = new JLabel("Human: " + Reversi.humanScore);
        this.humanScoreLabel.setFont(Reversi.TEXT_FONT);
        c.insets = new Insets(0, 10, 0, 0);
        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.menuPanel.add(this.humanScoreLabel, c);

        this.AIScoreLabel = new JLabel(Reversi.AIScore + " :AI", JLabel.RIGHT);
        this.AIScoreLabel.setFont(Reversi.TEXT_FONT);
        c.insets = new Insets(0, 0, 0, 10);
        c.gridx = 4;
        c.gridy = 0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.menuPanel.add(this.AIScoreLabel, c);

        this.goToMenu = new JButton(new ImageIcon(Reversi.menuImg));
        this.goToMenu.addActionListener(this);
        this.goToMenu.setActionCommand("goToMenu");
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.3;
        c.fill = GridBagConstraints.BOTH;
        this.gamePanel.add(this.goToMenu, c);
        this.infoPanel.add(this.goToMenu, c);
        this.colorPanel.add(this.goToMenu, c);
        this.settingsPanel.add(this.goToMenu, c);


        this.goToSettings = new JButton(new ImageIcon(Reversi.settingsImg));
        this.goToSettings.addActionListener(this);
        this.goToSettings.setActionCommand("goToSettings");
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 6;
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

        this.dimDisplay = new JLabel("" + Reversi.dim);
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

        this.player1Button = new JButton(Reversi.player1Name);
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

        this.player2Button = new JButton(Reversi.player2Name);
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

    public void updateLabels(){
        this.humanScoreLabel.setText("Human: " + Reversi.humanScore);
        this.AIScoreLabel.setText(Reversi.AIScore + " :AI");
        this.dimDisplay.setText("" + Reversi.dim);
        this.player1Button.setText(Reversi.player1Name);
        this.player2Button.setText(Reversi.player2Name);
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
                layout.show(this, "info");
                break;
            case "goToMenu":
                layout.show(this, "menu");
                break;
            case "goToSettings":
                layout.show(this, "settings");
                break;
            case "incDim": 
                if (Reversi.dim >= Reversi.MAX_DIM) {
                    break;
                }
                Reversi.dim += 1;
                updateLabels();
                break;
            case "decDim": 
                if (Reversi.dim <= Reversi.MIN_DIM) {
                    break;
                }
                Reversi.dim -= 1;
                updateLabels();
                break;
            case "toggle1": 
                if (Reversi.player1 == 0) {
                    Reversi.player1 = 1;
                    Reversi.player1Name = "AI";
                }else{
                    Reversi.player1 = 0;
                    Reversi.player1Name = "Human";
                }
                updateLabels();
                break;
            case "toggle2": 
                if (Reversi.player2 == 0) {
                    Reversi.player2 = 1;
                    Reversi.player2Name = "AI";
                }else{
                    Reversi.player2 = 0;
                    Reversi.player2Name = "Human";
                }
                updateLabels();
                break;
            case "color1":
                Reversi.colorSelection = 1;
                layout.show(this, "color");
                break;
            case "color2":
                Reversi.colorSelection = 2;
                layout.show(this, "color");
                break;
            default:
                break;
        }
    }
}