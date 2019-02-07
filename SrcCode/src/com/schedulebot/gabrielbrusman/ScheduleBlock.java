package com.schedulebot.gabrielbrusman;


import java.util.ArrayList;

public class ScheduleBlock {
    private ArrayList<Course> courses;
    private AcademicTime time;


    public ScheduleBlock(AcademicTime time, ArrayList<Course> courses){
        this.time = time;
        this.courses = courses;

    }

    public ScheduleBlock(AcademicTime time){
        this.time = time;
        this.courses = new ArrayList<Course>(2);
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public AcademicTime getTime() {
        return time;
    }

    public void setTime(AcademicTime time) {
        this.time = time;
    }

    public void addCourse(Course course){
        courses.add(course);
    }

    public boolean contains(String courseName){
        for(int i = 0; i < courses.size(); i++){
            if(courses.get(i).getName() == courseName){
                return true;
            }
        }
        return false;
    }
}
