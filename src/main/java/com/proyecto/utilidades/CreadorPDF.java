//objetivo: Extraer el dato de un alumno en pdf-

package com.proyecto.utilidades;
 
import com.proyecto.model.Alumno;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
 
import org.itextpdf.text.BaseColor;
import org.itextpdf.text.Chunk;
import com.itextpdf.Document;
import org.itextpdf.text.Element;
import org.itextpdf.text.Font;
import org.itextpdf.text.FontFactory;
import org.itextpdf.text.Image;
import org.itextpdf.text.List;
import org.itextpdf.text.ListItem;
import org.itextpdf.text.PageSize;
import org.itextpdf.text.Paragraph;
import org.itextpdf.text.Phrase;
import org.itextpdf.text.ZapfDingbatsList;
import org.itextpdf.text.pdf.PdfPCell;
import org.itextpdf.text.pdf.PdfPTable;
import org.itextpdf.text.pdf.PdfWriter;
 
public class CreadorPDF {
 
  public static void main(Alumno a) {
 
  // Creacion del documento con los margenes
  Document document = new Document(PageSize.A4, 35, 30, 50, 50);
  try {
 
   // El archivo pdf que vamos a generar
   FileOutputStream fileOutputStream = new FileOutputStream(
     "reportePDFDatoJava.pdf");
    
   // Obtener la instancia del PdfWriter
   PdfWriter.getInstance(document, fileOutputStream);
 
   // Abrir el documento
   document.open();
    
   Image image = null;
 
   // Obtenemos el logo de datojava
   image = Image.getInstance("/resources/img/footer.png");
   image.scaleAbsolute(80f, 60f);
 
   // Crear las fuentes para el contenido y los titulos
   Font fontContenido = FontFactory.getFont(
     FontFactory.TIMES_ROMAN.toString(), 11, Font.NORMAL,
     BaseColor.DARK_GRAY);
   Font fontTitulos = FontFactory.getFont(
     FontFactory.TIMES_BOLDITALIC, 11, Font.UNDERLINE,
     BaseColor.RED);
 
   // Creacion de una tabla
   PdfPTable table = new PdfPTable(1);
 
   // Agregar la imagen anterior a una celda de la tabla
   PdfPCell cell = new PdfPCell(image);
 
   // Propiedades de la celda
   cell.setColspan(5);
   cell.setBorderColor(BaseColor.WHITE);
   cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
 
   // Agregar la celda a la tabla
   table.addCell(cell);
 
   // Agregar la tabla al documento
	   document.add(table);
 
   // Creacion del parrafo
   Paragraph paragraph = new Paragraph();
 
   // Agregar un titulo con su respectiva fuente
   paragraph.add(new Phrase("Nombre:", fontTitulos));
 
   // Agregar saltos de linea
   paragraph.add(new Phrase(Chunk.NEWLINE));
   paragraph.add(new Phrase(Chunk.NEWLINE));
 
   // Agregar contenido con su respectiva fuente
   paragraph
     .add(new Phrase(
     	"\nNombre:" +a.getNombre()+
		"\nApellido:"+a.getApellido1()+
		"\nApellido:"+a.getApellido2()+
		"\nFecha de nacimiento"+a.getFnac()+
		"\nDirección"+a.getDireccion()+
		"\nLocalidad"+a.getLocalidad()+
		"\nProvincia"+a.getProvincia()+
		"\nCP"+ a.getCp()+
		"\nTlf:"+ a.getTlf()+
		"\nEmail:"+ a.getEmail1()+
		"\nEmail secundario"+ a.getEmail2()+
		"\nObservaciones"+ a.getObservaciones(),
		fontContenido));
 
   paragraph.add(new Phrase(Chunk.NEWLINE));
   paragraph.add(new Phrase(Chunk.NEWLINE));
   paragraph.add(new Phrase(Chunk.NEWLINE));
   paragraph.add(new Phrase("Otras Caracaterísticas:", fontTitulos));
 
   // Agregar el parrafo al documento
   document.add(paragraph);
 
   // La lista final
   List listaFinal = new List(List.UNORDERED);
   ListItem listItem = new ListItem();
   List list = new List();
 
   // Crear sangria en la lista
   list.setListSymbol(new Chunk("   "));
   ListItem itemNuevo = new ListItem("   ");
 
   // ZapfDingbatsListm, lista con simbolos
   List listSymbol = new ZapfDingbatsList(51);
 
   // Agregar contenido a la lista
   listSymbol
     .add(new ListItem(
       "Sensor CMOS X-Trans – Consigue una calidad de imagen superior",
       fontContenido));
   listSymbol
     .add(new ListItem(
       "Visor electrónico OLED de 2,36 pulgadas de alta resolución y luminosidad",
       fontContenido));
   listSymbol.add(new ListItem("Diseño y accesorios", fontContenido));
   listSymbol.add(new ListItem("Rápida respuesta", fontContenido));
 
   itemNuevo.add(listSymbol);
   list.add(itemNuevo);
   listItem.add(list);
 
   // Agregar todo a la lista final
   listaFinal.add(listItem);
 
   // Agregar la lista
   document.add(listaFinal);
 
   paragraph = new Paragraph();
   paragraph.add(new Phrase(Chunk.NEWLINE));
   paragraph.add(new Phrase(Chunk.NEWLINE));
   document.add(paragraph);
 
   // Crear tabla nueva con dos posiciones
   table = new PdfPTable(2);
   float[] longitudes = { 5.0f, 5.0f };
 
   // Establecer posiciones de celdas
   table.setWidths(longitudes);
   cell = new PdfPCell();
 
   // Cargar imagenes del producto y agregarlas
   image = Image.getInstance("fujifilmex1-2.png");
   image.scaleAbsolute(40f, 20f);
   table.getDefaultCell().setBorderColor(BaseColor.WHITE);
   table.addCell(image);
   image = Image.getInstance("fujifilmex1-3.png");
   image.scaleAbsolute(40f, 20f);
   table.addCell(image);
 
   // Agregar la tabla al documento
   document.add(table);
 
   // Cerrar el documento
   document.close();
 
   // Abrir el archivo
   File file = new File("reportePDFDatoJava.pdf");
   Desktop.getDesktop().open(file);
  } catch (Exception ex) {
   ex.printStackTrace();
  }
 }
}