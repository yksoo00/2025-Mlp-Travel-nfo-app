package com.multi.travelapp.model.dto;

public class BookMarkDto {
    private long bookmarkId;
    private long memberId;
    private long touristSpotId;

    public BookMarkDto() {
    }

    public long getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(long bookmarkId) {
        this.bookmarkId = bookmarkId;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public long getTouristSpotId() {
        return touristSpotId;
    }

    public void setTouristSpotId(long touristSpotId) {
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
