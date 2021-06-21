package com.example.sw_engineering.customer;

public class cusSearchListViewItem {
    private String nameStr, locationStr, phoneStr,camp;

    public void setName(String name) {
        nameStr = name;
    }
    public void setLocation(String location) {
        locationStr = location;
    }
    public void setPhone(String phone) {
        phoneStr = phone;
    }
    public void setCamp(String campId) { camp= campId; }

    public String getName() {
        return this.nameStr;
    }
    public String getLocation() {
        return this.locationStr;
    }
    public String getPhone() {
        return this.phoneStr;
    }
    public String getCamp() {
        return this.camp;
    }

}
