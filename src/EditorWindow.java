import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EditorWindow {
    private JPanel rootPanel;
    private JScrollPane scrollPane;
    private JTextArea textArea;
    private JPanel editorPanel;

    private static final String FILE_NAME = "dokument.txt";

    public JPanel getRootPanel(){
        return rootPanel;
    }

    public JMenuBar vytvorMenuBar(){
        JMenuBar menuBar = new JMenuBar();

        JMenu souborMenu = new JMenu("Soubor");
        JMenu analyzaMenu = new JMenu("Analýza");
        JMenu napovedaMenu = new JMenu("Nápověda");

        JMenuItem novyItem = new JMenuItem("Nový");
        JMenuItem otevritItem = new JMenuItem("Otevřít");
        JMenuItem ulozitItem = new JMenuItem("Uložit");
        JMenuItem ulozitJakoItem = new JMenuItem("Uložit jako");
        JMenuItem ukoncitItem = new JMenuItem("Ukončit");

        JMenuItem analyzaTextuItem = new JMenuItem("Analýza textu");
        JMenuItem analyzaVetItem = new JMenuItem("Analýza vět");
        JMenuItem analyzaSlovItem = new JMenuItem("Analýza slov");
        JMenuItem analyzaZnakuItem = new JMenuItem("Analýza znaků");
        JMenuItem casCteniItem = new JMenuItem("Čas čtení");

        JMenuItem navodItem = new JMenuItem("Návod");
        JMenuItem zkratkyItem = new JMenuItem("Klávesové zkratky");
        JMenuItem infoItem = new JMenuItem("O programu");
        JMenuItem kontaktItem = new JMenuItem("Kontakt");
        JMenuItem licenceItem = new JMenuItem("Licence");

        novyItem.addActionListener(e -> novy());
        otevritItem.addActionListener(e -> otevrit());
        ulozitItem.addActionListener(e -> ulozit());
        ulozitJakoItem.addActionListener(e -> ulozitJako());
        ukoncitItem.addActionListener(e -> ukoncit());

        analyzaTextuItem.addActionListener(e -> analyzaTextu());
        analyzaVetItem.addActionListener(e -> analyzaVet());
        analyzaSlovItem.addActionListener(e -> analyzaSlov());
        analyzaZnakuItem.addActionListener(e -> analyzaZnaku());
        casCteniItem.addActionListener(e -> odhadCasuCteni());

        navodItem.addActionListener(e -> zobrazNavod());
        zkratkyItem.addActionListener(e -> zobrazZkratky());
        infoItem.addActionListener(e -> zobrazInfoOProgramu());
        kontaktItem.addActionListener(e -> zobrazKontakt());
        licenceItem.addActionListener(e -> zobrazLicenci());

        novyItem.setAccelerator(
                KeyStroke.getKeyStroke("ctrl N")
        );
        otevritItem.setAccelerator(
                KeyStroke.getKeyStroke("ctrl O")
        );
        ulozitItem.setAccelerator(
                KeyStroke.getKeyStroke("ctrl S")
        );
        ulozitJakoItem.setAccelerator(
                KeyStroke.getKeyStroke("ctrl shift S")
        );
        ukoncitItem.setAccelerator(
                KeyStroke.getKeyStroke("ctrl Q")
        );

        souborMenu.add(novyItem);
        souborMenu.add(otevritItem);
        souborMenu.add(ulozitItem);
        souborMenu.add(ulozitJakoItem);
        souborMenu.add(ukoncitItem);

        analyzaMenu.add(analyzaTextuItem);
        analyzaMenu.add(analyzaVetItem);
        analyzaMenu.add(analyzaSlovItem);
        analyzaMenu.add(analyzaZnakuItem);
        analyzaMenu.add(casCteniItem);

        napovedaMenu.add(navodItem);
        napovedaMenu.add(zkratkyItem);
        napovedaMenu.add(infoItem);
        napovedaMenu.add(kontaktItem);
        napovedaMenu.add(licenceItem);

        menuBar.add(souborMenu);
        menuBar.add(analyzaMenu);
        menuBar.add(napovedaMenu);

        return menuBar;
    }

    private void novy(){
        if(!textArea.getText().isEmpty()){
            int zvolenaMoznost = JOptionPane.showConfirmDialog(
                    rootPanel,
                    "Chcete uložit změny?",
                    "Nový soubor",
                    JOptionPane.YES_NO_CANCEL_OPTION
            );

            if(zvolenaMoznost == JOptionPane.YES_OPTION){
                ulozit();
                textArea.setText("");
            }
            else if(zvolenaMoznost == JOptionPane.NO_OPTION){
                textArea.setText("");
            }
        }
        else{
            textArea.setText("");
        }
    }

    private void otevrit(){
        try(Scanner scanner = new Scanner(new File(FILE_NAME))){
            textArea.setText("");
            while(scanner.hasNextLine()){
                textArea.append(scanner.nextLine() + "\n");
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(
                        rootPanel,
                "Chyba při načítání souboru!"
            );
        }
    }

    private void ulozit(){
        try(FileWriter fw = new FileWriter(FILE_NAME, false)){
            fw.write(textArea.getText());
            JOptionPane.showMessageDialog(
                        rootPanel,
                "Uloženo do " + FILE_NAME
            );
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                        rootPanel,
                "Chyba při ukládání souboru!"
            );
        }
    }

    private void ulozitJako(){
        String nazevSouboru = JOptionPane.showInputDialog(
                    rootPanel,
          "Zadejte název souboru:"
        );

        if(nazevSouboru == null || nazevSouboru.trim().isEmpty()){
            return;
        }

        if(!nazevSouboru.endsWith(".txt")){
            nazevSouboru += ".txt";
        }

        try(FileWriter fw = new FileWriter(nazevSouboru, false)){
            fw.write(textArea.getText());

            JOptionPane.showMessageDialog(
                    rootPanel,
                    "Uloženo do " + nazevSouboru
            );
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    rootPanel,
                    "Chyba při ukládání souboru!"
            );
        }
    }

    private void ukoncit(){
        System.exit(0);
    }

    private void analyzaTextu(){
        String text = textArea.getText();

        int pocetZnaku = text.length();

        int pocetRadku = text.isEmpty()
                ? 0
                : text.split("\n").length;

        int pocetSlov = text.isEmpty()
                ? 0
                : text.trim().split("\\s+").length;

        JOptionPane.showMessageDialog(
                rootPanel,
                "Počet znaků: " + pocetZnaku + "\n" +
                "Počet řádků: " + pocetRadku + "\n" +
                "Počet slov: " + pocetSlov,
                "Analýza textu",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void analyzaVet(){
        String text = textArea.getText().trim();

        if(text.isEmpty()){
            JOptionPane.showMessageDialog(
                    rootPanel,
                    "Žádné věty v textu."
            );
            return;
        }

        String[] vety = text.split("[.!?]+");

        int pocetVet = 0;

        String nejdelsi = "";
        String nejkratsi = "";

        for(String veta : vety){
            veta = veta.trim();

            if(veta.isEmpty()){
                continue;
            }

            pocetVet++;

            if(nejdelsi.isEmpty() || veta.length() > nejdelsi.length()){
                nejdelsi = veta;
            }
            if(nejkratsi.isEmpty() || veta.length() < nejkratsi.length()){
                nejkratsi = veta;
            }
        }

        JOptionPane.showMessageDialog(
                rootPanel,
                "Počet vět: " + pocetVet + "\n" +
                        "Nejdelší věta: " + nejdelsi + "\n" +
                        "Nejkratší věta: " + nejkratsi + "\n",
                "Analýza vět",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void analyzaSlov(){
        String text = textArea.getText();

        String[] slova = text.trim().isEmpty()
                ? new String[0]
                : text.trim().split("\\s+");

        if(slova.length == 0){
            JOptionPane.showMessageDialog(
                    rootPanel,
                    "Žádná slova v textu."
            );
            return;
        }

        String nejdelsi = slova[0];
        String nejkratsi = slova[0];
        int celkemZnaku = 0;

        for(String s : slova){
            if(s.length() > nejdelsi.length()){
                nejdelsi = s;
            }
            if(s.length() < nejkratsi.length()){
                nejkratsi = s;
            }
            celkemZnaku += s.length();
        }

        double prumer = (double) celkemZnaku / slova.length;

        JOptionPane.showMessageDialog(
                rootPanel,
                "Nejdelší slovo: " + nejdelsi + "\n" +
                        "Nejkratší slovo: " + nejkratsi + "\n" +
                        "Průměrná délka slova: " + String.format("%.2f", prumer) + "\n" +
                        "Součet znaků ve slovech: " + celkemZnaku,
                "Analýza slov",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void analyzaZnaku(){
        String text = textArea.getText();

        int velkaPismena = 0;
        int malaPismena = 0;
        int mezery = 0;

        for(int i = 0; i < text.length(); i++){
            char znak = text.charAt(i);

            if(Character.isUpperCase(znak)){
                velkaPismena++;
            } else if (Character.isLowerCase(znak)){
                malaPismena++;
            } else if (Character.isWhitespace(znak)){
                mezery++;
            }
        }

        JOptionPane.showMessageDialog(
                rootPanel,
                "Velká písmena: " + velkaPismena + "\n" +
                        "Malá písmena: " + malaPismena + "\n" +
                        "Mezery: " + mezery,
                "Analýza znaků",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void odhadCasuCteni(){
        String text = textArea.getText();

        int pocetSlov = text.trim().isEmpty()
                ? 0
                : text.trim().split("\\s+").length;

        int celkemSekund = (int)((double) pocetSlov / 200 * 60);
        int minuty = celkemSekund / 60;
        int sekundy = celkemSekund % 60;

        JOptionPane.showMessageDialog(
                rootPanel,
                "Odhadovaný čas čtení: " +
                            minuty + " minut " +
                            sekundy + " sekund",
                "Čas čtení",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void zobrazNavod(){
        JOptionPane.showMessageDialog(
                rootPanel,
                "1. Pište svůj text do hlavního pole.\n" +
                "2. Použijte nabídku 'Soubor' pro práci se soubory.\n" +
                "3. Klávesové zkratky najdete vedle položek v menu.\n" +
                "4. Informace o programu najdete v nabídce 'Nápověda'.",
                "Návod",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void zobrazZkratky(){
        JOptionPane.showMessageDialog(
                    rootPanel,
                "Ctrl + N: Nový soubor\n" +
                    "Ctrl + O: Otevřít soubor\n" +
                    "Ctrl + S: Uložit práci\n" +
                    "Ctrl + Shift + S: Uložit jako...\n" +
                    "Ctrl + Q: Ukončit program",
                    "Klávesové zkratky",
                        JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void zobrazInfoOProgramu(){
        JOptionPane.showMessageDialog(
                        rootPanel,
                "mebi\n" +
                        "Verze 1.0\n" +
                        "Textový editor s GUI\n\n" +
                        "Rok: 2026",
                        "O programu",
                        JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void zobrazKontakt(){
        JOptionPane.showMessageDialog(
                rootPanel,
                "Autor: shyestfern\n" +
                        "GitHub: https://github.com/shyestfern/mebi",
                "Kontakt",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void zobrazLicenci(){
        JOptionPane.showMessageDialog(
                rootPanel,
                "Tento program byl vytvořen jako školní projekt.\n" +
                        "Je určen pouze pro vzdělávací účely.",
                "Licence",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
