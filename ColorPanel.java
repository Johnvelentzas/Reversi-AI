import java.awt.*;
import javax.swing.*;

public class ColorPanel extends JPanel implements Config{

    private Reversi parent;

    private JButton goBackButton;
    private JLabel title;
    private JScrollPane scrollColor;
    private JPanel color;

    private GridBagConstraints goBackButtonConstraints;
    private GridBagConstraints titleConstraints;
    private GridBagConstraints colorConstraints;

    public ColorPanel(Reversi parent){
        super(new GridBagLayout());
        this.parent = parent;
        this.setBackground(Config.BG_COLOR);

        this.goBackButtonConstraints = new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);

        this.titleConstraints = new GridBagConstraints(1, 0, 1, 1, 1, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 20, 0, 0), 0, 0);

        this.colorConstraints = new GridBagConstraints(0, 1, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0);

        this.goBackButton = new JButton(new ImageIcon(Reversi.backImg));
        this.goBackButton.addActionListener(this.parent);
        this.goBackButton.setActionCommand("goBack");
        this.add(this.goBackButton, this.goBackButtonConstraints);

        this.title = new JLabel("Choose color for player " + this.parent.colorSelection);
        this.title.setFont(Config.TEXT_FONT);
        this.add(this.title, this.titleConstraints);

        this.color = new JPanel();
        this.color.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        //this.color.add(null);

        this.scrollColor = new JScrollPane(this.color, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(this.scrollColor, this.colorConstraints);
    }

    public void updateLabel(){
        this.title.setText("Choose color for player " + this.parent.colorSelection);
    }
}
