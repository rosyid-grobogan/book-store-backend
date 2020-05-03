package com.rosyid.book.store.account.payload.request;

import lombok.Data;


@Data
public class LoginRequest
{
    private String username;
    private String password;
}
