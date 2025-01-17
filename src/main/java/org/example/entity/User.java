package org.example.entity;
import io.ebean.Model;
import io.ebean.annotation.Identity;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User  {

    @Id
    private Long id;
    private String name;
    private String email;


    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
