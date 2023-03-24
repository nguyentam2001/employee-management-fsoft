<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/main.css">
    <title>Document</title>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-xl-12">
                <div class="row t-header">
                    <div class="col-xl-12 bg-green-color t-header-height">
                       <div class="t-header-content">
                        <div class="text"><i class="fa-solid fa-users-rectangle"></i> Employee</div>
                        <div class="flex-basic">
                          <div class="dropdown font-size-13">
                            <a href="">Welcome <span>${employeeAccount}</span></a>
                          </div>
                          <div class="dropdown  margin-lt-16 font-size-13">
                                   <a href="${pageContext.request.contextPath}/login/logout"> <i class="fa-solid fa-right-from-bracket"></i> Logout</a>
                          </div>
                        </div>
                       </div>
                    </div>
                </div>
                <div class="row t-content-height">
                  <div class="col-xl-2 bg-green-color t-navbar-height border-nav-right">
                    <div class="menu-items">
                        <a href="employees">
                            <div class="flex-basic t-menu-content active-menu">
                              <i class="fa-solid fa-list"></i>
                                <div class="margin-lt-8">Employee list</div>
                            </div>
                        </a>
                       <a href="showFormForAdd">
                        <div class="flex-basic t-menu-content t-menu-content-last">
                          <i class="fa-solid fa-plus"></i>
                             <div class="margin-lt-8">Add employee</div>
                          </div>
                       </a>
                    </div>


                  </div>
                  <div class="col-xl-10 bg-red-color ">
                    <div class="row">


                        <!-- content -->
                        <div  class="main-content-wrap container " id="view-load">
                            <div class="content-header">
                                <p>Employee list</p>
                            </div>

                            <div>
                                <form:form method="get" id="employee-list" class="form-content margin-top-24 form">

                                      <div class="filter-group flex-basic flex-jc-end margin-right-24">
                                          <!-- Actual search box -->
                                          <div class="form-group has-search ">
                                            <span class="fa fa-search form-control-feedback"></span>
                                            <input type="text" name="name"  value="${param.name}" class="form-control padding-lt-8" placeholder="Search">
                                          </div>


                                       <div class="flex-basic margin-lt-8">
                                         <div class="input-group-prepend">
                                           <label class="input-group-text border-radius-right-0 font-size-inherit" for="inputGroupSelect01"><i class="fa-sharp fa-solid fa-filter"></i>Filter By</label>
                                         </div>
                                         <select name="selectOption" class="custom-select border-radius-left-0 height-inherit" id="inputGroupSelect01">
                                           <option ${param.selectOption=='name'?'selected':''} value="name">Name</option>
                                           <option ${param.selectOption=='phone'?'selected':''} value="phone" >Phone number</option>
                                           <option ${param.selectOption=='dateOfBirth'?'selected':''} value="dateOfBirth">Date of birth</option>
                                           <option ${param.selectOption=='departmentName'?'selected':''} value="departmentName">Department</option>

                                         </select>
                                       </div>
                                       <div>
                                        <button type="submit" class="margin-lt-8 backgroud-green width-100 btn-light t-btn t-btn-blue t-btn-hover btn btn-success">Search</button>
                                    </div>
                                     </div>
                                     <div class="form-header">
                                      Total employee: ${totalRecords}</span>
                                      </div>
                                      <div class="row">
                                        <div class="form-body content-list    ">
                                            <table class="table  table-bordered">
                                                <thead>
                                                  <tr>
                                                    <th scope="col">ID</th>
                                                    <th scope="col">Name</th>
                                                    <th scope="col">Date of birth</th>
                                                    <th scope="col">Address</th>
                                                    <th scope="col">Phone number</th>
                                                    <th scope="col">Department</th>
                                                    <th scope="col">Action</th>
                                                  </tr>
                                                </thead>
                                                <tbody>
                                                   <c:forEach items="${theEmployees}" var="tempEmployee" varStatus="loop">
                                                    <tr>
                                                    <th scope="row">${tempEmployee.employeeId}</th>
                                                    <td><a href="${pageContext.request.contextPath}/employee/viewEmployee?employeeId=${tempEmployee.employeeId}">${tempEmployee.firstName} ${tempEmployee.lastName}</a></td>
                                                    <td> ${tempEmployee.dateOfBirth}</td>
                                                    <td>${tempEmployee.address}</td>
                                                    <td >${tempEmployee.phone}</td>
                                                    <td>${tempEmployee.departmentName}</td>
                                                    <td>
                                                      <a href="${pageContext.request.contextPath}/employee/viewEmployee?employeeId=${tempEmployee.employeeId}"><i class="fa-solid fa-eye"></i>View</a>
                                                      <span>|</span>
                                                      <a href="${pageContext.request.contextPath}/employee/delete?employeeId=${tempEmployee.employeeId}" class="error" onclick="if(!(confirm('Are you sure you want to delete this employee?'))) return false"><i class="fa-solid fa-trash"></i>Delete</a>
                                                    </td>
                                                  </tr
                                                  </c:forEach>
                                                </tbody>
                                              </table>
                                          </div>
                                      </div>
                                </form:form>
                                <nav class="margin-lt-16" aria-label="Page navigation example">
                                  <ul class="pagination">
                                   <c:if test="${currentPage != 1}">
                                        <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/employee/employees?page=${currentPage-1}&name=${param.name}&selectOption=${param.selectOption}">Previous</a></li>
                                    </c:if>
                                    <c:forEach var = "i" begin = "1" end = "${totalPage}" >
                                    	<c:url var="pageLink" value="/employee/employees">
                                            <c:param name="page" value="${i}"/>
                                            <c:param name="name" value="${param.name}"/>
                                            <c:param name="selectOption" value="${param.selectOption}"/>
                                        </c:url>
                                        <c:choose>
                                         <c:when test = "${currentPage == i}">
                                                 <li class="page-item active"><a class="page-link" href="${pageLink}">${i}</a></li>
                                            </c:when>
                                                <c:otherwise>
                                                <li class="page-item "><a class="page-link" href="${pageLink}">${i}</a></li>
                                             </c:otherwise>
                                        </c:choose>
                                     </c:forEach>
                                       <c:if test="${currentPage < totalPage}">
                                         <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/employee/employees?page=${currentPage+1}&name=${param.name}&selectOption=${param.selectOption}">Next</a></li>
                                      </c:if>
                                  </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                  </div>
                </div>
            </div>

        </div>

      </div>
      <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
      <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
</body>
</html>