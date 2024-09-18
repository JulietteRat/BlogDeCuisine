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
public class Recipe {

    private Integer id_recipe;
    private Timestamp created_r;
    private String title;
    private String content;
    private String ingredients;
    private String img_url;
    private int difficulty;
    private Person author;

    public Integer getId_recipe() {
        return id_recipe;
    }

    public void setId_recipe(Integer id_recipe) {
        this.id_recipe = id_recipe;
    }

    public Timestamp getCreated_r() {
        return created_r;
    }

    public void setCreated_r(Timestamp created_r) {
        this.created_r = created_r;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Recipe{");
        sb.append("id_recipe=").append(id_recipe);
        sb.append(", created_r=").append(created_r);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", ingredients=").append(ingredients);
        sb.append(", img_url=").append(img_url);
        sb.append(", difficulty=").append(difficulty);
        sb.append(", author=").append(author);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id_recipe);
        hash = 53 * hash + Objects.hashCode(this.created_r);
        hash = 53 * hash + Objects.hashCode(this.title);
        hash = 53 * hash + Objects.hashCode(this.content);
        hash = 53 * hash + Objects.hashCode(this.ingredients);
        hash = 53 * hash + Objects.hashCode(this.img_url);
        hash = 53 * hash + this.difficulty;
        hash = 53 * hash + Objects.hashCode(this.author);
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
        final Recipe other = (Recipe) obj;
        if (this.difficulty != other.difficulty) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        if (!Objects.equals(this.ingredients, other.ingredients)) {
            return false;
        }
        if (!Objects.equals(this.img_url, other.img_url)) {
            return false;
        }
        if (!Objects.equals(this.id_recipe, other.id_recipe)) {
            return false;
        }
        if (!Objects.equals(this.created_r, other.created_r)) {
            return false;
        }
        return Objects.equals(this.author, other.author);
    }

}


