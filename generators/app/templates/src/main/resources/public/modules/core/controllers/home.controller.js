'use strict';


angular.module('core').controller('HomeController', ['$scope', '$rootScope', 'Authentication',
	function($scope, $rootScope, Authentication) {
		// This provides Authentication context.
		$rootScope.title="Sprout App";
		$scope.authentication = Authentication;
	}
]);