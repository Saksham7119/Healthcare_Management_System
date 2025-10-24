package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
// import java.sql.Statement;

public class User {

    private Integer userId;
    private String name;
    private Date dob;
    private Integer gender;
    private String contact;
    private String email;
    private City city;
    private String address;
    private UserType userType;
    private String verificationCode;
    private Integer otp;
    private Status status;
    private String password;

    // ------------Constructors start---------------------------------
    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password, String contact, Date dob, Integer gender, String address,
            UserType userType) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.contact = contact;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.userType = userType;
    }
    // ------------Constructors end---------------------------------

    // ------------JDBC Start---------------------------------
    public boolean signupUser() {
        boolean flag = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/healthcaredb?user=root&password=1234");

            String query = "INSERT INTO users (name , dob , gender , contact , email, address , password , user_type_id) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            // PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, name);
            ps.setDate(2, (java.sql.Date) dob);
            ps.setInt(3, gender);
            ps.setString(4, contact);
            ps.setString(5, email);
            ps.setString(6, address);
            ps.setString(7, password);
            ps.setInt(8 , userType.getUserTypeId());

            int i = ps.executeUpdate();

            if (i == 1)
                flag = true;

            // ResultSet rs =  ps.getGeneratedKeys();
            // int userId = 0;
            // if(rs.next())
            //     userId = rs.getInt(1);
            
            // if(userType.getUserTypeId() == 1 ){
            //     String patientQuery = "INSERT INTO patients (user_id) value (?)";
            //     PreparedStatement psPatient = con.prepareStatement(patientQuery);
            //     psPatient.setInt(1, userId);
            //     psPatient.executeUpdate();
            //     System.out.println("INSERTED USER_ID IN PATIENT TABLE" + userId);
            // } 
            // else if(userType.getUserTypeId() == 2){
            //     String doctorQuery = "INSERT INTO doctors (user_id) value (?)";
            //     PreparedStatement psDoctor = con.prepareStatement(doctorQuery);
            //     psDoctor.setInt(1, userId);
            //     psDoctor.executeUpdate();
            //     System.out.println("INSERTED USER_ID IN DOCTORS TABLE" + userId);
            // }
            // else if(userType.getUserTypeId() == 3){
            //     String manufacturerQuery = "INSERT INTO manufacturers (user_id) value (?)";
            //     PreparedStatement psManufacturer = con.prepareStatement(manufacturerQuery);
            //     psManufacturer.setInt(1, userId);
            //     psManufacturer.executeUpdate();
            //     System.out.println("INSERTED USER_ID IN MANUFACTURERS TABLE" + userId);
            // }

            con.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean loginUser() {
        boolean flag = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/healthcaredb?user=root&password=1234");

            String query = "select user_id, email , name , user_type_id from users where email=? and password=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            
            // if (rs.next()) {
            //     email = rs.getString("email");
            //     Integer userTypeId = rs.getInt("user_type_id");

            //     userType = new UserType();
            //     userType.setUserTypeId(userTypeId);
            //     flag = true;
            // }

            if (rs.next()) {
            this.setUserId(rs.getInt("user_id"));
            this.setEmail(rs.getString("email"));
            this.setName(rs.getString("name"));

            UserType userType = new UserType();
            userType.setUserTypeId(rs.getInt("user_type_id"));
            this.setUserType(userType);

            flag = true;
        }

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return flag;
    }

    // ------------JDBC End---------------------------------

    // ------------------Getter & Setter Methods-------------------------
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
