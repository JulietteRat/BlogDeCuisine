/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.util.Objects;

/**
 *
 * @author Ralph Lee <your.name at your.org>
 */
public class RecipeScoreVotable extends Recipe{
    
    private Integer score;
    private boolean votable;

    
    
    public void addScore(Recipe recipe, Integer score){
        this.setId_recipe(recipe.getId_recipe());
        this.setCreated_r(recipe.getCreated_r());
        this.setTitle(recipe.getTitle());
        this.setContent(recipe.getContent());
        this.setIngredients(recipe.getIngredients());

            this.setImg_url(recipe.getImg_url());

        this.setDifficulty(recipe.getDifficulty());
        this.setAuthor(recipe.getAuthor());
        this.score = score;
    }

    public boolean getVotable() {
        return votable;
    }

    public void setVotable(boolean votable) {
        this.votable = votable;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.score);
        hash = 17 * hash + (this.votable ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RecipeScoreVotable other = (RecipeScoreVotable) obj;
        if (this.votable != other.votable) {
            return false;
        }
        return Objects.equals(this.score, other.score);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RecipeScoreVotable{");
        sb.append("score=").append(score);
        sb.append(", votable=").append(votable);
        sb.append('}');
        return sb.toString();
    }
    
    
}
