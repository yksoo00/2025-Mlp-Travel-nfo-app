package com.multi.travelapp.controller;

import com.multi.travelapp.service.LikesService;
import com.multi.travelapp.view.TravelView;

public class LikesController {
    private LikesService likesService = new LikesService();
    private TravelView travelView;

    public LikesController() {

    }

    public LikesController(TravelView travelView) {
        this.travelView = travelView;
    }

    public void updateLikes(Long memberId, Long touristSpotId) {
        boolean isLiked = likesService.checkIfLiked(memberId, touristSpotId);

        if(isLiked) {
            likesService.removeLike(memberId,touristSpotId);
            travelView.displayMessage("좋아요가 취소되었습니다.");
        }else{
            likesService.addLike(memberId,touristSpotId);
            travelView.displayMessage("좋아요가 등록되었습니다.");

        }

        // 좋아요 수 조회
        int totalLikes = likesService.getLikeCount(touristSpotId);

        // 뷰에 보여주기
        travelView.displayLikesCount(touristSpotId, totalLikes);






    }
}
