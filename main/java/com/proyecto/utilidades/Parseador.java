package com.proyecto.utilidades;

import java.sql.ResultSet;
import com.proyecto.model.Resultado;
import java.util.ArrayList;
import java.util.Date;

public class Parseador {

	public static ArrayList <Resultado> transformaResulset(ArrayList <ResultSet> r) {
		
		ArrayList <Resultado> retorno = new ArrayList <Resultado> ();
		
		try {
			for (ResultSet entrada : r)
			{
				retorno.add(new Resultado(entrada.getString("nombre"), entrada.getString("apellido1"), entrada.getString("apellido2"), entrada.getString("fnac"), entrada.getString("direccion"), entrada.getString("localidad"), entrada.getInt("cp"), entrada.getString("provincia"),  Integer.parseInt(entrada.getString("tlf")), entrada.getString("email1"),  entrada.getString("email2"),  entrada.getBoolean("disponibilidad"),  entrada.getString("observaciones")));
			}
		} catch (Exception ex) {
			System.out.println("Database.getConnection() Error -->"
					+ ex.getMessage());
			return null;
		}
		return retorno;
	}	
}