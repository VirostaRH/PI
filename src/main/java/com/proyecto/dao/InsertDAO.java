package com.proyecto.dao;

import com.proyecto.utilidades.DataConnect;
import com.proyecto.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertDAO
{


    public static boolean insertCentro(String n)
    {
        Centro centro = new Centro();    
        Connection con = null;
        PreparedStatement ps = null;
 
        try{
            con = DataConnect.getConnection();
            
            ps = con.prepareStatement("insert into centro set nombre_centro = ?");
            ps.setString(1, n);
            int rs = ps.executeUpdate();   

            if (rs != 0) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println("Login KO-->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return false;
    }

    public static boolean insertApt(String n, String descripcion)
    {
        Connection con = null;
        PreparedStatement ps = null;
 
        try{
            con = DataConnect.getConnection();
            
            ps = con.prepareStatement("insert into aptitud set nombre_aptitud = ?, descripcion = ?");
            ps.setString(1, n);
            ps.setString(2, descripcion);

            int rs = ps.executeUpdate();

            if (rs != 0) {
                System.out.println("Entra en insertDao y resultado a insert ok");
                return true;
            }

        } catch (SQLException ex) {
            System.out.println("Login KO-->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        System.out.println("Entra en insertDao y resultado en insert ko");
        return false;
    }
 
    public static Ciclo addCicloUser(Ciclo c, String user)
    {
        Ciclo ciclo = c;
        Connection con = null;
        PreparedStatement ps = null;
 
        try{
            con = DataConnect.getConnection();
            
            ps = con.prepareStatement("insert into cursa_ciclo set id_alumno = ?, id_ciclo = ?, fecha_fin = ?");
            ps.setString(1, user);
            ps.setString(2, ciclo.getId_ciclo());

            ps.setString(3, ciclo.getFecha_fin()+":07:22");

            int rs = ps.executeUpdate();

            if (rs != 0) {
                System.out.println("Insert ok");
            }

        } catch (SQLException ex) {
            System.out.println("Login KO-->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return c;
    }

    public static Apt addAptUser(Apt a, String user)
    {
        
        Apt aptitud = a;
        Connection con = null;
        PreparedStatement ps = null;
 
        try{
            con = DataConnect.getConnection();
            
            ps = con.prepareStatement("insert into adquiere_aptitud set id_alumno = ?, id_aptitud = ?, nivel = ?");
            ps.setString(1, user);
            ps.setInt(2, a.getId_apt());
            ps.setInt(3, a.getNivel());

            int rs = ps.executeUpdate();

            if (rs != 0) {
                System.out.println("Insert ok");

            }

        } catch (SQLException ex) {
            System.out.println("Login KO-->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }

        return a;
    }

}