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
public class CompanyfairparticipationBean {

    public int getFair_participation_id() {
        return fair_participation_id;
    }

    public void setFair_participation_id(int fair_participation_id) {
        this.fair_participation_id = fair_participation_id;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public int getFair_id() {
        return fair_id;
    }

    public void setFair_id(int fair_id) {
        this.fair_id = fair_id;
    }

    public int getFair_stall_id() {
        return fair_stall_id;
    }

    public void setFair_stall_id(int fair_stall_id) {
        this.fair_stall_id = fair_stall_id;
    }

    public String getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(String booking_date) {
        this.booking_date = booking_date;
    }

    public int getNo_of_stall_booked() {
        return no_of_stall_booked;
    }

    public void setNo_of_stall_booked(int no_of_stall_booked) {
        this.no_of_stall_booked = no_of_stall_booked;
    }

    public int getIs_booking_cancelled() {
        return is_booking_cancelled;
    }

    public void setIs_booking_cancelled(int is_booking_cancelled) {
        this.is_booking_cancelled = is_booking_cancelled;
    }

    public String getCancellation_date() {
        return cancellation_date;
    }

    public void setCancellation_date(String cancellation_date) {
        this.cancellation_date = cancellation_date;
    }
    
    int fair_participation_id,company_id,fair_id,fair_stall_id,no_of_stall_booked,is_booking_cancelled;
    String booking_date,cancellation_date;
    
}
