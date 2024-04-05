import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class UserInterface extends JFrame {
    private JTextArea textArea;
    private JPanel panel;
    private Color randomGreenHue;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public UserInterface() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Menu Example");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        randomGreenHue = generateRandomGreenHue();

        textArea = new JTextArea();
        panel = new JPanel(); // Initialize the JPanel
        panel.setLayout(new BorderLayout()); // Set the layout
        panel.add(textArea); // Add the JTextArea to the JPanel

        getContentPane().add(panel); // Add the JPanel to the JFrame's content pane

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");

        JMenuItem menuItemDate = new JMenuItem("Show Date and Time");
        menuItemDate.addActionListener(e -> showDateTime());

        JMenuItem menuItemSave = new JMenuItem("Save Text");
        menuItemSave.addActionListener(e -> saveText());

        JMenuItem menuItemColor = new JMenuItem("Change Background Color");
        menuItemColor.addActionListener(e -> changeBackgroundColor());

        JMenuItem menuItemExit = new JMenuItem("Exit");
        menuItemExit.addActionListener(e -> System.exit(0));

        menu.add(menuItemDate);
        menu.add(menuItemSave);
        menu.add(menuItemColor);
        menu.add(menuItemExit);

        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    private void showDateTime() {
        String dateTime = sdf.format(new Date());
        textArea.setText(dateTime);
    }

    private void saveText() {
        try (FileWriter writer = new FileWriter("save_file.txt")) {
            writer.write(textArea.getText());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void changeBackgroundColor() {
        // Generate a new random green hue each time the button is pressed
        Color newRandomGreenHue = generateRandomGreenHue();
        textArea.setBackground(newRandomGreenHue);
    }
    
    private Color generateRandomGreenHue() {
        Random rand = new Random();
        // Random hue of green, keeping green channel high
        return new Color(rand.nextInt(100), 150 + rand.nextInt(105), rand.nextInt(100));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserInterface ex = new UserInterface();
            ex.setVisible(true);
        });
    }
}
