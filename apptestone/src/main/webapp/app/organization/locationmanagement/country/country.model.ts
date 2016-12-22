export class CountryModel
{


 private _countryName : string;

 private _countryCode1 : string;

 private _countryCode2 : string;

 private _countryFlag : string;

 private _capital : string;

 private _currencyCode : string;

 private _currencyName : string;

 private _currencySymbol : string;

 private _capitalLatitude : number;

 private _capitalLongitude : number;

 private _isoNumeric : number;

 private _countryId : string;

 private _versionId : string;

constructor(countryName:string,countryCode1:string,countryCode2:string,countryFlag:string,capital:string,currencyCode:string,currencyName:string,currencySymbol:string,capitalLatitude:number,capitalLongitude:number,isoNumeric:number,countryId:string,versionId:string) {
 this._countryName = countryName;
 this._countryCode1 = countryCode1;
 this._countryCode2 = countryCode2;
 this._countryFlag = countryFlag;
 this._capital = capital;
 this._currencyCode = currencyCode;
 this._currencyName = currencyName;
 this._currencySymbol = currencySymbol;
 this._capitalLatitude = capitalLatitude;
 this._capitalLongitude = capitalLongitude;
 this._isoNumeric = isoNumeric;
 this._countryId = countryId;
 this._versionId = versionId;
}

get countryName():string { return this._countryName; }

get countryCode1():string { return this._countryCode1; }

get countryCode2():string { return this._countryCode2; }

get countryFlag():string { return this._countryFlag; }

get capital():string { return this._capital; }

get currencyCode():string { return this._currencyCode; }

get currencyName():string { return this._currencyName; }

get currencySymbol():string { return this._currencySymbol; }

get capitalLatitude():number { return this._capitalLatitude; }

get capitalLongitude():number { return this._capitalLongitude; }

get isoNumeric():number { return this._isoNumeric; }

get countryId():string { return this._countryId; }

get versionId():string { return this._versionId; }


set countryName(value:string) { this._countryName = value; }

set countryCode1(value:string) { this._countryCode1 = value; }

set countryCode2(value:string) { this._countryCode2 = value; }

set countryFlag(value:string) { this._countryFlag = value; }

set capital(value:string) { this._capital = value; }

set currencyCode(value:string) { this._currencyCode = value; }

set currencyName(value:string) { this._currencyName = value; }

set currencySymbol(value:string) { this._currencySymbol = value; }

set capitalLatitude(value:number) { this._capitalLatitude = value; }

set capitalLongitude(value:number) { this._capitalLongitude = value; }

set isoNumeric(value:number) { this._isoNumeric = value; }

set countryId(value:string) { this._countryId = value; }

set versionId(value:string) { this._versionId = value; }

}