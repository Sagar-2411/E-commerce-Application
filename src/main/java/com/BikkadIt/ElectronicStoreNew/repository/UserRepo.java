package com.BikkadIt.ElectronicStoreNew.repository;

import com.BikkadIt.ElectronicStoreNew.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, String> {
}
