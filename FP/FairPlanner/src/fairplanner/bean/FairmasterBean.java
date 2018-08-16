    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fairplanner.bean;

/**
 *
 * @author Parvesh
 */
public class FairmasterBean {

    public int getFair_id() {
        return fair_id;
    }

    public void setFair_id(int fair_id) {
        this.fair_id = fair_id;
    }

    public int getFair_type_id() {
        return fair_type_id;
    }

    public void setFair_type_id(int fair_type_id) {
        this.fair_type_id = fair_type_id;
    }

    public String getFair_start_date() {
        return fair_start_date;
    }

    public void setFair_start_date(String fair_start_date) {
        this.fair_start_date = fair_start_date;
    }

    public String getFair_end_date() {
        return fair_end_date;
    }

    public void setFair_end_date(String fair_end_date) {
        this.fair_end_date = fair_end_date;
    }

    public String getFair_name() {
        return fair_name;
    }

    public void setFair_name(String fair_name) {
        this.fair_name = fair_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountary() {
        return countary;
    }

    public void setCountary(String countary) {
        this.countary = countary;
    }

    public int getPin_code() {
        return pin_code;
    }

    public void setPin_code(int pin_code) {
        this.pin_code = pin_code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    int fair_id,pin_code,fair_type_id;
    String fair_name,description,address,city,state,countary,status,fair_start_date,fair_end_date;
    
}
