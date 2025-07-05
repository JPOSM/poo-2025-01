package model.util;

public class EficienciaBiometano
{
    public static double EFICIENCIA = 0.9d;
    //Quanto de metano tem no biometano
    
    public static double MET_ENTALPIA = -802.27d; //kJ
    //energia liberada na queima de 1 mol de metano
    
    public static double MET_MASS_MOLAR = 0.01604245d; //16.04245; esse valor esta em grama/mol
    //massa de metano em kilograma/mol
    
    public static double MET_DENSIDADE = 0.668d;
    //densidade do metano em kg/m^3 segundo o site https://www.engineeringtoolbox.com/gas-density-d_158.html
    
    public static double VOLUME = 30.1045d;
    //Volume de metano produzido por dia por tonelada
    
    public static double VEL_QUEIMA = 389.763779528; //cm/s
    //velocidade de queima de metano em 25 Celsius, 1 atm, segundo o site: ttps://www.sciencedirect.com/science/article/abs/pii/0010218069900431#:~:text=The%20maximum%20burning%20velocity%20of,06%20per%20cent%20by%20volume
    
    
    
    public static double densidade_bio()
    {
        return MET_DENSIDADE * EFICIENCIA;
    }
    
    public static double massa_met()
    {
        return MET_MASS_MOLAR * EFICIENCIA;
    }
    
    public static double velocidade_queima()
    {
    	return VEL_QUEIMA * EFICIENCIA;
    }
}