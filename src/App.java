import javax.swing.*;

public class App {
    public static void main(String[] args) {
        JFrame window = new JFrame("Pong Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Board board = new Board();
        window.add(board);
        window.pack();

        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
