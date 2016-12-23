Ext.define('Appmob.view.smartdevice.design.drawer.main.MainPanelController', {
    extend: 'Ext.app.ViewController',
 
    alias: 'controller.mainPanel',
   
    onMainPanelAfterRender : function(mainPanel){
        if(mainPanel.userinfo && mainPanel.userinfo.hasOwnProperty('firstName')){
            var westPanel = Ext.getCmp('westPanel');
            var fullName = mainPanel.userinfo.firstName+' '+mainPanel.userinfo.lastName;
            westPanel.setTitle('<div><div style="font-size:16px;font-weight:bold;">'+fullName+'</div><div style="font-style:italic;font-size:12px;">'+mainPanel.userinfo.emailId+'</div></div>');
        }
        
        this.initializeGoogleMap();
    },
    initializeGoogleMap : function(){
        var googleScript = document.createElement('script');
        googleScript.setAttribute("type","text/javascript");
        googleScript.setAttribute("src", "https://maps.googleapis.com/maps/api/js?v=3.18");
        document.body.appendChild(googleScript);
    },
    onDrawerBtnClick : function(drawerBtn){
        var westPanel = Ext.getCmp('westPanel');
        westPanel.setHidden(!westPanel.isHidden());
        var centerPanel = Ext.getCmp('centerPanel');
        centerPanel.setDisabled(!centerPanel.isDisabled());
          
    },
    onLogoutMenuItemClick :function( item, e, eOpts ){
            
            Ext.Msg.show({
            title:'Confirmation',
            message: 'Do you want to logout?',
            buttons: Ext.Msg.YESNO,
            icon: Ext.Msg.QUESTION,
            fn: function(btn) {
                 if (btn === 'yes') {
        
            Ext.Ajax.request({
                url : AppRestUrl+"secure/Logout",
                method : 'POST',
                jsonData : {},
                success : function(response, scope) {
                    
                    var jsonRespone = Ext.JSON.decode(response.responseText);
                    if (jsonRespone.response.success == "true") {
                        var pathName=this.location.pathname;
                    var initialPath=pathName.slice(0,pathName.lastIndexOf("/"));
                    
                    /** Clearing all the cookies from browser */
                    Ext.util.Cookies.clear('changePwd',initialPath);
                    Ext.util.Cookies.clear('XA_TID', initialPath);
                    Ext.util.Cookies.clear('XA_ID', initialPath);
                    Ext.util.Cookies.clear('JSESSIONID', initialPath);
                    Ext.util.Cookies.clear('userInfo', initialPath);
                    var mainViewport = Ext.getCmp('mainViewport');
                    mainViewport.removeAll();
                    mainViewport.add(Ext.create('Appmob.view.smartdevice.login.Login'));
                    
                    } else {
                        Ext.Msg.alert('Logout failed',
                                        jsonRespone.response.message);
                    }
                },
                failure : function() {
                    Ext.Msg.alert('Error', 'Cannot connect to server');
                }
            });
                 }}
            });
        
    }
});
