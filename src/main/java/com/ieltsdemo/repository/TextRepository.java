package com.ieltsdemo.repository;

import com.ieltsdemo.model.text.Text;
import com.ieltsdemo.util.SectionType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TextRepository extends MongoRepository<Text, String> {
  List<Text> findTextByTestIdAndSection(String testId, SectionType section);
  int countByTestIdAndSection(String testId, SectionType section);
}
