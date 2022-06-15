package com.example.openbankpaydemo.Repository;

import com.example.openbankpaydemo.Entity.TestTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestTable, Object>{
}
