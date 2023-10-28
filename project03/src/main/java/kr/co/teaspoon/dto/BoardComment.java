package kr.co.teaspoon.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardComment {

    private int cno;
    private String boardNm;
    private int seq;
    private String author;
    private String content;
    private String resDate;

}