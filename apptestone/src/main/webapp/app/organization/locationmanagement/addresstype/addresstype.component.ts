import { Component } from '@angular/core';
import { SharedDataService } from '../../../services/shareddata.service';
import { Router } from '@angular/router';
import { AddressTypeModel } from './addresstype.model';
import { AddressTypeService } from './addresstype.service';
import { CommonUtils,TextInputConfiguration,DataGridConfiguration,SimpleButtonConfiguration,TextAreaInputConfiguration } from 'ng_custom_widgets';

@Component({
selector : 'addresstype-component',
templateUrl : 'app/organization/locationmanagement/addresstype/addresstype.template.html'
})
export class AddressTypeComponent {

	addressTypeModel : AddressTypeModel;
	addressType : TextInputConfiguration;
	addressTypeDesc : TextAreaInputConfiguration;
	addressTypeIcon : TextInputConfiguration;
	save : SimpleButtonConfiguration;
	update : SimpleButtonConfiguration;
	reset : SimpleButtonConfiguration;
	addressTypeGrid : DataGridConfiguration;
	addressTypeGridData : any;

	constructor(  private addressTypeService : AddressTypeService, private commonUtils : CommonUtils, public sharedDataService : SharedDataService, private routerService : Router ) {

		this.addressTypeModel = new AddressTypeModel('','','','','');
		this.addressType = new TextInputConfiguration(false,false,false,true,'0','128','Please enter address type');
		this.addressTypeDesc = new TextAreaInputConfiguration(false,false,false,false,'','','Please enter Address Type Desc');
		this.addressTypeIcon = new TextInputConfiguration(false,false,false,false,'0','128','Please enter Address Type Icon');
		this.save = new SimpleButtonConfiguration(false,false);
		this.update = new SimpleButtonConfiguration(true,false);
		this.reset = new SimpleButtonConfiguration(false,false);
		
		var addressTypeGridcolumns = [];
		addressTypeGridcolumns.push({text : 'Address Type Id', dataIndex : 'addressTypeId', hidden: true});
		addressTypeGridcolumns.push({text : 'Address Type', dataIndex : 'addressType', hidden: false});
		addressTypeGridcolumns.push({text : 'Address Type Desc', dataIndex : 'addressTypeDesc', hidden: false});
		addressTypeGridcolumns.push({text : 'Address Type Icon', dataIndex : 'addressTypeIcon', hidden: false});
		addressTypeGridcolumns.push({text : 'createdBy', dataIndex : 'createdBy', hidden: true});
		addressTypeGridcolumns.push({text : 'createdDate', dataIndex : 'createdDate', hidden: true});
		addressTypeGridcolumns.push({text : 'updatedBy', dataIndex : 'updatedBy', hidden: true});
		addressTypeGridcolumns.push({text : 'updatedDate', dataIndex : 'updatedDate', hidden: true});
		addressTypeGridcolumns.push({text : 'versionId', dataIndex : 'versionId', hidden: true});
		addressTypeGridcolumns.push({text : 'activeStatus', dataIndex : 'activeStatus', hidden: true});
		addressTypeGridcolumns.push({text : 'txnAccessCode', dataIndex : 'txnAccessCode', hidden: true});
		this.addressTypeGrid = new DataGridConfiguration(addressTypeGridcolumns);
		this.addressTypeGridData = [];

	}

	onSaveClick() {

		var requestData = {};

		var componentArray = [ this.addressType,this.addressTypeDesc,this.addressTypeIcon ];

		if(this.commonUtils.validateAndShowErrorMessages(componentArray)) {
			return;
		}

		requestData.addressType = this.addressTypeModel.addressType;
		requestData.addressTypeDesc = this.addressTypeModel.addressTypeDesc;
		requestData.addressTypeIcon = this.addressTypeModel.addressTypeIcon;

		this.addressTypeService.onSaveClick_callAddressTypeService_saveOperation(this,'onSaveClick_callAddressTypeService_saveOperationComplete',requestData);

		this.addressTypeModel.addressTypeId = null;
		this.addressTypeModel.addressType = null;
		this.addressTypeModel.addressTypeDesc = null;
		this.addressTypeModel.addressTypeIcon = null;
		this.addressTypeModel.versionId = null;
	}

	onSaveClick_callAddressTypeService_saveOperationComplete(responseData) {

		if(responseData.response.success) {
			toastr.info(responseData.response.message);
		} else {
			toastr.error(responseData.response.message);
		}
	}


	onUpdateClick() {

		var requestData = {};

		var componentArray = [ this.addressType,this.addressTypeDesc,this.addressTypeIcon ];

		if(this.commonUtils.validateAndShowErrorMessages(componentArray)) {
			return;
		}

		requestData.addressTypeId = this.addressTypeModel.addressTypeId;
		requestData.addressType = this.addressTypeModel.addressType;
		requestData.addressTypeDesc = this.addressTypeModel.addressTypeDesc;
		requestData.addressTypeIcon = this.addressTypeModel.addressTypeIcon;
		requestData.versionId = this.addressTypeModel.versionId;

		this.addressTypeService.onUpdateClick_callAddressTypeService_updateOperation(this,'onUpdateClick_callAddressTypeService_updateOperationComplete',requestData);

		this.addressTypeModel.addressTypeId = null;
		this.addressTypeModel.addressType = null;
		this.addressTypeModel.addressTypeDesc = null;
		this.addressTypeModel.addressTypeIcon = null;
		this.addressTypeModel.versionId = null;
		this.save.hidden = false;
		this.update.hidden = true;
	}

	onUpdateClick_callAddressTypeService_updateOperationComplete(responseData) {

		if(responseData.response.success) {
			toastr.info(responseData.response.message);
		} else {
			toastr.error(responseData.response.message);
		}
	}


	onResetClick() {

		this.addressTypeModel.addressTypeId = null;
		this.addressTypeModel.addressType = null;
		this.addressTypeModel.addressTypeDesc = null;
		this.addressTypeModel.addressTypeIcon = null;
		this.addressTypeModel.versionId = null;
		this.save.hidden = false;
		this.update.hidden = true;
	}

	onAddressTypeGridRowClick(data) {

		var requestData = {};
		requestData.findKey = data.addressTypeId;

		this.addressTypeService.onAddressTypeGridRowClick_callAddressType_findByIdOperation(this,'onAddressTypeGridRowClick_callAddressType_findByIdOperationComplete',requestData);

	}

	onAddressTypeGridRowClick_callAddressType_findByIdOperationComplete(responseData) {

		if(responseData.response.success) {
		let data = responseData.response.data;

		this.addressTypeModel.addressTypeId = data.addressTypeId;
		this.addressTypeModel.addressType = data.addressType;
		this.addressTypeModel.addressTypeDesc = data.addressTypeDesc;
		this.addressTypeModel.addressTypeIcon = data.addressTypeIcon;
		this.addressTypeModel.versionId = data.versionId;

		this.save.hidden = true;
		this.update.hidden = false;
		} else {
			toastr.error(responseData.response.message);
		}
	}


}