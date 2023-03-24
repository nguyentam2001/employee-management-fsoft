
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link type="text/css" rel="stylesheet"
        	href="${pageContext.request.contextPath}/resources/css/main.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
    <title>Login</title>
</head>
<body>

    <div class="t-container">

        <form action="${pageContext.request.contextPath}/login/authenticateUser"  class="form"  method="POST" role="form">

            <div class="form-header font-size-16">
              <i class="fa-solid fa-xmark"></i>
              </div>
              <div class="form-body">
                <div class="mb-3">
                  <div class="header-text font-size-20">
                        Member Login
                  </div>
                </div>
                <div class="position-relative mb-3">
                  <div class="input-icon">
                    <i class="fa-solid fa-user"></i>
                  </div>
                  <input name="account"  type="text" class="form-control padding-lt-24" placeholder="Username"  id="email" aria-label="Username" aria-describedby="basic-addon1" maxlength="50" />
                </div>
                  <div class="position-relative mb-3">
                    <div class="input-icon">
                      <i class="fa-solid fa-lock"></i>
                    </div>
                    <input name="password" type="password"  class="form-control padding-lt-24" id="password" placeholder="Password" maxlength="50"/>
                  </div>

                  <div class="mb-3">
                    <button type="submit " class="backgroud-green width-100 btn-light t-btn t-btn-green t-btn-hover btn btn-success">Login</button>
                </div

                    	<span class="error">${error}</span>

              </div>
              <div class="form-bottom font-size-inherit">
                <a href=""> Forgot Password?</a>
              </div>

        </form>
    </div>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/validate.js" type="text/javascript"></script>
</body>

</html>

