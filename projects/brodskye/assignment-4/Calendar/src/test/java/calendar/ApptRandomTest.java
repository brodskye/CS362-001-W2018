package calendar;

import java.util.Calendar;
import java.util.Random;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;



import static org.junit.Assert.*;



/**
 * Random Test Generator  for Appt class.
 */

public class ApptRandomTest {
	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS=100;

	/**
	 * Return a randomly selected method to be tests !.
	 */
    public static String RandomSelectMethod(Random random){
        String[] methodArray = new String[] {"setTitle","setRecurrence"};// The list of the of methods to be tested in the Appt class

    	int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)
    	            
        return methodArray[n] ; // return the method name 
        }
	/**
	 * Return a randomly selected appointments to recur Weekly,Monthly, or Yearly !.
	 */
    public static int RandomSelectRecur(Random random){
        int[] RecurArray = new int[] {Appt.RECUR_BY_WEEKLY,Appt.RECUR_BY_MONTHLY,Appt.RECUR_BY_YEARLY};// The list of the of setting appointments to recur Weekly,Monthly, or Yearly

    	int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
        return RecurArray[n] ; // return the value of the  appointments to recur 
        }	
	/**
	 * Return a randomly selected appointments to recur forever or Never recur  !.
	 */
    public static int RandomSelectRecurForEverNever(Random random){
        int[] RecurArray = new int[] {Appt.RECUR_NUMBER_FOREVER,Appt.RECUR_NUMBER_NEVER};// The list of the of setting appointments to recur RECUR_NUMBER_FOREVER, or RECUR_NUMBER_NEVER

    	int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
        return RecurArray[n] ; // return appointments to recur forever or Never recur 
        }	
   /**
     * Generate Random Tests that tests Appt Class.
     */
	 @Test
	  public void radnomtest()  throws Throwable  {

		 long startTime = Calendar.getInstance().getTimeInMillis();
		 long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		 
		 System.out.println("Start testing...");
		 
		try{ 
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				long randomseed =System.currentTimeMillis(); //10
	//			System.out.println(" Seed:"+randomseed );
				Random random = new Random(randomseed);
				
				 int startHour=ValuesGenerator.RandInt(random);
				 int startMinute=ValuesGenerator.RandInt(random);
				 int startDay=ValuesGenerator.RandInt(random);;
				 int startMonth=ValuesGenerator.getRandomIntBetween(random, 1, 11);
				 int startYear=ValuesGenerator.RandInt(random);
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
			 if(!appt.getValid())continue;
			for (int i = 0; i < NUM_TESTS; i++) {
					String methodName = ApptRandomTest.RandomSelectMethod(random);
					   if (methodName.equals("setTitle")){
						   String newTitle=(String) ValuesGenerator.getString(random);
						   appt.setTitle(newTitle);						   
						}
					   else if (methodName.equals("setRecurrence")){
						   int sizeArray=ValuesGenerator.getRandomIntBetween(random, 0, 8);
						   int[] recurDays=ValuesGenerator.generateRandomArray(random, sizeArray);
						   int recur=ApptRandomTest.RandomSelectRecur(random);
						   int recurIncrement = ValuesGenerator.RandInt(random);
						   int recurNumber=ApptRandomTest.RandomSelectRecurForEverNever(random);
						   appt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);
						}				
				}
				
				 elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
			        if((iteration%10000)==0 && iteration!=0 )
			              System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
			 
			}
		}catch(NullPointerException e){
			
		}
	 
		 System.out.println("Done testing...");
	 }

	 //create an Appt.
	 public static Appt createAppt(int startHour, int startMinute, int startDay, int startMonth, int startYear, String title, String description){
		return new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description);
	 }

	 /*
	  * tests isValid for completely random appts to see if method catches errors properly.
	  */
	 @Test
	 public void testRandomAppts() throws Throwable{
		 Appt appt;
		 int startHour;
		 int startMinute;
		 int startDay;
		 int startMonth;
		 int startYear=2018;
		 String title = "test";
		 String description = "test description";
		 Random rand;
		 for(int i = 0; i < 100000; i++) {
			rand = new Random(System.currentTimeMillis());
			startHour = rand.nextInt(50) - rand.nextInt(50);
			startMinute = rand.nextInt(80) - rand.nextInt(80);
			startDay = rand.nextInt(50) - rand.nextInt(50);
			startMonth = rand.nextInt(15) - rand.nextInt(15);
			System.out.println("startHour:" + startHour);
			System.out.println("startMinute:" + startMinute);
			System.out.println("startDay:" + startDay);
			System.out.println("startMonth:" + startMonth);
			
			int NumDaysInMonth;
			if(startMonth > 12 || startMonth < 1)
				startMonth = 1;

			NumDaysInMonth = CalendarUtil.NumDaysInMonth(startYear,startMonth-1);

			appt = createAppt(startHour, startMinute, startDay, startMonth, startYear, title, description);


			if(startHour<0 || startHour>23)
	    		assertFalse(appt.getValid());
	    	else
	        	if(startMinute<0 || startMinute>59)
		    		assertFalse(appt.getValid());
	        	else
	            	if(startDay<1 || startDay>NumDaysInMonth) 
	    	    		assertFalse(appt.getValid());
	            	else
	                	if(startMonth<1 || startMonth>12)
	        	    		assertFalse(appt.getValid());
	                	else
	        	    		assertTrue(appt.getValid());
		 }

		 //LinkedList<Appt> appts = new LinkedList<Appt>();
		 //appts.add(new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description)))
	 }
	
	@Test
	public void testNullRecurDays() throws Throwable{
		long randomseed =System.currentTimeMillis(); //10
				Random random = new Random(randomseed);
				
				 int startHour=ValuesGenerator.RandInt(random);
				 int startMinute=ValuesGenerator.RandInt(random);
				 int startDay=ValuesGenerator.RandInt(random);;
				 int startMonth=ValuesGenerator.getRandomIntBetween(random, 1, 11);
				 int startYear=ValuesGenerator.RandInt(random);
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
				int recur=ApptRandomTest.RandomSelectRecur(random);
				int recurIncrement = ValuesGenerator.RandInt(random);
				int recurNumber=ApptRandomTest.RandomSelectRecurForEverNever(random);
				appt.setRecurrence(null, recur, recurIncrement, recurNumber);
	}
}
