
package com.schedulebot.gabrielbrusman;
import java.util.ArrayList;
import java.util.HashMap;


public class Course {
    //constructor
    public Course(String name, ArrayList<String> interests, String after, HashMap<Student.Major, Boolean> required, String[] quartersOffered, OfferedPattern yearsOffered) {
        this.name = name;
        this.interests = interests;
        this.after = after;
        this.quartersOffered = quartersOffered;
        this.yearsOffered = yearsOffered;
        this.required = required;
    }

    //enum for when the class is generally offered (years)
    enum OfferedPattern{
        ALWAYS, EVENALTERNATE, ODDALTERNATE;
    }


    private String name;
    private ArrayList<String> prereqs;
    private ArrayList<String> interests;
    private String after;
    private HashMap<Student.Major, Boolean> required;
    private String[] quartersOffered;
    private OfferedPattern yearsOffered;

    public String getName() {
        return name;
    }

    public ArrayList<String> getPrereqs() {
        return prereqs;
    }

    public ArrayList<String> getInterests() {
        return interests;
    }

    public String getAfter() {
        return after;
    }

    public String[] getQuartersOffered() {
        return quartersOffered;
    }

    public OfferedPattern getYearsOffered() {
        return yearsOffered;
    }

    public HashMap<Student.Major, Boolean> getRequired() { return required;}

    public boolean isOffered(AcademicTime time){
        boolean offeredInQuarter = false;
        boolean offeredInYear = false;
        String quarter = time.getQuarter();
        int year = time.getYear();
        //convention is that year is "even" if Fall Quarter is in an even year.

        //figure out if it's offered during the requested year
        if(yearsOffered == OfferedPattern.ALWAYS){ //if course is always offered
            offeredInYear = true;
        }
        else if(yearsOffered == OfferedPattern.EVENALTERNATE){
            if(quarter.equals("Fall")){
                if(year % 2 == 0){ //if it's Fall and the year is Even
                    offeredInYear = true;
                }
            }
            else{
                if(year % 2 == 1){ //if it's Winter or Spring and the year is Odd
                    offeredInYear = true;
                }
            }
        }
        else{ //yearsOffered == OfferedPattern.ODDALTERNATE
            if(quarter.equals("Fall")){
                if(year % 2 == 1){ //if Fall and year is Odd
                    offeredInYear = true;
                }
            }
            else{
                if(year % 2 == 0){ //if Winter or Spring and the year is Even
                    offeredInYear = true;
                }
            }
        }

        //figure out if it's offered during the requested quarter
        for(String q: quartersOffered){
            if(quarter.equals(q)){
                offeredInQuarter = true;
                break;
            }
        }
        return offeredInQuarter && offeredInYear;
    }
}
