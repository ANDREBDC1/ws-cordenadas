/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import com.google.gson.Gson;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static jdk.nashorn.internal.objects.NativeError.printStackTrace;
import modelo.Cordenada;

/**
 *
 * @author Andre Rian
 */
public class CordenadaDAO {
    
    public static boolean inserirCordenada(Cordenada cordenada){ 
       String sql = "INSERT INTO [Cordenadas] (Latitude,Longitude,Data,IMEI)  "
               + "VALUES("+cordenada.getLatitude()+ "," +cordenada.getLongitude()+ ",'" +FormataData.FormateDB(cordenada.getData())+"','"+cordenada.getIMEI()+"')";
       
       return ConexaoDb.sqlExecute(sql.replace("[", "").replace("]", ""));
    }
    
    public static void inserirCordenadas(List<Cordenada> cordenadas){
        for (Cordenada cordenada : cordenadas) {
            inserirCordenada(cordenada);
        }
    }
    
    public static Cordenada getCordenada(int id){
       String sql = "SELECT * FROM  [Cordenadas] WHERE [Id] =" + id;
       ResultSet res = ConexaoDb.getResultSet(sql.replace("[", "").replace("]", ""));
       Cordenada newCordenada= null;
       
        try {
            while (res.next()) {
                newCordenada = new Cordenada();
                newCordenada.setId(res.getInt("Id"));
                newCordenada.setLatitude(res.getFloat("Latitude"));
                newCordenada.setLongitude(res.getFloat("Longitude"));
                newCordenada.setData(FormataData.FormateClasse(res.getString("Data")));
                newCordenada.setIMEI(res.getString("IMEI"));
            }
        } catch (SQLException ex) {
            printStackTrace(ex.getMessage());
        }
        
       
        return  newCordenada;
    }
    
    public static ArrayList<Cordenada> getCordenadas(){
       String sql = "SELECT * FROM  [Cordenadas]";
       ResultSet res = ConexaoDb.getResultSet(sql.replace("[", "").replace("]", ""));
       ArrayList<Cordenada> listCordenadas = new ArrayList<>();
       Cordenada newCordenada;
       
        try {
            while (res.next()) {
                newCordenada = new Cordenada();
                newCordenada.setId(res.getInt("Id"));
                newCordenada.setLatitude(res.getFloat("Latitude"));
                newCordenada.setLongitude(res.getFloat("Longitude"));
                newCordenada.setData(FormataData.FormateClasse(res.getString("Data")));
                //newCordenada.setData(new Date(res.getDate("Data").getTime()));
                newCordenada.setIMEI(res.getString("IMEI"));
                listCordenadas.add(newCordenada);
            }
        } catch (SQLException ex) {
            printStackTrace(ex.getMessage());
        }

        return  listCordenadas;
    }
    
     public static ArrayList<Cordenada> getCordenadasImei(String imei){
       String sql = "SELECT * FROM  [Cordenadas] WHERE [IMEI] = '" + imei +"'";
       ResultSet res = ConexaoDb.getResultSet(sql.replace("[", "").replace("]", ""));
       ArrayList<Cordenada> listCordenadas = new ArrayList<>();
       Cordenada newCordenada= null;
       
        try {
            while (res.next()) {
                newCordenada = new Cordenada();
                newCordenada.setId(res.getInt("Id"));
                newCordenada.setLatitude(res.getFloat("Latitude"));
                newCordenada.setLongitude(res.getFloat("Longitude"));
                newCordenada.setData(FormataData.FormateClasse(res.getString("Data")));
                newCordenada.setIMEI(res.getString("IMEI"));
                listCordenadas.add(newCordenada);
            }
        } catch (SQLException ex) {
            printStackTrace(ex.getMessage());
        }

        return  listCordenadas;
    }
    
    public static boolean  excluirCordenadaId(int id){
        String sql  = "DELETE FROM [Cordenadas] WHERE [Id]=" +id;
        return ConexaoDb.sqlExecute(sql);
    }
     public static boolean excluirCordenadaData(Date dataInicial, Date dataFinal){
        String sql  = "DELETE FROM [Cordenadas] WHERE [Data] BETWEEN '" + FormataData.FormateDB(dataInicial) + "' and '" +FormataData.FormateDB(dataFinal) +"'";
        return ConexaoDb.sqlExecute(sql.replace("[", "").replace("]", ""));
    }
     
    public static ArrayList<Cordenada> getCordenadaIds(List<Integer> ids){
        
       if(ids.isEmpty()){
           return new ArrayList<>();
       }
       
       String idString = "";
       String whereIds = "";
       boolean primeiroId = true;
        for (int idRes : ids) {
            if(primeiroId)
                idString = idString +  idRes;
            
            idString += "," + idRes;
            primeiroId = false;
        }
               
       whereIds = "("+ idString +")";
       String sql = "SELECT * FROM  [Cordenadas] WHERE [Id] IN" + whereIds;
       ResultSet res = ConexaoDb.getResultSet(sql.replace("[", "").replace("]", ""));
       ArrayList<Cordenada> listCordenadas = new ArrayList<>();
       Cordenada newCordenada= null;
       
        try {
            while (res.next()) {
                newCordenada = new Cordenada();
                newCordenada.setId(res.getInt("Id"));
                newCordenada.setLatitude(res.getFloat("Latitude"));
                newCordenada.setLongitude(res.getFloat("Longitude"));
                newCordenada.setData(res.getDate("Data"));
                newCordenada.setIMEI(res.getString("IMEI"));
                listCordenadas.add(newCordenada);
            }
        } catch (SQLException ex) {
            printStackTrace(ex.getMessage());
        }

        return  listCordenadas;
    }
    
    public static boolean atualizarCordenada(Cordenada cordenada){
        String sql = "UPDATE [Cordenadas] SET [Latitude] =" +cordenada.getLatitude()+ ",[Longitude] ="+cordenada.getLongitude()+",[Data]='"+FormataData.FormateDB(cordenada.getData())+"',[IMEI]='"+cordenada.getIMEI()+"' WHERE [Id]= " + cordenada.getId();
        return ConexaoDb.sqlExecute(sql.replace("[", "").replace("]", ""));
    }
    
    public static void main(String[] args) {
        //ArrayList<Cordenada> co = CordenadaDAO.get
        //CordenadaDAO dao = new CordenadaDAO();
//       ArrayList<Cordenada> cordenadas = CordenadaDAO.getCordenadas();  
//       Gson g = new Gson();
//       System.out.println(g.toJson(cordenadas));
       
        Cordenada newCor = new Cordenada();
        newCor.setLongitude((float) 1000.431);
        newCor.setLatitude((float) 1400.431);
        newCor.setData(FormataData.getDataAtual());
        newCor.setIMEI("1234");
        CordenadaDAO.inserirCordenada(newCor);
//          Cordenada c = getCordenada(7);
//          
//          c.setIMEI("4321");
//          
//          CordenadaDAO.atualizarCordenada(c);
        
        //System.out.println(""+ co.size());
   }

}
