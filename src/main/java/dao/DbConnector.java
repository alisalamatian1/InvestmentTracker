package dao;

import constants.Constant;

import java.sql.*;

public class DbConnector {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    public DbConnector() {}

    public void setConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/investmentTracker",
                    Constant.DB_USER, Constant.DB_PASSWORD);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String select(String username, String password) {
        String id = username + password;
        try{
            PreparedStatement statement = connection.prepareStatement("select * from inf where id = ?");
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                return resultSet.getString("stock");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException sqlEx) {

                }
                resultSet = null;
            }

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqlEx) {
                }
                statement = null;
            }
        }
        return "";
    }
}
