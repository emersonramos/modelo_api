package br.com.automation.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB {
    public static void main() {
    
        try {
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
     } catch (ClassNotFoundException e1) {
         e1.printStackTrace();
     }
        String connectionUrl = "jdbc:sqlserver://ultrond.cctdpwuhuznc.sa-east-1.rds.amazonaws.com;databaseName=apolice;user=usr_app_apolice;password=u5r_@pp_ap0lic&";
        try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
            String SQL = "SELECT TOP 10 * FROM PROPOSTA";
            ResultSet rs = stmt.executeQuery(SQL);
            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                System.out.println(rs.getString("id_proposta") + " " + rs.getString("nr_proposta"));
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
 }