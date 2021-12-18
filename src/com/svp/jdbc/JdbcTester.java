package com.svp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcTester {
    public static void main(String[] args) {
        String jdbcUrl="jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false";
        String jdbcUser="hbstudent";
        String jdbcPassword="hbstudent";
        try {
            System.out.println("Connecting to database "+ jdbcUrl);
            Connection connection= DriverManager.getConnection(jdbcUrl,jdbcUser,jdbcPassword);
            System.out.println("Connecton is successful");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
