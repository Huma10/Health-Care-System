<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<nav class="navbar navbar-expand-lg navbar-light bg-dark ">

  <a class="navbar-brand" href="#">
   
   <b class="text-white ml-3 font-weight-bold"><i class="fas fa-clinic-medical"></i> Health Care System</b>
  </a>
  
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <div class="collapse navbar-collapse" id="navbarText">
			<ul class="ml-auto nav justify-content-end">
		<li class="nav-item"><h6 class="text-white">Today's Date</h6><h6 class="text-white ml-3 font-weight-bold" id="h2"></h6></li>
			
			</ul>
			
		</div>
    
  </div>
</nav>

<nav class="navbar navbar-expand-md bg-white" >
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto navbar-center">
      <li class="nav-item active">
        <a class="nav-link" style="color: black;" href="<c:url value="/home"/>">Home <span class="sr-only">(current)</span></a>
      </li>
     
      
     <c:if test="${sessionScope.user != null}">
						<c:if test="${sessionScope.user.roleId == 1}">
								<li class="nav-item "><a class="nav-link " style="color: black;"
								href="<c:url value="/home/login/center"/>">Add Center</a></li>
								<li class="nav-item "><a class="nav-link " style="color: black;"
								href="<c:url value="/home/login/center/search"/>">View Center</a></li>
								<li class="nav-item "><a class="nav-link " style="color: black;"
								href="<c:url value="/home/login/test"/>">Add Test</a></li>
								<li class="nav-item "><a class="nav-link " style="color: black;"
								href="<c:url value="/home/login/test/search"/>">View Test</a></li>
								<li class="nav-item "><a class="nav-link " style="color: black;"
								href="<c:url value="/home/login/appointment/search"/>">Approve Appointment</a></li>
								<li class="nav-item "><a class="nav-link " style="color: black;"
								href="<c:url value="/home/login/contact/search"/>">Contact</a></li>
						</c:if>
						<c:if test="${sessionScope.user.roleId == 2}">
							<li class="nav-item "><a class="nav-link " style="color: black;"
								href="<c:url value="/home/login/appointment"/>">Add Appointment</a></li>
								<li class="nav-item "><a class="nav-link " style="color: black;"
								href="<c:url value="/home/login/appointment/search"/>">View Appointment</a></li>
						</c:if>
						
       </c:if>
    </ul>
    
  </div>
  <ul class="navbar-nav justify-content-end ">
				<c:if test="${sessionScope.user != null}">
				
				
				<li class="nav-item "> <a class="nav-link" style="padding: 6px; color: black;">
					Hello, ${sessionScope.user.firstName} (${sessionScope.user.roleName }) </a></li>
			

							 

					<li class="nav-item "><a class="nav-link"
						style="padding: 6px; color: black;" href="<c:url value="/home/login"/>">Logout</a></li>
				</c:if>
				<c:if test="${sessionScope.user == null}">
					 <li class="nav-item"><a class="nav-link" style="color: black;"
						 href="<c:url value="/home/login"/>"><i class="fa fa-sign-in"> SignIn</i></a></li>
					 <li class="nav-item">
        				<a class="nav-link" style="color: black;" href="<c:url value="/home/register"/>"><i class="fa fa-user-plus"> SignUp </i></a>
     				 </li>
     				 <li class="nav-item">
        				<a class="nav-link" style="color: black;" href="<c:url value="/contact"/>"><i class="fa fa-phone"> Contact us</i></a>
     				 </li>
     				 <li class="nav-item">
        				<a class="nav-link" style="color: black;" href="<c:url value="/about"/>"><i class="fa fa-info"> About Us </i></a>
     				 </li>
				</c:if>
			</ul>
</nav>
