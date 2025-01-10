package com.ieltsdemo.repository;

import com.ieltsdemo.model.Result;
import com.ieltsdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

    ArrayList<Result> findResultsByEmailAndTestIdAndDeletedFalse(String email, String testId);
}
