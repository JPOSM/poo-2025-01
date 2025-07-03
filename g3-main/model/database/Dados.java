package model.database;

import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.DataType;

import java.util.Date;
import java.text.SimpleDateFormat;



import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.database.Dados.TipoLixo;

@DatabaseTable(tableName = "Dados")
public class Dados {
	
	public Dados() {};
	
	
	/*
	 * CATEGORIZAR O TIPO DE LIXO
	 */
	public enum TipoLixo {
		SECO,
		ORGANICO
	}
	
	
	@DatabaseField(generatedId = true)
	 public int id;
	//id para identificar o objeto no banco de dados (DB)
	
	@DatabaseField
	 String local;
	//Qual o estado/município em que estamos fazendo a analise
	
	@DatabaseField
	TipoLixo tipo;
	//O tipo de lixo que estamos fazendo a analise
	
	@DatabaseField
	private double toneladas_lixo_organico;
	//Toneladas de lixo indo para o aterro/Lixao
	
	@DatabaseField
	private double toneladas_lixo_madeira;
	//Toneladas de madeira indo para o aterro/Lixao
	
	@DatabaseField
	private double toneladas_lixo_papel;
	//Toneladas de papel indo para o aterro/Lixao
	
	@DatabaseField
	private double toneladas_lixo_tecido;
	//Toneladas de tecidos indo para o aterro/Lixao
	
	//private double metano_produzido;
	//Toneladas de lixo indo para o aterro/Lixao
	
	@DatabaseField
	private double energia_gerador;
	//Energia gerada pelo gerador
	
	@DatabaseField
	private double energia_lixo;
	//Energia gerada pelo lixo
	
	@DatabaseField
	private double energia_perdida;
	//Energia perdida da energia total do lixo com a energia gerada 
	
	@DatabaseField
	private double area_vazao;
	//Area de vazao de queima. Como se fosse a boca de um fogão onde sai a chama
	
	@DatabaseField
	private double volume_agua;
	//Volume da água dentro da caldeira, em m^3
	
	@DatabaseField
	private String comentario;
	//Comentario do engenheiro sobre o processo de geração de energia
	
	@DatabaseField(dataType = DataType.DATE)
	Date dia_relatorio;
	//Dia em que o relatorio foi feito
	
	
	//METODO CONSTRUTOR
	public Dados(String local, TipoLixo tipo_lixo, double toneladas_organico, double toneladas_madeira, double toneladas_tecido, double toneladas_papel, double energia_gerador, double energia_lixo, double energia_perdida, double area_vazao, double volume_agua, String comentario, Date dia)
	{
		this.local = local;
		this.tipo = tipo_lixo;
		this.toneladas_lixo_organico = toneladas_organico;
		this.toneladas_lixo_madeira = toneladas_madeira;
		this.toneladas_lixo_tecido = toneladas_tecido;
		this.toneladas_lixo_papel = toneladas_papel;
		this.energia_gerador = energia_gerador;
		this.energia_lixo = energia_lixo;
		this.energia_perdida = energia_perdida;
		this.area_vazao = area_vazao;
		this.volume_agua = volume_agua;
		this.comentario = comentario;
		this.dia_relatorio = dia;
	}
	
	//METODOS GETS
	public int getId()
	{
		return this.id;
	}
	
	public String getLote()
	{
		return this.local;
	}
	
	public TipoLixo getTipo()
	{
		return this.tipo;
	}
	
	public String getTipoLixo()
	{
		return this.tipo.name();
	}
	
	public double getToneladas()
	{
		return this.toneladas_lixo_organico;
	}
	
	public double getToneladasTecido()
	{
		return this.toneladas_lixo_tecido;
	}
	
	public double getToneladasMadeira()
	{
		return this.toneladas_lixo_madeira;
	}
	
	public double getToneladasPapel()
	{
		return this.toneladas_lixo_papel;
	}
	
	/*
	public double get_metano()
	{
		return this.metano_produzido;
	}
	*/
	
	public double getEnergiaGerada()
	{
		return this.energia_gerador;
	}
	
	public double getEnergiaLixo()
	{
		return this.energia_lixo;
	}
	
	public double getEnergiaPerdida()
	{
		return this.energia_perdida;
	}
	
	public double getArea()
	{
		return this.area_vazao;
	}
	
	public double getVolume()
	{
		return this.volume_agua;
	}
	
	public String getComentario()
	{
		return this.comentario;
	}
	
	public Date getData()
	{
		return this.dia_relatorio;
	}
	
	
	
	//METODOS SET
	public void setId(int id)
	{
		this.id = id;
	}
	
	public void setLocal(String local)
	{
		this.local = local;
	}
	
	public void setTipo(TipoLixo tipo)
	{
		this.tipo = tipo;
	}
	
	public void setToneladas(double lixo)
	{
		this.toneladas_lixo_organico = lixo;
	}
	
	public void setToneladasMadeira(double lixo)
	{
		this.toneladas_lixo_madeira = lixo;
	}
	
	public void setToneladasTecido(double lixo)
	{
		this.toneladas_lixo_tecido = lixo;
	}
	
	public void setToneladasPapel(double lixo)
	{
		this.toneladas_lixo_papel = lixo;
	}
	
	/*
	public void set_metano(double metano)
	{
		this.metano_produzido = metano;
	}
	*/
	
	public void setEnergiaGerada(double energia)
	{
		this.energia_gerador = energia;
	}
	
	public void setEnergiaLixo(double energia)
	{
		this.energia_lixo = energia;
	}
	
	public void setEnergiaPerdida(double energia)
	{
		this.energia_perdida = energia;
	}
	
	public void setArea(double area)
	{
		this.area_vazao = area;
	}
	
	public void setVolume(double volume)
	{
		this.volume_agua = volume;
	}
	
	public void setComentario(String comentario)
	{
		this.comentario = comentario;
	}
	
	public void setData(Date dia)
	{
		this.dia_relatorio = dia;
	}
}
