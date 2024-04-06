package com.littlemonster.chushiwiki.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.littlemonster.chushiwiki.entity.domain.User;
import com.littlemonster.chushiwiki.entity.dto.LoginDTO;
import com.littlemonster.chushiwiki.entity.vo.UserVO;

/**
* @author white-zhou
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2024-04-06 18:20:42
*/
public interface UserService extends IService<User> {


    /**
     * 登录
     * @param loginDTO 登录信息
     * @return 登录结果
     */
    UserVO login(LoginDTO loginDTO);



    /**
     * 注册
     * @param loginDTO 注册信息
     * @return 注册结果
     */
    boolean register(LoginDTO loginDTO);

}
