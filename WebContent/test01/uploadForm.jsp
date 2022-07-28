<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<%
  request.setCharacterEncoding("UTF-8");
%>    
<html>
<head>
<meta charset="UTF-8">
 <head>
   <title>파일 업로드창</title>
    <style>
    
      form {
      	
      }
	  .upload {
	  	 border: 1px solid black;
	  }
	  
	  .upload__button {
	  	border: 1px solid black;
	  	border-radius: 2px;
	  	background: white;
	  	color: black;
	  	display: inline-block;
	  	width: 100px;
	  	margin: 10px;
	  	text-align: center;
	  }
	  
	  .upload__button:hover {
	  	border: 1px solid black;
	  	border-radius: 2px;
	  	background: black;
	  	color: white;
	  	display: inline-block;
	  	width: 100px;
	  	margin: 10px;
	  	text-align: center;
	  }  
	  
	  .sigle_button {
	  	margin: 10px;
	  }
	  
 </style>
 </head> 
 <body>
   	<div class="upload">
   <form action="${contextPath}/upload.do"  method="post" enctype="multipart/form-data" >	<%--멀티 파트는 무조건 --%>
      <div class="upload__button">파일1</div> <input type="file" name="file1" ><br>
      <div class="upload__button">파일2</div> <input type="file" name="file2" > <br>
      <div class="upload__button">파라미터1</div> <input type="text" name="param1" placeholder="전달하고 싶은 파라미터" > <br>
      <div class="upload__button">파라미터2</div> <input type="text" name="param2" placeholder="전달하고 싶은 파라미터"> <br>
      <div class="upload__button">파라미터3</div> <input type="text" name="param3" placeholder="전달하고 싶은 파라미터"> <br>
	  <div class="sigle_button"><input type="submit" value="업로드" ></div>
	</form>
 	</div>
 </body>
</html>
