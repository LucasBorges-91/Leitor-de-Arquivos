package br.com.leitordearquivos.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Venda {

  private int vendaId;
  private List<Item> itensVenda = new ArrayList<>();
  private String nomeVendedor;
  private double totalVenda;

  public Venda(int vendaId, List<Item> itensVenda, String nomeVendedor, double totalVenda) {
    this.vendaId = vendaId;
    this.itensVenda = itensVenda;
    this.nomeVendedor = nomeVendedor;
    this.totalVenda = totalVenda;
  }

  public int getVendaId() {
    return this.vendaId;
  }

  public String getNomeVendedor() {
    return this.nomeVendedor;
  }

  public double getTotalVenda() {
    return this.totalVenda;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Venda venda = (Venda) o;
    return vendaId == venda.vendaId &&
        Double.compare(venda.totalVenda, totalVenda) == 0 &&
        Objects.equals(nomeVendedor, venda.nomeVendedor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vendaId, nomeVendedor, totalVenda);
  }
}
