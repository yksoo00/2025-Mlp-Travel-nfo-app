package com.multi.travelapp.controller;

import com.multi.travelapp.model.dto.ReviewDto;
import com.multi.travelapp.service.ReviewService;
import com.multi.travelapp.view.TravelView;

import java.util.ArrayList;

public class ReviewController {
    private ReviewService reviewService = new ReviewService();

    public ReviewController() {
    }

    // ------------------------------- 리뷰 서비스 ------------------------------
    // memberId로 리뷰 조회
    public void selectAllReviewByMemberId(Long memberId) {
        TravelView travelView = new TravelView();

        try {
            ArrayList<ReviewDto> list = reviewService.selectAllReviewByMemberId(memberId); // 내 리뷰 가져오기

            if (!list.isEmpty()) {
                travelView.displayReviewList(list);
            } else {
                travelView.displayNoData();
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    // touristSpotId로 리뷰 조회
    public void selectAllReviewByTouristSpotId(Long memberId, Long touristSpotId) {
        TravelView travelView = new TravelView();

        try {

            ArrayList<ReviewDto> list = reviewService.selectAllReviewByTouristSpotId(touristSpotId); // 내 리뷰 가져오기

            if (!list.isEmpty()) {
                travelView.displayReviewList(list);
            } else {
                travelView.displayNoData();
            }

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    // memberId, reviewDto로 리뷰 등록
    public void insertReview(ReviewDto reviewDto) {
        try {
            int result = reviewService.insertReview(reviewDto);
            if (result > 0) {
                System.out.println("리뷰 등록 성공!");
            } else {
                System.out.println("리뷰 등록 실패ㅠㅠ");
            }
        } catch (RuntimeException e) {
            System.out.println("리뷰 등록 실패ㅠㅠ");
            e.printStackTrace();
        }
    }

    // memberId, reviewId로 리뷰 삭제
    public void deleteReview(Long memberId, Long reviewId) {
        try {

            int result = reviewService.deleteReview(memberId, reviewId);
            if (result > 0) {
                System.out.println("리뷰 삭제 성공!");
            } else {
                System.out.println("리뷰 삭제 실패ㅠㅠ");
            }
        } catch (RuntimeException e) {
            System.out.println("리뷰 삭제 실패ㅠㅠ");
            e.printStackTrace();
        }
    }

    // memberId로 리뷰 수정
    public void updateReview(Long memberId, Long reviewId, String title, String description, int score) {
        try {

            int result = reviewService.updateReview(reviewId, title, description, score);
            if (result > 0) {
                System.out.println("리뷰 수정 성공!");
            } else {
                System.out.println("리뷰 수정 실패ㅠㅠ");
            }

        } catch (RuntimeException e) {
            System.out.println("리뷰 수정 실패ㅠㅠ");
            e.printStackTrace();
        }
    }

}
