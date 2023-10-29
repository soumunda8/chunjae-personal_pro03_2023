package kr.co.teaspoon.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Qna {

    private int qno;
    private String title;
    private String content;
    private String author;
    private String resDate;
    private String answer;
    private boolean answerYn;
    private String answerDate;

}