package com.github.lowkkid.thewildoasisbackend.service;

import com.github.lowkkid.thewildoasisbackend.entity.Cabin;
import com.github.lowkkid.thewildoasisbackend.exception.NotFoundException;
import com.github.lowkkid.thewildoasisbackend.repository.CabinRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CabinService {

    private final CabinRepository cabinRepository;

    public List<Cabin> getAll() {
        return cabinRepository.findAll();
    }

    public Cabin getById(Long id) {
        return cabinRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cabin with id " + id + " not found"));
    }

    public Cabin create(Cabin cabin) {
        return cabinRepository.save(cabin);
    }

    public Cabin update(Long id, Cabin cabin) {
        Cabin existingCabin = getById(id);
        cabin.setId(existingCabin.getId());
        return cabinRepository.save(cabin);
    }

    public void delete(Long id) {
        Cabin cabin = getById(id);
        cabinRepository.delete(cabin);
    }
}
