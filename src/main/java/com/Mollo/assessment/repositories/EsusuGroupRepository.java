package com.Mollo.assessment.repositories;

import com.Mollo.assessment.entities.EsusuGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EsusuGroupRepository extends JpaRepository<EsusuGroup, Long> {

    Optional<EsusuGroup> findByGroupName(String groupName);
    List<EsusuGroup> findEsusuGroupByGroupName(String groupName);

}
