package com.example.testgit;
public class users {
    int ID = 0;
    String user_name;
    String phone_number;
    String email;
    String last_name;
    public users(int ID, String userName, String phoneNumber, String email, String lastName) {
        this.ID = this.ID;
        this.user_name = user_name;
        this.phone_number = phone_number;
        this.email = email;
        this.last_name = last_name;
        //constructor - בנאי
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = this.ID;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
