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

<c:url var="addUrl" value="/contact" />

<c:url var="addSearch" value="/home/login/contact/search" />



<br>
<div class="container mt-2 mb-3"
	style="position: relative; min-height: 100vh">
	<section class="contact">

		<div class="card shadow">
			<div class="card-body">
				<h5 class="card-title text-center">
					<i class="fa fa-edit"></i><br>View Messages
				</h5>
				<sf:form method="post"
					action="${pageContext.request.contextPath}/home/login/center/search"
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
								
								<th scope="col">#</th>
								<th scope="col">Email Id</th>
								<th scope="col">Message Detail</th>
								<th scope="col">Message Date</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="c" varStatus="contacts">
								<tr>
									
									<td scope="row">${contacts.index+1}</td>
									<td scope="row">${c.login}</td>
									<td scope="row">${c.message}</td>
									<td scope="row">${DataUtility.getDateString(c.createdDatetime)}</td>
									
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="clearfix">
						


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