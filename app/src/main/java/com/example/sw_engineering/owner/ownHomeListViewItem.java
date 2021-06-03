package com.example.sw_engineering.owner;

public class ownHomeListViewItem {
    private String nameStr, locationStr, phoneStr;

    public void setName(String name) {
        nameStr = name;
    }
    public void setLocation(String location) {
        locationStr = location;
    }
    public void setPhone(String phone) {
        phoneStr = phone;
    }

    public String getName() {
        return this.nameStr;
    }
    public String getLocation() {
        return this.locationStr;
    }
    public String getPhone() {
        return this.phoneStr;
    }


}
