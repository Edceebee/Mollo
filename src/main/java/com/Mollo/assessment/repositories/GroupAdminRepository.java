package com.Mollo.assessment.repositories;

import com.Mollo.assessment.entities.GroupAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupAdminRepository extends JpaRepository<GroupAdmin, Long> {

    Optional<GroupAdmin> findByAdminFullName(String adminFullName);

}
