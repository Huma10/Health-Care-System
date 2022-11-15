<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page isELIgnored="false"%>
<div class="container mt-2 mb-3"
	style="position: relative; min-height: 100vh">
	<div class="card shadow">
			<div class="card-body">
			<sf:form method="post"
					action="${pageContext.request.contextPath}/home/login/appointment/test"
					modelAttribute="form2">
				<b class="text-center"><%@ include file="businessMessage.jsp"%></b>
	<h2 class="text-dark">Test From the Selected Center</h2>
	<table class="table table-bordered bg-white mt-3">
						<thead>
							<tr>
								<c:if test="${sessionScope.user.roleId == 1}">
									<th scope="col"><input type="checkbox" id="selectall">Select
										All</th>
								</c:if>
								<th scope="col">#</th>
								<th scope="col">Center Name</th>
								<th scope="col">Test Name</th>
								<th scope="col">Center Address</th>
								<th scope="col">Center Contact No.</th>

								<c:if test="${sessionScope.user.roleId == 1}">
									<th scope="col">Action</th>
								</c:if>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="t" varStatus="tests">
								<tr>
									<c:if test="${sessionScope.user.roleId == 1}">
										<td><input type="checkbox" class="case" name="ids"
											value="${t.id}"></td>
									</c:if>
									<td scope="row">${tests.index+1}</td>
									<td scope="row">${t.center.centerName}</td>
									<td scope="row">${t.testName}</td>
									<td scope="row">${t.center.centerAddress}</td>
									<td scope="row">${t.center.centerNo}</td>
									<td><input type="checkbox" class="case" name="ids"
											value="${t.id}"></td>
											
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
		<h2 class="text-dark">Select Date & Time</h2>
		<div class="form-group">
		<s:bind path="dateOfAppointment">
		<label class="">Preferred Appointment Date:</label>	
		
			<sf:input path="${status.expression}" placeholder="Enter Date in DD/MM/YYYY" type="text"  name="dateOfAppointment"/>
			</s:bind>
			</div>
			
			<div class="form-group">
			<s:bind path="timeSlot">
			<label>Preferred time for Appointment:</label>
		<sf:select name="timeslot" path="${status.expression}" >
			<sf:option value="9 am - 10 am"> 9 am - 10 am </sf:option>
			<sf:option value="10 am - 11 am"> 10 am - 11 am </sf:option>
			<sf:option value="11 am - 12 pm"> 11 am - 12 pm </sf:option>
			<sf:option value="12 pm - 1 pm"> 12 pm - 1 pm </sf:option>
			<sf:option value="1 pm - 2 pm"> 1 pm - 2 pm </sf:option>
			<sf:option value="2 pm - 3 pm"> 2 pm - 3 pm </sf:option>
			<sf:option value="3 pm - 4 pm"> 3 pm - 4 pm </sf:option>
			<sf:option value="4 pm - 5 pm"> 4 pm - 5 pm </sf:option>
			<sf:option value="5 pm - 6 pm"> 5 pm - 6 pm </sf:option>
			<sf:option value="6 pm - 7 pm"> 6 pm - 7 pm </sf:option>
		</sf:select>
		</s:bind>
		</div>	
		<div class="container text-center">
						<input type="submit" class="btn btn-dark " value="Confirm Appointment"
							name="operation"> 
					</div></sf:form>
		</div></div>
				
</div>	

	
