package com.file.importer.service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * @author Pouya Khoshkhou
 * @since 06/02/2023
 */
public interface FileReader {

    List<String> readChunkFromFile(Path path, long chunkNumber, int chunkSize) throws IOException;

}
