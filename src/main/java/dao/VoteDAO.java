/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;

import entities.Vote;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Le J c'est le S
 */
public class VoteDAO extends DAO<Vote> {

    public VoteDAO() {
        super("vote");
    }

    // a priori pas besoin 
    @Override
    protected Vote createObject(ResultSet rs) throws SQLException {
        Vote obj = new Vote();
        obj.setType_vote(rs.getBoolean("type_vote"));
        obj.setId_person(rs.getInt("id_person"));
        obj.setId_recipe(rs.getInt("id_recipe"));
        return obj;
    }
    
    
    /**
     * Cette méthode insère un nouvel enregistrement de vote dans la base de données.
     * L'ID du vote généré automatiquement est assigné à l'objet Vote passé en paramètre.
     * 
     * @param obj L'objet Vote à insérer dans la base de données.
     */
    @Override
    public void create(Vote obj) {
        String sql = "INSERT INTO " + table
                + " (id_person, id_recipe, type_vote) VALUES (?,?,?)";
        try ( PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, obj.getId_person());
            pstmt.setInt(2, obj.getId_recipe());
            pstmt.setBoolean(3,obj.isType_vote());

            int nbLines = pstmt.executeUpdate();
            /*if (nbLines == 1) {
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                generatedKeys.first();
                int id = generatedKeys.getInt(1);
                obj.setId_vote(id);*/
        } catch (SQLException ex) {
            Logger.getLogger(VoteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int count(int id_recipe) {
        int count = 0;
        String sql = " SELECT COUNT(*) AS c FROM " + table + " WHERE id_recipe=?" ;
        try ( PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id_recipe);
            ResultSet rs = pstmt.executeQuery();
            if (rs.first()) {
                count = rs.getInt("c");
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoteDAO.class.getName()).log(Level.SEVERE, "Erreur lors du comptage : " + ex.getMessage());
        }
        return count;
    }
    
    /**
 * Cette méthode compte le nombre de votes positifs associés à une recette spécifique.
 * 
 * @param id_recipe L'ID de la recette pour laquelle compter les votes positifs.
 * @return Le nombre de votes positifs pour la recette spécifiée.
 * @throws SQLException Si une erreur SQL se produit lors de l'interaction avec la base de données.
 */
    public int count_pos (int id_recipe) {
        int count = 0;
        String sql = "SELECT COUNT(*) AS c " +
                 " FROM " + table +
                 " WHERE id_recipe = ? AND type_vote = true";
        try ( PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id_recipe);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("c");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, "Erreur lors du comptage : " + ex.getMessage());
        }
        return count;
    }
    
    public int generateScore(int id_recipe) {
        int res = 0;
        int totalVotes = count(id_recipe);
        if (totalVotes > 0) {
            int positiveVotes = count_pos(id_recipe);
            res = (positiveVotes * 100) / totalVotes;
        }
        return res;
    }

    @Override
    protected void update(Vote obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    public boolean exists (Integer id_person, Integer id_recipe){
        String sql = "SELECT * FROM vote WHERE id_person=? AND id_recipe=?";
        boolean result = false;
        try ( PreparedStatement pstmt = connection.prepareStatement(sql)) {
        pstmt.setInt(1, id_person);
        pstmt.setInt(2, id_recipe);
        ResultSet rs = pstmt.executeQuery();
        if(rs.first()){
            result= true;
        }else{
            result= false;
        }
    }   catch (SQLException ex) {
            Logger.getLogger(VoteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
