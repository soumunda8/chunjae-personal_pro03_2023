package kr.co.teaspoon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardVo {

    private int seq;
    private String boardNm;
    private String author;
    private String name;
    private String title;
    private String content;
    private String regDate;
    private int visited;

}