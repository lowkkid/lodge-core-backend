package com.github.lowkkid.lodgecore.user.service;

import com.github.lowkkid.lodgecore.user.model.UpdatePasswordRequest;
import com.github.lowkkid.lodgecore.user.model.UpdateUserRequest;

public interface AccountService {

    String update(UpdateUserRequest request);

    void updatePassword(UpdatePasswordRequest request);
}
