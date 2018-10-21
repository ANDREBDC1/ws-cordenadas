/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Andre Rian
 */
public class FormataData {
    public static String FormateDB(Date data){
        SimpleDateFormat format_ = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format_.format(data);
    }
    
    public static Date FormateClasse(String data){
        SimpleDateFormat format_ = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            return format_.parse(data);
        } catch (ParseException ex) {
            Logger.getLogger(FormataData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  null;
    }
    public static Date getDataAtual(){
	Calendar c = Calendar.getInstance();
        return c.getTime();
    }
}
