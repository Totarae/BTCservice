package com.gitexample.btcrest.btcwallet.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
public class DataFrame {
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
    @NotNull
    private ZonedDateTime startDatetime;

    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
    @NotNull
    private ZonedDateTime endDatetime;
}
