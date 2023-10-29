import javax.swing.*;
import java.awt.*;

public class Reversi extends JPanel{

    public static final Color BG_COLOR = Color.LIGHT_GRAY;
    public static Color PLAYER1_COLOR = Color.WHITE;
    public static Color PLAYER2_COLOR = Color.BLACK;
    public static final Color GUI_COLOR = Color.GREEN;
    public static final Color BORDER_COLOR = Color.GREEN;

    private JPanel menuPanel;
    private JLabel title;
    private JLabel dimDisplay;
    private JButton inc;
    private JButton dec;
    private JLabel player1;
    private JLabel player2;
    private JButton player1Button;
    private JButton player2Button;
    private JButton player1ColorButton;
    private JButton player2ColorButton;
    private JButton starButton;

    private JButton goToMenu;
    private JLabel humanScore;
    private JLabel AIScore;
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

    public static int dim = 7;
    public CardLayout layout = new CardLayout();

    public Reversi(){
        this.setLayout(layout);

        createPanels();

        this.add(menuPanel);
        this.add(infoPanel);
        this.add(colorPanel);
        this.add(gamePanel);
        this.add(settingsPanel);
        layout.show(menuPanel, "Menu");
    }

    private void createPanels(){
        this.menuPanel = new JPanel(new GridLayout(5, 1, 5, 0));
        
    }
    
    private static void createAndShowGui() {
        Reversi mainPanel = new Reversi();

        JFrame frame = new JFrame("Reversi");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGui();
        });
    }
}