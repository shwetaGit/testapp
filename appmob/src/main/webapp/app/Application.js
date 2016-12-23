/**
 * The main application class. An instance of this class is created by app.js
 * when it calls Ext.application(). This is the ideal place to handle
 * application launch and initialization details.
 */
Ext.define('Appmob.Application', {
	extend : 'Ext.app.Application',

	name : 'Appmob',

	requires : [ "Ext.data.Model","Ext.app.ViewModel","Ext.ux.BoxReorderer", "Ext.ux.CellDragDrop",
			"Ext.ux.DataTip", "Ext.ux.Explorer", "Ext.ux.FieldReplicator",
			"Ext.ux.GroupTabPanel", "Ext.ux.GroupTabRenderer", "Ext.ux.IFrame",
			"Ext.ux.LiveSearchGridPanel", "Ext.ux.PreviewPlugin",
			"Ext.ux.ProgressBarPager", "Ext.ux.RowExpander",
			"Ext.ux.SlidingPager", "Ext.ux.Spotlight", "Ext.ux.TabCloseMenu",
			"Ext.ux.TabReorderer", "Ext.ux.TabScrollerMenu",
			"Ext.ux.ToolbarDroppable", "Ext.ux.TreePicker", "Ext.ux.ajax.*",
			"Ext.ux.data.*", "Ext.ux.DataView.*", "Ext.ux.dd.*",
			"Ext.ux.event.*", "Ext.ux.form.*", "Ext.ux.grid.*",
			"Ext.ux.statusbar.*", "Ext.button.*", "Ext.container.*",
			"Ext.dashboard.*", "Ext.dd.*", "Ext.dom.*", "Ext.event.*",
			"Ext.flash.*", "Ext.form.*", "Ext.fx.*", "Ext.grid.*",
			"Ext.layout.*", "Ext.menu.*", "Ext.panel.*", "Ext.picker.*",
			"Ext.plugin.*", "Ext.resizer.*", "Ext.rtl.*", "Ext.selection.*",
			"Ext.slider.*", "Ext.sparkline.*", "Ext.state.*", "Ext.tab.*",
			"Ext.tip.*", "Ext.toolbar.*", "Ext.tree.*", "Ext.util.*",
			"Ext.view.*", "Ext.window.*", "Ext.Action", "Ext.Component",
			"Ext.ComponentLoader", "Ext.ElementLoader", "Ext.EventManager",
			"Ext.FocusManager", "Ext.Img", "Ext.LoadMask", "Ext.ProgressBar",
			"Ext.ProgressBarWidget", "Ext.ZIndexManager","Appmob.view.databrowsercalendar.DBCalendar",
			"Appmob.view.fw.component.CustomComboBox" ,"Appmob.view.fw.component.TreeComponent",
			"Appmob.view.fw.component.FileUploadComponent"],

	views : [

	],

	controllers : [ 'Root' ],

	stores : [ 'Appmob.store.QueryCriteriaWidgetsStore' ],

	launch : function() {
		var loadMsg = Ext.get("loadMsg");
		 if(loadMsg){
		 	loadMsg.remove();
		 }
	}
});
Ext.Ajax.timeout = 180000; 
var sessionTimeOutFlag = false;

/**isMultiForm**/ var isMultiTab = true;


Ext.Ajax.setDefaultHeaders({'token': Ext.util.Cookies.get('token')});

Ext.Ajax.on('beforerequest', function(connection, options, eOpts) {

	if (options.maskEnable) {
		if (options.maskEle != null) {
			options.maskEle.mask('Request processing...');
		} else {
			Ext.getBody().mask('Request processing...');
		}
	}
});

Ext.Ajax.on('requestcomplete', function(conn, response, options, eOpts) {
		
	try {
		if (options.maskEnable) {
			if (options.maskEle != null) {
				options.maskEle.unmask();
			} else {
				Ext.getBody().unmask();
			}
		}
	} catch(e) {
		console.log("requestcomplete >> e -- " +e);
	}
});

Ext.Ajax.on('requestexception', function(conn, response, options, eOpts) {
	
	try{
		var responseText = Ext.JSON.decode(response.responseText);
		if (responseText.alarm) {
			if (responseText.alarm.alarmID == "ABSAA254905401" && !sessionTimeOutFlag) {
				sessionTimeOutFlag = true;
				var loginWindow = Ext.create('Appmob.view.login.SessionLogin').center();
				loginWindow.currentRequest = options;
				loginWindow.show();
			}
		}
	} catch(e){ 
		console.log("requestexception >> e -- " +e);
	}
	
	try{
		if (options.maskEnable) {
			if (options.maskEle != null) {
				options.maskEle.unmask();
			} else {
				Ext.getBody().unmask();
			}
		}
	} catch(e1) {
		console.log("requestexception >> e1 -- " +e1);
	}
});
