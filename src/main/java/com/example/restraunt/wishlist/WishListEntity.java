package com.example.restraunt.wishlist;

import com.example.restraunt.db.MemoryDbEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishListEntity extends MemoryDbEntity {

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
