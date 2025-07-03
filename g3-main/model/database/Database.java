package model.database;

import java.sql.*;
import com.j256.ormlite.jdbc.JdbcConnectionSource;

public class Database {
	private String databaseName = null;
	private JdbcConnectionSource connection = null;
	
	public Database(String databaseName) 
	{
		this.databaseName = databaseName;
	}
	
	public JdbcConnectionSource getConnection() throws SQLException
	{
		if (databaseName == null)
		{
			throw new SQLException("databaseName é nulo. Coloque um nome para databaseName");
		}
		
		if (connection == null)
		{
			try 
			{
				connection = new JdbcConnectionSource("jdbc:sqlite:" + databaseName);
				//Tenta criar uma conexão com o databaseName
			}
			
			catch (Exception e)
			{
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);
				//Caso não consiga criar uma conexão, ele mostra uma mensagem de erro e fecha o processo
			}
			
			System.out.println("BANCO DE DADOS CRIADO COM SUCESSO");
		}
		return connection;
	}
	
	
	public void close()
	{
		//Se existir uma conexão...
	    if (connection != null)
	    {
	    	//tenta fechar a conexão, e deixar a variavel connection no database nula.
	        try 
	        {
	            connection.close();
	            this.connection = null;
	        } 
	        
	        //Se nao conseguir, joga um erro
	        catch (java.lang.Exception e)
	        {
	            System.err.println(e);
	        }
	    }
	}
}
