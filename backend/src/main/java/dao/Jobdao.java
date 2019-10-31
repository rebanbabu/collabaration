package dao;

import java.util.List;

import main.Job;

public interface Jobdao {
	void saveJob(Job job);

	List<Job> getAllJobs();

	Job getJobById(int job);

}
