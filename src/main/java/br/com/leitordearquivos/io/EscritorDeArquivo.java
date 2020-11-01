package br.com.leitordearquivos.io;



import br.com.leitordearquivos.service.ProcessadorDeArquivos;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EscritorDeArquivo {

  static final Logger logger = LogManager.getLogger( EscritorDeArquivo.class.getName() );

  public void escreve( File file, ProcessadorDeArquivos processador, String extensao ) {

    logger.info( "Escrevendo o arquivo" );

    String nomeArquivo = file.getName().replaceFirst( "[.][^.]+$", "" )
        .toLowerCase();

   String path = file.getParent().replace( "in", "out/" )
       + nomeArquivo + ".done" + extensao;

    try ( BufferedWriter bw = new BufferedWriter(
        new FileWriter( path ) ) ) {
      bw.write( processador.resposta() );
    } catch ( IOException e ) {
      e.printStackTrace();
      logger.error( "Erro ao escrever o arquivo" );
    }
  }
}
