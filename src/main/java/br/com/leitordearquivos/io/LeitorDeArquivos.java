package br.com.leitordearquivos.io;

import br.com.leitordearquivos.validador.Validador;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LeitorDeArquivos {

  static final Logger logger = LogManager.getLogger( LeitorDeArquivos.class.getName() );

  private List<String> linhas;

  public LeitorDeArquivos() {
    this.linhas = new ArrayList<>();
  }

  public List<String> ler( File file, Validador validador, Diretorio diretorio ) {

    logger.info( "Lendo o arquivo" );

    if ( validador.validaFormatoArquivo( file.getName() ) ) {
      try ( BufferedReader br = new BufferedReader( new FileReader( file.getPath() ) ) ) {
        String linha = br.readLine();
        while ( linha != null ) {
          if ( validador.validaConteudoArquivo( linha ) ) {
            linhas.add( linha );
            linha = br.readLine();
          } else {
            diretorio.move( file, "arquivos-corrompidos" );
            logger.warn( "arquivo movido para a pasta arquivos-corrompidos" );
            linhas.clear();
            break;
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
        logger.error( "Erro ao escrever o arquivo" );
      }
    } else {
      diretorio.move( file, "arquivos-outros-formatos" );
      logger.warn( "arquivo movido para a pasta arquivos-outros-formatos" );
    }
    return linhas;
  }
}
