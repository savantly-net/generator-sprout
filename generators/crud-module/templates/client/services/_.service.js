'use strict';


//service used to communicate with REST endpoints
angular.module('<%= slugifiedPluralName %>').factory('<%= classifiedPluralName %>', ['$resource', '$http', 
	function($resource, $http) {
		var moduleName = '<%= slugifiedPluralName %>';
		var baseUrl = '/api/'+moduleName;
		return $resource(baseUrl+'/:id', { id: '@id' }, {
			query: {
				url: baseUrl,
				isArray: false,
			},
			update: {
				method: 'PUT'
			},
			search: {
				isArray: false,
				url: baseUrl+'/search/?:method'
			}
		});
	}
]);