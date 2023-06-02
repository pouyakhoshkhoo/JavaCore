package com.file.importer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Pouya Khoshkhou
 * @since 06/02/2023
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ProceededChunk")
public class ProceededChunk {

    @Id
    private long chunkNumber;
}
