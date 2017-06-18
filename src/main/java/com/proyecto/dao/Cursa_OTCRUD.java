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

public class Cursa_OTCRUD implements ICursaOTCRUD
{

    public Cursa_OTCRUD(){}
    
    public void insertCursaOT(int id_OTitulacion, String user, int fecha_fin)
    {
        
        Connection con = null;
        PreparedStatement ps = null;
 
        try{
            con = DataConnect.getConnection();
            
            ps = con.prepareStatement("insert into cursa_OT set id_alumno = ?, id_OT = ?, fecha_fin = ?");
            ps.setString(1, user);
            ps.setInt(2, id_OTitulacion);
            ps.setString(3, fecha_fin+":07:22");
            int rs = ps.executeUpdate();
            if (rs != 0) {
                System.out.println("Insert OT ok");
            }

        } catch (SQLException ex) {
            System.out.println("INSERT OT KO" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        
    }
}
