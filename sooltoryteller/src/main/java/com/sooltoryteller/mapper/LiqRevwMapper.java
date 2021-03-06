package com.sooltoryteller.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sooltoryteller.domain.Criteria;
import com.sooltoryteller.domain.LiqRevwVO;
import com.sooltoryteller.domain.MyLikeVO;
import com.sooltoryteller.domain.MyRevwVO;

public interface LiqRevwMapper {

	//리뷰 등록
	public int insert(LiqRevwVO vo);
	
	//해당 리뷰 가져오기(한개)
	public LiqRevwVO read(Long revwId);
	
	//해당 리뷰 삭제
	public int delete(Long revwId);
	
	//해당 리뷰 
	public int update(LiqRevwVO vo);
	
	//페이징처리된 리뷰리스트 가져오기
	public List<LiqRevwVO> getListWithPaging(
						@Param("liqId") Long liqId,
						@Param("cri") Criteria cri);
	
	//리뷰 총 개수
	public int getCountByLiqId(Long liqId);
	
	//해당회원이 작성한리뷰
	public List<MyRevwVO> getMyListWithPaging(
						@Param("memberId") Long memberId,
						@Param("cri") Criteria cri);
	
	//해당회원이 작성한 리뷰 총 개수
	public int getCountByMemberId(Long memberId);
	
	
}
