export class StateModel
{


 private _countryId : any;

 private _stateName : string;

 private _stateCode : number;

 private _stateCodeChar2 : string;

 private _stateCodeChar3 : string;

 private _stateDescription : string;

 private _stateFlag : string;

 private _stateCapital : string;

 private _stateCapitalLatitude : number;

 private _stateCapitalLongitude : number;

 private _stateId : string;

 private _versionId : string;

constructor(countryId:any,stateName:string,stateCode:number,stateCodeChar2:string,stateCodeChar3:string,stateDescription:string,stateFlag:string,stateCapital:string,stateCapitalLatitude:number,stateCapitalLongitude:number,stateId:string,versionId:string) {
 this._countryId = countryId;
 this._stateName = stateName;
 this._stateCode = stateCode;
 this._stateCodeChar2 = stateCodeChar2;
 this._stateCodeChar3 = stateCodeChar3;
 this._stateDescription = stateDescription;
 this._stateFlag = stateFlag;
 this._stateCapital = stateCapital;
 this._stateCapitalLatitude = stateCapitalLatitude;
 this._stateCapitalLongitude = stateCapitalLongitude;
 this._stateId = stateId;
 this._versionId = versionId;
}

get countryId():any { return this._countryId; }

get stateName():string { return this._stateName; }

get stateCode():number { return this._stateCode; }

get stateCodeChar2():string { return this._stateCodeChar2; }

get stateCodeChar3():string { return this._stateCodeChar3; }

get stateDescription():string { return this._stateDescription; }

get stateFlag():string { return this._stateFlag; }

get stateCapital():string { return this._stateCapital; }

get stateCapitalLatitude():number { return this._stateCapitalLatitude; }

get stateCapitalLongitude():number { return this._stateCapitalLongitude; }

get stateId():string { return this._stateId; }

get versionId():string { return this._versionId; }


set countryId(value:any) { this._countryId = value; }

set stateName(value:string) { this._stateName = value; }

set stateCode(value:number) { this._stateCode = value; }

set stateCodeChar2(value:string) { this._stateCodeChar2 = value; }

set stateCodeChar3(value:string) { this._stateCodeChar3 = value; }

set stateDescription(value:string) { this._stateDescription = value; }

set stateFlag(value:string) { this._stateFlag = value; }

set stateCapital(value:string) { this._stateCapital = value; }

set stateCapitalLatitude(value:number) { this._stateCapitalLatitude = value; }

set stateCapitalLongitude(value:number) { this._stateCapitalLongitude = value; }

set stateId(value:string) { this._stateId = value; }

set versionId(value:string) { this._versionId = value; }

}