<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인페이지</title>

<style type="text/css">
.h-login-p{
  padding: 8.5px;
}
.h-body{
  height: 500px;
  width: 100%;
  background-color: rgb(245, 245, 245);
  
}
.h-left{
  height: 450px;
  width: 330px;
  display: inline-block;
}
#h-leftimg{
  height: 480px;
  width: 330px;
  opacity:0.9;
  display: inline-block;
}
.h-right{
height: 450px;
width: 910px;
display: inline-block;
}
.h-login-content{
height: 450px;
width: 450px;
border: 3px solid rgb(181, 135, 189);
border-radius: 25%;
display: inline-block;
margin-left: 170px;
overflow: hidden;
}

.h-content{
  margin: 20px;
}
.login-btn{
  height: 40px;
  width: 160px;
  border: none;
  margin: 10px;
  border-radius: 5%;
  color:white;
  background-color: rgb(181, 135, 189);
}
.snslogin{
  height: 40px;
  width: 350px;
}


.h-modal {
	  display: none;
	  position: fixed; 
	  z-index: 1;
	  padding-top: 100px; 
	  left: 0;
	  top: 0;
	  width: 100%; 
	  height: 100%; 
	  overflow: auto; 
	  background-color: rgb(0,0,0); 
	  background-color: rgba(0,0,0,0.4);
	}
	

	.h-modal-content {
    width: 400px;
    height: 200px;
	  background-color: #f2eff9;
	  margin: auto;
	  padding: 20px;
    text-align: center;
	  border: 3px solid rgb(181, 135, 189);
    border-radius: 3%;
	  
	}

  .h-modal-top{
    height: 30px;
    width: 350px;
  }
  .h-modal-topleft{
    height: 30px;
    width: 170px;
    text-align: left;
    font-weight: bold;
    display: inline-block;
    overflow: hidden;
  }
  .h-modal-topright{
    height: 30px;
    width: 170px;
    text-align: right;
    display: inline-block;
    overflow: hidden;
  }
	.h-modal-bottom{
    width: 320px;
    height: 80px;
    padding: 15px;

  }
	.h-close {
	  color: #aaaaaa;
	  font-size: 20px;
	  font-weight: bold;
	}
	
	.h-close:hover,
	.h-close:focus {
	  color: purple;
	  text-decoration: none;
	  cursor: pointer;
	}

	.h-btn{
	  height: 30px;
	  width: 300px;
	  border: none;
	  color: white;
	  margin: 10px;
    cursor: pointer;
	  background-color: rgb(181, 135, 189);
	}
	
  #h-findPwd-btn{
    cursor: pointer;
  }
  </style>
</head>