
``````mermaid
sequenceDiagram
    participant Usuário
    participant LixoSeco
    participant Turbina
    participant Conversoes
    participant HTML as CriadorHTML

    Usuário->>LixoSeco: Processo(local, área, volume)
    LixoSeco->>Turbina: vazao_massica(...)
    LixoSeco->>Turbina: vazao_energica(...)
    LixoSeco->>Turbina: tempo_aquecimento(...)
    LixoSeco->>Turbina: tempo_vaporizacao(...)
    LixoSeco->>Turbina: energia_perda(...)
    LixoSeco->>Turbina: potencia_turbina(...)
    LixoSeco->>Conversoes: kg_para_ton(...)
    LixoSeco->>HTML: CriarHTML(Dados)

``````
