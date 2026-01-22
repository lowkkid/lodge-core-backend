package com.github.lowkkid.lodgecore.user.controller.impl;

import com.github.lowkkid.lodgecore.user.controller.AccountApi;
import com.github.lowkkid.lodgecore.user.model.UpdatePasswordRequest;
import com.github.lowkkid.lodgecore.user.model.UpdateUserRequest;
import com.github.lowkkid.lodgecore.user.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController implements AccountApi {

    private final AccountService accountService;

    @Override
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> update(@ModelAttribute UpdateUserRequest request) {
        var newJwt = accountService.update(request);
        return ResponseEntity.ok(newJwt);
    }

    @Override
    @PatchMapping("/password")
    public ResponseEntity<Void> updatePassword(@RequestBody UpdatePasswordRequest request) {
        accountService.updatePassword(request);
        return ResponseEntity.noContent().build();
    }
}
