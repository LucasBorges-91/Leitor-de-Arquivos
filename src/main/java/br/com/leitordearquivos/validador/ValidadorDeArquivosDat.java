package br.com.leitordearquivos.validador;

import br.com.leitordearquivos.validador.Validador;

public class ValidadorDeArquivosDat implements Validador {

  @Override
  public boolean validaFormatoArquivo( String nomeArquivo ) {
    if ( nomeArquivo.endsWith( ".dat" ) ) {
      return true;
    }
    return false;
  }

  @Override
  public boolean validaConteudoArquivo( String linha ) {
    String[] linhaSplit = linha .split( "ç" );
    if ( linha.matches( "^00([123]+)ç.*ç\\[([^abc]+)\\]ç.*|^00([123]+)ç.*ç([a-z\\sA-Z]+)ç.*" )
        && ( linhaSplit.length == 4 ) ) {
      return true;
    }
    return false;
  }
}
