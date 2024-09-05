package com.ezpay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ezpay.entity.Ticket;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
          
	public List<Ticket> findAllByUserId(Long userId);
}
