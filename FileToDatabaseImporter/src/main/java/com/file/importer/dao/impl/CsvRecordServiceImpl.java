package com.file.importer.dao.impl;

import com.file.importer.dao.CsvRecordService;
import com.file.importer.entity.CsvRecord;
import com.file.importer.entity.ProceededChunk;
import com.file.importer.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Pouya Khoshkhou
 * @since 06/02/2023
 */
public class CsvRecordServiceImpl implements CsvRecordService {

    @Override
    public void saveAll(List<CsvRecord> csvRecords, long chunkNumber) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            csvRecords.forEach(session::persist);
            session.persist(new ProceededChunk(chunkNumber));
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<CsvRecord> getAllRecords() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            return session.createQuery("from CsvRecord", CsvRecord.class).list();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<Long> getProceededChunkNumbers() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<ProceededChunk> proceededChunks = session.createQuery("from ProceededChunk", ProceededChunk.class).list();
            return proceededChunks.stream().map(ProceededChunk::getChunkNumber).collect(Collectors.toSet());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }
}
