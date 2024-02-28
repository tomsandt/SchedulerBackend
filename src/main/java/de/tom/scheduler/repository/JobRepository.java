package de.tom.scheduler.repository;

import de.tom.scheduler.domain.Job;
import de.tom.scheduler.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {

    Job findJobByid(int id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Job(name, enabled, status, lastRun, nextRun, activeFrom, activeUntil, schedule) " +
            "VALUES (:name, :enabled, :status, :lastRun, :nextRun, :activeFrom, :activeUntil, :schedule)",
            nativeQuery = true)
    void saveJob(@Param("name") String name, @Param("enabled") boolean enabled,
                 @Param("status") Status status, @Param("lastRun") LocalDateTime lastRun,
                 @Param("nextRun") LocalDateTime nextRun, @Param("activeFrom") LocalDateTime activeFrom,
                 @Param("activeUntil") LocalDateTime activeUntil, @Param("schedule") String schedule);

}
