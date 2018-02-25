package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  TimeTable class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import org.junit.Test;

import static org.junit.Assert.*;

public class TimeTableRandomTest {

    public static LinkedList<Appt> createRandomLinkedList(){
        LinkedList<Appt> list = new LinkedList<Appt>();
        Random rand = new Random(System.currentTimeMillis());
        int bound = (rand.nextInt(50 - 1) + 1);

        for(int i = 0; i < bound; i++){
            long randomseed =System.currentTimeMillis(); //10
            Random random = new Random(randomseed);
            
            int startHour=ValuesGenerator.RandInt(random);
            int startMinute=ValuesGenerator.RandInt(random);
            int startDay=ValuesGenerator.RandInt(random);
            int startMonth=ValuesGenerator.getRandomIntBetween(random, 1, 11);
            int startYear=ValuesGenerator.RandInt(random);
            String title="Birthday Party";
            String description="This is my birthday party.";
            //Construct a new Appointment object with the initial data	 
            list.add(new Appt(startHour,
                    startMinute ,
                    startDay ,
                    startMonth ,
                    startYear ,
                    title,
                    description));
        }
        Random random = new Random(System.currentTimeMillis());

        list.add(null);
        list.add(null);
        list.add(null);
        list.add(new Appt(50, ValuesGenerator.RandInt(random), ValuesGenerator.RandInt(random), ValuesGenerator.getRandomIntBetween(random, 1, 11),
        ValuesGenerator.RandInt(random), "Birthday Party", "This is my birthday party."));
        list.add(new Appt(60, ValuesGenerator.RandInt(random), ValuesGenerator.RandInt(random), ValuesGenerator.getRandomIntBetween(random, 1, 11),
        ValuesGenerator.RandInt(random), "Birthday Party", "This is my birthday party."));
        list.add(new Appt(70, ValuesGenerator.RandInt(random), ValuesGenerator.RandInt(random), ValuesGenerator.getRandomIntBetween(random, 1, 11),
        ValuesGenerator.RandInt(random), "Birthday Party", "This is my birthday party."));

        return list;
    }

     @Test
     public void testDeleteAppt() throws Throwable{
        //create a random list of appointments, all elements either valid or null
        //remove random element
        Random random = new Random(System.currentTimeMillis()); 
        TimeTable timetable = new TimeTable();
        LinkedList<Appt> list;
        LinkedList<Appt> newList;
        for(int i = 0; i < 500; i++){
            list = createRandomLinkedList();
            int index = random.nextInt(list.size());
            System.out.println("index: " + index + "	" + "size: " + list.size());
            newList = timetable.deleteAppt(list, list.get(index));
        }
       /* LinkedList<Appt> uninit = new LinkedList<Appt>();
        newList = timetable.deleteAppt(uninit,new Appt(3, 55, 24, 2, 2018, "test", "testing"));
        newList = timetable.deleteAppt(uninit, null);
        newList = timetable.deleteAppt(list, new Appt(3, 55, 24, 2, 2018, "test", "testing"));*/
     }

}
