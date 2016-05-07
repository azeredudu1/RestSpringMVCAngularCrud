<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>AngularJS ngResource Example</title>
<style type="text/css">
.username.ng-valid {
	background-color: lightgreen;
}

.username.ng-dirty.ng-invalid-required {
	background-color: red;
}

.user.ng-dirty.ng-invalid-minlength {
	background-color: yellow;
}

.email.ng-valid {
	background-color: lightgreen;
}

.email.ng-dirty.ng-invalid-required {
	background-color: red;
}

.email.ng-dirty.ng-invalid-minlength {
	background-color: yellow;
}
</style>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
<link rel="stylesheet" href="../../static/css/app.css">
<link rel="stylesheet" href="../../static/css/bootstrap.css">
</head>
<body ng-app="myApp" class="ng-cloak">
	<div class="generic-container" ng-controller="UserController as ctrl">
		<div class="panel panel-default ">
			<div class="lead panel-heading">User Registration form</div>
			<div class="formcontainer">
				<form ng-submit="ctrl.submit()" name="myForm"
					class="form-horizontal">

					<input ng-model="ctrl.user.id" hidden="hidden">
					<div class="row">

						<div class="col-md-12 form-group">

							<label class="col-md-2 control-label" for="uname">Username</label>
							<div class="col-md-7">
								<input class="username form-control input-sm"
									ng-model="ctrl.user.username" id="uname" required
									ng-minlength="3" placeholder="Enter your name">
							</div>
							<div class="has-error" ng-show="myForm.$dirty">
								<span ng-show="myForm.uname.$error.required">This is a
									required field</span> <span ng-show="myForm.uname.$error.minlength">Minimum
									length required is 3</span><span ng-show="myForm.uname.$error.invalid">This
									field is invalid</span>


							</div>

						</div>


					</div>
					<div class="row">
						<div class="col-md-12 form-group">
							<label class="col-md-2 control-label" for="address">Address</label>
							<div class="col-md-7">
								<input class="form-control input-sm"
									ng-model="ctrl.user.address" placeholder="Enter your address">

							</div>


						</div>






					</div>
					<div class="row">
						<div class="col-md-12 form-group">
							<label class="col-md-2 control-label" for="email"> </label>
							<div class="col-md-7">

								<input class="email form-control input-sm"
									placeholder="Enter your email" required ng-minlength="3"
									ng-model="ctrl.user.email" id="email">
							</div>
							<div class="has-error" ng-show="myForm.$dirty">

								<span ng-show="myForm.email.$error.required">This is a
									required field</span> <span ng-show="myForm.email.$error.minlength">Minimum
									length for this field is 3</span> <span
									ng-show="myForm.email.$error.invalid">This field is
									invalid</span>
							</div>

						</div>
					</div>
					<div class="row">
						<div class="floatRight">
							<input type="submit" value="{{!ctrl.user.id?'Add':'Update'}}"
								class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
							<button type="button" ng-disabled="myForm.$prestine" ng-click="ctrl.reset()"
								class="btn btn-sm btn-warning">Reset Form</button>
						</div>
					</div>
				</form>



			</div>


		</div>
		<div class="panel panel-default">

			<div class="panel-heading">
				<span class="lead">List of Users</span>
			</div>
			<div class="tablecontainer">
				<table class="table table-hover">
					<thead>
						<tr>
							<td>ID.</td>
							<td>Name</td>
							<td>Address</td>
							<td>Email</td>
							<td width="20%"></td>
						</tr>

					</thead>
					<tbody>
						<tr ng-repeat="u in ctrl.users">
							<td><span ng-bind="u.id"></span></td>
							<td><span ng-bind="u.username"></span></td>
							<td><span ng-bind="u.address"></span></td>
							<td><span ng-bind="u.email"></span></td>
							<td>
								<button ng-click="ctrl.edit(u.id)"
									class="btn btn-success custom-width">Edit</button>
								<button ng-click="ctrl.remove(u.id)"
									class="btn btn-danger custom-width">Remove</button>
							</td>
						</tr>

					</tbody>

				</table>


			</div>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular-resource.js"></script>
	<script src="<c:url value='/static/js/app.js' />"></script>
	<script src="<c:url value='/static/js/service/user_service.js' />"></script>
	<script
		src="<c:url value='/static/js/controller/user1_controller.js' />"></script>
</body>

</body>
</html>