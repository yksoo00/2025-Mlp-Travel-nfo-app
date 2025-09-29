package com.multi.travelapp.controller;

import com.multi.travelapp.service.BookMarkService;
import com.multi.travelapp.view.TravelView;

import java.util.List;

public class BookMarkController {
    private BookMarkService bookMarkService=new BookMarkService();


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

    public boolean isFavorited(Long memberId, Long touristSpotId) {
        return bookMarkService.checkIfFavorited(memberId, touristSpotId);
    }

    public void addFavorite(Long memberId, Long touristSpotId) {
        bookMarkService.addFavorite(memberId, touristSpotId);
    }

    public void removeFavorite(Long memberId, Long touristSpotId) {
        bookMarkService.removeFavorite(memberId, touristSpotId);
    }

    public int getFavoriteCount(Long touristSpotId) {
        return bookMarkService.getFavoriteCount(touristSpotId);
    }

    public List<Long> getMyFavorites(Long memberId) {
        return bookMarkService.getFavoritesByMember(memberId);
    }

}
