import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

public class InfoPanel extends JPanel implements Config{
    
    private Reversi parent;

    private JButton goBackButton;
    private JLabel title;
    private JScrollPane scrollInfo;
    private JTextPane info;

    private GridBagConstraints goBackButtonConstraints;
    private GridBagConstraints titleConstraints;
    private GridBagConstraints infoConstraints;

    public InfoPanel (Reversi parent)
    {
        super(new GridBagLayout());
        this.parent = parent;
        this.setBackground(Config.BG_COLOR);

        this.goBackButtonConstraints = new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);

        this.titleConstraints = new GridBagConstraints(1, 0, 1, 1, 1, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 20, 0, 0), 0, 0);

        this.infoConstraints = new GridBagConstraints(0, 1, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0);

        this.goBackButton = new JButton(new ImageIcon(Reversi.backImg));
        this.goBackButton.addActionListener(this.parent);
        this.goBackButton.setActionCommand("goBack");
        this.add(this.goBackButton, this.goBackButtonConstraints);

        this.title = new JLabel("Info");
        this.title.setFont(Config.TEXT_FONT);
        this.add(this.title, this.titleConstraints);

        this.info = new JTextPane();
        this.info.setEditable(false);
        this.info.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 10));
        StyledDocument doc = this.info.getStyledDocument();

        Style style = this.info.addStyle("Color Style", null);
        StyleConstants.setForeground(style, Color.BLACK);
        StyleConstants.setFontSize(style, 26);
        try {
            doc.insertString(doc.getLength(), Config.INFO, style);
        } 
        catch (BadLocationException e) {
            e.printStackTrace();
        }    

        this.scrollInfo = new JScrollPane(this.info, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(this.scrollInfo, this.infoConstraints);
    }
}
