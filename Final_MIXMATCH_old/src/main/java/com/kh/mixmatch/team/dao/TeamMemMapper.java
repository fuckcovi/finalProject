package com.kh.mixmatch.team.dao;
 
import org.apache.ibatis.annotations.Select;

import com.kh.mixmatch.team.domain.TeamMemCommand;

public interface TeamMemMapper {
	@Select("SELECT * FROM g_team_member WHERE id=#{id}")
	public TeamMemCommand selectTeamMem(String id);
}
