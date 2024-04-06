package com.littlemonster.chushiwiki.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.littlemonster.chushiwiki.common.ResponseCode;
import com.littlemonster.chushiwiki.entity.domain.User;
import com.littlemonster.chushiwiki.entity.dto.LoginDTO;
import com.littlemonster.chushiwiki.entity.vo.UserVO;
import com.littlemonster.chushiwiki.exception.CustomException;
import com.littlemonster.chushiwiki.mapper.UserMapper;
import com.littlemonster.chushiwiki.service.UserService;
import com.littlemonster.chushiwiki.utils.CommonUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
     * @param request
     * @return 登录结果
     */
    @Override
    public UserVO login(LoginDTO loginDTO, HttpServletRequest request) {
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

        // 登录成功，将用户信息存入session
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("token", token);

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
}




