import javax.swing.SwingUtilities;

import GUI.StudentManagerGUI;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentManagerGUI().setVisible(true);
        });
    }
    
}
