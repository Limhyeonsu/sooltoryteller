
<script type="text/javascript">
	// 로그인이 안된 상태면 로그인페이지로 넘어가게
	let msg = "${msg}";
	if (msg != "") {
		alert(msg);
		location.href = "/login";
	}
</script>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="/WEB-INF/views/include/mypageSidebar.jsp"%>

<style>

.s-bbst-container {
	width: 800px;
	height: 700px;
	margin: 0 auto;
}

.s-bbst-item-container {
	height: 180px;
	width: 180px;
	margin: 20px 0 30px 60px;
	display: inline-block;
	position: relative;
	float: left;
	cursor: pointer;
}

.s-bbst-img {
	height: 180px;
	width: 180px;
	display: block;
}

.s-bbst-item-overlay {
	position: absolute;
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
	height: 180px;
	width: 180px;
	opacity: 0;
	transition: .3s ease;
	background-color: rgb(181, 135, 189);
}

.s-bbst-item-container:hover .s-bbst-item-overlay {
	opacity: 0.9;
}

.s-bbst-item-overlay-info {
	font-size: 12px;
	position: absolute;
	top: 50%;
	left: 50%;
	width: 180px;
	transform: translate(-50%, -50%);
	text-align: center;
	display: inline-block;
}

.s-bbst-item-overlay-span {
	color: white;
	font-size: 12px;
}


.d-paging {
	float: right;
}

.d-paging ul {
	list-style: none;
	margin: 10px;
}

.d-paging li {
	float: left;
}

.d-paging-btn-active {
	text-align: center;
	width: 25px;
	height: 25px;
	background-color: rgb(181, 135, 189);
	border-radius: 50%;
}

.d-paging-btn-active a {
	color: white;
}

.d-paging-btn-none {
	text-align: center;
	width: 25px;
	height: 25px;
	border-radius: 50%;
}

</style>
</head>
<body>
			<h2>좋아요 누른 게시글</h2>
			<div class="s-bbst-container">
				<div class="s-bbst-container-box"></div>
			</div>
			
			<!-- 페이징 처리 -->
			<div class="d-paging" style="width: 700px; margin-right: 25px;"></div>
		</div>
	</div>
</div>

<%@include file="/WEB-INF/views/include/footer.jsp" %>

<script type="text/javascript" src="/resources/js/bbstLike.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var memberIdValue = "<c:out value='${member.memberId}' />";
	var bbstBox = $(".s-bbst-container-box");
	var paging = $(".d-paging");
	var pageNum = 1;
	showMyList(1);
	
	// 페이지 번호 출력
	function showMyLikedPostPage(myLikeCnt) {
		console.log(myLikeCnt);
		var endNum = Math.ceil(pageNum / 5.0) * 5;
		var startNum = endNum - 4;
		var prev = startNum != 1;
		var next = false;
		
		if(endNum * 9 >= myLikeCnt) {
			endNum = Math.ceil(myLikeCnt / 9.0);
		}
		if(endNum * 9 < myLikeCnt){
			next = true;
		}
		
		var str = "<ul class='d-paging'>";
		if(prev){
			str += "<li class='d-paging-btn-none'><a href='" + (startNum - 1)+ "'>&#60;</a></li>";
		}
		for(let i=startNum; i <=endNum; i++){
			let active = pageNum == i? "active":"none";
			str+="<li class='d-paging-btn-"+active +"'><a href="+i+">"+i+"</a></li>";
		}
		if(next){
			str+="<li class='d-paging-btn-none'><a href='"+ (endNum + 1)+"'>&#62;</a></li>";
		}
		str += "</ul>";
		paging.html(str);
	}
	paging.on("click", "li a", function(e) {
		e.preventDefault();
		let targetPageNum =$(this).attr("href");
		pageNum = targetPageNum;
		showMyList(pageNum);
	});
	
	// 리스트 출력
	function showMyList(page) {
		bbstLikeService.getMyBbstLike({memberId : memberIdValue, page : page || 1},
			function(myLikeCnt, myLikeList) {
			
			if(page == -1) {
				pageNum = Math.ceil(myLikeCnt / 9.0);
				showMyList(pageNum);
				return;
			}
			
			var str = "";
			if(myLikeList == null || myLikeList.length == 0) {
				bbstBox.html("<p style='margin-top: 35px;'>좋아요를 누른 게시글이 없습니다.</p><br />");
				return;
			}
			
			for(var i = 0, len = myLikeList.length || 0; i < len; i++) {
				str += '<div class="s-bbst-item-container" data-bbstid="' + myLikeList[i].bbstId + '">';
				str += '<div class="s-bbst-img-div"><img class="s-bbst-img" src="' + myLikeList[i].cnImg + '" /></div>';
				str += '<div class="s-bbst-item-overlay"><div class="s-bbst-item-overlay-info"><i class="fas fa-eye" style="color: white;"></i> <span class="s-bbst-item-overlay-span">' + myLikeList[i].viewCnt + '</span>&nbsp;';
				str += '<i class="fas fa-heart" style="color: white;"></i> <span class="s-bbst-item-overlay-span">' + myLikeList[i].likeCnt + '</span>&nbsp;';
				str += '<i class="fas fa-comment-dots" style="color: white;"></i> <span class="s-bbst-item-overlay-span">' + myLikeList[i].replyCnt + '</span></div></div></div>';
			}
			bbstBox.html(str);
			showMyLikedPostPage(myLikeCnt);
		});
	}
	
	// 게시글 선택 시 해당 게시글로 이동
	bbstBox.on("click", ".s-bbst-item-container", function(e) {
		var targetBbstId = $(this).data("bbstid");
		location.href = location.href = "/cheers/get?bbstId=" + targetBbstId;
	});
});
</script>
</body>
</html>