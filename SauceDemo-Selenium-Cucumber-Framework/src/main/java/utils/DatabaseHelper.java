package utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DatabaseHelper - Utility for database operations
 */
public class DatabaseHelper {

    /**
     * Get database connection
     * 
     * @return Connection object
     */
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return java.sql.DriverManager.getConnection(
                    ConfigReader.getDbUrl(),
                    ConfigReader.getDbUsername(),
                    ConfigReader.getDbPassword());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to database: " + e.getMessage());
        }
    }

    /**
     * Execute SELECT query and return results
     * 
     * @param query SQL SELECT query
     * @return List of maps containing row data
     */
    public static List<Map<String, String>> executeQuery(String query) {
        List<Map<String, String>> results = new ArrayList<>();

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                Map<String, String> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    String value = rs.getString(i);
                    row.put(columnName, value);
                }
                results.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to execute query: " + e.getMessage());
        }

        return results;
    }

    /**
     * Execute INSERT, UPDATE, DELETE queries
     * 
     * @param query SQL query
     * @return Number of rows affected
     */
    public static int executeUpdate(String query) {
        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement()) {
            return stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to execute update: " + e.getMessage());
        }
    }

    /**
     * Get single value from database
     * 
     * @param query      SQL query
     * @param columnName Column name to retrieve
     * @return Column value
     */
    public static String getSingleValue(String query, String columnName) {
        List<Map<String, String>> results = executeQuery(query);
        if (!results.isEmpty()) {
            return results.get(0).get(columnName);
        }
        return null;
    }

    /**
     * Check if record exists
     * 
     * @param query SQL query
     * @return true if record exists
     */
    public static boolean recordExists(String query) {
        List<Map<String, String>> results = executeQuery(query);
        return !results.isEmpty();
    }

    /**
     * Close connection
     * 
     * @param conn Connection to close
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
