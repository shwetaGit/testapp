export class CityModel
{


 private _countryId : any;

 private _stateId : any;

 private _cityName : string;

 private _cityCodeChar2 : string;

 private _cityCode : number;

 private _cityDescription : string;

 private _cityFlag : string;

 private _cityLatitude : number;

 private _cityLongitude : number;

 private _cityId : string;

 private _versionId : string;

constructor(countryId:any,stateId:any,cityName:string,cityCodeChar2:string,cityCode:number,cityDescription:string,cityFlag:string,cityLatitude:number,cityLongitude:number,cityId:string,versionId:string) {
 this._countryId = countryId;
 this._stateId = stateId;
 this._cityName = cityName;
 this._cityCodeChar2 = cityCodeChar2;
 this._cityCode = cityCode;
 this._cityDescription = cityDescription;
 this._cityFlag = cityFlag;
 this._cityLatitude = cityLatitude;
 this._cityLongitude = cityLongitude;
 this._cityId = cityId;
 this._versionId = versionId;
}

get countryId():any { return this._countryId; }

get stateId():any { return this._stateId; }

get cityName():string { return this._cityName; }

get cityCodeChar2():string { return this._cityCodeChar2; }

get cityCode():number { return this._cityCode; }

get cityDescription():string { return this._cityDescription; }

get cityFlag():string { return this._cityFlag; }

get cityLatitude():number { return this._cityLatitude; }

get cityLongitude():number { return this._cityLongitude; }

get cityId():string { return this._cityId; }

get versionId():string { return this._versionId; }


set countryId(value:any) { this._countryId = value; }

set stateId(value:any) { this._stateId = value; }

set cityName(value:string) { this._cityName = value; }

set cityCodeChar2(value:string) { this._cityCodeChar2 = value; }

set cityCode(value:number) { this._cityCode = value; }

set cityDescription(value:string) { this._cityDescription = value; }

set cityFlag(value:string) { this._cityFlag = value; }

set cityLatitude(value:number) { this._cityLatitude = value; }

set cityLongitude(value:number) { this._cityLongitude = value; }

set cityId(value:string) { this._cityId = value; }

set versionId(value:string) { this._versionId = value; }

}