package application;

import dataBase.DbService;
import entities.Product;

public class Program {
    public static void main(String[] args) {
        //Product product = new Product("Arroz",500,6.0,3);
        DbService dbService = new DbService();
        //dbService.putProduct(product);
        dbService.showProduct("Arroz",3);
    }
}
