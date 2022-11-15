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
    <h5 class="card-title text-center"><i class="	fa fa-hospital"></i><br>Add Center</h5>
    <sf:form method="post" action="${pageContext.request.contextPath}/home/login/center" modelAttribute="form"><br/>
<b><%@ include file="businessMessage.jsp"%></b>
<sf:hidden path="id"/>
  <div class="form-group">
    <s:bind path="centerName">
<label for="exampleInputEmail1">Center Name</label>						<sf:input path="${status.expression}"
							placeholder="Enter Name" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
  </div>
  <div class="form-group">
    <s:bind path="centerNo">
<label for="exampleInputEmail1">Center Contact No.</label>						<sf:input path="${status.expression}"
							placeholder="Enter Number" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
  </div>
  <div class="form-group">
    <s:bind path="centerAddress">
<label for="exampleInputEmail1">Center Address</label>						<sf:textarea path="${status.expression}"
							placeholder="Enter Center Address" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
  </div>
  <div class="container text-center">
  <input type="submit" class="btn btn-dark " value="Save" name="operation">
  <input type="submit" class="btn btn-outline-dark " value="Reset" name="operation">
  </div>
  
</sf:form>
  </div>
</div>
		
</section>
</div>