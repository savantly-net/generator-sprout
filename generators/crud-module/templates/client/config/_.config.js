// Configuring the module
angular.module('<%= slugifiedPluralName %>').run(['Menus',
	function(Menus) {
		var moduleName = '<%= slugifiedPluralName %>';
		// Set top bar menu items
		Menus.addMenuItem('topbar', '<%= humanizedPluralName %>', '/'+moduleName, 'dropdown', '/'+moduleName, true);
		Menus.addSubMenuItem('topbar', '/'+moduleName, 'List', moduleName, moduleName, true);
		Menus.addSubMenuItem('topbar', '/'+moduleName, 'Create', moduleName+'/create', moduleName+'Create', true);
	}
]);