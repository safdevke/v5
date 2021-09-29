package dao;

import model.Product;
import util.Dbutil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao extends Dbutil {

    public void addProduct(Product product) {
        String query;

        query = "insert into product values(";
        query += "'" + product.getId( ) + "',";
        query += "'" + product.getName( ) + "',";
        query += "'" + product.getCategory( ) + "',";
        query += product.getPrice( ) + ",";
        query += "'" + product.getStatus( ) + "');";

        execUpdate(query);
    }

    public List<Product> listAllProducts() throws SQLException {
        String query;

        query = "select * from product;";
        List<Product> products = new ArrayList<>( );
        ResultSet rs = execQuery(query);
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getString("id"));
            product.setName(rs.getString("name"));
            product.setCategory(rs.getString("category"));
            product.setPrice(rs.getDouble("price"));
            product.setStatus(rs.getString("active"));
            products.add(product);
        }
        rs.close();

        return products;
    }

    public ResultSet listById(String productId) throws SQLException {
        String query;

        query = "select * from product where id=";
        query += "'" + productId + "';";
        return execQuery(query);
    }

    public Product fetchProduct(String productId) throws SQLException {
        String query;

        query = "select * from product where id=";
        query += "'" + productId + "';";
        Product product = new Product( );
        ResultSet rs = execQuery(query);
        while (rs.next()) {
            product.setId(rs.getString("id"));
            product.setName(rs.getString("name"));
            product.setCategory(rs.getString("category"));
            product.setPrice(rs.getDouble("price"));
            product.setStatus(rs.getString("active"));
        }
        return product;
    }

    public void updateProduct(Product product) {
        String query;

        query = "update product set name=";
        query += "'" + product.getName() + "',";
        query += "category=";
        query += "'" + product.getCategory() + "',";
        query += "price=";
        query += product.getPrice() + ",";
        query += "active=";
        query += "'" + product.getStatus() + "';";

        execUpdate(query);
    }

    public List<Product> fetchAllActiveProducts() throws SQLException {
        String query;

        query = "select * from product where active='Yes';";
        List<Product> products = new ArrayList<>( );
        ResultSet rs = execQuery(query);
        while (rs.next( )) {
            Product product = new Product( );
            product.setId(rs.getString("id"));
            product.setName(rs.getString("name"));
            product.setCategory(rs.getString("category"));
            product.setPrice(rs.getDouble("price"));
            product.setStatus(rs.getString("active"));
            products.add(product);
        }
        return products;
    }



    public List<Product> searchProduct(String search) throws SQLException {
        String query;

        query = "select * from product where name like '%" + search + "%'";
        query += " or category  like '%" + search + "%'";
        query += " and active='Yes';";
        List<Product> products = new ArrayList<>( );
        ResultSet rs = execQuery(query);
        while (rs.next( )) {
            Product product = new Product( );
            product.setId(rs.getString("id"));
            product.setName(rs.getString("name"));
            product.setCategory(rs.getString("category"));
            product.setPrice(rs.getDouble("price"));
            product.setStatus(rs.getString("active"));
            products.add(product);
        }
        return products;
    }
}

