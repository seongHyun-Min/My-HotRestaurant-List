package com.example.restraunt.naver.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchImageRes { //출력결과 response

    private String lastBuildDate; //검색 결과를 생성한 시간이다.
    private int total;
    private int start;
    private int display;
    private List<SearchImageItem> items;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchImageItem{ // 내부클래스 정의
    //리스트의 결과기 떄문에 내부클래스 생성해준다
        private String title;
        private String link;
        private String thumbnail;
        private String sizeheight;
        private String sizewidth;
    }
}
