package com.schedulebot.gabrielbrusman;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class ScheduleGUI {
    private JPanel tabContainer;
    private JTabbedPane basicData;
    private JComboBox majorOptions;
    private JCheckBox MAT21ACheckBox;
    private JCheckBox MAT21CCheckBox;
    private JCheckBox MAT21BCheckBox;
    private JCheckBox MAT21DCheckBox;
    private JCheckBox MAT22ACheckBox;
    private JCheckBox MAT22ALCheckBox;
    private JCheckBox MAT22BCheckBox;
    private JCheckBox MAT67CheckBox;
    private JCheckBox MAT108CheckBox;


    public ScheduleGUI() {
        Student student = new Student();

        //load in the class data manually

        //MAT21A
        HashMap<Student.Major, Boolean> required21A= new HashMap<Student.Major, Boolean>(8);
        required21A.put(Student.Major.LMATBS1, true);
        required21A.put(Student.Major.LMATBS2, true);
        required21A.put(Student.Major.LMATAB1, true);
        required21A.put(Student.Major.LMATAB2, true);
        required21A.put(Student.Major.LAMA, true);
        required21A.put(Student.Major.LMOR, true);
        required21A.put(Student.Major.LMCOMATH, true);
        required21A.put(Student.Major.LMCOBIO, true);
        String[] quartersOffered21A = new String[]{"Fall", "Winter", "Spring"};

        final Course MAT21A = new Course("MAT21A", null, "MAT21B", required21A, quartersOffered21A, Course.OfferedPattern.ALWAYS);

        //MAT21B
        HashMap<Student.Major, Boolean> required21B= new HashMap<Student.Major, Boolean>(8);
        required21B.put(Student.Major.LMATBS1, true);
        required21B.put(Student.Major.LMATBS2, true);
        required21B.put(Student.Major.LMATAB1, true);
        required21B.put(Student.Major.LMATAB2, true);
        required21B.put(Student.Major.LAMA, true);
        required21B.put(Student.Major.LMOR, true);
        required21B.put(Student.Major.LMCOMATH, true);
        required21B.put(Student.Major.LMCOBIO, true);
        String[] quartersOffered21B = new String[]{"Fall", "Winter", "Spring"};

        final Course MAT21B = new Course("MAT21B", null, "MAT21C", required21B, quartersOffered21B, Course.OfferedPattern.ALWAYS);


        //MAT21C
        HashMap<Student.Major, Boolean> required21C= new HashMap<Student.Major, Boolean>(8);
        required21C.put(Student.Major.LMATBS1, true);
        required21C.put(Student.Major.LMATBS2, true);
        required21C.put(Student.Major.LMATAB1, true);
        required21C.put(Student.Major.LMATAB2, true);
        required21C.put(Student.Major.LAMA, true);
        required21C.put(Student.Major.LMOR, true);
        required21C.put(Student.Major.LMCOMATH, true);
        required21C.put(Student.Major.LMCOBIO, true);
        String[] quartersOffered21C = new String[]{"Fall", "Winter", "Spring"};

        final Course MAT21C = new Course("MAT21C", null, "MAT21D", required21C, quartersOffered21C, Course.OfferedPattern.ALWAYS);


        //MAT21D
        HashMap<Student.Major, Boolean> required21D= new HashMap<Student.Major, Boolean>(8);
        required21D.put(Student.Major.LMATBS1, true);
        required21D.put(Student.Major.LMATBS2, true);
        required21D.put(Student.Major.LMATAB1, true);
        required21D.put(Student.Major.LMATAB2, true);
        required21D.put(Student.Major.LAMA, true);
        required21D.put(Student.Major.LMOR, true);
        required21D.put(Student.Major.LMCOMATH, true);
        required21D.put(Student.Major.LMCOBIO, true);
        String[] quartersOffered21D = new String[]{"Fall", "Winter", "Spring"};

        final Course MAT21D = new Course("MAT21D", null, null, required21D, quartersOffered21D, Course.OfferedPattern.ALWAYS);

        //MAT22A //FIXME: For all majors, either 22A OR MAT67 is required, but should I even consider scheduling 67?
        HashMap<Student.Major, Boolean> required22A= new HashMap<Student.Major, Boolean>(8);
        required22A.put(Student.Major.LMATBS1, true);
        required22A.put(Student.Major.LMATBS2, true);
        required22A.put(Student.Major.LMATAB1, true);
        required22A.put(Student.Major.LMATAB2, true);
        required22A.put(Student.Major.LAMA, true);
        required22A.put(Student.Major.LMOR, true);
        required22A.put(Student.Major.LMCOMATH, true);
        required22A.put(Student.Major.LMCOBIO, true);
        String[] quartersOffered22A = new String[]{"Fall", "Winter", "Spring"};
        // FIXME: should after be MAT22B? Doesn't say anything on the spreadsheet but I feel like it should be true
        final Course MAT22A = new Course("MAT22A", null, null, required22A, quartersOffered22A, Course.OfferedPattern.ALWAYS);

        //MAT22AL //FIXME: either 22AL or ENG6 is required, but I think ENG6 is now always also required, so maybe shouldn't ever recommend 22AL
        //figure out 22AL vs. ENG06
        HashMap<Student.Major, Boolean> required22AL = new HashMap<Student.Major, Boolean>(8);
        required22AL.put(Student.Major.LMATBS1, false);
        required22AL.put(Student.Major.LMATBS2, false);
        required22AL.put(Student.Major.LMATAB1, false);
        required22AL.put(Student.Major.LMATAB2, false);
        required22AL.put(Student.Major.LAMA, false);
        required22AL.put(Student.Major.LMOR, false);
        required22AL.put(Student.Major.LMCOMATH, false);
        required22AL.put(Student.Major.LMCOBIO, false);
        String[] quartersOffered22AL = new String[]{"Fall", "Winter", "Spring"};

        final Course MAT22AL = new Course("MAT22AL", null, null, required22AL, quartersOffered22AL, Course.OfferedPattern.ALWAYS);

        //ENG06
        HashMap<Student.Major, Boolean> requiredENG06 = new HashMap<Student.Major, Boolean>(8);
        requiredENG06.put(Student.Major.LMATBS1, true);
        requiredENG06.put(Student.Major.LMATBS2, true);
        requiredENG06.put(Student.Major.LMATAB1, true);
        requiredENG06.put(Student.Major.LMATAB2, true);
        requiredENG06.put(Student.Major.LAMA, true);
        requiredENG06.put(Student.Major.LMOR, true);
        requiredENG06.put(Student.Major.LMCOMATH, true);
        requiredENG06.put(Student.Major.LMCOBIO, true);
        String[] quartersOfferedENG06 = new String[]{"Fall", "Winter", "Spring"};

        final Course ENG06 = new Course("ENG06", null, null, requiredENG06, quartersOfferedENG06, Course.OfferedPattern.ALWAYS);

        //MAT67
        HashMap<Student.Major, Boolean> required67 = new HashMap<Student.Major, Boolean>(8);
        required67.put(Student.Major.LMATBS1, false);
        required67.put(Student.Major.LMATBS2, false);
        required67.put(Student.Major.LMATAB1, false);
        required67.put(Student.Major.LMATAB2, false);
        required67.put(Student.Major.LAMA, false);
        required67.put(Student.Major.LMOR, false);
        required67.put(Student.Major.LMCOMATH, false);
        required67.put(Student.Major.LMCOBIO, false);
        String[] quartersOffered67 = new String[]{"Winter"};
        final Course MAT67 = new Course("MAT67", null, null, required67, quartersOffered67, Course.OfferedPattern.ALWAYS);



        //MAT22B
        HashMap<Student.Major, Boolean> required22B= new HashMap<Student.Major, Boolean>(8);
        required22B.put(Student.Major.LMATBS1, true);
        required22B.put(Student.Major.LMATBS2, true);
        required22B.put(Student.Major.LMATAB1, true);
        required22B.put(Student.Major.LMATAB2, true);
        required22B.put(Student.Major.LAMA, true);
        required22B.put(Student.Major.LMOR, true);
        required22B.put(Student.Major.LMCOMATH, true);
        required22B.put(Student.Major.LMCOBIO, true);
        String[] quartersOffered22B = new String[]{"Fall", "Winter", "Spring"};
        final Course MAT22B = new Course("MAT22B", null, null, required22B, quartersOffered22B, Course.OfferedPattern.ALWAYS);

        //MAT108
        HashMap<Student.Major, Boolean> required108 = new HashMap<Student.Major, Boolean>(8);
        required108.put(Student.Major.LMATBS1, true);
        required108.put(Student.Major.LMATBS2, true);
        required108.put(Student.Major.LMATAB1, true);
        required108.put(Student.Major.LMATAB2, true);
        required108.put(Student.Major.LAMA, true);
        required108.put(Student.Major.LMOR, true);
        required108.put(Student.Major.LMCOMATH, true);
        required108.put(Student.Major.LMCOBIO, true);
        String[] quartersOffered108 = new String[]{"Fall", "Winter", "Spring"};
        final Course MAT108 = new Course("MAT108", null, "MAT127A", required108, quartersOffered108, Course.OfferedPattern.ALWAYS);

        //MAT111
        HashMap<Student.Major, Boolean> required111 = new HashMap<Student.Major, Boolean>(8);
        required111.put(Student.Major.LMATBS1, false);
        required111.put(Student.Major.LMATBS2, true);
        required111.put(Student.Major.LMATAB1, false);
        required111.put(Student.Major.LMATAB2, true);
        required111.put(Student.Major.LAMA, false);
        required111.put(Student.Major.LMOR, false);
        required111.put(Student.Major.LMCOMATH, false);
        required111.put(Student.Major.LMCOBIO, false);
        String[] quartersOffered111 = new String[]{"Winter"};
        ArrayList<String> interests111 = new ArrayList<String>(1);
        interests111.add("Teaching");
        final Course MAT111 = new Course("MAT111", interests111, null, required111, quartersOffered111, Course.OfferedPattern.ALWAYS);

        //MAT114
        HashMap<Student.Major, Boolean> required114 = new HashMap<Student.Major, Boolean>(8);
        required114.put(Student.Major.LMATBS1, false);
        required114.put(Student.Major.LMATBS2, false);
        required114.put(Student.Major.LMATAB1, false);
        required114.put(Student.Major.LMATAB2, false);
        required114.put(Student.Major.LAMA, false);
        required114.put(Student.Major.LMOR, false);
        required114.put(Student.Major.LMCOMATH, false);
        required114.put(Student.Major.LMCOBIO, false);
        String[] quartersOffered114 = new String[]{"Winter"};
        ArrayList<String> interests114 = new ArrayList<String>(1);
        interests114.add("Geometry");
        final Course MAT114 = new Course("MAT114", interests114, null, required114, quartersOffered114, Course.OfferedPattern.EVENALTERNATE);

        //MAT115A
        HashMap<Student.Major, Boolean> required115A = new HashMap<Student.Major, Boolean>(8);
        required115A.put(Student.Major.LMATBS1, false);
        required115A.put(Student.Major.LMATBS2, true);
        required115A.put(Student.Major.LMATAB1, false);
        required115A.put(Student.Major.LMATAB2, true);
        required115A.put(Student.Major.LAMA, false);
        required115A.put(Student.Major.LMOR, false);
        required115A.put(Student.Major.LMCOMATH, false);
        required115A.put(Student.Major.LMCOBIO, false);
        String[] quartersOffered115A = new String[]{"Fall"};
        ArrayList<String> interests115A = new ArrayList<String>(1);
        interests115A.add("Teaching");
        final Course MAT115A = new Course("MAT115A", interests115A, "MAT115B", required115A, quartersOffered115A, Course.OfferedPattern.ALWAYS);

        //MAT115B
        HashMap<Student.Major, Boolean> required115B = new HashMap<Student.Major, Boolean>(8);
        required115B.put(Student.Major.LMATBS1, false);
        required115B.put(Student.Major.LMATBS2, false);
        required115B.put(Student.Major.LMATAB1, false);
        required115B.put(Student.Major.LMATAB2, false);
        required115B.put(Student.Major.LAMA, false);
        required115B.put(Student.Major.LMOR, false);
        required115B.put(Student.Major.LMCOMATH, false);
        required115B.put(Student.Major.LMCOBIO, false);
        String[] quartersOffered115B = new String[]{"Winter"};
        ArrayList<String> interests115B = new ArrayList<String>(1);
        interests115B.add("Teaching");
        final Course MAT115B = new Course("MAT115B", interests115B, null, required115B, quartersOffered115B, Course.OfferedPattern.EVENALTERNATE);

        //MAT116
        HashMap<Student.Major, Boolean> required116 = new HashMap<Student.Major, Boolean>(8);
        required116.put(Student.Major.LMATBS1, false);
        required116.put(Student.Major.LMATBS2, false);
        required116.put(Student.Major.LMATAB1, false);
        required116.put(Student.Major.LMATAB2, false);
        required116.put(Student.Major.LAMA, false);
        required116.put(Student.Major.LMOR, false);
        required116.put(Student.Major.LMCOMATH, false);
        required116.put(Student.Major.LMCOBIO, false);
        String[] quartersOffered116 = new String[]{"Spring"};
        ArrayList<String> interests116 = new ArrayList<String>(1);
        interests116.add("Geometry");
        final Course MAT116 = new Course("MAT116", interests116, null, required116, quartersOffered116, Course.OfferedPattern.ODDALTERNATE);


        //MAT118A
        HashMap<Student.Major, Boolean> required118A = new HashMap<Student.Major, Boolean>(8);
        required118A.put(Student.Major.LMATBS1, false);
        required118A.put(Student.Major.LMATBS2, false);
        required118A.put(Student.Major.LMATAB1, false);
        required118A.put(Student.Major.LMATAB2, false);
        required118A.put(Student.Major.LAMA, false);
        required118A.put(Student.Major.LMOR, false);
        required118A.put(Student.Major.LMCOMATH, false);
        required118A.put(Student.Major.LMCOBIO, false);
        String[] quartersOffered118A = new String[]{"Fall"};
        ArrayList<String> interests118A = new ArrayList<String>(1);
        interests118A.add("Physics");
        final Course MAT118A = new Course("MAT118A", interests118A, "MAT118B", required118A, quartersOffered118A, Course.OfferedPattern.ALWAYS);

        //MAT118B
        HashMap<Student.Major, Boolean> required118B = new HashMap<Student.Major, Boolean>(8);
        required118B.put(Student.Major.LMATBS1, false);
        required118B.put(Student.Major.LMATBS2, false);
        required118B.put(Student.Major.LMATAB1, false);
        required118B.put(Student.Major.LMATAB2, false);
        required118B.put(Student.Major.LAMA, false);
        required118B.put(Student.Major.LMOR, false);
        required118B.put(Student.Major.LMCOMATH, false);
        required118B.put(Student.Major.LMCOBIO, false);
        String[] quartersOffered118B = new String[]{"Winter"};
        ArrayList<String> interests118B = new ArrayList<String>(1);
        interests118B.add("Physics");
        final Course MAT118B = new Course("MAT118B", interests118B, null, required118B, quartersOffered118B, Course.OfferedPattern.ODDALTERNATE);

        //MAT119A
        HashMap<Student.Major, Boolean> required119A = new HashMap<Student.Major, Boolean>(8);
        required119A.put(Student.Major.LMATBS1, false);
        required119A.put(Student.Major.LMATBS2, false);
        required119A.put(Student.Major.LMATAB1, false);
        required119A.put(Student.Major.LMATAB2, false);
        required119A.put(Student.Major.LAMA, true);
        required119A.put(Student.Major.LMOR, false);
        required119A.put(Student.Major.LMCOMATH, false);
        required119A.put(Student.Major.LMCOBIO, false);
        String[] quartersOffered119A = new String[]{"Fall", "Winter"};
        ArrayList<String> interests119A = new ArrayList<String>(1);
        interests119A.add("Physics");
        final Course MAT119A = new Course("MAT119A", interests119A, "MAT119B", required119A, quartersOffered119A, Course.OfferedPattern.ALWAYS);

        //MAT119B
        HashMap<Student.Major, Boolean> required119B = new HashMap<Student.Major, Boolean>(8);
        required119B.put(Student.Major.LMATBS1, false);
        required119B.put(Student.Major.LMATBS2, false);
        required119B.put(Student.Major.LMATAB1, false);
        required119B.put(Student.Major.LMATAB2, false);
        required119B.put(Student.Major.LAMA, false);
        required119B.put(Student.Major.LMOR, false);
        required119B.put(Student.Major.LMCOMATH, false);
        required119B.put(Student.Major.LMCOBIO, false);
        String[] quartersOffered119B = new String[]{"Spring"};
        ArrayList<String> interests119B = new ArrayList<String>(1);
        interests119B.add("Physics");
        final Course MAT119B = new Course("MAT119B", interests119B, null, required119B, quartersOffered119B, Course.OfferedPattern.EVENALTERNATE);

        //MAT124
        HashMap<Student.Major, Boolean> required124 = new HashMap<Student.Major, Boolean>(8);
        required124.put(Student.Major.LMATBS1, false);
        required124.put(Student.Major.LMATBS2, false);
        required124.put(Student.Major.LMATAB1, false);
        required124.put(Student.Major.LMATAB2, false);
        required124.put(Student.Major.LAMA, false);
        required124.put(Student.Major.LMOR, false);
        required124.put(Student.Major.LMCOMATH, false);
        required124.put(Student.Major.LMCOBIO, true);
        String[] quartersOffered124 = new String[]{"Spring"};
        ArrayList<String> interests124 = new ArrayList<String>(1);
        interests124.add("Biology");
        final Course MAT124 = new Course("MAT124", interests124, null, required124, quartersOffered124, Course.OfferedPattern.ODDALTERNATE);

        //MAT127A
        HashMap<Student.Major, Boolean> required127A = new HashMap<Student.Major, Boolean>(8);
        required127A.put(Student.Major.LMATBS1, true);
        required127A.put(Student.Major.LMATBS2, true);
        required127A.put(Student.Major.LMATAB1, true);
        required127A.put(Student.Major.LMATAB2, true);
        required127A.put(Student.Major.LAMA, true);
        required127A.put(Student.Major.LMOR, true);
        required127A.put(Student.Major.LMCOMATH, true);
        required127A.put(Student.Major.LMCOBIO, true);
        String[] quartersOffered127A = new String[]{"Fall", "Winter", "Spring"};
        final Course MAT127A = new Course("MAT127A", null, "MAT127B", required127A, quartersOffered127A, Course.OfferedPattern.ALWAYS);

        //MAT127B
        HashMap<Student.Major, Boolean> required127B = new HashMap<Student.Major, Boolean>(8);
        required127B.put(Student.Major.LMATBS1, true);
        required127B.put(Student.Major.LMATBS2, true);
        required127B.put(Student.Major.LMATAB1, true);
        required127B.put(Student.Major.LMATAB2, true);
        required127B.put(Student.Major.LAMA, true);
        required127B.put(Student.Major.LMOR, true);
        required127B.put(Student.Major.LMCOMATH, true);
        required127B.put(Student.Major.LMCOBIO, true);
        String[] quartersOffered127B = new String[]{"Fall", "Winter", "Spring"};
        final Course MAT127B = new Course("MAT127B", null, "MAT127C", required127B, quartersOffered127B, Course.OfferedPattern.ALWAYS);

        //MAT127C
        HashMap<Student.Major, Boolean> required127C = new HashMap<Student.Major, Boolean>(8);
        required127C.put(Student.Major.LMATBS1, true);
        required127C.put(Student.Major.LMATBS2, true);
        required127C.put(Student.Major.LMATAB1, true);
        required127C.put(Student.Major.LMATAB2, true);
        required127C.put(Student.Major.LAMA, true);
        required127C.put(Student.Major.LMOR, true);
        required127C.put(Student.Major.LMCOMATH, true);
        required127C.put(Student.Major.LMCOBIO, true);
        String[] quartersOffered127C = new String[]{"Fall", "Winter", "Spring"};
        final Course MAT127C = new Course("MAT127C", null, null, required127C, quartersOffered127C, Course.OfferedPattern.ALWAYS);

        //MAT128A //FIXME: LMOR needs to take either 128A, 128B, or 128C; LAMA needs to take two of 128A, 128B, 128C
        HashMap<Student.Major, Boolean> required128A = new HashMap<Student.Major, Boolean>(8);
        required128A.put(Student.Major.LMATBS1, false);
        required128A.put(Student.Major.LMATBS2, false);
        required128A.put(Student.Major.LMATAB1, false);
        required128A.put(Student.Major.LMATAB2, false);
        required128A.put(Student.Major.LAMA, true);
        required128A.put(Student.Major.LMOR, true);
        required128A.put(Student.Major.LMCOMATH, true);
        required128A.put(Student.Major.LMCOBIO, true);
        String[] quartersOffered128A = new String[]{"Fall"};
        ArrayList<String> interests128A = new ArrayList<String>(1);
        interests128A.add("Computers");
        final Course MAT128A = new Course("MAT128A", interests128A, null, required128A, quartersOffered128A, Course.OfferedPattern.ALWAYS);


        //MAT128B
        HashMap<Student.Major, Boolean> required128B = new HashMap<Student.Major, Boolean>(8);
        required128B.put(Student.Major.LMATBS1, false);
        required128B.put(Student.Major.LMATBS2, false);
        required128B.put(Student.Major.LMATAB1, false);
        required128B.put(Student.Major.LMATAB2, false);
        required128B.put(Student.Major.LAMA, true);
        required128B.put(Student.Major.LMOR, true); //because LMOR can just take 128A as their one class
        required128B.put(Student.Major.LMCOMATH, true);
        required128B.put(Student.Major.LMCOBIO, true);
        String[] quartersOffered128B = new String[]{"Winter"};
        ArrayList<String> interests128B = new ArrayList<String>(1);
        interests128B.add("Computers");
        final Course MAT128B = new Course("MAT128B", interests128B, null, required128B, quartersOffered128B, Course.OfferedPattern.ALWAYS);

        //MAT128C
        HashMap<Student.Major, Boolean> required128C = new HashMap<Student.Major, Boolean>(8);
        required128C.put(Student.Major.LMATBS1, false);
        required128C.put(Student.Major.LMATBS2, false);
        required128C.put(Student.Major.LMATAB1, false);
        required128C.put(Student.Major.LMATAB2, false);
        required128C.put(Student.Major.LAMA, true); //because LAMA can take 128A,B as their two classes
        required128C.put(Student.Major.LMOR, true); //because LMOR can just take 128A as their one class
        required128C.put(Student.Major.LMCOMATH, true);
        required128C.put(Student.Major.LMCOBIO, true);
        String[] quartersOffered128C = new String[]{"Spring"};
        ArrayList<String> interests128C = new ArrayList<String>(1);
        interests128C.add("Computers");
        final Course MAT128C = new Course("MAT128C", interests128C, null, required128C, quartersOffered128C, Course.OfferedPattern.ALWAYS);

        //MAT129
        HashMap<Student.Major, Boolean> required129 = new HashMap<Student.Major, Boolean>(8);
        required129.put(Student.Major.LMATBS1, false);
        required129.put(Student.Major.LMATBS2, false);
        required129.put(Student.Major.LMATAB1, false);
        required129.put(Student.Major.LMATAB2, false);
        required129.put(Student.Major.LAMA, false);
        required129.put(Student.Major.LMOR, false);
        required129.put(Student.Major.LMCOMATH, false);
        required129.put(Student.Major.LMCOBIO, false);
        String[] quartersOffered129 = new String[]{"Fall"};
        final Course MAT129 = new Course("MAT129", null, null, required129, quartersOffered129, Course.OfferedPattern.ODDALTERNATE);


        //MAT133
        HashMap<Student.Major, Boolean> required133 = new HashMap<Student.Major, Boolean>(8);
        required133.put(Student.Major.LMATBS1, false);
        required133.put(Student.Major.LMATBS2, false);
        required133.put(Student.Major.LMATAB1, false);
        required133.put(Student.Major.LMATAB2, false);
        required133.put(Student.Major.LAMA, false);
        required133.put(Student.Major.LMOR, false);
        required133.put(Student.Major.LMCOMATH, false);
        required133.put(Student.Major.LMCOBIO, false);
        String[] quartersOffered133 = new String[]{"Spring"};
        ArrayList<String> interests133 = new ArrayList<String>(1);
        interests133.add("Finance");
        final Course MAT133 = new Course("MAT133", interests133, null, required129, quartersOffered129, Course.OfferedPattern.ALWAYS);

        //MAT135A //FIXME: only want after to be 135B for LMOR b/c no one else has to take it? //alternatively could have an "after && required check"
        HashMap<Student.Major, Boolean> required135A = new HashMap<Student.Major, Boolean>(8);
        required135A.put(Student.Major.LMATBS1, true);
        required135A.put(Student.Major.LMATBS2, true);
        required135A.put(Student.Major.LMATAB1, true);
        required135A.put(Student.Major.LMATAB2, true);
        required135A.put(Student.Major.LAMA, true);
        required135A.put(Student.Major.LMOR, true);
        required135A.put(Student.Major.LMCOMATH, true);
        required135A.put(Student.Major.LMCOBIO, true);
        String[] quartersOffered135A = new String[]{"Fall", "Winter", "Spring"};
        final Course MAT135A = new Course("MAT135A", null, "MAT135B", required135A, quartersOffered135A, Course.OfferedPattern.ALWAYS);

        //MAT135B
        HashMap<Student.Major, Boolean> required135B = new HashMap<Student.Major, Boolean>(8);
        required135B.put(Student.Major.LMATBS1, false);
        required135B.put(Student.Major.LMATBS2, false);
        required135B.put(Student.Major.LMATAB1, false);
        required135B.put(Student.Major.LMATAB2, false);
        required135B.put(Student.Major.LAMA, false);
        required135B.put(Student.Major.LMOR, true);
        required135B.put(Student.Major.LMCOMATH, false);
        required135B.put(Student.Major.LMCOBIO, false);
        String[] quartersOffered135B = new String[]{"Spring"};
        final Course MAT135B = new Course("MAT135B", null, "MAT135B", required135B, quartersOffered135B, Course.OfferedPattern.ALWAYS);

        //MAT141
        HashMap<Student.Major, Boolean> required141 = new HashMap<Student.Major, Boolean>(8);
        required141.put(Student.Major.LMATBS1, false);
        required141.put(Student.Major.LMATBS2, true);
        required141.put(Student.Major.LMATAB1, false);
        required141.put(Student.Major.LMATAB2, true);
        required141.put(Student.Major.LAMA, false);
        required141.put(Student.Major.LMOR, false);
        required141.put(Student.Major.LMCOMATH, false);
        required141.put(Student.Major.LMCOBIO, false);
        String[] quartersOffered141 = new String[]{"Winter", "Spring"};
        ArrayList<String> interests141 = new ArrayList<String>(2);
        interests141.add("Geometry");
        interests141.add("Teaching");
        final Course MAT141 = new Course("MAT141", interests141, null, required141, quartersOffered141, Course.OfferedPattern.ALWAYS);

        //MAT145
        HashMap<Student.Major, Boolean> required145 = new HashMap<Student.Major, Boolean>(8);
        required145.put(Student.Major.LMATBS1, false);
        required145.put(Student.Major.LMATBS2, false);
        required145.put(Student.Major.LMATAB1, false);
        required145.put(Student.Major.LMATAB2, false);
        required145.put(Student.Major.LAMA, false);
        required145.put(Student.Major.LMOR, false);
        required145.put(Student.Major.LMCOMATH, false);
        required145.put(Student.Major.LMCOBIO, false);
        String[] quartersOffered145 = new String[]{"Fall", "Winter", "Spring"};
        ArrayList<String> interests145 = new ArrayList<String>(1);
        interests145.add("Teaching");
        final Course MAT145 = new Course("MAT145", interests145, "MAT146", required145, quartersOffered145, Course.OfferedPattern.ALWAYS);


        //MAT146
        HashMap<Student.Major, Boolean> required146 = new HashMap<Student.Major, Boolean>(8);
        required146.put(Student.Major.LMATBS1, false);
        required146.put(Student.Major.LMATBS2, false);
        required146.put(Student.Major.LMATAB1, false);
        required146.put(Student.Major.LMATAB2, false);
        required146.put(Student.Major.LAMA, false);
        required146.put(Student.Major.LMOR, false);
        required146.put(Student.Major.LMCOMATH, false);
        required146.put(Student.Major.LMCOBIO, false);
        String[] quartersOffered146 = new String[]{"Spring"};

        final Course MAT146 = new Course("MAT146", null, null, required146, quartersOffered146, Course.OfferedPattern.EVENALTERNATE);

        //MAT147
        HashMap<Student.Major, Boolean> required147 = new HashMap<Student.Major, Boolean>(8);
        required147.put(Student.Major.LMATBS1, false);
        required147.put(Student.Major.LMATBS2, false);
        required147.put(Student.Major.LMATAB1, false);
        required147.put(Student.Major.LMATAB2, false);
        required147.put(Student.Major.LAMA, false);
        required147.put(Student.Major.LMOR, false);
        required147.put(Student.Major.LMCOMATH, false);
        required147.put(Student.Major.LMCOBIO, false);
        String[] quartersOffered147 = new String[]{"Winter"};
        ArrayList<String> interests147 = new ArrayList<String>(1);
        interests147.add("Abstract");

        final Course MAT147 = new Course("MAT147", interests147, null, required147, quartersOffered147, Course.OfferedPattern.ALWAYS);


        //MAT148
        HashMap<Student.Major, Boolean> required148 = new HashMap<Student.Major, Boolean>(8);
        required148.put(Student.Major.LMATBS1, false);
        required148.put(Student.Major.LMATBS2, false);
        required148.put(Student.Major.LMATAB1, false);
        required148.put(Student.Major.LMATAB2, false);
        required148.put(Student.Major.LAMA, false);
        required148.put(Student.Major.LMOR, false);
        required148.put(Student.Major.LMCOMATH, false);
        required148.put(Student.Major.LMCOBIO, false);
        String[] quartersOffered148 = new String[]{"Winter"};

        final Course MAT148 = new Course("MAT148", null, null, required148, quartersOffered148, Course.OfferedPattern.ODDALTERNATE);

        //MAT150A //FIXME: Want to wait until 3rd year to take, haven't entirely figured out how yet (could try and place it year before they graduate)
        HashMap<Student.Major, Boolean> required150A = new HashMap<Student.Major, Boolean>(8);
        required150A.put(Student.Major.LMATBS1, true);
        required150A.put(Student.Major.LMATBS2, true);
        required150A.put(Student.Major.LMATAB1, true);
        required150A.put(Student.Major.LMATAB2, true);
        required150A.put(Student.Major.LAMA, true);
        required150A.put(Student.Major.LMOR, true);
        required150A.put(Student.Major.LMCOMATH, true);
        required150A.put(Student.Major.LMCOBIO, true);
        String[] quartersOffered150A = new String[]{"Fall", "Winter"};

        final Course MAT150A = new Course("MAT150A", null, "MAT150B", required150A, quartersOffered150A, Course.OfferedPattern.ALWAYS);

        //MAT150B
        HashMap<Student.Major, Boolean> required150B = new HashMap<Student.Major, Boolean>(8);
        required150B.put(Student.Major.LMATBS1, true);
        required150B.put(Student.Major.LMATBS2, false);
        required150B.put(Student.Major.LMATAB1, false);
        required150B.put(Student.Major.LMATAB2, false);
        required150B.put(Student.Major.LAMA, false);
        required150B.put(Student.Major.LMOR, false);
        required150B.put(Student.Major.LMCOMATH, false);
        required150B.put(Student.Major.LMCOBIO, false);
        String[] quartersOffered150B = new String[]{"Winter"};
        ArrayList<String> interests150B = new ArrayList<String>(1);
        interests150B.add("Abstract");

        final Course MAT150B = new Course("MAT150B", interests150B, "MAT150C", required150B, quartersOffered150B, Course.OfferedPattern.ALWAYS);

        //MAT150C
        HashMap<Student.Major, Boolean> required150C = new HashMap<Student.Major, Boolean>(8);
        required150C.put(Student.Major.LMATBS1, true);
        required150C.put(Student.Major.LMATBS2, false);
        required150C.put(Student.Major.LMATAB1, false);
        required150C.put(Student.Major.LMATAB2, false);
        required150C.put(Student.Major.LAMA, false);
        required150C.put(Student.Major.LMOR, false);
        required150C.put(Student.Major.LMCOMATH, false);
        required150C.put(Student.Major.LMCOBIO, false);
        String[] quartersOffered150C = new String[]{"Spring"};
        ArrayList<String> interests150C = new ArrayList<String>(1);
        interests150C.add("Abstract");

        final Course MAT150C = new Course("MAT150C", interests150C, "MAT150C", required150C, quartersOffered150C, Course.OfferedPattern.ALWAYS);

        //MAT160
        HashMap<Student.Major, Boolean> required160 = new HashMap<Student.Major, Boolean>(8);
        required160.put(Student.Major.LMATBS1, false);
        required160.put(Student.Major.LMATBS2, false);
        required160.put(Student.Major.LMATAB1, false);
        required160.put(Student.Major.LMATAB2, false);
        required160.put(Student.Major.LAMA, false);
        required160.put(Student.Major.LMOR, true);
        required160.put(Student.Major.LMCOMATH, false);
        required160.put(Student.Major.LMCOBIO, false);
        String[] quartersOffered160 = new String[]{"Spring"};
        ArrayList<String> interests160 = new ArrayList<String>(2);
        interests160.add("Computers");
        interests160.add("Data Analysis");

        final Course MAT160 = new Course("MAT160", interests160, null, required160, quartersOffered160, Course.OfferedPattern.ALWAYS);

        //MAT165
        HashMap<Student.Major, Boolean> required165 = new HashMap<Student.Major, Boolean>(8);
        required165.put(Student.Major.LMATBS1, false);
        required165.put(Student.Major.LMATBS2, false);
        required165.put(Student.Major.LMATAB1, false);
        required165.put(Student.Major.LMATAB2, false);
        required165.put(Student.Major.LAMA, false);
        required165.put(Student.Major.LMOR, false);
        required165.put(Student.Major.LMCOMATH, false);
        required165.put(Student.Major.LMCOBIO, false);
        String[] quartersOffered165 = new String[]{"Fall"};
        ArrayList<String> interests165 = new ArrayList<String>(1);
        interests165.add("Computers");

        final Course MAT165 = new Course("MAT165", interests165, null, required165, quartersOffered165, Course.OfferedPattern.EVENALTERNATE);


        //MAT167
        HashMap<Student.Major, Boolean> required167 = new HashMap<Student.Major, Boolean>(8);
        required167.put(Student.Major.LMATBS1, false);
        required167.put(Student.Major.LMATBS2, false);
        required167.put(Student.Major.LMATAB1, false);
        required167.put(Student.Major.LMATAB2, false);
        required167.put(Student.Major.LAMA, false);
        required167.put(Student.Major.LMOR, true); //because 160 is required and 167 is prereq for 160
        required167.put(Student.Major.LMCOMATH, false);
        required167.put(Student.Major.LMCOBIO, false);
        String[] quartersOffered167 = new String[]{"Fall", "Winter"};
        ArrayList<String> interests167 = new ArrayList<String>(2);
        interests167.add("Teaching");
        interests167.add("Data Analysis");

        final Course MAT167 = new Course("MAT167", interests167, null, required167, quartersOffered167, Course.OfferedPattern.ALWAYS);

        //MAT168
        HashMap<Student.Major, Boolean> required168 = new HashMap<Student.Major, Boolean>(8);
        required168.put(Student.Major.LMATBS1, false);
        required168.put(Student.Major.LMATBS2, false);
        required168.put(Student.Major.LMATAB1, false);
        required168.put(Student.Major.LMATAB2, false);
        required168.put(Student.Major.LAMA, false);
        required168.put(Student.Major.LMOR, true);
        required168.put(Student.Major.LMCOMATH, true);
        required168.put(Student.Major.LMCOBIO, false);
        String[] quartersOffered168 = new String[]{"Fall", "Winter"};
        ArrayList<String> interests168 = new ArrayList<String>(2);
        interests168.add("Computers");
        interests168.add("Data Analysis");

        final Course MAT168 = new Course("MAT168", interests168, null, required168, quartersOffered168, Course.OfferedPattern.ALWAYS);

        //MAT180 //FIXME: required depends on other classes (189, 192, 194)
        HashMap<Student.Major, Boolean> required180 = new HashMap<Student.Major, Boolean>(8);
        required180.put(Student.Major.LMATBS1, true);
        required180.put(Student.Major.LMATBS2, true);
        required180.put(Student.Major.LMATAB1, true);
        required180.put(Student.Major.LMATAB2, true);
        required180.put(Student.Major.LAMA, false);
        required180.put(Student.Major.LMOR, false);
        required180.put(Student.Major.LMCOMATH, false);
        required180.put(Student.Major.LMCOBIO, false);
        String[] quartersOffered180 = new String[]{"Fall", "Winter", "Spring"};

        final Course MAT180 = new Course("MAT180", null, null, required180, quartersOffered180, Course.OfferedPattern.ALWAYS);

        //MAT185A //FIXME: Wait until senior year to take
        HashMap<Student.Major, Boolean> required185A = new HashMap<Student.Major, Boolean>(8);
        required185A.put(Student.Major.LMATBS1, true);
        required185A.put(Student.Major.LMATBS2, false);
        required185A.put(Student.Major.LMATAB1, false);
        required185A.put(Student.Major.LMATAB2, false);
        required185A.put(Student.Major.LAMA, true);
        required185A.put(Student.Major.LMOR, false);
        required185A.put(Student.Major.LMCOMATH, false);
        required185A.put(Student.Major.LMCOBIO, false);
        String[] quartersOffered185A = new String[]{"Fall", "Spring"};

        final Course MAT185A = new Course("MAT185A", null, "MAT185B", required185A, quartersOffered185A, Course.OfferedPattern.ALWAYS);

        //MAT185B
        HashMap<Student.Major, Boolean> required185B = new HashMap<Student.Major, Boolean>(8);
        required185B.put(Student.Major.LMATBS1, true);
        required185B.put(Student.Major.LMATBS2, false);
        required185B.put(Student.Major.LMATAB1, false);
        required185B.put(Student.Major.LMATAB2, false);
        required185B.put(Student.Major.LAMA, true);
        required185B.put(Student.Major.LMOR, false);
        required185B.put(Student.Major.LMCOMATH, false);
        required185B.put(Student.Major.LMCOBIO, false);
        String[] quartersOffered185B = new String[]{"Spring"};

        final Course MAT185B = new Course("MAT185B", null, null, required185B, quartersOffered185B, Course.OfferedPattern.ODDALTERNATE);

        //MAT189
        HashMap<Student.Major, Boolean> required189 = new HashMap<Student.Major, Boolean>(8);
        required189.put(Student.Major.LMATBS1, false);
        required189.put(Student.Major.LMATBS2, false);
        required189.put(Student.Major.LMATAB1, false);
        required189.put(Student.Major.LMATAB2, false);
        required189.put(Student.Major.LAMA, false);
        required189.put(Student.Major.LMOR, false);
        required189.put(Student.Major.LMCOMATH, false);
        required189.put(Student.Major.LMCOBIO, false);
        String[] quartersOffered189 = new String[]{"Spring"};

        final Course MAT189 = new Course("MAT189", null, null, required189, quartersOffered189, Course.OfferedPattern.ALWAYS);

        //ECS32A //FIXME: only take after first quarter
        HashMap<Student.Major, Boolean> requiredECS32A = new HashMap<Student.Major, Boolean>(8);
        requiredECS32A.put(Student.Major.LMATBS1, false);
        requiredECS32A.put(Student.Major.LMATBS2, false);
        requiredECS32A.put(Student.Major.LMATAB1, false);
        requiredECS32A.put(Student.Major.LMATAB2, false);
        requiredECS32A.put(Student.Major.LAMA, true);
        requiredECS32A.put(Student.Major.LMOR, false);
        requiredECS32A.put(Student.Major.LMCOMATH, true);
        requiredECS32A.put(Student.Major.LMCOBIO, true);
        String[] quartersOfferedECS32A = new String[]{"Fall", "Winter", "Spring"};

        final Course ECS32A = new Course("ECS32A", null, null, requiredECS32A, quartersOfferedECS32A, Course.OfferedPattern.ALWAYS);

        //PHY7A //FIXME: This OR Stats class (see spreadsheet)
        HashMap<Student.Major, Boolean> requiredPHY7A = new HashMap<Student.Major, Boolean>(8);
        requiredPHY7A.put(Student.Major.LMATBS1, false);
        requiredPHY7A.put(Student.Major.LMATBS2, false);
        requiredPHY7A.put(Student.Major.LMATAB1, false);
        requiredPHY7A.put(Student.Major.LMATAB2, false);
        requiredPHY7A.put(Student.Major.LAMA, false);
        requiredPHY7A.put(Student.Major.LMOR, false);
        requiredPHY7A.put(Student.Major.LMCOMATH, false);
        requiredPHY7A.put(Student.Major.LMCOBIO, false);
        String[] quartersOfferedPHY7A = new String[]{"Fall", "Winter", "Spring"}; //doesn't say on spreadsheet

        final Course PHY7A = new Course("PHY7A", null, null, requiredPHY7A, quartersOfferedPHY7A, Course.OfferedPattern.ALWAYS);


        //PHY9A
        HashMap<Student.Major, Boolean> requiredPHY9A = new HashMap<Student.Major, Boolean>(8);
        requiredPHY9A.put(Student.Major.LMATBS1, true);
        requiredPHY9A.put(Student.Major.LMATBS2, false);
        requiredPHY9A.put(Student.Major.LMATAB1, false);
        requiredPHY9A.put(Student.Major.LMATAB2, false);
        requiredPHY9A.put(Student.Major.LAMA, false);
        requiredPHY9A.put(Student.Major.LMOR, false);
        requiredPHY9A.put(Student.Major.LMCOMATH, false);
        requiredPHY9A.put(Student.Major.LMCOBIO, false);
        String[] quartersOfferedPHY9A = new String[]{"Fall", "Spring"};

        final Course PHY9A = new Course("PHY9A", null, null, requiredPHY9A, quartersOfferedPHY9A, Course.OfferedPattern.ALWAYS);


        //ECN1A
        HashMap<Student.Major, Boolean> requiredECN1A = new HashMap<Student.Major, Boolean>(8);
        requiredECN1A.put(Student.Major.LMATBS1, false);
        requiredECN1A.put(Student.Major.LMATBS2, false);
        requiredECN1A.put(Student.Major.LMATAB1, false);
        requiredECN1A.put(Student.Major.LMATAB2, false);
        requiredECN1A.put(Student.Major.LAMA, true); //for debugging (without adding PHY/CHE/BIS support)
        requiredECN1A.put(Student.Major.LMOR, true);
        requiredECN1A.put(Student.Major.LMCOMATH, false);
        requiredECN1A.put(Student.Major.LMCOBIO, false);
        String[] quartersOfferedECN1A = new String[]{"Fall", "Winter", "Spring"};

        final Course ECN1A = new Course("ECN1A", null, null, requiredECN1A, quartersOfferedECN1A, Course.OfferedPattern.ALWAYS);

        //ECN1B
        HashMap<Student.Major, Boolean> requiredECN1B = new HashMap<Student.Major, Boolean>(8);
        requiredECN1B.put(Student.Major.LMATBS1, false);
        requiredECN1B.put(Student.Major.LMATBS2, false);
        requiredECN1B.put(Student.Major.LMATAB1, false);
        requiredECN1B.put(Student.Major.LMATAB2, false);
        requiredECN1B.put(Student.Major.LAMA, true); //for debugging (without adding PHY/CHE/BIS support)
        requiredECN1B.put(Student.Major.LMOR, true);
        requiredECN1B.put(Student.Major.LMCOMATH, false);
        requiredECN1B.put(Student.Major.LMCOBIO, false);
        String[] quartersOfferedECN1B = new String[]{"Fall", "Winter", "Spring"};

        final Course ECN1B = new Course("ECN1B", null, null, requiredECN1B, quartersOfferedECN1B, Course.OfferedPattern.ALWAYS);

        //STA32
        HashMap<Student.Major, Boolean> requiredSTA32 = new HashMap<Student.Major, Boolean>(8);
        requiredSTA32.put(Student.Major.LMATBS1, false);
        requiredSTA32.put(Student.Major.LMATBS2, false);
        requiredSTA32.put(Student.Major.LMATAB1, false);
        requiredSTA32.put(Student.Major.LMATAB2, false);
        requiredSTA32.put(Student.Major.LAMA, false);
        requiredSTA32.put(Student.Major.LMOR, true);
        requiredSTA32.put(Student.Major.LMCOMATH, false);
        requiredSTA32.put(Student.Major.LMCOBIO, false);
        String[] quartersOfferedSTA32 = new String[]{"Fall", "Winter", "Spring"};

        final Course STA32 = new Course("STA32", null, null, requiredSTA32, quartersOfferedSTA32, Course.OfferedPattern.ALWAYS);

        //STA100
        HashMap<Student.Major, Boolean> requiredSTA100 = new HashMap<Student.Major, Boolean>(8);
        requiredSTA100.put(Student.Major.LMATBS1, false);
        requiredSTA100.put(Student.Major.LMATBS2, false);
        requiredSTA100.put(Student.Major.LMATAB1, false);
        requiredSTA100.put(Student.Major.LMATAB2, false);
        requiredSTA100.put(Student.Major.LAMA, false);
        requiredSTA100.put(Student.Major.LMOR, false); //either this or STA32 for LMOR
        requiredSTA100.put(Student.Major.LMCOMATH, false);
        requiredSTA100.put(Student.Major.LMCOBIO, false);
        String[] quartersOfferedSTA100 = new String[]{"Fall", "Winter", "Spring"}; //doesn't say on spreadsheet

        final Course STA100 = new Course("STA100", null, null, requiredSTA100, quartersOfferedSTA100, Course.OfferedPattern.ALWAYS);





        //have to store class data in data structure
        ArrayList<Course> coursesOffered = new ArrayList<Course>(60); //there are ~50 classes, 60 is an approximation
        coursesOffered.add(MAT21A);
        coursesOffered.add(MAT21B);
        coursesOffered.add(MAT21C);
        coursesOffered.add(MAT21D);
        coursesOffered.add(MAT22A);
        coursesOffered.add(MAT22AL);
        coursesOffered.add(MAT67);
        coursesOffered.add(MAT22B);
        coursesOffered.add(MAT108);
        coursesOffered.add(MAT111);
        coursesOffered.add(MAT114);
        coursesOffered.add(MAT115A);
        coursesOffered.add(MAT115B);
        coursesOffered.add(MAT116);
        coursesOffered.add(MAT118A);
        coursesOffered.add(MAT118B);
        coursesOffered.add(MAT119A);
        coursesOffered.add(MAT119B);
        coursesOffered.add(MAT124);
        coursesOffered.add(MAT127A);
        coursesOffered.add(MAT127B);
        coursesOffered.add(MAT127C);
        coursesOffered.add(MAT128A);
        coursesOffered.add(MAT128B);
        coursesOffered.add(MAT128C);
        coursesOffered.add(MAT129);
        coursesOffered.add(MAT133);
        coursesOffered.add(MAT135A);
        coursesOffered.add(MAT135B);
        coursesOffered.add(MAT141);
        coursesOffered.add(MAT145);
        coursesOffered.add(MAT146);
        coursesOffered.add(MAT147);
        coursesOffered.add(MAT148);
        coursesOffered.add(MAT150A);
        coursesOffered.add(MAT150B);
        coursesOffered.add(MAT150C);
        coursesOffered.add(MAT160);
        coursesOffered.add(MAT165);
        coursesOffered.add(MAT167);
        coursesOffered.add(MAT168);
        coursesOffered.add(MAT180);
        coursesOffered.add(MAT185A);
        coursesOffered.add(MAT185B);
        coursesOffered.add(MAT189);
        coursesOffered.add(ECS32A);
        coursesOffered.add(ENG06);
        coursesOffered.add(PHY7A);
        coursesOffered.add(PHY9A);
        coursesOffered.add(ECN1A);
        coursesOffered.add(ECN1B);
        coursesOffered.add(STA32);
        coursesOffered.add(STA100);





        MAT21ACheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                student.getClassesTaken().put("MAT21A", MAT21A);
            }
        });
        MAT21BCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                student.getClassesTaken().put("MAT21B", MAT21B);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ScheduleGUI");

        /*JPanel tabContainer = new JPanel();
        JTabbedPane tabbedPane = new JTabbedPane();
        JComboBox<String> majorChoice = new JComboBox<String>();
        majorChoice.addItem("AB Plan 1");
        tabbedPane.add(majorChoice);
        tabContainer.add(tabbedPane);
        frame.add(tabContainer);*/


        frame.setContentPane(new ScheduleGUI().tabContainer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}

