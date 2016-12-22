import { Component } from '@angular/core';
import { SharedDataService } from '../../../services/shareddata.service';
import { Router } from '@angular/router';
import { CityModel } from './city.model';
import { CityService } from './city.service';
import { CommonUtils,TextInputConfiguration,DataGridConfiguration,NumberInputConfiguration,SimpleButtonConfiguration,SelectInputConfiguration,TextAreaInputConfiguration } from 'ng_custom_widgets';

@Component({
selector : 'city-component',
templateUrl : 'app/organization/locationmanagement/city/city.template.html'
})
export class CityComponent {

	cityModel : CityModel;
	countryId : SelectInputConfiguration;
	countryIdData : any;
	stateId : SelectInputConfiguration;
	stateIdData : any;
	cityName : TextInputConfiguration;
	cityCodeChar2 : TextInputConfiguration;
	cityCode : NumberInputConfiguration;
	cityDescription : TextAreaInputConfiguration;
	cityFlag : TextInputConfiguration;
	cityLatitude : NumberInputConfiguration;
	cityLongitude : NumberInputConfiguration;
	save : SimpleButtonConfiguration;
	update : SimpleButtonConfiguration;
	reset : SimpleButtonConfiguration;
	cityGrid : DataGridConfiguration;
	cityGridData : any;

	constructor(  private cityService : CityService, private commonUtils : CommonUtils, public sharedDataService : SharedDataService, private routerService : Router ) {

		this.cityModel = new CityModel(null,null,'','',null,'','',null,null,'','');
				this.countryIdData = [];
		this.countryId = new SelectInputConfiguration(false,false,true,'Please select Country','primaryDisplay','primaryKey');
				this.stateIdData = [];
		this.stateId = new SelectInputConfiguration(false,false,true,'Please select State','primaryDisplay','primaryKey');
		this.cityName = new TextInputConfiguration(false,false,false,true,'0','256','Please enter City Name');
		this.cityCodeChar2 = new TextInputConfiguration(false,false,false,true,'0','32','Please enter City Code');
		this.cityCode = new NumberInputConfiguration(false,false,false,true,'0','3','City Code1 must be between 0 and 3');
		this.cityDescription = new TextAreaInputConfiguration(false,false,false,false,'','','Please enter City Description');
		this.cityFlag = new TextInputConfiguration(false,false,false,false,'0','128','Please enter Flag');
		this.cityLatitude = new NumberInputConfiguration(false,false,false,false,'0','11','City Latitude must be between 0 and 11');
		this.cityLongitude = new NumberInputConfiguration(false,false,false,false,'0','11','City Longitude must be between 0 and 11');
		this.save = new SimpleButtonConfiguration(false,false);
		this.update = new SimpleButtonConfiguration(true,false);
		this.reset = new SimpleButtonConfiguration(false,false);
		
		var cityGridcolumns = [];
		cityGridcolumns.push({text : 'City Id', dataIndex : 'cityId', hidden: true});
		cityGridcolumns.push({text : 'Country', dataIndex : 'countryId', hidden: false});
		cityGridcolumns.push({text : 'State', dataIndex : 'stateId', hidden: false});
		cityGridcolumns.push({text : 'City Name', dataIndex : 'cityName', hidden: false});
		cityGridcolumns.push({text : 'City Code', dataIndex : 'cityCodeChar2', hidden: false});
		cityGridcolumns.push({text : 'City Code1', dataIndex : 'cityCode', hidden: false});
		cityGridcolumns.push({text : 'City Description', dataIndex : 'cityDescription', hidden: false});
		cityGridcolumns.push({text : 'Flag', dataIndex : 'cityFlag', hidden: false});
		cityGridcolumns.push({text : 'City Latitude', dataIndex : 'cityLatitude', hidden: false});
		cityGridcolumns.push({text : 'City Longitude', dataIndex : 'cityLongitude', hidden: false});
		cityGridcolumns.push({text : 'createdBy', dataIndex : 'createdBy', hidden: true});
		cityGridcolumns.push({text : 'createdDate', dataIndex : 'createdDate', hidden: true});
		cityGridcolumns.push({text : 'updatedBy', dataIndex : 'updatedBy', hidden: true});
		cityGridcolumns.push({text : 'updatedDate', dataIndex : 'updatedDate', hidden: true});
		cityGridcolumns.push({text : 'versionId', dataIndex : 'versionId', hidden: true});
		cityGridcolumns.push({text : 'activeStatus', dataIndex : 'activeStatus', hidden: true});
		cityGridcolumns.push({text : 'txnAccessCode', dataIndex : 'txnAccessCode', hidden: true});
		this.cityGrid = new DataGridConfiguration(cityGridcolumns);
		this.cityGridData = [];

	}

	onCountryIdSelect(data) {

		var requestData = {};
		requestData.findKey = this.cityModel.countryId;

		this.cityService.onCountryIdSelect_callStateService_findByCountryIdOperation(this,'onCountryIdSelect_callStateService_findByCountryIdOperationComplete',requestData);

	}

	onCountryIdSelect_callStateService_findByCountryIdOperationComplete(responseData) {

		if(responseData.response.success) {
		let data = responseData.response.data;

		this.stateIdData = responseData;
		} else {
			toastr.error(responseData.response.message);
		}
	}


