import { Component } from '@angular/core';
import { SharedDataService } from '../../../services/shareddata.service';
import { Router } from '@angular/router';
import { TitleModel } from './title.model';
import { TitleService } from './title.service';
import { CommonUtils,TextInputConfiguration,DataGridConfiguration,SimpleButtonConfiguration } from 'ng_custom_widgets';

@Component({
selector : 'title-component',
templateUrl : 'app/organization/contactmanagement/title/title.template.html'
})
export class TitleComponent {

	titleModel : TitleModel;
	titles : TextInputConfiguration;
	save : SimpleButtonConfiguration;
	update : SimpleButtonConfiguration;
	reset : SimpleButtonConfiguration;
	titleGrid : DataGridConfiguration;
	titleGridData : any;

	constructor(  private titleService : TitleService, private commonUtils : CommonUtils, public sharedDataService : SharedDataService, private routerService : Router ) {

		this.titleModel = new TitleModel('','','');
		this.titles = new TextInputConfiguration(false,false,false,true,'0','256','Please enter title');
		this.save = new SimpleButtonConfiguration(false,false);
		this.update = new SimpleButtonConfiguration(true,false);
		this.reset = new SimpleButtonConfiguration(false,false);
		
		var titleGridcolumns = [];
		titleGridcolumns.push({text : 'Id', dataIndex : 'titleId', hidden: true});
		titleGridcolumns.push({text : 'Title', dataIndex : 'titles', hidden: false});
		titleGridcolumns.push({text : 'createdBy', dataIndex : 'createdBy', hidden: true});
		titleGridcolumns.push({text : 'createdDate', dataIndex : 'createdDate', hidden: true});
		titleGridcolumns.push({text : 'updatedBy', dataIndex : 'updatedBy', hidden: true});
		titleGridcolumns.push({text : 'updatedDate', dataIndex : 'updatedDate', hidden: true});
		titleGridcolumns.push({text : 'versionId', dataIndex : 'versionId', hidden: true});
		titleGridcolumns.push({text : 'activeStatus', dataIndex : 'activeStatus', hidden: true});
		titleGridcolumns.push({text : 'txnAccessCode', dataIndex : 'txnAccessCode', hidden: true});
		this.titleGrid = new DataGridConfiguration(titleGridcolumns);
		this.titleGridData = [];

	}

	onSaveClick() {

		var requestData = {};

		var componentArray = [ this.titles ];

		if(this.commonUtils.validateAndShowErrorMessages(componentArray)) {
			return;
		}

		requestData.titles = this.titleModel.titles;

		this.titleService.onSaveClick_callTitleService_saveOperation(this,'onSaveClick_callTitleService_saveOperationComplete',requestData);

		this.titleModel.titleId = null;
		this.titleModel.titles = null;
		this.titleModel.versionId = null;
	}

	onSaveClick_callTitleService_saveOperationComplete(responseData) {

		if(responseData.response.success) {
			toastr.info(responseData.response.message);
		} else {
			toastr.error(responseData.response.message);
		}
	}


	onUpdateClick() {

		var requestData = {};

		var componentArray = [ this.titles ];

		if(this.commonUtils.validateAndShowErrorMessages(componentArray)) {
			return;
		}

		requestData.titleId = this.titleModel.titleId;
		requestData.titles = this.titleModel.titles;
		requestData.versionId = this.titleModel.versionId;

		this.titleService.onUpdateClick_callTitleService_updateOperation(this,'onUpdateClick_callTitleService_updateOperationComplete',requestData);

		this.titleModel.titleId = null;
		this.titleModel.titles = null;
		this.titleModel.versionId = null;
		this.save.hidden = false;
		this.update.hidden = true;
	}

	onUpdateClick_callTitleService_updateOperationComplete(responseData) {

		if(responseData.response.success) {
			toastr.info(responseData.response.message);
		} else {
			toastr.error(responseData.response.message);
		}
	}


	onResetClick() {

		this.titleModel.titleId = null;
		this.titleModel.titles = null;
		this.titleModel.versionId = null;
		this.save.hidden = false;
		this.update.hidden = true;
	}

	onTitleGridRowClick(data) {

		var requestData = {};
		requestData.findKey = data.titleId;

		this.titleService.onTitleGridRowClick_callTitle_findByIdOperation(this,'onTitleGridRowClick_callTitle_findByIdOperationComplete',requestData);

	}

	onTitleGridRowClick_callTitle_findByIdOperationComplete(responseData) {

		if(responseData.response.success) {
		let data = responseData.response.data;

		this.titleModel.titleId = data.titleId;
		this.titleModel.titles = data.titles;
		this.titleModel.versionId = data.versionId;

		this.save.hidden = true;
		this.update.hidden = false;
		} else {
			toastr.error(responseData.response.message);
		}
	}


}