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
					.otherwise('/app/productCategory');
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
					.state('app.physicalDetail', {
						url: '/physicalDetail/{hostId}/{ip}',
						templateUrl: 'static/tpl/monitor/physical_detail.html',
						resolve: {
							deps: ['$ocLazyLoad',
								function($ocLazyLoad) {
									return $ocLazyLoad.load(['static/js/controllers/monitor/physical-detail.js', 'static/js/controllers/chart.js']);
								}
							]
						}
					})
					.state('app.cacheDetail', {
						url: '/cacheDetail/{clustrId}',
						templateUrl: 'static/tpl/monitor/cache_detail.html',
						resolve: {
							deps: ['$ocLazyLoad',
								function($ocLazyLoad) {
									return $ocLazyLoad.load(['static/js/controllers/monitor/cache-detail.js']);
								}
							]
						}
					})
					.state('app.clusterMonitor', {
						url: '/clusterMonitor',
						templateUrl: 'static/tpl/monitor/cluster_monitor.html',
						resolve: {
							deps: ['$ocLazyLoad',
								function($ocLazyLoad) {
									return $ocLazyLoad.load(['static/js/controllers/monitor/cluster-minitor.js']);
								}
							]
						}
					})
					.state('app.cacheMonitor', {
						url: '/cacheMonitor',
						templateUrl: 'static/tpl/monitor/cache_monitor.html',
						resolve: {
							deps: ['$ocLazyLoad',
								function($ocLazyLoad) {
									return $ocLazyLoad.load(['static/js/controllers/monitor/cache-monitor.js']);
								}
							]
						}
					})
					.state('app.zookeeperConfig', {
                        url: '/zookeeperConfig',
                        templateUrl: 'static/tpl/monitor/zookeeper_config.html',
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function($ocLazyLoad) {
                                    return $ocLazyLoad.load(['static/js/controllers/monitor/zookeeper-config.js']);
                                }
                            ]
                        }
                    })
			}
		]
	);