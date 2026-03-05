package app.psn.mng.feed.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.psn.mng.feed.mapper.MngFeedMapper;
import app.psn.mng.feed.service.MngFeedService;
import app.psn.mng.feed.vo.MngReqFeedVO;
import app.psn.mng.feed.vo.MngResFeedVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("mngFeedService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MngFeedServiceImpl implements MngFeedService {

	private final MngFeedMapper mngFeedMapper;

	@Override
	public List<MngResFeedVO> sltFeedList() {

		return mngFeedMapper.sltFeedList();

	}

	@Override
	public MngResFeedVO sltFeed(Integer postSn) {

		return mngFeedMapper.sltFeed(postSn);

	}

	@Override
	public void crtFeedDo(MngReqFeedVO mngReqFeedVO) {

		mngFeedMapper.crtFeedDo(mngReqFeedVO);

	}

	@Override
	public void udtFeedDo(MngReqFeedVO mngReqFeedVO) {

		mngFeedMapper.udtFeedDo(mngReqFeedVO);

	}

}