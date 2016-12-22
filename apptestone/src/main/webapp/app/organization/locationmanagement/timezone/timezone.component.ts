import { Component } from '@angular/core';
import { SharedDataService } from '../../../services/shareddata.service';
import { Router } from '@angular/router';
import { TimezoneModel } from './timezone.model';
import { TimezoneService } from './timezone.service';
import { CommonUtils,TextInputConfiguration,DataGridConfiguration,NumberInputConfiguration,SimpleButtonConfiguration } from 'ng_custom_widgets';

@Component({
selector : 'timezone-component',
templateUrl : 'app/organization/locationmanagement/timezone/timezone.template.html'
})
export class TimezoneComponent {

	timezoneModel : TimezoneModel;
	utcdifference : NumberInputConfiguration;
	gmtLabel : TextInputConfiguration;
	timeZoneLabel : TextInputConfiguration;
	country : TextInputConfiguration;
	cities : TextInputConfiguration;
	save : SimpleButtonConfiguration;
	update : SimpleButtonConfiguration;
	reset : SimpleButtonConfiguration;
	timezoneGrid : DataGridConfiguration;
	timezoneGridData : any;

	constructor(  private timezoneService : TimezoneService, private commonUtils : CommonUtils, public sharedDataService : SharedDataService, private routerService : Router ) {

		this.timezoneModel = new TimezoneModel(null,'','','','','','');
		this.utcdifference = new NumberInputConfiguration(false,false,false,true,'0','11','UTC Difference must be between 0 and 11');
		this.gmtLabel = new TextInputConfiguration(false,false,false,false,'0','256','Please enter GMT');
		this.timeZoneLabel = new TextInputConfiguration(false,false,false,false,'0','256','Please enter Time Zone');
		this.country = new TextInputConfiguration(false,false,false,false,'0','256','Please enter Country');
		this.cities = new TextInputConfiguration(false,false,false,false,'0','256','Please enter City');
		this.save = new SimpleButtonConfiguration(false,false);
		this.update = new SimpleButtonConfiguration(true,false);
		this.reset = new SimpleButtonConfiguration(false,false);
		
		var timezoneGridcolumns = [];
		timezoneGridcolumns.push({text : 'Time Zone Id', dataIndex : 'timeZoneId', hidden: true});
		timezoneGridcolumns.push({text : 'UTC Difference', dataIndex : 'utcdifference', hidden: false});
		timezoneGridcolumns.push({text : 'GMT', dataIndex : 'gmtLabel', hidden: false});
		timezoneGridcolumns.push({text : 'Time Zone', dataIndex : 'timeZoneLabel', hidden: false});
		timezoneGridcolumns.push({text : 'Country', dataIndex : 'country', hidden: false});
		timezoneGridcolumns.push({text : 'City', dataIndex : 'cities', hidden: false});
		timezoneGridcolumns.push({text : 'createdBy', dataIndex : 'createdBy', hidden: true});
		timezoneGridcolumns.push({text : 'createdDate', dataIndex : 'createdDate', hidden: true});
		timezoneGridcolumns.push({text : 'updatedBy', dataIndex : 'updatedBy', hidden: true});
		timezoneGridcolumns.push({text : 'updatedDate', dataIndex : 'updatedDate', hidden: true});
		timezoneGridcolumns.push({text : 'versionId', dataIndex : 'versionId', hidden: true});
		timezoneGridcolumns.push({text : 'activeStatus', dataIndex : 'activeStatus', hidden: true});
		timezoneGridcolumns.push({text : 'txnAccessCode', dataIndex : 'txnAccessCode', hidden: true});
		this.timezoneGrid = new DataGridConfiguration(timezoneGridcolumns);
		this.timezoneGridData = [];

	}

