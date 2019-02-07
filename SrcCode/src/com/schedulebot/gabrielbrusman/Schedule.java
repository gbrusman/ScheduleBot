
//Get the Excel thing working
//Learn how to open the file first, then read it.

//v1.0 is just gonna hardcode the data from the spreadsheet into the program
//FIXME: Reorganize AcademicTime into just year, quarter, throw that into ScheduleBlock Class

package com.schedulebot.gabrielbrusman;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.*;
import java.io.File;

//FIXME: need a way to figure out how to pick 2 quarter-sequence classes or just classes where you have to pick (e.g. 2 out of 3 of x options)
//FIXME: adding elective classes just adds all non-required classes (relates to above)

/*FIXME: **IMPORTANT** MAIN ISSUES (RELATING TO AFTER)
    //FIXME: have to then add the afters of THESE courses to after, but after the loop is done
    //FIXME: since it can add classes during AFTER loop and then also later, it adds the same classes twice
 */


public class Schedule {
    private Student student;
    //private ArrayList<AcademicTime> academicTimes; //times between curTime and gradTime
    private ArrayList<Course> classesOffered; //all classes offered (from spreadsheet)
    private HashMap<AcademicTime, ScheduleBlock> schedule;
    private HashMap<String, Course> classesByName; //FIXME: AFTER


    public Schedule(Student student, ArrayList<Course> classesOffered) {
        this.student = student;
        this.classesOffered = classesOffered; //FIXME: since we have classesByName we can technically get rid of this
        this.schedule = new HashMap<AcademicTime, ScheduleBlock>(12); //max initial capacity needed if no summer sessions
        //FIXME: AFTER
        this.classesByName = new HashMap<String, Course>(55);
        for(Course course: this.classesOffered){
            this.classesByName.put(course.getName(), course);
        }

        placeClasses();
        System.out.println("done with required!"); //for debugging
    }



    public void placeClasses() {
        AcademicTime gradTime = student.getGradTime();
        AcademicTime curTime = student.getCurTime();
        curTime.progressTime(); //don't want it to start on the current quarter, want it to start on the next quarter
        AcademicTime startTime = student.getCurTime(); //FIXME: might not need this
        ArrayList<String> after = new ArrayList<String>(2);
        int requiredOrElectives = 0; //0 if need to place required, 1 if have placed required, 2 if have placed electives
        AcademicTime finishTime = student.getGradTime();
        finishTime.progressTime(); //semantically, we have to be finished by the END of gradTime
        Course curCourse;
        Course afterCourse;

        while (!curTime.equals(finishTime)) { //for each class that's offered by UC Davis
            ScheduleBlock curBlock = new ScheduleBlock(curTime);
            requiredOrElectives = 0;
            //add the courses in the after list from the previous quarter
            for(int i = 0; i < after.size(); i++){
                afterCourse = classesByName.get(after.get(i));


                //FIXME: since it can add classes here and then also later, it adds the same classes twice
                if(afterCourse != null){ //FIXME: have to then add the afters of THESE courses to after, but after the loop is done
                    if(afterCourse.isOffered(curTime) && afterCourse.getRequired().get(student.getMajor())){
                        curBlock.addCourse(afterCourse);
                        System.out.println("Adding: " + afterCourse.getName() + " at " + curTime.getQuarter() + " " + curTime.getYear());
                        after.remove(i);
                        i--; //because we're decreasing the size of the list
                        classesOffered.remove(afterCourse); //will this do anything or do I need to override .equals for Course obj?
                    }
                }
            }
            while(requiredOrElectives < 2) {
                for (int i = 0; i < classesOffered.size(); i++) {
                    curCourse = classesOffered.get(i);
                    if (curBlock.getCourses().size() == 2) { //if the current block is full of classes
                        break; //exit loop so we can move to next quarter and create new block
                    }
                    if (curCourse.isOffered(curTime) && student.hasPrereqs(curCourse) && !student.hasTaken(curCourse.getName()) && student.meetsRecommendations(curCourse)) {
                        if (requiredOrElectives == 0) { //if we're placing required classes, then we have to check if the course is required
                            if (curCourse.getRequired().get(student.getMajor())) {
                                //place course in the earliest possible quarter
                                curBlock.addCourse(curCourse); //adds course to current block
                                System.out.println("Adding: " + curCourse.getName() + " at " + curTime.getQuarter() + " " + curTime.getYear());
                                after.add(curCourse.getAfter());
                                classesOffered.remove(curCourse);
                                i--; //tp balance index when classes are removed
                            }
                        }
                        else { //we're not only placing require classes, so just place course in the earliest possible quarter (requiredOrElectives == 1)
                            curBlock.addCourse(curCourse); //adds course to current block
                            System.out.println("Adding: " + curCourse.getName() + " at " + curTime.getQuarter() + " " + curTime.getYear());
                            after.add(curCourse.getAfter());
                            classesOffered.remove(curCourse);
                            i--; //to balance index when classes are removed
                        }
                    }
                    requiredOrElectives++;
                }
            }
            for(Course course: curBlock.getCourses()) {
                student.getClassesTaken().put(course.getName(), course); //add all of the classes in curBlock to classesTaken (add here to avoid prereq issues)
            }

            schedule.put(curTime, curBlock); //adds block to schedule
            curTime.progressTime(); // move on to the next quarter (time)
        }
    }









//FIXME: Add Excel support
/*
    public void importClassesOffered(){
        ClassLoader classLoader = getClass().getClassLoader();
        File classInfo = new File(classLoader.getResource("ScheduleBot Class Info.xlsx"));
        Workbook workbook = WorkbookFactory.create(classInfo);
    }
*/
}



