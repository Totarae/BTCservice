package com.gitexample.btcrest.btcwallet.controllers;

import com.gitexample.btcrest.btcwallet.exceptions.InvalidDateFrameException;
import com.gitexample.btcrest.btcwallet.models.DataFrame;
import com.gitexample.btcrest.btcwallet.models.Record;
import com.gitexample.btcrest.btcwallet.repositories.RecordHistoryRepository;
import com.gitexample.btcrest.btcwallet.services.RecordService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class MainController {

    Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private final RecordService recordRepo;
    @Autowired
    private final RecordHistoryRepository historyRepository;

    public MainController(RecordService recordRepo, RecordHistoryRepository historyRepository) {
        this.recordRepo = recordRepo;
        this.historyRepository = historyRepository;
    }

    @PostMapping(value="/record", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> createRecord(@Valid @RequestBody Record record, Errors errors) throws ValidationException {

        logger.info("createRecord launched");

        //Check errors
        if (errors.hasErrors()) {
            logger.error("Transaction is not accepted");
            // Extract ConstraintViolation list from body errors
            List<ConstraintViolation<?>> violationsList = new ArrayList<>();
            for (ObjectError e : errors.getAllErrors()) {
                violationsList.add(e.unwrap(ConstraintViolation.class));
            }

            String exceptionMessage = "";

            // Construct a helpful message for each input violation
            for (ConstraintViolation<?> violation : violationsList) {
                exceptionMessage += violation.getMessage() + System.lineSeparator();
            }
            logger.error("Validation fail cause"+exceptionMessage);
            throw new ValidationException(exceptionMessage);
        }

        logger.info("Validation passed");
        Record temp = recordRepo.save(record);

        Map<String, Object> rtn = new LinkedHashMap<>();
        rtn.put("transaction_id", temp.getId());
        return rtn;
    }


    @PostMapping("/records")
    public List<Record> getRecords(@RequestBody DataFrame dataframe,
                                   @PageableDefault(page = 0, size = 20)
                                   @SortDefault.SortDefaults({@SortDefault(sort = "date_time", direction = Sort.Direction.DESC)})
                                           Pageable pageable) throws InvalidDateFrameException {

        logger.info("Data frame inbound: "+dataframe.toString());

        //Frame is frame and not inverted
        boolean difference = dataframe.getStartDatetime().compareTo(dataframe.getEndDatetime()) < 0;

        // BTC was created
        boolean lowThreshold = dataframe.getStartDatetime().compareTo(ZonedDateTime.parse("2009-01-03T00:00:00.000+00:00"))>=0;
        boolean highThreshold = dataframe.getEndDatetime().compareTo(ZonedDateTime.parse("2009-01-03T00:00:00.000+00:00"))>=0;

        // Can't reach the future
        boolean futureCheck = dataframe.getEndDatetime().compareTo(ZonedDateTime.now())<=0;

        if (difference && lowThreshold && highThreshold && futureCheck){
            logger.info("Dates validation passed");
            Page<Record> temp = historyRepository.findByDataFrame(dataframe.getStartDatetime(),dataframe.getEndDatetime(), pageable);
            return temp.getContent();
        }
        else{
            throw new InvalidDateFrameException("Invalid Dates Format!");
        }
    }

}
