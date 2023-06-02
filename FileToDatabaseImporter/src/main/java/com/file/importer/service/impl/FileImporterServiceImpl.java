package com.file.importer.service.impl;

import com.file.importer.dao.CsvRecordService;
import com.file.importer.dao.impl.CsvRecordServiceImpl;
import com.file.importer.entity.CsvRecord;
import com.file.importer.function.LineToCsvFunction;
import com.file.importer.service.FileImporterService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @author Pouya Khoshkhou
 * @since 06/02/2023
 */
public class FileImporterServiceImpl implements FileImporterService {

    private final FileReaderImpl fileReader;
    private final LineToCsvFunction lineToCsvFunction;
    private final CsvRecordService csvRecordService;

    public FileImporterServiceImpl() {
        this.fileReader = new FileReaderImpl();
        this.lineToCsvFunction = new LineToCsvFunction();
        this.csvRecordService = new CsvRecordServiceImpl();
    }

    @Override
    public void importToDb(String path, int chunkSize) throws IOException {
        Path of = Path.of(path);
        long size = Files.size(of);
        long totalChunkNumber = size / chunkSize;
        Set<Long> proceededChunkNumbers = csvRecordService.getProceededChunkNumbers();
        LongStream.range(0, totalChunkNumber + 1).parallel()
                .filter(chunkNumber -> !proceededChunkNumbers.contains(chunkNumber))
                .forEach(chunkNumber -> {
                    try {
                        List<CsvRecord> csvRecords = fileReader
                                .readChunkFromFile(of, chunkNumber, chunkSize)
                                .stream()
                                .map(lineToCsvFunction)
                                .collect(Collectors.toList());
                        csvRecordService.saveAll(csvRecords, chunkNumber);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }
}
