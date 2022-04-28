package mytests.examples.simulations.periodic.version1;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class TriggerOneJobWithStartTime {

	public void run () throws Exception {
		Scheduler sched = initializeScheduler();
		System.out.println("------- Scheduling Jobs ----------------");

		Date fireTime = computeFireTime();
		JobDetail job = newJob(MyUpdateJob.class)
				.withIdentity("job1", "group1").build();
		Trigger trigger = newTrigger()
				.withIdentity("triggerID", "group1")
				.startAt(fireTime)
				.build();
		scheduleJobWithTrigger(sched, job, trigger);

		startAndWait(sched);
		shutDown(sched);
	}

	private void scheduleJobWithTrigger (Scheduler sched, JobDetail job, Trigger trigger) throws SchedulerException {
		Date ft = sched.scheduleJob(job, trigger);
		System.out.println(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression (should not happpen): "
				+ trigger.getNextFireTime());
	}

	private Date computeFireTime () {
		Instant instantNow = Instant.now();
		Date fireTime = Date.from(instantNow.plus(Duration.ofSeconds(10)));
		return fireTime;
	}

	private void shutDown (Scheduler sched) throws SchedulerException {
		System.out.println("------- Shutting Down ---------------------");
		sched.shutdown(true);
	}

	private void startAndWait (Scheduler sched) throws SchedulerException {
		sched.start();
		System.out.println("------- Started Scheduler -----------------");
		System.out.println("------- Waiting five minutes... ------------");
		try {
			// wait five minutes to show jobs
			Thread.sleep(300L * 1000L);
			// executing...
		} catch (Exception e) {
			//
		}

	}

	private Scheduler initializeScheduler () throws SchedulerException {
		System.out.println("------- Initializing -------------------");
		// First we must get a reference to a scheduler
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		System.out.println("------- Initialization Complete --------");
		return sched;
	}

	public static void main(String[] args) throws Exception {
		TriggerOneJobWithStartTime example = new TriggerOneJobWithStartTime();
		example.run();
	}
}

