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

        JMenuItem navodItem = new JMenuItem("Návod");
        JMenuItem zkratkyItem = new JMenuItem("Klávesové zkratky");
        JMenuItem infoItem = new JMenuItem("O programu");
        JMenuItem kontaktItem = new JMenuItem("Kontakt");
        JMenuItem licenceItem = new JMenuItem("Licence");

        ukoncitItem.addActionListener(e -> ukoncit());

        navodItem.addActionListener(e -> zobrazNavod());
        zkratkyItem.addActionListener(e -> zobrazZkratky());
        infoItem.addActionListener(e -> zobrazInfoOProgramu());
        kontaktItem.addActionListener(e -> zobrazKontakt());
        licenceItem.addActionListener(e -> zobrazLicenci());

        ukoncitItem.setAccelerator(
                KeyStroke.getKeyStroke("ctrl Q")
        );

        souborMenu.add(ukoncitItem);

        napovedaMenu.add(navodItem);
        napovedaMenu.add(zkratkyItem);
        napovedaMenu.add(infoItem);
        napovedaMenu.add(kontaktItem);
        napovedaMenu.add(licenceItem);

        menuBar.add(souborMenu);
        menuBar.add(napovedaMenu);

        return menuBar;
    }

    private void ukoncit(){
        System.exit(0);
    }

    private void zobrazNavod(){
        JOptionPane.showMessageDialog(rootPanel,
                "1. Pište svůj text do hlavního pole.\n" +
                "2. Použijte nabídku 'Soubor' pro práci se soubory.\n" +
                "3. Klávesové zkratky najdete vedle položek v menu.\n" +
                "4. Informace o programu najdete v nabídce 'Nápověda'.",
                "Návod",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void zobrazZkratky(){
        JOptionPane.showMessageDialog(rootPanel,
                "Ctrl + Q: Ukončit program",
                    "Klávesové zkratky",
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

    private void zobrazKontakt(){
        JOptionPane.showMessageDialog(rootPanel,
                "Autor: shyestfern\n" +
                        "GitHub: https://github.com/shyestfern/mebi",
                "Kontakt",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void zobrazLicenci(){
        JOptionPane.showMessageDialog(rootPanel,
                "Tento program byl vytvořen jako školní projekt.\n" +
                        "Je určen pouze pro vzdělávací účely.",
                "Licence",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
