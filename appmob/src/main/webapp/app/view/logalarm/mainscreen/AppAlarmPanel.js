/**
 * 
 */
Ext.define('Appmob.view.logalarm.mainscreen.AppAlarmPanel', {
	extend : 'Ext.form.Panel',
	xtype : 'appAlarmPanel',
	requires : [ 'Appmob.view.logalarm.mainscreen.AppAlarmPanelController' ],
	controller : 'appAlarmPanelController',
	items : [ {
		xtype : 'fieldset',
		title : 'App Alarm',
		collapsible : true,
		defaults : {
			anchor : '100% 100%',
			margin : 10,
			labelWidth : 150,
			allowBlank : true,
		},
		items : [{
			xtype : 'hidden',
			name : 'alarmIndex',
			itemId : 'alarmIndex',
			value : ''
		}, {
			xtype : 'textfield',
			name : 'alarmId',
			itemId : 'alarmId',
			fieldLabel : 'ID',
			emptyText : 'App Alarm ID',
			blankText : 'App Alarm ID should not be blank!',
			disabled : true/*,
			value : 100*/
		},{
			xtype : 'textfield',
			fieldLabel : 'Context id',
			name : 'contextId',
			itemId : 'contextId',
			regex :  /^[0-9]+$/,
			maxLength: 3,
			minLength: 3
		},{
			xtype : 'fieldcontainer',
			fieldLabel : 'Connector Order',
			defaults : {
				labelWidth : 150,
				allowBlank : false,
			},
			layout : 'hbox',
			items : [ {
				xtype : 'combobox',
				name : 'connectorOrder',
				itemId : 'connectorOrder',
				displayField : 'name',
				valueField : 'cid',
				emptyText : 'Connector Order',
				blankText : 'Connector Order should not be blank!',
				margin : '0 10 0 0',
				flex : 2
			}, {
				xtype : 'combobox',
				name : 'connectorOrderSeverity',
				displayField : 'label',
				valueField : 'severity',
				itemId : 'connectorOrderSeverity',
				emptyText : 'Connector Order Severity',
				blankText : 'Connector Order Severity should not be blank!',
				margin : '0 10 0 0',
				flex : 2
			}, {
				xtype : 'button',
				text : 'Add',
				icon : 'images/entitybuilder/add.png',
				itemId : 'connectorAddButton',
				handler : 'onConnectorAddButton',
				flex : 1
			} ]
		}, {
			xtype : 'fieldcontainer',
			fieldLabel : 'Selected Default Connector Order',
			defaults : {
				labelWidth : 150,
				allowBlank : false,
			},
			items : [{
				xtype : 'grid',
				name : 'connectorOrderGrid',
				itemId : 'connectorOrderGrid',
				height : 150,
				stateful : true,
				collapsed : false,
				multiSelect : true,
				autoScroll : true,
				fieldLabel : 'Connector Order',
				columnLines : true,
				viewConfig : {
					enableTextSelection : true
				},
				store : {
					fields : [],
					data : []
				},
				columns : [ {
					text : 'Connector ID',
					width : '25%',
					sortable : true,
//					hidden : true,
					dataIndex : 'cid'
				}, {
					text : 'Connector Name',
					width : '25%',
					dataIndex : 'name'
				}, {
					text : 'Severity ID',
					width : '25%',
//					hidden : true,
					dataIndex : 'severity'
				}, {
					text : 'Severity',
					width : '25%',
					dataIndex : 'label'
				}]
			} ]
		}, {
			xtype : 'combobox',
			name : 'severity',
			itemId : 'severity',
			fieldLabel : 'Severity',
			displayField : 'label',
			valueField : 'severity',
			emptyText : 'App Alarm Severity',
			blankText : 'App Alarm Severity should not be blank!'
		}, {
			xtype : 'combobox',
			name : 'architectureLayer',
			itemId : 'architectureLayer',
			fieldLabel : 'Architecture Layer',
			displayField : 'layer',
			valueField : 'layerId',
			emptyText : 'Architecture Layer',
			blankText : 'Architecture Layer should not be blank!'
		}, {
			xtype : "textarea",
			name : 'message',
			itemId : 'message',
			fieldLabel : "Message Description",
			emptyText : 'Message should be format specified',
			blankText : 'Message should not be blank!',
			allowBlank : true,
			tip: "Message should be format specified using &lt;string&gt;, &lt;character&gt;, &lt;decimal&gt; and &lt;float&gt; specifiers, e.g &lt;string&gt; Saved Successfully, Error in &lt;string&gt; entity &lt;string&gt; on line &lt;decimal&gt;",
			listeners: {
			    render: function(c) {
			      Ext.create('Ext.tip.ToolTip', {
			        target: c.getEl(),
			        html: c.tip,
			      });
			    },
			  }
			
		}, {
			xtype : "textarea",
			name : 'diagnose',
			itemId : 'diagnose',
			fieldLabel : "Diagnose",
			emptyText : 'Diagnose',
			blankText : 'Diagnose should not be blank!',
			allowBlank : true
		}, {
			xtype : "textarea",
			name : 'solution',
			itemId : 'solution',
			fieldLabel : "Solution",
			emptyText : 'Solution',
			blankText : 'Solution should not be blank!',
			allowBlank : true
		}, {
			xtype : 'combobox',
			name : 'alarmStatus',
			itemId : 'alarmStatus',
			fieldLabel : 'Alarm Status',
			displayField : 'status',
			valueField : 'statusId',
			emptyText : 'Alarm Status',
			blankText : 'Alarm Status should not be blank!'
		}, {
			xtype : 'combobox',
			name : 'eventAction',
			itemId : 'eventAction',
			fieldLabel : 'Event Action',
			displayField : 'eventAction',
			valueField : 'eventActionId',
			emptyText : 'Event Action',
			blankText : 'Event action should not be blank!'
		}, {
			xtype : 'combobox',
			name : 'exception',
			itemId : 'exception',
			fieldLabel : 'Exception Label',
			store : {
				fields : ['exceptionId','exceptionName'],
				sorters: 'exceptionId'
			},
			displayField : 'exceptionName',
			valueField : 'exceptionId',
			emptyText : 'Exception',
			blankText : 'Exception should not be blank!',
			allowBlank : true
		}, {
			xtype : 'displayfield',
			name : 'exceptionLabel',
			itemId : 'exceptionLabel',
			fieldLabel : 'Exception'
		}, {
			xtype : 'displayfield',
			name : 'rootExceptionLabel',
			itemId : 'rootExceptionLabel',
			fieldLabel : 'Root exception'
		}, {
			xtype : 'fieldcontainer',
			layout : {
				type : 'hbox',
			},
			defaults : {
				margin : '0 0 0 10',
			},
			items : [ {
				flex : 7
			}, {
				xtype : "button",
				text : 'Set New',
				icon : 'images/entitybuilder/reset.png',
				name : 'resetButton',
				itemId : 'resetButton',
				flex : 1.5,
				handler : 'onResetButton'
			}, {
				xtype : "button",
				text : 'Update Alarm',
				icon : 'images/entitybuilder/save.png',
				name : 'addButton',
				itemId : 'addButton',
				formBind : true,
				flex : 1.5,
				handler : 'onAddAppAlarm'
			} ],
		} ],

	} ],

});