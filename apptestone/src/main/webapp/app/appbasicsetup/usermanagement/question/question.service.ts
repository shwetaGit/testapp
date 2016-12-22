import { Injectable } from '@angular/core';
import { RequestOptions,Headers,Http } from '@angular/http';

@Injectable()
export class QuestionService {

	responseData : any;
	callBackFunctionName : any;
	parentRef : any;

	constructor(private _http : Http){
	}

	onSaveClick_callQuestionService_saveOperation(parentRef : any, callBackFunctionName : any, requestJson : any){

		this.parentRef = parentRef;
		this.callBackFunctionName = callBackFunctionName;

		let headers = new Headers({'Content-Type': 'application/json;charset=UTF-8'});
		let options = new RequestOptions({headers : headers, body : requestJson, method : 'post'});

		this._http.request('secure/Question/', options).subscribe(
		response=>{
		this.responseData = response.json();
		},
		error=>{
			let errorData = error.json();
			toastr.error(errorData.response.message);
		},
		()=>{
		this.onSaveClick_callQuestionService_saveOperationComplete();
		}
		);
	}

	onSaveClick_callQuestionService_saveOperationComplete(){
		this.parentRef.onSaveClick_callQuestionService_saveOperationComplete(this.responseData);
	}

	onUpdateClick_callQuestionService_updateOperation(parentRef : any, callBackFunctionName : any, requestJson : any){

		this.parentRef = parentRef;
		this.callBackFunctionName = callBackFunctionName;

		let headers = new Headers({'Content-Type': 'application/json;charset=UTF-8'});
		let options = new RequestOptions({headers : headers, body : requestJson, method : 'put'});

		this._http.request('secure/Question/', options).subscribe(
		response=>{
		this.responseData = response.json();
		},
		error=>{
			let errorData = error.json();
			toastr.error(errorData.response.message);
		},
		()=>{
		this.onUpdateClick_callQuestionService_updateOperationComplete();
		}
		);
	}

	onUpdateClick_callQuestionService_updateOperationComplete(){
		this.parentRef.onUpdateClick_callQuestionService_updateOperationComplete(this.responseData);
	}

	onQuestionGridRowClick_callQuestion_findByIdOperation(parentRef : any, callBackFunctionName : any, requestJson : any){

		this.parentRef = parentRef;
		this.callBackFunctionName = callBackFunctionName;

		let headers = new Headers({'Content-Type': 'application/json;charset=UTF-8'});
		let options = new RequestOptions({headers : headers, body : requestJson, method : 'post'});

		this._http.request('secure/Question/findById', options).subscribe(
		response=>{
		this.responseData = response.json();
		},
		error=>{
		},
		()=>{
		this.onQuestionGridRowClick_callQuestion_findByIdOperationComplete();
		}
		);
	}

	onQuestionGridRowClick_callQuestion_findByIdOperationComplete(){
		this.parentRef.onQuestionGridRowClick_callQuestion_findByIdOperationComplete(this.responseData);
	}

}