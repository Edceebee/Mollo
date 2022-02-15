package com.Mollo.assessment.repositories;

import com.Mollo.assessment.entities.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupMembersRepository extends JpaRepository<GroupMember, Long> {
}
