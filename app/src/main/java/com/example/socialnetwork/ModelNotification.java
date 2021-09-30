package com.example.socialnetwork;

public class ModelNotification {

    String imageLeft;
    String imageRight;
    String descNoti;
    String timeNoti;

    public ModelNotification(String imageLeft, String imageRight, String descNoti, String timeNoti) {
        this.imageLeft = imageLeft;
        this.imageRight = imageRight;
        this.descNoti = descNoti;
        this.timeNoti = timeNoti;
    }

    public String getImageLeft() {
        return imageLeft;
    }

    public void setImageLeft(String imageLeft) {
        this.imageLeft = imageLeft;
    }

    public String getImageRight() {
        return imageRight;
    }

    public void setImageRight(String imageRight) {
        this.imageRight = imageRight;
    }

    public String getDescNoti() {
        return descNoti;
    }

    public void setDescNoti(String descNoti) {
        this.descNoti = descNoti;
    }

    public String getTimeNoti() {
        return timeNoti;
    }

    public void setTimeNoti(String timeNoti) {
        this.timeNoti = timeNoti;
    }
}
