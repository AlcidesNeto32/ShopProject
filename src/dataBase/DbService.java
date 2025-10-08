package dataBase;

import entities.Product;
import org.w3c.dom.ls.LSOutput;

import java.sql.*;

public class DbService {
    Statement statement = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    public void putProduct(Product product) {
        try {
            connection = DbOperations.connection();
            preparedStatement = connection.prepareStatement("insert into product " +
                            "(productId,productName,quantity,price) " +
                            "values " +
                            "(?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setDouble(4, product.getPrice());
            int rows = preparedStatement.executeUpdate();

            if (rows > 0) {
                System.out.println("Done! " + product + " added with success!");
            } else {
                System.out.println("Ops! a error happened");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DbOperations.closePreparedStatement(preparedStatement);
            DbOperations.closeConnection(connection);
        }
    }

    public void showProduct(String name, Integer id) {
        try {
            connection = DbOperations.connection();
            preparedStatement = connection.prepareStatement("select * from product " +
                    "where " +
                    "productName = ? and productId = ?");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String findName = resultSet.getString("productName");
                Integer findId = resultSet.getInt("productId");
                if (findId.equals(null)){
                    System.out.println("This product does not exist!");
                    break;
                }
                double findPrice  = resultSet.getDouble("price");
                System.out.println("Product name: " + findName + " \nprice: " + findPrice + "\nid: " + findId);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DbOperations.closePreparedStatement(preparedStatement);
            DbOperations.closeConnection(connection);
        }
    }
}
