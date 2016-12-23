Ext.define('Appmob.view.desktop.design.drawer.resource.AppFormsList', {
	extend : 'Ext.tree.TreePanel',
	xtype : 'appFormsList',
	controller:'appFormsList',
	requires:['Appmob.view.desktop.design.drawer.resource.AppFormsListController'],
	title:'',
	features:[{ftype:"grouping",groupHeaderTpl:'<b>{formGroupName} {name}</b>'}],
	hideHeaders:true,
	editTools:false,
	rootVisible:false,
	useArrows : true,
	rowLines : false,
	collapseFirst :false,
	header : {
        			titlePosition : 1,
        			style:{
        				"background":"#35baf6",
        				"box-shadow": "0 3px 6px rgba(0,0,0,0.16), 0 3px 6px rgba(0,0,0,0.23)"
        			},
                    items : [ {
        				xtype : 'image',
        				src : 'images/logo.png',
        				id : 'userImg',
        				height : 80,
        				width : 80,
        				margin : '5 5 5 5'
        			},{
                        xtype:'displayfield',
                        id:'appNameDField',
                        itemId:'appNameDField',
                        width:"70%",
                        value:(AppName)?AppName:"",
                        style:"word-break: break-word; word-wrap: break-word;",
                        fieldStyle:{
                        	color:"#ffffff",
                        	"font-size":"24px"
                        },
        			
                    }
                    ]
        		},
	bodyStyle:{
		background:'#424242'
	},
	cls:'appMenuTreePanelFocus',

	columns: [
		{
			xtype:'treecolumn',
			dataIndex:'menuName',
			flex:1
		},
	    {
	    	xtype:'gridcolumn',
	    	header:"",
	    	dataIndex:'menuGroupName',
	    	hidden:true
	    },
 		{
 			xtype:'templatecolumn',
 			tpl:'<div style="padding:8px;font-size:14px;font-weight: normal;vertical-align: middle;">{menuName}</div>',
 			hidden:true,
 			flex:1
 		}
 	],
 	listeners:{
 		afterrender:'onAppFormsListAfteRender',
 		itemclick:'onAppFormsListItemClick'
 	}
   
});
