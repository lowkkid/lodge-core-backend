package com.github.lowkkid.thewildoasisbackend.repository;

import com.github.lowkkid.thewildoasisbackend.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
}

