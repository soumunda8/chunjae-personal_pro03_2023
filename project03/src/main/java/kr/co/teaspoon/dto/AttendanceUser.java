package kr.co.teaspoon.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceUser {

    private int seq;
    private String author;
    private String nowDay;
    private boolean dateClick;
    private int par;

}