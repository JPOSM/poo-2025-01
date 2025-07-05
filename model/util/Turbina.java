package model.util;

public class Turbina {
	
	//Vazao Massica do combustivel liberada na queima
	public static double vazao_massica(double velocidade_queima, double area, double densidade)
	{
		return velocidade_queima * area * densidade; //m^3 /s
	}
	
	//Vazao energica do combustivel
	public static double vazao_energica(double massa_molar_combustivel, double vazao_massa_combustivel, double entalpia_queima)
	{
		return (vazao_massa_combustivel / massa_molar_combustivel) * entalpia_queima; //kJ/s
	}
	
	
	
	//AQUECIMENTO DA AGUA
	static double CALOR_ESPECIFICO_AGUA = 4.184; // kJ/kg * Kelvin
	
	public static double volume = 2; //m^3
	//0.001 m^3 seria 1 litro de água
	
	
	
	public static double DENSIDADE = 997; //kg/m^3
	
	public static double massa_agua = DENSIDADE * volume; //kg
	//Quanto de agua tem na caldeira.
	
	//Vou fazer com que a massa dependa do volume de agua e da densidade, já que densidade é uma constante
	
	
	
	public static double variacao_temperatura(double vazao_energia)
	{
		massa_agua = DENSIDADE * volume;
		//atualizo a massa de agua se o volume for diferente do valor inicial
		
		return Math.abs(vazao_energia) / (massa_agua * CALOR_ESPECIFICO_AGUA);
	}
	//Variacao de temperatura em Kelvin por segundo
	
	
	
	public static double temperatura_inicial = 20;
	
	public static double tempo_aquecimento(double variacao_temperatura)
	{
		return (373.15 - temperatura_inicial) / (variacao_temperatura);
	}
	//O tempo para a agua chegar ate 100 Celsius, ou 373.15 Kelvin
	
	
	static double COEFICIENTE_LATENCIA_VAPOR_AGUA = 2264.705; //kJ / kg
	
	public static double tempo_vaporizacao(double vazao_energia)
	{
		massa_agua = DENSIDADE * volume;
		//atualizo a massa de agua se o volume for diferente do valor inicial
		
		return (COEFICIENTE_LATENCIA_VAPOR_AGUA * massa_agua) / Math.abs(vazao_energia);
	}
	//O tempo que leva para a agua virar vapor
	
	
	public static double energia_perda(double vazao_energia, double tempo_total)
	{
		return Math.abs(vazao_energia) * tempo_total;
	}
	//Ate a agua virar vapor, foi usado uma vazao energica durante um certo tempo, logo essa energia é perdida no gerador.
	
	
	
	static double PRESSAO_AGUA = 101.33 * 1000d; //N/m^2
	
	public static double velocidade_vapor()
	{
		return Math.sqrt(2 * PRESSAO_AGUA / (volume * massa_agua));
	}
	//retorna a velocidade do vapor da água dentro da caldeira.
	
	
	
	static double DENSIDADE_VAPOR = 958.39; //kg/m^3
	//Densidade do vapor em 100 graus
	
	
	public static double potencia_turbina(double energia_vapor)//double area_turbina, double velocidade_vapor)
	{
		//return (16d/54d) * DENSIDADE_VAPOR * area_turbina * Math.pow(velocidade_vapor, 3);
		return (16d/27d) * energia_vapor;
	}
}
