package model.util;

public class LixoSeco {
    private double mMadeira;
    private double mTecidos;
    private double mPapeis;
    //private double eficiencia;
    private double jAparente;
    private double jUtil;
    private double jPerda;
    
    private double jMadeira = 16_000_000;
    private double jTecidos = 15_000_000;
    private double jPapeis = 13_500_000;

    
    
    public double energia_total = 0, energia_produzida_gerador = 0; //kJ
    public double vazao_energica_total = 0; //kJ/s
    
    public LixoSeco(double mMadeira, double mTecidos, double mPapeis) {
        this.mMadeira = mMadeira;
        this.mTecidos = mTecidos;
        this.mPapeis = mPapeis;
        
        //this.jMadeira = jMadeira;
        //this.jTecidos = jTecidos;
        //this.jPapeis = jPapeis;
        
    }
    
    public void ProcessoMadeira(double area_de_vazao)
    {
    	//double toneladas_madeira = Conversoes.kg_para_ton(mMadeira);
    	//Toneladas de lixo classificado como madeira que vai ser queimado
    	
    	double energia_madeira = mMadeira * jMadeira / 1000d;
    	//kJ da energia total da madeira que vai ser queimada
    	
    	double area_vazao = area_de_vazao; //cm^2
    	//Area de vazao da queima, onde o calor sai. como se fosse a boca de um fogao.
    	
    	double velocidade_queima = (EficienciaBiometano.VEL_QUEIMA * jMadeira) / EficienciaBiometano.MET_ENTALPIA;
    	//Velocidade da queima da madeira. Como eu nao tenho um dado especifico, eu fiz uma relacao com as informacoes de metano.
    	
    	double media_densidade = 664.95412844;
    	//Como existem diferentes tipos de madeira, eu calculei a media da densidade delas
    	
    	double vazao_madeira = Turbina.vazao_massica(velocidade_queima, area_vazao, media_densidade / 1000000);
    	//cm/s, cm^2, kg/cm^3 * 10^-6
    	//Retorna a vazao massica da queima da madeira em kg/m^3
    	
    	double vazao_energica = Turbina.vazao_energica(1, vazao_madeira, jMadeira / 1000);
    	//kg, kg/s, kJ
    	//Retorna a vazao da energia da queima da madeira em kJ/s
    	
    	
    	energia_total += energia_madeira;
    	vazao_energica_total += vazao_energica;
    }
    
    public void ProcessoPapel(double area_de_vazao)
    {
    	//double toneladas_papel = Conversoes.kg_para_ton(mPapeis);
    	//Toneladas de lixo classificado como madeira que vai ser queimado
    	
    	double energia_papel = mPapeis * jPapeis / 1000d;
    	//kJ da energia total da madeira que vai ser queimada
    	
    	double area_vazao = area_de_vazao; //cm^2
    	//Area de vazao da queima, onde o calor sai. como se fosse a boca de um fogao.
    	
    	double velocidade_queima = (EficienciaBiometano.VEL_QUEIMA * jPapeis) / EficienciaBiometano.MET_ENTALPIA;
    	//Velocidade da queima dos papeis. Como eu nao tenho um dado especifico, eu fiz uma relacao com as informacoes de metano.
    	
    	double densidade = 1200; //kg/m^3
    	//densidade do papel segundo o site: https://www.aqua-calc.com/page/density-table/substance/paper-coma-and-blank-standard
    	
    	double vazao_papel = Turbina.vazao_massica(velocidade_queima, area_vazao, densidade / 1000000);
    	//cm/s, cm^2, kg/cm^3 * 10^-6
    	//Retorna a vazao massica da queima dos papeis em kg/m^3
    	
    	double vazao_energica = Turbina.vazao_energica(1, vazao_papel, jPapeis / 1000);
    	//kg, kg/s, kJ
    	//Retorna a vazao da energia da queima dos papeis em kJ/s
    	
    	
    	
    	energia_total += energia_papel;
    	vazao_energica_total += vazao_energica;
    }
    
    public void ProcessoTecidos(double area_de_vazao)
    {
    	//double toneladas_tecidos = Conversoes.kg_para_ton(mTecidos);
    	//Toneladas de lixo classificado como madeira que vai ser queimado
    	
    	double energia_tecidos = mTecidos * jTecidos / 1000d;
    	//kJ da energia total da madeira que vai ser queimada
    	
    	double area_vazao = area_de_vazao; //cm^2
    	//Area de vazao da queima, onde o calor sai. como se fosse a boca de um fogao.
    	
    	double velocidade_queima = (EficienciaBiometano.VEL_QUEIMA * jTecidos) / EficienciaBiometano.MET_ENTALPIA;
    	//Velocidade da queima dos tecidos. Como eu nao tenho um dado especifico, eu fiz uma relacao com as informacoes de metano.
    	
    	double media_densidade = 885; //kg/m^3
    	//Como existem diferentes tipos dos tecidos, eu calculei a media da densidade delas
    	
    	double vazao_tecidos = Turbina.vazao_massica(velocidade_queima, area_vazao, media_densidade / 1000000);
    	//cm/s, cm^2, kg/cm^3 * 10^-6
    	//Retorna a vazao massica da queima dos tecidos em kg/m^3
    	
    	double vazao_energica = Turbina.vazao_energica(1, vazao_tecidos, jTecidos / 1000);
    	//kg, kg/s, kJ
    	//Retorna a vazao da energia da queima dos tecidos em kJ/s
    	
    	
    	energia_total += energia_tecidos;
    	vazao_energica_total += vazao_energica;
    }
    
    
    public void Processo(String local, double area_de_vazao, double volume_de_agua)
    {
    	ProcessoMadeira(area_de_vazao);
    	ProcessoTecidos(area_de_vazao);
    	ProcessoPapel(area_de_vazao);
    	
    	//Faço os 3 processos para cada tipo de lixo seco que pode ser queimado
    	
    	Turbina.volume = volume_de_agua;
    	//A quantidade de agua que vai ser evaporada
    	
    	double variacao_temperatura = Turbina.variacao_temperatura(vazao_energica_total);
    	//A quantidade de temperatura que preciso mudar para que a agua esteja a 100 graus celsius.
        
        double tempo_aquecimento = Turbina.tempo_aquecimento(variacao_temperatura);
        //O tempo de aquecimento ate a agua atingir 100 graus celsius
        
        double tempo_vaporizacao = Turbina.tempo_vaporizacao(vazao_energica_total);
        //O tempo de evaporacao da agua
        
        double energia_perdida = Turbina.energia_perda(vazao_energica_total, tempo_aquecimento + tempo_vaporizacao);
        //A energia perdida ao aquecer a agua
        
        double energia_usavel = Math.abs(energia_total) - Math.abs(energia_perdida);
        //A energia que pode ser usada no gerador
        
        energia_produzida_gerador = Turbina.potencia_turbina(energia_usavel);
        //A energia produzida pelo gerador
    	
    	
    	
    	
    	System.out.println("Toneladas de Madeira: " + Conversoes.kg_para_ton(mMadeira));
    	System.out.println("Toneladas de Tecidos: " + Conversoes.kg_para_ton(mTecidos));
    	System.out.println("Toneladas de Papeis: " + Conversoes.kg_para_ton(mPapeis));
    	System.out.println("Volume de Água: " + volume_de_agua);
    	System.out.println("Area de Vazão da Queima: " + area_de_vazao);
    	System.out.println("Energia Total do Lixo Seco: " + energia_total);
    	System.out.println("Energia Perdida: " + energia_perdida);
    	System.out.println("Energia Gerada pela Termelétrica: " + energia_produzida_gerador);
    	
    }
}