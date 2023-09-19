package kr.co.teaspoon.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {

    private int seq;
    private String author;
    private int dateYearMonth;
    private int dateDay;
    private int clickCnt;

}