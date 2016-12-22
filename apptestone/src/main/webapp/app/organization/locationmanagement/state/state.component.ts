import { Component } from '@angular/core';
import { SharedDataService } from '../../../services/shareddata.service';
import { Router } from '@angular/router';
import { StateModel } from './state.model';
import { StateService } from './state.service';
import { CommonUtils,TextInputConfiguration,DataGridConfiguration,NumberInputConfiguration,SimpleButtonConfiguration,SelectInputConfiguration,TextAreaInputConfiguration } from 'ng_custom_widgets';

@Component({
selector : 'state-component',
templateUrl : 'app/organization/locationmanagement/state/state.template.html'
})
export class StateComponent {

	stateModel : StateModel;
	countryId : SelectInputConfiguration;
	countryIdData : any;
	stateName : TextInputConfiguration;
	stateCode : NumberInputConfiguration;
	stateCodeChar2 : TextInputConfiguration;
	stateCodeChar3 : TextInputConfiguration;
	stateDescription : TextAreaInputConfiguration;
	stateFlag : TextInputConfiguration;
	stateCapital : TextInputConfiguration;
	stateCapitalLatitude : NumberInputConfiguration;
	stateCapitalLongitude : NumberInputConfiguration;
	save : SimpleButtonConfiguration;
	update : SimpleButtonConfiguration;
	reset : SimpleButtonConfiguration;
	stateGrid : DataGridConfiguration;
	stateGridData : any;

	constructor(  private stateService : StateService, private commonUtils : CommonUtils, public sharedDataService : SharedDataService, private routerService : Router ) {

		this.stateModel = new StateModel(null,'',null,'','','','','',null,null,'','');
				this.countryIdData = [];
		this.countryId = new SelectInputConfiguration(false,false,true,'Please select Country Code','primaryDisplay','primaryKey');
		this.stateName = new TextInputConfiguration(false,false,false,true,'0','256','Please enter State Name');
		this.stateCode = new NumberInputConfiguration(false,false,false,false,'0','2','State Code must be between 0 and 2');
		this.stateCodeChar2 = new TextInputConfiguration(false,false,false,true,'0','32','Please enter State Code 2');
		this.stateCodeChar3 = new TextInputConfiguration(false,false,false,false,'0','32','Please enter State Code 3');
		this.stateDescription = new TextAreaInputConfiguration(false,false,false,false,'','','Please enter State Description');
		this.stateFlag = new TextInputConfiguration(false,false,false,false,'0','128','Please enter Flag');
		this.stateCapital = new TextInputConfiguration(false,false,false,false,'0','128','Please enter Capital');
		this.stateCapitalLatitude = new NumberInputConfiguration(false,false,false,false,'0','11','Capitial Latitude must be between 0 and 11');
		this.stateCapitalLongitude = new NumberInputConfiguration(false,false,false,false,'0','11','Capitial Longitude must be between 0 and 11');
		this.save = new SimpleButtonConfiguration(false,false);
		this.update = new SimpleButtonConfiguration(true,false);
		this.reset = new SimpleButtonConfiguration(false,false);
		
		var stateGridcolumns = [];
		stateGridcolumns.push({text : 'State Id', dataIndex : 'stateId', hidden: true});
		stateGridcolumns.push({text : 'Country Code', dataIndex : 'countryId', hidden: false});
		stateGridcolumns.push({text : 'State Name', dataIndex : 'stateName', hidden: false});
		stateGridcolumns.push({text : 'State Code', dataIndex : 'stateCode', hidden: false});
		stateGridcolumns.push({text : 'State Code 2', dataIndex : 'stateCodeChar2', hidden: false});
		stateGridcolumns.push({text : 'State Code 3', dataIndex : 'stateCodeChar3', hidden: false});
		stateGridcolumns.push({text : 'State Description', dataIndex : 'stateDescription', hidden: false});
		stateGridcolumns.push({text : 'Flag', dataIndex : 'stateFlag', hidden: false});
		stateGridcolumns.push({text : 'Capital', dataIndex : 'stateCapital', hidden: false});
		stateGridcolumns.push({text : 'Capitial Latitude', dataIndex : 'stateCapitalLatitude', hidden: false});
		stateGridcolumns.push({text : 'Capitial Longitude', dataIndex : 'stateCapitalLongitude', hidden: false});
		stateGridcolumns.push({text : 'createdBy', dataIndex : 'createdBy', hidden: true});
		stateGridcolumns.push({text : 'createdDate', dataIndex : 'createdDate', hidden: true});
		stateGridcolumns.push({text : 'updatedBy', dataIndex : 'updatedBy', hidden: true});
		stateGridcolumns.push({text : 'updatedDate', dataIndex : 'updatedDate', hidden: true});
		stateGridcolumns.push({text : 'versionId', dataIndex : 'versionId', hidden: true});
		stateGridcolumns.push({text : 'activeStatus', dataIndex : 'activeStatus', hidden: true});
		stateGridcolumns.push({text : 'txnAccessCode', dataIndex : 'txnAccessCode', hidden: true});
		this.stateGrid = new DataGridConfiguration(stateGridcolumns);
		this.stateGridData = [];

	}

