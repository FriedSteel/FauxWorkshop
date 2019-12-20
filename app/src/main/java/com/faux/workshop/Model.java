package com.faux.workshop;

public class Model {

    private int id;
    private String name;
    private String company;
    private String duration;
    private String location;
    private int fee;
    private String applied;

    public Model() {}

    public Model(String name, String company, String duration, String location, int fee, String applied) {
        this.name = name;
        this.company = company;
        this.duration = duration;
        this.location = location;
        this.fee = fee;
        this.applied = applied;
    }
    public Model(int id, String name) {
        this.id = id;
        this.name = name;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public String getApplied() {
        return applied;
    }

    public void setApplied(String applied) {
        this.applied = applied;
    }
}
