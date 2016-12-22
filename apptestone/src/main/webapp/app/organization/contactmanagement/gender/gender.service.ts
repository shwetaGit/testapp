import { Injectable } from '@angular/core';
import { RequestOptions,Headers,Http } from '@angular/http';

@Injectable()
export class GenderService {

	responseData : any;
	callBackFunctionName : any;
	parentRef : any;

	constructor(private _http : Http){
	}

	onSaveClick_callGenderService_saveOperation(parentRef : any, callBackFunctionName : any, requestJson : any){

		this.parentRef = parentRef;
		this.callBackFunctionName = callBackFunctionName;

		let headers = new Headers({'Content-Type': 'application/json;charset=UTF-8'});
		let options = new RequestOptions({headers : headers, body : requestJson, method : 'post'});

		this._http.request('secure/Gender/', options).subscribe(
		response=>{
		this.responseData = response.json();
		},
		error=>{
			let errorData = error.json();
			toastr.error(errorData.response.message);
		},
		()=>{
		this.onSaveClick_callGenderService_saveOperationComplete();
		}
		);
	}

	onSaveClick_callGenderService_saveOperationComplete(){
		this.parentRef.onSaveClick_callGenderService_saveOperationComplete(this.responseData);
	}

	onUpdateClick_callGenderService_updateOperation(parentRef : any, callBackFunctionName : any, requestJson : any){

		this.parentRef = parentRef;
		this.callBackFunctionName = callBackFunctionName;

		let headers = new Headers({'Content-Type': 'application/json;charset=UTF-8'});
		let options = new RequestOptions({headers : headers, body : requestJson, method : 'put'});

		this._http.request('secure/Gender/', options).subscribe(
		response=>{
		this.responseData = response.json();
		},
		error=>{
			let errorData = error.json();
			toastr.error(errorData.response.message);
		},
		()=>{
		this.onUpdateClick_callGenderService_updateOperationComplete();
		}
		);
	}

	onUpdateClick_callGenderService_updateOperationComplete(){
		this.parentRef.onUpdateClick_callGenderService_updateOperationComplete(this.responseData);
	}

	onGenderGridRowClick_callGender_findByIdOperation(parentRef : any, callBackFunctionName : any, requestJson : any){

		this.parentRef = parentRef;
		this.callBackFunctionName = callBackFunctionName;

		let headers = new Headers({'Content-Type': 'application/json;charset=UTF-8'});
		let options = new RequestOptions({headers : headers, body : requestJson, method : 'post'});

		this._http.request('secure/Gender/findById', options).subscribe(
		response=>{
		this.responseData = response.json();
		},
		error=>{
		},
		()=>{
		this.onGenderGridRowClick_callGender_findByIdOperationComplete();
		}
		);
	}

	onGenderGridRowClick_callGender_findByIdOperationComplete(){
		this.parentRef.onGenderGridRowClick_callGender_findByIdOperationComplete(this.responseData);
	}

}