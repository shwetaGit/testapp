/**
 * 
 */
Ext.define('Appmob.view.logalarm.mainscreen.AppAlarmPanelController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.appAlarmPanelController',
	
	alarmIndex : null,
	alarmId : null,
	severity : null,
	connectorOrder : null,
	connectorOrderSeverity : null,
	connectorOrderGrid : null,
	
	idCounter : null,
	message : null,
	diagnose : null,
	solution : null,
	
	init : function(){
		var currentObject = this;
		currentObject.alarmIndex = currentObject.view.down('#alarmIndex');
		currentObject.alarmId = currentObject.view.down('#alarmId');
		currentObject.severity = currentObject.view.down('#severity');
		currentObject.connectorOrder = currentObject.view.down('#connectorOrder');
		currentObject.connectorOrderSeverity = currentObject.view.down('#connectorOrderSeverity');
		currentObject.connectorOrderGrid = currentObject.view.down('#connectorOrderGrid');
		
		currentObject.message = currentObject.view.down('#message');
		currentObject.diagnose = currentObject.view.down('#diagnose');
		currentObject.solution = currentObject.view.down('#solution');
		currentObject.idCounter = currentObject.view.down('#idCounter');
	},
	
	setFormData : function(alarmData) {
		var currentObject = this;
		var appAlarmGridPanelController = this.view.up().down('#appAlarmGridPanel').getController();
		appAlarmGridPanelController.clearGrid();
		/*Ext.Ajax.request({
			timeout: 6000000,
			url : 'secure/buzzorapp/awslogalarm/getGridData',
			method : 'POST',
			jsonData : domainData.mappingId,
			currentObject : currentObject,
			success : function(response, opts){
				var responseJson = Ext.JSON.decode(response.responseText);
				if (responseJson.response.success == 'true') {
					var logAlarmData = responseJson.response.data;*/
					currentObject.view.down('#addButton').setText('Update Alarm');
					if(alarmData != null && alarmData.length>0) {
						for(var i = 0; i<alarmData.length; i++) {
							var appAlarm = alarmData[i];
//							currentObject.renderFormData(appAlarm);
							var appAlarmGridPanelController = currentObject.view.up().down('#appAlarmGridPanel').getController();

							appAlarmGridPanelController.addDataToGrid({
								"alarmIndex" : appAlarm.alarmPkId,
								"alarmContext" : appAlarm.alarmContext,
								"alarmId": appAlarm.alarmId,
								"severity": appAlarm.severity,
								"connectorOrder": appAlarm.connectorOrder/*.substring(1,appAlarm.connectorOrder.length -1)*/,
								"message": appAlarm.message,
								"diagnose": appAlarm.diagnose,
								"solution": appAlarm.solution,
								"architectureLayer": appAlarm.appLayer,
								"alarmStatus": appAlarm.status,
								"exception": appAlarm.exception,
								"eventAction": appAlarm.eventAction
							});
						}
					}
				/*} else {
					Ext.Msg.alert("Error.", responseJson.response.errormessage);
				}
			},
			failure : function(response, opts){
				var responseJson = Ext.JSON.decode(response.responseText);
				Ext.Msg.alert("Error...", responseJson.response.errormessage);
			}
		});*/
		
	},
	
	renderFormData : function(appAlarm) {
		var currentObject = this;
		currentObject.clearConnectorOrderGrid();
		currentObject.alarmIndex.setValue(appAlarm.id);
		currentObject.alarmId.setValue(appAlarm.alarmId);
		currentObject.severity.select(currentObject.severity.getStore().getAt(appAlarm.severity));
		currentObject.view.down('#architectureLayer').getStore().each(function(rec) {
			if(rec.get('layerId') == appAlarm.architectureLayer) {
				currentObject.view.down('#architectureLayer').setValue(rec.get('layerId'));
			}
		});
		currentObject.view.down('#eventAction').getStore().each(function(rec) {
			if(rec.get('eventActionId') == appAlarm.eventAction) {
				currentObject.view.down('#eventAction').setValue(rec.get('eventActionId'));
			}
		});
		currentObject.view.down('#alarmStatus').getStore().each(function(rec) {
			if(rec.get('statusId') == appAlarm.alarmStatus) {
				currentObject.view.down('#alarmStatus').setValue(rec.get('statusId'));
			}
		});
		currentObject.view.down('#rootExceptionLabel').setHidden(true);
		currentObject.view.down('#exception').getStore().each(function(rec) {
			if(rec.get('exceptionId') == appAlarm.exception) {
				currentObject.view.down('#exception').setValue(rec.get('exceptionId'));
				currentObject.view.down('#exceptionLabel').setValue(rec.get('exception'));
				if(rec.get('rootException') != "null") {
					currentObject.view.down('#rootExceptionLabel').setHidden(false);
					currentObject.view.down('#rootExceptionLabel').setValue(rec.get('rootException'));
				}
			}
		});
//		currentObject.view.down('#architectureLayer').select(appAlarm.architectureLayer);
//		currentObject.view.down('#eventAction').select(appAlarm.eventAction);
//		currentObject.view.down('#alarmStatus').select(appAlarm.alarmStatus);
//		currentObject.view.down('#exception').select(appAlarm.exception);
		if(currentObject.view.down('#exception').getSelectedRecord() != null && currentObject.view.down('#exception').getSelectedRecord().data.systemDefined == true) {
			currentObject.view.down('#exception').setReadOnly(true);
		} else {
			currentObject.view.down('#exception').setReadOnly(false);
		}
		var connector_orders = appAlarm.connectorOrder.split(',');
		var contextId = appAlarm.alarmId.substring((appAlarm.alarmId.length-6),(appAlarm.alarmId.length-3));
		currentObject.view.down('#contextId').setValue(contextId);
		currentObject.view.down('#contextId').setReadOnly(true);
		this.connectorOrderGrid.getStore()
		for(var j = 0;j<connector_orders.length;j++) {
			var connector_order = connector_orders[j];
			if(connector_order != -1) {
				currentObject.connectorOrder.select(currentObject.connectorOrder.getStore().getAt(j));
				currentObject.connectorOrderSeverity.select(currentObject.connectorOrderSeverity.getStore().getAt(connector_order));
				this.onConnectorAddButton();
			}
		}
	
		currentObject.message.setValue(this.getFormattedMessage(appAlarm.message));
		currentObject.diagnose.setValue(appAlarm.diagnose);
		currentObject.solution.setValue(appAlarm.solution);
	},
	
	getFormattedMessage : function(message) {
		message = message.replace(/%s/ig, "<string>");
		message = message.replace(/%c/ig, "<character>");
		message = message.replace(/%d/ig, "<decimal>");
		message = message.replace(/%f/ig, "<float>");
		return message;
	},
	
	getFormatSpecifiedMessage : function(message) {
		message = message.replace(/<string>/ig, "%s");
		message = message.replace(/<character>/ig, "%c");
		message = message.replace(/<decimal>/ig,"%d");
		message = message.replace(/<float>/ig,"%f");
		return message;
	},
	
	clearConnectorOrderGrid : function() {
		this.connectorOrderGrid.store.removeAll();
	},
	
	onClearButton : function() {
		var appAlarmForm = this.view.getForm();
		appAlarmForm.reset();
		this.clearConnectorOrderGrid();
	},
	
	onResetButton : function(but,evt){
		this.onClearButton();
		var appAlarmGridPanelController = this.view.up().down('#appAlarmGridPanel').getController();
//		var alarm_id = appAlarmGridPanelController.getAlarmId();
//		this.alarmId.setValue(alarm_id);
		this.view.down('#addButton').setText('Add Alarm');
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
		this.severity.setStore(severityStore);
	},
	setArchitectureLayers : function(architectureLayers) {
		var store = this.view.down('#architectureLayer');
		var layerStore = Ext.create('Ext.data.Store', {
					fields : ['layerId', 'layer'],
					data : architectureLayers,
					sorters: [{
				         property: 'layerId',
				         direction: 'ASC'
				     }],
				});

		store.setStore(layerStore);
	},
	setAlarmStatus: function(alarmStatus) {
		var store = this.view.down('#alarmStatus');
		var statusStore = Ext.create('Ext.data.Store', {
					fields : ['statusId', 'status'],
					data : alarmStatus,
					sorters: [{
				         property: 'status',
				         direction: 'ASC'
				     }],
				});

		store.setStore(statusStore);
	},
	setExceptions : function(exceptions) {
		/*var json = {};
		json.exceptionId = 0;
		json.exception = "System defined";
		json.systemDefined = true;
		exceptions.push(json);*/
		var store = this.view.down('#exception');
		var exceptionStore = Ext.create('Ext.data.Store', {
					fields : ['exceptionId', 'exception', 'systemDefined', 'exceptionName'],
					data : exceptions,
					sorters: [{
				         property: 'exceptionId',
				         direction: 'ASC'
				     }],
				});

		store.setStore(exceptionStore);
	},
	setEventActions: function(eventActions) {
		var store = this.view.down('#eventAction');
		var actionStore = Ext.create('Ext.data.Store', {
					fields : ['eventActionId', 'eventAction'],
					data : eventActions,
					sorters: [{
				         property: 'eventActionId',
				         direction: 'ASC'
				     }],
				});

		store.setStore(actionStore);
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
	
	onAddAppAlarm : function(but, evt){
		var form = but.up('form').getForm();
		var formData = form.getValues();
		this.generateAlarmId();
		formData.alarmId = this.alarmId.getValue();
		
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
		
		formData.connectorOrder = connector_order
		formData.message = this.getFormatSpecifiedMessage(formData.message);
		var appAlarmGridPanelController = this.view.up().down('#appAlarmGridPanel').getController();
		appAlarmGridPanelController.addDataToGrid(formData);
		but.setText('Update Alarm');
	},
	
	generateAlarmId : function() {
		alarmId = "";
		tree = this.view.up().up().up().down('#logModuleTree');
		alarmId = alarmId.concat(tree.getSelection()[0].parentNode.data.contextPrefix);
		alarmId = alarmId.concat(tree.getSelection()[0].data.domainPrefix);
		alarmId = alarmId.concat(this.view.down('#architectureLayer').getValue());
		alarmId = alarmId.concat(this.view.down('#severity').getValue());
		alarmId = alarmId.concat(this.view.down('#eventAction').getValue());
		alarmId = alarmId.concat(this.view.down('#contextId').getValue());
		alarmId = alarmId.concat(this.view.down('#alarmStatus').getValue());
		this.view.down('#alarmId').setValue(alarmId);
	}
});