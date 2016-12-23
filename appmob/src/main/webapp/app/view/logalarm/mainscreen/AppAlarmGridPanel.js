/**
 * 
 */
Ext.define('Appmob.view.logalarm.mainscreen.AppAlarmGridPanel', {
	extend : 'Ext.grid.Panel',
	xtype : 'appAlarmGridPanel',
	requires : [ 'Appmob.view.logalarm.mainscreen.AppAlarmGridPanelController',
			'Ext.grid.column.Action' ],
	controller : 'appAlarmGridPanelController',
	stateful : true,
	collapsible : true,
	collapsed : false,
	multiSelect : true,
	autoScroll : true,
	title : 'App Alarams',
	columnLines : true,
	viewConfig : {
		enableTextSelection : true
	},
	store : {
		fields : [ {
			name : 'alarmIndex',
			type : 'string'
		}, {
			name : 'alarmId',
			type : 'string'
		}, {
			name : 'connectorOrder',
			type : 'string'
		}, {
			name : 'message',
			type : 'string'
		}, {
			name : 'diagnose',
			type : 'string'
		}, {
			name : 'solution',
			type : 'string'
		}, ],
		data : [],
		sorters: [{
	         property: 'alarmId',
	         direction: 'ASC'
	     }],
	},
	initComponent : function() {
		
		var me = this;
		me.columns = [ {
			text : 'Alarm Index',
//			width : '1%',
			sortable : false,
			dataIndex : 'alarmIndex',
			hidden : true
		}, {
			text : 'Alarm ID',
			width : '08%',
			sortable : false,
			dataIndex : 'alarmId'
		}, {
			text : 'Alarm context',
			width : '06%',
			sortable : false,
			hidden : true,
			dataIndex : 'alarmContext'
		}, {
			text : 'Severity',
			width : '06%',
			sortable : false,
			dataIndex : 'severity'
		}, {
			text : 'Layer',
			width : '08%',
			sortable : false,
			dataIndex : 'architectureLayer'
		}, {
			text : 'Connector Order',
			width : '13%',
			sortable : false,
			dataIndex : 'connectorOrder'
		}, {
			text : 'Message',
			width : '13%',
			sortable : false,
			dataIndex : 'message'
		}, {
			text : 'Diagnose',
			width : '13%',
			sortable : false,
			dataIndex : 'diagnose'
		}, {
			text : 'Solution',
			width : '15%',
			sortable : false,
			dataIndex : 'solution'
		}, {
			text : 'Exception',
			width : '08%',
			sortable : false,
			dataIndex : 'exception'
		}, {
			text : 'Status',
			width : '08%',
			sortable : false,
			dataIndex : 'alarmStatus'
		}, {
			text : 'Event action',
			width : '08%',
			sortable : false,
			dataIndex : 'eventAction'
		} ];

		me.callParent();
	},
	listeners : {
		select : function(selModel, record, index, options) {
			var appAlarm = record.getData();
			appAlarm.id = appAlarm.alarmIndex;
			this.up().down('#appAlarmPanel').getController().renderFormData(appAlarm);
		}
	}
});
