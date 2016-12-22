import { Component } from '@angular/core';
import { SharedDataService } from '../../../services/shareddata.service';
import { Router } from '@angular/router';
import { QuestionModel } from './question.model';
import { QuestionService } from './question.service';
import { CommonUtils,TextInputConfiguration,DataGridConfiguration,NumberInputConfiguration,SimpleButtonConfiguration,TextAreaInputConfiguration } from 'ng_custom_widgets';

@Component({
selector : 'question-component',
templateUrl : 'app/appbasicsetup/usermanagement/question/question.template.html'
})
export class QuestionComponent {

	questionModel : QuestionModel;
	levelid : NumberInputConfiguration;
	question : TextInputConfiguration;
	questionDetails : TextAreaInputConfiguration;
	questionIcon : TextInputConfiguration;
	save : SimpleButtonConfiguration;
	update : SimpleButtonConfiguration;
	reset : SimpleButtonConfiguration;
	questionGrid : DataGridConfiguration;
	questionGridData : any;

	constructor(  private questionService : QuestionService, private commonUtils : CommonUtils, public sharedDataService : SharedDataService, private routerService : Router ) {

		this.questionModel = new QuestionModel(null,'','','','','');
		this.levelid = new NumberInputConfiguration(false,false,false,true,'0','11','Level Id must be between 0 and 11');
		this.question = new TextInputConfiguration(false,false,false,true,'0','256','Please enter Question');
		this.questionDetails = new TextAreaInputConfiguration(false,false,false,false,'','','Please enter Details');
		this.questionIcon = new TextInputConfiguration(false,false,false,false,'0','64','Please enter Icon');
		this.save = new SimpleButtonConfiguration(false,false);
		this.update = new SimpleButtonConfiguration(true,false);
		this.reset = new SimpleButtonConfiguration(false,false);
		
		var questionGridcolumns = [];
		questionGridcolumns.push({text : 'Question Id', dataIndex : 'questionId', hidden: true});
		questionGridcolumns.push({text : 'Level Id', dataIndex : 'levelid', hidden: false});
		questionGridcolumns.push({text : 'Question', dataIndex : 'question', hidden: false});
		questionGridcolumns.push({text : 'Details', dataIndex : 'questionDetails', hidden: false});
		questionGridcolumns.push({text : 'Icon', dataIndex : 'questionIcon', hidden: false});
		questionGridcolumns.push({text : 'createdBy', dataIndex : 'createdBy', hidden: true});
		questionGridcolumns.push({text : 'createdDate', dataIndex : 'createdDate', hidden: true});
		questionGridcolumns.push({text : 'updatedBy', dataIndex : 'updatedBy', hidden: true});
		questionGridcolumns.push({text : 'updatedDate', dataIndex : 'updatedDate', hidden: true});
		questionGridcolumns.push({text : 'versionId', dataIndex : 'versionId', hidden: true});
		questionGridcolumns.push({text : 'activeStatus', dataIndex : 'activeStatus', hidden: true});
		questionGridcolumns.push({text : 'txnAccessCode', dataIndex : 'txnAccessCode', hidden: true});
		this.questionGrid = new DataGridConfiguration(questionGridcolumns);
		this.questionGridData = [];

	}

	onSaveClick() {

		var requestData = {};

		var componentArray = [ this.levelid,this.question,this.questionDetails,this.questionIcon ];

		if(this.commonUtils.validateAndShowErrorMessages(componentArray)) {
			return;
		}

		requestData.levelid = this.questionModel.levelid;
		requestData.question = this.questionModel.question;
		requestData.questionDetails = this.questionModel.questionDetails;
		requestData.questionIcon = this.questionModel.questionIcon;

		this.questionService.onSaveClick_callQuestionService_saveOperation(this,'onSaveClick_callQuestionService_saveOperationComplete',requestData);

		this.questionModel.questionId = null;
		this.questionModel.levelid = null;
		this.questionModel.question = null;
		this.questionModel.questionDetails = null;
		this.questionModel.questionIcon = null;
		this.questionModel.versionId = null;
	}

	onSaveClick_callQuestionService_saveOperationComplete(responseData) {

		if(responseData.response.success) {
			toastr.info(responseData.response.message);
		} else {
			toastr.error(responseData.response.message);
		}
	}


	onUpdateClick() {

		var requestData = {};

		var componentArray = [ this.levelid,this.question,this.questionDetails,this.questionIcon ];

		if(this.commonUtils.validateAndShowErrorMessages(componentArray)) {
			return;
		}

		requestData.questionId = this.questionModel.questionId;
		requestData.levelid = this.questionModel.levelid;
		requestData.question = this.questionModel.question;
		requestData.questionDetails = this.questionModel.questionDetails;
		requestData.questionIcon = this.questionModel.questionIcon;
		requestData.versionId = this.questionModel.versionId;

		this.questionService.onUpdateClick_callQuestionService_updateOperation(this,'onUpdateClick_callQuestionService_updateOperationComplete',requestData);

		this.questionModel.questionId = null;
		this.questionModel.levelid = null;
		this.questionModel.question = null;
		this.questionModel.questionDetails = null;
		this.questionModel.questionIcon = null;
		this.questionModel.versionId = null;
		this.save.hidden = false;
		this.update.hidden = true;
	}

	onUpdateClick_callQuestionService_updateOperationComplete(responseData) {

		if(responseData.response.success) {
			toastr.info(responseData.response.message);
		} else {
			toastr.error(responseData.response.message);
		}
	}


	onResetClick() {

		this.questionModel.questionId = null;
		this.questionModel.levelid = null;
		this.questionModel.question = null;
		this.questionModel.questionDetails = null;
		this.questionModel.questionIcon = null;
		this.questionModel.versionId = null;
		this.save.hidden = false;
		this.update.hidden = true;
	}

	onQuestionGridRowClick(data) {

		var requestData = {};
		requestData.findKey = data.questionId;

		this.questionService.onQuestionGridRowClick_callQuestion_findByIdOperation(this,'onQuestionGridRowClick_callQuestion_findByIdOperationComplete',requestData);

	}

	onQuestionGridRowClick_callQuestion_findByIdOperationComplete(responseData) {

		if(responseData.response.success) {
		let data = responseData.response.data;

		this.questionModel.questionId = data.questionId;
		this.questionModel.levelid = data.levelid;
		this.questionModel.question = data.question;
		this.questionModel.questionDetails = data.questionDetails;
		this.questionModel.questionIcon = data.questionIcon;
		this.questionModel.versionId = data.versionId;

		this.save.hidden = true;
		this.update.hidden = false;
		} else {
			toastr.error(responseData.response.message);
		}
	}


}