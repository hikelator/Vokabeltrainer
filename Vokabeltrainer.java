package vokabeltrainer;

/**
 * Users/hmedyan/NetBeansProjects/Vokabeltrainer/src/vokabeltrainer/Vokabel.txt
 *
 * @author hmedyan
 */
import java.io.*;
import java.util.Random;
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

        for (int i = 0; (zeile = br.readLine()) != null; i++) { //i wird als Variable benutzt die immer mitwächst. Solange i nicht leer ist, wird +1 addiert. ist die nächste Zeile leer, wird die Operation abgebrochen. Ohne diese addition würde der erste Wert bei jedem durchlauf die Stelle 0 überschrieben.
            splitted = zeile.split(";");                     //In jeder Zeile wird am ; die Liste gespalten
            eng[i] = splitted[0];                          //linke seite bekommt den Wert 0
            ger[i] = splitted[1];                          //rechte Seinte bekommt den Wert 1
        }

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

        for (int j = 0; j < eng.length; j++) {

            correct = false;
            while (!correct) {

                System.out.print(eng[j] + " bedeutet: ");

                gerEingabe = vokabel.nextLine(); //nextLine betrachtet den ganzen folgenden satz. .next nur das erste Wort

                if (gerEingabe.equals("Lösung bitte")){
                    System.out.println(ger[j]);
                }
                
                else if (gerEingabe.equals("please let me out")) {

                    System.out.println("Bis zum nächsten Mal.");
                    System.exit(0);         //Programm wird beendet bevor die nächste Vokabel kommt.

                } else if (gerEingabe.equals(ger[j])) {
                    System.out.println("Das ist Korrekt. Auf zur nächsten Aufgabe.");
                    correct = true;
                } else {
                    System.out.println("Das war leider Falsch. Bitte versuche es noch ein mal.");
                }
            }

        }
        System.out.println("Das war alles. Gut gemacht.");
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
//    }
//public static int getRandom(int[] array) {
//    int rnd = new Random().nextInt(array.length);
//    return array[rnd];
//}
}
