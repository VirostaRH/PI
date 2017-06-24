package com.proyecto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.proyecto.interfaces.*;

import com.proyecto.utilidades.DataConnect;

public class UsuariosC implements IUsuariosC
{

    Connection con = null;
    PreparedStatement ps = null;

    public UsuariosC (){};

	public boolean alta(String user, String password){
		if (buscar(user))
		{
			return false;
		}
		else
		{
	        try {
	            con = DataConnect.getConnection();
	            ps = con.prepareStatement("insert into usuario set id_usuario = ?, password = ?, rol_alumno = ?");
	             
	                ps.setString(1, user);
	                ps.setString(2, password);
	                ps.setBoolean(3, false);
	            
	            int rs = ps.executeUpdate();

	            if(rs != 0)
	            {
	            	System.out.println("Inserta");
	            	return true;
	            }
	            else
	            {
	            	System.out.println("NO Inserta");
	                return false;
	            }

	        } catch (SQLException ex) {
	            System.out.println("Búsqueda usuario KO-->" + ex.getMessage());
	        } finally {
	            DataConnect.close(con);
        	}

			return false;
		}
	}

	public boolean buscar (String user)
	{

        //retorno
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select * from usuario where id_usuario = ?");
            ps.setString(1, user);
            
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
            	System.out.println("Encuentra");
            	return true;
            }
            else
            {
            	System.out.println("No encuentra");
                return false;
            }

        } catch (SQLException ex) {
            System.out.println("Búsqueda usuario KO-->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return false;
	}
}
