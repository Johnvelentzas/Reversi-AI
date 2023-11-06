import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class GamePanel extends JPanel implements Config{
    
    private Reversi parent;
    
    private JButton goToMainMenu;
    private JLabel humanScoreLabel;
    private JLabel activePlayer;
    private JLabel AIScoreLabel;
    private JButton goToInfo;
    private JPanel innerPanel;
    private JPanel gridPanel;
    private JButton[][] gameGrid;
    private JButton placePawn;

    private GridBagConstraints goToMainMenuConstraints;
    private GridBagConstraints humanScoreLabelConstraints;
    private GridBagConstraints activePlayerConstraints;
    private GridBagConstraints AIScoreLabelConstraints;
    private GridBagConstraints goToInfoConstraints;
    private GridBagConstraints innerPanelConstraints;
    private GridBagConstraints placePawnConstraints;


    public void updateLabels(){
        this.humanScoreLabel.setText("Human: " + this.parent.humanScore);
        this.AIScoreLabel.setText(this.parent.AIScore + " :AI");
        this.activePlayer.setText(this.parent.activePlayerLabel);
    }

    public GamePanel(Reversi parent){
        super(new GridBagLayout());
        this.parent = parent;
        this.setBackground(Config.BG_COLOR);

        this.goToMainMenuConstraints = new GridBagConstraints(0, 0, 1, 1, 0.3, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);

        this.humanScoreLabelConstraints = new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 10, 0, 0), 0, 0);

        this.activePlayerConstraints = new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 10, 0, 10), 0, 0);

        this.AIScoreLabelConstraints = new GridBagConstraints(3, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 10), 0, 0);

        this.goToInfoConstraints = new GridBagConstraints(4, 0, 1, 1, 0.3, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);

        this.innerPanelConstraints = new GridBagConstraints(0, 1, 5, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0);

        this.placePawnConstraints = new GridBagConstraints(0, 2, 5, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 10, 0), 0, 0);

        this.goToMainMenu = new JButton(new ImageIcon(Reversi.menuImg));
        this.goToMainMenu.addActionListener(this.parent);
        this.goToMainMenu.setActionCommand("goToMenu");
        this.add(this.goToMainMenu, this.goToMainMenuConstraints);

        this.humanScoreLabel = new JLabel("Human: " + this.parent.humanScore);
        this.humanScoreLabel.setFont(Config.TEXT_FONT);
        this.add(this.humanScoreLabel, this.humanScoreLabelConstraints);

        this.activePlayer = new JLabel(this.parent.activePlayerLabel);
        this.activePlayer.setFont(Config.TEXT_FONT);
        this.add(this.activePlayer, this.activePlayerConstraints);

        this.AIScoreLabel = new JLabel(this.parent.AIScore + " :AI", JLabel.RIGHT);
        this.AIScoreLabel.setFont(Config.TEXT_FONT);
        this.add(this.AIScoreLabel, this.AIScoreLabelConstraints);

        this.goToInfo = new JButton(new ImageIcon(Reversi.infoImg));
        this.goToInfo.addActionListener(this.parent);
        this.goToInfo.setActionCommand("goToInfo");
        this.add(this.goToInfo, this.goToInfoConstraints);

        this.innerPanel = new JPanel(new BorderLayout());
        this.innerPanel.setBackground(Config.BG_COLOR);
        this.add(this.innerPanel, this.innerPanelConstraints);

        this.placePawn = new JButton("Place Pawn");
        this.placePawn.setFont(Config.SETTINGS_FONT);
    }

    public void setupNewGame(){
        this.parent.board = new Board(this.parent.dim);
        this.parent.posibleMoves = new ArrayList<>();
        this.gameGrid = new JButton[this.parent.dim][this.parent.dim];
        this.innerPanel.removeAll();
        this.gridPanel = new JPanel(new GridLayout(this.parent.dim, this.parent.dim));
        this.innerPanel.add(this.gridPanel, BorderLayout.CENTER);

        for (int i = 0; i < gameGrid.length; i++) {
            for (int j = 0; j < gameGrid[i].length; j++) {
                JButton button = new JButton();
                button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                button.setPreferredSize(new Dimension(50, 50));
                this.gameGrid[i][j] = button;
                this.gridPanel.add(this.gameGrid[i][j]);
            }
        }

        if (this.parent.previewMove) {
            this.add(this.placePawn, this.placePawnConstraints);
        }else{
            this.remove(this.placePawn);
        }

        this.nextMove();
    }

    public void updatePawns(){
        for (int i = 0; i < this.parent.dim; i++) {
            for (int j = 0; j < this.parent.dim; j++) {
                switch (this.parent.board.getPawn(i, j)) {
                    case -1:
                        this.gameGrid[i][j].setIcon(new ImageIcon(this.parent.PLAYER1PAWN));
                        break;
                    case 0:
                        this.gameGrid[i][j].setIcon(new ImageIcon(this.parent.EMPTY_PAWN));
                        break;
                    case 1:
                        this.gameGrid[i][j].setIcon(new ImageIcon(this.parent.PLAYER2PAWN));
                        break;
                    default:
                        break;
                }
            }
        }
        for (Move pawn : this.parent.posibleMoves) {
            this.gameGrid[pawn.getRow()][pawn.getCol()].setIcon(new ImageIcon(this.parent.POSIBLE_MOVE));
        }
    }

    private void nextMove(){
        if (this.parent.board.isTerminal()) {
            finishGame();
        }
        if (this.parent.activePlayerInput == Player.human ) {
            this.parent.posibleMoves = this.parent.board.findPossibleMoves(this.parent.activePlayerLetter);
        } else{
            this.parent.posibleMoves.clear();
        }
        this.updatePawns();
    }

    private void finishGame(){

    }
}
