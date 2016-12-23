/**
 * 
 */
Ext.define('Appmob.view.logalarm.mainscreen.AddLogAlarmController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.addLogAlarmController',
	
//	moduleId : null,
	trace : null,
	connectorOrder : null,
	connectorOrderSeverity : null,
	connectorOrderGrid : null,
	criticality : null,
	idRangeStartsWith : null,
	appAlarmPanel : null,
	appAlarmPanelController : null,
	appAlarmGridPanel : null,
	appAlarmGridPanelController : null,
	
	init : function() {
		var currentObject = this;
//		currentObject.moduleId = currentObject.view.down('#moduleId');
		currentObject.trace = currentObject.view.down('#trace');
		currentObject.connectorOrder = currentObject.view.down('#connectorOrder');
		currentObject.connectorOrderSeverity = currentObject.view.down('#connectorOrderSeverity');
		
		currentObject.connectorOrderGrid = currentObject.view.down('#connectorOrderGrid');
		
		currentObject.criticality = currentObject.view.down('#criticality');
		currentObject.idRangeStartsWith = currentObject.view.down('#idRangeStartsWith');
		
		currentObject.appAlarmPanel = currentObject.view.down('#appAlarmPanel');
		currentObject.appAlarmPanelController = currentObject.appAlarmPanel.getController();
		
		currentObject.appAlarmGridPanel = currentObject.view.down('#appAlarmGridPanel');
		currentObject.appAlarmGridPanelController = currentObject.appAlarmGridPanel.getController();
	},
	
	loadLoggerData : function(logConfigData) {
		var currentObject = this;
		currentObject.setConnectorOrder(logConfigData.connectorArray);
		currentObject.setSeverities(logConfigData.alarmSeverity);
		
		currentObject.appAlarmPanelController.setConnectorOrder(logConfigData.connectorArray);
		currentObject.appAlarmPanelController.setSeverities(logConfigData.alarmSeverity);
		currentObject.appAlarmPanelController.setExceptions(logConfigData.exceptions);
		currentObject.appAlarmPanelController.setAlarmStatus(logConfigData.alarmStatus);
		currentObject.appAlarmPanelController.setEventActions(logConfigData.eventActions);
		currentObject.appAlarmPanelController.setArchitectureLayers(logConfigData.architectureLayers);
	},
	
	setFormData : function(bcData, domainData) {
		var currentObject = this;
		this.clearFormData();
		currentObject.view.down('#bcId').setValue(bcData.bId);
//		currentObject.view.down('#bcName').setValue(bcData.contextName);
//		currentObject.view.down('#bcPrefix').setValue(bcData.contextPrefix);
		currentObject.view.down('#domainId').setValue(domainData.mappingId);
//		currentObject.view.down('#domainName').setValue(domainData.domain);
//		currentObject.view.down('#domainPrefix').setValue(domainData.domainPrefix);
//		currentObject.moduleId.setValue(logModuleData.id);
		Ext.Ajax.request({
			timeout: 6000000,
			url : 'secure/logAlarmService/getGridData',
			method : 'POST',
			jsonData : domainData.mappingId,
			currentObject : currentObject,
			success : function(response, opts){
				var responseJson = Ext.JSON.decode(response.responseText);
				if (responseJson.response.success == true) {
					var logAlarmData = responseJson.response.data;
					currentObject.view.down('#addButton').setText('Update Alarm');
					if(logAlarmData != null) {
						currentObject.trace.setValue(logAlarmData.trace);
						currentObject.criticality.select(currentObject.criticality.getStore().getAt(logAlarmData.domainCriticality-1));
						var connector_orders = logAlarmData.connectorLogPriority.split(',');
						for(i = 0;i<connector_orders.length;i++) {
							var connector_order = connector_orders[i];
							if(connector_order != -1) {
								currentObject.connectorOrder.select(currentObject.connectorOrder.getStore().getAt(i));
								currentObject.connectorOrderSeverity.select(currentObject.connectorOrderSeverity.getStore().getAt(connector_order));
								currentObject.onConnectorAddButton();
							}
						}
						currentObject.appAlarmPanelController.setFormData(logAlarmData.artLogAlarm);
					}
				} else {
					Ext.Msg.alert("Error.", responseJson.response.message);
				}
			},
			failure : function(response, opts){
				var responseJson = Ext.JSON.decode(response.responseText);
				Ext.Msg.alert("Error...", responseJson.response.message);
			}
		});
//		currentObject.idRangeStartsWith.setValue(logModuleData.idRangeStartsWith);
	},
	
	clearConnectorOrderGrid : function() {
		this.connectorOrderGrid.store.removeAll();
	},
	
	clearFormData : function() {
		var logModuleForm = this.view.getForm();
		logModuleForm.reset();
		this.clearConnectorOrderGrid();
		this.appAlarmGridPanelController.clearGrid();
		this.appAlarmPanelController.onClearButton();
	},
	
	setConnectorOrder : function(connectorsData) {
		var store = this.connectorOrder.getStore();
		var connectorOrderStore = Ext.create('Ext.data.Store', {
					fields : ['cid', 'name'],
					data : connectorsData,
					
					sorters: [{
				         property: 'cid',
				         direction: 'ASC'
				     }],
				});

		this.connectorOrder.setStore(connectorOrderStore);
	},
	
	setSeverities : function(severityData) {
		var store = this.connectorOrder.getStore();
		var severityStore = Ext.create('Ext.data.Store', {
			fields : ['severity', 'label'],
			data : severityData,
			sorters: [{
				property: 'severity',
				direction: 'ASC'
			}],
		});

		this.connectorOrderSeverity.setStore(severityStore);
//		this.criticality.setStore(severityStore);
	},
	
	onConnectorAddButton : function(but, evt) {
		var currentObject = this;
		
		var cid = this.connectorOrder.getValue();
		var name = this.connectorOrder.getRawValue();
		var severity = this.connectorOrderSeverity.getValue();
		var label = this.connectorOrderSeverity.getRawValue();
		var isPresent = false;
		if(cid==null || name == null || severity==null || label==null || cid=="" || name == "" || severity=="" || label=="") {
			Ext.Msg.alert("Error", "Connector Order is Not Selected");
		}
		else {
			var connectorObject = [{ 'cid' : cid, 'name': name, 'severity' : severity, 'label' : label }];

			var connectorOrderGridStore = this.connectorOrderGrid.store;
			connectorOrderGridStore.each(function(rec) {
				var cid = rec.get('cid');
				var name = rec.get('name')
				var severity = rec.get('severity');
				var label = rec.get('label')

				if(cid == currentObject.connectorOrder.getValue()){
					rec.set('name', currentObject.connectorOrder.getRawValue());
					rec.set('severity', currentObject.connectorOrderSeverity.getValue());
					rec.set('label', currentObject.connectorOrderSeverity.getRawValue());
					isPresent = true;
				}
			});

			if(!isPresent) {
				this.connectorOrderGrid.getStore().add(connectorObject);
			}
		}
	},
	
	onClearButton : function(but, evt) {
		this.clearFormData();
	},
	
	updateLogModules : function(logModuleJSON){
		var currentObject = this;
		Ext.Ajax.request({
			timeout: 6000000,
			url : 'secure/logAlarmService/updateLogAlarm',
			jsonData : Ext.JSON.decode(logModuleJSON),
			method : 'PUT',
			success : function(response, opts) {
				var responseJson = Ext.JSON.decode(response.responseText);
				if (responseJson.response.success == true) {
					var message = responseJson.response.message;
					//get newly updated log modules after update
					currentObject.clearFormData();
					currentObject.view.up().up().controller.getLogModules();
					Ext.Msg.alert("Log Alarm Update", message);
				} 
				else {
					Ext.Msg.alert("Error...",responseJson.response.message);
				}
			},
			failure : function(response,opts) {
				var responseJson = Ext.JSON.decode(response.responseText);	
				Ext.Msg.alert("Error...",responseJson.response.message); 
			},
		}, this);
	},
	
	onSaveButton : function(but, evt) {
		var currentObject = this;
		var form = but.up('form').getForm();
		var formJSON = Ext.JSON.encode(form.getValues());
				
		var logModuleJSON = this.createLogModuleJSON(formJSON);
		
		if (form.isValid()) {
//			this.waitWindow = Ext.MessageBox.wait('Please wait, request in process');
			currentObject.updateLogModules(logModuleJSON);
		} else {
			Ext.Msg.alert('Form Validation', "Form is not valid");
		}
	},
	
	getTranslatedConnectorOrder : function() {
		var connectorOrderStore = this.connectorOrder.getStore();
		var connector_order = new Array(connectorOrderStore.data.length);
		for(i = 0; i<connector_order.length ; i++) {
			connector_order[i] = -1;
		}
		
		var connectorOrderGridStore = this.connectorOrderGrid.getStore();
		connectorOrderGridStore.each(function(rec) {
			var cid = rec.get('cid');
			var name = rec.get('name')
			var severity = rec.get('severity');
			var label = rec.get('label')
			
			connector_order[cid - 1] = parseInt(severity);
		});
		return connector_order;
	},
	
	createLogModuleJSON : function(formJSON) {
		var currentObject = this;

		var connector_order = currentObject.getTranslatedConnectorOrder();
		
		var alarmsJSON = this.appAlarmGridPanelController.toJson();
		var artLogAlarmJSON = '';
		artLogAlarmJSON = artLogAlarmJSON.concat("'artLogAlarm': [ ");
		artLogAlarmJSON = artLogAlarmJSON + alarmsJSON + "]";
		/*if(currentObject.view.down('#trace').getValue()) {
			trace = "yes";
		} else {
			trace = "no";
		}*/
		var logModuleJSON = '{';
		/*if(currentObject.moduleId.getValue().length  != 0){
			logModuleJSON = logModuleJSON.concat("'id': '"+  currentObject.moduleId.getValue() + "',");
		}*/
		logModuleJSON = logModuleJSON.concat("'connectorLogPriority': '"+ connector_order + "',");
		logModuleJSON = logModuleJSON.concat("'domainCriticality': "+ currentObject.criticality.getValue() + ",");
//		logModuleJSON = logModuleJSON.concat("'idRangeStartsWith': "+ currentObject.idRangeStartsWith.getValue() + ",");
//		logModuleJSON = logModuleJSON.concat("'boundedContextId': '"+ currentObject.view.down('#bcId').getValue() + "',");
		logModuleJSON = logModuleJSON.concat("'domainPkId': '"+ currentObject.view.down('#domainId').getValue() + "',");
		logModuleJSON = logModuleJSON.concat("'trace': "+ currentObject.view.down('#trace').getValue() + ",");
		logModuleJSON = logModuleJSON.concat(artLogAlarmJSON);
		
		logModuleJSON = logModuleJSON.concat("}")
		
		return logModuleJSON;
	}
});