/**
 * 
 */
Ext.define('Appmob.view.logalarm.mainscreen.AppAlarmGridPanelController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.appAlarmGridPanelController',
	dataGrid : null,
	init : function() {
		this.dataGrid = this.view.getStore();
	},
	
	clearGrid : function() {
		this.view.store.removeAll();
	},
	
	onRowSelection : function(selModel, record, index, options){
    },
    
    /*getAlarmId : function() {
    	var currentObject = this;
    	var newAlarmId = 0;
    	var isRecFound = false;

    	currentObject.dataGrid.each(function(rec) {

    		isRecFound = true;
    		var alarmId = rec.get('alarmId');

    		if(alarmId > newAlarmId) {
    			newAlarmId = alarmId;
    		}
    	});
    	if(!isRecFound) {
    		newAlarmId = this.view.up().down('#idRangeStartsWith').getValue();
    	}
    	newAlarmId = newAlarmId + 1;
    	return newAlarmId;
    },*/
	
	addDataToGrid : function(formData){
		var currentObject = this;
		var isPresent = false;
		var duplicateEntryFound = false;
		currentObject.dataGrid.each(function(rec) {
			if(rec.get('alarmIndex') != formData.alarmIndex && rec.get('alarmId') == formData.alarmId) {
				Ext.Msg.alert('Error', "Duplicate entries found for alarm id. Alarm/s cannot be updated.");
				duplicateEntryFound = true;
			}
		});
		if(duplicateEntryFound) {
			return;
		}
		if(!duplicateEntryFound) {
			currentObject.dataGrid.each(function(rec) {
				if(rec.get('alarmIndex') == formData.alarmIndex)
				{
					rec.set('alarmId', formData.alarmId);
					rec.set('alarmContext', formData.contextId);
					rec.set('severity', formData.severity);
					rec.set('connectorOrder', formData.connectorOrder);
					rec.set('message', formData.message);
					rec.set('diagnose', formData.diagnose);
					rec.set('solution', formData.solution);
					rec.set('architectureLayer', formData.architectureLayer);
					rec.set('alarmStatus', formData.alarmStatus);
					rec.set('exception', formData.exception);
					rec.set('eventAction', formData.eventAction);
					isPresent = true;
				}
			});

			if(!isPresent) {
				this.dataGrid.add(formData);
			}
		}
	},
	
	toJson : function() {
		var appAlarmsJSON = '';
		var appAlarmsGridStore = this.view.getStore();
		
		appAlarmsGridStore.each(function(rec) {
			var alarmPkId = rec.get('alarmIndex');
			var alarmId = rec.get('alarmId');
			var alarmContext = rec.get('alarmContext');
			var severity = rec.get('severity');
			var connectorOrder = rec.get('connectorOrder');
			var message = rec.get('message').replace('\n\t\t\t\t','');
			var diagnose = rec.get('diagnose').replace('\n\t\t\t\t','');
			var solution = rec.get('solution').replace('\n\t\t\t\t','');
			var status = rec.get('alarmStatus');
			var appLayer = rec.get('architectureLayer');
			var eventAction = rec.get('eventAction');
			var exception = rec.get('exception');
			var alarmJSON = '{';
			if(alarmPkId.length  != 0){
				alarmJSON = alarmJSON.concat("'alarmPkId': '"+  alarmPkId + "',");
			}
			alarmJSON = alarmJSON.concat("'alarmId': '"+ alarmId + "',");
			alarmJSON = alarmJSON.concat("'alarmContext': "+ alarmContext + ",");
			alarmJSON = alarmJSON.concat("'severity': "+ severity + ",");
			alarmJSON = alarmJSON.concat("'connectorOrder': '"+ connectorOrder + "',");
			alarmJSON = alarmJSON.concat("'message': '"+ message + "',");
			alarmJSON = alarmJSON.concat("'diagnose': '"+ diagnose + "',");
			alarmJSON = alarmJSON.concat("'status': '"+ status + "',");
			alarmJSON = alarmJSON.concat("'appLayer': '"+ appLayer + "',");
			alarmJSON = alarmJSON.concat("'eventAction': '"+ eventAction + "',");
			alarmJSON = alarmJSON.concat("'exception': '"+ exception + "',");
			alarmJSON = alarmJSON.concat("'solution': '"+ solution + "'");
			alarmJSON = alarmJSON.concat("},")

			appAlarmsJSON = appAlarmsJSON.concat(alarmJSON);
		});
		appAlarmsJSON = appAlarmsJSON.substring(0,appAlarmsJSON.lastIndexOf(','));
		return appAlarmsJSON;
	}
});
