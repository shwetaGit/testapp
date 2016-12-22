import { Component } from '@angular/core';
import { SharedDataService } from '../../../services/shareddata.service';
import { Router } from '@angular/router';
import { CountryModel } from './country.model';
import { CountryService } from './country.service';
import { CommonUtils,TextInputConfiguration,DataGridConfiguration,NumberInputConfiguration,SimpleButtonConfiguration } from 'ng_custom_widgets';

@Component({
selector : 'country-component',
templateUrl : 'app/organization/locationmanagement/country/country.template.html'
})
export class CountryComponent {

	countryModel : CountryModel;
	countryName : TextInputConfiguration;
	countryCode1 : TextInputConfiguration;
	countryCode2 : TextInputConfiguration;
	countryFlag : TextInputConfiguration;
	capital : TextInputConfiguration;
	currencyCode : TextInputConfiguration;
	currencyName : TextInputConfiguration;
	currencySymbol : TextInputConfiguration;
	capitalLatitude : NumberInputConfiguration;
	capitalLongitude : NumberInputConfiguration;
	isoNumeric : NumberInputConfiguration;
	save : SimpleButtonConfiguration;
	update : SimpleButtonConfiguration;
	reset : SimpleButtonConfiguration;
	countryGrid : DataGridConfiguration;
	countryGridData : any;

	constructor(  private countryService : CountryService, private commonUtils : CommonUtils, public sharedDataService : SharedDataService, private routerService : Router ) {

		this.countryModel = new CountryModel('','','','','','','','',null,null,null,'','');
		this.countryName = new TextInputConfiguration(false,false,false,true,'0','128','Please enter Name');
		this.countryCode1 = new TextInputConfiguration(false,false,false,false,'0','3','Please enter Code 1');
		this.countryCode2 = new TextInputConfiguration(false,false,false,false,'0','3','Please enter Code 2');
		this.countryFlag = new TextInputConfiguration(false,false,false,false,'0','64','Please enter Flag');
		this.capital = new TextInputConfiguration(false,false,false,false,'0','32','Please enter Capital');
		this.currencyCode = new TextInputConfiguration(false,false,false,false,'0','3','Please enter Currency Code');
		this.currencyName = new TextInputConfiguration(false,false,false,false,'0','128','Please enter Currency Name');
		this.currencySymbol = new TextInputConfiguration(false,false,false,false,'0','32','Please enter Currency Symbol');
		this.capitalLatitude = new NumberInputConfiguration(false,false,false,false,'0','11','Capital Latitude must be between 0 and 11');
		this.capitalLongitude = new NumberInputConfiguration(false,false,false,false,'0','11','Capital Longitude must be between 0 and 11');
		this.isoNumeric = new NumberInputConfiguration(false,false,false,false,'0','1000','ISO Numeric must be between 0 and 1000');
		this.save = new SimpleButtonConfiguration(false,false);
		this.update = new SimpleButtonConfiguration(true,false);
		this.reset = new SimpleButtonConfiguration(false,false);
		
		var countryGridcolumns = [];
		countryGridcolumns.push({text : 'Country Code', dataIndex : 'countryId', hidden: true});
		countryGridcolumns.push({text : 'Name', dataIndex : 'countryName', hidden: false});
		countryGridcolumns.push({text : 'Code 1', dataIndex : 'countryCode1', hidden: false});
		countryGridcolumns.push({text : 'Code 2', dataIndex : 'countryCode2', hidden: false});
		countryGridcolumns.push({text : 'Flag', dataIndex : 'countryFlag', hidden: false});
		countryGridcolumns.push({text : 'Capital', dataIndex : 'capital', hidden: false});
		countryGridcolumns.push({text : 'Currency Code', dataIndex : 'currencyCode', hidden: false});
		countryGridcolumns.push({text : 'Currency Name', dataIndex : 'currencyName', hidden: false});
		countryGridcolumns.push({text : 'Currency Symbol', dataIndex : 'currencySymbol', hidden: false});
		countryGridcolumns.push({text : 'Capital Latitude', dataIndex : 'capitalLatitude', hidden: false});
		countryGridcolumns.push({text : 'Capital Longitude', dataIndex : 'capitalLongitude', hidden: false});
		countryGridcolumns.push({text : 'ISO Numeric', dataIndex : 'isoNumeric', hidden: false});
		countryGridcolumns.push({text : 'createdBy', dataIndex : 'createdBy', hidden: true});
		countryGridcolumns.push({text : 'createdDate', dataIndex : 'createdDate', hidden: true});
		countryGridcolumns.push({text : 'updatedBy', dataIndex : 'updatedBy', hidden: true});
		countryGridcolumns.push({text : 'updatedDate', dataIndex : 'updatedDate', hidden: true});
		countryGridcolumns.push({text : 'versionId', dataIndex : 'versionId', hidden: true});
		countryGridcolumns.push({text : 'activeStatus', dataIndex : 'activeStatus', hidden: true});
		countryGridcolumns.push({text : 'txnAccessCode', dataIndex : 'txnAccessCode', hidden: true});
		this.countryGrid = new DataGridConfiguration(countryGridcolumns);
		this.countryGridData = [];

	}

