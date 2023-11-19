import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener, Config{
    
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
        this.humanScoreLabel.setText("Player 1: " + this.parent.board.getPlayer1Score());
        this.AIScoreLabel.setText(this.parent.board.getPlayer2Score() + " : Player 2");
        this.activePlayer.setText(this.parent.activePlayerLabel);
    }

    public GamePanel(Reversi parent){
        super(new GridBagLayout());
        this.parent = parent;
        this.parent.board = new Board();
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

        this.humanScoreLabel = new JLabel("Player 1: " + this.parent.board.getPlayer1Score());
        this.humanScoreLabel.setFont(Config.TEXT_FONT);
        this.add(this.humanScoreLabel, this.humanScoreLabelConstraints);

        this.activePlayer = new JLabel(this.parent.activePlayerLabel);
        this.activePlayer.setFont(Config.TEXT_FONT);
        this.add(this.activePlayer, this.activePlayerConstraints);

        this.AIScoreLabel = new JLabel(this.parent.board.getPlayer2Score() + " :Player 2" + this.parent.player2Name, JLabel.RIGHT);
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
        this.placePawn.addActionListener(this);
        this.placePawn.setActionCommand("place");
    }

    public void setupNewGame(){
        this.parent.board = new Board(this.parent.dim);
        this.parent.posibleMoves = new ArrayList<>();
        this.parent.previewPawns = new ArrayList<>();
        this.parent.activePlayer = 1;
        this.parent.activePlayerInput = this.parent.player1Tag;
        this.parent.activePlayerLetter = Board.PLAYER_1;
        this.parent.activePlayerLabel = "Player 1";
        this.parent.player1 = new Player(this.parent.maxDepth, Board.PLAYER_1);
        this.parent.player2 = new Player(this.parent.maxDepth, Board.PLAYER_2);
        this.gameGrid = new JButton[this.parent.dim][this.parent.dim];
        this.innerPanel.removeAll();
        this.gridPanel = new JPanel(new GridLayout(this.parent.dim, this.parent.dim));
        this.innerPanel.add(this.gridPanel, BorderLayout.CENTER);

        for (int i = 0; i < gameGrid.length; i++) {
            for (int j = 0; j < gameGrid[i].length; j++) {
                JButton button = new JButton();
                button.addActionListener(this);
                button.setActionCommand(i + "-" + j);
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
        this.updatePawns();
        this.updateLabels();
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
    }

    public synchronized void updatePosibleMoves(){
        for (Move pawn : this.parent.posibleMoves) {
            this.gameGrid[pawn.getRow()][pawn.getCol()].setIcon(new ImageIcon(this.parent.POSIBLE_MOVE));
        }
    }

    public synchronized void removePosibleMoves(){
        for (Move pawn : this.parent.posibleMoves) {
            this.gameGrid[pawn.getRow()][pawn.getCol()].setIcon(new ImageIcon(this.parent.EMPTY_PAWN));
        }
    }

    public synchronized void updatePreviewPawns(){
        Icon icon;
        if (this.parent.activePlayerLetter == -1) {
            icon = new ImageIcon(this.parent.PLAYER1PAWN);
        } else {
            icon = new ImageIcon(this.parent.PLAYER2PAWN);
        }
        for (Move pawn : this.parent.previewPawns) {
            this.gameGrid[pawn.getRow()][pawn.getCol()].setIcon(icon);
        }
    }

    private void nextMove(){
        if (this.parent.board.isTerminal()) {
            finishGame();
        }
        this.parent.posibleMoves = this.parent.board.findPossibleMoves(this.parent.activePlayerLetter);
        if (this.parent.posibleMoves.isEmpty()) {
            changePlayer();
            this.parent.posibleMoves = this.parent.board.findPossibleMoves(this.parent.activePlayerLetter);
        }
        if (this.parent.activePlayerInput == PlayerTag.AI) {
            this.parent.nextMove = this.parent.activePlayerAI.getMove(this.parent.board);
            this.placeMove();
        } else {
            this.updatePosibleMoves();
        }
    }

    private void finishGame(){

    }

    private void placeMove(){
        this.removePosibleMoves();
        this.parent.board.makeMove(this.parent.nextMove, this.parent.activePlayerLetter);
        this.changePlayer();
        this.updatePawns();
        this.updateLabels();
        this.nextMove();
    }

    private void changePlayer(){
        if (this.parent.activePlayer == 1) {
            this.parent.activePlayer = 2;
            this.parent.activePlayerInput = this.parent.player2Tag;
            this.parent.activePlayerLetter = Board.PLAYER_2;
            this.parent.activePlayerLabel = "Player 2";
            this.parent.activePlayerAI = this.parent.player2;
        } else {
            this.parent.activePlayer = 1;
            this.parent.activePlayerInput = this.parent.player1Tag;
            this.parent.activePlayerLetter = Board.PLAYER_1;
            this.parent.activePlayerLabel = "Player 1";
            this.parent.activePlayerAI = this.parent.player1;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "place") {
            if (this.parent.previewMove) {
                placeMove();
            }
            return;
        }
        String[] command = e.getActionCommand().split("-");
        Move move = new Move(Integer.parseInt(command[0]), Integer.parseInt(command[1]));
        if (this.parent.activePlayerInput == PlayerTag.human) {
            for (Move posibleMove : this.parent.posibleMoves) {
                if (move.equals(posibleMove)) {
                    this.parent.nextMove = move;
                    this.removePosibleMoves();
                    this.updatePreviewPawns();
                    if (!this.parent.previewMove) {
                        placeMove();
                    }
                }
            }
        }
    }
}
