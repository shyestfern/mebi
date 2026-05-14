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

        JMenuItem zkratkyItem = new JMenuItem("Klávesové zkratky");
        JMenuItem kontaktItem = new JMenuItem("Kontakt");
        JMenuItem infoItem = new JMenuItem("O programu");

        ukoncitItem.addActionListener(e -> ukoncit());

        zkratkyItem.addActionListener(e -> zobrazZkratky());
        kontaktItem.addActionListener(e -> zobrazKontakt());
        infoItem.addActionListener(e -> zobrazInfoOProgramu());

        ukoncitItem.setAccelerator(
                KeyStroke.getKeyStroke("ctrl Q")
        );

        souborMenu.add(ukoncitItem);

        napovedaMenu.add(zkratkyItem);
        napovedaMenu.add(kontaktItem);
        napovedaMenu.add(infoItem);

        menuBar.add(souborMenu);
        menuBar.add(napovedaMenu);

        return menuBar;
    }

    private void ukoncit(){
        System.exit(0);
    }

    private void zobrazZkratky(){
        JOptionPane.showMessageDialog(rootPanel,
                "Ctrl + Q: Ukončit program",
                    "Klávesové zkratky",
                        JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void zobrazKontakt(){
        JOptionPane.showMessageDialog(rootPanel,
                "Autor: shyestfern\n" +
                        "GitHub: https://github.com/shyestfern/mebi",
                        "Kontakt",
                        JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void zobrazInfoOProgramu(){
        JOptionPane.showMessageDialog(rootPanel,
                "mebi\n" +
                        "Verze 1.0\n" +
                        "Textový editor s GUI\n\n" +
                        "Rok: 2026",
                        "O programu",
                        JOptionPane.INFORMATION_MESSAGE
        );
    }
}
