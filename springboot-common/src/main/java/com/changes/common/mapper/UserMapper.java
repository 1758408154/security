package com.changes.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.changes.common.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author LiuJunJie
 * @since 2019/11/21 15:48
 */
@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
