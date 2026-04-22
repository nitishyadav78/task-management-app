package com.dogratechnologies.task_management_app.RepsitoryPackage;

import com.dogratechnologies.task_management_app.EntityPackage.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User>findByUsername(String username);
}
