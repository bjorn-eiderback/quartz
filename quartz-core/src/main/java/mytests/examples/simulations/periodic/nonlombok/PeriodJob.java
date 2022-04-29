package mytests.examples.simulations.periodic.nonlombok;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

public class PeriodJob implements Job  {

	@Override
	public void execute (JobExecutionContext context) throws JobExecutionException {
		JobKey jobKey = context.getJobDetail().getKey();
		System.out.println("\nUpdate job: " + jobKey + " executing at " + new Date());
		System.out.println("the period object: " + context.getJobDetail().getJobDataMap().get("period")); //New
	}
}
