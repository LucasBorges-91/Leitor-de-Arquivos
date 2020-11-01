package br.com.leitordearquivos.io;

import br.com.leitordearquivos.service.ProcessaArquivoDat;
import br.com.leitordearquivos.service.ProcessadorDeArquivos;
import br.com.leitordearquivos.validador.ValidadorDeArquivosDat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Optional;

public class ObservaDiretorioDat implements Observador {

  static final Logger logger = LogManager.getLogger( ObservaDiretorioDat.class.getName() );

  public void assisteDiretorio() throws IOException, InterruptedException {

    logger.info( "Criando os diretórios!" );

    Diretorio diretorioDat =  new DiretorioDat();
    diretorioDat.cria( "in" );
    diretorioDat.cria( "out" );
    diretorioDat.cria( "arquivos-corrompidos" );
    diretorioDat.cria( "arquivos-outros-formatos" );
    diretorioDat.cria( "backup" );

    WatchService watchService = FileSystems.getDefault().newWatchService();

    Path diretorio = Paths.get( System.getProperty( "user.home") + "/data/in/" );
    diretorio.register( watchService, StandardWatchEventKinds.ENTRY_CREATE );

    while ( true ) {
      WatchKey key = watchService.take();
      Optional<WatchEvent<?>> watchEvent = key.pollEvents().stream().findFirst();
      if ( watchEvent.isPresent() ) {
        if ( watchEvent.get().kind() == StandardWatchEventKinds.OVERFLOW ) {
          continue;
        }
        Path path = (Path) watchEvent.get().context();

        File dir = new File( diretorio + "/" + path );

        List<String> arquivoDat = new LeitorDeArquivos().ler( dir, new ValidadorDeArquivosDat(), diretorioDat );

        if ( arquivoDat.size() > 0 ) {

          logger.info( "Processando informacoes do arquivo!" );

          ProcessadorDeArquivos processadorDat = new ProcessaArquivoDat();

          processadorDat.processaLinhas( arquivoDat );

          EscritorDeArquivo escritor = new EscritorDeArquivo();

          escritor.escreve( dir, processadorDat, ".dat" );

          diretorioDat.move( dir, "backup" );
        }

      }

      boolean valido = key.reset();
      if ( !valido ) {
        break;
      }
    }
    watchService.close();
  }
}
