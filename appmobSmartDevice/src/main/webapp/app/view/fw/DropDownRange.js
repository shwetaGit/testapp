Ext.define('Appmob.view.fw.DropDownRange', {
    extend : 'Ext.panel.Panel',
	xtype : 'dropdownrange',
	items : [ {
		xtype : 'fieldset',
		itemId:'filedSetId',
		defaults : {
			anchor : '100%'
		},
		layout : 'anchor',
		items : [ {
			xtype : 'radiogroup',
			itemId : 'dropDownRadio',
			margin : '0 0 10 0',
			vertical : true,
			columns : 2,
			items : [ {
				boxLabel : 'All',
				name : 'dropdownRadio',
				inputValue : 'range',
				margin : '0 25 0 0',
				checked : true
			}, {
				boxLabel : 'Custom',
				name : 'dropdownRadio',
				inputValue : 'custom'
			} ],
			listeners : {
				change : function(radiogroup, newValue, oldValue, eOpts ){
					
					var dropdownrange = this.up("dropdownrange");
					var dropDownRadio=dropdownrange.down("#dropDownRadio");
	
					if(newValue.dropdownRadio.dropdownRadio=="custom"){
							var fromCombo=dropdownrange.down("#fromCombo");
							var toCombo=dropdownrange.down("#toCombo");
							fromCombo.setValue(dropdownrange.defaultValue);
							toCombo.setValue(dropdownrange.toDefaultValue);
					}
			}
			}
		}, {
			xtype:'combo',
			itemId:'fromCombo',
			emptyText:'Select',
			labelWidth:130,
			queryMode : 'local',
			displayField : "primaryDisplay",
			valueField : "primaryKey"
				
		},{
			xtype:'combo',
			itemId:'toCombo',
			emptyText:'Select',
			labelWidth:130,
			queryMode : 'local',
			displayField : "primaryDisplay",
			valueField : "primaryKey"
		} ]
	}]

});
