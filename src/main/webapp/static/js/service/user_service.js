App.factory('User', [ '$resource', function($resource) {
	return $resource('http://localhost:8080/user/:id', {
		id : '@id'
	}, {
		update : {
			method : 'PUT'
		}
	});

} ]);