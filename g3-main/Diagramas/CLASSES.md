``````mermaid

classDiagram
    class LixoOrganico {
        +double toneladas_lixo
        +double metano_produzido
        +double energia_lixo
        +void Processo(String local, double lixo_gerado, double area, double volume)
    }

    class LixoSeco {
        -double mMadeira
        -double mTecidos
        -double mPapeis
        +void Processo(String local, double area, double volume)
        +void ProcessoMadeira(double area)
        +void ProcessoTecidos(double area)
        +void ProcessoPapel(double area)
    }

    class Turbina {
        +static double vazao_massica(double, double, double)
        +static double vazao_energica(double, double, double)
        +static double tempo_aquecimento(double)
        +static double tempo_vaporizacao(double)
        +static double energia_perda(double, double)
        +static double potencia_turbina(double)
    }

    class Conversoes {
        +static double kg_para_ton(double)
        +static double massa_met(double)
        +static double mol_met(double)
        +static double energia_met(double)
    }

    class EficienciaBiometano {
        <<static>>
        +double EFICIENCIA
        +double MET_ENTALPIA
        +double MET_DENSIDADE
    }

    class CriadorHTML {
        +static void CriarHTML(Dados)
    }

    class Dados {
        +get_tipo()
        +get_local()
        +get_energia_lixo()
        +get_energia_gerada()
        +get_volume()
        +get_area()
        +get_comentario()
    }

    LixoOrganico --> Turbina
    LixoOrganico --> Conversoes
    LixoOrganico --> EficienciaBiometano
    LixoSeco --> Turbina
    LixoSeco --> Conversoes
    LixoSeco --> EficienciaBiometano
    CriadorHTML --> Dados

``````
