package com.proyecto.dao;

import com.proyecto.utilidades.DataConnect;
import com.proyecto.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RemoveDAO
{ 
    public static Ciclo removeCicloUser(Ciclo c, String user, String anio)
    {
        Ciclo ciclo = c;
        Connection con = null;
        PreparedStatement ps = null;
 
        try{
            con = DataConnect.getConnection();
            
            ps = con.prepareStatement("delete from cursa_ciclo where id_alumno = ? and id_ciclo = ? and EXTRACT(YEAR FROM cursa_ciclo.fecha_fin) = ?");
            ps.setString(1, user);
            ps.setString(2, ciclo.getId_ciclo());
            ps.setString(3, anio);

            int rs = ps.executeUpdate();

            if (rs != 0) {
                System.out.println("Delete ok");
            }

        } catch (SQLException ex) {
            System.out.println("Remove ciclo user KO-->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return c;
    }

    public static Apt removeAptUser(Apt a, String user)
    {   
        Apt aptitud = a;
        Connection con = null;
        PreparedStatement ps = null;
 
        try{
            con = DataConnect.getConnection();
            
            ps = con.prepareStatement("delete from adquiere_aptitud where id_alumno = ? and id_apt = ?");
            ps.setString(1, user);
            ps.setInt(2, a.getId_apt());

            int rs = ps.executeUpdate();

            if (rs != 0) {
                System.out.println("Delete ok");
            }

        } catch (SQLException ex) {
            System.out.println("Remove aptitud ko-->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return a;
    }

    public static OTitulacion removeOTUser(OTitulacion ot, String user)
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