package com.proyecto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.proyecto.utilidades.DataConnect;

public class LoginDAO {

	public static boolean validate(String user, String password) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("Select id_usuario, password from usuario where id_usuario = ? and password = ?");
			ps.setString(1, user);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				//hay resultado, es correcto.
				return true;
			}
		} catch (SQLException ex) {
			System.out.println("Login KO-->" + ex.getMessage());
			return false;
		} finally {
			DataConnect.close(con);
		}
		return false;
	}

	public static String alumnoTipo(String user) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("select rol_alumno from usuario where id_usuario = ?");
			ps.setString(1, user);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				//hay resultado, es correcto.
				if(rs.getBoolean("rol_alumno"))
				{
					return "alumno";
				}
				return "profesor";
			}
		} catch (SQLException ex) {
			return "error";
		} finally {
			DataConnect.close(con);
		}
		return "error";
	}
}