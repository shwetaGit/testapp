import { Component } from '@angular/core';
import { SharedDataService } from '../../../services/shareddata.service';
import { Router } from '@angular/router';
import { GenderModel } from './gender.model';
import { GenderService } from './gender.service';
import { CommonUtils,TextInputConfiguration,DataGridConfiguration,SimpleButtonConfiguration } from 'ng_custom_widgets';

@Component({
selector : 'gender-component',
templateUrl : 'app/organization/contactmanagement/gender/gender.template.html'
})
export class GenderComponent {

	genderModel : GenderModel;
	gender : TextInputConfiguration;
	save : SimpleButtonConfiguration;
	update : SimpleButtonConfiguration;
	reset : SimpleButtonConfiguration;
	genderGrid : DataGridConfiguration;
	genderGridData : any;

	constructor(  private genderService : GenderService, private commonUtils : CommonUtils, public sharedDataService : SharedDataService, private routerService : Router ) {

		this.genderModel = new GenderModel('','','');
		this.gender = new TextInputConfiguration(false,false,false,true,'0','256','Please enter gender');
		this.save = new SimpleButtonConfiguration(false,false);
		this.update = new SimpleButtonConfiguration(true,false);
		this.reset = new SimpleButtonConfiguration(false,false);
		
		var genderGridcolumns = [];
		genderGridcolumns.push({text : 'Id', dataIndex : 'genderId', hidden: true});
		genderGridcolumns.push({text : 'Gender', dataIndex : 'gender', hidden: false});
		genderGridcolumns.push({text : 'createdBy', dataIndex : 'createdBy', hidden: true});
		genderGridcolumns.push({text : 'createdDate', dataIndex : 'createdDate', hidden: true});
		genderGridcolumns.push({text : 'updatedBy', dataIndex : 'updatedBy', hidden: true});
		genderGridcolumns.push({text : 'updatedDate', dataIndex : 'updatedDate', hidden: true});
		genderGridcolumns.push({text : 'versionId', dataIndex : 'versionId', hidden: true});
		genderGridcolumns.push({text : 'activeStatus', dataIndex : 'activeStatus', hidden: true});
		genderGridcolumns.push({text : 'txnAccessCode', dataIndex : 'txnAccessCode', hidden: true});
		this.genderGrid = new DataGridConfiguration(genderGridcolumns);
		this.genderGridData = [];

	}

	onSaveClick() {

		var requestData = {};

		var componentArray = [ this.gender ];

		if(this.commonUtils.validateAndShowErrorMessages(componentArray)) {
			return;
		}

		requestData.gender = this.genderModel.gender;

		this.genderService.onSaveClick_callGenderService_saveOperation(this,'onSaveClick_callGenderService_saveOperationComplete',requestData);

		this.genderModel.genderId = null;
		this.genderModel.gender = null;
		this.genderModel.versionId = null;
	}

	onSaveClick_callGenderService_saveOperationComplete(responseData) {

		if(responseData.response.success) {
			toastr.info(responseData.response.message);
		} else {
			toastr.error(responseData.response.message);
		}
	}


	onUpdateClick() {

		var requestData = {};

		var componentArray = [ this.gender ];

		if(this.commonUtils.validateAndShowErrorMessages(componentArray)) {
			return;
		}

		requestData.genderId = this.genderModel.genderId;
		requestData.gender = this.genderModel.gender;
		requestData.versionId = this.genderModel.versionId;

		this.genderService.onUpdateClick_callGenderService_updateOperation(this,'onUpdateClick_callGenderService_updateOperationComplete',requestData);

		this.genderModel.genderId = null;
		this.genderModel.gender = null;
		this.genderModel.versionId = null;
		this.save.hidden = false;
		this.update.hidden = true;
	}

	onUpdateClick_callGenderService_updateOperationComplete(responseData) {

		if(responseData.response.success) {
			toastr.info(responseData.response.message);
		} else {
			toastr.error(responseData.response.message);
		}
	}


	onResetClick() {

		this.genderModel.genderId = null;
		this.genderModel.gender = null;
		this.genderModel.versionId = null;
		this.save.hidden = false;
		this.update.hidden = true;
	}

	onGenderGridRowClick(data) {

		var requestData = {};
		requestData.findKey = data.genderId;

		this.genderService.onGenderGridRowClick_callGender_findByIdOperation(this,'onGenderGridRowClick_callGender_findByIdOperationComplete',requestData);

	}

	onGenderGridRowClick_callGender_findByIdOperationComplete(responseData) {

		if(responseData.response.success) {
		let data = responseData.response.data;

		this.genderModel.genderId = data.genderId;
		this.genderModel.gender = data.gender;
		this.genderModel.versionId = data.versionId;

		this.save.hidden = true;
		this.update.hidden = false;
		} else {
			toastr.error(responseData.response.message);
		}
	}


}