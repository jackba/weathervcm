package com.cma.intervideo.service;

import java.util.List;

import com.cma.intervideo.pojo.Conference;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public interface IConfService {
	public List<Conference> findConfs(List<ParamVo> params, PageHolder ph);
	public void saveOrUpdate(Conference conf);
}
