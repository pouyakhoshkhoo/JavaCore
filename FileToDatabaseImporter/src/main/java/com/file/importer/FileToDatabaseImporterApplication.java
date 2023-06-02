package com.file.importer;

import com.file.importer.service.FileImporterService;
import com.file.importer.service.impl.FileImporterServiceImpl;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * Processing huge files, This application demonstrates how to split a big file into multiple chunks and
 * then process all chunks in parallel.
 * <p>
 * Technologies: Java function, java stream, parallel stream
 *
 * @author Pouya Khoshkhou
 * @since 06/02/2023
 */
@AllArgsConstructor
public class FileToDatabaseImporterApplication {

    private FileImporterService fileImporterService;

    @SneakyThrows
    public static void main(String[] args) {
        FileToDatabaseImporterApplication fileToDatabaseImporterApplication = new FileToDatabaseImporterApplication(new FileImporterServiceImpl());
        fileToDatabaseImporterApplication.importToDatabase();
    }

    protected void importToDatabase() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getResourceAsStream("/application.properties"));
        File file = new File(getClass().getResource("/" + properties.getProperty("server.file.path")).getFile());
        String chunkSize = properties.getProperty("server.file.chunk-size-in-bytes");
        fileImporterService.importToDb(file.getAbsolutePath(), Integer.parseInt(chunkSize));
    }
}