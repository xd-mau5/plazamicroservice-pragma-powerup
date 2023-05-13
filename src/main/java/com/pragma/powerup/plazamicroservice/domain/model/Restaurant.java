package com.pragma.powerup.plazamicroservice.domain.model;

public class Restaurant {
    private int id;
    private String name;
    private String address;
    private Long ownerId;
    private Long phoneNumber;
    private String urlLogo;
    private String nit;

    public Restaurant(int id, String name, String address, Long ownerId, Long phoneNumber, String urlLogo, String nit) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.ownerId = ownerId;
        this.phoneNumber = phoneNumber;
        this.urlLogo = urlLogo;
        this.nit = nit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }
}
