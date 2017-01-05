'use strict';
// Setting up route
angular.module('<%= slugifiedPluralName %>').config(['$stateProvider', '$urlRouterProvider',
	function($stateProvider, $urlRouterProvider) {
	
		var moduleName = '<%= slugifiedPluralName %>';

		$stateProvider.
		state(moduleName, {
			url: '/'+moduleName,
			templateUrl: 'modules/'+moduleName+'/views/list.view.html'
		}).
		state(moduleName+'Create', {
			url:'/'+moduleName+'/create',
			templateUrl: 'modules/'+moduleName+'/views/edit.view.html'
		}).
		state(moduleName+'Edit', {
			url:'/'+moduleName+'/:id/edit',
			templateUrl: 'modules/'+moduleName+'/views/edit.view.html'
		}).
		state(moduleName+'EditWithRedirect', {
			url:'/'+moduleName+'/:id/edit/:redirectUrl?',
			templateUrl: 'modules/'+moduleName+'/views/edit.view.html'
		}).
		state(moduleName+'View', {
			url:'/'+moduleName+'/:id',
			templateUrl: 'modules/'+moduleName+'/views/item.view.html'
		});
	}
]);