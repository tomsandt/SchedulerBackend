package de.tom.scheduler.controller;
import de.tom.scheduler.domain.Job;
import de.tom.scheduler.domain.JobDTO;
import de.tom.scheduler.service.JobService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping("/job/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable int id) {
        Optional<Job> job = jobService.getJobById(id);
        if(job.isPresent()) {
            return ResponseEntity.ok(job.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> getAllJobs() {
        List<Job> jobs = jobService.getAllJobs();
        return ResponseEntity.ok(jobs);
    }

    @PostMapping("/jobs/create")
    public ResponseEntity<Job> createJob(@RequestBody JobDTO jobDTO) {
        Job newJob = jobService.createJob(jobDTO);
        return new ResponseEntity<>(newJob, HttpStatus.CREATED);
    }

    @PutMapping("/job/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable int id, @RequestBody JobDTO jobDTO) {
        try {
            Job updatedJob = jobService.updateJob(id, jobDTO);
            return ResponseEntity.ok(updatedJob);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/job/{id}")
    public ResponseEntity<Job> deleteJob(@PathVariable int id) {
        try {
            jobService.deleteJob(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
