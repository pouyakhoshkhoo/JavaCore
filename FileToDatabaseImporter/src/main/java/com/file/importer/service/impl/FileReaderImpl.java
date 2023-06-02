package com.file.importer.service.impl;

import com.file.importer.service.FileReader;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pouya Khoshkhou
 * @since 06/02/2023
 */
public class FileReaderImpl implements FileReader {

    @Override
    public List<String> readChunkFromFile(Path path, long chunkNumber, int chunkSize) throws IOException {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(new File(path.toUri()), "r")) {
            long startPosition = chunkNumber * chunkSize;
            long endPosition = startPosition + chunkSize;
            randomAccessFile.seek(startPosition);
            randomAccessFile.readLine();
            ArrayList<String> chunkLines = new ArrayList<>(chunkSize);
            String e;
            do {
                e = randomAccessFile.readLine();
                if (e != null) {
                    chunkLines.add(e);
                }
            } while (randomAccessFile.getChannel().position() <= endPosition && e != null);
            return chunkLines;
        }
    }
}
