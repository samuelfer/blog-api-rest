package br.com.marhasoft.blogapirest.blogapirest.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDetails {

    private LocalDateTime timesTemp;
    private String message;
    private String details;
}
