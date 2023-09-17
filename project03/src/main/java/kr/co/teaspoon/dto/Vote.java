package kr.co.teaspoon.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vote {

    private int vno;
    private String title;
    private String content;
    private String regDate;
    private int visited;
    private String startDate;
    private String finishDate;
    private boolean useYn;
    private boolean stateYn;
    private int addPt;

}