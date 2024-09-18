/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entities.Person;
import entities.Recipe;
import entities.RecipeScoreVotable;
import entities.Score;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ralph Lee <your.name at your.org>
 */
public class RecipeDAO extends DAO<Recipe> {

    public RecipeDAO() {
        super("recipe");
    }

    @Override
    protected Recipe createObject(ResultSet rs) throws SQLException {
        Recipe obj = new Recipe();
        obj.setId_recipe(rs.getInt("id_" + table));
        obj.setCreated_r(rs.getTimestamp("created_r"));
        obj.setTitle(rs.getString("title"));
        obj.setContent(rs.getString("content"));
        obj.setIngredients(rs.getString("ingredients"));
        obj.setImg_url(rs.getString("img_url"));
        obj.setDifficulty(rs.getInt("difficulty"));
        obj.setAuthor(new PersonDAO().read(rs.getInt("id_person")));

        return obj;
    }

    @Override
    public void create(Recipe obj) {
        String sql = "INSERT INTO " + table
                + " (created_r, title, content, ingredients, img_url, difficulty,"
                + "id_person) VALUES (?,?,?,?,?,?,?)";
        try ( PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setTimestamp(1, obj.getCreated_r());
            pstmt.setString(2, obj.getTitle());
            pstmt.setString(3, obj.getContent());
            pstmt.setString(4, obj.getIngredients());
//            if (obj.getImg_url() != null) {
                pstmt.setString(5, obj.getImg_url());
//            }
            pstmt.setInt(6, obj.getDifficulty());
            pstmt.setInt(7, obj.getAuthor().getId_person());
            int nbLines = pstmt.executeUpdate();
            if (nbLines == 1) {
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                generatedKeys.first();
                int id = generatedKeys.getInt(1);
                obj.setId_recipe(id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecipeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void update(Recipe obj) {
        String sql = "UPDATE " + table
                + " SET created_r=?, title=?, content=?, ingredients=?, img_url=?,"
                + " difficulty=?, id_person=? WHERE id_" + table + "=?";
        try ( PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setTimestamp(1, obj.getCreated_r());
            pstmt.setString(2, obj.getTitle());
            pstmt.setString(3, obj.getContent());
            pstmt.setString(4, obj.getIngredients());
            pstmt.setString(5, obj.getImg_url());
            pstmt.setInt(6, obj.getDifficulty());
            pstmt.setInt(7, obj.getAuthor().getId_person());
            pstmt.setInt(8, obj.getId_recipe());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RecipeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Collection<RecipeScoreVotable> listLast(int qty) {
        ArrayList<RecipeScoreVotable> list = new ArrayList<>();
        String sql = "SELECT * FROM " + table + " ORDER BY created_r DESC LIMIT ?";
        try ( PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, qty);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Recipe obj = createObject(rs);
                VoteDAO vdao = new VoteDAO();
                RecipeScoreVotable rws =new RecipeScoreVotable();
                rws.addScore(obj, vdao.generateScore(obj.getId_recipe()));
                rws.setVotable(checkDate(obj.getCreated_r()));
                list.add(rws);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecipeDAO.class.getName()).log(Level.SEVERE, "Erreur lors du listage : {0}", ex.getMessage());
        }
        return list;
    }

    public Collection<Recipe> listantichronological() {
        ArrayList<Recipe> list = new ArrayList<>();
        String sql = "SELECT * FROM " + table + " ORDER BY created_r DESC";
        try ( PreparedStatement pstmt = connection.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Recipe obj = createObject(rs);
                list.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecipeDAO.class.getName()).log(Level.SEVERE, "Erreur lors du listage : {0}", ex.getMessage());
        }
        return list;
    }

    public Collection<RecipeScoreVotable> listBest(int qty) {
        VoteDAO vdao = new VoteDAO();
        ArrayList<Recipe> list = new ArrayList<>();
        list = (ArrayList) this.list();
        ArrayList<Score> scores = new ArrayList<>();
        ArrayList<RecipeScoreVotable> theBest = new ArrayList<>();

        for (Recipe recipe : list) {
            Score score = new Score();
            score.setId_recipe(recipe.getId_recipe());
            score.setScore(vdao.generateScore(recipe.getId_recipe()));
            scores.add(score);
        }

        // Trier les scores du plus bas au plus haut
        scores.sort(Comparator.comparingInt(Score::getScore));

        int validCount = 0;
        int index = 1;
        while (validCount < qty && index <= scores.size()) {
            int currentIndex = scores.size() - index;
            if (currentIndex >= 0) {
                Integer id_recipe = scores.get(currentIndex).getId_recipe();
                if (checkDate(this.read(id_recipe).getCreated_r())) {
                    RecipeScoreVotable rsv = new RecipeScoreVotable();
                    Recipe currentRecipe = this.read(id_recipe);
                    rsv.addScore(currentRecipe, vdao.generateScore(id_recipe));
                    rsv.setVotable(true);
                    theBest.add(rsv);
                    validCount++;
                }
            }
            index++;
        }

        return theBest;
    }
//    commented by anastasiia because it spoils my pictures
    //    commented by anastasiia because it spoils my pictures
//    public Collection<RecipeScoreVotable> listSortId(int id) {
//        ArrayList<RecipeScoreVotable> list = new ArrayList<>();
//        String sql = "SELECT * FROM " + table + " WHERE id_person=?";
//        try ( PreparedStatement pstmt = connection.prepareStatement(sql)) {
//            pstmt.setInt(1, id);
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                Recipe obj = createObject(rs);
//                VoteDAO vdao = new VoteDAO();
//                RecipeScoreVotable rws =new RecipeScoreVotable();
//                rws.addScore(obj, vdao.generateScore(obj.getId_recipe()));
//                rws.setVotable(checkDate(obj.getCreated_r()));
//                list.add(rws);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(RecipeDAO.class.getName()).log(Level.SEVERE, "Erreur lors du listage : {0}", ex.getMessage());
//        }
//        return list;
//    }
    //added by anastasiia to display list of recipes in profile based on id of user

    public Collection<Recipe> listSortId(int id) {
        ArrayList<Recipe> list = new ArrayList<>();
        String sql = "SELECT * FROM " + table + " WHERE id_person=?";
        try ( PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Recipe obj = createObject(rs);
                list.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecipeDAO.class.getName()).log(Level.SEVERE, "Erreur lors du listage : {0}", ex.getMessage());
        }
        return list;
    }


    
    public boolean checkDate(Timestamp created) {
       Calendar creation =Calendar.getInstance();
       Calendar rightNow = Calendar.getInstance();
       creation.setTimeInMillis(created.getTime());
       creation.add(Calendar.MONTH,1);
       
        return creation.compareTo(rightNow) > 0;
            
    }
        public void deleteallrecipe(Integer id) {
        String sql = "DELETE FROM " + table + " WHERE id_person=?";
        try ( PreparedStatement pstmt = connection.prepareStatement(sql);) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, "Erreur lors du delete : " + ex.getMessage());
        }
    }

    
    
    

}
