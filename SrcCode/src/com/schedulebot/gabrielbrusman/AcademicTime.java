package com.schedulebot.gabrielbrusman;


import java.util.Objects;

public class AcademicTime {

    public AcademicTime(String quarter, int year) {
        this.quarter = quarter;
        this.year = year;
    }

    //copy constructor
    public AcademicTime(AcademicTime time){
        this.quarter = time.getQuarter();
        this.year = time.getYear();
    }

    public AcademicTime(){

    }

    private String quarter;
    private int year;


    public String getQuarter() {
        return quarter;
    }

    public int getYear() {
        return year;
    }

    public AcademicTime progressTime(){
        AcademicTime newTime = new AcademicTime(this);
        switch (newTime.quarter){
            case "Fall":
                newTime.quarter = "Winter";
                newTime.year++;
                break;
            case "Winter":
                newTime.quarter = "Spring";
                break;
            case "Spring":
                newTime.quarter = "Fall";
                break;
        }
        return newTime;

    }

    public AcademicTime reverseTime(){
        AcademicTime newTime = new AcademicTime(this);
        switch(this.quarter){
            case "Fall":
                newTime.quarter = "Spring";
                break;
            case "Winter":
                newTime.quarter = "Fall";
                newTime.year--;
                break;
            case "Spring":
                newTime.quarter = "Winter";
                break;
        }
        return newTime;
    }



    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }
        if (!(obj instanceof AcademicTime)) {
            return false;
        }
        AcademicTime compare = (AcademicTime)obj;
        return (this.quarter == compare.getQuarter()) && (this.year == compare.getYear());
    }

    @Override
    public int hashCode() {
        return Objects.hash(quarter, year);
    }
}
