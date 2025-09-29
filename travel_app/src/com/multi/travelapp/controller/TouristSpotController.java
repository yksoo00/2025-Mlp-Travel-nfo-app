package com.multi.travelapp.controller;

import com.multi.travelapp.model.dto.TouristSpotDto;
import com.multi.travelapp.service.MemberService;
import com.multi.travelapp.service.TouristService;
import com.multi.travelapp.view.TravelView;

import java.util.ArrayList;

public class TouristSpotController {

    private TouristService touristService=new TouristService();

    public void selectTouristSpotByTitle(Long memberId, String title) {
        TravelView travelView = new TravelView();

        try{
            ArrayList<TouristSpotDto> list = touristService.selectTourSpotsByTitle(title);
            if (!list.isEmpty()) {
                travelView.displayRegionTitle(list);
            } else {
                travelView.displayNoData();
            }

        }catch (RuntimeException e){
            e.printStackTrace();

        }



    }


    public void selectTouristSpotByDistrict(Long memberId, String district) {
        TravelView travelView = new TravelView();

        try{
            ArrayList<TouristSpotDto> list = touristService.selectTourSpotsByRegion(district);

            if (!list.isEmpty()) {
                travelView.displayRegionPlace(list);
            } else {
                travelView.displayNoData();
            }

        }catch (RuntimeException e){
            e.printStackTrace();
        }

    }


    //장소 아이디로 상세정보조회
    public ArrayList<TouristSpotDto> selectTouristSpotById(Long memberId, Long touristSpotId) {
        TravelView travelView = new TravelView();
        ArrayList<TouristSpotDto> list=new ArrayList<>();

        try{
             list = touristService.selectTouristSpotById(memberId,touristSpotId);

            if (!list.isEmpty()) {
                travelView.detailPage(memberId,touristSpotId,list);
            } else {
                travelView.displayNoData();
            }

        }catch (RuntimeException e){
            e.printStackTrace();
        }

        return list;


    }

}

