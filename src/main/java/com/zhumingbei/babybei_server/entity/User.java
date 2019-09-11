package com.zhumingbei.babybei_server.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhumingbei.babybei_server.common.UserPrincipal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -8592740736423969002L;
    private Integer id;
    private String username;
    private String password;
    private String email;
    private Integer isDisable;
    private Integer isMatched;
    private Integer partnerId;
    private List<Role> roles;

    public User(UserPrincipal user) {
        this(user.getUserId(), user.getUsername(), null, user.getIsMatched(), user.getPartnerId(), user.getRoles());
    }
}
