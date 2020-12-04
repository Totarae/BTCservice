package com.gitexample.btcrest.btcwallet.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Entity
@Table(name = "records")
@Getter
@Setter
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Need to hide Id field for reusage
    @Getter(AccessLevel.NONE)
    private Long id;

    @NotNull
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateTime;

    @NotNull
    @DecimalMin("0.01")
    private Double amount;

    //Hiding id from response
    @JsonIgnore
    @JsonProperty(value = "id")
    public Long getId() {
        return id;
    }

}
