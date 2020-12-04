package com.gitexample.btcrest.btcwallet.repositories;

import com.gitexample.btcrest.btcwallet.services.RecordServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class RecordHistoryRepositoryTest {

    RecordServiceImpl recordService;

    @Mock
    RecordRepository recordRepository;



    @Test
    void findByDataFrame() {
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recordService = new RecordServiceImpl(recordRepository);
    }
}