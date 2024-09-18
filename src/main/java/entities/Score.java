/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.util.Comparator;
import java.util.Objects;

/**
 *
 * @author Ralph Lee <your.name at your.org>
 */
public class Score implements Comparable<Score> {

    private Integer id_recipe;
    private Integer score;

    public Integer getId_recipe() {
        return id_recipe;
    }

    public void setId_recipe(Integer id_recipe) {
        this.id_recipe = id_recipe;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Score{");
        sb.append("id_recipe=").append(id_recipe);
        sb.append(", score=").append(score);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.id_recipe);
        hash = 47 * hash + Objects.hashCode(this.score);
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
        final Score other = (Score) obj;
        if (!Objects.equals(this.id_recipe, other.id_recipe)) {
            return false;
        }
        return Objects.equals(this.score, other.score);
    }

    @Override
    public int compareTo(Score sc) {
        return (this.score - sc.getScore());
    }

    public static Comparator<Score> ScoreComparator = new Comparator<Score>() {
        @Override
        public int compare(Score t, Score t1) {
            return t.getScore().compareTo(t1.getScore());
        }

    };
    
    
}
