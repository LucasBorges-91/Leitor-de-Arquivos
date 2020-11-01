package br.com.leitordearquivos.service;

import java.util.List;

public interface ProcessadorDeArquivos {

  void processaLinhas( List<String> linhas );
  String resposta();
}
