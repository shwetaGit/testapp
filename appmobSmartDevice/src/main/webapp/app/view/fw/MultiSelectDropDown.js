Ext.define('Appmob.view.fw.MultiSelectDropDown', {
    extend : 'Ext.panel.Panel',
  //  requires : ['Appmob.view.fw.MultiSelectDropDownController'],
	xtype : 'multiselectdd',
	//controller:'MultiSelectDropDownController',
	items : [ {
		xtype : 'fieldset',
		title : 'Multi-Select Criteria',
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
				boxLabel : 'Range',
				name : 'dropdownRadio',
				inputValue : 'range',
				margin : '0 25 0 0',
				checked : true
			}, {
				boxLabel : 'Custom',
				name : 'dropdownRadio',
				inputValue : 'custom'
			} ]/*,
			listeners : {
				change : 'afterRadioChange'
			}*/
		}, {
			xtype:'tagfield',
			itemId:'multSelectCombo',
			labelWidth:170,
			queryMode : 'local',
			displayField : "primaryDisplay",
			valueField : "primaryKey",
		 //   multiSelect: true,
		} ]
	}]
});
