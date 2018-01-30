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

import org.junit.Test;

import static org.junit.Assert.*;

public class TimeTableTest {
	
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
		 assertEquals(i+1, appts.size()); //meaning that a is no longer in the appts.
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
//add more unit tests as you needed
}
