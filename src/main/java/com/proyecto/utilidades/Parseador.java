package com.proyecto.utilidades;

import com.proyecto.model.Busqueda;
import com.proyecto.model.Alumno;

import java.util.ArrayList;
import java.util.HashMap;

import com.proyecto.utilidades.DataConnect;

public class Parseador{


    public static String cargaAptitudes(String[] aptitudesParaSql)
    {
    	String [] aptitudes = aptitudesParaSql;
    	String carroSql = "";

        if (aptitudes.length != 0)
        {
            carroSql = "APTITUD.nombre_aptitud = '" + aptitudes[0] + "'";
            if (aptitudes.length > 1)
            {
                for(int i = 1; i < aptitudes.length; i++)
            	{
            		carroSql = carroSql + " OR APTITUD.nombre_aptitud = '" + aptitudes[0] + "'";
            	}
            }
        }
        System.out.println("Retorno carroSql - " + carroSql);
    	return carroSql;
    }

    public static String [] descomprimeAptitudes(String aptitudes)
    {
    	String [] retorno = null;
    	
    	if(aptitudes.length() != 0)
    	{
    		retorno = aptitudes.split("\\,");
    	}

    	return retorno;
    }
}