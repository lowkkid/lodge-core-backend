package com.github.lowkkid.thewildoasisbackend.repository;

import com.github.lowkkid.thewildoasisbackend.entity.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingRepository extends JpaRepository<Setting, Long> {
}

