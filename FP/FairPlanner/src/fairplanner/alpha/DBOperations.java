/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fairplanner.alpha;

import fairplanner.bean.CompanyfairparticipationBean;
import fairplanner.bean.CompanymasterBean;
import fairplanner.bean.FairmasterBean;
import fairplanner.bean.FairstallsBean;
import fairplanner.bean.FairtypeBean;
import fairplanner.bean.UsermasterBean;
import fairplanner.email.SendSMTP;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class DBOperations {

    public UsermasterBean authUser(String un, String pass) {
        UsermasterBean objBean = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectDB.connect();
            pstmt = conn.prepareStatement("select * from usermaster where username=? and password=binary(?)");
            pstmt.setString(1, un);
            pstmt.setString(2, pass);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                objBean = new UsermasterBean();
                objBean.setUserId(rs.getInt("user_id"));
                objBean.setUserName(rs.getString("username"));
                objBean.setPassword(rs.getString("password"));
                objBean.setUserType(rs.getString("user_type"));
                objBean.setUserStatus(rs.getString("user_status"));
                objBean.setName(rs.getString("name"));
                objBean.setContact(rs.getString("contact_number"));
                objBean.setEmail(rs.getString("email"));
            }
        } catch (Exception e) {
            System.out.println("In authUser()" + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println("In authUser()" + ex);
            }
        }
        return objBean;
    }

    public String forgotPassword(String un) {
        String result = "failed";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectDB.connect();
            pstmt = conn.prepareStatement("select * from usermaster where username=?");
            pstmt.setString(1, un);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = new SendSMTP().sendMail(rs.getString("email"), "Your FairPlanner Password is:" + rs.getString("password"), "Password Recovery Email");
            }
        } catch (Exception e) {
            System.out.println("In forgotPassword()" + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println("In forgotPassword()" + ex);
            }
        }
        return result;
    }

    public List<UsermasterBean> getAllUsers() {
        List<UsermasterBean> lst = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectDB.connect();
            pstmt = conn.prepareStatement("select * from usermaster");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                UsermasterBean objBean = new UsermasterBean();
                objBean.setUserId(rs.getInt("user_id"));
                objBean.setUserName(rs.getString("username"));
                objBean.setPassword(rs.getString("password"));
                objBean.setUserType(rs.getString("user_type"));
                objBean.setUserStatus(rs.getString("user_status"));
                objBean.setName(rs.getString("name"));
                objBean.setContact(rs.getString("contact_number"));
                objBean.setEmail(rs.getString("email"));
                lst.add(objBean);
            }
        } catch (Exception e) {
            System.out.println("In getAllUsers()" + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println("In getAllUsers()" + ex);
            }
        }
        return lst;
    }

    public int getMaxUserId() {
        int maxUserId = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectDB.connect();
            pstmt = conn.prepareStatement("select max(user_id) from usermaster");
            rs = pstmt.executeQuery();
            if (rs.next()) {
                maxUserId = rs.getInt(1);

            }
        } catch (Exception e) {
            System.out.println("In getMaxUserId()" + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println("In getMaxUserId()" + ex);
            }
        }
        return maxUserId;
    }

    public String addRecord(UsermasterBean objBean) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String result = "Failed";
        try {
            conn = ConnectDB.connect();
            pstmt = conn.prepareStatement("insert into usermaster(username,password,user_type,user_status,name,contact_number,email) values(?,?,?,?,?,?,?)");
            pstmt.setString(1, objBean.getUserName());
            pstmt.setString(2, objBean.getPassword());
            pstmt.setString(3, objBean.getUserType());
            pstmt.setString(4, objBean.getUserStatus());
            pstmt.setString(5, objBean.getName());
            pstmt.setString(6, objBean.getContactNumber());
            pstmt.setString(7, objBean.getEmail());
            int i = pstmt.executeUpdate();

            if (i > 0) {
                result = "added";
            }
        } catch (Exception e) {
            System.out.println("In addRecord()" + e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println("In addUser()" + ex);
            }
        }
        return result;
    }

    public String updateRecord(UsermasterBean objBean) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String result = "Failed";
        try {
            conn = ConnectDB.connect();
            pstmt = conn.prepareStatement("update usermaster set username=?,password=?,user_type=?,user_status=?,name=?,contact_number=?,email=? where user_id=?");
            pstmt.setString(1, objBean.getUserName());
            pstmt.setString(2, objBean.getPassword());
            pstmt.setString(3, objBean.getUserType());
            pstmt.setString(4, objBean.getUserStatus());
            pstmt.setString(5, objBean.getName());
            pstmt.setString(6, objBean.getContactNumber());
            pstmt.setString(7, objBean.getEmail());
            pstmt.setInt(8, objBean.getUserId());
            int i = pstmt.executeUpdate();

            if (i > 0) {
                result = "updated";
            }
        } catch (Exception e) {
            System.out.println("In updateRecord()" + e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println("In updateRecord()" + ex);
            }
        }
        return result;
    }

    public List<FairtypeBean> getAllFairTypes() {
        List<FairtypeBean> lstFairtype = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectDB.connect();
            pstmt = conn.prepareStatement("select * from fairtype");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                FairtypeBean objFairtypeBean = new FairtypeBean();
                objFairtypeBean.setFair_type_id(rs.getInt("fair_type_id"));
                objFairtypeBean.setFair_type(rs.getString("fair_type"));
                objFairtypeBean.setDescription(rs.getString("description"));
                objFairtypeBean.setStatus(rs.getString("status"));
                lstFairtype.add(objFairtypeBean);
            }
        } catch (Exception e) {
            System.out.println("In getAllFairTypes()" + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println("In getAllFairTypes()" + ex);
            }
        }
        return lstFairtype;
    }

    public int getMaxFairTypeID() {
        int maxFairTypeID = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectDB.connect();
            pstmt = conn.prepareStatement("select max(fair_type_id) from fairtype");
            rs = pstmt.executeQuery();
            if (rs.next()) {
                maxFairTypeID = rs.getInt(1);

            }
        } catch (Exception e) {
            System.out.println("In getMaxFairTypeID()" + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println("In getMaxFairTypeID()" + ex);
            }
        }
        return maxFairTypeID;
    }

    public String addRecord(FairtypeBean objFairtypeBean) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String result = "Failed";
        try {
            conn = ConnectDB.connect();
            pstmt = conn.prepareStatement("insert into fairtype(fair_type,description,status) values(?,?,?)");
            pstmt.setString(1, objFairtypeBean.getFair_type());
            pstmt.setString(2, objFairtypeBean.getDescription());
            pstmt.setString(3, objFairtypeBean.getStatus());
            int i = pstmt.executeUpdate();
            if (i > 0) {
                result = "added";
            }
        } catch (Exception e) {
            System.out.println("In addRecord" + e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println("In addRecord" + ex);
            }
        }
        return result;
    }

    public String updateRecord(FairtypeBean objFairtypeBean) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String result = "Failed";
        try {
            conn = ConnectDB.connect();
            pstmt = conn.prepareStatement("update fairtype set fair_type=?,description=?,status=? where fair_type_id=?");
            pstmt.setString(1, objFairtypeBean.getFair_type());
            pstmt.setString(2, objFairtypeBean.getDescription());
            pstmt.setString(3, objFairtypeBean.getStatus());
            pstmt.setInt(4, objFairtypeBean.getFair_type_id());
            int i = pstmt.executeUpdate();
            if (i > 0) {
                result = "updated";
            }
        } catch (Exception e) {
            System.out.println("In updateRecord" + e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception ex) {
                System.out.println("In updateRecord()" + ex);
            }
        }
        return result;
    }

    public List<FairstallsBean> getAllFairstalls() {
        List<FairstallsBean> lstFairstalls = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectDB.connect();
            pstmt = conn.prepareStatement("select * from fairstalls");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                FairstallsBean objFairstallsBean = new FairstallsBean();
                objFairstallsBean.setFair_stall_id(rs.getInt("fair_stall_id"));
                objFairstallsBean.setNo_of_stalls(rs.getInt("no_of_stalls"));
                objFairstallsBean.setFair_id(rs.getInt("fair_id"));
                objFairstallsBean.setPrice(rs.getDouble("price"));
                objFairstallsBean.setFair_stall(rs.getString("fair_stall"));
                objFairstallsBean.setSize(rs.getString("size"));
                objFairstallsBean.setStatus(rs.getString("status"));
                lstFairstalls.add(objFairstallsBean);
            }
        } catch (Exception e) {
            System.out.println("In getAllFairstalls()" + e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception ex) {
                System.out.println("In getAllFairstalls()" + ex);
            }
        }
        return lstFairstalls;
    }

    public int getMaxFairStallID() {
        int maxFairStallID = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectDB.connect();
            pstmt = conn.prepareStatement("select max(fair_stall_id) from fairStalls");
            rs = pstmt.executeQuery();
            if (rs.next()) {
                maxFairStallID = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("In getMaxFairStallsID()" + e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception ex) {
                System.out.println("In getMaxFairStallsID()" + ex);
            }
            return maxFairStallID;
        }
    }

    public int getMaxFairID() {
        int maxFairID = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectDB.connect();
            pstmt = conn.prepareStatement("select max(fair_id) from fairstalls");
            rs = pstmt.executeQuery();
            if (rs.next()) {
                maxFairID = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("In getMaxFairID()" + e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception ex) {
                System.out.println("In getMaxFairID()" + ex);
            }
        }
        return maxFairID;
    }

    public String addRecord(FairstallsBean objFairstallsBean) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String result = "Failed";
        try {
            conn = ConnectDB.connect();
            pstmt = conn.prepareStatement("insert into fairstalls(no_of_stalls,fair_stall,price,size,status) values(?,?,?,?,?)");
            pstmt.setInt(1, objFairstallsBean.getNo_of_stalls());
            pstmt.setString(2, objFairstallsBean.getFair_stall());
            pstmt.setDouble(3, objFairstallsBean.getPrice());
            pstmt.setString(4, objFairstallsBean.getSize());
            pstmt.setString(5, objFairstallsBean.getStatus());
            int i = pstmt.executeUpdate();
            if (i > 0) {
                result = "added";
            }
        } catch (Exception e) {
            System.out.println("In addRecord()" + e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception ex) {
                System.out.println("In addRecord" + ex);
            }
        }
        return result;
    }

    public String updateRecord(FairstallsBean objFairstallsBean) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String result = "Failed";
        try {
            conn = ConnectDB.connect();
            pstmt = conn.prepareStatement("update fairstalls set no_of_stalls=?,fair_stall=?,price=?,size=?,status=?,fair_id=? where fair_stall_id=?");
            pstmt.setInt(1, objFairstallsBean.getNo_of_stalls());
            pstmt.setString(2, objFairstallsBean.getFair_stall());
            pstmt.setDouble(3, objFairstallsBean.getPrice());
            pstmt.setString(4, objFairstallsBean.getSize());
            pstmt.setString(5, objFairstallsBean.getStatus());
            pstmt.setInt(6, objFairstallsBean.getFair_id());
            pstmt.setInt(7, objFairstallsBean.getFair_stall_id());
            int i = pstmt.executeUpdate();
            if (i > 0) {
                result = "updated";
            }
        } catch (Exception e) {
            System.out.println("In updateRecord()" + e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception ex) {
                System.out.println("In updateRecord" + ex);
            }
        }
        return result;
    }

    public List<FairmasterBean> getAllFairmasters() {
        List<FairmasterBean> lstFairmaster = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectDB.connect();
            pstmt = conn.prepareStatement("select * from fairmaster");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                FairmasterBean objFairmasterBean = new FairmasterBean();
                objFairmasterBean.setFair_id(rs.getInt("fair_id"));
                objFairmasterBean.setFair_type_id(rs.getInt("fair_type_id"));
                objFairmasterBean.setFair_start_date(rs.getString("fair_start_date"));
                objFairmasterBean.setFair_end_date(rs.getString("fair_end_date"));
                objFairmasterBean.setPin_code(rs.getInt("pin_code"));
                objFairmasterBean.setFair_name(rs.getString("fair_name"));
                objFairmasterBean.setDescription(rs.getString("description"));
                objFairmasterBean.setAddress(rs.getString("address"));
                objFairmasterBean.setCity(rs.getString("city"));
                objFairmasterBean.setState(rs.getString("state"));
                objFairmasterBean.setCountary(rs.getString("country"));
                objFairmasterBean.setStatus(rs.getString("status"));
                lstFairmaster.add(objFairmasterBean);
            }
        } catch (Exception e) {
            System.out.println("In getAllFairmasters()" + e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception ex) {
                System.out.println("In getAllFairmasters()" + ex);
            }
        }
        return lstFairmaster;
    }

    public String addRecord(FairmasterBean objFairmasterBean) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String result = "Failed";
        try {
            conn = ConnectDB.connect();
            pstmt = conn.prepareStatement("insert into fairmaster(fair_start_date,fair_end_date,fair_name,description,address,city,state,country,pin_code,status)values(?,?,?,?,?,?,?,?,?,?)");
            pstmt.setString(1, objFairmasterBean.getFair_start_date());
            pstmt.setString(2, objFairmasterBean.getFair_end_date());
            pstmt.setString(3, objFairmasterBean.getFair_name());
            pstmt.setString(4, objFairmasterBean.getDescription());
            pstmt.setString(5, objFairmasterBean.getAddress());
            pstmt.setString(6, objFairmasterBean.getCity());
            pstmt.setString(7, objFairmasterBean.getState());
            pstmt.setString(8, objFairmasterBean.getCountary());
            pstmt.setInt(9, objFairmasterBean.getPin_code());
            pstmt.setString(10, objFairmasterBean.getStatus());
            int i = pstmt.executeUpdate();
            if (i > 0) {
                result = "added";
            }
        } catch (Exception e) {
            System.out.println("In addRecord" + e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception ex) {
                System.out.println("In addRecord" + ex);
            }
        }
        return result;
    }

    public String updateRecord(FairmasterBean objFairmasterBean) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String result = "Failed";
        try {
            conn = ConnectDB.connect();
            pstmt = conn.prepareStatement("update  fairmaster set fair_start_date=?,fair_end_date=?,fair_name=?,description=?,address=?,city=?,state=?,country=?,pin_code=?,status=?,fair_type_id=? where fair_id=?");
            pstmt.setString(1, objFairmasterBean.getFair_start_date());
            pstmt.setString(2, objFairmasterBean.getFair_end_date());
            pstmt.setString(3, objFairmasterBean.getFair_name());
            pstmt.setString(4, objFairmasterBean.getDescription());
            pstmt.setString(5, objFairmasterBean.getAddress());
            pstmt.setString(6, objFairmasterBean.getCity());
            pstmt.setString(7, objFairmasterBean.getState());
            pstmt.setString(8, objFairmasterBean.getCountary());
            pstmt.setInt(9, objFairmasterBean.getPin_code());
            pstmt.setString(10, objFairmasterBean.getStatus());
            pstmt.setInt(11, objFairmasterBean.getFair_type_id());
            pstmt.setInt(12, objFairmasterBean.getFair_id());
            int i = pstmt.executeUpdate();
            if (i > 0) {
                result = "updated";
            }
        } catch (Exception e) {
            System.out.println("In updateRecord" + e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception ex) {
                System.out.println("In updateRecord" + ex);
            }
        }
        return result;
    }

    public List<CompanymasterBean> getAllCompanymasters() {
        List<CompanymasterBean> lstCompanymaster = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectDB.connect();
            pstmt = conn.prepareStatement("select * from companymaster");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                CompanymasterBean objCompanymasterBean = new CompanymasterBean();
                objCompanymasterBean.setCompany_id(rs.getInt("Company_id"));
                objCompanymasterBean.setPhone(rs.getInt("Phone"));
                objCompanymasterBean.setMobile(rs.getInt("Mobile"));
                objCompanymasterBean.setPin_code(rs.getInt("Pin_code"));
                objCompanymasterBean.setName(rs.getString("name"));
                objCompanymasterBean.setAddress(rs.getString("Address"));
                objCompanymasterBean.setCity(rs.getString("City"));
                objCompanymasterBean.setState(rs.getString("State"));
                objCompanymasterBean.setCountary(rs.getString("Country"));
                objCompanymasterBean.setEmail_id(rs.getString("Email_id"));
                objCompanymasterBean.setWebsite_url(rs.getString("Website_url"));
                lstCompanymaster.add(objCompanymasterBean);
            }
        } catch (Exception e) {
            System.out.println("In getAllCompanymasters()" + e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception ex) {
                System.out.println("In getAllCompanymasters()" + ex);
            }
        }
        return lstCompanymaster;
    }

    public int getMaxCompanyID() {
        int maxCompanyID = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectDB.connect();
            pstmt = conn.prepareStatement("select max(comapany_id) from companymaster");
            rs = pstmt.executeQuery();
            if (rs.next()) {
                maxCompanyID = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("In getMaxCompanyID()" + e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception ex) {
                System.out.println("In getMaxCompanyID()" + ex);
            }

        }
        return maxCompanyID;
    }

    public String addRecord(CompanymasterBean objCompanymasterBean) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String result = "Failed";
        try {
            conn = ConnectDB.connect();
            pstmt = conn.prepareStatement("insert into companymaster(name,address,city,state,country,pin_code,email_id,website_url,phone,mobile) values(?,?,?,?,?,?,?,?,?,?)");
            pstmt.setString(1, objCompanymasterBean.getName());
            pstmt.setString(2, objCompanymasterBean.getAddress());
            pstmt.setString(3, objCompanymasterBean.getCity());
            pstmt.setString(4, objCompanymasterBean.getState());
            pstmt.setString(5, objCompanymasterBean.getCountary());
            pstmt.setInt(6, objCompanymasterBean.getPin_code());
            pstmt.setString(7, objCompanymasterBean.getEmail_id());
            pstmt.setString(8, objCompanymasterBean.getWebsite_url());
            pstmt.setInt(9, objCompanymasterBean.getPhone());
            pstmt.setInt(10, objCompanymasterBean.getMobile());
            int i = pstmt.executeUpdate();
            if (i > 0) {
                result = "added";
            }
        } catch (Exception e) {
            System.out.println("In addRecord" + e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception ex) {
                System.out.println("In addRecord" + ex);
            }
        }
        return result;
    }

    public String updateRecord(CompanymasterBean objCompanymasterBean) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String result = "Failed";
        try {
            conn = ConnectDB.connect();
            pstmt = conn.prepareStatement("update companymaster set name=?,address=?,city=?,state=?,countary=?,pin_code=?,email_id=?,website_url=?,phone=?,mobile=? where company_id=?)");
            pstmt.setString(1, objCompanymasterBean.getName());
            pstmt.setString(2, objCompanymasterBean.getAddress());
            pstmt.setString(3, objCompanymasterBean.getCity());
            pstmt.setString(4, objCompanymasterBean.getState());
            pstmt.setString(5, objCompanymasterBean.getCountary());
            pstmt.setInt(6, objCompanymasterBean.getPin_code());
            pstmt.setString(7, objCompanymasterBean.getEmail_id());
            pstmt.setString(8, objCompanymasterBean.getWebsite_url());
            pstmt.setInt(9, objCompanymasterBean.getPhone());
            pstmt.setInt(10, objCompanymasterBean.getMobile());
            pstmt.setInt(11, objCompanymasterBean.getCompany_id());
            int i = pstmt.executeUpdate();
            if (i > 0) {
                result = "updated";
            }
        } catch (Exception e) {
            System.out.println("In updateRecord" + e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception ex) {
                System.out.println("In updateRecord" + ex);
            }
        }
        return result;
    }

    public List<CompanyfairparticipationBean> getAllCompanyfairparticipations() {
        List<CompanyfairparticipationBean> lstCompanyfairparticipation = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectDB.connect();
            pstmt = conn.prepareStatement("select * from companyfairparticipation");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("pstmt  " + pstmt.toString());
                CompanyfairparticipationBean objCompanyfairparticipationBean = new CompanyfairparticipationBean();
                objCompanyfairparticipationBean.setFair_participation_id(rs.getInt("Fair_participation_id"));
                objCompanyfairparticipationBean.setCompany_id(rs.getInt("Company_id"));
                objCompanyfairparticipationBean.setFair_id(rs.getInt("Fair_id"));
                objCompanyfairparticipationBean.setFair_stall_id(rs.getInt("Fair_stall_id"));
                objCompanyfairparticipationBean.setBooking_date(rs.getString("Booking_date"));
                objCompanyfairparticipationBean.setNo_of_stall_booked(rs.getInt("No_of_stall_booked"));
                objCompanyfairparticipationBean.setIs_booking_cancelled(rs.getInt("Is_booking_cancelled"));
                objCompanyfairparticipationBean.setCancellation_date(rs.getString("Cancellation_date"));
                lstCompanyfairparticipation.add(objCompanyfairparticipationBean);
            }
        } catch (Exception e) {
            System.out.println("In getAllCompanyfairparticipations()" + e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception ex) {
                System.out.println("In getAllCompanyfairparticipations()" + ex);
            }
        }
        return lstCompanyfairparticipation;
    }

    public int getMaxFairParticipationID() {
        int maxFairParticipationID = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectDB.connect();
            pstmt = conn.prepareStatement("select max(fair_participation_id) from Companyfairparticipation");
            rs = pstmt.executeQuery();
            if (rs.next()) {
                maxFairParticipationID = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("In getMaxFairParticipationID()" + e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception ex) {
                System.out.println("In getMaxFairParticipationID()" + ex);
            }
        }
        return maxFairParticipationID;
    }

    public int getAvailableStalls(int fairStallId) {
        int maxFairParticipationID = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectDB.connect();
            pstmt = conn.prepareStatement("select (select no_of_stalls from fairstalls where fair_stall_id=" + fairStallId + ")-(select count(fair_stall_id) from companyfairparticipation where fair_stall_id=" + fairStallId + ");");
            rs = pstmt.executeQuery();
            if (rs.next()) {
                maxFairParticipationID = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("In getMaxFairParticipationID()" + e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception ex) {
                System.out.println("In getMaxFairParticipationID()" + ex);
            }
        }
        return maxFairParticipationID;
    }

    public int getUsedStalls(int comapanyId) {
        int maxFairParticipationID = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectDB.connect();
            pstmt = conn.prepareStatement("select count(fair_stall_id) from companyfairparticipation where company_id=" + comapanyId + "");
            rs = pstmt.executeQuery();
            if (rs.next()) {
                maxFairParticipationID = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("In getMaxFairParticipationID()" + e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception ex) {
                System.out.println("In getMaxFairParticipationID()" + ex);
            }
        }
        return maxFairParticipationID;
    }

    public String addRecord(CompanyfairparticipationBean objCompanyfairparticipationBean) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String result = "Failed";
        try {
            conn = ConnectDB.connect();
            pstmt = conn.prepareStatement("insert into Companyfairparticipation(booking_date,no_of_stall_booked,is_booking_cancelled,cancellation_date) values(?,?,?,?)");
            pstmt.setString(1, objCompanyfairparticipationBean.getBooking_date());
            pstmt.setInt(2, objCompanyfairparticipationBean.getNo_of_stall_booked());
            pstmt.setInt(3, objCompanyfairparticipationBean.getIs_booking_cancelled());
            pstmt.setString(4, objCompanyfairparticipationBean.getCancellation_date());
            int i = pstmt.executeUpdate();
            if (i > 0) {
                result = "added";
            }
        } catch (Exception e) {
            System.out.println("In addRecord()" + e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception ex) {
                System.out.println("In addRecord()" + ex);
            }
        }
        return result;
    }

    public String updateRecord(CompanyfairparticipationBean objCompanyfairparticipationBean) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String result = "Failed";
        try {
            conn = ConnectDB.connect();
            pstmt = conn.prepareStatement("update Companyfairparticipation set booking_date=?,no_of_stall_booked=?,is_booking_cancelled=?,cancellation_date=?, company_id=?,fair_id=?,fair_stall_id=? where fair_participation_id=?");
            pstmt.setString(1, objCompanyfairparticipationBean.getBooking_date());
            pstmt.setInt(2, objCompanyfairparticipationBean.getNo_of_stall_booked());
            pstmt.setInt(3, objCompanyfairparticipationBean.getIs_booking_cancelled());
            pstmt.setString(4, objCompanyfairparticipationBean.getCancellation_date());
            pstmt.setInt(5, objCompanyfairparticipationBean.getCompany_id());
            pstmt.setInt(6, objCompanyfairparticipationBean.getFair_id());
            pstmt.setInt(7, objCompanyfairparticipationBean.getFair_stall_id());
            pstmt.setInt(8, objCompanyfairparticipationBean.getFair_participation_id());
            int i = pstmt.executeUpdate();
            if (i > 0) {
                result = "updated";
            }
        } catch (Exception e) {
            System.out.println("In updateRecord()" + e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception ex) {
                System.out.println("In updateRecord()" + ex);
            }
        }
        return result;
    }

}
