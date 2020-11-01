package br.com.leitordearquivos.io;

import br.com.leitordearquivos.io.Diretorio;

import java.io.File;

public class DiretorioDat implements Diretorio {

  @Override
  public void cria( String diretorio ) {
    File file = new File( System.getProperty( "user.home" ) + "/data/" + diretorio );
    if ( !file.exists() ) {
      file.mkdirs();
    }
  }

  @Override
  public void move( File file, String path ) {
    File destino = new File( System.getProperty( "user.home" ) + "/data/"
        + path + "/" + file.getName() );
    file.renameTo( destino );
  }

}
