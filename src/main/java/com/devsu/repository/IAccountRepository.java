package com.devsu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsu.entities.AccountEntity;

public interface IAccountRepository extends JpaRepository<AccountEntity, String> {

}
