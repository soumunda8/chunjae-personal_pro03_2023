package kr.co.teaspoon.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteUser {

    private int uno;
    private int vno;
    private int lno;
    private String author;

}