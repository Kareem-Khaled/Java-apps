
import java.io.*;
import java.util.*;
import java.text.*;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        while (true) {
            try {
                Scanner in = new Scanner(System.in);
                Operation op = new Operation();
                System.out.print("Please Enter Your First Name : ");
                String userName = in.next();
                System.out.println("Hello, " + userName + " ($_$)");
                System.out.println("If you are a student, press 1. If you are a manager, press 2");
                System.out.print("You Are : ");
                int userIs = in.nextInt(), userChoice;
                op.line();
                if (userIs == 1) {
                    Student st = new Student(userName);
                    st.showMessage();
                    userChoice = in.nextInt();
                    while (true) {
                        if (userChoice == 1) {
                            st.showPublication(1);
                        } else if (userChoice == 2) {
                            st.searchPublication();
                        } else if (userChoice == 3) {
                            st.borrowPublication();
                        } else if (userChoice == 4) {
                            st.returnPublication();
                        } else if (userChoice == 0) {
                            op.history(userName + " Loged Out ");
                            System.out.println("BYe " + userName);
                            st.clearFile();
                            st.End();
                        } else {
                            throw new Exception();

                        }
                        op.line();
                        st.showMessage();
                        userChoice = in.nextInt();
                    }
                } else if (userIs == 2) {
                    Manger ma = new Manger(userName);
                    String password = JOptionPane.showInputDialog(null, "Please Enter The Password : ");
                    while (ma.enterPass(password) == false) {
                        op.line();
                        System.out.println("The password you enterd isn't Correct :(");
                        System.out.println("If You want to try again Prees 1, If you wnat to exit press 0");
                        System.out.print("you wnat to : ");
                        int mangerChoice = in.nextInt();
                        if (mangerChoice == 1) {
                            password = JOptionPane.showInputDialog(null, "Please Enter The Password : ");
                        } else {
                            op.history(userName + " Loged Out ");
                            System.out.println("BYe " + userName);
                            ma.clearFile();
                            ma.End();
                        }
                    }
                    op.line();
                    System.out.println("Correct Password :)");
                    op.history("(Manger) " + userName + " Loged IN");
                    ma.showMessage();
                    userChoice = in.nextInt();
                    while (true) {
                        if (userChoice == 1) {
                            ma.showPublication(2);
                        } else if (userChoice == 2) {
                            ma.searchPublication();
                        } else if (userChoice == 3) {
                            ma.addNewPublication();
                        } else if (userChoice == 4) {
                            ma.removeBook();
                        } else if (userChoice == 5) {
                            ma.changePass();
                        } else if (userChoice == 6) {
                            op.line();
                            op.showHistory();
                        } else if (userChoice == 7) {
                            ma.showBorrowed();
                        } else if (userChoice == 8) {
                            ma.showOvBorrowed();
                        } else if (userChoice == 9) {
                            ma.modifyPublication();
                        } else if (userChoice == 0) {
                            op.history(userName + " Loged Out ");
                            System.out.println("BYe " + userName);
                            ma.clearFile();
                            ma.End();
                        } else {
                            throw new Exception();
                        }
                        op.line();
                        ma.showMessage();
                        userChoice = in.nextInt();
                    }
                } else {
                    System.out.println("Error :( The Program will Rerun Please Enter valid value");
                }
            } catch (Exception e) {
                System.out.println("Error :( The Program will Rerun Please Enter valid value " + e.getMessage());
            }
        }
    }
}
