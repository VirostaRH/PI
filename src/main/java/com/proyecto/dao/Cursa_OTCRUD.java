package com.proyecto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;
import com.proyecto.model.*;
import com.proyecto.interfaces.*;
import com.proyecto.dao.*;

import com.proyecto.utilidades.DataConnect;
import com.proyecto.utilidades.Parseador;

public class Cursa_OTCRUD implements ICursaOTCRUD
{

    OTitulacionCRUD oTitulacionCRUD = new OTitulacionCRUD();

    public Cursa_OTCRUD(){}
    
    public void insertCursaOT(OTitulacion ot, String user)
    {
        
        Connection con = null;
        PreparedStatement ps = null;
 
        try{
            con = DataConnect.getConnection();
            
            if(buscarOTCursadaByUserAndOT(ot, user))
            {
                System.out.println("Ya existente.");
            }
            else
            {
                ps = con.prepareStatement("insert into cursa_OT set id_alumno = ?, id_OT = ?, fecha_fin = ?");
                ps.setString(1, user);
                ps.setInt(2, ot.getId_OTitulacion());
                ps.setString(3, ot.getFecha_fin()+":07:22");
                int rs = ps.executeUpdate();
                if (rs != 0) {
                    System.out.println("Insert CURSA_OT ok");
                }
            }

        } catch (SQLException ex) {
            System.out.println("INSERT CURSA_OT KO" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
    }

    public ArrayList <OTitulacion> buscarOtrasTitulacionesUser(String user)
    {
        //conexión a bbdd
        Connection con = null;
        PreparedStatement ps = null;

        //retorno
        ArrayList <OTitulacion> listado = new ArrayList <OTitulacion>();

        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select id_OT, id_alumno, EXTRACT(YEAR FROM cursa_OT.fecha_fin) as anio from cursa_OT where id_alumno = ?");
                ps.setString(1, user);
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OTitulacion ot = oTitulacionCRUD.buscarById(rs.getInt("id_OT"));
                ot.setFecha_fin(rs.getInt("anio"));
                listado.add(ot);
            }
            return listado;

        } catch (SQLException ex) {
            System.out.println("Búsqueda cursa_OT KO-->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return listado;
    }

    public boolean buscarOTCursadaByUserAndOT(OTitulacion ot, String user)
    {
        //conexión a bbdd
        Connection con = null;
        PreparedStatement ps = null;

        //retorno
        int id_OT = ot.getId_OTitulacion();

        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select * from cursa_OT where id_alumno = ? and id_OT = ? and fecha_fin = ?");

                ps.setString(1, user);
                ps.setInt(2, id_OT);
                ps.setString(3, ot.getFecha_fin()+":07:22");
            
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

    public void deleteCursaOT(OTitulacion ot, String user)
    {
        //conexión a bbdd
        Connection con = null;
        PreparedStatement ps = null;
        
        try{
            con = DataConnect.getConnection();
            ps = con.prepareStatement("delete from cursa_OT where id_alumno = ? and id_OT = ? and EXTRACT(YEAR FROM cursa_OT.fecha_fin) = ?");
            
            ps.setString(1, user);
            ps.setInt(2, ot.getId_OTitulacion());
            ps.setInt(3, ot.getFecha_fin());

            int rs = ps.executeUpdate();
            
            if (rs != 0) {
                System.out.println("Delete ok");
            }
            else
            {
                System.out.println("No produce resultado:\n");
            }

        } catch (SQLException ex) {
            System.out.println("CursaOT.Deletecursa_OT ko" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
    }


}
