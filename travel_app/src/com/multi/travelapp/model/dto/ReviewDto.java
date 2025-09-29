package com.multi.travelapp.model.dto;

public class ReviewDto {

    private Long reviewId;
    private String title;
    private String description;
    private int score;
    private Long memberId;
    private Long touristSpotId;

    public ReviewDto() {
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", score=" + score +
                ", memberId=" + memberId +
                ", touristSpotId=" + touristSpotId +
                '}';
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getTouristSpotId() {
        return touristSpotId;
    }

    public void setTouristSpotId(Long touristSpotId) {
        this.touristSpotId = touristSpotId;
    }




}
