package com.sooltoryteller.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 게시글 집계
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BbstCntVO {

	// 게시글 일련번호 
	private Long bbstId;

	// 조회수 
	private int viewCnt = 0;

	// 댓글수 
	private int replyCnt = 0;

	// 좋아요수 
	private int likeCnt = 0;

	// 등록일시 
	private Date regdate;

	// 변경일시 
	private Date updatedate;
}