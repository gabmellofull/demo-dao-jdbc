package application;

import db.DB;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.util.Date;

public class Program {

    public static void main(String[] args) {

        Department obj = new Department(1, "Books");

        Seller seller = new Seller(21, "Bb", "bob@gmail.com", new Date(), 300.00, obj);

        System.out.println(seller);

        //Connection conn = DB.getConnection(); // faz a conexão
        //DB.closeConnection(); // fecha a conexão

    }

}
