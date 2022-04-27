package mytests.simple;// file: QuartzTest.java
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class QuartzTest {

	public static void main(String[] args) throws InterruptedException {

		try {
			// Grab the Scheduler instance from the Factory
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			//Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			// and start it off
			scheduler.start();
			System.out.println("Started");
			// define the job and tie it to our HelloJob class
			JobDetail job = newJob(HelloJob.class)
					.withIdentity("job1", "group1")
					.build();

			// Trigger the job to run now, and then repeat every 4 seconds
			Trigger trigger = newTrigger()
					.withIdentity("trigger1", "group1")
					.startNow()
					.withSchedule(simpleSchedule()
							.withIntervalInSeconds(4)
							.repeatForever())
					.build();

			// Tell quartz to schedule the job using our trigger
			scheduler.scheduleJob(job, trigger);
			Thread.sleep(15000);
			scheduler.shutdown();
			System.out.println("Stopped");

		} catch (SchedulerException se) {
			se.printStackTrace();
		}
	}
}
