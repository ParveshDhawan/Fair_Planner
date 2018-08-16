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
public class FairstallsBean {

    public int getFair_stall_id() {
        return fair_stall_id;
    }

    public void setFair_stall_id(int fair_stall_id) {
        this.fair_stall_id = fair_stall_id;
    }

    public int getNo_of_stalls() {
        return no_of_stalls;
    }

    public void setNo_of_stalls(int no_of_stalls) {
        this.no_of_stalls = no_of_stalls;
    }

    public int getFair_id() {
        return fair_id;
    }

    public void setFair_id(int fair_id) {
        this.fair_id = fair_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFair_stall() {
        return fair_stall;
    }

    public void setFair_stall(String fair_stall) {
        this.fair_stall = fair_stall;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

 
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    int fair_stall_id,no_of_stalls,fair_id;
    double price;
    String fair_stall,status,size;
    
}
