package com.github.lowkkid.thewildoasisbackend.service;

import com.github.lowkkid.thewildoasisbackend.dto.CabinDTO;
import com.github.lowkkid.thewildoasisbackend.entity.Cabin;
import com.github.lowkkid.thewildoasisbackend.exception.NotFoundException;
import com.github.lowkkid.thewildoasisbackend.mapper.CabinMapper;
import com.github.lowkkid.thewildoasisbackend.repository.CabinRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CabinService {

    private final CabinRepository cabinRepository;
    private final CabinMapper cabinMapper;

    public List<CabinDTO> getAll() {
        return cabinRepository.findAll().stream()
                .map(cabinMapper::toDto)
                .collect(Collectors.toList());
    }

    public CabinDTO getById(Long id) {
        Cabin cabin = cabinRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cabin with id " + id + " not found"));
        return cabinMapper.toDto(cabin);
    }

    @Transactional
    public CabinDTO create(CabinDTO cabinDTO) {
        Cabin cabin = cabinMapper.toEntity(cabinDTO);
        Cabin savedCabin = cabinRepository.save(cabin);
        return cabinMapper.toDto(savedCabin);
    }

    @Transactional
    public CabinDTO update(Long id, CabinDTO cabinDTO) {
        Cabin existingCabin = cabinRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cabin with id " + id + " not found"));
        Cabin cabin = cabinMapper.toEntity(cabinDTO);
        cabin.setId(existingCabin.getId());
        Cabin savedCabin = cabinRepository.save(cabin);
        return cabinMapper.toDto(savedCabin);
    }

    public void delete(Long id) {
        Cabin cabin = cabinRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cabin with id " + id + " not found"));
        cabinRepository.delete(cabin);
    }
}
