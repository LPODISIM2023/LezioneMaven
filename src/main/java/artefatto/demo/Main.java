package artefatto.demo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.compress.utils.IOUtils;

public class Main {

	public static void main(String[] args) {
		try {
			compressFolderToZip("/Users/juri/Desktop/ESEMPIO", "/Users/juri/Desktop/ESEMPIO.zip");
			System.out.println("La certella è compressa!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void compressFolderToZip(String folderToCompress, String zipFile) throws IOException {
		File ftc = new File(folderToCompress);
		FileOutputStream fos = new FileOutputStream(zipFile);
		ZipOutputStream zos = new ZipOutputStream(fos);
		ZipEntry entry;
		for (File file : ftc.listFiles()) {
			entry = new ZipEntry(file.getName());
			zos.putNextEntry(entry);
			IOUtils.copy(new FileInputStream(file), zos);
			zos.closeEntry();
		}
		zos.finish();
		zos.close();
	}
}
