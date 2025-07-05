package model.util;

import java.awt.Desktop;
import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.net.*;

import model.database.Dados;

public class CriadorHTML
{
	public static File html;

	//CriadorHTML.html 
	
    public static void CriarHTML(Dados dado)
    {
    	String HTML = "";
        /*
         * CRIAR DIFERENTES RELATORIOS PARA DIFERENTES TIPOS DE LIXO
         */
        
    	switch(dado.getTipo())
    	{
    	
    	/*
    	 * DO LIXO ORGANICO
    	 */
    	case Dados.TipoLixo.ORGANICO:
   			HTML =
  					"""
   					    <html>
   					        <body>
   					            <h1>
   					                Relatorio de Lixo Orgânico:
   					            </h1>
   					            <p>
   					                O local foi: %s <br>
   					                O Rejeito Orgânico enviado para o local foi: %s toneladas. <br>
   					                <br>
   					                A Energia total do rejeito foi: %s kJ<br>
   					                A Água para ser aquecida dentro da caldeira é: %s m^3 <br>
   					                A Área de vazão do calor da caldeira foi: %s cm^2 <br>
   					                A Energia que pode ser produzida pelo gerador é: %s kJ<br>
   					               	<br>
   					                Relatório gerado no dia: %s
   					                <br>
   					                Comentário: %s
   					            </p>
   					        </body>
   					    </html>
   					""";
   			
   			HTML = String.format(HTML,dado.getLote(), dado.getToneladas(), dado.getEnergiaLixo(), dado.getVolume(), dado.getArea(), dado.getEnergiaGerada(), dado.getData(), dado.getComentario());
        
    		break;
        
    	/*
    	 * DO LIXO SECO	
    	 */
    	case Dados.TipoLixo.SECO:
    		HTML =
				"""
				    <html>
				        <body>
				            <h1>
				                Relatorio de Lixo Seco:
				            </h1>
				            <p>
				                O local foi: %s <br>
				                A quantidade de madeira presente no lixo enviado para o local foi: %s toneladas. <br>
				                A quantidade de tecidos presente no lixo enviado para o local foi: %s toneladas. <br>
				                A quantidade de papeis presente no lixo enviado para o local foi: %s toneladas. <br>
				                <br>
				                A Energia total do lixo foi: %s kJ<br>
				                A Água para ser aquecida dentro da caldeira é: %s m^3 <br>
				                A Área de vazão do calor da caldeira foi: %s cm^2 <br>
				                A Energia que pode ser produzida pelo gerador é: %s kJ<br>
    							<br>
				                Relatório gerado no dia: %s
				                <br>
				                Comentário: %s
				            </p>
				        </body>
				    </html>
				""";
		
		HTML = String.format(HTML,dado.getLote(), dado.getToneladasMadeira(), dado.getToneladasTecido(),dado.getToneladasPapel(), dado.getEnergiaLixo(), dado.getVolume(), dado.getArea(), dado.getEnergiaGerada(), dado.getData(), dado.getComentario());
    		break;
    	}
        //Criaçao de arquivo
        try
        {
            //LocalDateTime data = LocalDateTime.now();
            File arquivo = new File("RELATORIO" + ".html");
            
            if (arquivo.isFile())
            {
            	System.out.println("RELATORIO.hmtl ENCONTRADO, SUBSTITUINDO");
            	
            	if(arquivo.delete())
            	{
            		System.out.println("deletado");
            	}
            }
            
            if(arquivo.createNewFile())
            {
                System.out.println("Arquivo Criado: " + arquivo.getName());
            }
            
            FileWriter fwriter = new FileWriter(arquivo);
            
            fwriter.write(HTML);
            
            html = arquivo;
            
            fwriter.close();
        }
        catch(Exception e)
        {
        
        }
    }
    
    public static void AbrirHTML()
    {
    	new Thread(() -> {
    	try {
    		Desktop.getDesktop().browse(html.toURI());
    	}
    	catch (IOException e2)
    	{
    		e2.printStackTrace();
    	}
    	}).start();
    }
    
}