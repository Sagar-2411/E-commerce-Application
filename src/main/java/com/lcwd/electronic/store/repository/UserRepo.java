package com.lcwd.electronic.store.repository;

import com.lcwd.electronic.store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepo extends JpaRepository<User, String> {
}
