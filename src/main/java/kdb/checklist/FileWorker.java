package kdb.checklist;

import java.io.*;
import java.util.ArrayList;
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
        writer.append(System.lineSeparator());
        writer.append(newItem);

        writer.close();
    }
}
