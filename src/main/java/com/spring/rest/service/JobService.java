package com.spring.rest.service;

import com.spring.rest.model.JobPost;
import com.spring.rest.repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class JobService {

    List<JobPost> jobs = new ArrayList<>(Arrays.asList(
            new JobPost(1,"Java Dev","Ok Dev",5,Arrays.asList("Java", "Spring", "Hibernate")),
            new JobPost(2,"JS Dev","Good Dev",5,Arrays.asList("React", "Material UI", "JS")),
            new JobPost(4,"QA","Idiot QA",5,Arrays.asList("Good for nothing","Idiot")),
            new JobPost(3,"Manager","Dumb Manager",5,Arrays.asList("Nothing","Being idiot"))
    ));

    @Autowired
    private JobRepo repo;

    public void addJob(JobPost jobpost){
        repo.save(jobpost);
    }

    public List<JobPost> getAllJobs() {
        return repo.findAll();
    }

    public JobPost getJob(int postId) {
        return repo.findById(postId).orElse(new JobPost()) ;
    }

    public void updateJob(JobPost jobPost) {
        repo.save(jobPost);
    }

    public void deleteJob(int postId) {
        repo.deleteById(postId);
    }

    public void loadAllData() {
        repo.saveAll(jobs);
    }

    public List<JobPost> search(String keyword) {
        return repo.findByPostProfileContainingOrPostDescContaining(keyword, keyword);
    }
}