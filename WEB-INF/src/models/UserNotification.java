package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserNotification {
    private Integer notificationID;
    private String notificationMessage;
    private User user;
    private String createdAt;

    public UserNotification(){}
    
    public UserNotification(Integer notificationID, String notificationMessage, User user) {
        this.notificationID = notificationID;
        this.notificationMessage = notificationMessage;
        this.user = user;
    }

    public UserNotification(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }
    public UserNotification(String notificationMessage , Integer notificationID) {
        this.notificationMessage = notificationMessage;
        this.notificationID = notificationID;
    }
    
    public UserNotification(String notificationMessage, User user) {
        this.notificationMessage = notificationMessage;
        this.user = user;
    }

    public UserNotification( String notificationMessage, Integer notificationID,String createdAt) {
        this.notificationMessage = notificationMessage;
        this.notificationID = notificationID;
        this.createdAt = createdAt;
    }

    public boolean addNotification() {
        boolean flag = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/healthcaredb?user=root&password=1234");

            String query = "INSERT INTO notifications (user_id , notification_message) VALUES (?,?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1 , user.getUserId());
            ps.setString(2 , notificationMessage);
        

            int i = ps.executeUpdate();

            if (i == 1)
                flag = true;

            con.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return flag;
    }

    
    public ArrayList<UserNotification> collectNotifications(int userId){
        ArrayList<UserNotification> notifications = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcaredb?user=root&password=1234");
            String query = "select * from notifications where user_id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                notifications.add(
                    new UserNotification(
                        rs.getString("notification_message"),
                        rs.getInt("notification_id"),
                        rs.getString("created_at")
                    )
                );
            }

            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return notifications;
    }

    public boolean deleteNotification(int notificationId) {
    boolean flag = false;
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/healthcaredb?user=root&password=1234"
        );
        String query = "DELETE FROM notifications WHERE notification_id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, notificationId);

        int rows = ps.executeUpdate();
        if (rows > 0) {
            flag = true;
        }

        con.close();
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }
    return flag;
}


    public Integer getNotificationID() {
        return notificationID;
    }
    public void setNotificationID(Integer notificationID) {
        this.notificationID = notificationID;
    }
    public String getNotificationMessage() {
        return notificationMessage;
    }
    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }


}
