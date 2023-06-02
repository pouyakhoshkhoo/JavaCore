package com.file.importer.service;

import java.io.IOException;

/**
 * @author Pouya Khoshkhou
 * @since 06/02/2023
 */
public interface FileImporterService {

    void importToDb(String path, int chunkSize) throws IOException;
}
