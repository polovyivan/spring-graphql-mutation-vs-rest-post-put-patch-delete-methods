package com.polovyi.ivan.repository;

import com.polovyi.ivan.entity.PurchaseTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseTransactionRepository extends JpaRepository<PurchaseTransactionEntity, String> {

}
