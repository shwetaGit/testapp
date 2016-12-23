Ext.define('Appmob.view.smartdevice.design.resource.AppFormsList', {
	extend : 'Ext.grid.Panel',
	xtype : 'appFormsList',
	controller:'appFormsList',
	requires:['Appmob.view.smartdevice.design.resource.AppFormsListController'],
	title:'',
	features:[{ftype:"grouping",groupHeaderTpl:'<b>{formGroupName} {name}</b>'}],
	hideHeaders:true,
	editTools:false,
	/*bodyStyle:{
		background:'#F2F2F2'
	},*/
	cls:'appMenuCls',
	header : {
		    title:{style:"color:#ffffff"}
	},
	columns: [
	    {
	    	xtype:'gridcolumn',
	    	header:"",
	    	dataIndex:'formGroupName',
	    	hidden:true
	    },
 		{
 			xtype:'templatecolumn',
 			tpl:'<div style="padding:10px;font-size:14px;font-weight: bold;vertical-align: middle;">{menuName}</div>',
 			flex:1
 		}
 	],
 	listeners:{
 		afterrender:'onAppFormsListAfteRender',
 		itemclick:'onAppFormsListItemClick'
 	}
   
});
