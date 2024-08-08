package com.kataproject.bokareva.withBoot.repository;


import com.kataproject.bokareva.withBoot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
