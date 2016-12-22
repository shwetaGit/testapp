import { Component } from '@angular/core';
import { SharedDataService } from '../../../services/shareddata.service';
import { Router } from '@angular/router';
import { LanguageModel } from './language.model';
import { LanguageService } from './language.service';
import { CommonUtils,TextInputConfiguration,DataGridConfiguration,NumberInputConfiguration,SimpleButtonConfiguration,TextAreaInputConfiguration } from 'ng_custom_widgets';

@Component({
selector : 'language-component',
templateUrl : 'app/organization/locationmanagement/language/language.template.html'
})
export class LanguageComponent {

	languageModel : LanguageModel;
	language : TextInputConfiguration;
	languageType : TextInputConfiguration;
	languageDescription : TextAreaInputConfiguration;
	languageIcon : TextInputConfiguration;
	alpha2 : TextInputConfiguration;
	alpha3 : TextInputConfiguration;
	alpha4 : TextInputConfiguration;
	alpha4parentid : NumberInputConfiguration;
	save : SimpleButtonConfiguration;
	update : SimpleButtonConfiguration;
	reset : SimpleButtonConfiguration;
	languageGrid : DataGridConfiguration;
	languageGridData : any;

	constructor(  private languageService : LanguageService, private commonUtils : CommonUtils, public sharedDataService : SharedDataService, private routerService : Router ) {

		this.languageModel = new LanguageModel('','','','','','','',null,'','');
		this.language = new TextInputConfiguration(false,false,false,true,'0','256','Please enter Language');
		this.languageType = new TextInputConfiguration(false,false,false,false,'0','32','Please enter Language Type');
		this.languageDescription = new TextAreaInputConfiguration(false,false,false,false,'','','Please enter Description');
		this.languageIcon = new TextInputConfiguration(false,false,false,false,'0','128','Please enter Icon');
		this.alpha2 = new TextInputConfiguration(false,false,false,false,'0','2','Please enter Alpha 2');
		this.alpha3 = new TextInputConfiguration(false,false,false,false,'0','3','Please enter Alpha 3');
		this.alpha4 = new TextInputConfiguration(false,false,false,false,'0','4','Please enter Alpha 4');
		this.alpha4parentid = new NumberInputConfiguration(false,false,false,false,'0','11','Aplha4 Parent Id must be between 0 and 11');
		this.save = new SimpleButtonConfiguration(false,false);
		this.update = new SimpleButtonConfiguration(true,false);
		this.reset = new SimpleButtonConfiguration(false,false);
		
		var languageGridcolumns = [];
		languageGridcolumns.push({text : 'Language Id', dataIndex : 'languageId', hidden: true});
		languageGridcolumns.push({text : 'Language', dataIndex : 'language', hidden: false});
		languageGridcolumns.push({text : 'Language Type', dataIndex : 'languageType', hidden: false});
		languageGridcolumns.push({text : 'Description', dataIndex : 'languageDescription', hidden: false});
		languageGridcolumns.push({text : 'Icon', dataIndex : 'languageIcon', hidden: false});
		languageGridcolumns.push({text : 'Alpha 2', dataIndex : 'alpha2', hidden: false});
		languageGridcolumns.push({text : 'Alpha 3', dataIndex : 'alpha3', hidden: false});
		languageGridcolumns.push({text : 'Alpha 4', dataIndex : 'alpha4', hidden: false});
		languageGridcolumns.push({text : 'Aplha4 Parent Id', dataIndex : 'alpha4parentid', hidden: false});
		languageGridcolumns.push({text : 'createdBy', dataIndex : 'createdBy', hidden: true});
		languageGridcolumns.push({text : 'createdDate', dataIndex : 'createdDate', hidden: true});
		languageGridcolumns.push({text : 'updatedBy', dataIndex : 'updatedBy', hidden: true});
		languageGridcolumns.push({text : 'updatedDate', dataIndex : 'updatedDate', hidden: true});
		languageGridcolumns.push({text : 'versionId', dataIndex : 'versionId', hidden: true});
		languageGridcolumns.push({text : 'activeStatus', dataIndex : 'activeStatus', hidden: true});
		languageGridcolumns.push({text : 'txnAccessCode', dataIndex : 'txnAccessCode', hidden: true});
		this.languageGrid = new DataGridConfiguration(languageGridcolumns);
		this.languageGridData = [];

	}

