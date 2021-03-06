'use strict';

/**
 * Config for the router
 */
angular.module('app')
	.run(
		['$rootScope', '$state', '$stateParams',
			function($rootScope, $state, $stateParams) {
				$rootScope.$state = $state;
				$rootScope.$stateParams = $stateParams;
			}
		]
	)
	.config(
		['$stateProvider', '$urlRouterProvider',
			function($stateProvider, $urlRouterProvider) {

				$urlRouterProvider
					.otherwise('/app/pic');
				$stateProvider
					.state('app', {
						abstract: true,
						url: '/app',
						templateUrl: 'static/tpl/app.html'
					})
					.state('app.pic', {
						url: '/pic',
						templateUrl: 'static/tpl/jyyx/pic_resource.html',
						resolve: {
							deps: ['$ocLazyLoad',
								function($ocLazyLoad) {
	                            	  return $ocLazyLoad.load(['static/js/jyyx/pic-resource.js'
										                         , 'static/js/jyyx/modal/pic_resource_add.js']);
								}
							]
						}
					})
					.state('app.productCategory', {
						url: '/productCategory',
						templateUrl: 'static/tpl/jyyx/product_category.html',
						resolve: {
							deps: ['$ocLazyLoad',
								function($ocLazyLoad) {
;									return $ocLazyLoad.load(['static/js/jyyx/product_category.js'
 									                         , 'static/js/jyyx/modal/product_category_add.js']);
								}
							]
						}
					})
					.state('access', {
		                  url: '/access',
		                  template: '<div ui-view class="fade-in-right-big smooth"></div>'
		            })
					.state('access.login', {
						url: '/login',
						templateUrl: 'static/tpl/jyyx/login.html',
						resolve: {
							deps: ['uiLoad',
		                        function( uiLoad ){
		                          return uiLoad.load( ['static/js/jyyx/login.js'] );
							}]
						}
					})
					.state('app.caseCategory', {
						url: '/caseCategory',
						templateUrl: 'static/tpl/jyyx/case_category.html',
						resolve: {
							deps: ['$ocLazyLoad',
								function($ocLazyLoad) {
									return $ocLazyLoad.load(['static/js/jyyx/case_category.js'
									                         , 'static/js/jyyx/modal/case_category_add.js']);
								}
							]
						}
					})
					.state('app.msgCategory', {
						url: '/msgCategory',
						templateUrl: 'static/tpl/jyyx/msg_category.html',
						resolve: {
							deps: ['$ocLazyLoad',
								function($ocLazyLoad) {
									return $ocLazyLoad.load(['static/js/jyyx/msg_category.js'
									                         , 'static/js/jyyx/modal/msg_category_add.js']);
								}
							]
						}
					})
					.state('app.product', {
						url: '/product',
						templateUrl: 'static/tpl/jyyx/product.html',
						resolve: {
							deps: ['$ocLazyLoad',
								function($ocLazyLoad) {
									return $ocLazyLoad.load(['static/js/jyyx/product.js'
									                         , 'static/js/jyyx/modal/product_add.js']);
								}
							]
						}
					})
					.state('app.case', {
						url: '/case',
						templateUrl: 'static/tpl/jyyx/case.html',
						resolve: {
							deps: ['$ocLazyLoad',
								function($ocLazyLoad) {
									return $ocLazyLoad.load(['static/js/jyyx/case.js'
									                         , 'static/js/jyyx/modal/case_add.js']);
								}
							]
						}
					})
					.state('app.user', {
						url: '/user',
						templateUrl: 'static/tpl/jyyx/user.html',
						resolve: {
							deps: ['$ocLazyLoad',
								function($ocLazyLoad) {
									return $ocLazyLoad.load(['static/js/jyyx/user.js'
									                         , 'static/js/jyyx/modal/user_add.js']);
								}
							]
						}
					})
					.state('app.msg', {
						url: '/msg',
						templateUrl: 'static/tpl/jyyx/msg.html',
						resolve: {
							deps: ['$ocLazyLoad',
								function($ocLazyLoad) {
									return $ocLazyLoad.load('textAngular').then(
											function() {
												return $ocLazyLoad.load(['static/js/jyyx/msg.js'
									                         , 'static/js/jyyx/modal/msg_add.js']);
											}
									);
								}
							]
						}
					})					
			}
		]
	);