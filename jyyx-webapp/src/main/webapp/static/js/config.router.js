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
					.otherwise('/app/cacheMonitor');
				$stateProvider
					.state('app', {
						abstract: true,
						url: '/app',
						templateUrl: 'static/tpl/app.html'
					})
					.state('app.allocating', {
						url: '/allocating',
						templateUrl: 'static/tpl/allocating_resource.html',
						resolve: {
							deps: ['$ocLazyLoad',
								function($ocLazyLoad) {
									return $ocLazyLoad.load(['static/js/controllers/allocting-resource.js']);
								}
							]
						}
					})
					.state('app.updateMemory', {
						url: '/updateMemory/{clusterId}',
						templateUrl: 'static/tpl/update_memory.html',
						resolve: {
							deps: ['$ocLazyLoad',
								function($ocLazyLoad) {
									return $ocLazyLoad.load(['static/js/controllers/update-memory.js']);
								}
							]
						}
					})
					.state('app.physicalMinitor', {
						url: '/physicalMinitor',
						templateUrl: 'static/tpl/monitor/physical_monitor.html',
						resolve: {
							deps: ['$ocLazyLoad',
								function($ocLazyLoad) {
									return $ocLazyLoad.load(['static/js/controllers/monitor/physical-monitor.js']);
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