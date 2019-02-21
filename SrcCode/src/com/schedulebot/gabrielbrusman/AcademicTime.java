package com.schedulebot.gabrielbrusman;


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
}
