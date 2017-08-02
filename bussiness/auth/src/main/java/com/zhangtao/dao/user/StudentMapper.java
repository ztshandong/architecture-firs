package com.zhangtao.dao.user;

import com.zhangtao.domain.user.Student;
import com.zhangtao.domain.user.StudentExample;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StudentMapper {
    int countByExample(StudentExample example);

    int deleteByExample(StudentExample example);

    @Delete({
        "delete from student",
        "where s_id = #{sId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer sId);

    @Insert({
        "insert into student (s_id, s_name, ",
        "class_id, s_jsonField)",
        "values (#{sId,jdbcType=INTEGER}, #{sName,jdbcType=VARCHAR}, ",
        "#{classId,jdbcType=INTEGER}, #{sJsonfield,jdbcType=VARCHAR})"
    })
    int insert(Student record);

    int insertSelective(Student record);

    List<Student> selectByExample(StudentExample example);

    @Select({
        "select",
        "s_id, s_name, class_id, s_jsonField",
        "from student",
        "where s_id = #{sId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Student selectByPrimaryKey(Integer sId);

    int updateByExampleSelective(@Param("record") Student record, @Param("example") StudentExample example);

    int updateByExample(@Param("record") Student record, @Param("example") StudentExample example);

    int updateByPrimaryKeySelective(Student record);

    @Update({
        "update student",
        "set s_name = #{sName,jdbcType=VARCHAR},",
          "class_id = #{classId,jdbcType=INTEGER},",
          "s_jsonField = #{sJsonfield,jdbcType=VARCHAR}",
        "where s_id = #{sId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Student record);
}