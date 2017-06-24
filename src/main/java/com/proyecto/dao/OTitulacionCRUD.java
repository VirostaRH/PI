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

//(int i, String nombre, String descripcion, int fecha_fin)
//()


public class OTitulacionCRUD implements IOTitulacionCRUD
{

    ICentroCRUD centroCRUD = new CentroCRUD();
    public OTitulacionCRUD(){}

    public boolean insertOT(String nombre, String descripcion, Centro centro)
    {
        Connection con = null;
        PreparedStatement ps = null;
        

        try{
            if (buscarOTbyNameAndCentro(nombre, centro))
            {
                return true;
            }    
            else
            {
                con = DataConnect.getConnection();
            
                ps = con.prepareStatement("insert into OT set nombre_OT = ?, descripcion_OT = ?, id_centro = ?");
                ps.setString(1, nombre);
                ps.setString(2, descripcion);
                ps.setInt(3, centro.getId_centro());
                int rs = ps.executeUpdate();

                if (rs != 0) {
                    return true;
                }

                return false;
            }

        } catch (SQLException ex) {
            System.out.println("insert OT KO ---> " + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return false;
    } 

    public boolean buscarOTbyNameAndCentro(String nombre_ot, Centro centro)
    {
        //conexión a bbdd
        Connection con = null;
        PreparedStatement ps = null;

        //retorno
        OTitulacion ot=new OTitulacion();
        int c = centro.getId_centro();

        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select * from OT where nombre_OT = ? and id_centro = ?");
                ps.setString(1, nombre_ot);
                ps.setInt(2, c);
            
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }
            else
            {
                return false;
            }

        } catch (SQLException ex) {
            System.out.println("Búsqueda OT.nameandcentro KO-->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return false;
    }

    public OTitulacion buscarOTbyNameAndCentroObj(String nombre_ot, Centro centro, String fecha_fin)
    {
        //conexión a bbdd
        Connection con = null;
        PreparedStatement ps = null;

        //retorno
        OTitulacion ot=new OTitulacion();

        int c=centro.getId_centro();
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select * from OT where nombre_OT = ? and id_centro = ?");
                ps.setString(1, nombre_ot);
                ps.setInt(2, c);
            
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ot = new OTitulacion(rs.getInt("id_OT"), rs.getString("nombre_OT"), rs.getString("descripcion_OT"), Integer.parseInt(fecha_fin));
                return ot;
            }
            else
            {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("Búsqueda OT.nameandcentroobj KO-->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return null;
    }

    public OTitulacion buscarById(int id_ot)
    {
        //conexión a bbdd
        Connection con = null;
        PreparedStatement ps = null;

        //retorno
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select * from OT where id_OT = ?");
                ps.setInt(1, id_ot);
            
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                OTitulacion ot = new OTitulacion(rs.getInt("id_OT"), rs.getString("nombre_OT"), rs.getString("descripcion_OT"), 0);
                ot.setCentro(centroCRUD.findCentroById(rs.getInt("id_centro")));
                return ot;
            }
            else
            {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("Búsqueda OT.byid KO-->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return null;  
    }
}