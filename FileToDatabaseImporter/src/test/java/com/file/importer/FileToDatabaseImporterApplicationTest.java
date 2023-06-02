package com.file.importer;

import com.file.importer.dao.CsvRecordService;
import com.file.importer.dao.impl.CsvRecordServiceImpl;
import com.file.importer.entity.CsvRecord;
import com.file.importer.service.impl.FileImporterServiceImpl;
import com.file.importer.util.HibernateUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author Pouya Khoshkhou
 * @since 06/02/2023
 */
class FileToDatabaseImporterApplicationTest {

    private static FileToDatabaseImporterApplication fileToDatabaseImporterApplication;
    private static CsvRecordService csvRecordService;

    @BeforeAll
    static void setup() {
        fileToDatabaseImporterApplication = new FileToDatabaseImporterApplication(new FileImporterServiceImpl());
        csvRecordService = new CsvRecordServiceImpl();
    }

    @SneakyThrows
    @Test
    void importToDatabase() {
        fileToDatabaseImporterApplication.importToDatabase();
        List<CsvRecord> allRecords = csvRecordService.getAllRecords();
        //print all lines for fun :)
        csvRecordService.getAllRecords().forEach(System.out::println);
        Assertions.assertEquals(35, allRecords.size());
        HibernateUtil.shutdown();
    }
}