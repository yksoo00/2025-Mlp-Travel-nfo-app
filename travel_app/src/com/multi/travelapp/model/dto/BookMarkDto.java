package com.multi.travelapp.model.dto;

public class BookMarkDto {
    private int bookmarkId;
    private int memberId;
    private int touristSpotId;

    public BookMarkDto() {
    }

    public int getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(int bookmarkId) {
        this.bookmarkId = bookmarkId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getTouristSpotId() {
        return touristSpotId;
    }

    public void setTouristSpotId(int touristSpotId) {
        this.touristSpotId = touristSpotId;
    }

    @Override
    public String toString() {
        return "BookMarkDto{" +
                "bookmarkId=" + bookmarkId +
                ", memberId=" + memberId +
                ", touristSpotId=" + touristSpotId +
                '}';
    }
}
