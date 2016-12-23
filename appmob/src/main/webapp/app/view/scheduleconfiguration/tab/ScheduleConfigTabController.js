/**
 * 
 */
Ext.define("Appmob.view.scheduleconfiguration.tab.ScheduleConfigTabController", {
	extend : 'Ext.app.ViewController',
	alias : 'controller.schedulerConfigTabController',
	requires : [ 'Appmob.view.scheduleconfiguration.panel.ScheduleConfig' ],

	init : function() {
		var component = Ext.create("Appmob.view.scheduleconfiguration.panel.ScheduleConfig", {
			title : "New Schedule",
			scheduleId : null,
			closable : false,
			icon : 'images/new.gif',
		});
		this.onAddTabClick(component);
	},

	onAddTabClick : function(component) {
		var tabPanel = this.getView(), tab = tabPanel.add({
			xtype : component
		});
		tabPanel.setActiveTab(tab);
	}
});