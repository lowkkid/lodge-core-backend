package com.github.lowkkid.lodgecore.cabin.service.impl;

import static com.github.lowkkid.lodgecore.common.utils.Constants.RESOURCE_WITH_ID_NOT_FOUND;
import static com.github.lowkkid.lodgecore.common.utils.UrlUtils.extractFileName;

import com.github.lowkkid.lodgecore.cabin.domain.entity.Cabin;
import com.github.lowkkid.lodgecore.cabin.domain.repository.CabinRepository;
import com.github.lowkkid.lodgecore.cabin.mapper.CabinMapper;
import com.github.lowkkid.lodgecore.cabin.model.CabinCreateRequest;
import com.github.lowkkid.lodgecore.cabin.model.CabinDTO;
import com.github.lowkkid.lodgecore.cabin.model.CabinEditRequest;
import com.github.lowkkid.lodgecore.cabin.service.CabinService;
import com.github.lowkkid.lodgecore.common.exception.NotFoundException;
import com.github.lowkkid.lodgecore.minio.service.MinioService;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class CabinServiceImpl implements CabinService {

    private static final String CABIN_IMAGES_PREFIX = "cabins/";

    private final CabinRepository cabinRepository;
    private final CabinMapper cabinMapper;
    private final MinioService minioService;

    @Override
    public List<CabinDTO> getAll() {
        return cabinRepository.findAll().stream()
                .map(cabinMapper::toDto)
                .toList();
    }

    @Override
    public CabinDTO getById(Long id) {
        Cabin cabin = cabinRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(RESOURCE_WITH_ID_NOT_FOUND, "Cabin", id)));
        return cabinMapper.toDto(cabin);
    }

    @Override
    @Transactional
    public CabinDTO create(CabinCreateRequest cabinCreateRequest) {
        Cabin cabin = cabinMapper.toEntity(cabinCreateRequest);
        Cabin savedCabin = cabinRepository.save(cabin);
        if (cabinCreateRequest.getImage() == null) {
            return cabinMapper.toDto(savedCabin);
        }
        String url = minioService.uploadFile(cabinCreateRequest.getImage(), CABIN_IMAGES_PREFIX + savedCabin.getId());
        savedCabin.setImage(url);
        // here I don't need to call save() again, because dirty check will reflect changes to database automatically
        return cabinMapper.toDto(savedCabin);
    }

    @Override
    @Transactional
    public CabinDTO update(Long id, CabinEditRequest cabinEditRequest) {
        Cabin existingCabin = cabinRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(RESOURCE_WITH_ID_NOT_FOUND, "Cabin", id)));
        Cabin cabin = cabinMapper.toEntity(cabinEditRequest);
        cabin.setId(existingCabin.getId());
        if (cabinEditRequest.getImage() == null) {
            cabin.setImage(existingCabin.getImage());
        } else {
            if (existingCabin.getImage() != null) {
                minioService.deleteFile(CABIN_IMAGES_PREFIX + extractFileName(existingCabin.getImage()));
            }
            minioService.deleteFile(CABIN_IMAGES_PREFIX + cabin.getId());
            String url = minioService.uploadFile(cabinEditRequest.getImage(),
                    CABIN_IMAGES_PREFIX + existingCabin.getId());
            cabin.setImage(url);
        }
        Cabin savedCabin = cabinRepository.save(cabin);
        return cabinMapper.toDto(savedCabin);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Cabin cabin = cabinRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(RESOURCE_WITH_ID_NOT_FOUND, "Cabin", id)));
        if (cabin.getImage() != null) {
            minioService.deleteFile(CABIN_IMAGES_PREFIX + extractFileName(cabin.getImage()));
        }
        cabinRepository.delete(cabin);

    }

    @Override
    @Transactional
    public boolean refreshCabinImages() {
        try {
            AtomicBoolean hasErrors = new AtomicBoolean(false);
            List<Cabin> allCabins = cabinRepository.findAll();

            var newUrls = new ConcurrentHashMap<Long, String>(allCabins.size());
            var tasks = allCabins.stream()
                    .map(cabin -> CompletableFuture.runAsync(() -> {
                        var newUrl = minioService.generateDownloadUrlWithRetry(CABIN_IMAGES_PREFIX + cabin.getId());
                        newUrls.put(cabin.getId(), newUrl);
                    })).toList();

            CompletableFuture.allOf(tasks.toArray(new CompletableFuture[allCabins.size()])).join();
            allCabins.forEach(cabin -> {
                var newUrl = newUrls.get(cabin.getId());
                if (newUrl == null) {
                    log.info("Failed to generate download url for cabin {}", cabin.getId());
                    hasErrors.set(true);
                } else {
                    cabin.setImage(newUrl);
                }

            });
            cabinRepository.saveAll(allCabins);
            return !hasErrors.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}