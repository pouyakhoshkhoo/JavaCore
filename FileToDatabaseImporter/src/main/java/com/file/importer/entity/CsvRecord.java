package com.file.importer.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

/**
 * @author Pouya Khoshkhou
 * @since 06/02/2023
 */
@ToString
@Data
@Entity
@Table(name = "CsvRecord")
public class CsvRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String a;
    private String b;
    private String c;
    private String d;
}
