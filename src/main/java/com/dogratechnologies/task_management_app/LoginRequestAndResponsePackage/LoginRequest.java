package com.dogratechnologies.task_management_app.LoginRequestAndResponsePackage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginRequest {

    private String username;
    private String password;
}
