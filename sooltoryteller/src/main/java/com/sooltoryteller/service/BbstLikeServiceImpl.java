package com.sooltoryteller.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sooltoryteller.domain.BbstLikeCriteria;
import com.sooltoryteller.domain.MyBbstLikePageDTO;
import com.sooltoryteller.mapper.BbstLikeMapper;
import com.sooltoryteller.mapper.BbstMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class BbstLikeServiceImpl implements BbstLikeService {

	@Setter(onMethod_ = @Autowired)
	private BbstLikeMapper mapper;
	
	// 좋아요
	@Transactional
	@Override
	public int likeBbst(
		@Param("bbstId") Long bbstId,
		@Param("memberId") Long memberId) {
		
		log.info("========== LIKE BBST ==========");
		mapper.likeBbst(bbstId, memberId);
		// 좋아요수 업데이트
		mapper.updateLikeCnt(bbstId, 1);
		// 좋아요수 가져오기
		int result = mapper.getBbstLikeCnt(bbstId);
		return result;
	}
	
	// 좋아요 취소
	@Transactional
	@Override
	public boolean cancelLikeBbst(
		@Param("bbstId") Long bbstId,
		@Param("memberId") Long memberId) {
		
		log.info("========== CANCEL LIKE BBST ==========");
		// 좋아요수 업데이트
		mapper.updateLikeCnt(bbstId, -1);
		// 좋아요수 가져오기
		mapper.getBbstLikeCnt(bbstId);
		return mapper.cancelLikeBbst(bbstId, memberId) == 1;
	}
	
	// 좋아요 상태
	@Override
	public boolean bbstLikeStus(
		@Param("bbstId") Long bbstId,
		@Param("memberId") Long memberId) {
		
		log.info("========== BBST LIKE STATUS ==========");
		return mapper.bbstLikeStus(bbstId, memberId) == 1;
	}
	
	// 마이페이지
	// 내가 좋아요 누른 게시글 리스트
	public MyBbstLikePageDTO getMyBbstLike(
		@Param("cri") BbstLikeCriteria cri,
		@Param("memberId") Long memberId) {
		
		log.info("========== MEMBERID " + memberId + "'S BBST LIKE PAGE ==========");
		return new MyBbstLikePageDTO(
			mapper.getMyBbstLikeTotalCount(memberId),
			mapper.getMyBbstLike(cri, memberId));
	}
}
