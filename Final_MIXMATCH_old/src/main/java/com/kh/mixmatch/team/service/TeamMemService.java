package com.kh.mixmatch.team.service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.mixmatch.team.domain.TeamMemCommand;

@Transactional
public interface TeamMemService {
	@Transactional(readOnly=true)
	public TeamMemCommand selectTeamMem(String id);
}
