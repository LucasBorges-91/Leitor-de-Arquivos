package br.com.leitordearquivos.validador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidadorDeArquivosDatTest {

  private Validador validador;

  @BeforeEach
  public void setup() {
    this.validador = new ValidadorDeArquivosDat();
  }

  @Test
  void validaFormatoArquivoDeveRetornarFalse() {
    String nomeArquivo = "arquivo.txt";

    assertFalse( this.validador.validaFormatoArquivo( nomeArquivo ) );
  }


  @Test
  void validaFormatoArquivoDeveRetornarTrue() {
    String nomeArquivo = "arquivo.dat";

    assertTrue( this.validador.validaFormatoArquivo( nomeArquivo ) );
  }

  @Test
  void validaConteudoArquivoDeveRetornarFalse() {
    String linha1 = "002ç1345675434544345ç";
    String linha2 = "00ç1345675434544345çJose da SilvaçUrbano";
    String linha3 = "003ç[1-10-1.9,2-30-2.50,3-55-3.10]çPedro";
    String linha4 = "004ç1345675434544345çJose da SilvaçUrbano";

    assertFalse( this.validador.validaConteudoArquivo( linha1 ) );
    assertFalse( this.validador.validaConteudoArquivo( linha2 ) );
    assertFalse( this.validador.validaConteudoArquivo( linha3 ) );
    assertFalse( this.validador.validaConteudoArquivo( linha4 ) );
  }

  @Test
  void validaConteudoArquivoDeveRetornarTrue() {
    String linha1 = "002ç1345675434544345çJose da SilvaçUrbano";
    String linha2 = "003ç11ç[1-10-1.9,2-30-2.50,3-55-3.10]çPedro";
    String linha3 = "001ç3245678865401çPauloç400.99";
    String linha4 = "002ç2355675433444345çEduardo PereiraçRural";

    assertTrue( this.validador.validaConteudoArquivo( linha1 ) );
    assertTrue( this.validador.validaConteudoArquivo( linha2 ) );
    assertTrue( this.validador.validaConteudoArquivo( linha3 ) );
    assertTrue( this.validador.validaConteudoArquivo( linha4 ) );
  }
}