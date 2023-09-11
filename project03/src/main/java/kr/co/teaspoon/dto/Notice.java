package kr.co.teaspoon.dto;

import lombok.*;

@Setter
@Getter
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Notice {

    private int seq;
    private String title;
    private String content;
    private String regDate;
    private int visited;

}