<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>  
<div class="container mt-2" style="position: relative; min-height: 100vh">
	<section class="contact">
<div class="card shadow" style="width: 25rem;">
  <div class="card-body">
    <h5 class="card-title text-center"><i class="	fa fa-user-circle" style="font-size: 50px"></i><br>Login</h5>
             <sf:form method="post" action="${pageContext.request.contextPath}/home/login" modelAttribute="form"><br/>
<b><%@ include file="businessMessage.jsp"%></b>
  <div class="form-group">
    
<s:bind path="login">
<label for="exampleInputEmail1">Email address</label>						<sf:input path="${status.expression}"
							placeholder="Enter Email" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>    
  </div>
  <div class="form-group">
    
    <s:bind path="password">
						<label for="exampleInputPassword1">Password</label>
						<sf:input type="password" path="${status.expression}"
							placeholder="Enter Password" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
			</s:bind>
  </div>
  <div class="container text-center">
  <input type="submit" class="btn btn-dark " value="Login" name="operation">
  <input type="submit" class="btn btn-outline-dark " value="Reset" name="operation">
  </div>
  
</sf:form>
  </div>
</div>
		
</section>
</div>