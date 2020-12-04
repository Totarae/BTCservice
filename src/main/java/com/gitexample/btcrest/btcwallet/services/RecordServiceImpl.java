package com.gitexample.btcrest.btcwallet.services;

import com.gitexample.btcrest.btcwallet.models.Record;
import com.gitexample.btcrest.btcwallet.repositories.RecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@Slf4j
public class RecordServiceImpl implements RecordService {

    private final RecordRepository recordRepo;

    @Autowired
    private EntityManager entityManager;

    public RecordServiceImpl(RecordRepository recordRepo) {
        this.recordRepo = recordRepo;
    }

    @Override
    @Transactional
    public Record save(Record record) {
        recordRepo.save(record);
        return record;
    }

    @Override
    public Page<Record> findAll(Pageable pageable) {
        return  recordRepo.findAll(pageable);
    }
}
