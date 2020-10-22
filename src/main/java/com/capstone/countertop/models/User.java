package com.capstone.countertop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    @Size(min = 3, max = 24)
    private String username;

    @Column(nullable = false)
    @JsonIgnore
    @Size(min = 8, max = 50)
    private String password;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date signupDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy") // This is for bind Date with @ModelAttribute
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dob;

    @Column(nullable = false)
    private String url;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Recipe> recipes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Comment> comments;

    public User(long id, String email,
                @Size(min = 3, max = 24) String username,
                @Size(min = 8, max = 50) String password,
                Date signupDate,
                Date dob,
                String url) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.signupDate = signupDate;
        this.dob = dob;
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getSignupDate() {
        return signupDate;
    }

    public void setSignupDate(Date signupDate) {
        this.signupDate = signupDate;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
