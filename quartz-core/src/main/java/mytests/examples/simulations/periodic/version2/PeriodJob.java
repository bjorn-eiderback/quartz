package mytests.examples.simulations.periodic.version2;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

public class PeriodJob implements Job  {

	@Override
	public void execute (JobExecutionContext context) throws JobExecutionException {
		JobKey jobKey = context.getJobDetail().getKey();
		System.out.println("Update job: " + jobKey + " executing at " + new Date());
	}
}
