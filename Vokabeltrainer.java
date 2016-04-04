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
        String[] splitted;
        String gerEingabe;
        boolean correct;
        int beantwortet = 0;
        int fehler = 0;
        double ergebnis = 0;
        int note = 0;

        //Deklaration + Initialisierung
        
        fr.close(); // Filereader muss auf 0 gesetzt werden
        fr = new FileReader(vokFile.getSelectedFile()); //Filereader wird wieder gestartet
        br = new BufferedReader(fr);

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
                    ++beantwortet;
                } else {
                    System.out.println("Das war leider Falsch. Bitte versuche es noch ein mal.");
                    ++fehler;
                }
                
            }
        ergebnis = (fehler / beantwortet);
//        
        if (ergebnis <= 1 && ergebnis>= 0.9) note = 6;
        if (ergebnis < 0.9 && ergebnis >=0.8 ) note = 5;
        if (ergebnis < 0.8 && ergebnis >= 0.66) note = 4;
        if (ergebnis < 0.66 && ergebnis >= 0.50) note = 3;
        if (ergebnis < 0.50 && ergebnis >= 0.2) note = 2;
        if (ergebnis < 0.19 && ergebnis >= 0) note = 1;
        
        

        }
        
        System.out.println("Das war alles. Gut gemacht.");
        System.out.println("Deine Note: "+ note);
        System.out.println("Beantwortet: "+ beantwortet);
        System.out.println("Fehler: "+ fehler);
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
