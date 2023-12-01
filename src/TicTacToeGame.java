import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class TicTacToeGame {
    int boardWidth = 600;
    int boardHeight = 650; //50px for the text panel on top

    JFrame frame = new JFrame("FWD-Game");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board;
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;
    int turns = 0;
    int numberOfGrid = 0;

    TicTacToeGame(int gridNumber) {
        numberOfGrid = gridNumber;
        board = new JButton[numberOfGrid][numberOfGrid];
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("FWD-Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(numberOfGrid, numberOfGrid));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        for (int r = 0; r < numberOfGrid; r++) {
            for (int c = 0; c < numberOfGrid; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 60));
                tile.setFocusable(false);

                tile.addActionListener(e -> {
                    if (gameOver) {
                        new TicTacToeGame(numberOfGrid);
                    } else {
                        JButton tile1 = (JButton) e.getSource();
                        if (Objects.equals(tile1.getText(), "")) {
                            tile1.setForeground(Color.black);
                            tile1.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if (!gameOver) {
                                currentPlayer = Objects.equals(currentPlayer, playerX) ? playerO : playerX;
                                textLabel.setText(currentPlayer + "'s turn.");
                            }
                        }
                    }
                });
            }
        }
    }

    void checkWinner() {
        for (int r = 0; r < numberOfGrid; r++) {
            for (int c = 0; c < numberOfGrid; c++){
                if (Objects.equals(board[r][c].getText(), "")) continue;

                // Horizontal
                if ((c+2) < numberOfGrid &&
                        Objects.equals(board[r][c].getText(), board[r][c + 1].getText()) &&
                        Objects.equals(board[r][c + 1].getText(), board[r][c + 2].getText())) {
                    for (int i = 0; i < 3; i++) {
                        setWinner(board[r][c + i]);
                    }
                    gameOver = true;
                    return;
                }

                // Vertical
                if ((r+2) < numberOfGrid &&
                        Objects.equals(board[r][c].getText(), board[r + 1][c].getText()) &&
                        Objects.equals(board[r + 1][c].getText(), board[r + 2][c].getText())) {
                    for (int i = 0; i < 3; i++) {
                        setWinner(board[r + i][c]);
                    }
                    gameOver = true;
                    return;
                }

                // Diagonal
                if ((r+2) < numberOfGrid && (c+2) < numberOfGrid &&
                        Objects.equals(board[r][c].getText(), board[r + 1][c + 1].getText()) &&
                        Objects.equals(board[r + 1][c + 1].getText(), board[r + 2][c + 2].getText())) {
                    for (int i = 0; i < 3; i++) {
                        setWinner(board[r + i][c + i]);
                    }
                    gameOver = true;
                    return;
                }

                // Anti Diagonal
                if ((r+2) < numberOfGrid && c > 1 &&
                        Objects.equals(board[r][c].getText(), board[r + 1][c - 1].getText()) &&
                        Objects.equals(board[r + 1][c - 1].getText(), board[r + 2][c - 2].getText())) {
                    for (int i = 0; i < 3; i++) {
                        setWinner(board[r + i][c - i]);
                    }
                    gameOver = true;
                    return;
                }
            }
        }

        if (turns == (numberOfGrid * numberOfGrid)) {
            for (int r = 0; r < numberOfGrid; r++) {
                for (int c = 0; c < numberOfGrid; c++) {
                    setTie(board[r][c]);
                }
            }
            gameOver = true;
        }
    }

    void setWinner(JButton tile) {
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        textLabel.setText(currentPlayer + " is the winner! Tap anywhere to restart");
        textLabel.setFont(new Font("Arial", Font.BOLD, 20));
    }

    void setTie(JButton tile) {
        tile.setForeground(Color.orange);
        tile.setBackground(Color.gray);
        textLabel.setText("Tie!");
    }
}