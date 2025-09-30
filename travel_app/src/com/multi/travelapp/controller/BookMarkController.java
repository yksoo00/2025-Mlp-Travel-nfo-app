package com.multi.travelapp.controller;

import com.multi.travelapp.model.dto.TouristSpotDto;
import com.multi.travelapp.service.BookMarkService;
import com.multi.travelapp.view.TravelView;

import java.util.List;

public class BookMarkController {
    private final BookMarkService bookMarkService = new BookMarkService();
    private TravelView travelView;


    public void setTravelView(TravelView travelView) {
        this.travelView = travelView;
    }



    // 즐겨찾기 등록/삭제 토글
    public void updateBookMark(Long memberId, Long touristSpotId) {
        boolean exists = bookMarkService.checkIfFavorited(memberId, touristSpotId);

        if (exists) {
            bookMarkService.removeFavorite(memberId, touristSpotId);
            System.out.println("🗑️ 즐겨찾기 삭제 완료 (관광지 ID: " + touristSpotId + ")");
        } else {
            bookMarkService.addFavorite(memberId, touristSpotId);
            System.out.println("⭐ 즐겨찾기 등록 완료 (관광지 ID: " + touristSpotId + ")");
        }
    }

    // 즐겨찾기 여부 확인
    public boolean isFavorited(Long memberId, Long touristSpotId) {
        return bookMarkService.checkIfFavorited(memberId, touristSpotId);
    }

    // 즐겨찾기 추가
    public void addFavorite(Long memberId, Long touristSpotId) {
        bookMarkService.addFavorite(memberId, touristSpotId);
    }

    // 즐겨찾기 삭제
    public void removeFavorite(Long memberId, Long touristSpotId) {
        bookMarkService.removeFavorite(memberId, touristSpotId);
    }

    // 특정 관광지의 즐겨찾기 개수
    public int getFavoriteCount(Long touristSpotId) {
        return bookMarkService.getBookMarkedCount(touristSpotId);
    }



    // 내가 즐겨찾기한 관광지 목록 (상세 정보까지)
    public void myBookMarkPage(Long memberId) {
        List<TouristSpotDto> favorites = bookMarkService.getMyBookMarkById(memberId);
        travelView.displayMyBookMarks(favorites);
    }
}
