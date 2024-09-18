/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entities.Comment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stag
 */
public class CommentDAO extends DAO<Comment> {

    public CommentDAO() {
        super("comment");
    }

    @Override
    protected Comment createObject(ResultSet rs) throws SQLException {
        Comment obj = new Comment();
        obj.setId_comment(rs.getInt("id_" + table));
        obj.setId_person(new PersonDAO().read(rs.getInt("id_person")));
        obj.setId_recipe(new RecipeDAO().read(rs.getInt("id_recipe")));
        obj.setStatus(rs.getInt("status"));
        obj.setCreated_c(rs.getTimestamp("created_c"));
        obj.setContent(rs.getString("content"));
        return obj;

    }

    @Override
    public void create(Comment obj) {
        String sql = "INSERT INTO " + table
                + " (content, created_c, status,id_person, id_recipe) VALUES (?, ?, ?, ?,?)";
        try ( PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, obj.getContent());
            pstmt.setTimestamp(2, obj.getCreated_c());
            pstmt.setInt(3, obj.getStatus());
            pstmt.setInt(4, obj.getId_person().getId_person());
            pstmt.setInt(5, obj.getId_recipe().getId_recipe());

            int nbLines = pstmt.executeUpdate();
            if (nbLines == 1) {
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                generatedKeys.first();
                int id = generatedKeys.getInt(1);
                obj.setId_comment(id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Comment obj) {
        String sql = "UPDATE " + table
                + " SET content=?, created_c=?, status=?,id_person=?, id_recipe=? WHERE id_" + table + "=?";
        try ( PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, obj.getContent());
            pstmt.setTimestamp(2, obj.getCreated_c());
            pstmt.setInt(3, obj.getStatus());
            pstmt.setInt(4, obj.getId_person().getId_person());
            pstmt.setInt(5, obj.getId_recipe().getId_recipe());
            pstmt.setInt(6, obj.getId_comment());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updatestatusonly(Comment obj) {
        String sql = "UPDATE " + table
                + " SET status=? WHERE id_" + table + "=?";
        try ( PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, obj.getStatus());
            pstmt.setInt(2, obj.getId_comment());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    when user is deleted, the content of his comments is being changed to "Comment est suprime"
    public void updateContent(Integer id) {
        String sql = "UPDATE " + table + " SET content=?, status=3 WHERE id_person=?";
        try ( PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, "Commentaire supprimé, ça pédale dans la choucroute !");
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return la liste des commentaire signalés ( de status 1)
     */
    public Collection<Comment> reportedcommentlist() {
        ArrayList<Comment> list = new ArrayList<>();
        String sql = "SELECT * FROM " + table + " WHERE status=1 ORDER BY created_c DESC";
        try ( PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Comment obj = createObject(rs);
                System.out.println(obj);
                list.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, "Erreur lors du listage : " + ex.getMessage());
        }
        return list;
    }


    public Collection<Comment> listSortComment(int id_recipe) {
        ArrayList<Comment> list = new ArrayList<>();
        String sql = "SELECT * FROM " + table + " WHERE id_recipe=? ORDER BY created_c DESC";
        try ( PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id_recipe);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Comment obj = createObject(rs);
                list.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, "Erreur lors du listage : " + ex.getMessage());
        }
        return list;
    }

}
