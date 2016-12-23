Ext.define('Appmob.view.desktop.design.drawer.resource.AppFormsListController', {
    extend: 'Ext.app.ViewController',
 
    alias: 'controller.appFormsList',
    appFormsList:undefined,
    homeScreenMenuItem:undefined,
    init:function(){
        this.appFormsList = this.getView();
    },
    onAppFormsListItemClick : function( sender, record, item, index, e, eOpts ){
        var nodedata = record.data;
        var scope = this;

        if(nodedata.menuId && nodedata.menuAction!==""){
        
         var appMainTabPanel =  Ext.getCmp('appMainTabPanel');
         var clickedAction = appMainTabPanel.down("[menuId='"+nodedata.menuId+"']");

         if(clickedAction){
            appMainTabPanel.setActiveItem(clickedAction);
         }else{


         var addedForm = appMainTabPanel.add(Ext.create(nodedata.menuAction,{
             closable:true,
             title:nodedata.menuName,
             menuId:nodedata.menuId,
             nodedata:nodedata,
             sender:scope,
             refId:nodedata.hasOwnProperty('refId')?nodedata.refId:"",
             listeners:{ 
                close: function(tab){
                    tab.sender.removeClosedTabFromSavedTabs(tab.nodedata);
                }
             }
         }));

         appMainTabPanel.setActiveItem(addedForm);
         if(nodedata.autoSave){
        	 this.saveFormAsStoredTab(nodedata);
         }
         
         }
     }
    },
    onDrawerBtnClick : function(drawerBtn){
        var westPanel = Ext.getCmp('westPanel');
        westPanel.setHidden(!westPanel.isHidden());
        var centerPanel = Ext.getCmp('centerPanel');
        centerPanel.setDisabled(!centerPanel.isDisabled());
          
    },
    onAppFormsListAfteRender : function(appFormsList){
       
       this.fetchAndLoadFormList();
       
    },

    fetchAndPushStoredTabs :function(){
        var sender = this;
        
        var loadMask = new Ext.LoadMask({
            msg    : 'Please wait...',
            target : Ext.getCmp('appMainTabPanel')
        }).show();

        Ext.Ajax.request({
            url : "secure/MenuService/fetchStoreMenu",
            method:'POST', 
            scope:sender,
            loadMask:loadMask,
            success : function(response,sender){
                var scope = sender.scope;
                var appMainTabPanel =  Ext.getCmp('appMainTabPanel');
        
                var menus = Ext.decode(response.responseText);
                var data = Ext.decode(menus.response.data);
                    
                    var appFormListRoot = scope.appFormsList.getRootNode() ;
                    

                    for (var i = 0; i <= data.length; i++) {
                        var menuObj = data[i];
                       
                        var menuNode = appFormListRoot.store.findNode("menuId",menuObj?menuObj.menuId:"");
                        
                        if(menuNode){
                            var nodedata = menuNode.data;

                        var addedForm = appMainTabPanel.add(Ext.create(nodedata.menuAction,{
                            closable:true,
                            title:nodedata.menuName,
                            menuId:nodedata.menuId,
                            nodedata:nodedata,
                            sender:scope,
                            refId:nodedata.hasOwnProperty('refId')?nodedata.refId:"",
                            listeners:{ 
                                close: function(tab){
                                tab.sender.removeClosedTabFromSavedTabs(tab.nodedata);
                            }
                            }
                        }));

                        appMainTabPanel.setActiveItem(addedForm);

                        }
                    }
                sender.loadMask.hide();
                },
            failure : function(response,sender){
                sender.loadMask.hide();
            }
        },sender);      

    },
    saveFormAsStoredTab : function(config){

           if(config){
                    var json = {};
                    json.id = config.menuId;
                    json.value = config.menuAction;
                    
                     Ext.Ajax.request({
                         url : "secure/MenuService/storeMenu",
                         method:'POST', 
                         jsonData: json,
                         success : function(response){
                         },
                         failure : function(response){
                         }
                     });    
            }
    },
    removeClosedTabFromSavedTabs : function(config){
                    var json = {};
                    
                    json.id = config.menuId;
                    json.value = config.menuAction;
                    
                     Ext.Ajax.request({
                         url : "secure/MenuService/deleteMenu",
                         method:'POST', 
                         jsonData: json,
                         success : function(response){
                            
                         },
                         failure : function(response){
                         }
                     });            
                    
    },
    fetchAndLoadFormList : function(){
        var sender = this;
        var loadMask = new Ext.LoadMask({
            msg    : 'Loading...',
            target : this.appFormsList
        }).show();

        Ext.Ajax.request({
                     url : "secure/MenuService/findMainScreenMenus",
                     method:'POST', 
                     sender:sender,
                     loadMask:loadMask,
                     jsonData:{
                     },
                     success : function(response,scope){
                         var sender = scope.sender;
                         var responseJson = Ext.JSON.decode(response.responseText);

//                          var data = Ext.decode(responseJson.response.data);
                          
                          var data = responseJson.response.data; 
                          
                          sender.configureAppMenu(scope.sender,sender.view.getRootNode(),data.menus,0);
                          sender.pushHomeScreen(data);

                          sender.fetchAndPushStoredTabs();
                          scope.loadMask.hide();
                     },
                     failure : function(response,scope){
                    	   var pathName=this.location.pathname;
                           var initialPath=pathName.slice(0,pathName.lastIndexOf("/"));
                        
                           Ext.util.Cookies.clear('changePwd',initialPath);
                           Ext.util.Cookies.clear('XA_TID', initialPath);
                           Ext.util.Cookies.clear('XA_ID', initialPath);
                           Ext.util.Cookies.clear('JSESSIONID', initialPath);
                           Ext.util.Cookies.clear('userInfo', initialPath);
                        
                           this.location.href=this.location.origin+initialPath+"/";
                           scope.loadMask.hide();
                     }
                 },sender);   
    },
    pushHomeScreen : function(data){
        
        var homescreenlist = data.homeScreenMenus;
        
        if(homescreenlist) {
            
                try {
                    var appMainTabPanel =  Ext.getCmp('appMainTabPanel');
                    appMainTabPanel.removeAll();
                    
                    
                    for(var i=0; i < homescreenlist.length; i++) {
                        var config = homescreenlist[i];
                        var addedtab = appMainTabPanel.add(Ext.create(config.menuAction, {
                            closable : false,
                            title : config.text,
                            refId : config.refId,
                            menuid : config.menuId
                        }));
                        appMainTabPanel.setActiveItem(addedtab);
                    }
                } catch(e) {    
                    
                }
        }
    },
    configureAppMenu: function(scope,menunode,data,grouper){
        
          for (var i = 0; i <= data.length; i++) {
            var obj = data[i];
            if(obj){
                
                obj['menuGroupName'] = grouper;
                obj['icon'] = 'none';
                if(obj.menuIcon && obj.menuIcon != "")
                {
                    obj['icon'] = obj.menuIcon;
                    obj['cls'] = 'menuTreeLeafNode';
                } else {
                    obj['icon'] = 'none';
                    obj['cls'] = 'menuTreeLeafNode menuTreeLeafNodeIcon';
                }
                var addedNode = menunode.appendChild(obj);
                
                if(obj.children && obj.children.length>0){
                	 var allFolderChild = Ext.Array.filter(obj.children,function(child){return child.leaf == false});
                     if(allFolderChild.length>0){
                    	 addedNode.expand();
                     }
                    scope.configureAppMenu(scope,addedNode,obj.children,obj.menuName);
                }
            }
   
          }

    }
   
});
