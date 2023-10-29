package kr.co.teaspoon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QnaVo {
    
    private int qno;
    private String title;
    private String content;
    private String author;
    private String name;
    private String resDate;
    private String answer;
    private boolean answerYn;
    private String answerDate;

}