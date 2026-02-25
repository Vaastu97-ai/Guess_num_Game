import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Game {

    // ===== GLOBALS =====
    static JFrame frame;
    static CardLayout cardLayout;
    static JPanel mainPanel;

    static String currentUser = "";
    static int randomNumber;
    static int tries;

    static JTextField guessField;
    static JLabel messageLabel;
    static JLabel statsLabel;

    // ===== FILES =====
    static final String USER_FILE = "users.txt";
    static final String SCORE_FILE = "scores.txt";

    public static void main(String[] args) {
        frame = new JFrame("🎯 Number Guessing Game Pro");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(loginPanel(), "login");
        mainPanel.add(gamePanel(), "game");

        frame.add(mainPanel);
        frame.setSize(420, 420);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // ================= LOGIN PANEL =================
    static JPanel loginPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));

        JLabel title = new JLabel("🔐 Login", SwingConstants.CENTER);
        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();

        JButton loginBtn = new JButton("Login / Register");

        panel.add(title);
        panel.add(new JLabel("Username:"));
        panel.add(userField);
        panel.add(new JLabel("Password:"));
        panel.add(passField);
        panel.add(loginBtn);

        loginBtn.addActionListener(e -> {
            String user = userField.getText().trim();
            String pass = new String(passField.getPassword()).trim();

            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Fill all fields!");
                return;
            }

            if (authenticate(user, pass)) {
                currentUser = user;
                startNewGame();
                updateStats();
                cardLayout.show(mainPanel, "game");
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong password!");
            }
        });

        return panel;
    }

    // ================= GAME PANEL =================
    static JPanel gamePanel() {
        JPanel panel = new JPanel(new GridLayout(7, 1, 10, 10));

        JLabel title = new JLabel("🎯 Guess the Number (1-100)", SwingConstants.CENTER);
        guessField = new JTextField();
        messageLabel = new JLabel("Start guessing!", SwingConstants.CENTER);
        statsLabel = new JLabel("", SwingConstants.CENTER);

        JButton guessBtn = new JButton("Guess");
        JButton restartBtn = new JButton("Restart");
        JButton exitBtn = new JButton("Exit");

        panel.add(title);
        panel.add(guessField);
        panel.add(guessBtn);
        panel.add(messageLabel);
        panel.add(statsLabel);
        panel.add(restartBtn);
        panel.add(exitBtn);

        // ===== GUESS BUTTON =====
        guessBtn.addActionListener(e -> handleGuess());

        // ===== RESTART =====
        restartBtn.addActionListener(e -> {
            startNewGame();
            messageLabel.setText("Game restarted!");
        });

        // ===== EXIT =====
        exitBtn.addActionListener(e -> System.exit(0));

        return panel;
    }

    // ================= GAME LOGIC =================
    static void startNewGame() {
        randomNumber = new Random().nextInt(100) + 1;
        tries = 0;
        guessField.setText("");
        messageLabel.setText("Start guessing!");
    }

    static void handleGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());
            tries++;

            if (guess == randomNumber) {
                messageLabel.setText("🎉 Correct in " + tries + " tries!");
                updateScore(currentUser, tries);
                updateStats();
            } else if (guess < randomNumber) {
                messageLabel.setText("📉 Too Low!");
            } else {
                messageLabel.setText("📈 Too High!");
            }

        } catch (Exception ex) {
            messageLabel.setText("⚠ Enter valid number!");
        }
    }

    // ================= AUTH =================
    static boolean authenticate(String user, String pass) {
        try {
            File file = new File(USER_FILE);
            if (!file.exists()) file.createNewFile();

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(user)) {
                    br.close();
                    return parts[1].equals(pass);
                }
            }
            br.close();

            // register new user
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            bw.write(user + "," + pass);
            bw.newLine();
            bw.close();
            return true;

        } catch (IOException e) {
            return false;
        }
    }

    // ================= SCORE TRACKING =================
    static void updateScore(String user, int tries) {
        try {
            File file = new File(SCORE_FILE);
            if (!file.exists()) file.createNewFile();

            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            bw.write(user + "," + tries);
            bw.newLine();
            bw.close();
        } catch (IOException ignored) {}
    }

    static void updateStats() {
        int best = Integer.MAX_VALUE;
        int worst = Integer.MIN_VALUE;

        try {
            BufferedReader br = new BufferedReader(new FileReader(SCORE_FILE));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int t = Integer.parseInt(parts[1]);
                best = Math.min(best, t);
                worst = Math.max(worst, t);
            }
            br.close();

            if (best == Integer.MAX_VALUE) {
                statsLabel.setText("No records yet.");
            } else {
                statsLabel.setText("🏆 Best: " + best + " | 😅 Worst: " + worst);
            }

        } catch (Exception e) {
            statsLabel.setText("No records yet.");
        }
    }
}