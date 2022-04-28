package mytests.examples.simulations.periodic.version2;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class TriggerSeveralJobs {

	public static void main (String[] args) throws Exception {
		TriggerSeveralJobs example = new TriggerSeveralJobs();
		example.run();
	}

	List<Period> createEmulatedPeriods (Instant when) {
		return Arrays.asList(Period.builder().id(1).from(Date.from(when.minus(Duration.ofSeconds(20))))
						.to(Date.from(when.minus(Duration.ofSeconds(10)))).build(),
				Period.builder().id(2).from(Date.from(when.minus(Duration.ofSeconds(5))))
						.to(Date.from(when.plus(Duration.ofSeconds(20)))).build(),
				Period.builder().id(3).from(Date.from(when.plus(Duration.ofSeconds(15))))
						.to(Date.from(when.plus(Duration.ofSeconds(30)))).build());
	}

	private void createJobsAndTriggers (Scheduler sched, Period p) {
		JobDetail jobDetail1 = newJob(PeriodJob.class).withIdentity("job_from:" + p.getId(), "group:" + p.getId())
				.build();
		JobDetail jobDetail2 = newJob(PeriodJob.class).withIdentity("job_to:" + p.getId(), "group:" + p.getId())
				.build();
		Trigger trigger1 = newTrigger().withIdentity("trigger_from" + p.getId(), "group1").startAt(p.getFrom()).build();
		Trigger trigger2 = newTrigger().withIdentity("trigger_to" + p.getId(), "group1").startAt(p.getTo()).build();
		scheduleJob(sched, jobDetail1, trigger1);
		scheduleJob(sched, jobDetail2, trigger2);
	}

	private Instant defineCurrentTime () {
		Instant instantNow = Instant.now();
		System.out.println("<Time now: " + instantNow + ">");
		return instantNow;
	}

	private Scheduler initializeScheduler () throws SchedulerException {
		System.out.println("------- Initializing -------------------");
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		System.out.println("------- Initialization Complete --------");
		return sched;
	}

	public void run () throws Exception {
		Scheduler sched = initializeScheduler();
		Instant instantNow = defineCurrentTime();
		List<Period> periods = createEmulatedPeriods(instantNow);
		System.out.println("------- Scheduling Jobs ----------------");
		periods.forEach((Period p) -> {
			createJobsAndTriggers(sched, p);
		});
		startAndWait(sched);
		shutDown(sched);
	}

	private void scheduleJob (Scheduler sched, JobDetail jobDetail, Trigger trigger) {
		Date ft;
		try {
			ft = sched.scheduleJob(jobDetail, trigger);
			System.out.println(jobDetail.getKey() + " has been scheduled to run at: " + ft);
		} catch (SchedulerException e) {
			throw new RuntimeException(e);
		}
	}

	private void shutDown (Scheduler sched) throws SchedulerException {
		System.out.println("------- Shutting Down ---------------------");
		sched.shutdown(true);
	}

	private void startAndWait (Scheduler sched) throws SchedulerException {
		sched.start();
		System.out.println("------- Started Scheduler -----------------");
		System.out.println("------- Waiting one minute... ------------");
		try {
			// wait one minute to show jobs
			Thread.sleep(60L * 1000L);
			// executing...
		} catch (Exception e) {
			//
		}
	}
}

