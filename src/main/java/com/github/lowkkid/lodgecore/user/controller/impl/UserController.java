package com.github.lowkkid.lodgecore.user.controller.impl;

import com.github.lowkkid.lodgecore.user.controller.UserApi;
import com.github.lowkkid.lodgecore.user.model.UserDTO;
import com.github.lowkkid.lodgecore.user.model.UserRole;
import com.github.lowkkid.lodgecore.user.model.UsernameAndPassword;
import com.github.lowkkid.lodgecore.user.service.UserService;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;

    @Override
    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/employee")
    public ResponseEntity<Void> createEmployee(@RequestBody @Valid UsernameAndPassword usernameAndPassword) {
        userService.createEmployee(usernameAndPassword);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable UUID id) {
        userService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PreAuthorize("hasAuthority('admin')")
    @GetMapping
    public ResponseEntity<Page<UserDTO>> getUsers(@RequestParam(required = false) UserRole role,
                                                  @RequestParam(defaultValue = "1") Integer pageNumber,
                                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                                  @RequestParam(defaultValue = "createdAt") String sortField,
                                                  @RequestParam(defaultValue = "DESC") Sort.Direction sortDirection) {
        var res = userService.findAllDtoByRole(role, pageNumber, pageSize, sortField, sortDirection);
        return ResponseEntity.ok(res);
    }
}
