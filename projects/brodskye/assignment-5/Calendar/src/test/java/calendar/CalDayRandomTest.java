package calendar;
/**
*  This class provides a basic set of test cases for the
*  CalDay class.
*/
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.GregorianCalendar;
import java.util.Random;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalDayRandomTest {

	/*
	* test getters
	*/
	@Test
		public void test01()  throws Throwable {
		GregorianCalendar gc = new GregorianCalendar();
		CalDay calday = new CalDay(gc);
		int day = gc.get(gc.DAY_OF_MONTH);
		int month = gc.get(gc.MONTH);
		int year = gc.get(gc.YEAR);

		assertEquals(calday.getDay(), gc.get(gc.DAY_OF_MONTH));
		assertEquals(calday.getMonth(), gc.get(gc.MONTH));
		assertEquals(calday.getYear(), gc.get(gc.YEAR));
	}

	/*
	* test if no GregorianCalendar parameter in constructor
	*/
	@Test
		public void test02() throws Throwable {
		CalDay calday = new CalDay();
		assertFalse(calday.isValid());
	}

	/*
	* test toString method
	*/
	@Test
		public void test03() throws Throwable {
		GregorianCalendar gc = new GregorianCalendar();
		CalDay calday = new CalDay(gc);

		StringBuilder sb = new StringBuilder();
		String todayDate = (calday.getMonth()) + "/" + calday.getDay() + "/" + calday.getYear();
		sb.append("\t --- " + todayDate + " --- \n");
		sb.append(" --- -------- Appointments ------------ --- \n");
		Iterator<Appt> itr = calday.appts.iterator();
		while (itr.hasNext()) {
			Object element = itr.next();

			sb.append(element + " ");
		}

		//			sb.append(this.appts);
		sb.append("\n");

		assertEquals(calday.toString(), sb.toString());
	}
	/*
	* test adAppt
	*/
	@Test
		public void test04() throws Throwable {
		GregorianCalendar gc = new GregorianCalendar();
		CalDay calday = new CalDay(gc);
		calday.addAppt(new Appt(21, 30, 15, 01, 2018, "Birthday party", "This is my birthday party"));
		assertEquals(calday.getSizeAppts(), 1);
	}

	/*
	* test toString for no parameter constructor
	*/
	@Test
		public void test05() throws Throwable {
		CalDay calday = new CalDay();
		assertEquals(calday.toString(), "");
	}

	/*
	* test addAppt for no parameter constructor
	*/
	/*@Test
	public void test06() throws Throwable{
	CalDay calday = new CalDay();
	calday.addAppt(new Appt(21, 30, 15, 01, 2018, "Birthday party", "This is my birthday party"));
	//assertEquals(calday.getSizeAppts(), 0);
	}*/


	/*
	* test adAppt
	*/
	@Test
		public void test06() throws Throwable {
		GregorianCalendar gc = new GregorianCalendar();
		CalDay calday = new CalDay(gc);
		calday.addAppt(new Appt(21, 30, 15, 01, 2018, "Birthday party", "This is my birthday party"));
		assertEquals(calday.getSizeAppts(), 1);
	}

    @Test
    public void testAddAppt() throws Throwable{
        Random random;
        CalDay calday = new CalDay(new GregorianCalendar(2018, 2, 24));
        for(int i = 0; i < 500; i++){
            random = new Random(System.currentTimeMillis());
            
            int startHour=ValuesGenerator.RandInt(random);
            int startMinute=ValuesGenerator.RandInt(random);
            int startDay=calday.getDay();
            int startMonth=calday.getMonth();
            int startYear=calday.getYear();
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
            calday.addAppt(appt);
        }

        random = new Random(System.currentTimeMillis());

        int startMinute=ValuesGenerator.RandInt(random);
        int startDay=calday.getDay();
        int startMonth=calday.getMonth();
        int startYear=calday.getYear();
        String title="Birthday Party";
        String description="This is my birthday party.";
        Appt invalidAppt = new Appt(50, startMinute, startDay, startMonth, startYear, title, description);
        calday.addAppt(invalidAppt);
    }
	/*
	* test addAppt for no parameter constructor
	*/
	/*@Test
	public void test06() throws Throwable{
	CalDay calday = new CalDay();
	calday.addAppt(new Appt(21, 30, 15, 01, 2018, "Birthday party", "This is my birthday party"));
	//assertEquals(calday.getSizeAppts(), 0);
	}*/

}