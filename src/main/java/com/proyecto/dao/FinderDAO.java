package com.proyecto.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.proyecto.model.*;
import com.proyecto.dao.*;

import com.proyecto.utilidades.DataConnect;
import com.proyecto.utilidades.Parseador;

public class FinderDAO {
   
    public static ArrayList <Alumno> buscar(Busqueda data) {

        //datos para bbdd

        String [] aptitudes = Parseador.descomprimeAptitudes(data.getAptitud());
        String valorEdad = (data.getEdad() != "") ? data.getEdad() : "67";
        String trozoCiclo = "";
        String trozoAptitud = (aptitudes == null) ? null:Parseador.cargaAptitudes(aptitudes);

        //conexión a bbdd
        Connection con = null;
        PreparedStatement ps = null;

        //retorno
        ArrayList <Alumno> a = new ArrayList <Alumno>();

        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select distinct alumno.* from ciclo inner join cursa_ciclo on ciclo.id_ciclo = cursa_ciclo.id_ciclo inner join alumno on cursa_ciclo.id_alumno = alumno.id_alumno where ciclo.siglas= ? and alumno.disponibilidad = ? and TIMESTAMPDIFF(YEAR, alumno.f_nac,CURDATE()) between 16 and ?");
            ps.setString(1, data.getCiclo());
            ps.setBoolean(2, data.getDispo());
            ps.setString(3, valorEdad);

            if (trozoAptitud != null)
                {
                    ps = con.prepareStatement("Select distinct alumno.* from ciclo inner join cursa_ciclo on ciclo.id_ciclo = cursa_ciclo.id_ciclo inner join alumno on cursa_ciclo.id_alumno = alumno.id_alumno where ciclo.siglas= ? and alumno.disponibilidad = ? and TIMESTAMPDIFF(YEAR, alumno.f_nac,CURDATE()) between 16 ?");
                    ps.setString(1, data.getCiclo());
                    ps.setBoolean(2, data.getDispo());
                    ps.setString(3, trozoAptitud);
                }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                a.add(new Alumno(rs.getString(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getString(9),rs.getInt(10),rs.getString(11),rs.getString(12),rs.getBoolean(13),rs.getString(14)));
                System.out.println(a.size());
            }
            return a;
        } catch (SQLException ex) {
            System.out.println("Login KO-->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return a;
    }

    public static Alumno buscarUno(String user){
        //conexión a bbdd
        Connection con = null;
        PreparedStatement ps = null;

        //retorno
        Alumno a = null;

        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select alumno.*  from alumno where alumno.id_alumno = ?");
                ps.setString(1, user);
            
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                a = new Alumno(rs.getString(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getString(9),rs.getInt(10),rs.getString(11),rs.getString(12),rs.getBoolean(13),rs.getString(14));
            }
            return a;
        } catch (SQLException ex) {
            System.out.println("Búsqueda KO-->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }

        return a;
    }

    public static ArrayList <Ciclo> buscarCiclosUser(String user){
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

    public static ArrayList <Apt> buscarAptUser(String user){
        //conexión a bbdd
        Connection con = null;
        PreparedStatement ps = null;

        //retorno
        ArrayList <Apt> a = new ArrayList <Apt> ();

        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select aptitud.id_aptitud, aptitud.nombre_aptitud, aptitud.descripcion, adquiere_aptitud.nivel from aptitud inner join adquiere_aptitud on aptitud.id_aptitud=adquiere_aptitud.id_aptitud where adquiere_aptitud.id_alumno = ?");
                ps.setString(1, user);
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                a.add(new Apt(rs.getInt("id_aptitud"), rs.getString("nombre_aptitud"), rs.getInt("nivel"), rs.getString("descripcion")));
            }

            return a;
        } catch (SQLException ex) {
            System.out.println("Búsqueda Apt KO-->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return a;
    }

    public static Apt buscarApt(String nombre_apt, String descripcionApt){
        //conexión a bbdd
        Connection con = null;
        PreparedStatement ps = null;

        //retorno
        Apt a=new Apt();

        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select aptitud.* from aptitud where nombre_aptitud = ?");
                ps.setString(1, nombre_apt);
            
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                a = new Apt(rs.getInt("id_aptitud"), rs.getString("nombre_aptitud"), rs.getString("descripcion"));
                System.out.println("Entra en finder y hay ResultSet");
            }
            else
            {
                System.out.println("Entra en insertDao");
                InsertDAO.insertApt(nombre_apt, descripcionApt);
                a = buscarApt(nombre_apt, descripcionApt);
            }

            return a;
        } catch (SQLException ex) {
            System.out.println("Búsqueda Apt KO-->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return a;
    }

    public static Ciclo buscarCiclo(String siglas){
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
            return c;
        } catch (SQLException ex) {
            System.out.println("Búsqueda Apt KO-->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return c;
    }

    public static Centro findCentro(String n_centro)
    {
        Centro c = new Centro();
        //conexión a bbdd
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select * from centro where nombre_centro= ?");
            ps.setString(1, n_centro);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                c.setId_centro(Integer.parseInt(rs.getInt("id_centro")));
                c.setNombre_centro(rs.getString("nombre_centro"));
            }
            else
            {
                if(InsertDAO.insertCentro(n_centro)){
                    return c;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Login KO-->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return c;
    }
}