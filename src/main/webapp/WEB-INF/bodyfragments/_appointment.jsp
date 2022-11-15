<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page isELIgnored="false"%>


<c:url var="editUrl" value="/home/login/appointment/test?id=" />
<div class="container mt-2 mb-3"
	style="position: relative; min-height: 100vh">
	<h2 class="text-white"> View Centers </h2>
	<sf:form method="post"
					action="${pageContext.request.contextPath}/home/login/appointment/test"
					modelAttribute="form">
					
	<table class="table table-bordered bg-white mt-5">
						<thead>
							<tr>
								<c:if test="${sessionScope.user.roleId == 1}">
									<th scope="col"><input type="checkbox" id="selectall">Select
										All</th>
								</c:if>
								<th scope="col">#</th>
								<th scope="col">Center Name</th>
								<th scope="col">Center Contact No</th>
								<th scope="col">Center Address</th>
				                <th scope="col">View Test</th>
								<c:if test="${sessionScope.user.roleId == 1}">
									<th scope="col">Action</th>
								</c:if>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="c" varStatus="centers">
								<tr>
									<c:if test="${sessionScope.user.roleId == 1}">
										<td><input type="checkbox" class="case" name="ids"
											value="${c.id}"></td>
									</c:if>
									<td scope="row">${centers.index+1}</td>
									<td scope="row">${c.centerName}</td>
									<td scope="row">${c.centerNo}</td>
									<td scope="row">${c.centerAddress}</td>
									
								<td scope="row"><a class="text-white btn btn-dark" href="${editUrl} ${c.id}"
									>View Available Test</a></td>
									
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</sf:form>	
</div>