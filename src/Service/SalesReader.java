package Service;

import java.io.*;
import java.util.*;

public class SalesReader {

    public static List<String> readFile(String filename) {
        List<String> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca file: " + e.getMessage());
        }

        return list;
    }
}
