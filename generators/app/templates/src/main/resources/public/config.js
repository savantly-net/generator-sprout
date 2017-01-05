'use strict';

// Init the application configuration module for AngularJS application
var ApplicationConfiguration = (function() {
	// Init module configuration options
	var applicationModuleName = "sproutApp";
	var applicationModuleVendorDependencies = ['ngMaterial', 
	                                           'ngMdIcons',
	                                           'ngAria', 
	                                           'ngResource', 
	                                           'ngCookies',  
	                                           'ngAnimate',  
	                                           'ngSanitize',  
	                                           'md.data.table',
	                                           'flow',
	                                           'ui.router', 'ui.bootstrap', 'ui.utils', 'ui.validate'];

	// Add a new vertical module
	var registerModule = function(moduleName, dependencies) {
		console.log('Loading Module: ' + moduleName);
		// Create angular module
		angular.module(moduleName, dependencies || []);

		// Add the module to the AngularJS configuration file
		angular.module(applicationModuleName).requires.push(moduleName);
	};

	return {
		applicationModuleName: applicationModuleName,
		applicationModuleVendorDependencies: applicationModuleVendorDependencies,
		registerModule: registerModule
	};
})();