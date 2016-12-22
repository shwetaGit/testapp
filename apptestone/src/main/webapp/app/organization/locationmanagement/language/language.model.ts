export class LanguageModel
{


 private _language : string;

 private _languageType : string;

 private _languageDescription : string;

 private _languageIcon : string;

 private _alpha2 : string;

 private _alpha3 : string;

 private _alpha4 : string;

 private _alpha4parentid : number;

 private _languageId : string;

 private _versionId : string;

constructor(language:string,languageType:string,languageDescription:string,languageIcon:string,alpha2:string,alpha3:string,alpha4:string,alpha4parentid:number,languageId:string,versionId:string) {
 this._language = language;
 this._languageType = languageType;
 this._languageDescription = languageDescription;
 this._languageIcon = languageIcon;
 this._alpha2 = alpha2;
 this._alpha3 = alpha3;
 this._alpha4 = alpha4;
 this._alpha4parentid = alpha4parentid;
 this._languageId = languageId;
 this._versionId = versionId;
}

get language():string { return this._language; }

get languageType():string { return this._languageType; }

get languageDescription():string { return this._languageDescription; }

get languageIcon():string { return this._languageIcon; }

get alpha2():string { return this._alpha2; }

get alpha3():string { return this._alpha3; }

get alpha4():string { return this._alpha4; }

get alpha4parentid():number { return this._alpha4parentid; }

get languageId():string { return this._languageId; }

get versionId():string { return this._versionId; }


set language(value:string) { this._language = value; }

set languageType(value:string) { this._languageType = value; }

set languageDescription(value:string) { this._languageDescription = value; }

set languageIcon(value:string) { this._languageIcon = value; }

set alpha2(value:string) { this._alpha2 = value; }

set alpha3(value:string) { this._alpha3 = value; }

set alpha4(value:string) { this._alpha4 = value; }

set alpha4parentid(value:number) { this._alpha4parentid = value; }

set languageId(value:string) { this._languageId = value; }

set versionId(value:string) { this._versionId = value; }

}