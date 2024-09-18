/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entities.Person;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jonathan DAH
 */
public class PersonDAO extends DAO<Person> {

    public PersonDAO() {
        super("person");
    }


    @Override
    protected Person createObject(ResultSet rs) throws SQLException {
        Person obj = new Person();
        obj.setId_person(rs.getInt("id_" + table));
        obj.setLogin(rs.getString("login"));
        obj.setPwd(rs.getString("pwd"));
        obj.setStatus(rs.getInt("status"));

        return obj;

    }

    @Override
    public void create(Person obj) {
        String sql = "INSERT INTO " + table + " (login, pwd, status) VALUES (?, ?, ?)";
        try ( PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, obj.getLogin());
            pstmt.setString(2, obj.getPwd());
            pstmt.setInt(3, obj.getStatus());
            int nbLines = pstmt.executeUpdate();
            if (nbLines == 1) {
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                generatedKeys.first();
                int id = generatedKeys.getInt(1);
                obj.setId_person(id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Person obj) {
        String sql = "UPDATE " + table
                + " SET login=?, pwd=?, status=? WHERE id_person=?";
        try ( PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, obj.getLogin());
            pstmt.setString(2, obj.getPwd());
            pstmt.setInt(3, obj.getStatus());
            pstmt.setInt(4, obj.getId_person());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Person read(String login) {
        Person obj = null;
        String sql = "SELECT * FROM " + table + " WHERE login=?";
        try ( PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, login);
            ResultSet rs = pstmt.executeQuery();
            if (rs.first()) {
                obj = createObject(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, "Erreur lors de la lecture : " + ex.getMessage());
        }
        return obj;
    }

    @Override
    public int count() {

        int count = 0;
        String sql = "SELECT COUNT(*) AS c FROM " + table;
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            if (rs.first()) {
                count = rs.getInt("c");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;

    }

    public Collection<Person> list() {
        ArrayList<Person> list = new ArrayList<>();
        String sql = "SELECT * FROM " + table;
        try ( PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Person obj = createObject(rs);
                list.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, "Erreur lors du listage : " + ex.getMessage());
        }
        return list;
    }
}
