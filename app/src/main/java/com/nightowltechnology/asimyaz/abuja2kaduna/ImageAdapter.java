package com.nightowltechnology.asimyaz.abuja2kaduna;

/**
 * Created by pc on 5/12/2017.
 */

public class ImageAdapter {


    private String arrivalFirst;
    private String departureFirst;
    private String arrivalSecond;
    private String departureSecond;
    private String destination;
    private String myRoute1,myRoute2,myRoute3,myRoute4;

    public ImageAdapter()
    {

    }
    public ImageAdapter(String arrive1, String arrive2, String depart1, String depart2, String statusImage, String destination, String route1, String route2, String route3, String route4)
    {
        this.arrivalFirst = arrive1;
        this.arrivalSecond = arrive2;
        this.departureFirst = depart1;
        this.departureSecond = depart2;
        this.destination = destination;
        this.statusImage = statusImage;
        this.myRoute1 = route1;
        this.myRoute2 = route2;
        this.myRoute3 = route3;
        this.myRoute4 = route4;
    }
    public String getMyRoute1() {
        return myRoute1;
    }

    public void setMyRoute1(String myRoute1) {
        this.myRoute1 = myRoute1;
    }

    public String getMyRoute2() {
        return myRoute2;
    }

    public void setMyRoute2(String myRoute2) {
        this.myRoute2 = myRoute2;
    }

    public String getMyRoute3() {
        return myRoute3;
    }

    public void setMyRoute3(String myRoute3) {
        this.myRoute3 = myRoute3;
    }

    public String getMyRoute4() {
        return myRoute4;
    }

    public void setMyRoute4(String myRoute4) {
        this.myRoute4 = myRoute4;
    }


    public String getStatusImage() {
        return statusImage;
    }

    public void setStatusImage(String statusImage) {
        this.statusImage = statusImage;
    }

    private String statusImage;

    public String getArrivalFirst() {
        return arrivalFirst;
    }

    public void setArrivalFirst(String arrivalFirst) {
        this.arrivalFirst = arrivalFirst;
    }

    public String getDepartureFirst() {
        return departureFirst;
    }

    public void setDepartureFirst(String departureFirst) {
        this.departureFirst = departureFirst;
    }

    public String getArrivalSecond() {
        return arrivalSecond;
    }

    public void setArrivalSecond(String arrivalSecond) {
        this.arrivalSecond = arrivalSecond;
    }

    public String getDepartureSecond() {
        return departureSecond;
    }

    public void setDepartureSecond(String departureSecond) {
        this.departureSecond = departureSecond;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }


}
