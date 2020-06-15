
import java.io.*;
import java.util.*;
import java.text.*;

public class Person {

    public static ArrayList<String> Blist = Data.BrecoverData();
    public static ArrayList<Publication> list = Data.recoverData();
    Operation op = new Operation();
    Scanner in = new Scanner(System.in);

    private String userName;
    private int userIs;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Person() {
    }

    public String getUserName() {
        return userName;
    }

    public int getUserIs() {
        return userIs;
    }

    public void showMessage() {
        System.out.println("If you want to display Publications, press 1");
        System.out.println("If you want to  Search  Publication, press 2");

    }

    public void showPublication(int userIs) {
        op.line();
        if (userIs == 1) {
            op.history(userName + " Show The Publications");
            System.out.println("id   name    edition  TheAuthor    Type ");
            op.line();
            for (Publication itr : list) {
                System.out.printf("%2s %9s %5s %10s %11s", itr.id, itr.name, itr.edition, itr.author, itr.type + "\n");
            }
        } else {
            System.out.println("id   name    edition  TheAuthor    Type      Quantity");
            op.line();
            for (Publication itr : list) {
                System.out.printf("%2s %9s %5s %10s %11s %7s", itr.id, itr.name, itr.edition, itr.author, itr.type, itr.quantity + "\n");
            }
        }
    }

    public void searchPublication() {
        System.out.print("Please enter the name of the Publication you want to search : ");
        String bookName = in.next(), type = null;
        op.line();
        boolean found = false;
        for (Publication itr : list) {
            if (itr.name.equalsIgnoreCase(bookName)) {
                type = itr.type;
                found = true;
                break;
            }
        }
        if (found == true) {
            System.out.println("The " + type + " exists :)");
        } else {
            System.out.println("Sorry The Publication does not exist :(");
        }
        op.history(userName + " searched for a Publication : " + bookName);
    }

    public void clearFile() {
        try {
            Publication.reSetID();
            File data = new File("data.txt");
            PrintWriter out = new PrintWriter(data);
            out.println("id   name    edition  TheAuthor    Type      Quantity");
            out.print("============================================================");
            out.close();

            data = new File("borrowed.txt");
            out = new PrintWriter(data);
            out.println("ProcessId     StudentName     borrowed         at             LastTimeToReturn");
            out.print("=====================================================================================");
            out.close();
        } catch (Exception e) {

        }
    }

    private void finish(String name, String edition, String author, String type, String quantity) {
        try {
            Publication p = new Publication();
            String id = p.getID();
            File data = new File("data.txt");
            PrintWriter out = new PrintWriter(new FileWriter(data, true));
            out.println();
            out.printf("%2s %9s %5s %10s %11s %7s", id, name, edition, author, type, quantity);
            out.close();

        } catch (Exception e) {

        }
    }

    private void Bfinish(String pId, String SName, String Bp, String at, String LTR) {
        try {
            File data = new File("borrowed.txt");
            PrintWriter out = new PrintWriter(new FileWriter(data, true));
            out.println();
            out.printf("%2s %15s %17s %14s %18s", pId, SName, Bp, at, LTR);
            out.close();

        } catch (Exception e) {

        }
    }

    public void End() {
        for (Publication itr : list) {
            this.finish(itr.name, itr.edition, itr.author, itr.type, itr.quantity);
        }
        for (String itr : Blist) {
            String[] str = new String[100];
            String line = itr, r = "";
            int idx = 0;
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
            this.Bfinish(str[0], str[1], str[2], str[3], str[4]);
        }
        System.exit(0);
    }

    public boolean ISInTime(String currTime, String finTime) {
        int cnt = 1, d = 0, m = 0, y = 0;
        String r = "";
        for (int i = 0; i < currTime.length(); i++) {
            if (currTime.charAt(i) == '-') {
                if (cnt == 1) {
                    d = Integer.parseInt(r);
                } else if (cnt == 2) {
                    m = Integer.parseInt(r);
                }
                r = "";
                cnt++;
            } else {
                r += currTime.charAt(i);
            }
        }
        y = Integer.parseInt(r);

        cnt = 1;
        int d0 = 0, m0 = 0, y0 = 0;
        r = "";
        for (int i = 0; i < finTime.length(); i++) {
            if (finTime.charAt(i) == '-') {
                if (cnt == 1) {
                    d0 = Integer.parseInt(r);
                } else if (cnt == 2) {
                    m0 = Integer.parseInt(r);
                }
                r = "";
                cnt++;
            } else {
                r += finTime.charAt(i);
            }
        }
        y0 = Integer.parseInt(r);
        if (d > d0 || m > m0 || y > y0) {
            return false;
        }
        return true;
    }

}
