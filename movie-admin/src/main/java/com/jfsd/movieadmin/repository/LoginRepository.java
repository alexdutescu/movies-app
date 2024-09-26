package com.jfsd.movieadmin.repository;

import com.jfsd.movieadmin.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, String> {

    Optional<Login> findByEmailAndPassword(String email, String password);
}
