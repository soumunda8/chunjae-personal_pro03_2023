package kr.co.teaspoon.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardCommentVo {

    private int cno;
    private String boardNm;
    private int boardNo;
    private String author;
    private String name;
    private String content;
    private String resDate;

}