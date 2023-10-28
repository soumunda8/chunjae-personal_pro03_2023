package kr.co.teaspoon.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    private int seq;
    private String boardNm;
    private String author;
    private String title;
    private String content;
    private String regDate;
    private int visited;

}