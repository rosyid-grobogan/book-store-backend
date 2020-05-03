package com.rosyid.book.store.account.payload.request;

import lombok.Data;
import java.util.Set;

@Data
public class SignupRequest
{
    private String username;
    private String email;
    private String password;
    private Set<String> role;
}
