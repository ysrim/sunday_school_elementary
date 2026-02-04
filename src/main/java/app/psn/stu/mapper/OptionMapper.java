package app.psn.stu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import app.psn.stu.vo.HomeGuildInfoVO;
import app.psn.stu.vo.HomeGuildListVO;

@Mapper
public interface OptionMapper {

	void pwChg(@Param("mberSn") Integer mberSn, @Param("newPw") String newPw);

}