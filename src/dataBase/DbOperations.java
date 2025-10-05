package dataBase;

import java.sql.*;

public class DbOperations {
    static Connection connection = null;
    public static Connection connection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(InformationDb.URL, InformationDb.USER, InformationDb.PASSWORD);
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        } else {
            System.out.println("[ERROR] Can't close the connection!");
        }
    }

    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        } else {
            System.out.println("[ERROR] Can't close the statement!");
        }
    }

    public static void closePreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        } else {
            System.out.println("Can't close the PreparedStatement!");
        }
    }
}
