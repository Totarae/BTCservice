package com.gitexample.btcrest.btcwallet.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecordTest {

    Record record;

    @BeforeEach
    void setUp() {
        record = new Record();
    }

    @Test
    void setDateTime() {
    }

    @Test
    void setAmount() {
    }

    @Test
    void getDateTime() {
    }

    @Test
    void getAmount() {
    }

    @Test
    void setId() {
    }

    @Test
    void testSetDateTime() {
    }

    @Test
    void testSetAmount() {
    }

    @Test
    void getId() {
        Long idValue = 4L;
        record.setId(4l);
        assertEquals(idValue, record.getId());
    }

    @Test
    void testGetDateTime() {
    }

    @Test
    void testGetAmount() {
    }

}