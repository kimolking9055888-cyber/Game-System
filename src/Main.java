// Game System Project:-

// Imports:-
import javax.swing.*;

// Main Class:-
public class Main {

    // Main Method:-
    public static void main(String[] args) {
        // Set system look for better fonts/rendering:
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }
        // If setting the look and feel fails, we catch the exception and proceed with the default look and feel.
        catch (Exception e) {
            System.out.println("Failed to set look and feel: " + e.getMessage());
        }

        // Launch the Game UI on the Event Dispatch Thread (EDT) for thread safety:
        SwingUtilities.invokeLater(() -> new GameUI());

    }
    // End of Main Method.
}
// End of Main Class.