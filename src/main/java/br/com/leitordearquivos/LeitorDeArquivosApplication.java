package br.com.leitordearquivos;

import br.com.leitordearquivos.io.ObservaDiretorioDat;
import br.com.leitordearquivos.io.Observador;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class LeitorDeArquivosApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(LeitorDeArquivosApplication.class, args);

		Observador diretorio = new ObservaDiretorioDat();
		diretorio.assisteDiretorio();
	}
}
