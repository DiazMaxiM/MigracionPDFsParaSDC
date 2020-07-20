package migracion;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Ejecucion {
	
	private String tipoDeArchivoABuscar;
	  private List<String> result = new ArrayList<String>();

	  public String getTipoDeArchivoABuscar() {
		return tipoDeArchivoABuscar;
	  }

	  public void setTipoDeArchivoABuscar(String fileNameToSearch) {
		this.tipoDeArchivoABuscar = fileNameToSearch;
	  }

	  public List<String> getResult() {
		return result;
	  }

	  public static void main(String[] args) {

		Ejecucion buscadorArchivo = new Ejecucion();

	        //try different directory and filename :)
		buscadorArchivo.searchDirectory(new File("/home/rosali/Escritorio/Resultados 2020"), "pdf");

		int count = buscadorArchivo.getResult().size();
		if(count ==0){
		    System.out.println("\nNo se encontraron resultados!");
		}else{
		    System.out.println("\nSe encontraron " + count + " resultados!\n");
		    for (String matched : buscadorArchivo.getResult()){
			System.out.println("Se encontró : " + matched);
		    }
		}
	  }

	  public void searchDirectory(File directory, String tipoDeArchivoABuscar) {

		setTipoDeArchivoABuscar(tipoDeArchivoABuscar);

		if (directory.isDirectory()) {
		    search(directory);
		} else {
		    System.out.println(directory.getAbsoluteFile() + " no es un directorio!");
		}

	  }

	  private void search(File file) {

		if (file.isDirectory()) {
		  System.out.println("Buscando directorio ... " + file.getAbsoluteFile());

	            //do you have permission to read this directory?
		    if (file.canRead()) {
			for (File temp : file.listFiles()) {
			    if (temp.isDirectory()) {
				search(temp);
			    } else {
				if (temp.getName().contains(getTipoDeArchivoABuscar())) {
				    result.add(temp.getAbsoluteFile().toString());
			    }

			}
		    }

		 } else {
			System.out.println(file.getAbsoluteFile() + "Permiso denegado");
		 }
	      }

	  }

}
