package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {
 // ******************* CONECTAR COM O BANCO *************************************

    private static Connection conn = null; //objeto do jdbc de conexão com o banco

    public static Connection getConnection(){ // método para conectar com o banco
        if(conn == null){ //pula o processo se já houver a conexão
            try {
                Properties props = loadProperties(); //pega as propriedades de conexão
                String url = props.getProperty("dburl"); // pega a string url do arquivo props
                conn = DriverManager.getConnection(url, props); //conecta com o banco de dados
            }
            catch (SQLException e){
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    //**************************** FECHAR CONEXÃO *******************************

    public static void closeConnection(){
        if(conn != null){ //Testa se a conexao está instanciada
            try{
                conn.close(); //fecha a conexão
            }
            catch (SQLException e){
                throw new DbException(e.getMessage());
            }
        }
    }

    //****************************** CARREGAR PROPRIEDADES ***************************************

    private static Properties loadProperties(){ //carregar o arquivo de propriedades
        try (FileInputStream fs = new FileInputStream("db.properties")){ //cria stream com o arquivo
            Properties props = new Properties(); // instancia um objeto properties
            props.load(fs); //carrega o arquivo dentro do objeto properties
            return props;
        }
        catch (IOException e){
            throw new DbException(e.getMessage());
        }
    }

    public static void closeStatement(Statement st){
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e){
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e){
                throw new DbException(e.getMessage());
            }
        }
    }


}
