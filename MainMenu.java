import java.awt.*;
import javax.swing.*;

public class MainMenu extends JPanel implements Config
{

    private Reversi parent;
    
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
    private JLabel humanScoreLabel;
    private JLabel AIScoreLabel;
    private JButton goToSettings;
    private JButton goToInfo;

    private GridBagConstraints titleConstraints;
    private GridBagConstraints dimDisplayConstraints;
    private GridBagConstraints incConstraints;
    private GridBagConstraints decConstraints;
    private GridBagConstraints player1LabelConstraints;
    private GridBagConstraints player2LabelConstraints;
    private GridBagConstraints player1ButtonConstraints;
    private GridBagConstraints player2ButtonConstraints;
    private GridBagConstraints player1ColorButtonConstraints;
    private GridBagConstraints player2ColorButtonConstraints;
    private GridBagConstraints startButtonConstraints;
    private GridBagConstraints humanScoreLabelConstraints;
    private GridBagConstraints AIScoreLabelConstraints;
    private GridBagConstraints goToSettingsConstraints;
    private GridBagConstraints goToInfoConstraints;

    public void updateLabels()
    {
        this.humanScoreLabel.setText("Human: " + this.parent.humanScore);
        this.AIScoreLabel.setText(this.parent.AIScore + " :AI");
        this.dimDisplay.setText("" + this.parent.dim);
        this.player1Button.setText(this.parent.player1Name);
        this.player2Button.setText(this.parent.player2Name);
    }

    public MainMenu(Reversi parent)
    {
        super(new GridBagLayout());
        this.parent = parent;
        this.setBackground(Config.BG_COLOR);

        this.titleConstraints = new GridBagConstraints(2, 1, 3, 1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(10, 0, 10, 0), 0, 0);

        this.dimDisplayConstraints = new GridBagConstraints(2, 2, 1, 2, 0.3, 0.3, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 10, 0, 10), 0, 0);

        this.incConstraints = new GridBagConstraints(4, 2, 1, 1, 0.3, 0.3, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 10, 0, 10), 0, 0);

        this.decConstraints = new GridBagConstraints(4, 3, 1, 1, 0.3, 0.3, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 10, 0, 10), 0, 0);

        this.player1LabelConstraints = new GridBagConstraints(2, 4, 1, 1, 1.0, 0.3, GridBagConstraints.SOUTH, GridBagConstraints.NONE, new Insets(0, 10, 0, 10), 0, 0);

        this.player2LabelConstraints = new GridBagConstraints(4, 4, 1, 1, 1.0, 0.3, GridBagConstraints.SOUTH, GridBagConstraints.NONE, new Insets(0, 10, 0, 10), 0, 0);

        this.player1ButtonConstraints = new GridBagConstraints(2, 5, 1, 1, 1.0, 0.3, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);

        this.player2ButtonConstraints = new GridBagConstraints(4, 5, 1, 1, 1.0, 0.3, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);

        this.player1ColorButtonConstraints = new GridBagConstraints(1, 5, 1, 1, 0.3, 0.3, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);

        this.player2ColorButtonConstraints = new GridBagConstraints(5, 5, 1, 1, 0.3, 0.3, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);

        this.startButtonConstraints = new GridBagConstraints(2, 6, 3, 1, 0.3, 0.3, GridBagConstraints.SOUTH, GridBagConstraints.BOTH, new Insets(20, 0, 20, 0), 0, 0);

        this.humanScoreLabelConstraints = new GridBagConstraints(2, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 10, 0, 0), 0, 0);

        this.AIScoreLabelConstraints = new GridBagConstraints(4, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 10), 0, 0);

        this.goToSettingsConstraints = new GridBagConstraints(0, 0, 1, 1, 0.3, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);

        this.goToInfoConstraints = new GridBagConstraints(6, 0, 1, 1, 0.3, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);

        this.title = new JLabel(Config.TITLE);
        this.title.setFont(Reversi.TITLE_FONT);
        this.add(this.title, this.titleConstraints);

        this.dimDisplay = new JLabel("" + this.parent.dim);
        this.dimDisplay.setFont(Config.NUM_FONT);
        this.add(this.dimDisplay, this.dimDisplayConstraints);

        this.inc = new JButton(new ImageIcon(Reversi.upImg));
        this.inc.addActionListener(this.parent);
        this.inc.setActionCommand("incDim");
        this.add(this.inc, this.incConstraints);

        this.dec = new JButton(new ImageIcon(Reversi.downImg));
        this.dec.addActionListener(this.parent);
        this.dec.setActionCommand("decDim");
        this.add(this.dec, this.decConstraints);

        this.player1Label = new JLabel("Player 1");
        this.player1Label.setFont(Config.TEXT_FONT);
        this.add(this.player1Label, this.player1LabelConstraints);

        this.player2Label = new JLabel("Player 2");
        this.player2Label.setFont(Config.TEXT_FONT);
        this.add(this.player2Label, this.player2LabelConstraints);

        this.player1Button = new JButton(this.parent.player1Name);
        this.player1Button.setFont(Config.TEXT_FONT);
        this.player1Button.addActionListener(this.parent);
        this.player1Button.setActionCommand("toggle1");
        this.add(this.player1Button, this.player1ButtonConstraints);

        this.player2Button = new JButton(this.parent.player2Name);
        this.player2Button.setFont(Config.TEXT_FONT);
        this.player2Button.addActionListener(this.parent);
        this.player2Button.setActionCommand("toggle2");
        this.add(this.player2Button, this.player2ButtonConstraints);

        this.player1ColorButton = new JButton(new ImageIcon(Reversi.colorImg));
        this.player1ColorButton.addActionListener(this.parent);
        this.player1ColorButton.setActionCommand("color1");
        this.add(this.player1ColorButton, this.player1ColorButtonConstraints);

        this.player2ColorButton = new JButton(new ImageIcon(Reversi.colorImg));
        this.player2ColorButton.addActionListener(this.parent);
        this.player2ColorButton.setActionCommand("color2");
        this.add(this.player2ColorButton, this.player2ColorButtonConstraints);

        this.starButton = new JButton("Start");
        this.starButton.setFont(Config.NUM_FONT);
        this.starButton .addActionListener(this.parent);
        this.starButton .setActionCommand("start");
        this.add(this.starButton, this.startButtonConstraints);

        this.humanScoreLabel = new JLabel("Human: " + this.parent.humanScore);
        this.humanScoreLabel.setFont(Config.TEXT_FONT);
        this.add(this.humanScoreLabel, this.humanScoreLabelConstraints);

        this.AIScoreLabel = new JLabel(this.parent.AIScore + " :AI", JLabel.RIGHT);
        this.AIScoreLabel.setFont(Config.TEXT_FONT);
        this.add(this.AIScoreLabel, this.AIScoreLabelConstraints);

        this.goToSettings = new JButton(new ImageIcon(Reversi.settingsImg));
        this.goToSettings.addActionListener(this.parent);
        this.goToSettings.setActionCommand("goToSettings");
        this.add(this.goToSettings, this.goToSettingsConstraints);

        this.goToInfo = new JButton(new ImageIcon(Reversi.infoImg));
        this.goToInfo.addActionListener(this.parent);
        this.goToInfo.setActionCommand("goToInfo");
        this.add(this.goToInfo, this.goToInfoConstraints);
    }
}