
public class Recordes {

/**
 * @author KeMo
 */

    private Integer id;
    private String name, phone, sys, state;
    private float pay;

    public Recordes() {
    }

    public Recordes(Integer id, String name, String phone, String sys, float pay, String state) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.sys = sys;
        this.pay = pay;
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getSys() {
        return sys;
    }

    public float getPay() {
        return pay;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSys(String sys) {
        this.sys = sys;
    }

    public void setPay(float pay) {
        this.pay = pay;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setState(String state) {
        this.state = state;
    }

}
