package com.spring.rest.repo;

import com.spring.rest.model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public interface JobRepo extends JpaRepository<JobPost, Integer> {
    List<JobPost> findByPostProfileContainingOrPostDescContaining(String postProfile, String postDesc);


//    List<JobPost> jobs = new ArrayList<>(Arrays.asList(
//            new JobPost(1,"Java Dev","Ok Dev",5,Arrays.asList("Java", "Spring", "Hibernate")),
//            new JobPost(2,"JS Dev","Good Dev",5,Arrays.asList("React", "Material UI", "JS")),
//            new JobPost(4,"QA","Idiot QA",5,Arrays.asList("Good for nothing","Idiot")),
//            new JobPost(3,"Manager","Dumb Manager",5,Arrays.asList("Nothing","Being idiot"))
//    ));
//
//    public void addJob(JobPost job) {
//        jobs.add(job);
//    }
//
//    public List<JobPost> getAllJobs() {
//        return jobs;
//    }
//
//    public JobPost getJob(int postId) {
//        for(JobPost job: jobs){
//            if(job.getPostId() == postId)
//                return job;
//        }
//        return null;
//    }
//
//    public void updateJobs(JobPost jobPost) {
//        for(JobPost job: jobs){
//            if(job.getPostId() == jobPost.getPostId())
//                job.setReqExperience(jobPost.getReqExperience());
//        }
//    }
//
//    public void deleteJobPost(int postId) {
//        jobs.removeIf(job -> job.getPostId() == postId);
//    }
}
