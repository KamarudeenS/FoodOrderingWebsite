package com.food.daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.food.dao.OrderItemDAO;
import com.food.model.OrderItem;
import com.food.util.DBConnection;

public class OrderItemDAOImplementation implements OrderItemDAO {

    private static final String GETALLORDERITEMSQUERY = "SELECT * FROM `orderitem`";
    private static final String GETORDERITEMQUERY = "SELECT * FROM `orderitem` WHERE `orderitemid`=?";
    private static final String ADDORDERITEMQUERY = "INSERT INTO `orderitem`(orderid, menuid, quantity, totalammount) VALUES (?, ?, ?, ?)";
    private static final String UPDATEORDERITEMQUERY = "UPDATE `orderitem` SET `quantity`=?, `totalammount`=? WHERE `orderitemid`=?";
    private static final String DELETEORDERITEMQUERY = "DELETE FROM `orderitem` WHERE `orderitemid`=?";

    @Override
    public List<OrderItem> getAllOrderItems() {
        List<OrderItem> orderItems = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(GETALLORDERITEMSQUERY);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                OrderItem item = new OrderItem();
                item.setOrderItemId(rs.getInt("orderitemid"));
                item.setOrderId(rs.getInt("orderid"));
                item.setMenuId(rs.getInt("menuid"));
                item.setQuantity(rs.getInt("quantity"));
                item.setTotalAmmount(rs.getInt("totalammount"));

                orderItems.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderItems;
    }

    @Override
    public OrderItem getOrderItem(int orderItemId) {
        OrderItem item = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(GETORDERITEMQUERY)) {

            ps.setInt(1, orderItemId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                item = new OrderItem();
                item.setOrderItemId(rs.getInt("orderitemid"));
                item.setOrderId(rs.getInt("orderid"));
                item.setMenuId(rs.getInt("menuid"));
                item.setQuantity(rs.getInt("quantity"));
                item.setTotalAmmount(rs.getInt("totalammount"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;
    }

    @Override
    public void addOrderItem(OrderItem orderItem) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(ADDORDERITEMQUERY)) {

            ps.setInt(1, orderItem.getOrderId());
            ps.setInt(2, orderItem.getMenuId());
            ps.setInt(3, orderItem.getQuantity());
            ps.setInt(4, orderItem.getTotalAmmount());

            int result = ps.executeUpdate();
            System.out.println("OrderItem added: " + result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATEORDERITEMQUERY)) {

            ps.setInt(1, orderItem.getQuantity());
            ps.setInt(2, orderItem.getTotalAmmount());
            ps.setInt(3, orderItem.getOrderItemId());

            int result = ps.executeUpdate();
            System.out.println("OrderItem updated: " + result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrderItem(int orderItemId) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETEORDERITEMQUERY)) {

            ps.setInt(1, orderItemId);

            int result = ps.executeUpdate();
            System.out.println("OrderItem deleted: " + result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
