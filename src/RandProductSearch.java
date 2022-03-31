import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import static java.nio.file.StandardOpenOption.CREATE;

public class RandProductSearch extends JFrame {
    JPanel mainPnl, topPnl, botPnl, namePnl;
    JButton enter;
    static JTextField namef;
    JLabel nameL;
    static JTextArea displayTxt;

    private static ArrayList<String> fileLines = new ArrayList<>();

    public RandProductSearch(){
        mainPnl = new JPanel();
        mainPnl.setLayout(new GridLayout(2,1));

        createTopPnl();
        mainPnl.add(topPnl);

        createBotPnl();
        mainPnl.add(botPnl);

        add(mainPnl);
        setSize(500,600);
        setVisible(true);
    }

    public void createTopPnl(){
        topPnl = new JPanel();
        topPnl.setLayout(new GridLayout(2,1));

        namePnl = new JPanel();

        nameL = new JLabel("Name: ");
        namef = new JTextField(35);
        namePnl.add(nameL);
        namePnl.add(namef);

        enter = new JButton("Search");
        enter.addActionListener((ActionEvent ae) ->
            {
                try {
                    getFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                searchFile();
            }
        );
        topPnl.add(namePnl);
        topPnl.add(enter);
    }

    public void createBotPnl(){
        botPnl = new JPanel();
        displayTxt = new JTextArea("",20,130);
        botPnl.add(displayTxt);
    }

    public static void getFile() throws IOException {
        String filePath = "ProductData.txt";
        //Instantiating the File class
        File file = new File(filePath);
        //Instantiating the StringBuffer
        StringBuffer buffer = new StringBuffer();
        //instantiating the RandomAccessFile
        RandomAccessFile raFile = new RandomAccessFile(file, "rw");
        //Reading each line using the readLine() method
        while(raFile.getFilePointer() < raFile.length()) {
//            buffer.append(raFile.readLine()+System.lineSeparator());
            fileLines.add(raFile.readLine()+System.lineSeparator());
        }
    }

    public static void searchFile(){
        displayTxt.selectAll();
        displayTxt.replaceSelection("");

        String input = namef.getText();
        Stream<String> s = fileLines.stream().filter(s1 -> s1.contains(input));
        s.forEach(s1 -> displayTxt.append(s1 + "\n"));
    }
}
