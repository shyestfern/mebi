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
        JMenu napovedaMenu = new JMenu("Nápověda");

        JMenuItem ukoncitItem = new JMenuItem("Ukončit");
        JMenuItem infoItem = new JMenuItem("O programu");

        ukoncitItem.addActionListener(e -> ukoncit());
        infoItem.addActionListener(e -> zobrazInfo());

        ukoncitItem.setAccelerator(
                KeyStroke.getKeyStroke("ctrl Q")
        );

        souborMenu.add(ukoncitItem);
        napovedaMenu.add(infoItem);

        menuBar.add(souborMenu);
        menuBar.add(napovedaMenu);

        return menuBar;
    }

    private void ukoncit(){
        System.exit(0);
    }

    private void zobrazInfo(){
        JOptionPane.showMessageDialog(rootPanel,
                "mebi\n" +
                        "Verze 1.0\n" +
                        "Textový editor s GUI\n\n" +
                        "Autor: shyestfern\n" +
                        "Rok: 2026\n" +
                        "GitHub: https://github.com/shyestfern/mebi",
                        "O programu",
                        JOptionPane.INFORMATION_MESSAGE
        );
    }
}
