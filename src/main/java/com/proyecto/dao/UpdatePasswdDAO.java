package com.proyecto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.proyecto.utilidades.DataConnect;

public class UpdatePasswdDAO {

        public static boolean update(String usuario, String newPassword){
        //conexión a bbdd
    	Connection con = null;
		PreparedStatement ps = null;

        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("update usuario set password = ? where id_usuario = ?");
            ps.setString(1, newPassword);
            ps.setString(2, usuario);
 
            int rs = ps.executeUpdate();

            if (rs==1) {
                return true;
            }
        } catch (SQLException ex) {
        } finally {
            DataConnect.close(con);
        }
        return false;
    }
}