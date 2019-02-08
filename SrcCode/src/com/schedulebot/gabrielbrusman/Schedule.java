
//v1.0 is just gonna hardcode the data from the spreadsheet into the program

package com.schedulebot.gabrielbrusman;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.*;
import java.io.File;

//FIXME: need a way to figure out how to pick 2 quarter-sequence classes or just classes where you have to pick (e.g. 2 out of 3 of x options)
    //related to isRedundant
//FIXME: might want isRedundant function per major, or if statements based on major
//FIXME: for choosing between PHY, CHE, ECN, etc. could just ask at the beginning what they'd prefer to take
    //only need to ask for certain majors though (Applied, BS2)

//FIXME: ***IMPORTANT*** NOT GETTING ALL REQUIRE CLASSES (E.G. LAMA ECS32A)

public class Schedule {
    //data members
    private Student student;
    private ArrayList<Course> classesOffered; //all classes offered (from spreadsheet)
    private HashMap<AcademicTime, ScheduleBlock> schedule;
    private HashMap<String, Course> classesByName; //FIXME: AFTER

    //constructor
    public Schedule(Student student, ArrayList<Course> classesOffered) {
        this.student = student;
        this.classesOffered = classesOffered; //FIXME: since we have classes ByName we can technically get rid of this
        this.schedule = new HashMap<AcademicTime, ScheduleBlock>(12); //max initial capacity needed if no summer sessions
        //FIXME: AFTER
        this.classesByName = new HashMap<String, Course>(55);
        for(Course course: this.classesOffered){
            this.classesByName.put(course.getName(), course);
        }

        placeClasses();
        System.out.println("done with required!"); //for debugging
        if(isSuccess()){
            System.out.println("SUCCESS!");
        }
        else{
            System.out.println("FAILURE ;(");
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////
    //main sorting method


    public void placeClasses() {
        AcademicTime gradTime = student.getGradTime();
        AcademicTime curTime = student.getCurTime();
        curTime.progressTime(); //don't want it to start on the current quarter, want it to start on the next quarter
        ArrayList<String> after = new ArrayList<String>(2);
        int requiredOrElectives = 0; //0 if need to place required, 1 if have placed required, 2 if have placed electives
        AcademicTime finishTime = student.getGradTime();
        finishTime.progressTime(); //semantically, we have to be finished by the END of gradTime
        //AcademicTime startTime = new AcademicTime(curTime);


        while (!curTime.equals(finishTime)) { //while the student hasn't graduated yet
            ScheduleBlock curBlock = new ScheduleBlock(curTime);

            fillFromAfter(curBlock, after, curTime); //if there are classes from after, fill them in

            tryToFillCurTime(curBlock, after, curTime); //try to fill the current quarter with classes

            for(Course course: curBlock.getCourses()) {  //add all of the classes in curBlock to classesTaken (add here to avoid prereq issues)
                student.getClassesTaken().put(course.getName(), course);
            }

            schedule.put(curTime, curBlock); //adds block to schedule
            curTime.progressTime(); // move on to the next quarter (time)
        }
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


public void addCourseToBlock(Course course, ScheduleBlock block, ArrayList<String> after, AcademicTime time){
    block.addCourse(course); //adds course to current block
    System.out.println("Adding: " + course.getName() + " at " + time.getQuarter() + " " + time.getYear());
    after.add(course.getAfter());
    classesOffered.remove(course);
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public void addCourseFromAfter(Course course, ScheduleBlock block, ArrayList<String> after, AcademicTime time, int index){
    block.addCourse(course); //adds course to current block
    System.out.println("Adding: " + course.getName() + " at " + time.getQuarter() + " " + time.getYear());
    //after.add(course.getAfter());
    classesOffered.remove(course);
    after.remove(index);
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public void fillFromAfter(ScheduleBlock curBlock, ArrayList<String> after, AcademicTime curTime){
    //add the courses in the after list from the previous quarter
    Course afterCourse;
    ArrayList<String> nextAfter = new ArrayList<String>(2);

    for(int i = 0; i < after.size(); i++){
        afterCourse = classesByName.get(after.get(i));

        if(afterCourse != null){ //FIXME: have to then add the afters of THESE courses to after, but after the loop is done
            if(afterCourse.isOffered(curTime) && afterCourse.getRequired().get(student.getMajor())){
                addCourseFromAfter(afterCourse, curBlock, after, curTime, i);
                nextAfter.add(afterCourse.getAfter());
                i--; //because we're decreasing the size of the list
            }
        }
    }
    for(int i = 0; i < nextAfter.size(); i++){ //adds next quarter's "afters" from the current quarter's afters
        after.add(nextAfter.get(i));
    }
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public void tryToFillCurTime(ScheduleBlock curBlock, ArrayList<String> after, AcademicTime curTime){

    Course curCourse;
    int requiredOrElectives = 0;

    while(requiredOrElectives < 2) {
        for (int i = 0; i < classesOffered.size(); i++) {

            curCourse = classesOffered.get(i);
            if (curBlock.getCourses().size() == 2) { //if the current block is full of classes
                break; //exit loop so we can move to next quarter and create new block
            }
            if (curCourse.isOffered(curTime) && student.hasPrereqs(curCourse) && !student.hasTaken(curCourse.getName()) && student.meetsRecommendations(curCourse) && !isRedundant(curCourse, curBlock)) {
                if (requiredOrElectives == 0) { //if we're placing required classes, then we have to check if the course is required
                    if (curCourse.getRequired().get(student.getMajor())) {
                        //place course in the earliest possible quarter
                        addCourseToBlock(curCourse, curBlock, after, curTime);
                        i--; //to balance index when classes are removed
                    }
                } else { //we're not only placing require classes, so just place course in the earliest possible quarter (requiredOrElectives == 1)
                    addCourseToBlock(curCourse, curBlock, after, curTime);
                    i--; //to balance index when classes are removed
                }
            }
        }
        requiredOrElectives++;
    }
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean isRedundant(Course course, ScheduleBlock block){
        switch(course.getName()){
            case "MAT67":
                return student.hasTaken("MAT22A") || student.hasTaken("MAT108") || block.contains("MAT22A") || block.contains("MAT108");
            case "MAT22AL":
                return student.hasTaken("ENG06") || block.contains("ENG06");
        }
        return false;
    }


    public boolean isSuccess(){ //loop through student.classesTaken at the end to make sure the schedule meets the requirements
        ArrayList<String> requirements = new ArrayList<String>(30); //So far only for LAMA
        requirements.add("MAT21A");
        requirements.add("MAT21B");
        requirements.add("MAT21C");
        requirements.add("MAT21D");
        requirements.add("MAT22B");
        requirements.add("ENG06");
        requirements.add("ECS32A");
        requirements.add("MAT127A");
        requirements.add("MAT127B");
        requirements.add("MAT127C");
        requirements.add("MAT135A");
        requirements.add("MAT150A");
        requirements.add("MAT119A");
        requirements.add("MAT185A");

        for(String classStr: requirements){
            if(!student.getClassesTaken().containsKey(classStr)){
                return false;
            }
        }

        return true;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//FIXME: Add Excel support
/*
    public void importClassesOffered(){
        ClassLoader classLoader = getClass().getClassLoader();
        File classInfo = new File(classLoader.getResource("ScheduleBot Class Info.xlsx"));
        Workbook workbook = WorkbookFactory.create(classInfo);
    }
*/
}



