package livraria.log;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.time.LocalTime;

public class RegistrarLog {
	private boolean monitor = false;

	public RegistrarLog() {
		String filePath = "RegistrosLog.txt";
		File file = new File(filePath);
		file.delete();
	}
	private void gravarDados(String lineToAppend) {
		try {
			String filePath = "RegistrosLog.txt";
			FileOutputStream f = new FileOutputStream(filePath, true);
			byte[] byteArr = lineToAppend.getBytes();
			f.write(byteArr);
			f.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void registrar(Boolean sucesso, String usuario, String operacao, String parametros) {
		StringBuilder sb = new StringBuilder();
		if(sucesso) {
			sb.append("SUCESSO: ");
		} else {
			sb.append("FRACASSO: ");
		}
		sb.append(LocalTime.now().toString());
		sb.append(" | ");
		sb.append("Usuario: ");
		sb.append(usuario);
		sb.append(" | ");
		sb.append("Operacao: ");
		sb.append(operacao);
		sb.append(" | ");
		sb.append("Parametros operacao: ");
		sb.append(parametros);
		sb.append("\n");
		this.gravarDados(sb.toString());
	}

	public synchronized void request() {
		while (monitor) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		monitor = true;
	}

	public synchronized void release() {
		monitor = false;
		notifyAll();
	}

}
