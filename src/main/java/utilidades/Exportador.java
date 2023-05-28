package utilidades;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
//import java.lang.reflect.Field;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import basico.Paciente;
import basico.Terapista;
import basico.Turno;
import exceptions.ExportadorException;
import exceptions.ServicioException;

public class Exportador {
	
	public static void exportarHistorial(List<Turno> listaTurnos) throws ExportadorException {
		
		String nombreArchivo= "historicoTurnos_" + listaTurnos.get(0).getAsignadoA() + ".xlsx";
		
		String[] nombresColumnas= {"Nro turno", "Fecha", "Paciente", "Terapista"};
		
		XSSFWorkbook libro= new XSSFWorkbook();
		XSSFSheet hoja= libro.createSheet("Historial turnos");
		
		XSSFRow fila= null;
		XSSFCell celda= null;
		
		//Estilos
		XSSFCellStyle estiloTitulo= libro.createCellStyle();
		XSSFCellStyle estiloDatos= libro.createCellStyle();
		
		XSSFFont fuente= libro.createFont();
		fuente.setBold(true);
		
		estiloTitulo.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		estiloTitulo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		estiloTitulo.setBorderBottom(BorderStyle.THIN);
		estiloTitulo.setBorderTop(BorderStyle.THIN);
		estiloTitulo.setBorderLeft(BorderStyle.THIN);
		estiloTitulo.setBorderRight(BorderStyle.THIN);
		estiloTitulo.setFont(fuente);
		estiloTitulo.setAlignment(HorizontalAlignment.CENTER);
		
		estiloDatos.setBorderBottom(BorderStyle.THIN);
		estiloDatos.setBorderTop(BorderStyle.THIN);
		estiloDatos.setBorderLeft(BorderStyle.THIN);
		estiloDatos.setBorderRight(BorderStyle.THIN);
		estiloDatos.setAlignment(HorizontalAlignment.CENTER);
		
		for(int filaPos= 0; filaPos < listaTurnos.size(); filaPos++) {
			if(filaPos == 0) {
				//Encabezado
				fila= hoja.createRow(0);
				
				for(int j= 0; j < nombresColumnas.length; j++) {
					celda= fila.createCell(j);
					celda.setCellValue(nombresColumnas[j]);
					celda.setCellStyle(estiloTitulo);
				}
			}
			
			Turno turno= listaTurnos.get(filaPos); 
			
			fila= hoja.createRow(filaPos + 1);
			
			for(int colPos= 0; colPos < 4; colPos++) {
				celda= fila.createCell(colPos);
				if(colPos == 0) {
					celda.setCellValue(turno.getNroTurno());
				} else if(colPos == 1) {
					celda.setCellValue(FechaUtil.getFechaMostrable(turno.getFechaDesde()));
				} else if(colPos == 2) {
					celda.setCellValue(turno.getAsignadoA().toString());
				} else if(colPos == 3) {
					celda.setCellValue(turno.getTerapista().toString());
				}
				
				celda.setCellStyle(estiloDatos);
				hoja.autoSizeColumn(colPos);
			}
				
		}
		
		try {
			File archivo= guardarComo();
			
			if(archivo != null) {
				OutputStream output= new FileOutputStream(archivo.toString() + "\\" + nombreArchivo);
				libro.write(output);
				
				libro.close();
				output.close();
			}
			
		} catch (IOException e) {
			throw new ExportadorException("No se pudo generar archivo");
        } 
	}
	
	private static File guardarComo(){
		File archivo= null;
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);
		
		FileNameExtensionFilter excelFilter = new FileNameExtensionFilter("Excel", "xlsx");
		fileChooser.setFileFilter(excelFilter);
		
		int seleccion= fileChooser.showSaveDialog(null);
		
		if(seleccion ==  JFileChooser.APPROVE_OPTION) {
			archivo = fileChooser.getSelectedFile();
		}
		
		return archivo;

	}

}
