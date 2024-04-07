package com.littlemonster.chushiwiki.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.littlemonster.chushiwiki.common.ResponseCode;
import com.littlemonster.chushiwiki.entity.domain.User;
import com.littlemonster.chushiwiki.entity.dto.LoginDTO;
import com.littlemonster.chushiwiki.entity.dto.resetPasswordDTO;
import com.littlemonster.chushiwiki.entity.vo.UserVO;
import com.littlemonster.chushiwiki.exception.CustomException;
import com.littlemonster.chushiwiki.mapper.UserMapper;
import com.littlemonster.chushiwiki.service.UserService;
import com.littlemonster.chushiwiki.utils.CommonUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author white-zhou
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2024-04-06 18:20:42
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    /**
     * 登录
     *
     * @param loginDTO 登录信息
     * @return 登录结果
     */
    @Override
    public UserVO login(LoginDTO loginDTO) {
        if (loginDTO == null) {
            throw new CustomException(ResponseCode.NO_PARAM);
        }
        if (loginDTO.getPassword().length() < 4 || loginDTO.getPassword().length() > 32) {
            throw new CustomException(ResponseCode.PASSWORD_RULE_ERROR);
        }

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, loginDTO.getUserName());
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new CustomException(ResponseCode.NO_USER);
        }

        UserVO userVO = null;
        // 比对密码
        if (CommonUtils.md5(loginDTO.getPassword()).equals(user.getPassword())) {
            userVO = BeanUtil.copyProperties(user, UserVO.class);
        }

        // 生成token
        String token = CommonUtils.createToken(user);
        if (userVO != null) {
            userVO.setToken(token);
        }

        return userVO;
    }



    /**
     * 注册
     * @param loginDTO 注册信息
     * @return 注册结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean register(LoginDTO loginDTO) {
        if (loginDTO == null) {
            throw new CustomException(ResponseCode.NO_PARAM);
        }
        if (loginDTO.getPassword().length() < 4 || loginDTO.getPassword().length() > 32) {
            throw new CustomException(ResponseCode.PASSWORD_RULE_ERROR);
        }

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, loginDTO.getUserName());
        User user = userMapper.selectOne(queryWrapper);
        if (user != null) {
            throw new CustomException(ResponseCode.HAS_USER);
        }

        User insterUser = BeanUtil.copyProperties(loginDTO, User.class);
        insterUser.setPassword(CommonUtils.md5(loginDTO.getPassword()));
        int inserted = userMapper.insert(insterUser);

        return inserted > 0;
    }




    /**
     * 重置密码
     * @param resetPasswordDTO 重置密码信息
     * @return 重置密码结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean resetPassword(resetPasswordDTO resetPasswordDTO) {
        if (resetPasswordDTO == null) {
            throw new CustomException(ResponseCode.NO_PARAM);
        }
        if (resetPasswordDTO.getOldPassword().length() < 4 || resetPasswordDTO.getOldPassword().length() > 32) {
            throw new CustomException(ResponseCode.NO_PARAM);
        }
        if (resetPasswordDTO.getNewPassword().length() < 4 || resetPasswordDTO.getNewPassword().length() > 32) {
            throw new CustomException(ResponseCode.NO_PARAM);
        }
        if (resetPasswordDTO.getConfirmPassword().length() < 4 || resetPasswordDTO.getConfirmPassword().length() > 32) {
            throw new CustomException(ResponseCode.NO_PARAM);
        }
        if (!resetPasswordDTO.getNewPassword().equals(resetPasswordDTO.getConfirmPassword())) {
            throw new CustomException(ResponseCode.NO_PARAM);
        }

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, resetPasswordDTO.getUserName());
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new CustomException(ResponseCode.NO_USER);
        }
        if (!CommonUtils.md5(resetPasswordDTO.getOldPassword()).equals(user.getPassword())) {
            throw new CustomException(400, "旧密码错误");
        }


        user.setPassword(CommonUtils.md5(resetPasswordDTO.getNewPassword()));
        int updated = userMapper.updateById(user);

        return updated > 0;
    }

}




