package kr.co.teaspoon.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {

    private int seq;
    private String author;
    private String dateYearMonth;
    private String nowDay;

}