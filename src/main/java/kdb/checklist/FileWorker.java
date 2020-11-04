package kdb.checklist;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;

public class FileWorker {
    public static List<String> readFile(String path) throws IOException {
        List<String> items = new ArrayList<>();
        File myObj = new File(path);
        try {
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                items.add(data);

                //     System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            if (!myObj.createNewFile())
                return null;
        }
        return items;
    }

    public static void writeFile(String path, String newItem) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
        newItem = newItem.replace(System.lineSeparator(), " ").replace("\r", " ").replace("\n", " ");
        writer.append(newItem + System.lineSeparator());

        writer.close();
    }

    public static void writeItemsFile(String textpath, Enumeration elements) throws IOException {
        Path path = Paths.get(textpath);
        BufferedWriter writer = Files.newBufferedWriter(path , StandardOpenOption.TRUNCATE_EXISTING);//new BufferedWriter(new FileWriter(path, false));
        while (elements.hasMoreElements()) {
            String newItem = (String) elements.nextElement();
            //items.forEach(newItem -> {
            newItem = newItem.replace(System.lineSeparator(), " ").replace("\r", " ").replace("\n", " ");
            try {
                writer.append(newItem + System.lineSeparator());
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        writer.close();
    }
}
