import { Injectable } from '@angular/core';
import { RequestOptions,Headers,Http } from '@angular/http';

@Injectable()
export class TimezoneService {

	responseData : any;
	callBackFunctionName : any;
	parentRef : any;

	constructor(private _http : Http){
	}

	onSaveClick_callTimezoneService_saveOperation(parentRef : any, callBackFunctionName : any, requestJson : any){

		this.parentRef = parentRef;
		this.callBackFunctionName = callBackFunctionName;

		let headers = new Headers({'Content-Type': 'application/json;charset=UTF-8'});
		let options = new RequestOptions({headers : headers, body : requestJson, method : 'post'});

		this._http.request('secure/Timezone/', options).subscribe(
		response=>{
		this.responseData = response.json();
		},
		error=>{
			let errorData = error.json();
			toastr.error(errorData.response.message);
		},
		()=>{
		this.onSaveClick_callTimezoneService_saveOperationComplete();
		}
		);
	}

	onSaveClick_callTimezoneService_saveOperationComplete(){
		this.parentRef.onSaveClick_callTimezoneService_saveOperationComplete(this.responseData);
	}

	onUpdateClick_callTimezoneService_updateOperation(parentRef : any, callBackFunctionName : any, requestJson : any){

		this.parentRef = parentRef;
		this.callBackFunctionName = callBackFunctionName;

		let headers = new Headers({'Content-Type': 'application/json;charset=UTF-8'});
		let options = new RequestOptions({headers : headers, body : requestJson, method : 'put'});

		this._http.request('secure/Timezone/', options).subscribe(
		response=>{
		this.responseData = response.json();
		},
		error=>{
			let errorData = error.json();
			toastr.error(errorData.response.message);
		},
		()=>{
		this.onUpdateClick_callTimezoneService_updateOperationComplete();
		}
		);
	}

	onUpdateClick_callTimezoneService_updateOperationComplete(){
		this.parentRef.onUpdateClick_callTimezoneService_updateOperationComplete(this.responseData);
	}

	onTimezoneGridRowClick_callTimezone_findByIdOperation(parentRef : any, callBackFunctionName : any, requestJson : any){

		this.parentRef = parentRef;
		this.callBackFunctionName = callBackFunctionName;

		let headers = new Headers({'Content-Type': 'application/json;charset=UTF-8'});
		let options = new RequestOptions({headers : headers, body : requestJson, method : 'post'});

		this._http.request('secure/Timezone/findById', options).subscribe(
		response=>{
		this.responseData = response.json();
		},
		error=>{
		},
		()=>{
		this.onTimezoneGridRowClick_callTimezone_findByIdOperationComplete();
		}
		);
	}

	onTimezoneGridRowClick_callTimezone_findByIdOperationComplete(){
		this.parentRef.onTimezoneGridRowClick_callTimezone_findByIdOperationComplete(this.responseData);
	}

}