	onSaveClick() {

		var requestData = {};

		var componentArray = [ this.countryId,this.stateName,this.stateCode,this.stateCodeChar2,this.stateCodeChar3,this.stateDescription,this.stateFlag,this.stateCapital,this.stateCapitalLatitude,this.stateCapitalLongitude ];

		if(this.commonUtils.validateAndShowErrorMessages(componentArray)) {
			return;
		}

		requestData.countryId = this.stateModel.countryId;
		requestData.stateName = this.stateModel.stateName;
		requestData.stateCode = this.stateModel.stateCode;
		requestData.stateCodeChar2 = this.stateModel.stateCodeChar2;
		requestData.stateCodeChar3 = this.stateModel.stateCodeChar3;
		requestData.stateDescription = this.stateModel.stateDescription;
		requestData.stateFlag = this.stateModel.stateFlag;
		requestData.stateCapital = this.stateModel.stateCapital;
		requestData.stateCapitalLatitude = this.stateModel.stateCapitalLatitude;
		requestData.stateCapitalLongitude = this.stateModel.stateCapitalLongitude;

		this.stateService.onSaveClick_callStateService_saveOperation(this,'onSaveClick_callStateService_saveOperationComplete',requestData);

		this.stateModel.stateId = null;
		this.stateModel.countryId = null;
		this.stateModel.stateName = null;
		this.stateModel.stateCode = null;
		this.stateModel.stateCodeChar2 = null;
		this.stateModel.stateCodeChar3 = null;
		this.stateModel.stateDescription = null;
		this.stateModel.stateFlag = null;
		this.stateModel.stateCapital = null;
		this.stateModel.stateCapitalLatitude = null;
		this.stateModel.stateCapitalLongitude = null;
		this.stateModel.versionId = null;
	}

	onSaveClick_callStateService_saveOperationComplete(responseData) {

		if(responseData.response.success) {
			toastr.info(responseData.response.message);
		} else {
			toastr.error(responseData.response.message);
		}
	}


	onUpdateClick() {

		var requestData = {};

		var componentArray = [ this.countryId,this.stateName,this.stateCode,this.stateCodeChar2,this.stateCodeChar3,this.stateDescription,this.stateFlag,this.stateCapital,this.stateCapitalLatitude,this.stateCapitalLongitude ];

		if(this.commonUtils.validateAndShowErrorMessages(componentArray)) {
			return;
		}

		requestData.stateId = this.stateModel.stateId;
		requestData.countryId = this.stateModel.countryId;
		requestData.stateName = this.stateModel.stateName;
		requestData.stateCode = this.stateModel.stateCode;
		requestData.stateCodeChar2 = this.stateModel.stateCodeChar2;
		requestData.stateCodeChar3 = this.stateModel.stateCodeChar3;
		requestData.stateDescription = this.stateModel.stateDescription;
		requestData.stateFlag = this.stateModel.stateFlag;
		requestData.stateCapital = this.stateModel.stateCapital;
		requestData.stateCapitalLatitude = this.stateModel.stateCapitalLatitude;
		requestData.stateCapitalLongitude = this.stateModel.stateCapitalLongitude;
		requestData.versionId = this.stateModel.versionId;

		this.stateService.onUpdateClick_callStateService_updateOperation(this,'onUpdateClick_callStateService_updateOperationComplete',requestData);

		this.stateModel.stateId = null;
		this.stateModel.countryId = null;
		this.stateModel.stateName = null;
		this.stateModel.stateCode = null;
		this.stateModel.stateCodeChar2 = null;
		this.stateModel.stateCodeChar3 = null;
		this.stateModel.stateDescription = null;
		this.stateModel.stateFlag = null;
		this.stateModel.stateCapital = null;
		this.stateModel.stateCapitalLatitude = null;
		this.stateModel.stateCapitalLongitude = null;
		this.stateModel.versionId = null;
		this.save.hidden = false;
		this.update.hidden = true;
	}

	onUpdateClick_callStateService_updateOperationComplete(responseData) {

		if(responseData.response.success) {
			toastr.info(responseData.response.message);
		} else {
			toastr.error(responseData.response.message);
		}
	}


	onResetClick() {

		this.stateModel.stateId = null;
		this.stateModel.countryId = null;
		this.stateModel.stateName = null;
		this.stateModel.stateCode = null;
		this.stateModel.stateCodeChar2 = null;
		this.stateModel.stateCodeChar3 = null;
		this.stateModel.stateDescription = null;
		this.stateModel.stateFlag = null;
		this.stateModel.stateCapital = null;
		this.stateModel.stateCapitalLatitude = null;
		this.stateModel.stateCapitalLongitude = null;
		this.stateModel.versionId = null;
		this.save.hidden = false;
		this.update.hidden = true;
	}

	onStateGridRowClick(data) {

		var requestData = {};
		requestData.findKey = data.stateId;

		this.stateService.onStateGridRowClick_callState_findByIdOperation(this,'onStateGridRowClick_callState_findByIdOperationComplete',requestData);

	}

	onStateGridRowClick_callState_findByIdOperationComplete(responseData) {

		if(responseData.response.success) {
		let data = responseData.response.data;

		this.stateModel.stateId = data.stateId;
		this.stateModel.countryId = data.countryId;
		this.stateModel.stateName = data.stateName;
		this.stateModel.stateCode = data.stateCode;
		this.stateModel.stateCodeChar2 = data.stateCodeChar2;
		this.stateModel.stateCodeChar3 = data.stateCodeChar3;
		this.stateModel.stateDescription = data.stateDescription;
		this.stateModel.stateFlag = data.stateFlag;
		this.stateModel.stateCapital = data.stateCapital;
		this.stateModel.stateCapitalLatitude = data.stateCapitalLatitude;
		this.stateModel.stateCapitalLongitude = data.stateCapitalLongitude;
		this.stateModel.versionId = data.versionId;

		this.save.hidden = true;
		this.update.hidden = false;
		} else {
			toastr.error(responseData.response.message);
		}
	}


}