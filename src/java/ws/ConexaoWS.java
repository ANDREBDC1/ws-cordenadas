/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dao.CordenadaDAO;
import dao.FormataData;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import modelo.Cordenada;
import sun.text.resources.FormatData;

/**
 * REST Web Service
 *
 * @author Andre Rian
 */
@Path("wsConection")
public class ConexaoWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ConexaoWS
     */
    public ConexaoWS() {
    }

    /**
     * Retrieves representation of an instance of ws.ConexaoWS
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
       return "Web Server On";   
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Cordenadas/get")
    public String getCordenadasLista() {
        //TODO return proper representation object
       ArrayList<Cordenada> Cordenadas = CordenadaDAO.getCordenadas();
       Gson g = new Gson();
       return g.toJson(Cordenadas);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Cordenadas/Imei/{IMEI}")
    public String getCordenadasImei(@PathParam("IMEI") String imei) {
        //TODO return proper representation objec
       ArrayList<Cordenada> Cordenadas = CordenadaDAO.getCordenadasImei(imei);
       Gson g = new Gson();
       return g.toJson(Cordenadas);
    }
    
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("Teste/{IMEI}/{Data}")
//    public String getTeste(@PathParam("IMEI") String imei, @PathParam("Data") String data) {
//        //TODO return proper representation objec
//       
//       return "P1" + imei + "  P2 " + data;
//    }
    /**
     * PUT method for updating or creating an instance of ConexaoWS
     * @param content representation for the resource
     */
    
    
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("cordenada/inserir")
    public boolean inserirCordenada(String content){
        Gson g = new Gson();
        Cordenada cordenada = (Cordenada) g.fromJson(content, Cordenada.class);
        cordenada.setData(FormataData.getDataAtual());
       return CordenadaDAO.inserirCordenada(cordenada);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("cordenada/atualizar")
    public boolean atualizarCordenada(String content){
        Gson g = new Gson();
        Cordenada cordenada = (Cordenada) g.fromJson(content, Cordenada.class);
        return CordenadaDAO.atualizarCordenada(cordenada);
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("cordenada/excluir/{id}")
    public boolean deleteCordenadas(@PathParam("id")String idCordenada){
        return CordenadaDAO.excluirCordenadaId((Integer.parseInt(idCordenada)));
    }
}
