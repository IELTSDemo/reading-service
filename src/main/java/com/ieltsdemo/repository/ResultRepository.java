package com.ieltsdemo.repository;

import com.ieltsdemo.model.Result;
import com.ieltsdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    Optional<Result> findResultByUser(User user);

    Optional<Result> findResultByUserAndTestId(User user, String testId);
}
