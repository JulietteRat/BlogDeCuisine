/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.util.Objects;

/**
 *
 * @author Jonathan DAH
 */
public class Vote {
    private Integer id_person;
    private Integer id_recipe;
    private boolean type_vote;

    public Vote() {
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id_person);
        hash = 71 * hash + Objects.hashCode(this.id_recipe);
        hash = 71 * hash + (this.type_vote ? 1 : 0);
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
        final Vote other = (Vote) obj;
        if (this.type_vote != other.type_vote) {
            return false;
        }
        if (!Objects.equals(this.id_person, other.id_person)) {
            return false;
        }
        return Objects.equals(this.id_recipe, other.id_recipe);
    }

    public Integer getId_person() {
        return id_person;
    }

    public void setId_person(Integer id_person) {
        this.id_person = id_person;
    }

    public Integer getId_recipe() {
        return id_recipe;
    }

    public void setId_recipe(Integer id_recipe) {
        this.id_recipe = id_recipe;
    }

    public boolean isType_vote() {
        return type_vote;
    }

    public void setType_vote(boolean type_vote) {
        this.type_vote = type_vote;
    }
}
