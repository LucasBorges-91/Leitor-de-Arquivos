package br.com.leitordearquivos.service;

import br.com.leitordearquivos.model.Cliente;
import br.com.leitordearquivos.model.Venda;
import br.com.leitordearquivos.model.Vendedor;
import br.com.leitordearquivos.model.builder.VendaBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProcessaArquivoDatTest {

  private List<String> arquivo = new ArrayList<>();
  private Vendedor vendedor;
  private Cliente cliente;
  private Cliente outroCliente;
  private Venda venda;

  @BeforeEach
  public void setup() {
    this.arquivo.add( "002ç1345675434544345çJose da SilvaçUrbano" );
    this.arquivo.add( "003ç11ç[1-10-1.9,2-30-2.50,3-55-3.10]çPedro" );
    this.arquivo.add( "001ç3245678865401çPauloç400.99" );
    this.arquivo.add( "002ç2355675433444345çEduardo PereiraçRural");

    this.vendedor = new Vendedor( "3245678865401", "Paulo", 400.99 );
    this.cliente = new Cliente( "1345675434544345", "Jose da Silva", "Urbano" );
    this.outroCliente = new Cliente( "2355675433444345", "Eduardo Pereira", "Rural" );
    this.venda = new VendaBuilder().comId( 11 ).com( "[1-10-1.9,2-30-2.50,3-55-3.10]" )
        .vendedor( "Pedro" ).constroi();
    System.out.println( "iniciando testes" );
  }


  @Test
  void processaLinhas() {

    ProcessaArquivoDat processadorDat = new ProcessaArquivoDat();
    processadorDat.processaLinhas(arquivo);

    assertEquals( 1, processadorDat.totalVendedores() );
    assertEquals( 2, processadorDat.totalClientes() );
    assertEquals( 1, processadorDat.getVendas().size() );

    assertEquals( this.vendedor, processadorDat.getVendedores().get(0) );
    assertEquals( this.cliente, processadorDat.getClientes().get(0) );
    assertEquals( this.outroCliente, processadorDat.getClientes().get(1) );
    assertEquals( this.venda, processadorDat.getVendas().get(0) );
  }

  @Test
  void idVendaMaisCara() {
    this.arquivo.add( "003ç12ç[1-34-10,2-33-2.50,5-30-9.5]çPaulo" );
    this.arquivo.add( "003ç13ç[1-10-8,2-30-2.50,3-55-3.10]çJoao" );
    this.arquivo.add( "003ç14ç[1-34-10,2-33-2.50]çAlexandre" );

    ProcessaArquivoDat processadorDat = new ProcessaArquivoDat();
    processadorDat.processaLinhas(arquivo);

    assertEquals( 12, processadorDat.idVendaMaisCara() );
  }

  @Test
  void totalVendaVendores() {

    ProcessaArquivoDat processadorDat = new ProcessaArquivoDat();
    processadorDat.processaLinhas(arquivo);

    assertEquals( 1, processadorDat.totalVendaVendores().size() );
    assertEquals( 264.5, processadorDat.totalVendaVendores().get( "Pedro" ) );
  }

  @Test
  void piorVendedor() {
    ProcessaArquivoDat processadorDat = new ProcessaArquivoDat();
    processadorDat.processaLinhas(arquivo);

    assertEquals( "Pedro", processadorDat.piorVendedor() );
  }
}