	onSaveClick() {

		var requestData = {};

		var componentArray = [ this.countryId,this.stateId,this.cityName,this.cityCodeChar2,this.cityCode,this.cityDescription,this.cityFlag,this.cityLatitude,this.cityLongitude ];

		if(this.commonUtils.validateAndShowErrorMessages(componentArray)) {
			return;
		}

		requestData.countryId = this.cityModel.countryId;
		requestData.stateId = this.cityModel.stateId;
		requestData.cityName = this.cityModel.cityName;
		requestData.cityCodeChar2 = this.cityModel.cityCodeChar2;
		requestData.cityCode = this.cityModel.cityCode;
		requestData.cityDescription = this.cityModel.cityDescription;
		requestData.cityFlag = this.cityModel.cityFlag;
		requestData.cityLatitude = this.cityModel.cityLatitude;
		requestData.cityLongitude = this.cityModel.cityLongitude;

		this.cityService.onSaveClick_callCityService_saveOperation(this,'onSaveClick_callCityService_saveOperationComplete',requestData);

		this.cityModel.cityId = null;
		this.cityModel.countryId = null;
		this.cityModel.stateId = null;
		this.cityModel.cityName = null;
		this.cityModel.cityCodeChar2 = null;
		this.cityModel.cityCode = null;
		this.cityModel.cityDescription = null;
		this.cityModel.cityFlag = null;
		this.cityModel.cityLatitude = null;
		this.cityModel.cityLongitude = null;
		this.cityModel.versionId = null;
	}

	onSaveClick_callCityService_saveOperationComplete(responseData) {

		if(responseData.response.success) {
			toastr.info(responseData.response.message);
		} else {
			toastr.error(responseData.response.message);
		}
	}


	onUpdateClick() {

		var requestData = {};

		var componentArray = [ this.countryId,this.stateId,this.cityName,this.cityCodeChar2,this.cityCode,this.cityDescription,this.cityFlag,this.cityLatitude,this.cityLongitude ];

		if(this.commonUtils.validateAndShowErrorMessages(componentArray)) {
			return;
		}

		requestData.cityId = this.cityModel.cityId;
		requestData.countryId = this.cityModel.countryId;
		requestData.stateId = this.cityModel.stateId;
		requestData.cityName = this.cityModel.cityName;
		requestData.cityCodeChar2 = this.cityModel.cityCodeChar2;
		requestData.cityCode = this.cityModel.cityCode;
		requestData.cityDescription = this.cityModel.cityDescription;
		requestData.cityFlag = this.cityModel.cityFlag;
		requestData.cityLatitude = this.cityModel.cityLatitude;
		requestData.cityLongitude = this.cityModel.cityLongitude;
		requestData.versionId = this.cityModel.versionId;

		this.cityService.onUpdateClick_callCityService_updateOperation(this,'onUpdateClick_callCityService_updateOperationComplete',requestData);

		this.cityModel.cityId = null;
		this.cityModel.countryId = null;
		this.cityModel.stateId = null;
		this.cityModel.cityName = null;
		this.cityModel.cityCodeChar2 = null;
		this.cityModel.cityCode = null;
		this.cityModel.cityDescription = null;
		this.cityModel.cityFlag = null;
		this.cityModel.cityLatitude = null;
		this.cityModel.cityLongitude = null;
		this.cityModel.versionId = null;
		this.save.hidden = false;
		this.update.hidden = true;
	}

	onUpdateClick_callCityService_updateOperationComplete(responseData) {

		if(responseData.response.success) {
			toastr.info(responseData.response.message);
		} else {
			toastr.error(responseData.response.message);
		}
	}


	onResetClick() {

		this.cityModel.cityId = null;
		this.cityModel.countryId = null;
		this.cityModel.stateId = null;
		this.cityModel.cityName = null;
		this.cityModel.cityCodeChar2 = null;
		this.cityModel.cityCode = null;
		this.cityModel.cityDescription = null;
		this.cityModel.cityFlag = null;
		this.cityModel.cityLatitude = null;
		this.cityModel.cityLongitude = null;
		this.cityModel.versionId = null;
		this.save.hidden = false;
		this.update.hidden = true;
	}

	onCityGridRowClick(data) {

		var requestData = {};
		requestData.findKey = data.cityId;

		this.cityService.onCityGridRowClick_callCity_findByIdOperation(this,'onCityGridRowClick_callCity_findByIdOperationComplete',requestData);

	}

	onCityGridRowClick_callCity_findByIdOperationComplete(responseData) {

		if(responseData.response.success) {
		let data = responseData.response.data;

		this.cityModel.cityId = data.cityId;
		this.cityModel.countryId = data.countryId;
		this.cityModel.stateId = data.stateId;
		this.cityModel.cityName = data.cityName;
		this.cityModel.cityCodeChar2 = data.cityCodeChar2;
		this.cityModel.cityCode = data.cityCode;
		this.cityModel.cityDescription = data.cityDescription;
		this.cityModel.cityFlag = data.cityFlag;
		this.cityModel.cityLatitude = data.cityLatitude;
		this.cityModel.cityLongitude = data.cityLongitude;
		this.cityModel.versionId = data.versionId;

		this.save.hidden = true;
		this.update.hidden = false;
		} else {
			toastr.error(responseData.response.message);
		}
	}


}