package com.github.lowkkid.lodgecore.cabin.service;

import com.github.lowkkid.lodgecore.cabin.model.CabinCreateRequest;
import com.github.lowkkid.lodgecore.cabin.model.CabinDTO;
import com.github.lowkkid.lodgecore.cabin.model.CabinEditRequest;
import java.util.List;

public interface CabinService {

    List<CabinDTO> getAll();

    CabinDTO getById(Long id);

    CabinDTO create(CabinCreateRequest cabinCreateRequest);

    CabinDTO update(Long id, CabinEditRequest request);

    void delete(Long id);

    boolean refreshCabinImages();
}
