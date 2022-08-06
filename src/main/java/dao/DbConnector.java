package dao;

import constants.Constant;
import constants.Queries;
import model.UserProfileAndWallet;
import org.json.JSONObject;

import java.sql.*;

public class DbConnector {
    public static Connection connection = null;
    public static Statement statement = null;
    public static ResultSet resultSet = null;
    public DbConnector() {}

    public static void setConnection() {
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

    public static String select(String id) {
        setConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(Queries.SELECT_ALL);
            statement.setString(1, id);
            System.out.println(statement.toString());
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                String result = resultSet.getString("profileAndWallet");
                System.out.println(result);
                return result;
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
                    System.out.println(sqlEx.getMessage());
                }
                resultSet = null;
            }

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqlEx) {
                    System.out.println(sqlEx.getMessage());
                }
                statement = null;
            }
        }
        return "";
    }

    // EFFECTS: writes JSON representation of UserProfileAndWallet to database
    public static void write(UserProfileAndWallet userProfileAndWallet, String id) {
        setConnection();
        JSONObject json = userProfileAndWallet.toJsonObject();
        String jsonString = json.toString();
        try{
            PreparedStatement statement = connection.prepareStatement(Queries.INSERT);
            statement.setString(1, id);
            statement.setString(2, jsonString);
            System.out.println(statement.toString());
            statement.execute();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    // EFFECTS: writes JSON representation of UserProfileAndWallet to database
    public static void update(UserProfileAndWallet userProfileAndWallet, String id) {
        JSONObject json = userProfileAndWallet.toJsonObject();
        String jsonString = json.toString();
        try{
            PreparedStatement statement = connection.prepareStatement(Queries.UPDATE);
            statement.setString(1, jsonString);
            statement.setString(2, id);
            System.out.println(statement.toString());
            statement.execute();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    public static String checkIfIdExists(String id) {
        setConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(Queries.SELECT_COUNT);
            statement.setString(1, id);
            System.out.println(statement.toString());
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                return resultSet.getString("count(*)");
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
                    System.out.println(sqlEx.getMessage());
                }
                resultSet = null;
            }

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqlEx) {
                    System.out.println(sqlEx.getMessage());
                }
                statement = null;
            }
        }
        return "";
    }
}
