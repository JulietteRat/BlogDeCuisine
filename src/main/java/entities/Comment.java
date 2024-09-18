/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author Jonathan DAH
 */
public class Comment {

    private Integer id_comment;
    private String content;
    private Timestamp created_c;
    private int status; //0 normal; 1 reported ; 2 validé ; 3 supprimé
    private Recipe id_recipe;
    private Person id_person;

    public Integer getId_comment() {
        return id_comment;
    }

    public void setId_comment(Integer id_comment) {
        this.id_comment = id_comment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreated_c() {
        return created_c;
    }

    public void setCreated_c(Timestamp created_c) {
        this.created_c = created_c;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Recipe getId_recipe() {
        return id_recipe;
    }

    public void setId_recipe(Recipe id_recipe) {
        this.id_recipe = id_recipe;
    }

    public Person getId_person() {
        return id_person;
    }

    public void setId_person(Person id_person) {
        this.id_person = id_person;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.id_comment);
        hash = 19 * hash + Objects.hashCode(this.content);
        hash = 19 * hash + Objects.hashCode(this.created_c);
        hash = 19 * hash + this.status;
        hash = 19 * hash + Objects.hashCode(this.id_recipe);
        hash = 19 * hash + Objects.hashCode(this.id_person);
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
        final Comment other = (Comment) obj;
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        if (!Objects.equals(this.id_comment, other.id_comment)) {
            return false;
        }
        if (!Objects.equals(this.created_c, other.created_c)) {
            return false;
        }
        if (!Objects.equals(this.id_recipe, other.id_recipe)) {
            return false;
        }
        return Objects.equals(this.id_person, other.id_person);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Comment{");
        sb.append("id_comment=").append(id_comment);
        sb.append(", content=").append(content);
        sb.append(", created_c=").append(created_c);
        sb.append(", status=").append(status);
        sb.append(", id_recipe=").append(id_recipe);
        sb.append(", id_person=").append(id_person);
        sb.append('}');
        return sb.toString();
    }
}
