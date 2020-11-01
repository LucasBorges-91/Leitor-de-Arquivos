package br.com.leitordearquivos.io;

import java.io.IOException;

public interface Observador {

  void assisteDiretorio() throws IOException, InterruptedException;
}
