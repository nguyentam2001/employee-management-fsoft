<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
  <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <html>

      <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
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
                        <a href="">Welcome <span>
                            <%=request.getSession().getAttribute("employeeAccount")%>
                          </span></a>
                      </div>
                      <div class="dropdown  margin-lt-16 font-size-13">
                        <a href=""> <i class="fa-solid fa-right-from-bracket"></i> Logout</a>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="row t-content-height">
                <div class="col-xl-2 bg-green-color t-navbar-height border-nav-right height-initial">

                  <div class="menu-items">
                    <a href="${pageContext.request.contextPath}/employee/employees">
                      <div class="flex-basic t-menu-content">
                        <i class="fa-solid fa-list"></i>
                        <div class="margin-lt-8">Employee list</div>
                      </div>
                    </a>
                    <a href="showFormForAdd">
                      <div class="flex-basic t-menu-content active-menu t-menu-content-last">
                        <i class="fa-solid fa-plus"></i>
                        <div class="margin-lt-8">Add employee</div>
                      </div>
                    </a>
                  </div>

                </div>
                <div class="col-xl-10 bg-red-color ">
                  <div class="row">
                    <div id="edit" class="main-content-wrap container">
                      <div class="content-header">
                        <p>Add Employee</p>
                      </div>
                      <div class="t-border-bm"></div>
                      <div>

                        <form:form method="POST" action="saveEmployee" class="form-content margin-top-24 form"
                          role="form" modelAttribute="employee" id="employee-form">

                          <div class="row">
                            <div class="form-body  col-xl-12 ">
                              <div class="mb-3">
                                <label for="firstname" class="form-label font-weight-bold">First names <span
                                    class="error">(*)</span> </label>

                                <form:input type="text" path="firstName" id="firstname" class="form-control" />
                              </div>
                              <div class="mb-3">
                                <label for="lastname" class="form-label font-weight-bold">Last name<span class="error">
                                    (*)</span> </label>
                                <form:input path="lastName" type="text" class="form-control" id="lastname"
                                  placeholder="Last name" />
                              </div>
                              <div class="mb-3">
                                <label for="phone" class="form-label font-weight-bold">Phone number<span class="error">
                                    (*)</span> </label>
                                <form:input path="phone" type="text" class="form-control" id="phone"
                                  placeholder="Last name" />
                              </div>
                              <div class="mb-3">
                                <label for="date" class="form-label font-weight-bold">Date of birth<span class="error">
                                    (*)</span> </label>
                                <form:input path="dateOfBirth" type="date" class="form-control" />
                              </div>
                              <div class="mb-3">
                                <label for="date" class="form-label font-weight-bold">Gender<span class="error">
                                    (*)</span> </label>
                                <div class="flex-basic">
                                  <div class="check-group margin-lt-16">
                                    <form:radiobutton path="gender" value="1" id="male" checked="checked"  /> <label for="male"
                                      class="margin-bm-0 margin-lt-8">male</label>
                                  </div>
                                  <div class="check-group margin-lt-16">
                                    <form:radiobutton path="gender" value="0" id="female" /> <label for="female"
                                      class="margin-bm-0 margin-lt-8">female</label>
                                  </div>
                                </div>
                              </div>
                              <div class="mb-3">
                                <label for="address" class="form-label font-weight-bold">Address</label>
                                <form:textarea class="form-control" path="address" id="address" rows="3" />
                              </div>

                              <div class="mb-3">
                                <label for="account" class="form-label font-weight-bold">Account<span class="error">
                                    (*)</span></label>
                                <form:input type="text" path="account.account" class="form-control" id="account.account" />
                                <p class="error">${isExistAccount}</p>
                              </div>


                              <div class="mb-3">
                                <label for="email" class="form-label font-weight-bold">Email<span class="error">
                                    (*)</span></label>
                                <form:input type="text" path="account.email" class="form-control" />
                                <p class="error">${isExistEmail}</p>
                              </div>

                              <div class="mb-3">
                                <label for="password" class="form-label font-weight-bold">Password<span class="error">
                                    (*)</span></label>
                                <form:input type="password" path="account.password" class="form-control"
                                  id="account.password" />
                              </div>
                              <div class="mb-3">
                                <label for="date" class="form-label font-weight-bold">Status<span class="error">
                                    (*)</span> </label>
                                <div class="flex-basic">
                                  <div class="check-group margin-lt-16">
                                    <form:checkbox path="account.status" value="1" id="status"/>  <label for="status" class="margin-bm-0 margin-lt-8">Active</label>
                                  </div>
                                </div>
                              </div>
                              <div class="mb-3">
                                <label for="department" class="form-label font-weight-bold">department<span
                                    class="error"> (*)</span></label>
                                <form:input type="text" path="departmentName" class="form-control"
                                  id="departmentName" />
                              </div>
                              <div class="mb-3">
                                <label for="remark" class="form-label font-weight-bold">remark</label>
                                <form:textarea class="form-control" path="remark" id="remark" rows="3" />
                              </div>

                            </div>
                          </div>
                          <div class="row">
                            <div class="col-xl-1">
                              <button type="submit" onclick="window.history.go(-1); return false;"
                                class="width-100 font-size-inherit btn btn-info " id="btnEdit"><i
                                  class="fa-solid fa-backward"></i> Back</button>
                            </div>
                            <div class="col-xl-1 padding-lt-0">
                              <button type="reset" class=" width-100 font-size-inherit btn btn-warning"><i
                                  class="fa-solid fa-rotate-right"></i> Reset</button>
                            </div>
                            <div class="col-xl-1 padding-lt-0">
                              <button type="submit" class=" width-100 font-size-inherit  btn btn-success "><i
                                  class="fa-solid fa-plus"></i> Add</button>
                            </div>
                          </div>
                      </div>
                    </div>


                    </form:form>

                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div id="toast"></div>
        </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
          integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
          crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
          integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
          crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
          integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
          crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/validate.js" type="text/javascript"></script>

      </body>

      </html>