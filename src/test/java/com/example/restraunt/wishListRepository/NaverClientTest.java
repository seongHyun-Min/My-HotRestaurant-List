package com.example.restraunt.wishListRepository;


import com.example.restraunt.naver.dto.SearchImageReq;
import com.example.restraunt.naver.dto.SearchLocalReq;
import com.example.restraunt.naver.NaverClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NaverClientTest {

    @Autowired
    private NaverClient naverClient;

    @Test
    public void SearchLocalTest(){

        var search = new SearchLocalReq();
        search.setQuery("중국집");

        var result = naverClient.searchLocal(search);
        System.out.println(result);

    }

    @Test
    public void SearchImageTest(){ //에러코드 400bad sort 라는 값이 문제가 된다.
        var search = new SearchImageReq();
        search.setQuery("중국집");

        var result = naverClient.searchImage(search);
        System.out.println(result);
        System.out.println(search.getQuery());

    }
}
