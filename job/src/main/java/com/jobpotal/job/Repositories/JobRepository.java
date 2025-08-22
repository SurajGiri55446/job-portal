package com.jobpotal.job.Repositories;

import com.jobpotal.job.entity.Job;
import com.jobpotal.job.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobRepository extends JpaRepository<Job,Long> {
    List<Job> findByUser(User user);

    @Query("SELECT j FROM Job j WHERE " +
            "(:title IS NULL OR LOWER(j.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
            "(:location IS NULL OR LOWER(j.location) LIKE LOWER(CONCAT('%', :location, '%'))) AND " +
            "(:salary IS NULL OR j.salary >= :salary)")
    List<Job> searchJobs(@Param("title") String title,
                         @Param("location") String location,
                         @Param("salary") Double salary);


}