	onSaveClick() {

		var requestData = {};

		var componentArray = [ this.utcdifference,this.gmtLabel,this.timeZoneLabel,this.country,this.cities ];

		if(this.commonUtils.validateAndShowErrorMessages(componentArray)) {
			return;
		}

		requestData.utcdifference = this.timezoneModel.utcdifference;
		requestData.gmtLabel = this.timezoneModel.gmtLabel;
		requestData.timeZoneLabel = this.timezoneModel.timeZoneLabel;
		requestData.country = this.timezoneModel.country;
		requestData.cities = this.timezoneModel.cities;

		this.timezoneService.onSaveClick_callTimezoneService_saveOperation(this,'onSaveClick_callTimezoneService_saveOperationComplete',requestData);

		this.timezoneModel.timeZoneId = null;
		this.timezoneModel.utcdifference = null;
		this.timezoneModel.gmtLabel = null;
		this.timezoneModel.timeZoneLabel = null;
		this.timezoneModel.country = null;
		this.timezoneModel.cities = null;
		this.timezoneModel.versionId = null;
	}

	onSaveClick_callTimezoneService_saveOperationComplete(responseData) {

		if(responseData.response.success) {
			toastr.info(responseData.response.message);
		} else {
			toastr.error(responseData.response.message);
		}
	}


	onUpdateClick() {

		var requestData = {};

		var componentArray = [ this.utcdifference,this.gmtLabel,this.timeZoneLabel,this.country,this.cities ];

		if(this.commonUtils.validateAndShowErrorMessages(componentArray)) {
			return;
		}

		requestData.timeZoneId = this.timezoneModel.timeZoneId;
		requestData.utcdifference = this.timezoneModel.utcdifference;
		requestData.gmtLabel = this.timezoneModel.gmtLabel;
		requestData.timeZoneLabel = this.timezoneModel.timeZoneLabel;
		requestData.country = this.timezoneModel.country;
		requestData.cities = this.timezoneModel.cities;
		requestData.versionId = this.timezoneModel.versionId;

		this.timezoneService.onUpdateClick_callTimezoneService_updateOperation(this,'onUpdateClick_callTimezoneService_updateOperationComplete',requestData);

		this.timezoneModel.timeZoneId = null;
		this.timezoneModel.utcdifference = null;
		this.timezoneModel.gmtLabel = null;
		this.timezoneModel.timeZoneLabel = null;
		this.timezoneModel.country = null;
		this.timezoneModel.cities = null;
		this.timezoneModel.versionId = null;
		this.save.hidden = false;
		this.update.hidden = true;
	}

	onUpdateClick_callTimezoneService_updateOperationComplete(responseData) {

		if(responseData.response.success) {
			toastr.info(responseData.response.message);
		} else {
			toastr.error(responseData.response.message);
		}
	}


	onResetClick() {

		this.timezoneModel.timeZoneId = null;
		this.timezoneModel.utcdifference = null;
		this.timezoneModel.gmtLabel = null;
		this.timezoneModel.timeZoneLabel = null;
		this.timezoneModel.country = null;
		this.timezoneModel.cities = null;
		this.timezoneModel.versionId = null;
		this.save.hidden = false;
		this.update.hidden = true;
	}

	onTimezoneGridRowClick(data) {

		var requestData = {};
		requestData.findKey = data.timeZoneId;

		this.timezoneService.onTimezoneGridRowClick_callTimezone_findByIdOperation(this,'onTimezoneGridRowClick_callTimezone_findByIdOperationComplete',requestData);

	}

	onTimezoneGridRowClick_callTimezone_findByIdOperationComplete(responseData) {

		if(responseData.response.success) {
		let data = responseData.response.data;

		this.timezoneModel.timeZoneId = data.timeZoneId;
		this.timezoneModel.utcdifference = data.utcdifference;
		this.timezoneModel.gmtLabel = data.gmtLabel;
		this.timezoneModel.timeZoneLabel = data.timeZoneLabel;
		this.timezoneModel.country = data.country;
		this.timezoneModel.cities = data.cities;
		this.timezoneModel.versionId = data.versionId;

		this.save.hidden = true;
		this.update.hidden = false;
		} else {
			toastr.error(responseData.response.message);
		}
	}


}