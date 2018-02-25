package calendar;
import static org.junit.Assert.assertEquals;

import java.util.GregorianCalendar;
import java.util.Random;

import org.junit.Test;

public class CalDayRandomTest {

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