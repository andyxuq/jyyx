<!DOCTYPE html>
<html lang="en" data-ng-app="app">
<head>
  <meta charset="utf-8" />
  <title>Zedis</title>
  <meta name="description" content="app, web app, responsive, responsive layout, admin, admin panel, admin dashboard, flat, flat ui, ui kit, AngularJS, ui route, charts, widgets, components" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <link rel="stylesheet" href="static/css/bootstrap.css" type="text/css" />
  <link rel="stylesheet" href="static/css/animate.css" type="text/css" />
  <link rel="stylesheet" href="static/css/font-awesome.min.css" type="text/css" />
  <link rel="stylesheet" href="static/css/simple-line-icons.css" type="text/css" />
  <link rel="stylesheet" href="static/css/font.css" type="text/css" />
  <link rel="stylesheet" href="static/css/app.css" type="text/css" />
  <link rel="stylesheet" href="static/vendor/modules/angularjs-toaster/toaster.css" type="text/css" />
</head>
<body ng-controller="AppCtrl">
  <div class="app" id="app" ng-class="{'app-header-fixed':app.settings.headerFixed, 'app-aside-fixed':app.settings.asideFixed, 'app-aside-folded':app.settings.asideFolded, 'app-aside-dock':app.settings.asideDock, 'container':app.settings.container}" ui-view></div>


  <!-- jQuery -->
  <script src="static/vendor/jquery/jquery.min.js"></script>

  <!-- Angular -->
  <script src="static/vendor/angular/angular.js"></script>
  
  <script src="static/vendor/angular/angular-animate/angular-animate.js"></script>
  <script src="static/vendor/angular/angular-cookies/angular-cookies.js"></script>
  <script src="static/vendor/angular/angular-resource/angular-resource.js"></script>
  <script src="static/vendor/angular/angular-sanitize/angular-sanitize.js"></script>
  <script src="static/vendor/angular/angular-touch/angular-touch.js"></script>
<!-- Vendor -->
  <script src="static/vendor/angular/angular-ui-router/angular-ui-router.js"></script> 
  <script src="static/vendor/angular/ngstorage/ngStorage.js"></script>
  <script src="static/vendor/modules/angularjs-toaster/toaster.js"></script>

  <!-- bootstrap -->
  <script src="static/vendor/angular/angular-bootstrap/ui-bootstrap-tpls.js"></script>
  <!-- lazyload -->
  <script src="static/vendor/angular/oclazyload/ocLazyLoad.js"></script>
  <!-- translate -->
  <script src="static/vendor/angular/angular-translate/angular-translate.js"></script>
  <script src="static/vendor/angular/angular-translate/loader-static-files.js"></script>
  <script src="static/vendor/angular/angular-translate/storage-cookie.js"></script>
  <script src="static/vendor/angular/angular-translate/storage-local.js"></script>

  <!-- App -->
  <script src="static/js/app.js"></script>
  <script src="static/js/config.js"></script>
  <script src="static/js/config.lazyload.js"></script>
  <script src="static/js/config.router.js"></script>
  <script src="static/js/main.js"></script>
  <script src="static/js/services/ui-load.js"></script>
  <script src="static/js/services/websocket.js"></script>
  <script src="static/js/services/function-service.js"></script>
  <script src="static/js/lib/angular.radialIndicator.js"></script>
  <script src="static/js/services/http-function.js"></script>
  <script src="static/js/filters/fromNow.js"></script>
  <script src="static/js/directives/setnganimate.js"></script>
  <script src="static/js/directives/ui-butterbar.js"></script>
  <script src="static/js/directives/ui-focus.js"></script>
  <script src="static/js/directives/ui-fullscreen.js"></script>
  <script src="static/js/directives/ui-jq.js"></script>
  <script src="static/js/directives/ui-module.js"></script>
  <script src="static/js/directives/ui-nav.js"></script>
  <script src="static/js/directives/ui-scroll.js"></script>
  <script src="static/js/directives/ui-shift.js"></script>
  <script src="static/js/directives/ui-toggleclass.js"></script>
  <script src="static/js/directives/ui-validate.js"></script>
  <script src="static/js/controllers/bootstrap.js"></script>
  <!-- Lazy loading -->
</body>
</html>