package com.file.importer.function;

import com.file.importer.entity.CsvRecord;

import java.util.function.Function;

/**
 * @author Pouya Khoshkhou
 * @since 06/02/2023
 */
public class LineToCsvFunction implements Function<String, CsvRecord> {

    @Override
    public CsvRecord apply(String record) {
        System.out.println(record);
        String[] split = record.split(",");
        CsvRecord csvRecord = new CsvRecord();
        csvRecord.setA(split[0]);
        csvRecord.setB(split[1]);
        csvRecord.setC(split[2]);
        csvRecord.setD(split[3]);
        return csvRecord;
    }
}
