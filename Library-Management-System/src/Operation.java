
import java.io.*;
import java.util.*;
import java.text.*;

public class Operation extends Publication {
        
    Scanner in=new Scanner(System.in);
    public static void history(String hs) {
        try {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy (HH:mm:ss)");
            File data = new File("history.txt");
            PrintWriter out = new PrintWriter(new FileWriter(data, true));
            out.println(formatter.format(date) + " " + hs);
            out.println();
            out.close();
        } catch (Exception e) {

        }
    }

    public void showHistory() {
        try {
            File data = new File("history.txt");
            Scanner sc = new Scanner(data);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                System.out.println(line);
            }
        } catch (Exception e) {

        }
    }
    public void line(){
       System.out.println("============================================================");
    }
}
