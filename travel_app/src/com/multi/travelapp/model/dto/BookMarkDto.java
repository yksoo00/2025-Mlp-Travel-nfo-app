package com.multi.travelapp.model.dto;

public class BookMarkDto {
    private int bookmark_id;
    private int member_id;
    private int tourist_spot_id;

    public int getBookmark_id() {
        return bookmark_id;
    }

    public void setBookmark_id(int bookmark_id) {
        this.bookmark_id = bookmark_id;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public int getTourist_spot_id() {
        return tourist_spot_id;
    }

    public void setTourist_spot_id(int tourist_spot_id) {
        this.tourist_spot_id = tourist_spot_id;
    }

    @Override
    public String toString() {
        return "BookMarkDto{" +
                "bookmark_id=" + bookmark_id +
                ", member_id=" + member_id +
                ", tourist_spot_id=" + tourist_spot_id +
                '}';
    }
}
