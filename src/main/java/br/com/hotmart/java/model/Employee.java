package br.com.hotmart.java.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String cpf;
    private LocalDate date;
    private Integer gender;
    private Integer idAdress;
    private Integer idSupervisor;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getGender() {
        return gender;
    }

    public Integer getIdAdress() {
        return idAdress;
    }

    public Integer getIdSupervisor() {
        return idSupervisor;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public void setIdAdress(Integer idAdress) {
        this.idAdress = idAdress;
    }

    public void setIdSupervisor(Integer idSupervisor) {
        this.idSupervisor = idSupervisor;
    }
}
