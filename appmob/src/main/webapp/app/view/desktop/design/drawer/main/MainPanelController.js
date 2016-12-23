Ext.define('Appmob.view.desktop.design.drawer.main.MainPanelController', {
    extend: 'Ext.app.ViewController',
 
    alias: 'controller.mainPanel',
    
    onMainPanelAfterRender : function(mainPanel){
       
    	this.configureTabContextMenu();
    	
        this.initializeGoogleMap();
        
        mainPanel.getEl().on('contextmenu', function(view) {
                       view.stopEvent();
        });
        this.checkForPasswordChange();
    },
    initializeGoogleMap : function(){
        var googleScript = document.createElement('script');
        googleScript.setAttribute("type","text/javascript");
        googleScript.setAttribute("src", "https://maps.googleapis.com/maps/api/js?v=3.18");
        document.body.appendChild(googleScript);
    },
    displayUserDetail:function()
    {
        //Get user cookie value set from angular js loginController
         var userNameDField =  Ext.getCmp('userNameDField');
        
        var cookieUserInfo = Ext.util.Cookies.get('userInfo');
        if(cookieUserInfo==null){
            var pathName=location.pathname;
            var initialPath=pathName.slice(0,pathName.lastIndexOf("/"));
            location.href=location.origin+initialPath+"/";
        }
        
        userNameDField.setValue(decodeURI(cookieUserInfo));
         
    
    },
    configureTabContextMenu : function(){
    	
        var appMainTabPanel = Ext.getCmp('appMainTabPanel');
        var scope = this;
        var contextMenu = new Ext.menu.Menu({
              targetTab :"",
              items: [{
                text: 'Close Other',
                scope:scope,
                listeners:{
                    click: function(tab){
                        
                        tab.scope.onCloseTabOptionClick(1);
                    }
                }
              },{
                text: 'Close All',
                scope:scope,
                listeners:{
                    click: function(tab){
                        
                        tab.scope.onCloseTabOptionClick(2);
                    }
                }
              }]
            });

        appMainTabPanel.tabBar.getEl().on('contextmenu', function(e, t, eOpts) {
            
                 e.stopEvent();
                 var appMainTabPanel =  Ext.getCmp('appMainTabPanel');
                 if(appMainTabPanel.items.items.length>0){
                  contextMenu.showAt(e.getXY());
                 }else{
                    return false;
                 }
            });
    },
     onCloseTabOptionClick : function(type){
    	 
         var appMainTabPanel = Ext.getCmp('appMainTabPanel');
         var activeItem = appMainTabPanel.getActiveTab();
         var centerTabs = appMainTabPanel.items.items;
         if(type == 1){
             
               
               for(var i = centerTabs.length -1; i >= 0 ; i--){
               
                  
                   if(centerTabs[i] !== activeItem){
           
                        centerTabs[i].close();
                   }
               }

         }else{
           while(centerTabs.length>0){
             var tab = centerTabs[0];
             tab.close();
          }

         }
         },
    onDrawerBtnClick : function(drawerBtn){
        var westPanel = Ext.getCmp('westPanel');
        westPanel.setHidden(!westPanel.isHidden());
    },
    
    onMyProfileClick : function()
    {
        
         this.pushViewInMainTab( "Appmob.view.usermanagement.enduser.UserProfile","My Profile","menuAction","Appmob.view.usermanagement.enduser.UserProfile");
    },
    
    onChangePwdClick:function()
    {
      
         this.pushViewInMainTab( "Appmob.view.usermanagement.enduser.ChangePwd","Change Password","menuAction","Appmob.view.usermanagement.enduser.ChangePwd");
    },
     onCloudClick : function()
    {
        
        this.pushViewInMainTab("Appmob.view.clouddrive.CloudDrive","Cloud Drive","menuAction","Appmob.view.clouddrive.CloudDrive");
    },
    
    pushViewInMainTab : function(className,title,cmpKey,cmpValue){
        var appMainTabPanel = Ext.getCmp('appMainTabPanel');
        if(className){
        var ident= {};
          ident[cmpKey]=cmpValue;

         var appMainTabPanel =  Ext.getCmp('appMainTabPanel');
         var clickedAction = appMainTabPanel.down("["+cmpKey+"='"+cmpValue+"']");
         
         if(clickedAction){
            appMainTabPanel.setActiveItem(clickedAction);
            return clickedAction;
         }else{
        
         var addedForm = appMainTabPanel.add(Ext.create(className,{
             closable:true,
             title:title,
             menuAction:className,
             cmpKey:cmpValue
            
         }));
         Ext.merge(addedForm,ident);
         appMainTabPanel.setActiveItem(addedForm);
         return addedForm;
         }
     }
    },

    onLogoutClick : function(logoutBtn)
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
                    
                    Ext.util.Cookies.clear('changePwd',initialPath);
                    
                    this.location.href=this.location.origin+initialPath+"/";
                } else {
                    Ext.Msg.alert('Logout failed',jsonRespone.response.message);
                }
            },
            failure : function() {
                Ext.Msg.alert('Error', 'Cannot connect to server');
            }
        });
    },
    checkForPasswordChange : function(){

		/** ADDING FLAG TO SET SECURITY QUESTION WHEN FLAT IS TRUE USER GETS THE POP-UP TO SET SECURITY QUESTION FROM USER PROFILE OPTION */
		var setSecutityQuestions = Ext.util.Cookies.get("setSecutityQuestions");
		if (setSecutityQuestions == "true") {
			Ext.util.Cookies.clear("setSecutityQuestions");
			
			Ext.MessageBox.show({
				title : "Note",
				msg : "Security questions not set yet, In case you forgot the password it is needed for password recovery.<br>GOTO My Profile and set security questions",
				icon : Ext.MessageBox.INFO

			});
		}
		
		var cookieChangePwd = Ext.util.Cookies.get('changePwd');
		if(cookieChangePwd=="true"){
			this.pushViewInMainTab( "Appmob.view.usermanagement.enduser.ChangePwd","Change Password","menuAction","Appmob.view.usermanagement.enduser.ChangePwd");
		}
	
    }
   
});
