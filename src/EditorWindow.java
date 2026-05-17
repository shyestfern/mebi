import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EditorWindow {
    private JPanel rootPanel; // hlavní panel editoru
    private JScrollPane scrollPane;
    private JTextArea textArea;
    private JPanel editorPanel;
    private JLabel statusBar;
    private JPanel toolbarPanel;
    private JLabel fontLabel;
    private JComboBox fontBox;
    private JLabel velikostLabel;
    private JComboBox velikostBox;
    private JCheckBox zalomitBox;
    private JMenuBar menuBar;

    private static final String FILE_NAME = "dokument.txt";
    private String soucasnySoubor = null;

    private String vybranyFont = "Arial";
    private int vybranaVelikost = 12;


    public JPanel getRootPanel(){ // vrátí hlavní panel pro JFrame
        return rootPanel;
    }

    private void updateFont(){ // aktualizace fontu a velikosti textu
        textArea.setFont(new Font(vybranyFont, Font.PLAIN, vybranaVelikost));
    }

    private void vytvorToolbar(){ // vytvoření toolbaru s výběrem fontu, velikosti a zalamováním řádků
        fontBox.addActionListener(e -> {
            vybranyFont = (String) fontBox.getSelectedItem();
            updateFont();
        });

        velikostBox.addActionListener(e -> {
            vybranaVelikost = Integer.parseInt(velikostBox.getSelectedItem().toString());
            updateFont();
        });

        zalomitBox.setSelected(true);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        zalomitBox.addActionListener(e -> {
            boolean wrap = zalomitBox.isSelected();
            textArea.setLineWrap(wrap);
            textArea.setWrapStyleWord(wrap);
            nastavStatus("Zalomování řádků " + (wrap ? "zapnuto" : "vypnuto"));
        });

        updateFont();
    }

    public EditorWindow(){ // konstruktor, který inicializuje toolbar
        vytvorToolbar();
    }

    public JMenuBar vytvorMenuBar(){ // vytvoření hlavního menu editoru
        menuBar = new JMenuBar();

        // definice hlavních položek menu

        JMenu souborMenu = new JMenu("Soubor");
        JMenu upravyMenu = new JMenu("Úpravy");
        JMenu analyzaMenu = new JMenu("Analýza");
        JMenu barvyMenu = new JMenu("Barvy");
        JMenu napovedaMenu = new JMenu("Nápověda");

        // definice položek pod jednotlivými menu

        // položky pro Soubor

        JMenuItem novyItem = new JMenuItem("Nový");
        JMenuItem otevritItem = new JMenuItem("Otevřít");
        JMenuItem ulozitItem = new JMenuItem("Uložit");
        JMenuItem ulozitJakoItem = new JMenuItem("Uložit jako");
        JMenuItem ukoncitItem = new JMenuItem("Ukončit");

        // položky pro Úpravy

        JMenuItem kopirovatItem = new JMenuItem("Kopírovat");
        JMenuItem vlozitItem = new JMenuItem("Vložit");
        JMenuItem vyjmoutItem = new JMenuItem("Vyjmout");
        JMenuItem najitItem = new JMenuItem("Najít");
        JMenuItem nahraditItem = new JMenuItem("Nahradit");

        // položky pro Analýzu

        JMenuItem analyzaTextuItem = new JMenuItem("Analýza textu");
        JMenuItem analyzaVetItem = new JMenuItem("Analýza vět");
        JMenuItem analyzaSlovItem = new JMenuItem("Analýza slov");
        JMenuItem analyzaZnakuItem = new JMenuItem("Analýza znaků");
        JMenuItem casCteniItem = new JMenuItem("Čas čtení");

        // položky pro Barvy

        JMenuItem barvaMenuItem = new JMenuItem("Barva menu");
        JMenuItem barvaPozadiItem = new JMenuItem("Barva pozadí");
        JMenuItem barvaPoleItem = new JMenuItem("Barva pole");
        JMenuItem barvaPismaItem = new JMenuItem("Barva písma");

        // položky pro Nápovědu

        JMenuItem navodItem = new JMenuItem("Návod");
        JMenuItem zkratkyItem = new JMenuItem("Klávesové zkratky");
        JMenuItem infoItem = new JMenuItem("O programu");
        JMenuItem kontaktItem = new JMenuItem("Kontakt");
        JMenuItem licenceItem = new JMenuItem("Licence");

        // přidání ActionListenerů pro jednotlivé položky menu

        novyItem.addActionListener(e -> novy());
        otevritItem.addActionListener(e -> otevrit());
        ulozitItem.addActionListener(e -> ulozit());
        ulozitJakoItem.addActionListener(e -> ulozitJako());
        ukoncitItem.addActionListener(e -> ukoncit());

        kopirovatItem.addActionListener(e -> kopirovat());
        vlozitItem.addActionListener(e -> vlozit());
        vyjmoutItem.addActionListener(e -> vyjmout());
        najitItem.addActionListener(e -> najit());
        nahraditItem.addActionListener(e -> nahradit());

        analyzaTextuItem.addActionListener(e -> analyzaTextu());
        analyzaVetItem.addActionListener(e -> analyzaVet());
        analyzaSlovItem.addActionListener(e -> analyzaSlov());
        analyzaZnakuItem.addActionListener(e -> analyzaZnaku());
        casCteniItem.addActionListener(e -> odhadCasuCteni());

        barvaMenuItem.addActionListener(e -> nastavBarvuMenu());
        barvaPozadiItem.addActionListener(e -> nastavBarvuPozadi());
        barvaPoleItem.addActionListener(e -> nastavBarvuPole());
        barvaPismaItem.addActionListener(e -> nastavBarvuPisma());

        navodItem.addActionListener(e -> zobrazNavod());
        zkratkyItem.addActionListener(e -> zobrazZkratky());
        infoItem.addActionListener(e -> zobrazInfoOProgramu());
        kontaktItem.addActionListener(e -> zobrazKontakt());
        licenceItem.addActionListener(e -> zobrazLicenci());

        // nastavení klávesových zkratek pro položky menu

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
        kopirovatItem.setAccelerator(
                KeyStroke.getKeyStroke("ctrl C")
        );
        vlozitItem.setAccelerator(
                KeyStroke.getKeyStroke("ctrl V")
        );
        vyjmoutItem.setAccelerator(
                KeyStroke.getKeyStroke("ctrl X")
        );
        najitItem.setAccelerator(
                KeyStroke.getKeyStroke("ctrl F")
        );
        nahraditItem.setAccelerator(
                KeyStroke.getKeyStroke("ctrl R")
        );

        // přidání položek do jednotlivých menu

        souborMenu.add(novyItem);
        souborMenu.add(otevritItem);
        souborMenu.add(ulozitItem);
        souborMenu.add(ulozitJakoItem);
        souborMenu.add(ukoncitItem);

        upravyMenu.add(kopirovatItem);
        upravyMenu.add(vlozitItem);
        upravyMenu.add(vyjmoutItem);
        upravyMenu.add(najitItem);
        upravyMenu.add(nahraditItem);

        analyzaMenu.add(analyzaTextuItem);
        analyzaMenu.add(analyzaVetItem);
        analyzaMenu.add(analyzaSlovItem);
        analyzaMenu.add(analyzaZnakuItem);
        analyzaMenu.add(casCteniItem);

        barvyMenu.add(barvaMenuItem);
        barvyMenu.add(barvaPozadiItem);
        barvyMenu.add(barvaPoleItem);
        barvyMenu.add(barvaPismaItem);

        napovedaMenu.add(navodItem);
        napovedaMenu.add(zkratkyItem);
        napovedaMenu.add(infoItem);
        napovedaMenu.add(kontaktItem);
        napovedaMenu.add(licenceItem);

        // přidání hlavních menu do menu baru

        menuBar.add(souborMenu);
        menuBar.add(upravyMenu);
        menuBar.add(analyzaMenu);
        menuBar.add(barvyMenu);
        menuBar.add(napovedaMenu);

        return menuBar;
    }

    private void nastavStatus(String text){ // aktualizace textu ve stavovém řádku
        statusBar.setText(text);
    }

    private void novy(){ // vytvoření nového souboru - zeptá se na uložení změn
        if(!textArea.getText().isEmpty()){
            int zvolenaMoznost = JOptionPane.showConfirmDialog(
                    rootPanel,
                    "Chcete uložit změny?",
                    "Nový soubor",
                    JOptionPane.YES_NO_CANCEL_OPTION
            );

            if(zvolenaMoznost == JOptionPane.CANCEL_OPTION){
                return;
            }

            if(zvolenaMoznost == JOptionPane.YES_OPTION){
                ulozit();
            }

            textArea.setText("");
            soucasnySoubor = null;
            nastavStatus("Vytvořen nový soubor");
        }
        else{
            textArea.setText("");
            soucasnySoubor = null;
            nastavStatus("Vytvořen nový soubor");
        }
    }

    private void otevrit(){ // otevření souboru - zeptá se na název souboru
        String nazevSouboru = JOptionPane.showInputDialog(
                rootPanel,
                "Zadejte název souboru k otevření:"
        );

        if(nazevSouboru == null || nazevSouboru.trim().isEmpty()){
            return;
        }

        if(!nazevSouboru.endsWith(".txt")){
            nazevSouboru += ".txt";
        }

        try(Scanner scanner = new Scanner(new File(nazevSouboru))){
            textArea.setText(""); // vyčistíme textové pole před načtením souboru
            while(scanner.hasNextLine()){
                textArea.append(scanner.nextLine() + "\n"); // postupně přidáváme každou řádku ze souboru
            }

            soucasnySoubor = nazevSouboru; // uložíme aktuální soubor

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(
                        rootPanel,
                "Soubor neexistuje!"
            );
        }

        nastavStatus("Načten soubor: " + nazevSouboru);
    }

    private void ulozit(){ // uložení souboru - nezadává se název
        if(soucasnySoubor == null){
            soucasnySoubor = FILE_NAME;
        }

        try(FileWriter fw = new FileWriter(soucasnySoubor, false)){
            fw.write(textArea.getText()); // zapíšeme celý obsah textového pole do souboru
            nastavStatus("Uloženo do " + soucasnySoubor);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                        rootPanel,
                "Chyba při ukládání souboru!"
            );
        }
    }

    private void ulozitJako(){ // uložení souboru - zeptá se na název souboru
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
            soucasnySoubor = nazevSouboru;
            nastavStatus("Uloženo do " + nazevSouboru);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    rootPanel,
                    "Chyba při ukládání souboru!"
            );
        }
    }

    private void ukoncit(){ // ukončení programu
        System.exit(0);
    }

    private void kopirovat(){ // kopírování textu do schránky
        textArea.copy();
        nastavStatus("Text zkopírován do schránky");
    }

    private void vlozit(){ // vložení textu ze schránky
        textArea.paste();
        nastavStatus("Text vložen ze schránky");
    }

    private void vyjmout(){ // vyjmutí textu do schránky
        textArea.cut();
        nastavStatus("Text vyjmut do schránky");
    }

    private void najit(){ // nalezení počtu výskytů zadaného textu
        String hledanyText = JOptionPane.showInputDialog(
                rootPanel,
                "Zadejte hledaný text:"
        );

        if(hledanyText == null || hledanyText.trim().isEmpty()){
            return;
        }

        hledanyText = hledanyText.trim().toLowerCase();

        String text = textArea.getText().toLowerCase();

        text = text.replace("\n", " "); // všechny nové řádky nahradíme mezerami pro správné počítání

        int pocet = text.split(hledanyText, -1).length - 1; // spočítáme počet výskytů hledaného textu

        JOptionPane.showMessageDialog(
                rootPanel,
                "Počet nalezení: " + pocet,
                "Najít",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void nahradit(){ // nahrazení zadaného textu jiným textem
        String hledanyText = JOptionPane.showInputDialog(
                rootPanel,
                "Zadejte text, který chcete nahradit:"
        );

        if(hledanyText == null || hledanyText.trim().isEmpty()){
            return;
        }

        String novyText = JOptionPane.showInputDialog(
                rootPanel,
                "Zadejte nový text:"
        );

        if(novyText == null){
            return;
        }

        String puvodniText = textArea.getText();

        if(!puvodniText.contains(hledanyText)){
            nastavStatus("Text \"" + hledanyText + "\" nebyl nalezen.");
            return;
        }

        String novyObsah = puvodniText.replace(hledanyText, novyText); // nahrazení všech výskytů hledaného textu novým

        textArea.setText(novyObsah);

        nastavStatus("Všechny výskyty \"" + hledanyText + "\" byly nahrazeny \"" + novyText + "\".");
    }

    private void analyzaTextu(){ // počet znaků, řádků a slov
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

    private void analyzaVet(){ // počet vět, nejdelší a nejkratší věta
        String text = textArea.getText().trim();

        if(text.isEmpty()){
            JOptionPane.showMessageDialog(
                    rootPanel,
                    "Žádné věty v textu."
            );
            return;
        }

        String[] vety = text.split("[.!?]+"); // rozdělíme text podle koncových interpunkcí

        int pocetVet = 0;

        String nejdelsi = "";
        String nejkratsi = "";

        for(String veta : vety){
            veta = veta.trim(); // odstraníme přebytečné mezery kolem věty

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

    private void analyzaSlov(){ // počet slov, nejdelší a nejkratší slovo, průměrná délka slova, součet znaků ve slovech
        String text = textArea.getText();

        String[] slova = text.trim().isEmpty()
                ? new String[0]
                : text.trim().split("\\s+"); // rozdělíme text na slova podle mezer

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

    private void analyzaZnaku(){ // počet velkých písmen, malých písmen a mezer
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

    private void odhadCasuCteni(){ // na základě počtu slov - pruměrná rychlost 200 slov za minutu
        String text = textArea.getText();

        int pocetSlov = text.trim().isEmpty()
                ? 0
                : text.trim().split("\\s+").length;

        int celkemSekund = (int)((double) pocetSlov / 200 * 60); // přepočítáme počet slov na sekundy (200 slov/min)
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

    private void nastavBarvuMenu(){ // nastavení barvy menu
        Color color = JColorChooser.showDialog(
                rootPanel,
                "Vyberte barvu menu",
                menuBar.getBackground()
        );

        if(color != null){
            menuBar.setBackground(color);

            for(int i = 0; i < menuBar.getMenuCount(); i++){
                JMenu menu = menuBar.getMenu(i);
                if(menu != null){
                    menu.setBackground(color); // změníme barvu jednotlivých položek menu

                    for(int j = 0; j < menu.getItemCount(); j++){
                        JMenuItem item = menu.getItem(j);
                        if(item != null){
                            item.setBackground(color); // změníme barvu podmenu položek
                        }
                    }
                }
            }
        }
    }

    private void nastavBarvuPozadi(){ // nastavení barvy pozadí
        Color color = JColorChooser.showDialog(
                rootPanel,
                "Vyberte barvu pozadí",
                rootPanel.getBackground()
        );

        if(color != null){
            rootPanel.setBackground(color);
            toolbarPanel.setBackground(color);
            zalomitBox.setBackground(color);
            UIManager.put("OptionPane.background", color); // změníme barvu pozadí pro dialogová okna
        }
    }

    private void nastavBarvuPole(){ // nastavení barvy textového pole
        Color color = JColorChooser.showDialog(
                rootPanel,
                "Vyberte barvu textového pole",
                textArea.getBackground()
        );

        if(color != null){
            textArea.setBackground(color);
            fontBox.setBackground(color);
            velikostBox.setBackground(color);
            UIManager.put("Panel.background", color); // pozadí pro ostatní panely
        }
    }

    private void nastavBarvuPisma(){ // nastavení barvy písma
        Color color = JColorChooser.showDialog(
                rootPanel,
                "Vyberte barvu písma",
                textArea.getForeground()
        );

        if(color != null){
            textArea.setForeground(color);

            statusBar.setForeground(color);
            fontLabel.setForeground(color);
            velikostLabel.setForeground(color);
            fontBox.setForeground(color);
            velikostBox.setForeground(color);

            UIManager.put("OptionPane.messageForeground", color);

            menuBar.setForeground(color);

            for(int i = 0; i < menuBar.getMenuCount(); i++){
                JMenu menu = menuBar.getMenu(i);
                if(menu != null){
                    menu.setForeground(color); // barva textu jednotlivých menu

                    for(int j = 0; j < menu.getItemCount(); j++){
                        JMenuItem item = menu.getItem(j);
                        if(item != null){
                            item.setForeground(color); // barva textu jednotlivých podmenu položek
                        }
                    }
                }
            }
        }
    }

    private void zobrazNavod(){ // zobrazení návodu k použití programu
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

    private void zobrazZkratky(){ // zobrazení seznamu klávesových zkratek
        JOptionPane.showMessageDialog(
                    rootPanel,
                "Ctrl + N: Nový soubor\n" +
                    "Ctrl + O: Otevřít soubor\n" +
                    "Ctrl + S: Uložit práci\n" +
                    "Ctrl + Shift + S: Uložit jako...\n" +
                    "Ctrl + Q: Ukončit program\n" +
                    "Ctrl + C: Kopírovat\n" +
                    "Ctrl + V: Vložit\n" +
                    "Ctrl + X: Vyjmout\n" +
                    "Ctrl + F: Najít text\n" +
                    "Ctrl + R: Nahradit text",
                    "Klávesové zkratky",
                        JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void zobrazInfoOProgramu(){ // zobrazení informací o programu
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

    private void zobrazKontakt(){ // zobrazení kontaktních informací autora programu
        JOptionPane.showMessageDialog(
                rootPanel,
                "Autor: shyestfern\n" +
                        "GitHub: https://github.com/shyestfern/mebi",
                "Kontakt",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void zobrazLicenci(){ // zobrazení informace o licenci programu
        JOptionPane.showMessageDialog(
                rootPanel,
                "Tento program byl vytvořen jako školní projekt.\n" +
                        "Je určen pouze pro vzdělávací účely.",
                "Licence",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