	onSaveClick() {

		var requestData = {};

		var componentArray = [ this.countryName,this.countryCode1,this.countryCode2,this.countryFlag,this.capital,this.currencyCode,this.currencyName,this.currencySymbol,this.capitalLatitude,this.capitalLongitude,this.isoNumeric ];

		if(this.commonUtils.validateAndShowErrorMessages(componentArray)) {
			return;
		}

		requestData.countryName = this.countryModel.countryName;
		requestData.countryCode1 = this.countryModel.countryCode1;
		requestData.countryCode2 = this.countryModel.countryCode2;
		requestData.countryFlag = this.countryModel.countryFlag;
		requestData.capital = this.countryModel.capital;
		requestData.currencyCode = this.countryModel.currencyCode;
		requestData.currencyName = this.countryModel.currencyName;
		requestData.currencySymbol = this.countryModel.currencySymbol;
		requestData.capitalLatitude = this.countryModel.capitalLatitude;
		requestData.capitalLongitude = this.countryModel.capitalLongitude;
		requestData.isoNumeric = this.countryModel.isoNumeric;

		this.countryService.onSaveClick_callCountryService_saveOperation(this,'onSaveClick_callCountryService_saveOperationComplete',requestData);

		this.countryModel.countryId = null;
		this.countryModel.countryName = null;
		this.countryModel.countryCode1 = null;
		this.countryModel.countryCode2 = null;
		this.countryModel.countryFlag = null;
		this.countryModel.capital = null;
		this.countryModel.currencyCode = null;
		this.countryModel.currencyName = null;
		this.countryModel.currencySymbol = null;
		this.countryModel.capitalLatitude = null;
		this.countryModel.capitalLongitude = null;
		this.countryModel.isoNumeric = null;
		this.countryModel.versionId = null;
	}

	onSaveClick_callCountryService_saveOperationComplete(responseData) {

		if(responseData.response.success) {
			toastr.info(responseData.response.message);
		} else {
			toastr.error(responseData.response.message);
		}
	}


	onUpdateClick() {

		var requestData = {};

		var componentArray = [ this.countryName,this.countryCode1,this.countryCode2,this.countryFlag,this.capital,this.currencyCode,this.currencyName,this.currencySymbol,this.capitalLatitude,this.capitalLongitude,this.isoNumeric ];

		if(this.commonUtils.validateAndShowErrorMessages(componentArray)) {
			return;
		}

		requestData.countryId = this.countryModel.countryId;
		requestData.countryName = this.countryModel.countryName;
		requestData.countryCode1 = this.countryModel.countryCode1;
		requestData.countryCode2 = this.countryModel.countryCode2;
		requestData.countryFlag = this.countryModel.countryFlag;
		requestData.capital = this.countryModel.capital;
		requestData.currencyCode = this.countryModel.currencyCode;
		requestData.currencyName = this.countryModel.currencyName;
		requestData.currencySymbol = this.countryModel.currencySymbol;
		requestData.capitalLatitude = this.countryModel.capitalLatitude;
		requestData.capitalLongitude = this.countryModel.capitalLongitude;
		requestData.isoNumeric = this.countryModel.isoNumeric;
		requestData.versionId = this.countryModel.versionId;

		this.countryService.onUpdateClick_callCountryService_updateOperation(this,'onUpdateClick_callCountryService_updateOperationComplete',requestData);

		this.countryModel.countryId = null;
		this.countryModel.countryName = null;
		this.countryModel.countryCode1 = null;
		this.countryModel.countryCode2 = null;
		this.countryModel.countryFlag = null;
		this.countryModel.capital = null;
		this.countryModel.currencyCode = null;
		this.countryModel.currencyName = null;
		this.countryModel.currencySymbol = null;
		this.countryModel.capitalLatitude = null;
		this.countryModel.capitalLongitude = null;
		this.countryModel.isoNumeric = null;
		this.countryModel.versionId = null;
		this.save.hidden = false;
		this.update.hidden = true;
	}

	onUpdateClick_callCountryService_updateOperationComplete(responseData) {

		if(responseData.response.success) {
			toastr.info(responseData.response.message);
		} else {
			toastr.error(responseData.response.message);
		}
	}


	onResetClick() {

		this.countryModel.countryId = null;
		this.countryModel.countryName = null;
		this.countryModel.countryCode1 = null;
		this.countryModel.countryCode2 = null;
		this.countryModel.countryFlag = null;
		this.countryModel.capital = null;
		this.countryModel.currencyCode = null;
		this.countryModel.currencyName = null;
		this.countryModel.currencySymbol = null;
		this.countryModel.capitalLatitude = null;
		this.countryModel.capitalLongitude = null;
		this.countryModel.isoNumeric = null;
		this.countryModel.versionId = null;
		this.save.hidden = false;
		this.update.hidden = true;
	}

	onCountryGridRowClick(data) {

		var requestData = {};
		requestData.findKey = data.countryId;

		this.countryService.onCountryGridRowClick_callCountry_findByIdOperation(this,'onCountryGridRowClick_callCountry_findByIdOperationComplete',requestData);

	}

	onCountryGridRowClick_callCountry_findByIdOperationComplete(responseData) {

		if(responseData.response.success) {
		let data = responseData.response.data;

		this.countryModel.countryId = data.countryId;
		this.countryModel.countryName = data.countryName;
		this.countryModel.countryCode1 = data.countryCode1;
		this.countryModel.countryCode2 = data.countryCode2;
		this.countryModel.countryFlag = data.countryFlag;
		this.countryModel.capital = data.capital;
		this.countryModel.currencyCode = data.currencyCode;
		this.countryModel.currencyName = data.currencyName;
		this.countryModel.currencySymbol = data.currencySymbol;
		this.countryModel.capitalLatitude = data.capitalLatitude;
		this.countryModel.capitalLongitude = data.capitalLongitude;
		this.countryModel.isoNumeric = data.isoNumeric;
		this.countryModel.versionId = data.versionId;

		this.save.hidden = true;
		this.update.hidden = false;
		} else {
			toastr.error(responseData.response.message);
		}
	}


}