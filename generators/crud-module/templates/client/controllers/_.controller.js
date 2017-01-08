'use strict';


angular.module('<%= slugifiedPluralName %>').controller('<%= classifiedPluralName %>Controller', ['$scope', '$rootScope', '$stateParams', '$location', '$state', 'Authentication', '<%= classifiedPluralName %>', 
	function($scope, $rootScope, $stateParams, $location, $state, Authentication, <%= classifiedPluralName %>) {

		var moduleName = '<%= slugifiedPluralName %>';
		var EntityService = <%= classifiedPluralName %>;
		var locationRedirectRoot = '/'+moduleName;
		// This provides Authentication context.
		$rootScope.title='<%= humanizedPluralName %>';
		$scope.authentication = Authentication;
		$scope.loading = [];
		$scope.$location = $location;
		$scope.item = {};
		$scope.items = [];
		$scope.console = '';
		$scope.logs = [];
		$scope.fromJson = angular.fromJson;
		$scope.toJson = angular.toJson;
		
		
		/************ Generic functions *****************/
		
		$scope.redirect = function(){
			if($stateParams.redirectState && $stateParams.redirectState != undefined) {
				$state.go($stateParams.redirectState);
			}
			else {
				$location.path(locationRedirectRoot);
			}
		};
		
		$scope.findAll = function(){
			EntityService.query().$promise.then(function(response){
				$scope.items = response.content;
			});
		};
		
		$scope.addLoader = function(id){
			$scope.loading.push(id);
		};
		
		$scope.removeLoader = function(id){
			var loaderIndex = $scope.loading.indexOf(id);
			if(loaderIndex > -1){
				$scope.loading.splice(loaderIndex, 1);
			}
		};
		
		$scope.save = function(doRedirect) {
			$scope.addLoader('save');
			// Create new object
			var _item = new EntityService($scope.item);

			// Redirect after save
			_item.$save(function(response) {
				$scope.removeLoader('save');
				if (doRedirect) $location.path(locationRedirectRoot + '/' + response.id + '/edit/' + ($stateParams.redirectState || ''));
			}, function(errorResponse) {
				$scope.error = errorResponse.data.message;
				$scope.removeLoader('save');
			});
		};
		
		$scope.duplicate = function(item){
			$scope.item = item;
			$scope.item.id = null;
			$scope.save();
		}
		
		$scope.cancel = function(){
			$location.path(locationRedirectRoot);
		};
		
		// Find existing item
		$scope.findOne = function() {
			if($stateParams.id == undefined) return;
			
			EntityService.get({
				id: $stateParams.id
			}).$promise.then(function(item){
				$scope.item = item;
			});
		};
		
		$scope.log = function(msg){
			$scope.logs.push(msg);
			$scope.console = angular.toJson($scope.logs, true);
		}
		$scope.clearLogs = function(){
			$scope.logs = [];
			$scope.console = angular.toJson($scope.logs, true);
		}
		
		$scope.deleteItem = function(item){
			var _item = new EntityService(item);
			_item.$delete().then(function(response){
				var itemIndex = $scope.items.indexOf(item);
				if (itemIndex > -1){
					$scope.items.splice(itemIndex, 1);
				}
			});
		};
		
		$scope.selectAll = function(value){
			for(var i=0; i<$scope.items.length; i++){
				$scope.items[i].checked = value;
			}
		};
		
	}
]);