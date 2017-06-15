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

public class CentroCRUD implements ICentroCRUD
{
    public CentroCRUD(){}
    
    public boolean insertCentro(String n){
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
            System.out.println("CENTRO KO" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return false;
    }

    public Centro findCentro(String n_centro){
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
                c.setId_centro(rs.getInt("id_centro"));
                c.setNombre_centro(rs.getString("nombre_centro"));
            }
            else
            {
                if(insertCentro(n_centro)){
                    c = findCentro(n_centro);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Login KO-->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return c;
    }

    public Centro findCentroById(int id){
        Centro c = new Centro();
        //conexión a bbdd
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select * from centro where id_centro= ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                c.setId_centro(rs.getInt("id_centro"));
                c.setNombre_centro(rs.getString("nombre_centro"));
            }
        } catch (SQLException ex) {
            System.out.println("Login KO-->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return c;
    }
  
}