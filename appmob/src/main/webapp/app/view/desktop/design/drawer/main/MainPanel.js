Ext.define('Appmob.view.desktop.design.drawer.main.MainPanel', {
    extend : 'Ext.panel.Panel',
    xtype : 'mainPanel',
    requires : ['Appmob.view.desktop.design.drawer.main.MainPanelController','Appmob.view.desktop.design.drawer.resource.AppFormsList'],
    userinfo:{},
    id:'mainPanel',
    controller:'mainPanel',
   
    layout: {
        type: 'border'
    },
    bodyStyle:{
        background:"#f6f6f6"
    },
    items: [
            {
                region : 'west',
                itemId : 'westPanel',
                id:'westPanel',
                split : false,
                width : '20%',
                height:'100%',
                xtype : 'appFormsList',
                collapsible:false,
                hidden:false,
                title:"",
                autoScroll:true,
                bodyPadding:'0 5 0 0',
                plugins:'responsive',
                responsiveConfig: {
                      portrait: {
                          visible: false,
                          width:'25%'
                      },
                      landscape: {
                          visible: true,
                          width:'20%'
                      }
                  },
                style:{
                    background:"#ffffff"
                }
                
            },{
                region : 'center',
                xtype:'tabpanel',
                id:'appMainTabPanel',
                itemId:'appMainTabPanel',
                layout:'fit',
                tabBar:{
                             border:0,
                             margin:'5 0 0 0',
                             style:{"border-width":"0px !important","border-radius":"0px !important"},
                             height:48,
                             defaults:{
                                 height:32
                             }
                            
                 },
                bodyStyle:{
                                 background:"#f6f6f6"
                },
                header:{
                         titlePosition:1,
                        style:{
                            "background":"#ffffff",
                            "box-shadow": "0 3px 6px rgba(0,0,0,0.16), 0 3px 6px rgba(0,0,0,0.23)"
                        },
                      tools:[{
                        xtype:'displayfield',
                        id:'userNameDField',
                        itemId:'userNameDField',
                        style:"word-break: break-word; word-wrap: break-word;",
                        fieldStyle:{
                            color:"#424242",
                            "font-weight":"normal",
                            "font-size":"16px"
                        },listeners:{
                        	afterrender:'displayUserDetail'
                        }
             
                    
                        },{
                            xtype: 'button',
                            text:'',
                            scale:'large',
                            arrowVisible:false,
                            margin:'0 0 0 5',
                            style:{
                                  borderWidth:'0px',
                                  borderRadius:'50%',background:'transparent'
                               },
                            icon:'resources/appicons/ic_user_settings_grey.png',
                            arrowAlign:'bottom',
                            listeners : {mouseover : function() { this.menu.showBy(this); } },
                            menu:{
                                items : [
                                                            {
                                                                xtype : 'menuitem',
                                                                height : 38,
                                                                text : '<span class="user-settings-menu-item-cls">My Profile<span>',
                                                                listeners : {
                                                                    click : 'onMyProfileClick'
                                                                }
                                                            },
                                                            {
                                                                xtype : 'menuitem',
                                                                height : 38,
                                                                text : '<span class="user-settings-menu-item-cls">Change Password<span>',
                                                                listeners : {
                                                                    click : 'onChangePwdClick'
                                                                }
                                                            },
                                                            {
                                                                xtype : 'menuitem',
                                                                text : '<span class="user-settings-menu-item-cls">Cloud Drive<span>',
                                                                height : 38,
                                                                disabled : true,
                                                                listeners : {
                                                                    click : 'onCloudClick'
                                                                }
                                                            },
                                                            {
                                                                xtype : 'menuitem',
                                                                text : '<span class="user-settings-menu-item-cls">Log Out<span>',
                                                                height : 38,
                                                                listeners : {
                                                                    click : 'onLogoutClick'
                                                                }
                                                            }]
                            }
                         }],
                         items:[
                         { 
                               xtype: 'button',
                               text: '',
                               style:{
         	                 	  borderWidth:'0px',
                                  borderRadius:'50%',background:'transparent'
         	                   },
                               scale:'large',
                               icon:'resources/appicons/ic_drawer_button_grey.png',
                               margin:'0 10 0 0',
                               listeners:{
                                  click:'onDrawerBtnClick'
                               }
                         }
                         ]
                }
                

            }
    ],
    listeners:{
    	boxready:'onMainPanelAfterRender'
    }

});
