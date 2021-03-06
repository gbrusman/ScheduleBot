
//v1.0 is just gonna hardcode the data from the spreadsheet into the program

package com.schedulebot.gabrielbrusman;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import java.io.File;

//FIXME: need a way to figure out how to pick 2 quarter-sequence classes or just classes where you have to pick (e.g. 2 out of 3 of x options)
    //related to isRedundant, could have case for each of the, say 3, options, and then say don't take it if you've already taken the other 2
//FIXME: for choosing between PHY, CHE, ECN, etc. could just ask at the beginning what they'd prefer to take
    //only need to ask for certain majors though (Applied, BS2)

public class Schedule {
    //data members
    private Student student;
    private ArrayList<Course> classesOffered; //all classes offered (from spreadsheet)
    private HashMap<AcademicTime, ScheduleBlock> schedule;
    private HashMap<String, Course> classesByName; //FIXME: AFTER

    private HashMap<String, HashMap<String, Course>> interestTable; //need to be able to pull interests by name, and courses by name

    //constructor
    public Schedule(Student student, ArrayList<Course> classesOffered) {
        this.student = new Student(student);
        this.classesOffered = new ArrayList<Course>(classesOffered); //FIXME: since we have classesByName we can technically get rid of this
        this.schedule = new HashMap<AcademicTime, ScheduleBlock>(12); //max initial capacity needed if no summer sessions
        //FIXME: AFTER
        this.classesByName = new HashMap<String, Course>(55);
        for(Course course: this.classesOffered){
            this.classesByName.put(course.getName(), course);
        }

        //implement interest list system (this could be made a lot more efficient, could also make it in TesterMain perhaps
        this.interestTable = new HashMap<String, HashMap<String, Course>>();
       String[] interests = {"Teaching", "Geometry", "Physics", "Biology", "Computers", "Finance", "Abstract", "Data Analysis"};
        for(String interest: interests){
            HashMap<String, Course> curInterest = new HashMap<String, Course>();
            for (Course course: classesOffered){
                if( course.getInterests() != null && course.getInterests().contains(interest)){
                    curInterest.put(course.getName(), course);
                }
            }
            interestTable.put(interest, curInterest);
        }

        placeClasses();
       /* if(isSuccess(student.getMajor())){
            System.out.println("SUCCESS!");
        }
        else{
            System.out.println("FAILURE ;(");
        }*/
    }

