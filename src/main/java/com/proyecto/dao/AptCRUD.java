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

public class AptCRUD implements IAptCRUD
{
    public boolean insertApt(String n, String descripcion)
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
            System.out.println("APTITUD KO" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        System.out.println("Entra en insertDao y resultado en insert ko");
        return false;
    } 

    public boolean addAptUser(Apt a, String user)
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
                return true;
            }

        } catch (SQLException ex) {
            System.out.println("Insert APT KO-->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return false;
    }

    public Boolean removeAptUser(int a, String user)
    {   
        Apt aptitud = new Apt();
        aptitud.setId_apt(a);
        Connection con = null;
        PreparedStatement ps = null;
 
        try{
            con = DataConnect.getConnection();
            
            ps = con.prepareStatement("delete from adquiere_aptitud where id_alumno = ? and id_aptitud = ?");
            ps.setString(1, user);
            ps.setInt(2, aptitud.getId_apt());

            int rs = ps.executeUpdate();

            if (rs != 0) {
                System.out.println("Delete ok");
                return true;
            }

        } catch (SQLException ex) {
            System.out.println("Remove aptitud ko-->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return false;
    }

        public ArrayList <Apt> buscarAptUser(String user){
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

    public Apt buscarApt(String nombre_apt, String descripcionApt){
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
                insertApt(nombre_apt, descripcionApt);
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
}