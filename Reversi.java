import javax.swing.*;
import java.awt.*;

public class Reversi extends JPanel{

    private static final Color BG_COLOR = Color.LIGHT_GRAY;
    private static final Color WHITE_COLOR = Color.WHITE;
    private static final Color BLACK_COLOR = Color.BLACK;
    private static final Color GUI_COLOR = Color.GREEN;

    private static int dim = 7;

    public Reversi(){
        
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