import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;
import javax.swing.Timer;

import static utils.Constants.*;

public class Board extends JPanel implements ActionListener, KeyListener {

    private final PaddlePlayer1 paddle1;
    private final PaddlePlayer2 paddle2;
    private final Ball ball;
    private final Set<Integer> activeKeys = new HashSet<>();

    private int score1 = 0;
    private int score2 = 0;
    private int rally = 0;
    private boolean matchOver = false;
    private String winnerMessage = "";
    private final Timer timer;
    private final List<Sprite> sprites;

    public Board() {
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setBackground(Color.BLACK);
        paddle1 = new PaddlePlayer1(20, BOARD_HEIGHT / 2 - PADDLE_HEIGHT / 2);
        paddle2 = new PaddlePlayer2(BOARD_WIDTH - 20 - PADDLE_WIDTH, BOARD_HEIGHT / 2 - PADDLE_HEIGHT / 2);
        ball = new Ball();
        sprites = new ArrayList<>();
        sprites.add(paddle1);
        sprites.add(paddle2);
        sprites.add(ball);
        timer = new Timer(TICK_DELAY, this);
        timer.start();
        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (matchOver) {
            repaint();
            return;
        }
        paddle1.handleKeys(activeKeys);
        paddle2.handleKeys(activeKeys);
        for (Sprite sprite : sprites) {
            sprite.tick();
        }
        checkCollisions();
        repaint();

    }

    private void checkCollisions() {
        if (ball.pos.y <= 0 || ball.pos.y + BALL_SIZE >= BOARD_HEIGHT) {
            ball.reverseY();
        }

        if (ball.pos.x <= paddle1.pos.x + PADDLE_WIDTH &&
                ball.pos.x >= paddle1.pos.x &&
                ball.pos.y + BALL_SIZE >= paddle1.pos.y &&
                ball.pos.y <= paddle1.pos.y + PADDLE_HEIGHT) {
            ball.pos.x = paddle1.pos.x + PADDLE_WIDTH;
            ball.reverseX();
            rally++;
        } else if (ball.pos.x + BALL_SIZE >= paddle2.pos.x &&
                ball.pos.x + BALL_SIZE <= paddle2.pos.x + PADDLE_WIDTH &&
                ball.pos.y + BALL_SIZE >= paddle2.pos.y &&
                ball.pos.y <= paddle2.pos.y + PADDLE_HEIGHT) {
            ball.pos.x = paddle2.pos.x - BALL_SIZE;
            ball.reverseX();
            rally++;
        }

        if (ball.pos.x < 0) {
            score2++;
            rally = 0;
            checkMatchWinner();
            ball.reset();
        }
        if (ball.pos.x > BOARD_WIDTH) {
            score1++;
            rally = 0;
            checkMatchWinner();
            ball.reset();
        }
    }

    private void checkMatchWinner() {
        if (score1 >= WINNING_SCORE) {
            matchOver = true;
            winnerMessage = "Player 1 is the winner of the Match!";
        } else if (score2 >= WINNING_SCORE) {
            matchOver = true;
            winnerMessage = "Player 2 is the winner of the Match!";
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        for (Sprite sprite : sprites) {
            sprite.draw(graphics, this);
        }

        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, BOARD_WIDTH, 5);
        graphics.fillRect(0, BOARD_HEIGHT - 5, BOARD_WIDTH, 5);
        graphics.setFont(new Font("Arial", Font.BOLD, 25));
        graphics.drawString("Player 1: " + score1, 50, 40);
        graphics.drawString("Player 2: " + score2, BOARD_WIDTH - 220, 40);
        graphics.drawString("Rally: " + rally, BOARD_WIDTH / 2 - 50, 40);
        if (matchOver) {
            graphics.setFont(new Font("Arial", Font.BOLD, 25));
            graphics.drawString(winnerMessage, BOARD_WIDTH / 2 - 200, BOARD_HEIGHT / 2);
            timer.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        activeKeys.add(keyEvent.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        activeKeys.remove(keyEvent.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {}
}