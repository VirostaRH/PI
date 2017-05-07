package com.proyecto.dao;

import com.proyecto.model.Busqueda;
import com.proyecto.model.Alumno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.proyecto.utilidades.DataConnect;

public class FinderDAO {

    public static ArrayList <Alumno> buscar(Busqueda data) {

        //datos para bbdd

        String [] aptitudes = descomprimeAptitudes(data.getAptitud());
        String valorEdad = (data.getEdad() != null) ? "":"";
        String trozoCiclo = "";
        String trozoAptitud = (aptitudes == null) ? null:cargaAptitudes(aptitudes);
        String trozoEdad = (data.getEdad() == "") ? "12" : valorEdad;

        //conexión a bbdd
    	Connection con = null;
		PreparedStatement ps = null;

        //retorno
        ArrayList <Alumno> a = new ArrayList <Alumno>();

        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select distinct alumno.* from ciclo inner join cursa_ciclo on ciclo.id_ciclo = cursa_ciclo.id_ciclo inner join alumno on cursa_ciclo.id_alumno = alumno.id_alumno where ciclo.siglas= ? and alumno.disponibilidad = ?");
            ps.setString(1, data.getCiclo());
            ps.setBoolean(2, data.getDispo());
            //ps.setString(3, valorEdad);
            if (trozoAptitud != null)
                {
                    ps = con.prepareStatement("Select distinct alumno.* from ciclo inner join cursa_ciclo on ciclo.id_ciclo = cursa_ciclo.id_ciclo inner join alumno on cursa_ciclo.id_alumno = alumno.id_alumno where ciclo.siglas= ? and alumno.disponibilidad = ? ?");
                    ps.setString(1, data.getCiclo());
                    ps.setBoolean(2, data.getDispo());
                    ps.setString(3, trozoAptitud);
                }

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                a.add(new Alumno(rs.getString(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getString(9),rs.getInt(10),rs.getString(11),rs.getString(12),rs.getBoolean(13),rs.getString(14)));
            }

            return a;
        } catch (SQLException ex) {
            System.out.println("Login KO-->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }

        return a;
    }

    private static String cargaAptitudes(String[] aptitudesParaSql)
    {
    	String [] aptitudes = aptitudesParaSql;
    	String carroSql = null;

        if (aptitudes.length != 0)
        {
        	for(int i = 0; i <= aptitudes.length; i++)
        	{
                System.out.println("entra" + i);
        		carroSql= carroSql+"AND APTITUD.nombre_aptitud = '"+aptitudes[i]+"'";
        	}
        }
    	return carroSql;
    }

    private static String [] descomprimeAptitudes(String aptitudes)
    {
    	String [] retorno = null;
    	
    	if(aptitudes.length() != 0)
    	{
    		retorno = aptitudes.split("\\,");
    	}
    	return retorno;
    }
}