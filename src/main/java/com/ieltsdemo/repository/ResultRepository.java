package com.ieltsdemo.repository;

import com.ieltsdemo.model.Result;
import com.ieltsdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

    ArrayList<Result> findResultsByEmailAndTestIdAndDeletedFalse(String email, String testId);

    Optional<ArrayList<Result>> findResultsByTextIdAndEmailAndDeleted(String textId, String email, boolean deleted);
}
