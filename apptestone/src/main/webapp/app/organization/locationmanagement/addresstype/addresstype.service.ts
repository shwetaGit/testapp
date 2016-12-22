import { Injectable } from '@angular/core';
import { RequestOptions,Headers,Http } from '@angular/http';

@Injectable()
export class AddressTypeService {

	responseData : any;
	callBackFunctionName : any;
	parentRef : any;

	constructor(private _http : Http){
	}

	onSaveClick_callAddressTypeService_saveOperation(parentRef : any, callBackFunctionName : any, requestJson : any){

		this.parentRef = parentRef;
		this.callBackFunctionName = callBackFunctionName;

		let headers = new Headers({'Content-Type': 'application/json;charset=UTF-8'});
		let options = new RequestOptions({headers : headers, body : requestJson, method : 'post'});

		this._http.request('secure/AddressType/', options).subscribe(
		response=>{
		this.responseData = response.json();
		},
		error=>{
			let errorData = error.json();
			toastr.error(errorData.response.message);
		},
		()=>{
		this.onSaveClick_callAddressTypeService_saveOperationComplete();
		}
		);
	}

	onSaveClick_callAddressTypeService_saveOperationComplete(){
		this.parentRef.onSaveClick_callAddressTypeService_saveOperationComplete(this.responseData);
	}

	onUpdateClick_callAddressTypeService_updateOperation(parentRef : any, callBackFunctionName : any, requestJson : any){

		this.parentRef = parentRef;
		this.callBackFunctionName = callBackFunctionName;

		let headers = new Headers({'Content-Type': 'application/json;charset=UTF-8'});
		let options = new RequestOptions({headers : headers, body : requestJson, method : 'put'});

		this._http.request('secure/AddressType/', options).subscribe(
		response=>{
		this.responseData = response.json();
		},
		error=>{
			let errorData = error.json();
			toastr.error(errorData.response.message);
		},
		()=>{
		this.onUpdateClick_callAddressTypeService_updateOperationComplete();
		}
		);
	}

	onUpdateClick_callAddressTypeService_updateOperationComplete(){
		this.parentRef.onUpdateClick_callAddressTypeService_updateOperationComplete(this.responseData);
	}

	onAddressTypeGridRowClick_callAddressType_findByIdOperation(parentRef : any, callBackFunctionName : any, requestJson : any){

		this.parentRef = parentRef;
		this.callBackFunctionName = callBackFunctionName;

		let headers = new Headers({'Content-Type': 'application/json;charset=UTF-8'});
		let options = new RequestOptions({headers : headers, body : requestJson, method : 'post'});

		this._http.request('secure/AddressType/findById', options).subscribe(
		response=>{
		this.responseData = response.json();
		},
		error=>{
		},
		()=>{
		this.onAddressTypeGridRowClick_callAddressType_findByIdOperationComplete();
		}
		);
	}

	onAddressTypeGridRowClick_callAddressType_findByIdOperationComplete(){
		this.parentRef.onAddressTypeGridRowClick_callAddressType_findByIdOperationComplete(this.responseData);
	}

}