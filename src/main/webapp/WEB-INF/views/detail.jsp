<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@taglib  prefix="spring" uri="http://www.springframework.org/tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" type="image/x-icon" href="https://raw.githubusercontent.com/hyunmin0317/instagram-clone/main/src/main/webapp/resources/static/instagram.svg">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/static/jumbotron-narrow.css'/>">
    <link rel="stylesheet" type="text/css" href="<spring:url value='/resources/static/style.css'/>">
    
    <title>Instagram</title>
  </head>
  <body>
  <!-- 네비게이션바 -->
	   <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	      <div class="container">
	        <a class="navbar-brand" href="list">
	          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-instagram" viewBox="0 0 16 16">
				  <path d="M8 0C5.829 0 5.556.01 4.703.048 3.85.088 3.269.222 2.76.42a3.917 3.917 0 0 0-1.417.923A3.927 3.927 0 0 0 .42 2.76C.222 3.268.087 3.85.048 4.7.01 5.555 0 5.827 0 8.001c0 2.172.01 2.444.048 3.297.04.852.174 1.433.372 1.942.205.526.478.972.923 1.417.444.445.89.719 1.416.923.51.198 1.09.333 1.942.372C5.555 15.99 5.827 16 8 16s2.444-.01 3.298-.048c.851-.04 1.434-.174 1.943-.372a3.916 3.916 0 0 0 1.416-.923c.445-.445.718-.891.923-1.417.197-.509.332-1.09.372-1.942C15.99 10.445 16 10.173 16 8s-.01-2.445-.048-3.299c-.04-.851-.175-1.433-.372-1.941a3.926 3.926 0 0 0-.923-1.417A3.911 3.911 0 0 0 13.24.42c-.51-.198-1.092-.333-1.943-.372C10.443.01 10.172 0 7.998 0h.003zm-.717 1.442h.718c2.136 0 2.389.007 3.232.046.78.035 1.204.166 1.486.275.373.145.64.319.92.599.28.28.453.546.598.92.11.281.24.705.275 1.485.039.843.047 1.096.047 3.231s-.008 2.389-.047 3.232c-.035.78-.166 1.203-.275 1.485a2.47 2.47 0 0 1-.599.919c-.28.28-.546.453-.92.598-.28.11-.704.24-1.485.276-.843.038-1.096.047-3.232.047s-2.39-.009-3.233-.047c-.78-.036-1.203-.166-1.485-.276a2.478 2.478 0 0 1-.92-.598 2.48 2.48 0 0 1-.6-.92c-.109-.281-.24-.705-.275-1.485-.038-.843-.046-1.096-.046-3.233 0-2.136.008-2.388.046-3.231.036-.78.166-1.204.276-1.486.145-.373.319-.64.599-.92.28-.28.546-.453.92-.598.282-.11.705-.24 1.485-.276.738-.034 1.024-.044 2.515-.045v.002zm4.988 1.328a.96.96 0 1 0 0 1.92.96.96 0 0 0 0-1.92zm-4.27 1.122a4.109 4.109 0 1 0 0 8.217 4.109 4.109 0 0 0 0-8.217zm0 1.441a2.667 2.667 0 1 1 0 5.334 2.667 2.667 0 0 1 0-5.334z"/>
				</svg>
	          Instagram
	        </a>
	        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
	          <span class="navbar-toggler-icon"></span>
	        </button>
	
	        <left>
	        <div class="collapse navbar-collapse" id="navbarNav">
	          <ul class="navbar-nav">
	            <li class="nav-item">
	              <a class="nav-link" href="upload">
	                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-square-fill" viewBox="0 0 16 16">
	                  <path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm6.5 4.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3a.5.5 0 0 1 1 0z"/>
	                </svg>
	              </a>
	            </li>
	            <li class="nav-item">
	              <a class="nav-link" href="{% url 'facebook:follow' %}">
	                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-plus-fill" viewBox="0 0 16 16">
	                  <path d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
	                  <path fill-rule="evenodd" d="M13.5 5a.5.5 0 0 1 .5.5V7h1.5a.5.5 0 0 1 0 1H14v1.5a.5.5 0 0 1-1 0V8h-1.5a.5.5 0 0 1 0-1H13V5.5a.5.5 0 0 1 .5-.5z"/>
	                </svg>
	              </a>
	
	            </li>
	            <!-- {% if user.is_authenticated %} -->
	            <li class="nav-item">
	                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
	                  <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
	                  <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
	                </svg>
	              </a>
	            </li>
	            
	            <!--
	            <li class="nav-item dropdown">
	              <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
	                환영합니다. {{ user.username }}! 최현민님
	              </a>
	              <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
	                <li><a class="dropdown-item" href="{% url 'facebook:post_create' %}">Post</a></li>
	                <li><a class="dropdown-item" href="{% url 'facebook:post_user' user.username %}">Profile</a></li>
	                <li><a class="dropdown-item" href="{% url 'common:logout' %}">Logout</a></li>
	              </ul>
	            </li>
	            {% else %}
	            -->
	            
	            <li class="nav-item">
	              <a class="nav-item nav-link" href="/instagram-clone/logout">Logout</a>
	            </li>

	          </ul>
	        </div>
	          </left>
	      </div>
	    </nav>
	    
	<!-- 게시물 -->	
		<main role="main">
		      <div class="album bg-light">
		        <div class="container-sm">
		            <div class="album py-5 bg-light">
		              <div class="container">  
		                  <c:forEach items="${list}" var="post">	
			                  <div class="row">
			                      <div class="container">
			                          <div class="card shadow-sm">
			                              <a href="/instagram-clone/detail?name=${post.userName}" class="list-group-item">
											<div>${post.userName }</div>
			                              </a>
			                              <img class="card-img-top" src="<spring:url value='/resources/img/${post.image}'/>" alt=" Card image cap"/>

			                              <div class="card-body">
			 								<h4>${post.title }</h4>
			                                  <p class="card-text">${post.content }</p>    
			                                  <p class="card-text">${post.date }</p>
			                                  <c:if test="${sessionScope.isAdmin == 'true'}"><a href="delete?id=${post.id}">삭제</a><br><br></c:if>
			
			                                  <center>

			                                      <div class="col-6 input-group">
			                                      
			                                      <c:choose> 
			                                     <c:when test="${post.like eq true}">
			                                     
			                                     	 <a class="btn btn-primary" href="likesdelete?id=${post.id}"class="recommend btn btn-sm btn-secondary btn-block" style="width:10%;">
			                                          	 	<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-heart-fill" viewBox="0 0 16 16">
			                                                  <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"/>
			                                                </svg>	 
			                                                ${post.likes }                	
			                                          </a>    	 
			                                      </c:when>
	 	
			                                     <c:otherwise>
			                                     
			                                          	 <a class="btn btn-primary" href="likes?id=${post.id}" class="recommend btn btn-sm btn-secondary btn-block" style="width:10%;">     
			                                          	 	<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16">
			                                                   <path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"/>
			                                               	</svg>  
			                                               	${post.likes }
			                                             </a>
			                                      </c:otherwise>      	 
			                                   	</c:choose>      
			                                   	<form action="comment?id=${post.id}" method="post" >
				                                   	<input type="text" name="content" width="80%" />
				                                   	<input type="submit" value="게시" width="10%" />
			                                   	</form>				
			                                   	 </center>				                                   	
			                                   	                       
			                                   	<div class="mt-3">
			                                   	<c:forEach items="${post.comments}" var="comment">                                			                  		
			                                   	<div class="comment py-2 text-muted">
		                                            <span>
		                                              ${ comment.userName } - ${ comment.content }, ${ comment.date }
		                                            </span> 
		                                        </div>               	
			                                   	</c:forEach>    
			                                   	</div>
			                                   	  <!-- </div>    -->
											        </div>
			                                 
			                              
			                              </div>
			                          </div>
			                      </div>
			                  </div>
			                  <br>
		                  </c:forEach>

		              </div>
		            </div>
		          </div>
		        </div>
		      </div>
		</main>
	
    <!-- Optional JavaScript; choose one of the two! -->
    <!-- Option 1: Bootstrap Bundle with Popper -->
    <!-- Option 2: Separate Popper and Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>

  </body>
</html>