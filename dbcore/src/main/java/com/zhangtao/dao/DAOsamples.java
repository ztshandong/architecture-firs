/*package com.zhangtao.dao;

import com.zhangtao.common.Snowflake;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("commontools")
public interface CommonTools {
    @RequestMapping("/commontools/getid/")
    long getid();

    @RequestMapping("/commontools/sha512")
    String sha512(@RequestParam(value = "text") String text);

    @RequestMapping("/commontools/encodeHex")
    String encodeHex(@RequestParam(value = "text") String text);

    @RequestMapping("/commontools/decodeHex")
    String decodeHex(@RequestParam(value = "text") String text);

    @RequestMapping("/commontools/encodeBase64")
    String encodeBase64(@RequestParam(value = "text") byte[] text);

    @RequestMapping("/commontools/decodeBase64")
    byte[] decodeBase64(@RequestParam(value = "text") String text);

}
*/

/*
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

@Select({
        "select ",
        "a.c_id as cid, a.c_name, a.teacher_id,",
        "b.s_id as sid, b.s_name,b.s_jsonField,",
        "c.t_id as tid, c.t_name",
        "from class a ",
        "left join student b ",
        "on a.c_id=b.class_id ",
        "left join teacher c ",
        "on a.teacher_id=c.t_id ",
        "where a.c_id = #{cId,jdbcType=INTEGER}"
})
@ResultMap("ClassesResultMap2")
    Classes getClasses(Integer cId);
*/



/*
Mapper.xml
<resultMap type="com.example.demo.domain.Classes" id="ClassesResultMap2">
<id property="cId" column="cid"/>
<result property="cName" column="c_name"/>
<result property="teacherId" column="teacher_id"/>
<association property="teacher" javaType="com.example.demo.domain.Teacher">
<id property="tId" column="tid"/>
<result property="tName" column="t_name"/>
</association>
<collection property="student" ofType="com.example.demo.domain.Student">
<id property="sId" column="sid"/>
<result property="sName" column="s_name"/>
<result property="jsonField" column="s_jsonfield" javaType="com.example.demo.domain.JsonField"/>
</collection>
</resultMap>
*/
