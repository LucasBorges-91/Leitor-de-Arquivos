package br.com.leitordearquivos.validador;

public interface Validador {

  boolean validaFormatoArquivo( String nomeArquivo );

  boolean validaConteudoArquivo( String linha );
}
