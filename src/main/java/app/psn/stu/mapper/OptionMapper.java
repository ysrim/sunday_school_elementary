package app.psn.stu.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OptionMapper {

	void pwChg(@Param("mberSn") Integer mberSn, @Param("newPw") String newPw);

}