package com.zhangtao.dao.user;

import java.util.List;

import com.zhangtao.domain.user.UserDetails;
import com.zhangtao.domain.user.UserDetailsExample;
import org.apache.ibatis.annotations.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDetailsMapper {
    @Select({
            "select",
            "UserDetails.d_id as UserDetails_d_id, UserDetails.d_mid as UserDetails_d_mid, ",
            "UserDetails.d_account as UserDetails_d_account, UserDetails.d_name as UserDetails_d_name, ",
            "UserDetails.d_password as UserDetails_d_password ",
            "from u_details UserDetails",
            "where UserDetails.d_account = #{dAccount,jdbcType=VARCHAR}"
    })
    @ResultMap("InfoForTokenMap")
    UserDetails getUserInfoBeforeLogin(String dAccount);

    int countByExample(UserDetailsExample example);

    int deleteByExample(UserDetailsExample example);

    @Delete({
            "delete from u_details",
            "where d_id = #{dId,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long dId);

    @Insert({
            "insert into u_details (d_id, d_mid, ",
            "d_account, d_name, ",
            "d_password, d_salt, ",
            "d_create_time, d_modify_time, ",
            "d_ts)",
            "values (#{dId,jdbcType=BIGINT}, #{dMid,jdbcType=BIGINT}, ",
            "#{dAccount,jdbcType=VARCHAR}, #{dName,jdbcType=VARCHAR}, ",
            "#{dPassword,jdbcType=VARCHAR}, #{dSalt,jdbcType=VARCHAR}, ",
            "#{dCreateTime,jdbcType=TIMESTAMP}, #{dModifyTime,jdbcType=TIMESTAMP}, ",
            "#{dTs,jdbcType=TIMESTAMP})"
    })
    int insert(UserDetails record);

    int insertSelective(UserDetails record);

    List<UserDetails> selectByExample(UserDetailsExample example);

    @Select({
            "select",
            "UserDetails.d_id as UserDetails_d_id, UserDetails.d_mid as UserDetails_d_mid, ",
            "UserDetails.d_account as UserDetails_d_account, UserDetails.d_name as UserDetails_d_name, ",
            "UserDetails.d_password as UserDetails_d_password, UserDetails.d_salt as UserDetails_d_salt, ",
            "UserDetails.d_create_time as UserDetails_d_create_time, UserDetails.d_modify_time as UserDetails_d_modify_time, ",
            "UserDetails.d_ts as UserDetails_d_ts",
            "from u_details UserDetails",
            "where UserDetails.d_id = #{dId,jdbcType=BIGINT}"
    })
    @ResultMap("BaseResultMap")
    UserDetails selectByPrimaryKey(Long dId);

    int updateByExampleSelective(@Param("record") UserDetails record, @Param("example") UserDetailsExample example);

    int updateByExample(@Param("record") UserDetails record, @Param("example") UserDetailsExample example);

    int updateByPrimaryKeySelective(UserDetails record);

    @Update({
            "update u_details",
            "set d_mid = #{dMid,jdbcType=BIGINT},",
            "d_account = #{dAccount,jdbcType=VARCHAR},",
            "d_name = #{dName,jdbcType=VARCHAR},",
            "d_password = #{dPassword,jdbcType=VARCHAR},",
            "d_salt = #{dSalt,jdbcType=VARCHAR},",
            "d_create_time = #{dCreateTime,jdbcType=TIMESTAMP},",
            "d_modify_time = #{dModifyTime,jdbcType=TIMESTAMP},",
            "d_ts = #{dTs,jdbcType=TIMESTAMP}",
            "where d_id = #{dId,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(UserDetails record);
}