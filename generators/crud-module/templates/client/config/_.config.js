// Configuring the module
angular.module('<%= slugifiedPluralName %>').run(['Menus',
    function(Menus) {
		var moduleName = '<%= slugifiedPluralName %>';
			// Set top bar menu items
		Menus.addMenuItem({
			menuId: 'topbar', 
			menuItemId: moduleName+'Menu',
			title: moduleName, 
			location: '/'+moduleName, 
			menuItemType: 'dropdown'
		});
		var menu = Menus.getMenuItem('topbar', moduleName+'Menu');
		menu.addMenuItem({
			menuItemId: moduleName+'ListMenu',
			title: 'List', 
			location: '/'+moduleName, 
		});
		menu.addMenuItem({
			menuItemId: moduleName+'CreateMenu',
			title: 'Create', 
			location: moduleName+'/create' 
		});
	}
]);