package com.gitexample.btcrest.btcwallet.services;

import com.gitexample.btcrest.btcwallet.models.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecordService {
    Record save(Record record);
    Page<Record> findAll(Pageable pageable);
}
