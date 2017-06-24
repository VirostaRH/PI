package com.proyecto.utilidades;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;

import com.proyecto.model.*;

public class ExportarCSV {

    public static boolean creaCSV(ArrayList <Alumno> a, ArrayList <Ciclo> cs, ArrayList <Apt> apts, ArrayList <OTitulacion> ots) throws Exception {

        boolean finalizado = false;

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet ddAlumno = workbook.createSheet();
        workbook.setSheetName(0, "Datos_personales_alumno");
        HSSFSheet ciclosAlumno = workbook.createSheet();
        workbook.setSheetName(1, "Ciclos_alumno");
        HSSFSheet aptitudesAlumno = workbook.createSheet();
        workbook.setSheetName(2, "Aptitudes_Alumno");
        HSSFSheet otrasTitulacionesAlumno = workbook.createSheet();
        workbook.setSheetName(3, "OT_alumno");

        String[] headersDd= new String[]{
            "id_alumno",
            "nombre",
            "apellido1",
            "apellido2",
            "fnac",
            "direccion",
            "localidad",
            "provincia",
            "cp",
            "tlf",
            "email1",
            "email2",
            "disponibilidad",
            "observaciones"
        };

        String[] headersCiclos= new String[]{
            "Siglas",
            "Nombre",
            "Fecha finalización"
        };

        String[] headersApt= new String[]{
            "Nombre",
            "Descripción",
            "Nivel"
        };

        String[] headersOT= new String[]{
            "Nombre",
            "Centro",
            "Descripcion",
            "Fecha finalización"
        };

        Object[][] data = new Object[][] {
            new Object[] { a.get(0).getId_alumno(), a.get(0).getNombre(), a.get(0).getApellido1(), a.get(0).getApellido2(), a.get(0).getFnac(), a.get(0).getDireccion(), a.get(0).getLocalidad(), a.get(0).getProvincia(), a.get(0).getCp(), a.get(0).getTlf(), a.get(0).getEmail1(), a.get(0).getEmail2(), a.get(0).isDisponibilidad(), a.get(0).getObservaciones() }
        };

        
        CellStyle headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerStyle.setFont(font);

        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);

        HSSFRow headerRow = ddAlumno.createRow(0);

        for (int i = 0; i < headersDd.length; ++i) {
            String header = headersDd[i];
            HSSFCell cell = headerRow.createCell(i);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(header);
        }

        for (int i = 0; i < data.length; ++i) {

            HSSFRow dataRow = ddAlumno.createRow(i + 1);

            Object[] d = data[i];
            String id_alumno = (String) d[0];
            String nombre = (String) d[1];
            String apellido1 = (String) d[2];
            String apellido2 = (String) d[3];
            String fnac = (String) d[4];
            String direccion = (String) d[5];
            String localidad = (String) d[6];
            String provincia = (String) d[7];
            int cp = (int) d[8];
            int tlf = (int) d[9];
            String mail1 = (String) d[10];
            String mail2 = (String) d[11];
            boolean dispo = (boolean)d[12];
            String obs = (String) d[13];

            dataRow.createCell(0).setCellValue(id_alumno);
            dataRow.createCell(1).setCellValue(nombre);
            dataRow.createCell(2).setCellValue(apellido1);
            dataRow.createCell(3).setCellValue(apellido2);
            dataRow.createCell(4).setCellValue(fnac);
            dataRow.createCell(5).setCellValue(direccion);
            dataRow.createCell(6).setCellValue(localidad);
            dataRow.createCell(7).setCellValue(provincia);
            dataRow.createCell(8).setCellValue(cp);
            dataRow.createCell(9).setCellValue(tlf);
            dataRow.createCell(10).setCellValue(mail1);
            dataRow.createCell(11).setCellValue(mail2);
            dataRow.createCell(12).setCellValue(dispo);
            dataRow.createCell(13).setCellValue(obs);

            finalizado = true;
        }

        HSSFRow headerRowCiclo = ciclosAlumno.createRow(0);

        for (int cont = 0; cont < headersCiclos.length; ++cont){
            String header = headersCiclos[cont];
            HSSFCell cell = headerRowCiclo.createCell(cont);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(header);
        }

        for (int j = 0; j < cs.size(); j++){
                HSSFRow dataRowCiclos = ciclosAlumno.createRow(j + 1);

                String siglas = cs.get(j).getSiglas();
                String nombre_ciclo = cs.get(j).getNombre_ciclo();
                String fecha_fin = cs.get(j).getFecha_fin();

                dataRowCiclos.createCell(0).setCellValue(siglas);
                dataRowCiclos.createCell(1).setCellValue(nombre_ciclo);
                dataRowCiclos.createCell(2).setCellValue(fecha_fin);
        }

        HSSFRow headerRowAptitudes = aptitudesAlumno.createRow(0);

        for (int cont = 0; cont < headersApt.length; ++cont) {
            String header = headersApt[cont];
            HSSFCell cell = headerRowAptitudes.createCell(cont);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(header);
        }

        for (int j = 0; j < apts.size(); j++){
                HSSFRow dataRowAptitudes = aptitudesAlumno.createRow(j + 1);

                String n = apts.get(j).getNombre_apt();
                String d = apts.get(j).getDescripcion();
                int c = apts.get(j).getNivel();

                dataRowAptitudes.createCell(0).setCellValue(n);
                dataRowAptitudes.createCell(1).setCellValue(d);
                dataRowAptitudes.createCell(2).setCellValue(c);
        }

        HSSFRow headerRowOT = otrasTitulacionesAlumno.createRow(0);

        for (int cont = 0; cont < headersOT.length; ++cont) {
            String header = headersOT[cont];
            HSSFCell cell = headerRowOT.createCell(cont);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(header);
        }

        for (int j = 0; j < apts.size(); j++){

                HSSFRow dataRowOT = otrasTitulacionesAlumno.createRow(j + 1);

                String n = ots.get(j).getNombre();
                String d = ots.get(j).getDescripcion();
                String c = ots.get(j).getCentro().getNombre_centro();
                int f = ots.get(j).getFecha_fin();

                dataRowOT.createCell(0).setCellValue(n);
                dataRowOT.createCell(1).setCellValue(d);
                dataRowOT.createCell(2).setCellValue(c);
                dataRowOT.createCell(3).setCellValue(f);
        }

        

        FileOutputStream file = new FileOutputStream("Datos de"+a.get(0).getNombre() + a.get(0).getApellido1() + a.get(0).getApellido2() + ".xls");
        workbook.write(file);
        file.close();
        return finalizado;
    }
}