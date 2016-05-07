App.controller('UserController', function($scope, User) {
	var self = this;
	self.user = new User();
	self.users = [];

	self.fetchAllUsers = function() {
		self.users = User.query();
	}
	self.fetchAllUsers();
	self.createUser = function() {
		self.user.$save(function() {
			self.fetchAllUsers();
		});

	};

	self.updateUser = function() {
		self.user.$update(function() {
			self.fetchAllUsers();

		});
	};

	self.deleteUser = function(identity) {
		var user = User.get({
			id : identity
		}, function() {
			user.$delete(function() {
				console.log('Deleting user with id ', identity);
				self.fetchAllUsers();

			});

		});

	};

	self.edit = function(id) {
		console.log('id to be edited ', id);
		for (var i = 0; i < self.users.length; i++) {

			if (self.users[i].id === id) {
				self.user = angular.copy(self.users[i]);
				break;
			}
			;
		}
		;

	};

	self.reset = function() {
		self.user = new User();
		$scope.myForm.$setPrestine();
	};

	self.remove = function(id) {
		console.log('id to be deleted ', id);
		if (self.user.id === id) {
			self.reset();
		}
		self.deleteUser(id);
	};

	self.submit = function() {
		if (self.user.id == null) {
			console.log('Saving user ', self.user);
			self.createUser();
		} else {
			console.log('Updating user with id ', self.user.id);
			self.updateUser();
		}
		self.reset();
	}

})