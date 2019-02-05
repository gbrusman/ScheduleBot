
//Get the Excel thing working
//Learn how to open the file first, then read it.

//v1.0 is just gonna hardcode the data from the spreadsheet into the program
//FIXME: Reorganize AcademicTime into just year, quarter, throw that into ScheduleBlock Class

package com.schedulebot.gabrielbrusman;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.*;
import java.io.File;

public class Schedule {
    private Student student;
    //private ArrayList<AcademicTime> academicTimes; //times between curTime and gradTime
    private ArrayList<Course> classesOffered; //all classes offered (from spreadsheet)
    private HashMap<AcademicTime, ScheduleBlock> schedule;
    //private HashMap<String, Course> classesByName; //FIXME: AFTER


    public Schedule(Student student, ArrayList<Course> classesOffered) {
        this.student = student;
  //      this.academicTimes = initTimes();
        this.classesOffered = classesOffered;
        this.schedule = new HashMap<AcademicTime, ScheduleBlock>(12); //max initial capacity needed if no summer sessions
/* //FIXME: AFTER
        for(Course course: this.classesOffered){
            this.classesByName.put(course.getName(), course);
        }
*/
        placeClasses(true);
        System.out.println("done with required!"); //for debugging
        placeClasses(false);
    }
/*
    public ArrayList<AcademicTime> initTimes(){
        AcademicTime curTime = student.getCurTime(); //get current time
        this.academicTimes.add(curTime); //add to array
        //need a while loop here to initialize the rest of the values
        AcademicTime endTime = student.getGradTime(); //get the end so we know where to stop
        while(curTime != endTime){
            curTime.progressTime();
            this.academicTimes.add(curTime);
        }
        return academicTimes;
    }
*/


    public void placeClasses(boolean required) {
        AcademicTime gradTime = student.getGradTime();
        AcademicTime curTime = student.getCurTime();
        curTime.progressTime(); //don't want it to start on the current quarter, want it to start on the next quarter
        AcademicTime startTime = student.getCurTime(); //FIXME: might not need this
        ArrayList<String> after = new ArrayList<String>(2);


        while (curTime != gradTime) { //for each class that's offered by UC Davis
            ScheduleBlock curBlock = new ScheduleBlock(curTime);
         /*   for(String courseName: after){ //FIXME: AFTER
                curBlock.addCourse(classesByName.get(courseName));
                after.remove(courseName);
            }*/
            for (Course curCourse : classesOffered) {
                if(curBlock.getCourses().size() == 2){ //if the current block is full of classes
                    break; //exit loop so we can move to next quarter and create new block
                }
                if (curCourse.isOffered(curTime) && student.hasPrereqs(curCourse) && !student.hasTaken(curCourse.getName()) && student.meetsRecommendations(curCourse)) {
                    if(required){ //if we're placing required classes, then we have to check if the course is required
                        if(curCourse.getRequired().get(student.getMajor())){ //FIXME: my attempt at getting the value from the HashMap (prob doesn't work)
                            //place course in the earliest possible quarter
                            curBlock.addCourse(curCourse); //adds course to current block
                            System.out.println("Adding: " + curCourse.getName() + " at " + curTime.getQuarter() + " " + curTime.getYear());
                            after.add(curCourse.getAfter()); //FIXME: might need a null check if we're adding null values
                        }
                    }
                    else{ //we're not only placing require classes, so just place course in the earliest possible quarter
                        curBlock.addCourse(curCourse); //adds course to current block
                        System.out.println("Adding: " + curCourse.getName() + " at " + curTime.getQuarter() + " " + curTime.getYear());
                        after.add(curCourse.getAfter()); //FIXME: might need a null check if we're adding null values
                    }
                }

            }
            for(Course course: curBlock.getCourses()) {
                student.getClassesTaken().put(course.getName(), course); //add all of the classes in curBlock to classesTaken (add here to avoid prereq issues)
            }

            schedule.put(curTime, curBlock); //adds block to schedule
            curTime.progressTime(); // move on to the next quarter (time)
        }
    }

/*
    public void importClassesOffered(){
        ClassLoader classLoader = getClass().getClassLoader();
        File classInfo = new File(classLoader.getResource("ScheduleBot Class Info.xlsx"));
        Workbook workbook = WorkbookFactory.create(classInfo);
    }
*/
}





   /* public void placeRequiredClasses(){
        AcademicTime gradTime = student.getGradTime();
        ScheduleBlock scheduleBlock;
        for(Course curCourse: classesOffered){ //for each class that's offered by UC Davis
            AcademicTime curTime = student.getCurTime();
            scheduleBlock.setTime(curTime); //sets the time of the block
            boolean placed = false;
            while((curTime != gradTime) && !placed) { //while there are still times to place the class and the class isn't placed
                //if the course is required, offered, and the student has the prereqs to take it
                if (curCourse.getRequired() && curCourse.isOffered(curTime) && student.hasPrereqs(curCourse) && !student.hasTaken(curCourse)){
                    //place course in the earliest possible quarter
                    scheduleBlock.addCourse(curCourse); //
                    placed = true;
                }
                else {
                    curTime.progressTime();
                }
                }
            }
        }
*/