	onSaveClick() {

		var requestData = {};

		var componentArray = [ this.language,this.languageType,this.languageDescription,this.languageIcon,this.alpha2,this.alpha3,this.alpha4,this.alpha4parentid ];

		if(this.commonUtils.validateAndShowErrorMessages(componentArray)) {
			return;
		}

		requestData.language = this.languageModel.language;
		requestData.languageType = this.languageModel.languageType;
		requestData.languageDescription = this.languageModel.languageDescription;
		requestData.languageIcon = this.languageModel.languageIcon;
		requestData.alpha2 = this.languageModel.alpha2;
		requestData.alpha3 = this.languageModel.alpha3;
		requestData.alpha4 = this.languageModel.alpha4;
		requestData.alpha4parentid = this.languageModel.alpha4parentid;

		this.languageService.onSaveClick_callLanguageService_saveOperation(this,'onSaveClick_callLanguageService_saveOperationComplete',requestData);

		this.languageModel.languageId = null;
		this.languageModel.language = null;
		this.languageModel.languageType = null;
		this.languageModel.languageDescription = null;
		this.languageModel.languageIcon = null;
		this.languageModel.alpha2 = null;
		this.languageModel.alpha3 = null;
		this.languageModel.alpha4 = null;
		this.languageModel.alpha4parentid = null;
		this.languageModel.versionId = null;
	}

	onSaveClick_callLanguageService_saveOperationComplete(responseData) {

		if(responseData.response.success) {
			toastr.info(responseData.response.message);
		} else {
			toastr.error(responseData.response.message);
		}
	}


	onUpdateClick() {

		var requestData = {};

		var componentArray = [ this.language,this.languageType,this.languageDescription,this.languageIcon,this.alpha2,this.alpha3,this.alpha4,this.alpha4parentid ];

		if(this.commonUtils.validateAndShowErrorMessages(componentArray)) {
			return;
		}

		requestData.languageId = this.languageModel.languageId;
		requestData.language = this.languageModel.language;
		requestData.languageType = this.languageModel.languageType;
		requestData.languageDescription = this.languageModel.languageDescription;
		requestData.languageIcon = this.languageModel.languageIcon;
		requestData.alpha2 = this.languageModel.alpha2;
		requestData.alpha3 = this.languageModel.alpha3;
		requestData.alpha4 = this.languageModel.alpha4;
		requestData.alpha4parentid = this.languageModel.alpha4parentid;
		requestData.versionId = this.languageModel.versionId;

		this.languageService.onUpdateClick_callLanguageService_updateOperation(this,'onUpdateClick_callLanguageService_updateOperationComplete',requestData);

		this.languageModel.languageId = null;
		this.languageModel.language = null;
		this.languageModel.languageType = null;
		this.languageModel.languageDescription = null;
		this.languageModel.languageIcon = null;
		this.languageModel.alpha2 = null;
		this.languageModel.alpha3 = null;
		this.languageModel.alpha4 = null;
		this.languageModel.alpha4parentid = null;
		this.languageModel.versionId = null;
		this.save.hidden = false;
		this.update.hidden = true;
	}

	onUpdateClick_callLanguageService_updateOperationComplete(responseData) {

		if(responseData.response.success) {
			toastr.info(responseData.response.message);
		} else {
			toastr.error(responseData.response.message);
		}
	}


	onResetClick() {

		this.languageModel.languageId = null;
		this.languageModel.language = null;
		this.languageModel.languageType = null;
		this.languageModel.languageDescription = null;
		this.languageModel.languageIcon = null;
		this.languageModel.alpha2 = null;
		this.languageModel.alpha3 = null;
		this.languageModel.alpha4 = null;
		this.languageModel.alpha4parentid = null;
		this.languageModel.versionId = null;
		this.save.hidden = false;
		this.update.hidden = true;
	}

	onLanguageGridRowClick(data) {

		var requestData = {};
		requestData.findKey = data.languageId;

		this.languageService.onLanguageGridRowClick_callLanguage_findByIdOperation(this,'onLanguageGridRowClick_callLanguage_findByIdOperationComplete',requestData);

	}

	onLanguageGridRowClick_callLanguage_findByIdOperationComplete(responseData) {

		if(responseData.response.success) {
		let data = responseData.response.data;

		this.languageModel.languageId = data.languageId;
		this.languageModel.language = data.language;
		this.languageModel.languageType = data.languageType;
		this.languageModel.languageDescription = data.languageDescription;
		this.languageModel.languageIcon = data.languageIcon;
		this.languageModel.alpha2 = data.alpha2;
		this.languageModel.alpha3 = data.alpha3;
		this.languageModel.alpha4 = data.alpha4;
		this.languageModel.alpha4parentid = data.alpha4parentid;
		this.languageModel.versionId = data.versionId;

		this.save.hidden = true;
		this.update.hidden = false;
		} else {
			toastr.error(responseData.response.message);
		}
	}


}