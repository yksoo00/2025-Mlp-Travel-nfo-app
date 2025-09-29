package com.multi.travelapp.controller;

import com.multi.travelapp.model.dto.TouristSpotDto;
import com.multi.travelapp.service.MemberService;
import com.multi.travelapp.view.TravelView;

import java.util.ArrayList;

public class TouristSpotController {


    public void selectTouristSpotByTitle(Long memberId, String title) {
        TravelView travelView = new TravelView();
        ArrayList<TouristSpotDto> list = memberService.selectTourSpotsByTitle(title);

        if (!list.isEmpty()) {
            travelView.displayRegionTitle(list);
        } else {
            travelView.displayNoData();
        }



    }

    private MemberService memberService=new MemberService();




    public void selectTouristSpotByDistrict(Long memberId, String district) {
        TravelView travelView = new TravelView();
        ArrayList<TouristSpotDto> list = memberService.selectTourSpotsByRegion(district);

        if (!list.isEmpty()) {
            travelView.displayRegionPlace(list);
        } else {
            travelView.displayNoData();
        }
    }


}

