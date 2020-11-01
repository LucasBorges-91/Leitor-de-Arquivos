package br.com.leitordearquivos.io;

import java.io.File;

public interface Diretorio {

  void cria( String diretorio );
  void move( File file, String path );
}
