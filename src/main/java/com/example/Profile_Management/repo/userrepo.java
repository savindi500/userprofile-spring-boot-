package com.example.Profile_Management.repo;

import com.example.Profile_Management.entity.user;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userrepo extends JpaRepository<user,Integer> {


}
