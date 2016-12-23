'use strict';
angular.module('LoginPage',['ngCookies']).controller('LoginController',
    ['$scope','$location','$http', '$cookies',
    function ($scope,$location,$http,$cookies) {
 
    	//On Login Click
        $scope.login = function () {
        	
            $scope.dataLoading = true;
            var data={
            		"loginId" : $scope.userName,
					"password" : $scope.pwd,
					"latitude" : $scope.latitude!=undefined?$scope.latitude.toString():"",
					"longitude" :$scope.longitude!=undefined?$scope.longitude.toString():"",
					}
            $scope.loading = true;
            $http({
                url: 'secure/Authentication/authenticate',
                method: "POST",
                data : data,
                contentType: 'application/json;',
                headers: {'Content-Type': 'application/json'}
              }).success(function (data, status, headers, config) {
            	  	$scope.loading = false;
            	  	$cookies.remove('setSecutityQuestions');
            	  	$cookies.remove('token');
            	  	if(data.response.success==true){
            	  		//alert("UserName and Password is correct");
            	  		var userData=JSON.parse(data.response.userinfo);
            	  		var uNameValue = userData.firstName.concat((userData.hasOwnProperty('middleName')&&userData.middleName!=null)?(" "+userData.middleName):"").concat((userData.hasOwnProperty('lastName')&& userData.lastName!=null)?(" "+userData.lastName):"");
            	  		//var userName= userData.firstName + " "+userData.middleName + " "+userData.lastName;
            	  		$cookies.put('userInfo', uNameValue);
            	  		location.href = location.origin + location.pathname + "index.html";
            	  		$cookies.remove('changePwd');
            	  		/** ADDING FLAG IN COOkIES TO SET SECURITY QUESTION WHEN FLAT IS TRUE USER GETS THE POP-UP TO SET SECURITY QUESTION FROM USER PROFILE OPTION */
            	  		$cookies.put("setSecutityQuestions", data.response.setSecutityQuestions);
            	  		
            	  		if(data.response.token){
        					$cookies.put("token", data.response.token);
        				}            	  		
            	  		
            	  	} else {
            	  		if(data.response.token){
        					$cookies.put("token", data.response.token);
        				}            	  		
						if (data.response.changePassword == true) {
							var userData=JSON.parse(data.response.userinfo);
	            	  		var userName= userData.firstName.concat((userData.hasOwnProperty('middleName')&&userData.middleName!=null)?(" "+userData.middleName):"").concat((userData.hasOwnProperty('lastName')&& userData.lastName!=null)?(" "+userData.lastName):"");
	            	  		$cookies.put('userInfo', userName);
	            	  		$cookies.put('changePwd', true);
	            	  		location.href = location.origin + location.pathname + "index.html"
						}
            	  	else{
            	  		$scope.error=data.response.message;
            	  	}
            	  	}
                }).error(function (data, status, headers, config) {
                	
                	$scope.loading = false;
                	//Show error msg in div tag
                	if(status == -1){
                        $scope.error='Can not connect to network';
                    } else if(status!=200){
                        $scope.error='Username or password is incorrect';
                    }
            });
        };
        //Method call on init of html page to get location
        $scope.getLocation = function() {
        	
        	if (navigator.geolocation) {
        		var options = {
        				timeout : 180000
        		};
        		navigator.geolocation.getCurrentPosition(function(position) {
        			$scope.latitude = position.coords.latitude;
        			$scope.longitude = position.coords.longitude;
        		}, this.errorHandler, options);
        	} else {
        		console.log("Sorry, browser does not support geolocation!");
        	}

        	// checking if user is already logged in to redirect him to mainpage
        	$scope.loading = true;
        	$http({
        		url: 'secure/Authentication/checkLoginStatus',
        		method: "POST"
        	}).success(function (data, status, headers, config) {
        		$scope.loading = false;
        		if(data.response.success==true){
        			
        			location.href = location.origin + location.pathname + "index.html"
        		}
        	}).error(function (data, status, headers, config) {
        		
        		$scope.loading = false;
        		//Show error msg in div tag
        		/*if(status!=200){
        			$scope.error='Cannot connect to Server.';
        		}*/
        	});

        };
    	// On Forgot Password Click
    	$scope.onForgetPasswordClick=function(){
    		
    		var data={
    				"findKey" : $scope.userName
    		}
    		$scope.loading = true;
    		$http({
                url: 'secure/PasswordGenerator/findSecurityQuestions',
                method: "POST",
                data : data,
               // timeout : 180000,
                headers : {isBeforeSession : true},
              }).success(function (data, status, headers, config) {
            	  	
            	  	$scope.loading = false;
            	  	if(data.response.success==true){
            	  		var loginId=$scope.userName;
            	  		var securityData=data.response.data;
            	  		$cookies.put('loginId',loginId);
            	  		$cookies.put('securityData',securityData);
            	  		
            	  		location.href = location.origin + location.pathname + "ForgotPasswordPage.html"
            	  	}else {
            	  		$scope.error=data.response.message;
            	  	}
                }).error(function (data, status, headers, config) {
                	
                	$scope.loading = false;
                	//Show error msg in div tag
                	if(status!=200){
                		$scope.error='Cannot connect to server';
                	}
            });
    	};
}]);
