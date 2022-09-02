package com.example.restraunt.wishlist.service;


import com.example.restraunt.naver.NaverClient;
import com.example.restraunt.naver.dto.SearchImageReq;
import com.example.restraunt.naver.dto.SearchLocalReq;
import com.example.restraunt.wishlist.WishListDto.WishListDto;
import com.example.restraunt.wishlist.WishListEntity;
import com.example.restraunt.wishlist.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final NaverClient naverClient;

    private final WishListRepository wishListRepository;


    public WishListDto search(String query){
        //지역 검색
        var searchLocalReq = new SearchLocalReq();
        searchLocalReq.setQuery(query);
    // query값을 인코딩하기 위하여
        var searchLocalRes = naverClient.searchLocal(searchLocalReq); // 반드시 검색 결과가 있다는걸 가정한다

        if(searchLocalRes.getTotal() >0){            // 변수 토탈을 가저온다는 뜻
            var localItem = searchLocalRes.getItems().stream().findFirst().get();

            var imageQuery = localItem.getTitle().replaceAll("<[^>]*>", ""); //문자열 처리 정규식 사용
            //지역 검색에서 제목을 가저와 정규식 처리 한후 이미지 검색으로 넘어간다
            // 이미지 검색
            var searchImageReq = new SearchImageReq(); // 새로운 리퀘스트 설
            searchImageReq.setQuery(imageQuery); //검색결과를 가저온식을 정규식으로 문자열처리해서 다시 변수에 저장시켜준다
            var SearchImageRes = naverClient.searchImage(searchImageReq);

            // 결과를 리턴
            if(SearchImageRes.getTotal() >0){
                var imageItem = SearchImageRes.getItems().stream().findFirst().get();

                //결과를 리턴
                var result = new WishListDto();
                result.setIndex(1);
                result.setTitle(localItem.getTitle());
                result.setCategory(localItem.getCategory());
                result.setAddress(localItem.getAddress());
                result.setLoadAddress(localItem.getRoadAddress());
                result.setHomePageLink(localItem.getLink());
                result.setImageLink(imageItem.getLink());

                return result;
            }
        }
        return new WishListDto();


        // 결과를 리턴
    }

    public WishListDto add(WishListDto wishListDto) {
        var entity = dtoToEntity(wishListDto);
        var saveEntity = wishListRepository.save(entity);
        return entityToDto(saveEntity);
    }

    private WishListEntity dtoToEntity(WishListDto wishListDto){
        var entity = new WishListEntity();
        entity.setIndex(wishListDto.getIndex());
        entity.setTitle(wishListDto.getTitle());
        entity.setTitle(wishListDto.getTitle());
        entity.setCategory(wishListDto.getCategory());
        entity.setAddress(wishListDto.getAddress());
        entity.setLoadAddress(wishListDto.getLoadAddress());
        entity.setHomePageLink(wishListDto.getHomePageLink());
        entity.setImageLink(wishListDto.getImageLink());
        entity.setVisit(wishListDto.isVisit()); //boolean타입은 게더가 없다
        entity.setVisitCount(wishListDto.getVisitCount());
        entity.setLastVisitDate(wishListDto.getLastVisitDate());
        return entity;

    }

    private WishListDto entityToDto(WishListEntity wishListEntity){
        var dto = new WishListDto();
        dto.setIndex(wishListEntity.getIndex());
        dto.setTitle(wishListEntity.getTitle());
        dto.setTitle(wishListEntity.getTitle());
        dto.setCategory(wishListEntity.getCategory());
        dto.setAddress(wishListEntity.getAddress());
        dto.setLoadAddress(wishListEntity.getLoadAddress());
        dto.setHomePageLink(wishListEntity.getHomePageLink());
        dto.setImageLink(wishListEntity.getImageLink());
        dto.setVisit(wishListEntity.isVisit()); //boolean타입은 게더가 없다
        dto.setVisitCount(wishListEntity.getVisitCount());
        dto.setLastVisitDate(wishListEntity.getLastVisitDate());

        return dto;


    }

    public List<WishListDto> findAll() {


        return wishListRepository.listAll().stream().map(it -> entityToDto(it)).collect(Collectors.toList());
    }
}
