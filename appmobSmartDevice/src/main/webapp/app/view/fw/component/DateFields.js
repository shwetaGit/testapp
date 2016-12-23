/** Rename this file as CustomDateField 
 * but a file with same name is already there in app 
 * so make sure that app is getting generated without any problem after removing that file n 
 * then do changes for this file as well */

Ext.define('Appmob.view.fw.component.DateFields',{
	extend : 'Ext.form.Date',
	xtype : 'datefields',
	
	/** Gives Value in a way the Application's back end uses */
	getValues : function() {

		if (this.getSubmitValue() != "" && this.getSubmitValue() != null) {
			return this.getSubmitValue();

		} else {
			return undefined;
		}
	},

	/** Sets the value it get from backend after doing modifications on it */
	setValues : function(value) {
		if(value) {
			var dateValue = this.getCustomDate(value);
			this.setValue(dateValue);
		}
	},

	/** Modifies the Value in a way Extjs's date field component accepts  */
	getCustomDate : function(dateString) {

		if(dateString) {
			var dateParts = dateString.split("-");
			if(dateParts.length == 3) {
				var day = dateParts[0];
				var month = dateParts[1]-1;
				var year = dateParts[2];

				return new Date(year, month, day);
			} else {
				return undefined;
			}
		} else {
			return undefined;
		}
	},
 	
	/**Used for report custom ToDate daterange picker value*/
	getCustomToDateValues:function(){
		if (this.getSubmitValue() != "" && this.getSubmitValue() != null) {
			/** Appending time for datefield */
			var value = this.getSubmitValue() + " 23:59:59";
			var timezone = Ext.util.Cookies.get('XA_TID');
			return value + " "+ timezone;
		} else {
			return undefined;
		}
	},

	/**Used for report custom FromDate daterange picker value*/
	getCustomFromDateValues:function(){
		if (this.getSubmitValue() != "" && this.getSubmitValue() != null) {
			/** Appending time for datefield */
			var value = this.getSubmitValue() + " 00:00:00";
			var timezone = Ext.util.Cookies.get('XA_TID');
			return value + " "+ timezone;
		} else {
			return undefined;
		}
	}

});