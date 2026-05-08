import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("mebi");
        EditorWindow editorWindow = new EditorWindow();
        frame.setJMenuBar(editorWindow.vytvorMenuBar());
        frame.setContentPane(editorWindow.getRootPanel());
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
