package com.ieltsdemo.repository;

import com.ieltsdemo.model.Result;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

    ArrayList<Result> findResultsByEmailAndTestIdAndDeletedFalse(String email, String testId);
    ArrayList<Result> findResultsByTestId(String testId);

    boolean  existsByTextIdAndEmailAndDeleted(String textId, String email, boolean deleted);

    @Transactional
    @Modifying
    @Query("DELETE FROM Result r WHERE r.textId = :textId AND r.email = :email AND r.deleted = :deleted")
    void deleteByTextIdAndEmail(@Param("textId") String textId,
                                @Param("email") String email,
                                @Param("deleted") boolean deleted);


    @Transactional
    @Modifying
    @Query("DELETE FROM Result r WHERE r.testId = :testId AND r.email = :email AND r.deleted = :deleted")
    void deleteByTestIdAndEmail(@Param("testId") String testId,
                                @Param("email") String email,
                                @Param("deleted") boolean deleted);
}
