package com.wicore.testcases;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.GZIPOutputStream;

public class GZipCommand {
	public static void main(String[] args) throws IOException {
		Path fileToCompress = Paths.get("C:\\Users\\brahmini\\Desktop\\sample.txt");
		Path outputFile = Paths.get("C:\\Users\\brahmini\\Desktop\\sample.txt.gzip");
		
	//  Path fileToCompress = Paths.get("/data/backupcsv/request_idea_dup_tx_20200203_20220112145347.csv");
	//	Path outputFile = Paths.get("/data/backupcsv/request_idea_dup_tx_20200203_20220112145347.csv.gzip");
		compressGZip(fileToCompress, outputFile);
	}
	public static void compressGZip(Path fileToCompress, Path outputFile) throws IOException {
	    try (GZIPOutputStream gzipOutputStream = 
	         new GZIPOutputStream(Files.newOutputStream(outputFile))) {
	 
	        byte[] allBytes = Files.readAllBytes(fileToCompress);
	        gzipOutputStream.write(allBytes);
	    }
	}

}
