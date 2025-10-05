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
            preparedStatement = connection.prepareStatement("insert into product" +
                            "(productId,productName,quantity,price)" +
                            "values" +
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
                System.out.println("Ops!  a error happened");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public void showProduct(String name, Integer id){
        try {
            connection = DbOperations.connection();
            statement = connection.createStatement();
            String sqlQuery = "select * from product ";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()){
                String findName = "";
                //TODO you need to end the query here ! And search an method better!
                String n = resultSet.getString("productName");
                if (n.equals(name) ){
                    findName = n;
                }
                System.out.println(findName);
            }
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }
}
