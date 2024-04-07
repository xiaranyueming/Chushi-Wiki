package com.littlemonster.chushiwiki.entity.dto;

import lombok.Data;

/**
 * @author: white-zhou
 * @date: 2024-04-07 16:04
 **/
@Data
public class resetPasswordDTO {

    private String userName;

    private String oldPassword;

    private String newPassword;

    private String confirmPassword;
}
