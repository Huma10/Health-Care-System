<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page isELIgnored="false"%>

<c:url var="addUrl" value="/home/login/test" />

<c:url var="addSearch" value="/home/login/test/search" />

<c:url var="editUrl" value="/home/login/test?id=" />

<br>
<div class="container mt-2 mb-3"
	style="position: relative; min-height: 100vh">
	<section class="contact">

		<div class="card shadow">
			<div class="card-body">
				<h5 class="card-title text-center">
					<i class="fa fa-stethoscope"></i><br>View Tests
				</h5>
				<sf:form method="post"
					action="${pageContext.request.contextPath}/home/login/test/search"
					modelAttribute="form">
					<br />
					<b><%@ include file="businessMessage.jsp"%></b>

					<%-- <div class="form-group">
						<s:bind path="center.centerName">
							<label for="exampleInputEmail1">Center Name</label>
							<sf:input path="${status.expression}"
								placeholder="Enter Center Name" class="form-control" />
							<font color="red" style="font-size: 13px"><sf:errors
									path="${status.expression}" /></font>
						</s:bind>
					</div> --%>
					<div class="form-group">
						<s:bind path="testName">
							<label for="exampleInputEmail1">Test Name</label>
							<sf:input path="${status.expression}"
								placeholder="Enter Test Name" class="form-control" />
							<font color="red" style="font-size: 13px"><sf:errors
									path="${status.expression}" /></font>
						</s:bind>
					</div>
					<div class="col text-center">
						<input type="submit" class="btn btn-sm btn-dark" name="operation"
							value="Search"></input> or <input type="submit"
							class="btn btn-sm btn-outline-dark" name="operation"
							value="Reset">
					</div>
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
											value="${c.id}"></td>
									</c:if>
									<td scope="row">${tests.index+1}</td>
									<td scope="row">${t.center.centerName}</td>
									<td scope="row">${t.testName}</td>
									<td scope="row">${t.center.centerAddress}</td>
									<td scope="row">${t.center.centerNo}</td>
									<c:if test="${sessionScope.user.roleId == 1}">
								<td><a class="text-dark" href="${editUrl} ${t.id}"
									><i class="fas fa-edit"></i></a></td>
									</c:if>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="clearfix">
						<c:if test="${sessionScope.user.roleId == 1}">
							<input type="submit" name="operation"
								class="btn btn-sm btn-danger float-start"
								<c:if test="${listsize == 0}">disabled="disabled"</c:if>
								value="Delete">
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