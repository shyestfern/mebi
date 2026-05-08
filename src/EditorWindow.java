import javax.swing.*;

public class EditorWindow {
    private JPanel rootPanel;
    private JScrollPane scrollPane;
    private JTextArea textArea;
    private JPanel editorPanel;

    public JPanel getRootPanel(){
        return rootPanel;
    }

    public JMenuBar vytvorMenuBar(){
        JMenuBar menuBar = new JMenuBar();

        JMenu souborMenu = new JMenu("Soubor");

        JMenuItem ukoncitItem = new JMenuItem("Ukončit");

        ukoncitItem.addActionListener(e -> ukoncit());

        ukoncitItem.setAccelerator(
                KeyStroke.getKeyStroke("ctrl Q")
        );

        souborMenu.add(ukoncitItem);

        menuBar.add(souborMenu);

        return menuBar;
    }

    private void ukoncit(){
        System.exit(0);
    }
}
