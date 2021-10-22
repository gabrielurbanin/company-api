package br.com.hotmart.java.model;

public class Funcionario {

    int id;
    String nome;
    String cpf;
    // falta data
    byte sexo;
    int idEndereco;
    int idSupervisor;

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setSexo(byte sexo) {
        this.sexo = sexo;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public void setIdSupervisor(int idSupervisor) {
        this.idSupervisor = idSupervisor;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public byte getSexo() {
        return sexo;
    }

    public int getIdEndereco() {
        return idEndereco;
    }

    public int getIdSupervisor() {
        return idSupervisor;
    }
}
