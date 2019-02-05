package com.schedulebot.gabrielbrusman;


public class AcademicTime {

    public AcademicTime(String quarter, int year) {
        this.quarter = quarter;
        this.year = year;
    }

    private String quarter;
    private int year;


    public String getQuarter() {
        return quarter;
    }

    public int getYear() {
        return year;
    }

    public void progressTime(){
        switch (this.quarter){
            case "Fall":
                this.quarter = "Winter";
                this.year++;
                break;
            case "Winter":
                this.quarter = "Spring";
                break;
            case "Spring":
                this.quarter = "Fall";
                break;
        }

    }


}
