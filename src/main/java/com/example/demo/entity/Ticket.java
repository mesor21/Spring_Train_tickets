package com.example.demo.entity;
public class Ticket {
    private Long id;
    private String place;
    private String name;
    private String departureStation;
    private String arrivalStation;
    private double price;
    private double priceOfGroundCard;
    private boolean used;

    public Ticket() {
    }

    public Ticket(Long id, String place, String name, String departureStation, String arrivalStation, double price, double priceOfGroundCard, boolean used) {
        this.id = id;
        this.place = place;
        this.name = name;
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.price = price;
        this.priceOfGroundCard = priceOfGroundCard;
        this.used = used;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPriceOfGroundCard() {
        return priceOfGroundCard;
    }

    public void setPriceOfGroundCard(double priceOfGroundCard) {
        this.priceOfGroundCard = priceOfGroundCard;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
