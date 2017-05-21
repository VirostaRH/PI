package com.proyecto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.proyecto.model.Alumno;

import com.proyecto.utilidades.DataConnect;

public class UpdateDatosPersonalesDAO {

        public static boolean actualizar(Alumno a){
        //conexión a bbdd
    	Connection con = null;
		PreparedStatement ps = null;

        String n = (a.getNombre() != "") ? a.getNombre() : "";
        String a1 = (a.getApellido1() != "") ? a.getApellido1() : "";
        String a2 = (a.getApellido2() != "") ? a.getApellido2() : "";
        String f = (a.getFnac() != "") ? a.getFnac() : "";
        String d = (a.getDireccion() != "") ? a.getDireccion() : "";
        String l = (a.getLocalidad() != "") ? a.getLocalidad() : "";
        String p = (a.getProvincia() != "") ? a.getProvincia(): "";
        String e1 = (a.getEmail1() != "") ? a.getEmail1(): "";
        String e2 = (a.getEmail2() != "") ? a.getEmail2(): "";
        String o = (a.getObservaciones() != "") ? a.getObservaciones() : "";
        int t = (a.getTlf() == 000000000) ? 000000000:a.getTlf();
        int cp = (a.getCp() == 000000000) ? 000000000:a.getCp();
        boolean disp = a.isDisponibilidad();

        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("update alumno set nombre = ?, apellido1 = ?, apellido2 = ?, f_nac = ?, direccion = ?, localidad = ?, cp = ?, provincia = ?, telefono = ?, email = ?, email2 = ?, disponibilidad = ?, observaciones = ? where id_alumno = ?");
            


            ps.setString(1,n);
            ps.setString(2,a1);
            ps.setString(3,a2);
            ps.setString(4,f);
            ps.setString(5,d);
            ps.setString(6,l);
            ps.setInt(7,cp);
            ps.setString(8,p);
            ps.setInt(9,t);
            ps.setString(10,e1);
            ps.setString(11,e2);
            ps.setBoolean(12,disp);
            ps.setString(13,o);
            ps.setString(14,a.getId_alumno());
 
            int rs = ps.executeUpdate();

            if (rs==1) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println("ERROR->"+ex);
        } finally {
            DataConnect.close(con);
        }
        return false;
    }
}