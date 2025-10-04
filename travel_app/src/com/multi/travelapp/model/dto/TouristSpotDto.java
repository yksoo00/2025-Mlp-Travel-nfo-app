package com.multi.travelapp.model.dto;

public class TouristSpotDto {
  
    private Long tourist_spot_id;
    private String district;
    private String title;
    private String description;
    private String address;
    private String phone;
    private int like_count;

    public TouristSpotDto() {

    }

    public int getLikeCount() {
        return like_count;
    }

    public void setLikeCount(int likeCount) {
        this.like_count = likeCount;
    }


    public Long getTouristSpotId() {
        return tourist_spot_id;
    }

    public void setTouristSpotId(Long tourist_spot_id) {
        this.tourist_spot_id = tourist_spot_id;
    }

    public TouristSpotDto(Long tourist_spot_id, String district, String title, String description, String address,
                          String phone) {
        this.tourist_spot_id = tourist_spot_id;
        this.district = district;
        this.title = title;
        this.description = description;
        this.address = address;
        this.phone = phone;
        this.like_count = 0;
    }

    public void setTourist_spot_id(Long tourist_spot_id) {
        this.tourist_spot_id = tourist_spot_id;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getTourist_spot_id() {
        return tourist_spot_id;
    }

    public String getDistrict() {
        return district;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public TouristSpotDto(String district, String title, String description, String address,
                          String phone) {
        this.district = district;
        this.title = title;
        this.description = description;
        this.address = address;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "TouristSpotDto{" +
                "tourist_spot_id=" + tourist_spot_id +
                ", district='" + district + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

