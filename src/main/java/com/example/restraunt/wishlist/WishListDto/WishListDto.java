package com.example.restraunt.wishlist.WishListDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
//데이터베이스의 변수 명을 바꾸면 프론트엔드에서도 변수명을 변경해야되므로 데이터베이스를 상속받지않는다
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishListDto{
    private Integer index; //인덱스
    private String title;                //음식명, 장소명
    private String category;            // 카테고리
    private String address;             //주소
    private String loadAddress;         //도로명
    private String homePageLink;        // 홈페이지 주소
    private String imageLink;           //가게 이미지주소
    private boolean isVisit;            //방문여부
    private int visitCount;             //방문 카운트
    private LocalDateTime lastVisitDate;        //마지막 방문일자
}
