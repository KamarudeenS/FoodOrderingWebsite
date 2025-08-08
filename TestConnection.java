package com.food.util;

import java.sql.Connection;

public class TestConnection {

	public static void main(String[] args) {
        Connection conn = DBConnection.getConnection();
        if (conn != null) {
            System.out.println("✅ DB Test Passed!");
        } else {
            System.out.println("❌ DB Test Failed!");
        }
    }
}
