package com.hostEvents.transactionManagement.repo;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hostEvents.transactionManagement.entity.EventMasterEntity;

@Repository
public interface EeventMasterRepository extends JpaRepository<EventMasterEntity, BigInteger> {

}
