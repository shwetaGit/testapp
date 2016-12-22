import { Injectable } from '@angular/core';
import { RequestOptions,Headers,Http } from '@angular/http';

@Injectable()
export class CountryService {

	responseData : any;
	callBackFunctionName : any;
	parentRef : any;

	constructor(private _http : Http){
	}

	onSaveClick_callCountryService_saveOperation(parentRef : any, callBackFunctionName : any, requestJson : any){

		this.parentRef = parentRef;
		this.callBackFunctionName = callBackFunctionName;

		let headers = new Headers({'Content-Type': 'application/json;charset=UTF-8'});
		let options = new RequestOptions({headers : headers, body : requestJson, method : 'post'});

		this._http.request('secure/Country/', options).subscribe(
		response=>{
		this.responseData = response.json();
		},
		error=>{
			let errorData = error.json();
			toastr.error(errorData.response.message);
		},
		()=>{
		this.onSaveClick_callCountryService_saveOperationComplete();
		}
		);
	}

	onSaveClick_callCountryService_saveOperationComplete(){
		this.parentRef.onSaveClick_callCountryService_saveOperationComplete(this.responseData);
	}

	onUpdateClick_callCountryService_updateOperation(parentRef : any, callBackFunctionName : any, requestJson : any){

		this.parentRef = parentRef;
		this.callBackFunctionName = callBackFunctionName;

		let headers = new Headers({'Content-Type': 'application/json;charset=UTF-8'});
		let options = new RequestOptions({headers : headers, body : requestJson, method : 'put'});

		this._http.request('secure/Country/', options).subscribe(
		response=>{
		this.responseData = response.json();
		},
		error=>{
			let errorData = error.json();
			toastr.error(errorData.response.message);
		},
		()=>{
		this.onUpdateClick_callCountryService_updateOperationComplete();
		}
		);
	}

	onUpdateClick_callCountryService_updateOperationComplete(){
		this.parentRef.onUpdateClick_callCountryService_updateOperationComplete(this.responseData);
	}

	onCountryGridRowClick_callCountry_findByIdOperation(parentRef : any, callBackFunctionName : any, requestJson : any){

		this.parentRef = parentRef;
		this.callBackFunctionName = callBackFunctionName;

		let headers = new Headers({'Content-Type': 'application/json;charset=UTF-8'});
		let options = new RequestOptions({headers : headers, body : requestJson, method : 'post'});

		this._http.request('secure/Country/findById', options).subscribe(
		response=>{
		this.responseData = response.json();
		},
		error=>{
		},
		()=>{
		this.onCountryGridRowClick_callCountry_findByIdOperationComplete();
		}
		);
	}

	onCountryGridRowClick_callCountry_findByIdOperationComplete(){
		this.parentRef.onCountryGridRowClick_callCountry_findByIdOperationComplete(this.responseData);
	}

}