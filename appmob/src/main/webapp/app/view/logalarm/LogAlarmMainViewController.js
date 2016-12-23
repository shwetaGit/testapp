/**
 * 
 */
Ext.define('Appmob.view.logalarm.LogAlarmMainViewController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.logAlarmMainViewController',
	
	logAlarmTreePanelController : null,
	logAlarmMainViewTabPanelController : null,
	init : function() {
		var currentObject = this;
		currentObject.logAlarmTreePanelController = currentObject.view.down('#logAlarmTreePanel').getController();
		currentObject.logAlarmMainViewTabPanelController = currentObject.view.down('#logAlarmMainViewTabPanel').getController();
		
		currentObject.getLogModules();
	},
	
	getLogModules : function(){
		var currentObject = this;
		Ext.Ajax.request({
			timeout: 6000000,
			url : 'secure/logAlarmService/getLoggerDetails',
			method : 'GET',
			jsonData : {},
			success : function(response, opts){
				var responseJson = Ext.JSON.decode(response.responseText);
				if (responseJson.response.success == true) {
					var logConfigData = Ext.JSON.decode(responseJson.response.data);
					currentObject.logAlarmMainViewTabPanelController.getView().getActiveTab().getController().loadLoggerData(logConfigData);
					
					Ext.Ajax.request({
						timeout: 6000000,
						url : 'secure/logAlarmService/getListOfAlarms',
						method : 'GET',
						jsonData : {},
						success : function(response, opts){
							var responseJson = Ext.JSON.decode(response.responseText);
							if (responseJson.response.success == true) {
								var logModuleData = responseJson.response.data;
								currentObject.logAlarmTreePanelController.setFormData(logModuleData);
//								currentObject.logAlarmMainViewTabPanelController.setFormData(logModuleData);
							} else {
								Ext.Msg.alert("Error.", responseJson.response.message);
							}
						},
						failure : function(response, opts){
							var responseJson = Ext.JSON.decode(response.responseText);
							Ext.Msg.alert("Error...", responseJson.response.message);
						}
					});
				} else {
					Ext.Msg.alert("Error.", responseJson.response.message);
				}
			},
			failure : function(response, opts){
				var responseJson = Ext.JSON.decode(response.responseText);
				Ext.Msg.alert("Error...", responseJson.response.message);
			}
		});
	},
	
	setButtonText : function(text) {
		this.view.down('#saveButton').setText(text);
	},
	
	onClearButton : function(but, evt) {
		this.setButtonText("Save Module");
//		this.logAlarmMainViewTabPanelController.getView().getActiveTab().setTitle("New Log Module");
		this.logAlarmMainViewTabPanelController.getView().getActiveTab().getController().clearFormData(but,evt);
	},
	
	onSaveButton : function(but, evt) {
		this.logAlarmMainViewTabPanelController.getView().getActiveTab().getController().onSaveButton(but,evt);
	}
});
