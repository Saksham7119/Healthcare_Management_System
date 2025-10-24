package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor {

    private Integer doctorId;
    private String practiceStartDate;
    private String aboutMe;
    private User user;

    public Doctor() {
    }

    public Doctor(String practiceStartDate, String aboutMe, User user) {
        this.practiceStartDate = practiceStartDate;
        this.aboutMe = aboutMe;
        this.user = user;
    }

    public boolean fillDoctorDetails() {
        boolean flag = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/healthcaredb?user=root&password=1234");
            String query = "INSERT INTO doctors ( practice_start_date, about_me , user_id) VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, practiceStartDate);
            ps.setString(2, aboutMe);
            ps.setInt(3, user.getUserId());

            int i = ps.executeUpdate();
            if (i == 1)
                flag = true;

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static Doctor getByUserId(int userId) {
        Doctor m = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/healthcaredb?user=root&password=1234");

            String query = "SELECT * FROM doctors WHERE user_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                m = new Doctor();
                m.setDoctorId((rs.getInt("doctor_id")));
                m.setPracticeStartDate(rs.getString("practice_start_date"));
                m.setAboutMe((rs.getString("about_me")));
            }
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return m;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getPracticeStartDate() {
        return practiceStartDate;
    }

    public void setPracticeStartDate(String practiceStartDate) {
        this.practiceStartDate = practiceStartDate;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
