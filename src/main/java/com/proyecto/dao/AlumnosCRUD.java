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

public class AlumnosCRUD implements IAlumnosCRUD
{
    public AlumnosCRUD(){}
    
    public ArrayList <Alumno> buscar(Busqueda data) {

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

    public Alumno buscarUno(String user){
        //conexión a bbdd
        Connection con = null;
        PreparedStatement ps = null;
        //retorno
        Alumno a = null;

        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select * from alumno where alumno.id_alumno = ?");
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

    public boolean actualizar(Alumno a){
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