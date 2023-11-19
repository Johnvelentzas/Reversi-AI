import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

public class settingsPanel extends JPanel implements Config, ItemListener
{
    
    private Reversi parent;

    private JButton goBackButton;
    private JLabel title;
    private JScrollPane scrollSettings;
    private JPanel settings;

    private GridBagConstraints goBackButtonConstraints;
    private GridBagConstraints titleConstraints;
    private GridBagConstraints settingsConstraints;

    private JCheckBox previewMove;

    public settingsPanel(Reversi parent)
    {
        super(new GridBagLayout());
        this.parent = parent;
        this.setBackground(Config.BG_COLOR);

        this.goBackButtonConstraints = new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);

        this.titleConstraints = new GridBagConstraints(1, 0, 1, 1, 1, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 20, 0, 0), 0, 0);

        this.settingsConstraints = new GridBagConstraints(0, 1, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0);

        this.goBackButton = new JButton(new ImageIcon(Reversi.backImg));
        this.goBackButton.addActionListener(this.parent);
        this.goBackButton.setActionCommand("goBack");
        this.add(this.goBackButton, this.goBackButtonConstraints);

        this.title = new JLabel("Settings");
        this.title.setFont(Config.TEXT_FONT);
        this.add(this.title, this.titleConstraints);

        //Settings
        this.previewMove = new JCheckBox("Preview Move");
        this.previewMove.setFont(Config.SETTINGS_FONT);
        this.previewMove.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 10));
        this.previewMove.addItemListener(this);

        this.settings = new JPanel();
        this.settings.setLayout(new BoxLayout(this.settings, BoxLayout.PAGE_AXIS));
        this.settings.add(this.previewMove);

        this.scrollSettings = new JScrollPane(this.settings, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(this.scrollSettings, this.settingsConstraints);
    }

    @Override
    public void itemStateChanged(ItemEvent e) 
    {
        if (this.previewMove.isSelected()) {
            this.parent.previewMove = true;
        } 
        else {
            this.parent.previewMove = false;
        }
    }
}
