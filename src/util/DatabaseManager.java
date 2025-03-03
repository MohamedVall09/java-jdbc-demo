package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManager {
    // Singleton instance of the database connection
    private static Connection connection;

    // Database connection details
    private static String dbUrl;
    private static String dbUsername;
    private static String dbPassword;

    // Logger instance for logging
   private static final Logger logger = LoggerFactory.getLogger(DatabaseManager.class);

    // Private constructor to prevent instantiation
    private DatabaseManager() {}

    /**
     * Loads the database connection details from a properties file.
     * This should be called before trying to establish a connection.
     * @param filePath The path to the properties file in mariadb.properties
     */
    private static void loadDatabaseConfig(String filePath) {
        try (FileInputStream input = new FileInputStream(filePath)) {
            Properties properties = new Properties();
            properties.load(input);
            dbUrl = properties.getProperty("db.url");
            dbUsername = properties.getProperty("db.username");
            dbPassword = properties.getProperty("db.password");

           // logger.info("✅ Database configuration loaded successfully!");
            System.out.println("✅ Database configuration loaded successfully!");
        } catch (IOException e) {
           // logger.error("❌ Failed to load the database configuration file", e);
            throw new RuntimeException("Failed to load database configuration", e);
        }
    }

    /**
     * Gets the database connection.
     * If the connection doesn't exist, it will be created.
     * This method ensures only a single instance of the connection is used (Singleton pattern).
     * @return The database connection instance
     */
    public static Connection getConnection() {
        if (connection == null) {
            synchronized (DatabaseManager.class) {
                if (connection == null) {
                    // Load configuration from file (adjust path if necessary)
                    loadDatabaseConfig("mariadb.properties");

                    try {
                        connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
                        logger.info("✅ Database connection established!");
                    } catch (SQLException e) {
                        logger.error("❌ Failed to establish the connection", e);
                        throw new RuntimeException("Failed to establish the connection", e);
                    }
                }
            }
        }
        return connection;
    }

    /**
     * Tests the database connection by attempting to connect.
     * Logs the result of the connection test.
     */
    public static void testDatabaseConnection() {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                logger.info("✅ DB connection successful!");
            }
        } catch (SQLException e) {
            logger.error("❌ Connection failed: ", e);
        }
    }

    /**
     * Closes the database connection if it exists.
     * Logs the result of the connection close attempt.
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null; // Set to null after closing the connection
                logger.info("✅ DB connection closed!");
            } catch (SQLException e) {
                logger.error("❌ Failed to close the connection", e);
            }
        }
    }
}