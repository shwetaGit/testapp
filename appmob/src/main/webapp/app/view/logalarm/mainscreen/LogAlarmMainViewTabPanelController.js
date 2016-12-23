/**
 * 
 */
Ext.define('Appmob.view.logalarm.mainscreen.LogAlarmMainViewTabPanelController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.logAlarmMainViewTabPanelController',
	
	init : function()
	{
		var component = Ext.create("Appmob.view.logalarm.mainscreen.AddLogAlarm", {
			title : "",
		});
		this.onAddTabClick(component);
	},
	onAddTabClick: function(component) 
	{
		var tabPanel = this.getView(),
        tab = tabPanel.add({
           xtype : component
         });
		tabPanel.setActiveTab(tab);
    },
    
    /*setFormData : function(logModuleData)
	{
    	var tabPanel = this.getView();
		var logModuleData = Ext.JSON.decode(logModuleData);
		var logModule = logModuleData[0];
		tabPanel.getActiveTab().setTitle(logModule.name);
		
		tabPanel.getActiveTab().getController().setFormData(logModuleData[0]);
	},*/
});