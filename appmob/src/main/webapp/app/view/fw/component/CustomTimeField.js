/** For giving custom behavior to our TimeField component this class has been added */

Ext.define('Appmob.view.fw.component.CustomTimeField', {
	extend :'Ext.form.field.Time',
	xtype : 'customtimefield',

	getCmpValue : function() {
		var cmpValue = undefined;
		 
		var value = this.getValue();
		if(value!=undefined && value!=null) {
			cmpValue = value.getHours() +":"+ value.getMinutes() +":"+ value.getSeconds();
		}
		
		return cmpValue;
	},
	
	setCmpValue : function(valueToSet) {
//		setValue: function (v) {
//	        // Store MUST be created for parent setValue to function.
//	        this.getPicker();
//
//	        if (Ext.isDate(v)) {
//	            v = this.getInitDate(v.getHours(), v.getMinutes(), v.getSeconds());
//	        }
//
//	        return this.callParent([v]);
//	    },
	}

});