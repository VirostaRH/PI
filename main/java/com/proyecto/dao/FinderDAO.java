package com.proyecto.dao;

import com.proyecto.model.Busqueda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import com.proyecto.utilidades.DataConnect;

public class FinderDAO {

    public static ArrayList <ResultSet> buscar(Busqueda data) {


    	Connection con = null;
		PreparedStatement ps = null;

		String[] aptitudes = descomprimeAptitudes(data.getAptitud());

		String valorEdad = "edad <= "+data.getEdad()+" and ";
		String trozoCiclo = "";
		String trozoAptitud = (aptitudes == null) ? "" : cargaAptitudes(aptitudes);
		String trozoEdad = (data.getEdad() == "") ? "" : valorEdad;

		String sql = "select distinct ALUMNO.id_alumno, ALUMNO.nombre, CURSACICLO.fecha_fin, CICLO.siglas, ADQUIEREAPTITUD.id_aptitud from alumno ALUMNO inner join cursa_ciclo CURSACICLO on ALUMNO.id_alumno=CURSACICLO.id_alumno inner join ciclo CICLO on CURSACICLO.id_ciclo=CICLO.id_ciclo inner join adquiere_aptitud ADQUIEREAPTITUD on ADQUIEREAPTITUD.id_alumno = ALUMNO.id_alumno inner join aptitud APTITUD on ADQUIEREAPTITUD.id_aptitud = APTITUD.id_aptitud where ALUMNO.disponibilidad = "+ data.getDispo()+ " AND CICLO.siglas like '"+data.getCiclo()+"' "+trozoAptitud;
		ArrayList <ResultSet> alumnos = new ArrayList <ResultSet>();

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				alumnos.add(rs);
			}

		} catch (SQLException ex) {
			return null;
		} finally {
			DataConnect.close(con);
		}

		return alumnos;

    }

    private static String cargaAptitudes(String[] aptitudesParaSql)
    {
    	String [] aptitudes = aptitudesParaSql;
    	String carroSql = "";
    	int i = 0;

    	while (aptitudesParaSql != null && i < aptitudesParaSql.length)
    	{
    		carroSql= carroSql+"AND APTITUD.nombre_aptitud = '"+aptitudes[i]+"' ";
    		i++;
    	}
    	return carroSql;
    }

    private static String[] descomprimeAptitudes(String aptitudes)
    {
    	String [] ret = null;

    	if (aptitudes.length() > 0)
    		{
    			//machacar esto con metodo recursivo!
    			ret=aptitudes.split(",");
    			return ret;
    		}
	    return ret;
    }
}