/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Jonathan DAH
 */
class MariadbConnection {
    private static Connection connection;

    private MariadbConnection() {
    }

    public static Connection getInstance() {
        if (connection == null) {
            String url = String.format("%s://%s:%s/%s", "jdbc:mariadb", "wp.ldnr.fr", "3306", "cda202302_jee1");
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                connection = DriverManager.getConnection(url, "cda202302_jee1", "Licorne2024");
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(MariadbConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return connection;
    }
}
