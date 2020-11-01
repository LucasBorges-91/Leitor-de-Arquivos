package br.com.leitordearquivos.model;

import java.util.Objects;

public class Vendedor {

  private String cpf;
  private String nome;
  private double salario;

  public Vendedor( String cpf, String nome, double salario ) {
    this.cpf = cpf;
    this.nome = nome;
    this.salario = salario;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Vendedor vendedor = (Vendedor) o;
    return Double.compare(vendedor.salario, salario) == 0 &&
        Objects.equals(cpf, vendedor.cpf) &&
        Objects.equals(nome, vendedor.nome);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cpf, nome, salario);
  }
}
