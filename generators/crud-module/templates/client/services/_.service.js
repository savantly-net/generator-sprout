'use strict';


//service used to communicate with REST endpoints
angular.module('<%= slugifiedPluralName %>').factory('<%= classifiedPluralName %>', ['$resource', '$http', 
	function($resource, $http) {
		var moduleName = '<%= slugifiedPluralName %>';
		return $resource('/api/'+moduleName, { id: '@id' }, {
			query: {
				isArray: false
			},
			update: {
				method: 'PUT',
				url: '/api/'+moduleName+'/:id',
			},
			get: {
				url: '/api/'+moduleName+'/:id',
			},
			search: {
				isArray: false,
				url: '/api/'+moduleName+'/search/?:method'
			}
		});
	}
]);