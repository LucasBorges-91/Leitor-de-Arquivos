package br.com.leitordearquivos.service;

import br.com.leitordearquivos.io.ObservaDiretorioDat;
import br.com.leitordearquivos.model.*;
import br.com.leitordearquivos.model.builder.VendaBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class ProcessaArquivoDat implements ProcessadorDeArquivos {

  static final Logger logger = LogManager.getLogger( ObservaDiretorioDat.class.getName() );

  private List<Vendedor> vendedores = new ArrayList<>();
  private List<Cliente> clientes = new ArrayList<>();
  private List<Venda> vendas = new ArrayList<>();

  public List<Vendedor> getVendedores() {
    return vendedores;
  }

  public List<Cliente> getClientes() {
    return clientes;
  }

  public List<Venda> getVendas() {
    return vendas;
  }

  @Override
  public void processaLinhas( List<String> linhas ) {

    logger.info( "processando as linhas do arquivo" );

    for (String linha:
         linhas) {
      String[] field = linha.split( "ç" );
      switch ( field[0] ) {
        case "001":
          this.vendedores.add( new Vendedor( field[1], field[2], Double.parseDouble( field[3] ) ) );
          break;
        case "002":
          this.clientes.add( new Cliente( field[1], field[2], field[3] ) );
          break;
        case "003":
          this.vendas.add( new VendaBuilder().comId( Integer.parseInt( field[1] ) )
              .com( field[2] ).vendedor( field[3] ).constroi() );
          break;
      }
    }
  }

  public int totalVendedores() {
    return this.vendedores.size();
  }

  public int totalClientes() {
    return this.clientes.size();
  }

  public List<Venda> ordenaVendas() {
    List<Venda> listaVendas = this.vendas.stream()
        .sorted( Comparator.comparing( venda -> venda.getTotalVenda() )  ).collect(Collectors.toList());

    return listaVendas;
  }

  public int idVendaMaisCara() {
    return ordenaVendas().get( this.vendas.size() - 1 ).getVendaId();
  }

  public Map<String, Double> totalVendaVendores() {
    Map<String, Double> vendedorETotal = new HashMap<>();
    double total = 0.0;

    for (Venda venda:
         this.vendas) {
      if ( vendedorETotal.containsKey( venda.getNomeVendedor() ) ) {
        total = venda.getTotalVenda() + vendedorETotal.get( venda.getNomeVendedor() );
        vendedorETotal.put( venda.getNomeVendedor(), total );
      } else {
        vendedorETotal.put( venda.getNomeVendedor(), venda.getTotalVenda() );
      }
    }
    return vendedorETotal;
  }

  public String piorVendedor() {
    double total = Double.POSITIVE_INFINITY;
    String vendedor = "";

    for (String nome:
        totalVendaVendores().keySet()) {
      if ( totalVendaVendores().get( nome ) < total ) {
        total = totalVendaVendores().get( nome );
        vendedor = nome;
      }
    }
    return vendedor;
  }

  @Override
  public String resposta() {
    return "Quantidade de clientes: " + totalClientes()
        + "\n"
        + "Quantidade de vendedores: " + totalVendedores()
        + "\n"
        + "ID venda mais cara: " + idVendaMaisCara()
        + "\n"
        + "Pior vendedor: " + piorVendedor();
  }

}
