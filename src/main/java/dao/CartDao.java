package dao;

import model.Cart;
import model.Product;
import model.User;
import util.Dbutil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CartDao extends Dbutil {

    public void removeFromCart(int productId) {
        String query;

        query = "delete from cart where product_id=";
        query += productId + " ";
        query += "and address is NULL;";

        execUpdate(query);
    }

    public void removeFromCart(int productId, String email) {
        String query;

        query = "delete from cart where product_id=";
        query += "'" + productId + "'";
        query += " and email=";
        query += "'" + email + "'";
        query += " and address is NULL;";

        execUpdate(query);
    }

    public void updateCartDetails (Cart cart) throws SQLException {
        String query;

        query = "update users set address=";
        query += "'" + cart.getAddress() + "',";
        query += "city=";
        query += "'" + cart.getCity() + "',";
        query += "state=";
        query += "'" + cart.getState()+ "',";
        query += "country=";
        query += "'" + cart.getCountry() + "',";
        query += "mobileNumber=";
        query += "'" + cart.getMobileNumber() + "'";
        query += " where email=";
        query += "'" + cart.getEmail() + "';";

        execUpdate(query);
    }

    public void updateCartPayment (Cart cart) throws SQLException {
        String query;

        query = "update cart set address=";
        query += "'" + cart.getAddress() + "',";
        query += "city=";
        query += "'" + cart.getCity() + "',";
        query += "state=";
        query += "'" + cart.getState()+ "',";
        query += "country=";
        query += "'" + cart.getCountry() + "',";
        query += "mobileNumber=";
        query += "'" + cart.getMobileNumber() + "',";
        query += "orderDate=now(),";
        query += "deliveryDate=DATE_ADD(orderDate,INTERVAL 7 DAY),";
        query += "paymentMethod=";
        query += "'" + cart.getPaymentMethod() + "',";
        query += "transactionId=";
        query += "'" + cart.getTransactionId() + "',";
        query += "status=";
        query += "'" + cart.getStatus() + "'";
        query += " where email=";
        query += "'" + cart.getEmail() + "'";
        query += " and address is NULL;";

        execUpdate(query);


        execUpdate(query);
    }

    public ResultSet checkProductInCart(String product_id, String email) throws SQLException {
        String query;

        query = "select * from cart where product_id=";
        query += "'" + product_id + "'";
        query += " and email=";
        query += "'" + email + "'";
        query += " and address is NULL;";

        return execQuery(query);
    }

    public void updateProductInCart(Cart cart) {
        String query;

        query = "update cart set total=";
        query += "'" + cart.getTotal() + "',";
        query += "quantity=";
        query += "'" + cart.getQuantity() + "'";
        query += " where product_id=";
        query += "'" + cart.getProductId() + "'";
        query += " and email=";
        query += "'" + cart.getEmail() + "'";
        query += " and address is NULL;";

        execUpdate(query);
    }

    public void addToCart(Cart cart) {
        String query;

        query = "insert into cart(email,product_id,quantity,price,total) values(";
        query += "'" + cart.getEmail() + "',";
        query += "'" + cart.getProductId() + "',";
        query += "'" + cart.getQuantity() + "',";
        query += "'" + cart.getPrice() + "',";
        query += "'" + cart.getTotal() + "');";

        execUpdate(query);
    }

    public ResultSet cartTotal(String email) {
        String query;

        query = "select sum(total) from cart where email=";
        query += "'" + email + "'";
        query += " and address is NULL;";

        return execQuery(query);
    }

    public ResultSet cartBillTotal(String email) {
        String query;

        query = "select sum(total) from cart where email=";
        query += "'" + email + "'";
        query += " and status=";
        query += "'bill';";

        return execQuery(query);
    }

    public ResultSet innerJoin(String email) {
        String query;

        query = "select * from product inner join cart on product.id=cart.product_id";
        query += " and cart.email=";
        query += "'" + email + "'";
        query += " and cart.address is NULL;";

        return execQuery(query);
    }

    public ResultSet innerJoinOnProduct(String email) {
        String query;

        query = "select * from cart inner join product where cart.product_id=product.id";
        query += " and cart.email=";
        query += "'" + email + "'";
        query += " and cart.status='bill';";

        return execQuery(query);
    }

    public ResultSet innerJoinOnOrders(String email) {
        String query;

        query = "select * from cart inner join product where cart.product_id=product.id";
        query += " and cart.email=";
        query += "'" + email + "'";
        query += " and cart.orderDate is not NULL;";

        return execQuery(query);
    }

    public ResultSet innerJoinOnCart() {
        String query;

        query = "select * from cart inner join product where cart.product_id=product.id";
        query += " and cart.orderDate is not NULL and cart.status='processing';";

        return execQuery(query);
    }

    public ResultSet deliveredProductsInCart(String status) {
        String query;

        query = "select * from cart inner join product where cart.product_id=product.id";
        query += " and cart.orderDate is not NULL and cart.status=";
        query += "'" + status + "';";

        return execQuery(query);
    }

    public void updateCartStatus(String email, String status) {
        String query;

        query = "update cart set status=";
        query += "'" + status + "'";
        query += " and email=";
        query += "'" + email + "'";
        query += " and status='bill';";

        execUpdate(query);
    }

    public void updateCartStatus(Cart cart) {
        String query;

        query = "update cart set status=";
        query += "'" + cart.getStatus() + "'";
        query += " and email=";
        query += "'" + cart.getEmail() + "'";
        query += " and product_id=";
        query += "'" + cart.getProductId() + "'";
        query += " and address in not NULL;";

        execUpdate(query);
    }
}