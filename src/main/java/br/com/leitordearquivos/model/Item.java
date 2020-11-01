package br.com.leitordearquivos.model;

public class Item {

  private int id;
  private int quantidade;
  private double preco;

  public Item( int id, int quantidade, double preco ) {
    this.id = id;
    this.quantidade = quantidade;
    this.preco = preco;
  }
}
