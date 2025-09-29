package com.multi.travelapp.controller;

import com.multi.travelapp.model.dto.TouristSpotDto;
import com.multi.travelapp.service.MemberService;
import com.multi.travelapp.view.TravelView;

import java.util.ArrayList;

public class TouristSpotController {


    public void selectTouristSpotByTitle(Long memberId, String title) {
        TravelView travelView = new TravelView();


        try{
            ArrayList<TouristSpotDto> list = memberService.selectTourSpotsByTitle(title);
            if (!list.isEmpty()) {
                travelView.displayRegionTitle(list);
            } else {
                travelView.displayNoData();
            }

        }catch (RuntimeException e){
            e.printStackTrace();

        }



    }

    private MemberService memberService=new MemberService();



    public void selectTouristSpotByDistrict(Long memberId, String district) {
        TravelView travelView = new TravelView();

        try{
            ArrayList<TouristSpotDto> list = memberService.selectTourSpotsByRegion(district);

            if (!list.isEmpty()) {
                travelView.displayRegionPlace(list);
            } else {
                travelView.displayNoData();
            }

        }catch (RuntimeException e){
            e.printStackTrace();
        }

    }


    // page로 관광지 조회
    public void selectAllTouristSpotByPage(int page) {
        TravelView travelView = new TravelView();

        try {
            ArrayList<TouristSpotDto> list = memberService.selectAllTouristSpotByPage(page);

            if (!list.isEmpty()) {
                travelView.displayTouristSpotList(list);
            } else {
                travelView.displayNoData();
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }



}

