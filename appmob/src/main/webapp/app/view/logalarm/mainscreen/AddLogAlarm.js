/**
 * 
 */
Ext.define('Appmob.view.logalarm.mainscreen.AddLogAlarm', {
	extend : 'Ext.form.Panel',
	xtype : 'addLogAlarm',
	requires : [ 'Appmob.view.logalarm.mainscreen.AddLogAlarmController',
			'Appmob.view.logalarm.mainscreen.AppAlarmGridPanel',
			'Appmob.view.logalarm.mainscreen.AppAlarmPanel',
			'Ext.form.FieldSet', 'Ext.form.field.ComboBox',
			'Ext.form.FieldContainer' ],
	controller : 'addLogAlarmController',
	title : 'Add Alarm',
	layout : 'anchor',
	autoScroll : true,
	defaults : {
		anchor : '100% 100%',
		margin : 10,
		labelWidth : 150,
		allowBlank : false,
	},
	
	items : [ {
		xtype : 'fieldset',
		title : 'Domain configuration',
		collapsible : true,
		defaults : {
			anchor : '100% 100%',
			margin : 10,
			labelWidth : 150,
			allowBlank : false,
		},
		items : [ {
			xtype : 'hidden',
			name : 'bcId',
			itemId : 'bcId',
			value : ''
		},{
			xtype : 'hidden',
			name : 'domainId',
			itemId : 'domainId',
			value : ''
		}/*,{
			xtype: 'fieldcontainer',
			layout: 'column',
			items : [{
				labelWidth : 150,
				xtype : 'textfield',
				name : 'bcName',
				itemId : 'bcName',
				readOnly : true,
				columnWidth : .70,
				fieldLabel : 'Bounded context name',
				emptyText : 'Bounded context name',
				blankText : 'Bounded Context name should not be blank!'
			}, {
				labelWidth : 70,
				xtype : 'textfield',
				name : 'bcPrefix',
				itemId : 'bcPrefix',
				readOnly : true,
				columnWidth : .30,
				margin : '0 10 0 10',
				fieldLabel : 'Prefix',
				emptyText : 'Bounded context prefix',
				blankText : 'Bounded Context prefix should not be blank!'
			}]
		}, {
			xtype: 'fieldcontainer',
			layout: 'column',
			items : [{
				labelWidth : 150,
				xtype : 'textfield',
				name : 'domainName',
				itemId : 'domainName',
				readOnly : true,
				columnWidth : .70,
				fieldLabel : 'Domain name',
				emptyText : 'Domain name',
				blankText : 'Domain name should not be blank!'
			}, {
				labelWidth : 70,
				xtype : 'textfield',
				name : 'domainPrefix',
				itemId : 'domainPrefix',
				readOnly : true,
				columnWidth : .30,
				margin : '0 10 0 10',
				fieldLabel : 'Prefix',
				emptyText : 'Domain prefix',
				blankText : 'Domain prefix should not be blank!'
			}]
		}*/, {
			xtype: 'fieldcontainer',
			layout: 'column',
			items : [{
				labelWidth : 150,
				xtype : 'checkbox',
				name : 'trace',
				columnWidth : .20,
				itemId : 'trace',
				fieldLabel : 'Trace',
			}, {
				xtype : 'combobox',
				name : 'criticality',
				itemId : 'criticality',
				fieldLabel : 'Criticality',
				margin : '0 10 0 10',
				columnWidth : .80,
				store : {
					fields : ['criticality','label'],
					sorters: 'criticality',
					data : [{ label : 'LOW', criticality : '1' },
					        { label : 'MEDIUM', criticality : '2' },
					        { label : 'HIGH', criticality : '3' }]
				},
				displayField : 'label',
				valueField : 'criticality',
				emptyText : 'Criticality',
				blankText : 'Criticality should not be blank!'
			}]
		}, {
			xtype : 'fieldcontainer',
			fieldLabel : 'Default Connector Order',
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
				emptyText : 'Default Connector Order',
				blankText : 'Default Connector Order should not be blank!',
				margin : '0 10 0 0',
				flex : 2
			}, {
				xtype : 'combobox',
				name : 'connectorOrderSeverity',
				displayField : 'label',
				valueField : 'severity',
				itemId : 'connectorOrderSeverity',
				emptyText : 'Default Connector Order Severity',
				blankText : 'Default Connector Order Severity should not be blank!',
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
		}/*, {
			xtype : 'numberfield',
			name : 'idRangeStartsWith',
			itemId : 'idRangeStartsWith',
			fieldLabel : 'Alarm ID Start Range',
			minValue : 0,
			value : 100,
			disabled : true,
			emptyText : 'Alarm ID Range Starts With Mentioned Number',
			blankText : 'Alarm ID Range Starts With should not be blank!'
		} */]

	}, {
		xtype : 'appAlarmPanel',
		itemId : 'appAlarmPanel'
	}, {
		xtype : 'appAlarmGridPanel',
		itemId : 'appAlarmGridPanel'
	} ],

	/*buttons : [ {
		text : 'Clear',
		itemId : 'clearButton',
		handler : 'onClearButton',
	}, {
		text : 'Update Module',
		itemId : 'saveButton',
		formBind : true,
		handler : 'onSaveButton',
	} ]*/

});