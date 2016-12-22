export class AddressTypeModel
{


 private _addressType : string;

 private _addressTypeDesc : string;

 private _addressTypeIcon : string;

 private _addressTypeId : string;

 private _versionId : string;

constructor(addressType:string,addressTypeDesc:string,addressTypeIcon:string,addressTypeId:string,versionId:string) {
 this._addressType = addressType;
 this._addressTypeDesc = addressTypeDesc;
 this._addressTypeIcon = addressTypeIcon;
 this._addressTypeId = addressTypeId;
 this._versionId = versionId;
}

get addressType():string { return this._addressType; }

get addressTypeDesc():string { return this._addressTypeDesc; }

get addressTypeIcon():string { return this._addressTypeIcon; }

get addressTypeId():string { return this._addressTypeId; }

get versionId():string { return this._versionId; }


set addressType(value:string) { this._addressType = value; }

set addressTypeDesc(value:string) { this._addressTypeDesc = value; }

set addressTypeIcon(value:string) { this._addressTypeIcon = value; }

set addressTypeId(value:string) { this._addressTypeId = value; }

set versionId(value:string) { this._versionId = value; }

}