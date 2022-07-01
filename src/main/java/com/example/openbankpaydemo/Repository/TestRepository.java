package com.example.openbankpaydemo.Repository;

import com.example.openbankpaydemo.Entity.TestTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<TestTable, Object>{
}