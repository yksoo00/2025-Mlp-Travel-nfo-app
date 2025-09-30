package com.multi.travelapp.controller;

import com.multi.travelapp.model.dto.TouristSpotDto;
import com.multi.travelapp.service.TouristService;
import com.multi.travelapp.view.TravelView;
import java.util.ArrayList;

public class TouristSpotController {
    private TouristService touristService = new TouristService();

    public void insertTouristSpot(TouristSpotDto touristSpotDto) {
        TravelView travelView = new TravelView();

            int result  = touristService.insertTourist(touristSpotDto);
            if(result > 0){
                travelView.displaySuccess("관광지 등록");
            }else travelView.displayFail("관광지 등록");


    }

    public void selectTouristSpot() {
        TravelView travelView = new TravelView();

        ArrayList<TouristSpotDto> touristSpots= touristService.selectTourist();
        if(touristSpots.isEmpty()){
            travelView.displayFail("조회된 정보가 없습니다");
        }else travelView.displayTouristSpotList(touristSpots);
    }

    public void deleteTouristSpot(Long tourist_spot_id) {
        TravelView travelView = new TravelView();

        int result = touristService.deleteTourist(tourist_spot_id);
        if(result>0){
            travelView.displaySuccess("관광지 삭제");
        } else travelView.displayFail("조회된 정보가 없습니다");
    }

    public void UpdateTouristSpot(TouristSpotDto touristSpotDto, Long tourist_spot_id) {
        TravelView travelView = new TravelView();

        int result = touristService.UpdateTourist(tourist_spot_id,touristSpotDto);
        if(result>0){
            travelView.displaySuccess("관광지 수정");
        } else travelView.displayFail("조회된 정보가 없습니다");
    }
}
