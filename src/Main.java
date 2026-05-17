import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("mebi"); // vytvoření okna s názvem "mebi"
        EditorWindow editorWindow = new EditorWindow(); // vytvoření instance EditorWindow
        frame.setJMenuBar(editorWindow.vytvorMenuBar()); // nastavení hlavního menu okna
        frame.setContentPane(editorWindow.getRootPanel()); // nastavení obsahu okna na panel z EditorWindow
        frame.setSize(800, 600); // nastavení velikosti okna
        frame.setLocationRelativeTo(null); // umístění okna na střed obrazovky
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // nastavení akce při zavření okna
        frame.setVisible(true); // zobrazení okna
    }
}
