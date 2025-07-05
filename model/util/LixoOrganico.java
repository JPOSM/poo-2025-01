package model.util;

public class LixoOrganico {
	public double toneladas_lixo;
	public double metano_produzido;
	public double energia_lixo;
	public double vazao_metano, vazao_energica;
	public double tempo_aquecimento, tempo_vaporizacao;
	public double energia_perdida, energia_usavel, energia_produzida_gerador;
	
    public void Processo(String local, double lixo_gerado, double area_de_vazao, double volume_de_agua)
    {
        toneladas_lixo = lixo_gerado; //1
        //toneladas de lixo produzido
        
        metano_produzido = Conversoes.massa_met(toneladas_lixo);
        //metano produzido nesse lixo
        
        energia_lixo = Conversoes.energia_met(toneladas_lixo);
        //energia do metano desse lixo
        
        double area_vazao = area_de_vazao; //0.6; //cm^2
        //area de vazao da queima. Onde o calor sai (como se fosse a boca de um fogao).
        
        vazao_metano = Turbina.vazao_massica(EficienciaBiometano.velocidade_queima(), area_vazao, EficienciaBiometano.MET_DENSIDADE / 1000000); //cm/s, cm^2, kg/cm^3 * 10^-6
    
        vazao_energica = Turbina.vazao_energica(EficienciaBiometano.MET_MASS_MOLAR,vazao_metano, EficienciaBiometano.MET_ENTALPIA); //kg, //kg/s, kj
        
        double volume_agua = volume_de_agua;//0.001;
        //Quanto de agua tem na caldeira. Essa agua vai ser aquecida e virar vapor, e nesse processo, a energia total do sistema vai ser perdida
        
        Turbina.volume = volume_agua;
        
        double variacao_temperatura = Turbina.variacao_temperatura(vazao_energica);
        
        tempo_aquecimento = Turbina.tempo_aquecimento(variacao_temperatura);
        
        tempo_vaporizacao = Turbina.tempo_vaporizacao(vazao_energica);
        
        energia_perdida = Turbina.energia_perda(vazao_energica, tempo_aquecimento + tempo_vaporizacao);
        
        energia_usavel = Math.abs(energia_lixo) - Math.abs(energia_perdida);
        
        energia_produzida_gerador = Turbina.potencia_turbina(energia_usavel);
        
        System.out.println("Toneladas de lixo "+ toneladas_lixo);
        System.out.println("Metano produzido "+ metano_produzido);
        System.out.println("Energia total do lixo " +energia_lixo);
        System.out.println("Area vazao met "+ area_vazao);
        System.out.println("Vazao met "+ vazao_metano);
        System.out.println("Vazao_energica " + vazao_energica);
        System.out.println("Variacao temperatura " + variacao_temperatura);
        System.out.println("Tempo aquecimento " + tempo_aquecimento);
        System.out.println("Tempo vaporizacao " + tempo_vaporizacao);
        System.out.println("Energia perdida " + energia_perdida);
        System.out.println("Volume de agua " + Turbina.volume);
        System.out.println("Massa de agua " + Turbina.massa_agua);
        //System.out.println("potencia " + Caldeira.potencia_turbina(1, velocidade_vapor));
        System.out.println("Energia Gerada pela terméletrica: "+ energia_produzida_gerador);
        if (energia_produzida_gerador >= Math.abs(energia_lixo))
        {
            System.out.println("ERRO! A energia produzida na turbina é maior do que a energia do metano.");
        }
    }
}
