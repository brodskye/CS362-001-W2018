package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  Appt class.
 */
import org.junit.Test;

import java.util.Random;
//import jdk.internal.jline.internal.TestAccessible;


import static org.junit.Assert.*;

public class ApptTest {
    /**
     * Test that the gets methods work as expected.
     */
	 @Test
	  public void test01()  throws Throwable  {
		 int startHour=21;
		 int startMinute=30;
		 int startDay=15;
		 int startMonth=01;
		 int startYear=2018;
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
	// assertions
		 assertTrue(appt.getValid());
		 assertEquals(21, appt.getStartHour());
		 assertEquals(30, appt.getStartMinute());
		 assertEquals(15, appt.getStartDay());
		 assertEquals(01, appt.getStartMonth());
		 assertEquals(2018, appt.getStartYear());
		 assertEquals("Birthday Party", appt.getTitle());
		 assertEquals("This is my birthday party.", appt.getDescription());         		
	 }
	
	/*
	tests that setters work fine.
	*/

	@Test
	public void test02()  throws Throwable  {
		int startHour=21;
		int startMinute=30;
		int startDay=15;
		int startMonth=01;
		int startYear=2018;
		String title="Birthday Party";
		String description="This is my birthday party.";

		Appt appt = new Appt(22, 31, 16, 02, 2019, "Birthday Party 2", "This is my birthday party 2");
		appt.setStartDay(startDay);
		appt.setStartHour(startHour);
		appt.setStartMinute(startMinute);
		appt.setStartMonth(startMonth);
		appt.setStartYear(startYear);
		appt.setTitle(title);
		appt.setDescription(description);

		assertTrue(appt.getValid());
		assertEquals(21, appt.getStartHour());
		assertEquals(30, appt.getStartMinute());
		assertEquals(15, appt.getStartDay());
		assertEquals(01, appt.getStartMonth());
		assertEquals(2018, appt.getStartYear());
		assertEquals("Birthday Party", appt.getTitle());
		assertEquals("This is my birthday party.", appt.getDescription()); 

	}

	
	public static Appt messEverythingUp(Appt appt, int startHourRand, int startMinuteRand, int startDayRand, int startMonthRand) {
		
		appt.setStartHour(startHourRand);
		appt.setStartMinute(startMinuteRand);
		appt.setStartDay(startDayRand);
		appt.setStartMonth(startMonthRand);		
		return appt;
	}
	
	/*
	Tests that isValid() reports correctly
	*/
	@Test
	public void test03() throws Throwable{
		/*int startHour=5;
		int startMinute=30;
		int startDay=15;
		int startMonth=01;*/
		int startYear=2018;
		String title="Birthday Party";
		String description="This is my birthday party.";
		//Construct a new Appointment object with the initial data	 
		/*Appt appt = new Appt(startHour,
		        startMinute ,
		        startDay ,
		        startMonth ,
		        startYear ,
		        title,
				description);*/
		//this was equivalent to the longer method that created a new Appt and changed one of the fields to be invalid. Same thing, but shorter.
		/*assertFalse(startHour<0 && startHour>23);
		assertFalse(startMinute<0 || startMinute>59);
		assertFalse(startDay<1 || startDay>CalendarUtil.NumDaysInMonth(startYear,startMonth-1));
		assertFalse(startMonth<1 || startMonth>12);*/
		Random rand = new Random();
		
		int startHourRand;
		int startMinuteRand;
		int startDayRand;
		int startMonthRand;
		
		for(int i = 0; i < 1000; i++) { //randomly start testing different cases that would make the appointment be invalid		
			startHourRand = rand.nextInt(100) - rand.nextInt(100);
			startMinuteRand = rand.nextInt(100) - rand.nextInt(100);
			startDayRand = rand.nextInt(100) - rand.nextInt(100);
			//startMonthRand = rand.nextInt(100) - rand.nextInt(100);
			startMonthRand = rand.nextInt(12) + 1;
			Appt appt = new Appt(5, 30, 15, 01, 2018, "Birthday Party", "this is my birthday party"); //start off with normal appt to avoid exceptions
																										//when isValid is automatically called first time.
			assertTrue(startMonthRand >=1 && startMonthRand <=12); //necessary to ensure numdaysinmonth doesn't throw exception
			int NumDaysInMonth= CalendarUtil.NumDaysInMonth(startYear,startMonthRand-1);
			
			
			//mess up the values in appt based on the random values.
			
			appt = messEverythingUp(appt, startHourRand, startMinuteRand, startDayRand, startMonthRand);
	    	if(startHourRand<0 || startHourRand>23) //changed || to &&
	    		assertFalse(appt.getValid());
	    	else
	        	if(startMinuteRand<0 || startMinuteRand>59)
		    		assertFalse(appt.getValid());
	        	else
	            	if(startDayRand<1 || startDayRand>NumDaysInMonth) 
	    	    		assertFalse(appt.getValid());
	            	else
	                	if(startMonthRand<1 || startMonthRand>12)
	        	    		assertFalse(appt.getValid());
	                	else
	        	    		assertTrue(appt.getValid());
		}

	}

	/*
	test representationApp
	*/
	@Test
	public void test04() throws Throwable{
		int startHour=11; //will generate bad printable hour
		int startMinute=30;
		int startDay=15;
		int startMonth=01;
		int startYear=2018;
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

        String day= appt.getStartMonth()+"/"+appt.getStartDay()+"/"+appt.getStartYear() + " at ";
        
        String half = (appt.getStartHour() > 11) ? "pm" : "am";
        int printableHour = appt.getStartHour(); //copied this directly from Appt.java
        if (printableHour > 11) //changed > to >=
        {
            printableHour -= 12;
        }
        if (printableHour == 0)
        {
            printableHour = 12;
        }
        String represntationApp= printableHour +":"+ appt.getStartMinute() + half;

		assertEquals(appt.toString(), "\t"+ day +  represntationApp  + " ," +  appt.getTitle()+ ", "+  appt.getDescription()+"\n");
	}
	
	/*
	 * test compareTo
	 */
	@Test
	public void test05() throws Throwable{
		int startHour=5;
		int startMinute=30;
		int startDay=15;
		int startMonth=01;
		int startYear=2018;
		String title="Birthday Party";
		String description="This is my birthday party.";
		//Construct a new Appointment object with the initial data	 
		Appt appt1 = new Appt(startHour,
		        startMinute ,
		        startDay ,
		        startMonth ,
		        startYear ,
		        title,
				description);
		
		startHour=8;
		startMinute=40;
		startDay=16;
		startMonth=02;
		startYear=2019;
		title="Birthday Party2";
		description="This is my birthday party2.";
		//Construct a new Appointment object with the initial data	 
		Appt appt2 = new Appt(startHour,
		        startMinute ,
		        startDay ,
		        startMonth ,
		        startYear ,
		        title,
				description);
		//this is appt1.compareTo(appt2)
		int tstartMinute=	appt1.getStartMinute() - ((Appt) appt2).getStartMinute();
		int tstartHour=	appt1.getStartHour() - ((Appt) appt2).getStartHour();
		int tday = appt1.getStartDay()-((Appt) appt2).getStartDay();
		int tmonth = appt1.getStartMonth() -((Appt) appt2).getStartMonth();
		int tyear = appt1.getStartYear() -((Appt) appt2).getStartYear();
		
		assertEquals(appt1.compareTo(appt2), tstartMinute + tstartHour + tday + tmonth + tyear);
	}
	
	
}
