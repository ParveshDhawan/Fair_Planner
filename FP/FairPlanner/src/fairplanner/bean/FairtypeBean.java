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
public class FairtypeBean {

    public int getFair_type_id() {
        return fair_type_id;
    }

    public void setFair_type_id(int fair_type_id) {
        this.fair_type_id = fair_type_id;
    }

    public String getFair_type() {
        return fair_type;
    }

    public void setFair_type(String fair_type) {
        this.fair_type = fair_type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    int fair_type_id;
    String fair_type,description,status;
    
}
