package com.gitexample.btcrest.btcwallet.repositories;

import com.gitexample.btcrest.btcwallet.models.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;

@Repository
public interface RecordHistoryRepository extends JpaRepository<Record, Long> {
    // Made with pageable for future extensions
    @Query(value = "select row_number() over() as id, date_time, max as amount from ( " +
            "select distinct tmp.date_time, max(tmp.total) over (partition by date_time) from ( " +
            "select distinct DATE_TRUNC('hour', r.date_time) as date_time, " +
            "sum(r.amount) over (partition by DATE_TRUNC('hour', r.date_time)) + COALESCE((select sum(o.amount) from records o where o.date_time > ?1 and o.date_time < r.date_time),0) as total " +
            "from records r " +
            "where r.date_time between ?1 and ?2) tmp) s ", nativeQuery = true)
    Page<Record> findByDataFrame(ZonedDateTime startDate, ZonedDateTime endDate, Pageable pageable);
}
