```mermaid
%% Diagrama de Casos de Uso
graph TD
    Usuario((Usuário))
    
    subgraph Sistema
        CadastrarLixo["Cadastrar dados do lixo"]
        CalcularEnergia["Calcular energia gerada"]
        GerarRelatorio["Gerar relatório HTML"]
        ProcessarOrganico["Processar lixo orgânico"]
        ProcessarSeco["Processar lixo seco"]
    end

    Usuario --> CadastrarLixo
    Usuario --> CalcularEnergia
    Usuario --> GerarRelatorio
    CalcularEnergia -->|Usa| ProcessarOrganico
    CalcularEnergia -->|Usa| ProcessarSeco
``````
