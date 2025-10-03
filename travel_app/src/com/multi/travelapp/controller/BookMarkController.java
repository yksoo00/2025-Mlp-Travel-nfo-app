package com.multi.travelapp.controller;

import com.multi.travelapp.model.dto.BookMarkDto;
import com.multi.travelapp.model.dto.ReviewDto;
import com.multi.travelapp.model.dto.TouristSpotDto;
import com.multi.travelapp.service.BookMarkService;
import com.multi.travelapp.view.TravelView;
import java.util.ArrayList;



public class BookMarkController {
    private final BookMarkService bookMarkService = new BookMarkService();


    // 즐겨찾기 등록/삭제
    public void updateBookMark(Long memberId, Long touristSpotId) {
        boolean exists = bookMarkService.checkIfBookMarked(memberId, touristSpotId);

        if (exists) {
            bookMarkService.removeBookMark(memberId, touristSpotId);
            System.out.println("🗑️ 즐겨찾기 삭제 완료 (관광지 ID: " + touristSpotId + ")");
        } else {
            bookMarkService.addBookMark(memberId, touristSpotId);
            System.out.println("⭐ 즐겨찾기 등록 완료 (관광지 ID: " + touristSpotId + ")");
        }
    }


 public ArrayList<TouristSpotDto> selectAllMyBookMarkPage(Long memberId) {
        TravelView travelView = new TravelView();
        ArrayList<TouristSpotDto> list = null;
        try {
            list = bookMarkService.selectAllBookMarkByMemberId(memberId); // 내 즐겨찾기 전체 가져오기

            if (!list.isEmpty()) {
                travelView.displayBookMarkList(list);

            } else {
                travelView.displayNoData();
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

     return list;
    }
}
