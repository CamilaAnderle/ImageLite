package io.github.CamilaAnderle.imageliteapi.domain.service;

import io.github.CamilaAnderle.imageliteapi.domain.AccessToken;
import io.github.CamilaAnderle.imageliteapi.domain.entity.User;

public interface UserService {
    User getByEmail(String email);
    User save(User user);
    AccessToken autheticate(String email, String password);

}
