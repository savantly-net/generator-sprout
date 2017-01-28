'use strict';


angular.module('<%= slugifiedPluralName %>').controller('<%= classifiedPluralName %>Controller', ['$scope', '$rootScope', '$stateParams', '$location', '$state', 'Authentication', 'SpringDataRestAdapter', 'GuidGen', '<%= classifiedPluralName %>', 
	function($scope, $rootScope, $stateParams, $location, $state, Authentication, SpringDataRestAdapter, GuidGen, <%= classifiedPluralName %>) {

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
				$scope.items = response._embedded[moduleName];
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
		
		function saveSuccess(response) {
			$scope.removeLoader('save');
			$location.path(locationRedirectRoot + '/' + response.id + '/edit/' + ($stateParams.redirectState || ''));
		}
		
		function saveFailure(errorResponse) {
			$scope.error = errorResponse.data.message;
			$scope.removeLoader('save');
		}
		
		$scope.save = function(doRedirect) {
			$scope.addLoader('save');
			// Create new object
			var _item = new EntityService($scope.item);
			
			//TODO: add related entities
			//_item.customer = '/customers/' + _item.customer.id;

			if(_item.new == false){
				_item.$update(saveSuccess, saveFailure);
			} else {
				_item.$save(saveSuccess, saveFailure);
			}
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
				SpringDataRestAdapter.process(item).then(function(processedReponse){
					$scope.item = processedReponse;
				});
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