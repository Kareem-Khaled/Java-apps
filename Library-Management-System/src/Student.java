
import java.io.*;
import java.util.*;
import java.text.*;

public class Student extends Person {

    Operation op = new Operation();

    public Student(String userName) {
        op.history("(Student) " + userName + " Loged IN");
        super.setUserName(userName);
    }

    public int option(int choice) {
        return choice;
    }

    public void showMessage() {
        super.showMessage();
        op.history(super.getUserName() + " Show Message");
        System.out.println("If you want to Borrow Publication, press 3");
        System.out.println("If you want to return Publication, press 4");
        System.out.println("If you want to Exit, press 0");
        System.out.print("You Want to : ");
    }

    public void borrowPublication() {
        Scanner in = new Scanner(System.in);
        System.out.println("If you want to borrow a Book, press 1");
        System.out.println("If you want to borrow a Magazine, press 2");
        System.out.println("If you want to borrow a Booklet, press 3");
        System.out.print("You Want to : ");
        int studentWant = in.nextInt();
        op.line();
        if (studentWant == 1) {
            System.out.println("These are the books available in the library now");
            op.line();
            System.out.println("id      name    edition  TheAuthor");
            for (Publication itr : list) {
                if (itr.type.equals("Book")&&Integer.parseInt(itr.quantity)>0) {
                    System.out.printf("%2s %10s %7s %12s", itr.id, itr.name, itr.edition, itr.author + "\n");
                }
            }
        } else if (studentWant == 2) {
            System.out.println("These are the Magazine available in the library now");
            op.line();
            System.out.println("id      name    edition  TheAuthor");
            for (Publication itr : list) {
                if (itr.type.equals("Magazine")&&Integer.parseInt(itr.quantity)>0) {
                    System.out.printf("%2s %10s %7s %12s", itr.id, itr.name, itr.edition, itr.author + "\n");
                }
            }
        } else {
            System.out.println("These are the Booklet available in the library now");
            op.line();
            System.out.println("id      name    edition  TheAuthor");
            for (Publication itr : list) {
                if (itr.type.equals("Booklet")&&Integer.parseInt(itr.quantity)>0) {
                    System.out.printf("%2s %10s %7s %12s", itr.id, itr.name, itr.edition, itr.author + "\n");
                }
            }
        }
        System.out.print("please Enter the Id of The Publication You want : ");
        int pId = in.nextInt();
        op.line();
        String pName = null;
        for (Publication itr : list) {
            int id = Integer.parseInt(itr.id);
            if (pId == id) {
                int quantity = Integer.parseInt(itr.quantity);
                quantity--;
                itr.quantity = Integer.toString(quantity);
                pName = itr.name;
            }
        }

        Date now = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("d-M-y");
        String noow = dateFormatter.format(now);
        String fineTime = calcFineTime(noow, studentWant);
        String Bid = this.BID();
        System.out.println("Your Borrowed Id is " + Bid + " Please do not lose it :)");
        System.out.println("You must return the Publication before " + fineTime + " or You will pay a fine");
        op.history(super.getUserName() + " Borrowed a Publication : " + pName);

        Blist.add(Bid + " " + super.getUserName() + " " + pName + " " + noow + "  " + fineTime);

    }

    private String BID() {
        String alphabet = "0123456789";
        String s = "";
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            char c = alphabet.charAt(random.nextInt(10));
            s += c;
        }
        return s;
    }

    public void returnPublication() {
        Date now = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("d-M-y");
        String noow = dateFormatter.format(now);
        System.out.print("Please Enter Your Borrowed ID : ");
        String pID = in.next(), returnPName = null;
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
            if (str[0].equals(pID)) {
                returnPName = str[2];
                if (ISInTime(noow, str[4]) == false) {
                    System.out.println("You are late to return the Publication :(");
                    System.out.println("You must pay a fine of 10 EG");
                } else {
                    System.out.println("You are respected because you returned the book at its time. Thank you :)");
                }
                Blist.remove(itr);
                break;
            }
        }

        for (Publication itr : list) {
            if (itr.name.equalsIgnoreCase(returnPName)) {
                int quantity = Integer.parseInt(itr.quantity);
                quantity++;
                itr.quantity = Integer.toString(quantity);
            }
        }
        op.history(super.getUserName() + " return a publication : " + returnPName);
    }

    private String calcFineTime(String currTime, int studentWant) {
        int d = 0, m = 0, y = 0, cnt = 1, days;
        String r = "";
        if (studentWant == 1) {
            days = 20;
        } else {
            days = 12;
        }
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
        if (d + days > 30) {
            d += days;
            d %= 30;
            m++;
            if (m > 12) {
                m %= 12;
                y++;
            }
        } else {
            d += days;
        }
        String fineDate = Integer.toString(d);
        fineDate += '-';
        r = Integer.toString(m);
        fineDate += r;
        fineDate += '-';
        r = Integer.toString(y);
        fineDate += r;
        return fineDate;
    }
}