    public HashMap<AcademicTime, ScheduleBlock> getSchedule() {
        return schedule;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    //main sorting method


    public void placeClasses() {
        AcademicTime gradTime = student.getGradTime();
        AcademicTime curTime = student.getCurTime();
        curTime = curTime.progressTime(); //don't want it to start on the current quarter, want it to start on the next quarter
        ArrayList<String> after = new ArrayList<String>(2);
        int requiredOrElectives = 0; //0 if need to place required, 1 if have placed required, 2 if have placed electives
        AcademicTime finishTime = student.getGradTime();
        finishTime = finishTime.progressTime(); //semantically, we have to be finished by the END of gradTime
        //AcademicTime startTime = new AcademicTime(curTime);


        while (!curTime.equals(finishTime)) { //while the student hasn't graduated yet
            ScheduleBlock curBlock = new ScheduleBlock(curTime);

            fillFromAfter(curBlock, after, curTime); //if there are classes from after, fill them in

            tryToFillCurTime(curBlock, after, curTime); //try to fill the current quarter with classes

            for(Course course: curBlock.getCourses()) {  //add all of the classes in curBlock to classesTaken (add here to avoid prereq issues)
                student.getClassesTaken().put(course.getName(), course);
            }

            schedule.put(curTime, curBlock); //adds block to schedule
            curTime = new AcademicTime(curTime.progressTime()); // move on to the next quarter (time)
            student.setCurTime(curTime);
        }
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


public void addCourseToBlock(Course course, ScheduleBlock block, ArrayList<String> after, AcademicTime time){
    block.addCourse(course); //adds course to current block
    //System.out.println("Adding: " + course.getName() + " at " + time.getQuarter() + " " + time.getYear());
    after.add(course.getAfter());
    classesOffered.remove(course);
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public void addCourseFromAfter(Course course, ScheduleBlock block, ArrayList<String> after, AcademicTime time, int index){
    block.addCourse(course); //adds course to current block
    //System.out.println("Adding: " + course.getName() + " at " + time.getQuarter() + " " + time.getYear());
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
    boolean placedInterest = false;

    while(requiredOrElectives < 2) {
        for (int i = 0; i < classesOffered.size(); i++) {

            curCourse = classesOffered.get(i);
            if (curBlock.getCourses().size() == 2) { //if the current block is full of classes
                break; //exit loop so we can move to next quarter and create new block
            }
            if (classIsValid(curCourse, curTime, curBlock)) {
                if (requiredOrElectives == 0) {
                    if (curCourse.getRequired().get(student.getMajor())) { //if we're placing required classes, then we have to check if the course is required
                        addCourseToBlock(curCourse, curBlock, after, curTime);
                        i--; //to balance index when classes are removed
                    }
                } else {
                    placedInterest = tryToPlaceInterestingClass(curCourse, curTime, curBlock, after);

                    if(!placedInterest) {
                        addCourseToBlock(curCourse, curBlock, after, curTime);
                    }
                    i--; //to balance index
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
            case "ECS32A":
                if(student.getMajor().equals(Student.Major.LMATAB1) || student.getMajor().equals(Student.Major.LMATBS2)){
                   // return student.hasTaken("ENG06");
                    return true;
                }
            case "MAT128A":
                if(student.getMajor().equals(Student.Major.LAMA)) {
                    return student.hasTaken("MAT128B") && student.hasTaken("MAT128C");
                }
                if(student.getMajor().equals(Student.Major.LMOR) && !student.getInterests().contains("Computers")) {
                    return student.hasTaken("MAT128B") || student.hasTaken("MAT128C");
                }
            case "MAT128B":
                if(student.getMajor().equals(Student.Major.LAMA) && !student.getInterests().contains("Computers") ) {
                    return student.hasTaken("MAT128A") && student.hasTaken("MAT128C");
                }
                if(student.getMajor().equals(Student.Major.LMOR) && !student.getInterests().contains("Computers")) {
                    return student.hasTaken("MAT128A") || student.hasTaken("MAT128C");
                }
            case "MAT128C":
                if(student.getMajor().equals(Student.Major.LAMA) && !student.getInterests().contains("Computers")) {
                    return student.hasTaken("MAT128A") && student.hasTaken("MAT128B");
                }
                if(student.getMajor().equals(Student.Major.LMOR) && !student.getInterests().contains("Computers") ) {
                    return student.hasTaken("MAT128A") || student.hasTaken("MAT128B");
                }
        }
        return false;
    }


    //see if there's an "interesting" class that can be placed in the current time
    public boolean tryToPlaceInterestingClass(Course curCourse, AcademicTime curTime, ScheduleBlock curBlock, ArrayList<String> after){
        boolean placedInterest = false;
        //run through interests list
        HashMap<String, Course> curInterestTable = new HashMap<String, Course>();
        for(String interest: student.getInterests()){
            curInterestTable = interestTable.get(interest);

            //place class if it's in current interest
            if(curInterestTable.containsValue(curCourse)){
                addCourseToBlock(curCourse, curBlock, after, curTime);

                placedInterest = true;
                break;
            }
            else{ //see if there's an interesting class that can fit here (super inefficient, I'm well aware)
                for(Course interestCourse: curInterestTable.values()){
                    if (classIsValid(interestCourse, curTime, curBlock)){
                        addCourseToBlock(interestCourse, curBlock, after, curTime);

                        placedInterest = true;
                        break;

                    }
                }
            }
        }
        return placedInterest;

    }



///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean classIsValid(Course course, AcademicTime time, ScheduleBlock block) {
        if (course.isOffered(time) && student.hasPrereqs(course, block) && !student.hasTaken(course.getName()) && student.meetsRecommendations(course) && !isRedundant(course, block) && !block.contains(course.getName())) {
            return true;
        }
        return false;
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isSuccessLAMA(){
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

    public boolean isSuccessLMOR(){
        ArrayList<String> requirements = new ArrayList<String>(30); //So far only for LAMA
        requirements.add("MAT21A");
        requirements.add("MAT21B");
        requirements.add("MAT21C");
        requirements.add("MAT21D");
        requirements.add("MAT22B");
        requirements.add("ENG06");
        requirements.add("ECN1A");
        requirements.add("ECN1B");
        requirements.add("STA32");
        requirements.add("MAT127A");
        requirements.add("MAT127B");
        requirements.add("MAT127C");
        requirements.add("MAT135A");
        requirements.add("MAT135B");
        requirements.add("MAT150A");
        requirements.add("MAT160");
        requirements.add("MAT168");

        for(String classStr: requirements){
            if(!student.getClassesTaken().containsKey(classStr)){
               // System.out.println("Schedule doesn't contain " + classStr + "!");
                return false;
            }
        }

        return true;
    }

    public boolean isSuccessLMATAB1(){
        ArrayList<String> requirements = new ArrayList<String>(30); //So far only for LAMA
        requirements.add("MAT21A");
        requirements.add("MAT21B");
        requirements.add("MAT21C");
        requirements.add("MAT21D");
        requirements.add("MAT22B");
        requirements.add("ENG06");
        requirements.add("MAT127A");
        requirements.add("MAT127B");
        requirements.add("MAT127C");
        requirements.add("MAT135A");
        requirements.add("MAT150A");

        for(String classStr: requirements){
            if(!student.getClassesTaken().containsKey(classStr)){
               // System.out.println("Schedule doesn't contain " + classStr + "!");
                return false;
            }
        }

        return true;
    }

    public boolean isSuccessLMATAB2(){
        ArrayList<String> requirements = new ArrayList<String>(30); //So far only for LAMA
        requirements.add("MAT21A");
        requirements.add("MAT21B");
        requirements.add("MAT21C");
        requirements.add("MAT21D");
        requirements.add("MAT22B");
        requirements.add("ENG06");
        requirements.add("MAT127A");
        requirements.add("MAT127B");
        requirements.add("MAT127C");
        requirements.add("MAT135A");
        requirements.add("MAT150A");
        requirements.add("MAT111");
        requirements.add("MAT115A");
        requirements.add("MAT141");

        for(String classStr: requirements){
            if(!student.getClassesTaken().containsKey(classStr)){
               // System.out.println("Schedule doesn't contain " + classStr + "!");
                return false;
            }
        }

        return true;
    }

    public boolean isSuccessLMATBS1(){
        ArrayList<String> requirements = new ArrayList<String>(30); //So far only for LAMA
        requirements.add("MAT21A");
        requirements.add("MAT21B");
        requirements.add("MAT21C");
        requirements.add("MAT21D");
        requirements.add("MAT22B");
        requirements.add("ENG06");
        requirements.add("PHY9A");
        requirements.add("MAT127A");
        requirements.add("MAT127B");
        requirements.add("MAT127C");
        requirements.add("MAT135A");
        requirements.add("MAT150A");
        requirements.add("MAT150B");
        requirements.add("MAT150C");
        requirements.add("MAT185A");

        for(String classStr: requirements){
            if(!student.getClassesTaken().containsKey(classStr)){
              //  System.out.println("Schedule doesn't contain " + classStr + "!");
                return false;
            }
        }

        return true;
    }


    public boolean isSuccessLMATBS2(){
        ArrayList<String> requirements = new ArrayList<String>(30); //So far only for LAMA
        requirements.add("MAT21A");
        requirements.add("MAT21B");
        requirements.add("MAT21C");
        requirements.add("MAT21D");
        requirements.add("MAT22B");
        requirements.add("ENG06");
        requirements.add("MAT127A");
        requirements.add("MAT127B");
        requirements.add("MAT127C");
        requirements.add("MAT135A");
        requirements.add("MAT150A");
        requirements.add("MAT111");
        requirements.add("MAT115A");
        requirements.add("MAT141");

        for(String classStr: requirements){
            if(!student.getClassesTaken().containsKey(classStr)){
              //  System.out.println("Schedule doesn't contain " + classStr + "!");
                return false;
            }
        }

        return true;
    }


    public boolean isSuccessLMCOBIO(){
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
        requirements.add("MAT128A");
        requirements.add("MAT128B");
        requirements.add("MAT128C");
        requirements.add("MAT135A");
        requirements.add("MAT150A");
        requirements.add("MAT124");

        for(String classStr: requirements){
            if(!student.getClassesTaken().containsKey(classStr)){
              //  System.out.println("Schedule doesn't contain " + classStr + "!");
                return false;
            }
        }

        return true;
    }

    public boolean isSuccessLMCOMATH(){
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
        requirements.add("MAT128A");
        requirements.add("MAT128B");
        requirements.add("MAT128C");
        requirements.add("MAT135A");
        requirements.add("MAT150A");
        requirements.add("MAT168");

        for(String classStr: requirements){
            if(!student.getClassesTaken().containsKey(classStr)){
             //   System.out.println("Schedule doesn't contain " + classStr + "!");
                return false;
            }
        }

        return true;
    }

    public boolean isSuccess(Student.Major major){ //loop through student.classesTaken at the end to make sure the schedule meets the requirements

        switch(major){
            case LAMA:
                return isSuccessLAMA();
            case LMOR:
                return isSuccessLMOR();
            case LMATAB1:
                return isSuccessLMATAB1();
            case LMATAB2:
                return isSuccessLMATAB2();
            case LMATBS1:
                return isSuccessLMATBS1();
            case LMATBS2:
                return isSuccessLMATBS2();
            case LMCOBIO:
                return isSuccessLMCOBIO();
            case LMCOMATH:
                return isSuccessLMCOMATH();
        }



        return false;
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



