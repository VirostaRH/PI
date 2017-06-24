package com.proyecto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import org.joda.time.DateTime;
import com.proyecto.model.*;
import com.proyecto.interfaces.*;

import com.proyecto.utilidades.*;

public class AlumnosCRUD implements IAlumnosCRUD
{
    public AlumnosCRUD(){}
    
    public ArrayList <Alumno> buscar(Busqueda data) {

        //datos para bbdd

        String [] aptitudes = Parseador.descomprimeAptitudes(data.getAptitud());
        String valorEdad = (data.getEdad() != "") ? data.getEdad() : "67";
        String trozoCiclo = data.getCiclo();
        String trozoAptitud = (aptitudes == null) ? null:"and "+Parseador.cargaAptitudes(aptitudes);

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
                    ps = con.prepareStatement("Select distinct alumno.* from ciclo inner join cursa_ciclo on ciclo.id_ciclo = cursa_ciclo.id_ciclo inner join alumno on  alumno.id_alumno = cursa_ciclo.id_alumno left join adquiere_aptitud on alumno.id_alumno = adquiere_aptitud.id_alumno left join aptitud on adquiere_aptitud.id_aptitud = aptitud.id_aptitud where ciclo.siglas= ? and alumno.disponibilidad = ? and TIMESTAMPDIFF(YEAR, alumno.f_nac,CURDATE()) between 16 and ? and aptitud.nombre_aptitud is null or aptitud.nombre_aptitud = ?");
                    ps.setString(1, data.getCiclo());
                    ps.setBoolean(2, data.getDispo());
                    ps.setString(3, valorEdad);
                    ps.setString(4, trozoAptitud);
                }



            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                a.add(new Alumno(rs.getString(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getString(9),rs.getInt(10),rs.getString(11),rs.getString(12),rs.getBoolean(13),rs.getString(14)));
            }
            return a;
        } catch (SQLException ex) {
            System.out.println("Buscar Alumnos KO-->" + ex.getMessage());
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
            System.out.println("Búsqueda un alumno KO-->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return a;
    }

    public boolean comprobarExiste(String user)
    {
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
                CreadorPDF pdf = new CreadorPDF();
                pdf.createPDF(new File("/home/xules/codigoxules/GeneratePDFFileIText.pdf"));
                return true;
            }
            return false;
        } catch (SQLException ex) {
            System.out.println("Búsqueda un alumno KO-->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return false;
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
            System.out.println(" Actualizar alumno KO->"+ex);
        } finally {
            DataConnect.close(con);
        }
        return false;
    }

    public Alumno altaDD (String id_alumno, String nombre, String apellido1, String apellido2, String fnac, String direccion, String localidad, int codp, String provincia, int tlf, String email1, String email2, boolean disponibilidad, String observaciones)
    {
        //conexión a bbdd
        Connection con = null;
        PreparedStatement ps = null;

        String n = nombre;
        String a1 = apellido1;
        String a2 = apellido2;
        String f = fnac;
        String d = direccion;
        String l = localidad;
        String p = provincia;
        String e1 = email1;
        String e2 = email2;
        String o = observaciones;
        int t = tlf;
        int cp = codp;
        boolean disp = disponibilidad;

        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("insert into alumno set nombre = ?, apellido1 = ?, apellido2 = ?, f_nac = ?, direccion = ?, localidad = ?, cp = ?, provincia = ?, telefono = ?, email = ?, email2 = ?, disponibilidad = ?, observaciones = ?, id_alumno = ?");
            
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
            ps.setString(14,id_alumno);

            int rs = ps.executeUpdate();

            if (rs==1) {
                return new Alumno (id_alumno, nombre, apellido1, apellido2, fnac, direccion, localidad, cp, provincia, tlf, email1, email2, disponibilidad, observaciones);
            }

        } catch (SQLException ex) {
            System.out.println("Insertar alumno KO->"+ex);
        } finally {
            DataConnect.close(con);
        }
        return null;       
    }
}