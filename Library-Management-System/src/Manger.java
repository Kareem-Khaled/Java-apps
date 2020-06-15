
import java.io.*;
import java.util.*;
import java.text.*;

public class Manger extends Person {

    private String password;

    private void currentPass() {
        try {
            Scanner fileScanner = new Scanner(new File("Password.txt"));
            password = fileScanner.nextLine();
        } catch (Exception e) {

        }
    }

    public Manger(String userName) {
        super.setUserName(userName);
    }

    public boolean enterPass(String pass) {
        currentPass();
        if (password.length() != pass.length()) {
            return false;
        }
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) != pass.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public void changePass() {
        System.out.print("Please Enter The New Password : ");
        String newPass = in.next();
        File data = new File("Password.txt");
        try {
            PrintWriter out = new PrintWriter(new FileWriter(data));
            out.print(newPass);
            out.close();
            System.out.println("Password Changed Successfuly :) ");
            op.history(super.getUserName() + " Change The Password : ");
        } catch (Exception e) {

        }
    }

    public void showMessage() {
        super.showMessage();
        op.history(super.getUserName() + " Show Message");
        System.out.println("If you want to Add New Publication, press 3");
        System.out.println("If you want to Remove Publication, press 4");
        System.out.println("If you want to Change Password, press 5");
        System.out.println("If you want to View The History, press 6");
        System.out.println("If you want to Show Borrowed History, press 7");
        System.out.println("If you want to Show over-period borrowed publications, press 8");
        System.out.println("If you want to modify Publication, press 9");
        System.out.println("If you want to Exit, press 0");
        System.out.print("You Want to : ");
    }

    public void addNewPublication() {

        String pName, pEdition, pAuthor, pType, PQuantity;
        Publication p = new Publication();
        String id = p.getID();
        System.out.print("Please Enter Publication Name : ");
        pName = in.next();
        System.out.print("Please Enter Publication edition : ");
        pEdition = in.next();
        System.out.print("Please Enter Publication author : ");
        pAuthor = in.next();
        
        boolean valid = false;
        do {
             System.out.print("Please Enter Publication Type : ");
             pType = in.next();
            
            if (pType.equalsIgnoreCase("book")||pType.equalsIgnoreCase("booklet")||pType.equalsIgnoreCase("magazine")) {
                valid = true;
            } else {
                System.out.println("Invalid Input :( ");
            }
        } while (valid == false);
       
         valid = false;
        do {
            System.out.print("Please Enter Publication  Quantity : ");
            PQuantity = in.next();
            int pq = Integer.parseInt(PQuantity);
            if (pq > 0) {
                valid = true;
            } else {
                System.out.println("Invalid Input :( ");
            }
        } while (valid == false);

        list.add(new Publication(id, pName, pEdition, pAuthor, pType, PQuantity));
        System.out.println("The book was added successfully :) ");
        Operation op = new Operation();
        op.history(super.getUserName() + " Add New Publication : " + pName);
    }

    public void removeBook() {
        System.out.print("Please Enter The name of the book you want to remove : ");
        String removedBook = in.next();
        boolean found = false;
        for (Publication itr : list) {
            if (itr.name.equalsIgnoreCase(removedBook)) {
                list.remove(itr);
                found = true;
                break;
            }
        }
        if (found == true) {
            System.out.println("The book has been successfully deleted :)");
        } else {
            System.out.println("The book does not exist");
        }
        op.history(super.getUserName() + " Deleted Publication : " + removedBook);
    }

    public void showBorrowed() {
        System.out.println("****************(Borrowed History)****************");
        System.out.println("ProcessId     StudentName     borrowed         at             LastTimeToReturn");
        System.out.println("===================================================================================");
        for (String itr : Blist) {
            System.out.println(itr);
        }
        op.history(super.getUserName() + " Show Borrowed Publication");
    }

    public void showOvBorrowed() {
        Date now = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("d-M-y");
        String noow = dateFormatter.format(now);
        boolean flag = false;
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
            if (this.ISInTime(noow, str[4]) == false) {
                if (!flag) {
                    System.out.println("****************(Over-Borrowed History)****************");
                    System.out.println("ProcessId     StudentName     borrowed         at             LastTimeToReturn");
                    System.out.println("===================================================================================");
                }
                System.out.println(line);
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("There isn't over-period borrowed publications");
        }
        op.history(super.getUserName() + " Show Over-Borrowed Publication");
    }

    public void modifyPublication() {
        String pName = null, pEdition = null, pAuthor = null, pType = null, PQuantity = null;
        System.out.print("Please Enter Publication Name : ");
        String mName = in.next();
        boolean flag = false;
        for (Publication itr : list) {
            if (itr.name.equalsIgnoreCase(mName)) {
                System.out.print("Please Enter The New Publication Name : ");
                pName = in.next();
                System.out.print("Please Enter The New Publication edition : ");
                pEdition = in.next();
                System.out.print("Please Enter The New Publication author : ");
                pAuthor = in.next();
                System.out.print("Please Enter The New Publication Type : ");
                pType = in.next();
                System.out.print("Please Enter The New Publication  Quantity : ");
                PQuantity = in.next();
                flag = true;
            }
            if (flag == true) {
                op.history(super.getUserName() + " modified Publication : " + mName);
                itr.name = pName;
                itr.edition = pEdition;
                itr.author = pAuthor;
                itr.quantity = PQuantity;
                itr.type = pType;
                System.out.println("The Publication Successfully modified :)");
                break;
            }
        }
        if (!flag) {
            System.out.println("The Publication Doesn't Exist :(");
        }

    }
}
