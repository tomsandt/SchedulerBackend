package de.tom.scheduler.service;

import de.tom.scheduler.domain.Job;
import de.tom.scheduler.domain.JobDTO;
import de.tom.scheduler.domain.Status;
import de.tom.scheduler.repository.JobRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Optional<Job> getJobById(int id) {
        return jobRepository.findById(id);
    }

    public void deleteJob(int id) {
        if(!jobRepository.existsById(id)) {
            throw new EntityNotFoundException("Job not found with ID: " + id);
        }else {
            jobRepository.deleteById(id);
        }
    }

    public Job createJob(JobDTO jobDTO) {
        Job job = new Job();

        job.setName(jobDTO.getName());
        job.setEnabled(jobDTO.isEnabled());
        job.setStatus(jobDTO.getStatus());
        job.setLastRun(jobDTO.getLastRun());
        job.setNextRun(jobDTO.getNextRun());
        job.setActiveFrom(jobDTO.getActiveFrom());
        job.setActiveUntil(jobDTO.getActiveUntil());

        return jobRepository.save(job);
    }

}