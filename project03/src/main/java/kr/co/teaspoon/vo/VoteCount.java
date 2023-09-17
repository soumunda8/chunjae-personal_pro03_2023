package kr.co.teaspoon.vo;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteCount {

    private int lno;
    private int vno;
    private String title;
    private int cnt;

}