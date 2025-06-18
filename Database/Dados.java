import java.util.Date;
import java.text.SimpleDateFormat;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.DataType;

@DatabaseTable(tableName = "Dados")
public class Dados
{   
    @DatabaseField(generatedId = true)
    private int id;
    
    @DatabaseField
    private String local;
    
    @DatabaseField
    public double toneladas_lixo;
    
    @DatabaseField
    public double metano_produzido;
    
    @DatabaseField
    public double energia_lixo;
    
    @DatabaseField
    public double area_turbina;
    
    @DatabaseField(dataType=DataType.DATE)
    public Date data_relatorio;    
    
    public String printBirthday() {
        SimpleDateFormat dateFor = new SimpleDateFormat("dd/MM/yyyy");
        return dateFor.format(data_relatorio);
    }

//Start GetterSetterExtension Source Code

    /**GET Method Propertie id*/
    public int getId(){
        return this.id;
    }//end method getId

    /**SET Method Propertie id*/
    public void setId(int id){
        this.id = id;
    }//end method setId

    /**GET Method Propertie fullName*/
    public String getLocal(){
        return this.local;
    }//end method getFullName

    /**SET Method Propertie fullName*/
    public void setLocal(String local){
        this.local = local;
    }//end method setFullName

    /**GET Method Propertie registration*/
    public double getLixo(){
        return this.toneladas_lixo;
    }//end method getRegistration

    /**SET Method Propertie registration*/
    public void setLixo(double lixo_gerado){
        this.toneladas_lixo = lixo_gerado;
    }//end method setRegistration
    
    /**GET Method Propertie registration*/
    public double getMetano(){
        return this.metano_produzido;
    }//end method getRegistration

    /**SET Method Propertie registration*/
    public void setMetano(double metano_produzido){
        this.metano_produzido = metano_produzido;
    }//end method setRegistration

    /**GET Method Propertie registration*/
    public double getEnergia(){
        return this.energia_lixo;
    }//end method getRegistration

    /**SET Method Propertie registration*/
    public void setEnergia(double energia){
        this.energia_lixo = energia;
    }//end method setRegistration
    
    public double getAreaTurbina()
    {
        return this.area_turbina;
    }
    
    public void setAreaTurbina(double area)
    {
        this.area_turbina = area;
    }
    
    /**GET Method Propertie birthday*/
    public Date getDataRelatorio(){
        return this.data_relatorio;
    }//end method getBirthday

    /**SET Method Propertie birthday*/
    public void setDataRelatorio(Date data){
        this.data_relatorio = data;
    }//end method setBirthday

//End GetterSetterExtension Source Code


}//End class