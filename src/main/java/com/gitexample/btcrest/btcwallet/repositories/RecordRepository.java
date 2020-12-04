package com.gitexample.btcrest.btcwallet.repositories;

import com.gitexample.btcrest.btcwallet.models.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
}
