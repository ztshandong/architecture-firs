package com.zhangtao.dao.user;

import com.zhangtao.domain.user.UserMain;
import com.zhangtao.domain.user.UserMainExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMainMapper {
    int countByExample(UserMainExample example);

    int deleteByExample(UserMainExample example);

    @Delete({
        "delete from u_main",
        "where m_id = #{mId,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long mId);

    @Insert({
        "insert into u_main (m_id, m_flag, ",
        "m_multi_login, m_create_time, ",
        "m_modify_time, m_ts)",
        "values (#{mId,jdbcType=BIGINT}, #{mFlag,jdbcType=INTEGER}, ",
        "#{mMultiLogin,jdbcType=INTEGER}, #{mCreateTime,jdbcType=TIMESTAMP}, ",
        "#{mModifyTime,jdbcType=TIMESTAMP}, #{mTs,jdbcType=TIMESTAMP})"
    })
    int insert(UserMain record);

    int insertSelective(UserMain record);

    List<UserMain> selectByExample(UserMainExample example);

    @Select({
        "select",
        "UserMain.m_id as UserMain_m_id, UserMain.m_flag as UserMain_m_flag, UserMain.m_multi_login as UserMain_m_multi_login, ",
        "UserMain.m_create_time as UserMain_m_create_time, UserMain.m_modify_time as UserMain_m_modify_time, ",
        "UserMain.m_ts as UserMain_m_ts",
        "from u_main UserMain",
        "where UserMain.m_id = #{mId,jdbcType=BIGINT}"
    })
    @ResultMap("BaseResultMap")
    UserMain selectByPrimaryKey(Long mId);

    int updateByExampleSelective(@Param("record") UserMain record, @Param("example") UserMainExample example);

    int updateByExample(@Param("record") UserMain record, @Param("example") UserMainExample example);

    int updateByPrimaryKeySelective(UserMain record);

    @Update({
        "update u_main",
        "set m_flag = #{mFlag,jdbcType=INTEGER},",
          "m_multi_login = #{mMultiLogin,jdbcType=INTEGER},",
          "m_create_time = #{mCreateTime,jdbcType=TIMESTAMP},",
          "m_modify_time = #{mModifyTime,jdbcType=TIMESTAMP},",
          "m_ts = #{mTs,jdbcType=TIMESTAMP}",
        "where m_id = #{mId,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(UserMain record);
}