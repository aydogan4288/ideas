<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 

<!DOCTYPE html>
<html>
<head>
<style type="text/css">
*{
	padding:0;
	margin:0;
	font-family: arial;
	font-weight: 100;
}

.header{
	width:100%;
	text-align: center;
	padding-top:20px;
	height:80px;
	border-bottom:4px solid #4190af;
	margin-bottom:40px;
	box-shadow: 0px 0px 40px #c7c7c7;
}

.header h2{
	font-size: 18px;
	padding: 10px 0px;
}

h3{
	color: #4CA170;
}

.nav{
	display:flex;
	width:100%;
}

.nav a{
	border:2px solid #4CA170;
	padding:10px 10px 10px 10px;
	transition:0.3s;
	width:30%;
}

.edit, .review {
	width:60px;
	border:2px solid #2B1A2E;
}

a:link{
	text-decoration: none;
}

.nav a, .edit, .review{
	height:20px;
	border-radius:4px;
	text-align: center;
	color:#004080;
	margin:0px 20px;
}

.nav a:hover{
	background-color:#ededed;
}

.nav_contain{
	width:100%;
	display:flex;
}

.box_contain{
	margin-top: 40px;
	margin-bottom: 40px;
	display:flex;
	justify-content: center;
	
}

.dashboard{
	margin:auto;
	justify-content: center;
}

.box{
	width:80%;
	min-height: 500px;
	border-radius: 6px;
	box-shadow: 0px 0px 30px #c7c7c7;
}

.box h2{
	text-align: center;
	padding:20px 0px;
	border-bottom:2px solid #4190af;
}

table{
	width:85%;
	margin: 40px auto;
	border-collapse: collapse;
	border-radius:10px;
}

table a {
	color:#004080;
	text-decoration: none;
}

table a:hover{
	
}

table{
	padding:10px 10px;
	border:1px solid #ddd;
	border-collapse: collapse;
	text-align: left;
}

tr:nth-child(even) {
	background-color:#97b7fb;
}

.form_contain{
	text-align:center;
	width:50%;
	margin:
	50px auto;
}

label{
	font-size: 20px;
}
.form_contain input, textarea{
	width:100%;
	margin-top:10px;
	margin-bottom:20px;
	border-radius:3px;
	border:1px solid grey;
	font-size: 18px;
}

.form_contain input{
	text-align:center;
	height:40px;

}

.form_contain textarea {
	height:100px;
	text-align:center;
	padding:20px 2%;
	width:96%;
}

.submit{
	background-color:#7ab087;
	color:white;
	transition:.03s;
}

.submit:hover{
	background-color:#587f63;
	
}

.login_contain{
	margin-top:20px;
	display:flex;
}

.login_box{
	width:50%;
}

.login_box h3{
	padding-top:20px;
	text-align: center;
	font-size: 24px;
}

.login_box input{
	display:block;
	margin:auto;
	width:70%;
	height:35px;
	font-size: 16px;
	text-align: center;
	margin-bottom: 20px;
	border:1px solid grey;
	border-radius: 3px;

}

.login_box h3{
	margin-bottom: 20px;
}

.login_box .submit{
	background-color: green;
	border-style:none !important;
	transition:.08s;
}

.login_box .submit:hover{
	background-color: #5ec760;
}

.errors{
	margin:auto;
	text-align:center;
	display:block;
	width:66%;
	color:white;
	background-color: #aa0001;
	border-radius:4px;
	padding:2%;
	opacity:50%;
}

.remove{
	color:#c20001;
}

.t1, .t2{
	vertical-align:top;
	display:inline-block;
	padding:20px;
}

.t1{
	width:70%;
}

.acenter{
	text-align: center;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" media="screen" href="css/main.css" />
</head>
<body>
	<div class="header">
		<h1>Showing Idea</h1>
	</div>
	
	<div class="nav_contain">
		<div class="nav dashboard">
			<a href="/dashboard">Dashboard</a>
			<a href="/logout">Logout</a>
		</div>
	</div>
	
	<div class="box_contain">
		<div class="box">
			<h2>Ideas</h2>
			<div class="table_contain">
				<table>
					<tr>
						<th>Ideas</th>
						<th>Description</th>
						<th>Created By:</th>
					</tr>
					<tr>
						<td><c:out value="${ idea.name }"/></td>
						<td><c:out value="${ idea.description }"/></td>
						<td><c:out value="${ idea.user.username }"/></td>
					</tr>
				</table>
			</div>
		<h2></h2>
			<div class="table_contain">
				<table>
					<tr>
						<th>Users Who Liked this Idea</th>
					</tr>
					<tr>
						<td>
							<c:forEach items="${ users }" var="user">
								${user.username } , 
							</c:forEach>	
						</td>
					</tr>
				</table>
			</div>	
		</div>
	</div>
</body>
</html>