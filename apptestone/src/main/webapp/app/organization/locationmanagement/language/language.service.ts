import { Injectable } from '@angular/core';
import { RequestOptions,Headers,Http } from '@angular/http';

@Injectable()
export class LanguageService {

	responseData : any;
	callBackFunctionName : any;
	parentRef : any;

	constructor(private _http : Http){
	}

	onSaveClick_callLanguageService_saveOperation(parentRef : any, callBackFunctionName : any, requestJson : any){

		this.parentRef = parentRef;
		this.callBackFunctionName = callBackFunctionName;

		let headers = new Headers({'Content-Type': 'application/json;charset=UTF-8'});
		let options = new RequestOptions({headers : headers, body : requestJson, method : 'post'});

		this._http.request('secure/Language/', options).subscribe(
		response=>{
		this.responseData = response.json();
		},
		error=>{
			let errorData = error.json();
			toastr.error(errorData.response.message);
		},
		()=>{
		this.onSaveClick_callLanguageService_saveOperationComplete();
		}
		);
	}

	onSaveClick_callLanguageService_saveOperationComplete(){
		this.parentRef.onSaveClick_callLanguageService_saveOperationComplete(this.responseData);
	}

	onUpdateClick_callLanguageService_updateOperation(parentRef : any, callBackFunctionName : any, requestJson : any){

		this.parentRef = parentRef;
		this.callBackFunctionName = callBackFunctionName;

		let headers = new Headers({'Content-Type': 'application/json;charset=UTF-8'});
		let options = new RequestOptions({headers : headers, body : requestJson, method : 'put'});

		this._http.request('secure/Language/', options).subscribe(
		response=>{
		this.responseData = response.json();
		},
		error=>{
			let errorData = error.json();
			toastr.error(errorData.response.message);
		},
		()=>{
		this.onUpdateClick_callLanguageService_updateOperationComplete();
		}
		);
	}

	onUpdateClick_callLanguageService_updateOperationComplete(){
		this.parentRef.onUpdateClick_callLanguageService_updateOperationComplete(this.responseData);
	}

	onLanguageGridRowClick_callLanguage_findByIdOperation(parentRef : any, callBackFunctionName : any, requestJson : any){

		this.parentRef = parentRef;
		this.callBackFunctionName = callBackFunctionName;

		let headers = new Headers({'Content-Type': 'application/json;charset=UTF-8'});
		let options = new RequestOptions({headers : headers, body : requestJson, method : 'post'});

		this._http.request('secure/Language/findById', options).subscribe(
		response=>{
		this.responseData = response.json();
		},
		error=>{
		},
		()=>{
		this.onLanguageGridRowClick_callLanguage_findByIdOperationComplete();
		}
		);
	}

	onLanguageGridRowClick_callLanguage_findByIdOperationComplete(){
		this.parentRef.onLanguageGridRowClick_callLanguage_findByIdOperationComplete(this.responseData);
	}

}