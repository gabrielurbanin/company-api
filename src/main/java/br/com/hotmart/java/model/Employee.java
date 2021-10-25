package br.com.hotmart.java.model;

public class Employee {

    int id;
    String name;
    String cpf;
    // falta data
    byte gender;
    int idAdress;
    int idSupervisor;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public byte getGender() {
        return gender;
    }

    public int getIdAdress() {
        return idAdress;
    }

    public int getIdSupervisor() {
        return idSupervisor;
    }
}
