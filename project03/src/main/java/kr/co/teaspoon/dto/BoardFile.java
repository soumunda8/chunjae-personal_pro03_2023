package kr.co.teaspoon.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardFile {

    private int fno;
    private String boardNm;
    private int seq;
    private String saveFolder;
    private String originNm;
    private String saveNm;
    private String uploadDate;

}