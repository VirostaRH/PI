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

public class CiclosCRUD implements ICiclosCRUD
{
    public ArrayList <Ciclo> buscarCiclosUser(String user){
        //conexión a bbdd
        Connection con = null;
        PreparedStatement ps = null;

        //retorno
        ArrayList <Ciclo> c = new ArrayList <Ciclo> ();

        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select distinct ciclo.*, EXTRACT(YEAR FROM cursa_ciclo.fecha_fin) as anio from ciclo inner join cursa_ciclo on cursa_ciclo.id_ciclo=ciclo.id_ciclo inner join alumno on cursa_ciclo.id_alumno = alumno.id_alumno where alumno.id_alumno = ?");
                ps.setString(1, user);
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                c.add(new Ciclo(rs.getString("id_ciclo"), rs.getString("nombre_ciclo"), rs.getString("siglas"), rs.getString("anio")));
            }
            return c;
        } catch (SQLException ex) {
            System.out.println("Búsqueda ciclo KO-->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return c;
    }

    public Ciclo buscarCiclo(String siglas){
        //conexión a bbdd
        Connection con = null;
        PreparedStatement ps = null;

        //retorno
        Ciclo c = new Ciclo();

        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select ciclo.* from ciclo where siglas = ?");
                ps.setString(1, siglas);
            
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                c.setId_ciclo(rs.getString("id_ciclo"));
                c.setNombre_ciclo(rs.getString("nombre_ciclo"));
                c.setSiglas(rs.getString("siglas"));
            }
        } catch (SQLException ex) {
            System.out.println("Búsqueda Apt KO-->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return c;
    }
    
    public boolean addCicloUser(Ciclo c, String user){
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
                return true;
            }

        } catch (SQLException ex) {
            System.out.println("INSERT CICLO KO" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return false;
    }

    public Ciclo removeCicloUser(Ciclo c, String user, String anio){
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
}