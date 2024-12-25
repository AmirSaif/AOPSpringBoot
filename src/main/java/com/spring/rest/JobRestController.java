package com.spring.rest;

import com.spring.rest.model.JobPost;
import com.spring.rest.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobRestController {

    @Autowired
    private JobService service;

//    @GetMapping(path="posts",produces={"application/XML"}) This generates/produces XML formatted data
//    instead of default json
    @GetMapping("posts")
    public List<JobPost> getAllJobs(){
        return service.getAllJobs();
    }

    @GetMapping("post/{postId}")
    public JobPost getJob(@PathVariable("postId") int postId){
        return service.getJob(postId);
    }

    //    @PostMapping(path="posts",consumes={"application/XML"}) This accepts/consumes XML formatted data
//    instead of default json
    @PostMapping("/post")
    public JobPost addJob(@RequestBody JobPost job){
        service.addJob(job);
        return service.getJob(job.getPostId());
    }

    @PutMapping("/post")
    public JobPost updateJob(@RequestBody JobPost jobPost ){
        service.updateJob(jobPost);
        return service.getJob(jobPost.getPostId());
    }

    @DeleteMapping("/post/{postId}")
    public String deleteJob(@PathVariable int postId){
        service.deleteJob(postId);
        return "Job deleted!";
    }

    @GetMapping("/load")
    public String loadData(){
        service.loadAllData();
        return "Success!";
    }

    @GetMapping("/posts/keyword/{keyword}")
    public List<JobPost> searchByKeyword(@PathVariable("keyword") String keyword){
        return service.search(keyword);
    }
}
