
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Data {

    public static ArrayList<Publication> list = new ArrayList<Publication>();
    public static ArrayList<String> Blist = new ArrayList<String>();

    public static ArrayList<Publication> recoverData() {
        ArrayList list = new ArrayList<Publication>();
        try {
            File data = new File("data.txt");
            Scanner sc = new Scanner(data);
            int cnt = 0;
            while (sc.hasNextLine()) {
                int idx = 0;
                String line = sc.nextLine(), r = "";
                cnt++;
                if (cnt < 3) {
                    continue;
                }
                String[] str = new String[100];
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) != ' ') {
                        r += line.charAt(i);
                    } else if (!(r.isEmpty())) {
                        str[idx++] = r;
                        r = "";
                    }
                }
                if (!(r.isEmpty())) {
                    str[idx] = r;
                }
                list.add(new Publication(str[0], str[1], str[2], str[3], str[4], str[5]));

            }
        } catch (IOException e) {

        }
        return list;
    }

    public static ArrayList<String> BrecoverData() {
        ArrayList list = new ArrayList<String>();
        try {
            File data = new File("borrowed.txt");
            Scanner sc = new Scanner(data);
            int cnt = 0;
            while (sc.hasNextLine()) {
                int idx = 0;
                String line = sc.nextLine(), r = "";
                cnt++;
                if (cnt < 3) {
                    continue;
                }
                String[] str = new String[100];
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) != ' ') {
                        r += line.charAt(i);
                    } else if (!(r.isEmpty())) {
                        str[idx++] = r;
                        r = "";
                    }
                }
                if (!(r.isEmpty())) {
                    str[idx] = r;
                }
                String s = String.format("%2s %15s %17s %14s %18s", str[0], str[1], str[2], str[3], str[4]);
                list.add(s);

            }
        } catch (IOException e) {

        }
        return list;
    }

}
