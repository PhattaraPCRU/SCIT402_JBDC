/**
 *
 * @author koonp@641102064111
 * Create on 1:12:13 AM Oct 6, 2022
 * This file is part of "JBDC" Project.
 */
import java.sql.*;
public class JDBC {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            String driverName = "com.mysql.cj.jdbc.Driver";
            Class.forName(driverName);
            String serverName = "localhost";    //server location eg. localhost, IP-Address, mysqlserver.com
            String schema = "testdb";           //database name
            String url = "jdbc:mysql://" + serverName +  "/" + schema;
            String username = "root";           //username
            String password = "1234567890";     //password
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database");
            stmt = conn.createStatement();
            int result = stmt.executeUpdate("INSERT INTO student(name,age) VALUES('John', 20)");
            result += stmt.executeUpdate("INSERT INTO student(name,age) VALUES('Mary', 21)");
            result += stmt.executeUpdate("INSERT INTO student(name,age) VALUES('Peter', 22)");
            result += stmt.executeUpdate("INSERT INTO student(name,age) VALUES('Jane', 23)");
            System.out.println("Inserted " + result + " rows successfully");
            rs = stmt.executeQuery("SELECT * FROM student order by student_id");
            while (rs.next()) {
                int id = rs.getInt("student_id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {rs.close();}
                if (stmt != null) {stmt.close();}
                if (conn != null) {conn.close();}
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
