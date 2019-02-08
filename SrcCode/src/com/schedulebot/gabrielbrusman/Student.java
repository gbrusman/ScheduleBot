package com.schedulebot.gabrielbrusman;

//FIXME: Consider using CardLayout for GUI, or tabbed panels

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Student {

    private AcademicTime curTime;
    private AcademicTime gradTime;
    private AcademicTime startTime;
    private Major major;
    private ArrayList<String> interests; //might have to just use array and sort it later
    private boolean isTakingSummerSessions; //not sure if we're doing Summer Sessions or not
    private HashMap<String, Course> classesTaken;




    public Student(AcademicTime curTime, AcademicTime gradTime, Major major, ArrayList<String> interests, /*boolean isTakingSummerSessions,*/ HashMap<String, Course> classesTaken) {
        this.curTime = curTime;
        AcademicTime startTime = new AcademicTime(curTime);
        this.startTime = startTime;
        this.gradTime = gradTime;
        //this.isTakingSummerSessions = isTakingSummerSessions;
        this.major = major;
       // this.prereqsSatisfied = prereqsSatisfied;
        this.interests = interests;
        this.classesTaken = classesTaken;
    }

    enum Major{
        LMATBS1, LMATBS2, LMATAB1, LMATAB2, LAMA, LMOR, LMCOMATH, LMCOBIO;
    }


    public boolean isTakingSummerSessions() {
        return isTakingSummerSessions;
    }





    public AcademicTime getCurTime() {
        return curTime;
    }

    public AcademicTime getGradTime() {
        return gradTime;
    }

    public Major getMajor() {
        return major;
    }

   /* public boolean[] getPrereqsSatisfied() {
        return prereqsSatisfied;
    }*/

    public ArrayList<String> getInterests() {
        return interests;
    }

    public HashMap<String, Course> getClassesTaken() { return classesTaken;}


    public boolean hasTaken(String courseName){
       return classesTaken.containsKey(courseName);
    }

    //does student have prereqs to take some course
    //When implementing GUI make sure to include "C- or better" "B- or better", etc. esp. for 16/17 series and H classes
    public boolean hasPrereqs(Course course, ScheduleBlock block){ //FIXME: need to include Honors classes probably
        switch(course.getName()){
            case "MAT21A":
                return true; //no real prereqs for 21A
            case "MAT21B":
                return hasTaken("MAT21A") || hasTaken("MAT17A"); //17A B or above
            case "MAT21C":
                return hasTaken("MAT21B") || hasTaken("MAT16C") || hasTaken("MAT17C") || hasTaken("MAT17B"); //techincally 16C, 17B, 17C can be prereqs, but don't have info on these courses
            case "MAT21D":
                return hasTaken("MAT21C") || hasTaken("MAT17C"); //17C B or above
            case "MAT22A":
                return (hasTaken("MAT21C") || hasTaken("MAT16C") || hasTaken("MAT17C")) && (hasTaken("ENG06") || hasTaken("MAT22AL"));
            case "MAT22AL":
                return hasTaken("MAT21C") || hasTaken("MAT17C");
            case "MAT67":
                return hasTaken("MAT21C");
            case "MAT22B":
                return hasTaken("MAT22A") || hasTaken("MAT67");
            case "MAT108":
                return hasTaken("MAT21B");
            case "MAT111":
                return hasTaken("MAT67") || hasTaken("MAT67") || hasTaken("MAT108") || hasTaken("MAT114") || hasTaken("MAT115A") || hasTaken("MAT141") || hasTaken("MAT145");
            case "MAT114":
                return hasTaken("MAT21C") && (hasTaken("MAT22A") || hasTaken("MAT67"));
            case "MAT115A":
                return hasTaken("MAT21B");
            case "MAT115B":
                return hasTaken("MAT115A") && (hasTaken("MAT22A") || hasTaken("MAT67"));
            case "MAT116":
                return hasTaken("MAT21D") && hasTaken("MAT22B") && (hasTaken("MAT22A") || hasTaken("MAT67"));
            case "MAT118A":
                return hasTaken("MAT21D") && hasTaken("MAT22B") && (hasTaken("MAT22A") || hasTaken("MAT67"));
            case "MAT118B":
                return hasTaken("MAT118A");
            case "MAT119A":
                return hasTaken("MAT21D") && hasTaken("MAT22B") && (hasTaken("MAT22A") || hasTaken("MAT67"));
            case "MAT119B":
                return hasTaken("MAT119A");
            case "MAT124":
                return hasTaken("MAT22B") && (hasTaken("MAT22A") || hasTaken("MAT67"));
            case "MAT127A":
                return hasTaken("MAT21C") && (hasTaken("MAT67") || (hasTaken("MAT22A") && hasTaken("MAT108")));
            case "MAT127B":
                return hasTaken("MAT127A");
            case "MAT127C":
                return hasTaken("MAT127B");
            case "MAT128A":
                return hasTaken("MAT21C") && (hasTaken("ECS32A") || hasTaken("ENG06") || hasTaken("EME05") || hasTaken("ECS30"));
            case "MAT128B":
                return (hasTaken("MAT22A") || hasTaken("MAT67")) && (hasTaken("ECS32A") || hasTaken("ENG06") || hasTaken("EME005") || hasTaken("ECS30"));
            case "MAT128C":
                return (hasTaken("MAT22A") || hasTaken("MAT67")) && hasTaken("MAT22B") && (hasTaken("ECS32A") || hasTaken("ENG06") || hasTaken("EME05") || hasTaken("ECS30"));
            case "MAT129":
                return hasTaken("MAT21D") && hasTaken("MAT22B") && (hasTaken("MAT22A") || hasTaken("MAT67")) && hasTaken("MAT127A");
            case "MAT133":
                return (hasTaken("MAT67") || (hasTaken("MAT22A") && hasTaken("MAT108"))) && hasTaken("MAT135A");
            case "MAT135A":
                return hasTaken("MAT21C") && (hasTaken("MAT108") || hasTaken("MAT127A"));
            case "MAT135B":
                return hasTaken("MAT135A") && (hasTaken("MAT22A") || hasTaken("MAT67"));
            case "MAT141":
                return hasTaken("MAT21B") && (hasTaken("MAT22A") || hasTaken("MAT67"));
            case "MAT145":
                return hasTaken("MAT21C");
            case "MAT146":
                return hasTaken("MAT145") && hasTaken("MAT127A") && (hasTaken("MAT22A") || hasTaken("MAT67"));
            case "MAT147":
                return hasTaken("MAT127A");
            case "MAT148":
                return hasTaken("MAT67") || (hasTaken("MAT22A") && hasTaken("MAT108"));
            case "MAT150A":
                return hasTaken("MAT67") || (hasTaken("MAT22A") && hasTaken("MAT108"));
            case "MAT150B":
                return hasTaken("MAT150A");
            case "MAT150C":
                return hasTaken("MAT150B");
            case "MAT160":
                return hasTaken("MAT167");
            case "MAT165":
                return hasTaken("MAT22A") || hasTaken("MAT67") && (hasTaken("MAT127A") || hasTaken("MAT108") || hasTaken("MAT114") || hasTaken("MAT115A") || hasTaken("MAT145"));
            case "MAT167":
                return hasTaken("MAT22A") || hasTaken("MAT67");
            case "MAT168":
                return hasTaken("MAT21C") && ((hasTaken("MAT22A") && hasTaken("MAT108")) || hasTaken("MAT67"));
            case "MAT180":
                return hasTaken("MAT127A") && (hasTaken("MAT67") || (hasTaken("MAT22A") && hasTaken("MAT108")));
            case "MAT185A":
                return (hasTaken("MAT67") || (hasTaken("MAT22A") && hasTaken("MAT108"))) && hasTaken("MAT127B");
            case "MAT185B":
                return hasTaken("MAT185A");
            case "MAT189":
                return hasTaken("MAT127A") && ((hasTaken("MAT22A") && hasTaken("MAT108")) || hasTaken("MAT67"));
            case "ECS32A":
                return true;
            case "ENG06":
                return (hasTaken("MAT16A") || hasTaken("MAT17A") || hasTaken("MAT21A")) && (hasTaken("MAT16B") || hasTaken("MAT17B") || (hasTaken("MAT21B") || isTaking("MAT21B", block)));
            case "PHY7A":
                return true;
            case "PHY9A":
                return hasTaken("MAT21B");
            case "ECN1A":
                return true;
            case "ECN1B":
                return true;
            case "STA32":
                return hasTaken("MAT16B") || hasTaken("MAT21B") || hasTaken("MAT17B");
            case "STA100":
                return hasTaken("MAT16B") || hasTaken("MAT21B") || hasTaken("MAT17B");
        }

        return false; //error case
    }


    public boolean meetsRecommendations(Course course)
    {
        switch(course.getName()){
            case "ECS32A":
                return startTime != curTime;
            case "MAT108":
                return hasTaken("MAT21C");
            case "MAT22A":
                return hasTaken("MAT21C");

        }

        return true; //if it isn't a special case w/ recommendations
    }

    public boolean isTaking(String courseName, ScheduleBlock block){
        if(block.contains(courseName)){
            return true;
        }
        return false;
    }
}
