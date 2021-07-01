package br.com.southsystem.cooperativismo.exception;

import lombok.Getter;

@Getter
public enum ProblemType {

    ENTIDADE_NAO_ENONTRADA( "Entidade não encontrada"),
    ENTIDADE_EM_USO( "Entidade em uso"),
    BUSINESS_EXCEPTION( "Violação das regras de negócio"),
    MENSAGEM_INCOMPREENSIVEL( "Mensagem incompreensível"),
    RECURSO_NAO_ENCONTRADO( "Recurso não encontrado"),
    PARAMETRO_INVALIDO( "Parâmetro inválido"),
    ERRO_DE_SISTEMA( "Erro de sistema"),
    DADOS_INVALIDOS( "Dados inválidos");

    private String title;

    ProblemType(String title) {
        this.title = title;
    }
}
