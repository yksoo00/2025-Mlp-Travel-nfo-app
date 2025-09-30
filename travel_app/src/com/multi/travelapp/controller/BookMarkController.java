package com.multi.travelapp.controller;

import com.multi.travelapp.model.dto.BookMarkDto;
import com.multi.travelapp.model.dto.ReviewDto;
import com.multi.travelapp.model.dto.TouristSpotDto;
import com.multi.travelapp.service.BookMarkService;

import com.multi.travelapp.view.TravelView;

import java.util.ArrayList;

public class BookMarkController {
    private BookMarkService bookMarkService = new BookMarkService();

    public BookMarkController() {
    }

    public void selectAllMyBookMarkPage(Long memberId) {
        TravelView travelView = new TravelView();

        try {
            ArrayList<TouristSpotDto> list = bookMarkService.selectAllBookMarkByMemberId(memberId); // 내 즐겨찾기 전체 가져오기

            if (!list.isEmpty()) {
                travelView.displayBookMarkList(list);
            } else {
                travelView.displayNoData();
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

    }
}
