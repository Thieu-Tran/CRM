package service;

import model.JobModel;
import repository.JobRepository;

import java.sql.Date;
import java.util.List;

public class JobService {
    JobRepository jobRepository = new JobRepository();

    public List<JobModel> getAllJob(){
        return jobRepository.findAllJob();
    }

    public JobModel getJobById(int id){
        return jobRepository.findJobById(id);
    }

    public boolean insertJob(String name, Date startDate, Date endDate){
        return jobRepository.insertJob(name,startDate,endDate);
    }

    public boolean deleteJobById(int id){
        return jobRepository.deleteJobById(id);
    }

    public boolean updateJobById(String name,Date startDate,Date endDate, int id){
        return jobRepository.updateJobById(name,startDate,endDate,id);
    }

}
