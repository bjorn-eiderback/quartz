package mytests.simple;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloJob implements Job {

	public HelloJob() {
		System.out.println("Hello World created! - " + new Date());
	}

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("Hello World! - " + new Date());
	}

}
