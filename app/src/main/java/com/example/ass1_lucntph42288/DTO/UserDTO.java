package com.example.ass1_lucntph42288.DTO;

public class UserDTO {

    int id;
    String username;
    String email;
    String password;
    String fullname;

    public UserDTO() {
    }

    public UserDTO(String username, String email, String password, String fullname) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
    }

    public UserDTO(int id, String username, String email, String password, String fullname) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
