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
    	String carroSql = null;

        if (aptitudes.length != 0)
        {
        	for(int i = 0; i <= aptitudes.length; i++)
        	{
                System.out.println("entra " + carroSql);
        		carroSql= carroSql+"OR APTITUD.nombre_aptitud like '"+aptitudes[i]+"'";
        	}
        }
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