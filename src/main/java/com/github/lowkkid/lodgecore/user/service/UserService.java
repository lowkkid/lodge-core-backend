package com.github.lowkkid.lodgecore.user.service;

import com.github.lowkkid.lodgecore.user.domain.entity.User;
import com.github.lowkkid.lodgecore.user.model.UpdatePasswordRequest;
import com.github.lowkkid.lodgecore.user.model.UpdateUserRequest;
import com.github.lowkkid.lodgecore.user.model.UserDTO;
import com.github.lowkkid.lodgecore.user.model.UserRole;
import com.github.lowkkid.lodgecore.user.model.UsernameAndPassword;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface UserService {

    void createEmployee(UsernameAndPassword usernameAndPassword);

    Page<UserDTO> findAllDtoByRole(
            UserRole role, Integer pageNumber, Integer pageSize, String sortField, Sort.Direction sortDirection);

    User update(UUID id, UpdateUserRequest updateUserRequest);

    void updatePassword(UUID id, UpdatePasswordRequest updatePasswordRequest);

    void deleteEmployee(UUID id);
}
