package com.multi.travelapp.model.dto;

public class LikesDto {
    private int likes_id;
    private int member_id;
    private int tourist_spot_id;

    public int getLikes_id() {
        return likes_id;
    }

    public void setLikes_id(int likes_id) {
        this.likes_id = likes_id;
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
        return "LikesDto{" +
                "likes_id=" + likes_id +
                ", member_id=" + member_id +
                ", tourist_spot_id=" + tourist_spot_id +
                '}';
    }
}
