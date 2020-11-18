
package Presidentes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Grupo A
 */
public class conn {   
         
        //Criação do objecto
        potus jf = new potus();    
    
        //URL da base de dados
        String url = "jdbc:mysql://localhost:3306/president?zeroDateTimeBehavior=CONVERT_TO_NULL";
        
        //utilizador e password
        String username = "root";
        String password = "123456";
                
        //Atributos
        String nome;
        String partido;
        String iMandato;
        String fMandato;
        String bio;
        String numero;
        String votos;
        String imagem;
        String search;
        
    //Método Construtor
    public String conn (){        
   
        if (search.isEmpty()){
            return ("Sem dados");
        }
        
        else{
           
        try{       
            
             // criação do objecto para a ligaçaõ a base de dados
            Connection connection = DriverManager.getConnection(url,username,password);
            
            System.out.println("Ligado a Base de Dados");           
           
            //Criação de objecto com base na biblioteca java.sql.Statement
            Statement stmt = connection.createStatement();  
                        
            //Criação de objecto com base na biblioteca java.sql.ResultSet
            ResultSet rs = stmt.executeQuery("SELECT * from president.presidents INNER JOIN votes ON ( presidents.presidentID = votes.presidentID )INNER JOIN image ON ( presidents.presidentID = image.presidentID )INNER JOIN bio ON ( presidents.presidentID = bio.presidentID ) where presidentName LIKE '%"+search+"%' OR politicalParty LIKE '%"+search+"%' OR startDate LIKE '%"+search+"%' OR endDate LIKE '%"+search+"%' ;");
                        
            //Percorre a base de dados a procura da informação e retorna a mesma
            while (rs.next()) {

                nome = rs.getString("presidentName");
                partido = rs.getString("politicalParty");
                iMandato = rs.getString("startDate");
                fMandato = rs.getString("endDate");
                bio = rs.getString("sNarrative");
                numero = rs.getString("presidentID");
                votos = rs.getString("lectoralCollegeVotes");
                imagem = rs.getString("url");

            }            
        }
        
        //Caso exista algum erro, e lançada uma mensagem de excepção
        catch (SQLException e){
            
            System.out.println("Não foi possível ligar a Base de Dados");
            
            e.printStackTrace();
            
        }
        
        }

        if (nome == null){
            return ("Sem dados disponiveis");
        }
        else{
        return ("Nome: " + nome + "\nNúmero: " + numero + "\nPartido: " + partido + "\nInicio de Mandato: " + iMandato + "\nFim de Mandato: " + fMandato +"\nVotos: " + votos + "\nBiografia: " + bio);
        }
    }
}


    

