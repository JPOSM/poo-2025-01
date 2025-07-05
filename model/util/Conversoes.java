package model.util;

public class Conversoes

{
    //EficienciaBiometano ebio = new EficienciaBiometano();
    
	public static double kg_para_ton(double kg)
    {
        return kg * 0.001102311;
        //Retorna o valor em toneladas
    }
    public static double massa_met(double toneladas)
    {
        double massa = kg_para_ton((EficienciaBiometano.VOLUME / EficienciaBiometano.MET_DENSIDADE) * EficienciaBiometano.EFICIENCIA * toneladas);
        return massa;
    }
    
    public static double mol_met(double toneladas)
    {
        double mol = (double) (massa_met(toneladas) / kg_para_ton(EficienciaBiometano.MET_MASS_MOLAR));
        return mol;
    }
    
    public static double energia_met(double toneladas)
    {
        double energia = (double) (mol_met(toneladas) * EficienciaBiometano.MET_ENTALPIA);
        return energia;
    }
}