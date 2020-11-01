package br.com.leitordearquivos.model;

import java.util.Objects;

public class Cliente {

  private String cnpj;
  private String nome;
  private String areaDeTrabalho;

  public Cliente( String cnpj, String nome, String areaDeTrabalho ) {
    this.cnpj = cnpj;
    this.nome = nome;
    this.areaDeTrabalho = areaDeTrabalho;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Cliente cliente = (Cliente) o;
    return Objects.equals(cnpj, cliente.cnpj) &&
        Objects.equals(nome, cliente.nome) &&
        Objects.equals(areaDeTrabalho, cliente.areaDeTrabalho);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cnpj, nome, areaDeTrabalho);
  }
}
