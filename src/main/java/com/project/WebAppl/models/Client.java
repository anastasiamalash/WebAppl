package com.project.WebAppl.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String FIO, Email, Password;
    private int ClientsCode, Admin;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getClientsCode() {
        return ClientsCode;
    }

    public void setClientsCode(int clientsCode) {
        ClientsCode = clientsCode;
    }

    public int getAdmin() {
        return Admin;
    }

    public void setAdmin(int admin) {
        Admin = admin;
    }

    public Client() {
    }

    public Client(String FIO, String Email, String Password, int ClientsCode, int Admin) {
//        this.Id = Id;
        this.FIO = FIO;
        this.Email = Email;
        this.Password = Password;
        this.ClientsCode = ClientsCode;
        this.Admin = Admin;
    }
}
