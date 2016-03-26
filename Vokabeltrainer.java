package vokabeltrainer;

/**
 * Users/hmedyan/NetBeansProjects/Vokabeltrainer/src/vokabeltrainer/Vokabel.txt
 *
 * @author hmedyan
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;

public class Vokabeltrainer extends JFrame {

    public static void main(String[] args) throws IOException {

//  vokabeltrainerGui();
        JFileChooser vokFile = new JFileChooser(); //Filechooser wird integriert.
        vokFile.setCurrentDirectory(new java.io.File("/Users/hmedyan/NetBeansProjects/Vokabeltrainer/src/vokabeltrainer")); //standard dir für das öffnen des FileChooser
        vokFile.setDialogTitle("Vokabelcontainer Wählen");
        vokFile.showOpenDialog(null);

        FileReader fr = new FileReader(vokFile.getSelectedFile());//AusgewählteDatei wird in den FileReader geladen
        BufferedReader br = new BufferedReader(fr);

        //Deklaration
        String[] eng;
        String[] ger;
        String[] splitted;
        String gerEingabe;
        boolean correct;

        //Deklaration + Initialisierung
        int rows = 0;

        // solange line nicht leer zählt die Schleifen
        while (br.readLine() != null) {
            rows++;
        }
        fr.close(); // Filereader muss auf 0 gesetzt werden
        fr = new FileReader(vokFile.getSelectedFile()); //Filereader wird wieder gestartet
        br = new BufferedReader(fr);

        eng = new String[rows]; //Variable für englisch wird geschaffen
        ger = new String[rows]; //Variable für deutsch wird geschaffen

        String zeile;
        List<Translation> list = new ArrayList<>();
        while ((zeile = br.readLine()) != null) {
            splitted = zeile.split(";");
            list.add(new Translation(splitted[0], splitted[1]));
        }
        Collections.shuffle(list);

        br.close();

//    Vokabeltrainer gui = new Vokabeltrainer();
//
//            gui.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
//            gui.setSize(400,200);
//            gui.setVisible(true);
//            gui.setTitle("Vokabeltrainer");
//getRandom();
// Nutzerinteratkion
        System.out.print("Hallo Nutzer, viel Spaß beim ");
        System.out.println(vokFile.getSelectedFile().getName().substring(8));

        Scanner vokabel = new Scanner(System.in);

        for (int j = 0; j < list.size(); j++) {

            correct = false;
            while (!correct) {

                Translation t = list.get(j);
                System.out.print(t.originalWord + " bedeutet: ");

                gerEingabe = vokabel.nextLine(); //nextLine betrachtet den ganzen folgenden satz. .next nur das erste Wort

                if (gerEingabe.equals("Lösung bitte")) {
                    System.out.println(t.translatedWord);
                } else if (gerEingabe.equals("please let me out")) {

                    System.out.println("Bis zum nächsten Mal.");
                    System.exit(0);         //Programm wird beendet bevor die nächste Vokabel kommt.

                } else if (gerEingabe.equals(t.translatedWord)) {
                    System.out.println("Das ist Korrekt. Auf zur nächsten Aufgabe.");
                    correct = true;
                } else {
                    System.out.println("Das war leider Falsch. Bitte versuche es noch ein mal.");
                }
            }

        }
        System.out.println("Das war alles. Gut gemacht.");
    }

    public static class Translation {

        public final String originalWord;
        public final String translatedWord;

        public Translation(String original, String translated) {
            this.originalWord = original;
            this.translatedWord = translated;
        }
    }

//    public vokabeltrainerGui() {
//    private JLabel label;
//    private JButton button;
//    private JTextField textfield;
//                setLayout(new FlowLayout());   
//
//        label = new JLabel ("Vokabeltrainer");
//        add(label);
//        
//        textfield = new JTextField(15);
//        add(textfield);
//        
//        button = new JButton("Überprüfen");
//        add(button);
}
