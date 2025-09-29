package com.multi.travelapp.model.dto;

public class TouristSpotDto {
    private Long tourist_spot_id;
    private String tourist_spot_name;
    private String district;
    private String title;
    private String description;
    private String address;
    private String phone;
    private int likeCount;

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }


    public Long getTouristSpotId() {
        return tourist_spot_id;
    }

    public void setTouristSpotId(Long tourist_spot_id) {
        this.tourist_spot_id = tourist_spot_id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "TouristSpotDto{" +
                "tourist_spot_it=" + tourist_spot_id +
                ", district='" + district + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
