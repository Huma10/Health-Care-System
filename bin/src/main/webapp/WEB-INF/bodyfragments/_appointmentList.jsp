<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.abc.health.util.*"  %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page isELIgnored="false"%>

<c:url var="addUrl" value="/home/login/appointment" />

<c:url var="addSearch" value="/home/login/appointment/search" />

<c:url var="editUrl" value="/home/login/center?id=" />

<br>
<div class="container mt-2 mb-3"
	style="position: relative; min-height: 100vh">
	<section class="contact">

		<div class="card shadow">
			<div class="card-body">
				<h5 class="card-title text-center">
					<i class="fa fa-files-medical"></i><br>View Appointments
				</h5>
				<sf:form method="post"
					action="${pageContext.request.contextPath}/home/login/appointment/search"
					modelAttribute="form">
					<br />
					<b><%@ include file="businessMessage.jsp"%></b>

					
					<br/>
					<sf:input type="hidden" path="pageNo" />
					<sf:input type="hidden" path="pageSize" />

					<sf:input type="hidden" path="listsize" />
					<sf:input type="hidden" path="total" />
					<sf:input type="hidden" path="pagenosize" />
					<table class="table table-bordered">
						<thead>
							<tr>
								<c:if test="${sessionScope.user.roleId == 1}">
									<th scope="col">Select
										All</th>
								</c:if>
								<th scope="col">Appointment ID</th>
								<c:if test="${sessionScope.user.roleId == 1}">
										<th scope="col">User Name</th>
									</c:if>
								<th scope="col">Center Name</th>
								<th scope="col">Date Of Appointment</th>
								<th scope="col">Time Slot</th>

								<c:if test="${sessionScope.user.roleId == 1}">
									<th scope="col">Action</th>
								</c:if>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="ap" varStatus="appointments">
								<tr>
									<c:if test="${sessionScope.user.roleId == 1}">
										<td><input type="checkbox" class="case" name="ids"
											value="${ap.id}"></td>
									</c:if>
									<c:if test="${sessionScope.user.id==ap.user.id || sessionScope.user.roleId == 1}">
									
									<td scope="row">APP_ID_ ${ap.id}</td>
									<c:if test="${sessionScope.user.roleId == 1}">
										<td scope="row">${ap.user.firstName }</td>
									</c:if>
									<td scope="row">${ap.center.centerName}</td>									
									<td scope="row">${DataUtility.getDateString(ap.dateOfAppointnment)}</td>
									<td scope="row">${ap.timeSlot}</td>
									
									</c:if>
									<c:if test="${sessionScope.user.roleId == 1}">
									 	<c:if test="${ap.status.equalsIgnoreCase('APPROVED')}"><td><span class="text-white btn btn-dark">Approved</span></td></c:if>
								<c:if test="${ap.status.equalsIgnoreCase('ADD')}"><td><input type="submit" class="text-white btn btn-dark" name="operation" value="Approve Appointment"
									></td></c:if>
									<c:if test="${ap.status.equalsIgnoreCase('CANCEL')}"><td><span class="text-white btn btn-danger">Canceled</span></td></c:if>
									</c:if>
									<c:if test="${sessionScope.user.id == ap.userId}">
										<c:if test="${ap.status.equalsIgnoreCase('APPROVED')}"><td><span class="text-white btn btn-dark">Approved</span></td></c:if>
		
									<c:if test="${ap.status.equalsIgnoreCase('CANCEL')}"><td><span class="text-white btn btn-danger">Canceled</span></td></c:if>
									</c:if>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="clearfix">
						<c:if test="${sessionScope.user.roleId == 1}">
							<input type="submit" name="operation"
								class="btn btn-sm btn-warning float-start"
								<c:if test="${listsize == 0}">disabled="disabled"</c:if>
								value="Cancel Appointment">
						</c:if>


						<nav aria-label="Page navigation example float-end">
							<ul class="pagination justify-content-end"
								style="font-size: 13px">
								<li class="page-item"><input type="submit" name="operation"
									class="page-link"
									<c:if test="${form.pageNo == 1}">disabled="disabled"</c:if>
									value="Previous"></li>
								<c:forEach var="i" begin="1" end="${(listsize/10)+1}">
									<c:if test="${i== pageNo}">
										<li class="page-item active"><a
											class="page-link activate" href="${addSearch}?pageNo=${i}">${i}</a></li>
									</c:if>
									<c:if test="${i != pageNo}">
										<li class="page-item"><a class="page-link"
											href="${addSearch}?pageNo=${i}">${i}</a></li>
									</c:if>
								</c:forEach>
								<li class="page-item"><input type="submit" name="operation"
									class="page-link"
									<c:if test="${total == pagenosize  || listsize < pageSize   }">disabled="disabled"</c:if>
									value="Next"></li>
							</ul>
						</nav>
					</div>


				</sf:form>
			</div>
		</div>
	</section>
</div>