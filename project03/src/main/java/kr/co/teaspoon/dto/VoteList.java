package kr.co.teaspoon.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteList {

    private int lno;
    private int vno;
    private String title;
    private String colorNum;

}