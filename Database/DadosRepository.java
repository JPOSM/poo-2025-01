import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import com.j256.ormlite.table.TableUtils;
import java.util.List;
import java.util.ArrayList;

public class DadosRepository
{
    private static Database database;
    private static Dao<Dados, Integer> dao;
    private List<Dados> loadedDados;
    private Dados loadedDado; 
    
    public DadosRepository(Database database) {
        DadosRepository.setDatabase(database);
        loadedDados = new ArrayList<Dados>();
    }
    
    public static void setDatabase(Database database) {
        DadosRepository.database = database;
        try {
            dao = DaoManager.createDao(database.getConnection(), Dados.class);
            TableUtils.createTableIfNotExists(database.getConnection(), Dados.class);
        }
        catch(SQLException e) {
            System.out.println(e);
        }            
    }
    
    public Dados create(Dados dados) {
        int nrows = 0;
        try {
            nrows = dao.create(dados);
            if ( nrows == 0 )
                throw new SQLException("Error: object not saved");
            this.loadedDado = dados;
            loadedDados.add(dados);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return dados;
    }    

    public void update(Dados student) {
      // TODO
    }

    public void delete(Dados student) {
      // TODO
    }
    
    public Dados loadFromId(int id) {
        try {
            this.loadedDado = dao.queryForId(id);
            if (this.loadedDado != null)
                this.loadedDados.add(this.loadedDado);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return this.loadedDado;
    }    
    
    public List<Dados> loadAll() {
        try {
            this.loadedDados =  dao.queryForAll();
            if (this.loadedDados.size() != 0)
                this.loadedDado = this.loadedDados.get(0);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return this.loadedDados;
    }

    // getters and setters ommited...        
}