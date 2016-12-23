Ext.define('Appmob.view.mainleftmenutree.TopPanel.TopPanelController', {
	extend : 'Ext.app.ViewController',
	requires : [],
	alias : 'controller.menuTopPanelController',
	recognizing : false,
	recognition:null,
	searchCommand : [],

	init : function() {
		this.control({
			'container button[action=menuBtnToggle]' : {
				toggle : this.onButtonPress
			}
		});
		this.callParent();
		this.loadLangCombo();	
	},

	loadLangCombo : function(){

	
	Ext.Ajax.request({
			url : 'secure/SearchEngineService/getAvailableLanguages',
			method : 'GET',
			view : this.view,
			success : function(response, scope) {
				var responseData = Ext.decode(response.responseText);
				var data = Ext.decode(responseData.response.data.message);
				var combo = scope.view.down('#Lang');
				var store  = {
					fields: [ {
						name : 'name',
						type : 'string'
					}, {
						name : 'value',
						type : 'string',
					}],
					data : data					
				}
				combo.setStore(store);	
				 combo.select(combo.getStore().getAt(0));
			},
			failure : function() {
				//Ext.Msg.alert('Error', 'Cannot connect to server');
			}
		});
},

	afterTopPanelRender:function(panel)
	{
		//Get user cookie value set from angular js loginController
		var cookieUserInfo = Ext.util.Cookies.get('userInfo');
		if(cookieUserInfo==null){
			var pathName=location.pathname;
			var initialPath=pathName.slice(0,pathName.lastIndexOf("/"));
			location.href=location.origin+initialPath+"/";
		}
		panel.down('#userNameDField').setValue("Welcome "+decodeURI(cookieUserInfo));
		
		//Search Config
		var _currentScope=this;
		this.searchCommand.push("search");
		this.searchCommand.push("find");
		this.searchCommand.push("go");
		searchCommand=this.searchCommand;
		currentTab = this.getView();
		// txtField=this.getView().down("#searchs");
		var final_span = "";
		var interim_span = "";
		var final_transcript = '';

		var ignore_onend;
		var start_timestamp;
		if (!('webkitSpeechRecognition' in window)) {
			this.getView().down('#voice').setHidden(true);
			//upgrade();
		} else {
			start_button = 'inline-block';
			var recognition = new webkitSpeechRecognition();
			this.recognition=recognition;
			recognition.continuous = true;
			recognition.interimResults = true;

			recognition.onstart = function() {
				// currentTab = Ext.MainAppMagr.getActiveTab();
				recognizing = true;
				_currentScope.getView().down("#voice").setIcon(
						'images/mic-animate.gif');

			};

			recognition.onerror = function(event) {
				// currentTab = Ext.MainAppMagr.getActiveTab();
				if (event.error == 'no-speech') {

					_currentScope.getView().down("#voice").setIcon(
							'images/mic.gif');
					alert("No speech was detected. You may need to adjust your microphone");
					ignore_onend = true;
				}
				if (event.error == 'audio-capture') {

					currentTab.down("#voice").setIcon(
							'images/mic.gif');
					alert("No microphone was found. Ensure that a microphone is installed");
					ignore_onend = true;
				}
				if (event.error == 'not-allowed') {
					if (event.timeStamp - start_timestamp < 100) {
						alert("Permission to use microphone is blocked.");
					} else {
						alert("Permission to use microphone was denied.");
					}
					ignore_onend = true;
				}
			};

			recognition.onend = function() {
				// currentTab = Ext.MainAppMagr.getActiveTab();
				recognizing = false;
				if (ignore_onend) {

					return;
				}
				
				_currentScope.getView().down("#voice").setIcon(
						'images/menu/mic.png');

				if (!final_transcript) {

					return;
				}
				//showInfo('');
				if (window.getSelection) {
					window.getSelection().removeAllRanges();
					var range = document.createRange();
					range.selectNode(document
							.getElementById('final_span'));
					window.getSelection().addRange(range);
				}

			};

			recognition.onresult = function(event) {
				// currentTab = Ext.MainAppMagr.getActiveTab();
				var interim_transcript = '';
				for (var i = event.resultIndex; i < event.results.length; ++i) {
					if (event.results[i].isFinal) {
						final_transcript = event.results[i][0].transcript;
					} else {
						interim_transcript += event.results[i][0].transcript;
					}
				}
				var isSearchCommand = false;
				var inputWords = final_transcript.split(' ');

				for (var k = 0; k < inputWords.length; k++) {

					if (searchCommand.indexOf(inputWords[k]) != -1) {

						isSearchCommand = true;
						final_transcript = final_transcript
								.replace(inputWords[k], '');
					}
				}
				_currentScope.getView().down('#searchField').setValue(
						final_transcript);
				if (isSearchCommand) {
					_currentScope.onSearchClick(currentTab.down('#searchField'));
				}
				if (recognizing) {

					recognition.stop();
					return;
				}

				if (final_transcript || interim_transcript) {
					_currentScope.showButtons('inline-block');
				}
			};
		}
	},
	
	onButtonPress : function(button, pressed) {
		
		var westPanel = this.getView().up().up().down('#resourcePanel');
		westPanel.toggleCollapse();
	},
	
	onVoiceSearchClick:function(){

		currentScope = this;
		recognizing=this.recognizing;
		recognition=this.recognition;
		if (recognizing) {
			recognition.stop();
			return;
		}
		
		recognition.start();
		ignore_onend = false;

		this.getView().down("#voice").setIcon(
				'images/mic-slash.gif');

		currentScope.showButtons('none');
		start_timestamp = event.timeStamp;

		function showInfo(s) {
			if (s) {
				for (var child = info.firstChild; child; child = child.nextSibling) {
					if (child.style) {
						child = child.id == s ? 'inline'
								: 'none';
					}
				}
				info = 'visible';
			} else {
				info = 'hidden';
			}
		}

		 current_style;
	},
	showButtons:function(style) {
		/*if (style == current_style) {
			return;
		}*/
		current_style = style;
	},
	doVoiceSearch:function(btn){
		var mainTabView=this.getView().up();
		var searchTab=mainTabView.query('solrsearchmainpanel[isSearchCmp=true]');
		var component=null;
		var tab=null;
		if(searchTab.length==0){
			 component = Ext.create(
					"Appmob.view.searchengine.search.SearchEngineMainPanel", {
						closable : true,
						title : "Search Engine",
						refId : "-1",
						isSearchCmp:true
					});
	
			 tab = mainTabView.add({
				xtype : component
			});
		}else{
			component=searchTab[0];
			tab=component;
		}
		mainTabView.setActiveTab(tab);
			
		component.controller.onVoiceSearch(btn);
	},
	onSearchClick : function(button)
	{
		var mainTabView=this.getView().up();
		var searchTab=mainTabView.query('solrsearchmainpanel[isSearchCmp=true]');
		var component=null;
		var tab=null;
		if(searchTab.length==0){
			 component = Ext.create(
					"Appmob.view.searchengine.search.SearchEngineMainPanel", {
						closable : true,
						title : "Search Engine",
						refId : "-1",
						isSearchCmp:true,
						menuId:'searchtab'
					});
	
			/* tab = mainTabView.add({
				xtype : component
			});*/
		}else{
			component=searchTab[0];
			//tab=component;
		}
		this.addTab(component);
		//mainTabView.setActiveTab(tab);
			
		var searchText=this.getView().down('#searchField').getValue();
		var oprationType=this.getView().down('#Lang').getValue();
		component.controller.doSearch(component,searchText,oprationType);
	},
	
	onCloudClick : function()
	{
		var component = Ext.create(
				"Appmob.view.clouddrive.CloudDrive", {
					closable : true,
					title : "Cloud Drive",
					menuId:'cloudtab'
				});
		this.addTab(component);
	},
	
	addTab:function(tabView)
	{
		var mainTabView=this.getView().up();
		var tab = null;
		
		if(!isMultiTab){
	    		if(mainTabView.getActiveTab()){
					if(tabView.id != mainTabView.getActiveTab().id){	
								mainTabView.getActiveTab().close();
								tab = mainTabView.add({
									xtype : tabView
								});
					}
	    		}
	    		else{
	    			tab = mainTabView.add({
							xtype : tabView
					});
	    		}
	    }
		else{
			var flag = false;
			var tabBarLength = mainTabView.items.items.length;
			if(tabBarLength>0){
    			for(var i = 0;i<tabBarLength;i++){
    					if(tabView.menuId == mainTabView.items.items[i].menuId)
    					{
    						flag = true;
    						mainTabView.setActiveTab(mainTabView.tabBar.items.items[i].config.card);
    						break;
    					}
    			}
    		}
			if(!flag){
				tab = mainTabView.add({
					xtype : tabView
				});
			}
		}
		mainTabView.setActiveTab(tab);		
	},
	
	onMyProfileClick : function()
	{
		var component = Ext.create(
				"Appmob.view.usermanagement.enduser.UserProfile", {
					closable : true,
					title : "My Profile",
					menuId:'myprofiletab'
				});

		this.addTab(component);
	},
	
	onChangePwdClick:function()
	{
		var component = Ext.create(
				"Appmob.view.usermanagement.enduser.ChangePwd", {
					closable : true,
					title : "Change Password",
					menuId:'changepwdtab'
				});

		this.addTab(component);
	},
	
	onLogoutClick : function()
	{
		Ext.Ajax.request({
			url : "secure/Logout",
			method : 'POST',
			jsonData : {},
			success : function(response, scope) {
				
				var jsonRespone = Ext.JSON.decode(response.responseText);
				if (jsonRespone.response.success == "true") {
					//this.location.reload();
					var pathName=this.location.pathname;
					var initialPath=pathName.slice(0,pathName.lastIndexOf("/"));
					
					/** Clearing all the cookies from browser */
					Ext.util.Cookies.clear('changePwd',initialPath);
					Ext.util.Cookies.clear('XA_TID', initialPath);
					Ext.util.Cookies.clear('XA_ID', initialPath);
					Ext.util.Cookies.clear('JSESSIONID', initialPath);
					Ext.util.Cookies.clear('userInfo', initialPath);
					
					this.location.href=this.location.origin+initialPath+"/";
				} else {
					Ext.Msg.alert('Logout failed',jsonRespone.response.message);
				}
			},
			failure : function() {
				Ext.Msg.alert('Error', 'Cannot connect to server');
			}
		});
	}
});
