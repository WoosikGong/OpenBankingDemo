package com.example.openbankpaydemo.Repository;

import com.example.openbankpaydemo.Entity.BnkTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<BnkTransaction, Object> {
}
