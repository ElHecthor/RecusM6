package m9;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class VeureInfo {

	public static void main(String[] args) {

//		File file = new File(args[0]);
		File file = new File("F:\\M9\\oculto.txt");
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		
		if (file.exists()) {
			if (file.isDirectory()) { // Si  es un directorio...
				// Imprime todos los archivos de 
				// ese directorio por pantalla
				String[] archivos = file.list();
				System.out.println("Esta el directori ocult?    : "+file.isHidden());
				System.out.println("Ultimo dia modificado?    : "+sdf.format(file.lastModified()));
				if (date.getTime() - file.lastModified() < 259200000) {
					System.out.println("Modificado hace 3 dias");
				}
				
				System.out.println("\nFicheros:");
				for (int i = 0; i<archivos.length; i++){
					 System.out.println("  "+archivos[i]);
				 }
				
			} else if (file.isFile()) { // Si es un archivo...
				// Muestra la informacion del fichero
				 System.out.println("Nom del fitxer : "+file.getName());
				 System.out.println("Esta ocult?    : "+file.isHidden());
				 System.out.println("Ultimo dia modificado?    : "+sdf.format(file.lastModified()));
					if (date.getTime() - file.lastModified() < 259200000) {
						System.out.println("Modificado hace 3 dias");
					}
				 System.out.println("Ruta           : "+file.getPath());
				 System.out.println("Ruta absoluta  : "+file.getAbsolutePath());
				 System.out.println("Es pot escriure: "+file.canRead());
				 System.out.println("Es pot llegir  : "+file.canWrite());
				 System.out.println("Grandaria      : "+file.length());
//				 System.out.println("Es un directori: "+file.isDirectory());
//				 System.out.println("Es un fitxer   : "+file.isFile());
				
			} else {
				System.out.println("No deberias estar aqui.");
			}
		}
	}
}
