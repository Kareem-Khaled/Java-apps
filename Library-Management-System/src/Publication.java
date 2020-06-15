
import java.io.*;
import java.util.*;
import java.text.*;

public class Publication {

    protected String id, name, edition, author, type, quantity;

    public Publication() {
    }

    public Publication(String id, String name, String edition, String author, String type, String quantity) {
        this.id = id;
        this.name = name;
        this.edition = edition;
        this.author = author;
        this.type = type;
        this.quantity = quantity;
    }

    public static void reSetID() {
        try {
            File data = new File("ID.txt");
            PrintWriter out = new PrintWriter(data);
            out.print("1");
            out.close();
        } catch (Exception e) {

        }
    }

    public String getID() {
        try {
            File data = new File("ID.txt");
            Scanner sc = new Scanner(data);
            id = sc.next();
            PrintWriter out = new PrintWriter(new FileWriter(data));
            int ID = Integer.parseInt(id);
            out.print(ID + 1);
            out.close();

        } catch (Exception e) {

        }
        return id;
    }

}
