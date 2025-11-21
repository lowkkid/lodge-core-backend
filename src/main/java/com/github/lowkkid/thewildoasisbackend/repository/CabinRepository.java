package com.github.lowkkid.thewildoasisbackend.repository;

import com.github.lowkkid.thewildoasisbackend.entity.Cabin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CabinRepository extends JpaRepository<Cabin, Long> {
}

