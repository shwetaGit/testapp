export class GenderModel
{


 private _gender : string;

 private _genderId : string;

 private _versionId : string;

constructor(gender:string,genderId:string,versionId:string) {
 this._gender = gender;
 this._genderId = genderId;
 this._versionId = versionId;
}

get gender():string { return this._gender; }

get genderId():string { return this._genderId; }

get versionId():string { return this._versionId; }


set gender(value:string) { this._gender = value; }

set genderId(value:string) { this._genderId = value; }

set versionId(value:string) { this._versionId = value; }

}