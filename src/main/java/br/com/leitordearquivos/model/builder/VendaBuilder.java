package br.com.leitordearquivos.model.builder;

import br.com.leitordearquivos.model.Item;
import br.com.leitordearquivos.model.Venda;

import java.util.ArrayList;
import java.util.List;

public class VendaBuilder {

  private int idVenda;
  private List<Item> itensVenda = new ArrayList<>();
  private double totalVenda;
  private String nomeVendedor;

  public VendaBuilder() {
  }

  public VendaBuilder comId( int idVenda ) {
    this.idVenda = idVenda;
    return this;
  }

  public VendaBuilder com( String itens ) {
    String[] subFilds = itens.replace("[", "")
        .replace("]", "").split(",");
    for ( String string: subFilds ) {
      subFilds = string.split( "-" );
      this.itensVenda.add( new Item( Integer.parseInt( subFilds[0] )
          , Integer.parseInt( subFilds[1] ), Double.parseDouble( subFilds[2] ) ) );
      this.totalVenda += Double.parseDouble( subFilds[2]  ) * Integer.parseInt( subFilds[1] );
    }
    return this;
  }

  public VendaBuilder vendedor( String nomeVendedor ) {
    this.nomeVendedor = nomeVendedor;

    return this;
  }

  public Venda constroi() {
    Venda venda = new Venda( idVenda, itensVenda, nomeVendedor, totalVenda );

    return venda;
  }


}
