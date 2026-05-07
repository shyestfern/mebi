import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("mebi");
        EditorWindow editorWindow = new EditorWindow();
        frame.setContentPane(editorWindow.getRootPanel());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
