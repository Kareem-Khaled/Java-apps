
import java.awt.Font;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;

/**
 * @author KeMo
 */
public class RecordesQuery {

    public void insertRecorde(Recordes re) {
        Connection con = myConnection.getConnection();
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("INSERT INTO `customer` (`namee`,`phone`,`systemm`,`payment`,`state`) VALUES (?,?,?,?,?)");
            ps.setString(1, re.getName());
            ps.setString(2, re.getPhone());
            ps.setString(3, re.getSys());
            ps.setFloat(4, re.getPay());
            ps.setString(5, re.getState());
            if (ps.executeUpdate() != 0) {
                JLabel label = new JLabel("Recordes Add  Successfully !");
                label.setFont(new Font("Arial", Font.PLAIN, 15));
                JOptionPane.showMessageDialog(null, label, "Done", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JLabel label = new JLabel("Something Wrong !");
                label.setFont(new Font("Arial", Font.PLAIN, 15));
                JOptionPane.showMessageDialog(null, label, "Error", JOptionPane.ERROR_MESSAGE);
            }
            con.close();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(SignupForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editRecorde(Recordes re, int id) {
        Connection con = myConnection.getConnection();
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("update `customer` SET `namee`=?,`phone`=?,`systemm`=?,`payment`=?,`state`=? WHERE `Id`=?");
            ps.setString(1, re.getName());
            ps.setString(2, re.getPhone());
            ps.setString(3, re.getSys());
            ps.setFloat(4, re.getPay());
            ps.setString(5, re.getState());
            ps.setInt(6, id);
            if (ps.executeUpdate() != 0) {
                JLabel label = new JLabel("Recordes Edit  Successfully !");
                label.setFont(new Font("Arial", Font.PLAIN, 15));
                JOptionPane.showMessageDialog(null, label, "Done", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JLabel label = new JLabel("Something Wrong !");
                label.setFont(new Font("Arial", Font.PLAIN, 15));
                JOptionPane.showMessageDialog(null, label, "Error", JOptionPane.ERROR_MESSAGE);
            }
            con.close();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(SignupForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteRecorde(int id) {
        Connection con = myConnection.getConnection();
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("delete from `customer` where  `Id`=?");
            ps.setInt(1, id);
            if (ps.executeUpdate() != 0) {
                JLabel label = new JLabel("Recordes Deleted  Successfully !");
                label.setFont(new Font("Arial", Font.PLAIN, 15));
                JOptionPane.showMessageDialog(null, label, "Done", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JLabel label = new JLabel("Something Wrong");
                label.setFont(new Font("Arial", Font.PLAIN, 15));
                JOptionPane.showMessageDialog(null, label, "Error", JOptionPane.ERROR_MESSAGE);
            }
            con.close();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(SignupForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Recordes> getRecordes() {
        Connection con = myConnection.getConnection();
        Statement st;
        ResultSet res;
        ArrayList<Recordes> ret = new ArrayList<>();
        try {
            st = con.createStatement();
            res = st.executeQuery("SELECT * FROM customer");
            Recordes re;
            while (res.next()) {
                re = new Recordes(res.getInt("Id"),
                        res.getString("namee"),
                        res.getString("phone"),
                        res.getString("systemm"),
                        res.getFloat("payment"),
                        res.getString("state"));
                ret.add(re);
            }
            con.close();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(SignupForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    public ArrayList<Recordes> findIt(int type, String item) {
        Connection con = myConnection.getConnection();
        PreparedStatement st;
        ResultSet res;
        ArrayList<Recordes> ret = new ArrayList<>();
        String order = null;
        if (type == 1) {
            order = "SELECT * FROM customer Where namee = ?";
        } else {
            order = "SELECT * FROM customer Where phone = ?";
        }
        try {
            boolean found = false;

            st = con.prepareStatement(order);
            st.setString(1, item);

            res = st.executeQuery();

            Recordes re;
            while (res.next()) {
                found = true;
                re = new Recordes(res.getInt("Id"),
                        res.getString("namee"),
                        res.getString("phone"),
                        res.getString("systemm"),
                        res.getFloat("payment"),
                        res.getString("state"));

                ret.add(re);
            }
            if (found == false) {
                JLabel label = new JLabel("The Recorde Doesn't Exist !");
                label.setFont(new Font("Arial", Font.PLAIN, 15));
                JOptionPane.showMessageDialog(null, label, "Oops", JOptionPane.INFORMATION_MESSAGE);
            }
            con.close();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(SignupForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    public void addToAll(float value) {
        Connection con = myConnection.getConnection();
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("update  customer  SET  payment = payment + ? where id>0");
            ps.setFloat(1, value);
            if (ps.executeUpdate() != 0) {
                JLabel label = new JLabel("Value Add Successfully !");
                label.setFont(new Font("Arial", Font.PLAIN, 15));
                JOptionPane.showMessageDialog(null, label, "Done", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JLabel label = new JLabel("Something Wrong !");
                label.setFont(new Font("Arial", Font.PLAIN, 15));
                JOptionPane.showMessageDialog(null, label, "Error", JOptionPane.ERROR_MESSAGE);
            }
            con.close();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(SignupForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Clear() {
        Connection con = myConnection.getConnection();
        Statement st = null;
        try {
            st = con.createStatement();
            st.executeUpdate("DELETE FROM customer");
            st.executeUpdate("DELETE FROM sqlite_sequence WHERE name = 'customer'");
            JLabel label = new JLabel("Deleting Done Successfully !");
            label.setFont(new Font("Arial", Font.PLAIN, 15));
            JOptionPane.showMessageDialog(null, label, "Done", JOptionPane.INFORMATION_MESSAGE);
            con.close();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(SignupForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
