import { Component } from '@angular/core';
import { SharedDataService } from '../../../services/shareddata.service';
import { Router } from '@angular/router';
import { ContactTypeModel } from './contacttype.model';
import { ContactTypeService } from './contacttype.service';
import { CommonUtils,TextInputConfiguration,DataGridConfiguration,SimpleButtonConfiguration,TextAreaInputConfiguration } from 'ng_custom_widgets';

@Component({
selector : 'contacttype-component',
templateUrl : 'app/organization/contactmanagement/contacttype/contacttype.template.html'
})
export class ContactTypeComponent {

	contactTypeModel : ContactTypeModel;
	contactType : TextInputConfiguration;
	contactTypeDesc : TextAreaInputConfiguration;
	contactTypeIcon : TextInputConfiguration;
	save : SimpleButtonConfiguration;
	update : SimpleButtonConfiguration;
	reset : SimpleButtonConfiguration;
	contactTypeGrid : DataGridConfiguration;
	contactTypeGridData : any;

	constructor(  private contactTypeService : ContactTypeService, private commonUtils : CommonUtils, public sharedDataService : SharedDataService, private routerService : Router ) {

		this.contactTypeModel = new ContactTypeModel('','','','','');
		this.contactType = new TextInputConfiguration(false,false,false,true,'0','128','Please enter contact type');
		this.contactTypeDesc = new TextAreaInputConfiguration(false,false,false,false,'','','Please enter Contact Type Description');
		this.contactTypeIcon = new TextInputConfiguration(false,false,false,false,'0','128','Please enter Contact Type Icon');
		this.save = new SimpleButtonConfiguration(false,false);
		this.update = new SimpleButtonConfiguration(true,false);
		this.reset = new SimpleButtonConfiguration(false,false);
		
		var contactTypeGridcolumns = [];
		contactTypeGridcolumns.push({text : 'Contact Type Id', dataIndex : 'contactTypeId', hidden: true});
		contactTypeGridcolumns.push({text : 'Contact Type', dataIndex : 'contactType', hidden: false});
		contactTypeGridcolumns.push({text : 'Contact Type Description', dataIndex : 'contactTypeDesc', hidden: false});
		contactTypeGridcolumns.push({text : 'Contact Type Icon', dataIndex : 'contactTypeIcon', hidden: false});
		contactTypeGridcolumns.push({text : 'createdBy', dataIndex : 'createdBy', hidden: true});
		contactTypeGridcolumns.push({text : 'createdDate', dataIndex : 'createdDate', hidden: true});
		contactTypeGridcolumns.push({text : 'updatedBy', dataIndex : 'updatedBy', hidden: true});
		contactTypeGridcolumns.push({text : 'updatedDate', dataIndex : 'updatedDate', hidden: true});
		contactTypeGridcolumns.push({text : 'versionId', dataIndex : 'versionId', hidden: true});
		contactTypeGridcolumns.push({text : 'activeStatus', dataIndex : 'activeStatus', hidden: true});
		contactTypeGridcolumns.push({text : 'txnAccessCode', dataIndex : 'txnAccessCode', hidden: true});
		this.contactTypeGrid = new DataGridConfiguration(contactTypeGridcolumns);
		this.contactTypeGridData = [];

	}

	onSaveClick() {

		var requestData = {};

		var componentArray = [ this.contactType,this.contactTypeDesc,this.contactTypeIcon ];

		if(this.commonUtils.validateAndShowErrorMessages(componentArray)) {
			return;
		}

		requestData.contactType = this.contactTypeModel.contactType;
		requestData.contactTypeDesc = this.contactTypeModel.contactTypeDesc;
		requestData.contactTypeIcon = this.contactTypeModel.contactTypeIcon;

		this.contactTypeService.onSaveClick_callContactTypeService_saveOperation(this,'onSaveClick_callContactTypeService_saveOperationComplete',requestData);

		this.contactTypeModel.contactTypeId = null;
		this.contactTypeModel.contactType = null;
		this.contactTypeModel.contactTypeDesc = null;
		this.contactTypeModel.contactTypeIcon = null;
		this.contactTypeModel.versionId = null;
	}

	onSaveClick_callContactTypeService_saveOperationComplete(responseData) {

		if(responseData.response.success) {
			toastr.info(responseData.response.message);
		} else {
			toastr.error(responseData.response.message);
		}
	}


	onUpdateClick() {

		var requestData = {};

		var componentArray = [ this.contactType,this.contactTypeDesc,this.contactTypeIcon ];

		if(this.commonUtils.validateAndShowErrorMessages(componentArray)) {
			return;
		}

		requestData.contactTypeId = this.contactTypeModel.contactTypeId;
		requestData.contactType = this.contactTypeModel.contactType;
		requestData.contactTypeDesc = this.contactTypeModel.contactTypeDesc;
		requestData.contactTypeIcon = this.contactTypeModel.contactTypeIcon;
		requestData.versionId = this.contactTypeModel.versionId;

		this.contactTypeService.onUpdateClick_callContactTypeService_updateOperation(this,'onUpdateClick_callContactTypeService_updateOperationComplete',requestData);

		this.contactTypeModel.contactTypeId = null;
		this.contactTypeModel.contactType = null;
		this.contactTypeModel.contactTypeDesc = null;
		this.contactTypeModel.contactTypeIcon = null;
		this.contactTypeModel.versionId = null;
		this.save.hidden = false;
		this.update.hidden = true;
	}

	onUpdateClick_callContactTypeService_updateOperationComplete(responseData) {

		if(responseData.response.success) {
			toastr.info(responseData.response.message);
		} else {
			toastr.error(responseData.response.message);
		}
	}


	onResetClick() {

		this.contactTypeModel.contactTypeId = null;
		this.contactTypeModel.contactType = null;
		this.contactTypeModel.contactTypeDesc = null;
		this.contactTypeModel.contactTypeIcon = null;
		this.contactTypeModel.versionId = null;
		this.save.hidden = false;
		this.update.hidden = true;
	}

	onContactTypeGridRowClick(data) {

		var requestData = {};
		requestData.findKey = data.contactTypeId;

		this.contactTypeService.onContactTypeGridRowClick_callContactType_findByIdOperation(this,'onContactTypeGridRowClick_callContactType_findByIdOperationComplete',requestData);

	}

	onContactTypeGridRowClick_callContactType_findByIdOperationComplete(responseData) {

		if(responseData.response.success) {
		let data = responseData.response.data;

		this.contactTypeModel.contactTypeId = data.contactTypeId;
		this.contactTypeModel.contactType = data.contactType;
		this.contactTypeModel.contactTypeDesc = data.contactTypeDesc;
		this.contactTypeModel.contactTypeIcon = data.contactTypeIcon;
		this.contactTypeModel.versionId = data.versionId;

		this.save.hidden = true;
		this.update.hidden = false;
		} else {
			toastr.error(responseData.response.message);
		}
	}


}