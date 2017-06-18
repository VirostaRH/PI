package com.proyecto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.proyecto.model.*;
import com.proyecto.interfaces.*;

import com.proyecto.utilidades.DataConnect;
import com.proyecto.utilidades.Parseador;

public class OTitulacionCRUD implements IOTitulacionCRUD
{

    CentroCRUD centroCRUD = new CentroCRUD();
    Cursa_OTCRUD cursa_OTCRUD = new Cursa_OTCRUD();

    public OTitulacionCRUD(){}
    
    public OTitulacion findOT(String nombre_OT, String descripcionOt, Centro centro, String user, int fecha_fin_ot){

        Connection con = null;
        PreparedStatement ps = null;

        //retorno
        OTitulacion oTit = new OTitulacion();
        System.out.println(centroCRUD.findCentroById(5));
        try {
                con = DataConnect.getConnection();
                ps = con.prepareStatement("Select OT.* from OT where nombre_OT = ?");
                ps.setString(1, nombre_OT);
                System.out.println(nombre_OT + descripcionOt + centro.getNombre_centro() + centro.getId_centro());
                
                ResultSet rs = ps.executeQuery();

                if(rs.next())
                {
                    oTit.setId_OTitulacion(rs.getInt("id_OT"));
                    oTit.setNombre(rs.getString("nombre_OT"));
                    oTit.setDescripcion(rs.getString("descripcion_OT"));
                    oTit.setCentro(centroCRUD.findCentroById(rs.getInt("id_centro")));
                }
                else
                {
                    oTit.setNombre(nombre_OT);
                    oTit.setDescripcion(descripcionOt);
                    oTit.setCentro(centro);
                    insertOtraTitulacion(oTit, user, fecha_fin_ot);
                    cursa_OTCRUD.insertCursaOT(oTit.getId_OTitulacion(), user, fecha_fin_ot);
                }
            } catch (SQLException ex) {
                    System.out.println("Búsqueda OT KO-->" + ex.getMessage());
                } finally {
                    DataConnect.close(con);
                }
        
        return oTit;
    }


    public boolean insertOtraTitulacion(OTitulacion o, String user, int fecha_fin)
    {
        OTitulacion ot = o;
        Connection con = null;
        PreparedStatement ps = null;
 
        try{
            con = DataConnect.getConnection();
            
            ps = con.prepareStatement("insert into OT set nombre_OT = ?, descripcion_OT = ?, id_centro = ?");
            ps.setString(1, ot.getNombre());
            ps.setString(2, ot.getDescripcion());
            ps.setInt(3, ot.getCentro().getId_centro());
            
            int rs = ps.executeUpdate();

            if (rs != 0) {
                System.out.println("Insert OT ok");
                return true;
            }

        } catch (SQLException ex) {
            System.out.println("INSERT OT KO" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return false;
    }

    public ArrayList <OTitulacion> buscarOtrasTitulacionesUser(String user){
        //conexión a bbdd
        Connection con = null;
        PreparedStatement ps = null;

        //retorno
        ArrayList <OTitulacion> listadoOtrasTitulaciones = new ArrayList <OTitulacion> ();

        try {

            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select distinct OT.*, EXTRACT(YEAR FROM cursa_OT.fecha_fin) as anio from OT inner join cursa_OT on cursa_OT.id_OT=OT.id_OT inner join alumno on cursa_OT.id_alumno = alumno.id_alumno where alumno.id_alumno = ?");
            
                ps.setString(1, user);
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OTitulacion oAux = new OTitulacion(rs.getInt("id_OT"), rs.getString("nombre_OT"), rs.getString("descripcion"), rs.getInt("id_centro"));
                oAux.setFecha_fin(rs.getInt("anio"));
                listadoOtrasTitulaciones.add(oAux);
            }

            return listadoOtrasTitulaciones;

        } catch (SQLException ex) {
            System.out.println("Búsqueda Otras titulaciones alumno KO-->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return listadoOtrasTitulaciones;
    }

    public OTitulacion removeOTUser(OTitulacion ot, String user)
    {   
        OTitulacion a = ot;
        Connection con = null;
        PreparedStatement ps = null;
 
        try{
            con = DataConnect.getConnection();
            
            ps = con.prepareStatement("delete from otra_titulacion where id_alumno = ? and id_Titulacion = ?");
            ps.setString(1, user);
            ps.setInt(2, ot.getId_OTitulacion());

            int rs = ps.executeUpdate();

            if (rs != 0) {
                System.out.println("Delete ok");
            }

        } catch (SQLException ex) {
            System.out.println("Remove titulacion ko-->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return a;
    }
}