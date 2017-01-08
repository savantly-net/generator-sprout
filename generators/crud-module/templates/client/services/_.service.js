'use strict';


//service used to communicate with REST endpoints
angular.module('<%= slugifiedPluralName %>').factory('<%= classifiedPluralName %>', ['$resource', '$http', 
	function($resource, $http) {
		var moduleName = '<%= slugifiedPluralName %>';
		return $resource('/'+moduleName+'/:id', { id: '@id' }, {
			query: {
				isArray: false,
			}
		});
	}
]);