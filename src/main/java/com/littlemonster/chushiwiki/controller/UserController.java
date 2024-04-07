package com.littlemonster.chushiwiki.controller;

import com.littlemonster.chushiwiki.common.ResponseCode;
import com.littlemonster.chushiwiki.entity.dto.LoginDTO;
import com.littlemonster.chushiwiki.entity.dto.resetPasswordDTO;
import com.littlemonster.chushiwiki.entity.vo.UserVO;
import com.littlemonster.chushiwiki.service.UserService;
import com.littlemonster.chushiwiki.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: white-zhou
 * @date: 2024-04-06 18:51
 **/
@RestController
@RequestMapping("/user")
@Tag(name = "用户管理")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }





    /**
     * 登录
     * @param loginDTO 登录信息
     * @return 登录结果
     */
    @PostMapping("/login")
    @Operation(summary = "登录")
    public Result login(@RequestBody LoginDTO loginDTO) {
        if (loginDTO == null) {
            return Result.failure(ResponseCode.NO_PARAM);
        }
        if (loginDTO.getPassword().length() < 4 || loginDTO.getPassword().length() > 32) {
            return Result.failure(ResponseCode.PASSWORD_RULE_ERROR);
        }

        UserVO userVO = userService.login(loginDTO);
        if (userVO == null) {
            return Result.failure(ResponseCode.LOGIN_ERROR);
        }

        return Result.success(userVO);
    }





    /**
     * 注册
     * @param loginDTO 注册信息
     * @return 注册结果
     */
    @PostMapping("/register")
    @Operation(summary = "注册")
    public Result register(@RequestBody LoginDTO loginDTO) {
        if (loginDTO == null) {
            return Result.failure(ResponseCode.NO_PARAM);
        }
        if (loginDTO.getPassword().length() < 4 || loginDTO.getPassword().length() > 32) {
            return Result.failure(ResponseCode.PASSWORD_RULE_ERROR);
        }

        boolean b = userService.register(loginDTO);
        if (!b) {
            return Result.failure(500, "注册失败");
        }

        return Result.success();
    }




    /**
     * 重置密码
     * @param resetPasswordDTO 重置密码信息
     * @return 重置密码结果
     */
    @PostMapping("/reset")
    @Operation(summary = "重置密码")
    public Result resetPassword(@RequestBody resetPasswordDTO resetPasswordDTO) {
        if (resetPasswordDTO == null) {
            return Result.failure(ResponseCode.NO_PARAM);
        }
        if (resetPasswordDTO.getOldPassword().length() < 4 || resetPasswordDTO.getOldPassword().length() > 32) {
            return Result.failure(ResponseCode.PASSWORD_RULE_ERROR);
        }
        if (resetPasswordDTO.getNewPassword().length() < 4 || resetPasswordDTO.getNewPassword().length() > 32) {
            return Result.failure(ResponseCode.PASSWORD_RULE_ERROR);
        }
        if (resetPasswordDTO.getConfirmPassword().length() < 4 || resetPasswordDTO.getConfirmPassword().length() > 32) {
            return Result.failure(ResponseCode.PASSWORD_RULE_ERROR);
        }
        if (!resetPasswordDTO.getNewPassword().equals(resetPasswordDTO.getConfirmPassword())) {
            return Result.failure(400, "两次密码不一致");
        }

        boolean b = userService.resetPassword(resetPasswordDTO);
        if (!b) {
            return Result.failure(500, "重置密码失败");
        }

        return Result.success();
    }
}
