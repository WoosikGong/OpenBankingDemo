package com.example.openbankpaydemo.Repository;

import com.example.openbankpaydemo.Entity.BnkTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<BnkTransaction, Object> {
}
