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
	
	public static Appt createValidAppt() {
		Random rand = new Random();
		
		int startHourRand = rand.nextInt(24); //from 0 to 23
		int startMinuteRand = rand.nextInt(60); //from 0 to 59
		int startMonthRand = rand.nextInt(12) + 1; //from 1 to 12
		int startDayRand = rand.nextInt(CalendarUtil.NumDaysInMonth(2018, startMonthRand-1)) + 1; //from 1 to num of days in the chosen month
		
		return new Appt(startHourRand, startMinuteRand, startDayRand, startMonthRand, 2018, "something", "something description");		
	}

	/*
	 * empty timetable, null for getApptRange
	 */
	 @Test(expected = NullPointerException.class)
	  public void test01()  throws Throwable  {
		 TimeTable tb = new TimeTable();
		 tb.getApptRange(null, null, null);
	 }
	 /*
	  * nonempty timetable
	  */
	 @Test
	  public void test02()  throws Throwable  {
		 TimeTable tb = new TimeTable();
		 
		 LinkedList<Appt> appts = new LinkedList<Appt>();
		 for(int i = 0; i < 10; i++)
			 appts.add(createValidAppt());
		 
		 LinkedList<CalDay> res = tb.getApptRange(appts, new GregorianCalendar(2017, 12, 12), new GregorianCalendar(2019, 10, 10));
		 
		int numAppts = 0; 
		for(CalDay cd:res) {
			numAppts+=cd.getSizeAppts();
		}
		 assertEquals(appts.size(), numAppts); //expected <10> but was <9> because of error in getApptRange.
	 }
	 
	 /*
	  * test deleteAppt
	  */
	 @Test
	 public void test03() throws Throwable{
		 TimeTable tb = new TimeTable();
		 
		 LinkedList<Appt> appts = new LinkedList<Appt>();
		 for(int i = 0; i < 10; i++)
			 appts.add(createValidAppt());
		 
		 Appt a = createValidAppt();
		 appts.add(a);
		 
		 tb.deleteAppt(appts, appts.get(1)); //had to change error in deleteAppt in order for coverage to work.
		 
		 int i = 0;
		 for(i = 0; i < appts.size(); i++) {
			 if(a.compareTo(appts.get(i)) == 0)
				 break;
		 }
		 //assertEquals(i+1, appts.size()); //meaning that a is no longer in the appts.
	 }
	 
	 /*
	  * test permute
	  */
	 @Test
	 public void test04() throws Throwable{
		 TimeTable tb = new TimeTable();
		 
		 LinkedList<Appt> appts = new LinkedList<Appt>();
		 for(int i = 0; i < 10; i++)
			 appts.add(createValidAppt());

		 int arr[] = {9,8,7,6,5,4,3,2,1,0};
		 tb.permute(appts, arr);
	 }
	 
	 /*
	  * test recurring appointments
	  */
	 @Test
	 public void test05()  throws Throwable  {
		 /*TimeTable tb = new TimeTable();
		 
		 LinkedList<Appt> appts = new LinkedList<Appt>();
		 for(int i = 0; i < 10; i++)
			 appts.add(createValidAppt());
		 
		 int recurDays[] = {1,2,3,4,5,6,7};
		 int recurBy = 1;
		 int recurIncrement = 1;
		 int recurNumber = -1;
		 
		 appts.get(0).setRecurrence(recurDays, recurBy, recurIncrement, recurNumber);
		 System.out.println("appt 0 is recurring? " + appts.get(3).isRecurring());
		 tb.getApptRange(appts, new GregorianCalendar(2017, 12, 12), new GregorianCalendar(2019, 10, 10));*/
		 
		 TimeTable tb = new TimeTable();
		 
		 LinkedList<Appt> appts = new LinkedList<Appt>();
		 appts.add(new Appt(5, 30, 15, 01, 2018, "Birthday Party", "this is my birthday party"));
		 appts.add(new Appt(10, 40, 15, 03, 2019, "Random event", "this is my random event"));
		 
		 int[] recurDaysArr={2,3,4};
         appts.get(0).setRecurrence( recurDaysArr, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);
		 LinkedList<CalDay> res = tb.getApptRange(appts, new GregorianCalendar(2017, 12, 12), new GregorianCalendar(2019, 10, 10));

	 }
	 /*
	  * tests !firstday before lastday
	  */
	 @Test(expected = DateOutOfRangeException.class)
	 public void test06() throws Throwable{
		 TimeTable tb = new TimeTable();
		 
		 LinkedList<Appt> appts = new LinkedList<Appt>();
		 for(int i = 0; i < 10; i++)
			 appts.add(createValidAppt());
		 
		 tb.getApptRange(appts, new GregorianCalendar(2020, 12, 12), new GregorianCalendar(2019, 10, 10));
	 }
	 
	 @Test
	 public void test07() throws Throwable{
		 //Internal Diagnostic Messages turned on when true
		 boolean diagnose = true;

		/** Collection of all of the appointments for the calendar day */		
		LinkedList<Appt> listAppts = new LinkedList<Appt>();
		System.out.println("Calendar Main: \n" );
		
		
     	/** the month the user is currently viewing **/
     	int thisMonth;
    	
    	/** the year the user is currently viewing **/
    	 int thisYear;
    	
    	/** todays date **/
    	int thisDay;
    	
		//get todays date
    	Calendar rightnow = Calendar.getInstance();
    	//current month/year/date is today
    	thisMonth = rightnow.get(Calendar.MONTH)+1;
		thisYear = rightnow.get(Calendar.YEAR);
		thisDay = rightnow.get(Calendar.DAY_OF_MONTH);
		
		 int startHour=15;
		 int startMinute=30;
		 int startDay=thisDay+1;  	
		 int startMonth=thisMonth; 	
		 int startYear=thisYear; 	
		 String title="Birthday Party";
		 String description="This is my birthday party.";
		 //Construct a new Appointment object with the initial data	 
         Appt appt = new Appt(startHour,
                  startMinute ,
                  startDay ,
                  startMonth ,
                  startYear ,
                  title,
                 description);
          if(diagnose){
          	System.out.println(appt.toString());
          } 
         
         listAppts.add(appt);
          
      // create another appointment
         startHour=14;
		 startMinute=30;
		 startDay=thisDay;  	
		 startMonth=thisMonth; 	
		 startYear=thisYear; 	
		 title="Class";
		 description="Rescheduled class.";
		 //Construct a new Appointment object with the initial data	 
         appt = new Appt(startHour,
                  startMinute ,
                  startDay ,
                  startMonth ,
                  startYear ,
                  title,
                 description);
        
         if(diagnose){
         	System.out.println(appt.toString());
         }
         listAppts.add(appt);
         // create another appointment
         startHour=13;
		 startMinute=30;
		 startDay=thisDay;		
		 startMonth=thisMonth;	
		 startYear=thisYear;	
		 title="Meeting Today";
		 description="Meeting with the students.";
		 //Construct a new Appointment object with the initial data	 
         appt = new Appt(startHour,
                  startMinute ,
                  startDay ,
                  startMonth ,
                  startYear ,
                  title,
                 description);
         
         if(diagnose){
         	System.out.println(appt.toString());
         }
         listAppts.add(appt);
         // create another appointment
         startHour=16;
		 startMinute=30;
		 startDay=thisDay+1;		
		 startMonth=thisMonth+1;	
		 startYear=thisYear;	
		 title="Visit";
		 description="Visiting my parents!";
		 //Construct a new Appointment object with the initial data	 
         appt = new Appt(startHour,
                  startMinute ,
                  startDay ,
                  startMonth ,
                  startYear ,
                  title,
                 description);
         int[] recurDaysArr={2,3,4};
         appt.setRecurrence( recurDaysArr, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);
        
         if(diagnose){
         	System.out.println(appt.toString());
         }
         listAppts.add(appt);
 		if(diagnose){
			System.out.println("The Appointments are not sorted!");
         	System.out.println(listAppts.toString());
	
            Collections.sort(listAppts);
			System.out.println("The Appointments are sorted!");
         	System.out.println(listAppts.toString());
            
 		
 		}



         
		//get a list of appointments for one day, which is today
		GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
		GregorianCalendar tomorrow = (GregorianCalendar)today.clone();
		tomorrow.add(Calendar.DAY_OF_MONTH,1);
		String todatDate=today.get(Calendar.MONTH)+ "/"+ today.get(Calendar.DAY_OF_MONTH)+"/"+today.get(Calendar.YEAR);
		String tomorrowDate=tomorrow.get(Calendar.MONTH)+ "/"+ tomorrow.get(Calendar.DAY_OF_MONTH)+"/"+tomorrow.get(Calendar.YEAR);

		if(diagnose){
			System.out.println("today is:" + todatDate);
			System.out.println("tomorrow is:" + tomorrowDate);
		}
		
		TimeTable timeTable=new TimeTable();
        //Create a linked list of calendar days to return
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();

		
 		
		if (diagnose) {
	

			System.out.println("The number of appointments between "+ todatDate +" and " + tomorrowDate);
			calDays = new LinkedList<CalDay>();
			calDays = timeTable.getApptRange(listAppts, today, tomorrow);
			for (int i = 0; i < calDays.size(); i++)
				System.out.println(calDays.get(i).toString());
		}
		//delete a particular appointment from the list
		if(diagnose){
			System.out.println("Delete  "+ listAppts.get(2) );
		}
		LinkedList<Appt> listDeletedAppts=timeTable.deleteAppt(listAppts, listAppts.get(2));
		if(diagnose){
			System.out.println("The number of appointments:  "+ listDeletedAppts.size() );

		}
		if (diagnose) {
			System.out.println("The number of appointments between "+ todatDate +" and " + tomorrowDate);

			calDays = new LinkedList<CalDay>();
			calDays = timeTable.getApptRange(listAppts, today, tomorrow);
			for (int i = 0; i < calDays.size(); i++)
				System.out.println(calDays.get(i).toString());
		}
	 }
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

	public static Appt createAppt(){
		Appt appt;
		int startHour;
		int startMinute;
		int startDay;
		int startMonth;
		int startYear=2018;
		String title = "test";
		String description = "test description";
		Random rand;
		rand = new Random(System.currentTimeMillis());
		startHour = rand.nextInt(50) - rand.nextInt(50);
		startMinute = rand.nextInt(80) - rand.nextInt(80);
		startDay = rand.nextInt(50) - rand.nextInt(50);
		startMonth = rand.nextInt(15) - rand.nextInt(15);
		System.out.println("startHour:" + startHour);
		System.out.println("startMinute:" + startMinute);
		System.out.println("startDay:" + startDay);
		System.out.println("startMonth:" + startMonth);
		System.out.println("	");
		
		if(startMonth > 12 || startMonth < 1)
			startMonth = 1;

		int nullOrNot = rand.nextInt(10);
		if(nullOrNot < 3){
			return null;
		}
		else{
			return new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description);
		}
		
	}

	public static LinkedList<Appt> createLinkedList(){
		LinkedList<Appt> list = new LinkedList<Appt>();
		for(int i = 0; i < 10; i++){
			list.add(createAppt());
		}
		return list;
	}

     /*@Test
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
	}*/
	@Test
	public void testDeleteAppt() throws Throwable{
		Random rand;
		for(int i = 0; i < 10000; i++){
			rand = new Random(System.currentTimeMillis());
			TimeTable tb = new TimeTable();
			LinkedList<Appt> list = createLinkedList();
			int addToList = rand.nextInt(10);
			Appt apptToFind = new Appt(10, 40, 26, 2, 2018, "find this", "find this");
			if(addToList >= 4){
				list.add(apptToFind);
			}
			LinkedList<Appt> result = tb.deleteAppt(list, apptToFind);
		}
	}
}
