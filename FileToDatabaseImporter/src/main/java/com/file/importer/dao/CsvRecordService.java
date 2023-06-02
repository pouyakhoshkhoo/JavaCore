package com.file.importer.dao;

import com.file.importer.entity.CsvRecord;

import java.util.List;
import java.util.Set;

/**
 * @author Pouya Khoshkhou
 * @since 06/02/2023
 */
public interface CsvRecordService {

    void saveAll(List<CsvRecord> csvRecords, long chunkNumber);

    List<CsvRecord> getAllRecords();

    Set<Long> getProceededChunkNumbers();
}
