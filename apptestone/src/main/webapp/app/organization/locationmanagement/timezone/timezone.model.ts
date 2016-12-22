export class TimezoneModel
{


 private _utcdifference : number;

 private _gmtLabel : string;

 private _timeZoneLabel : string;

 private _country : string;

 private _cities : string;

 private _timeZoneId : string;

 private _versionId : string;

constructor(utcdifference:number,gmtLabel:string,timeZoneLabel:string,country:string,cities:string,timeZoneId:string,versionId:string) {
 this._utcdifference = utcdifference;
 this._gmtLabel = gmtLabel;
 this._timeZoneLabel = timeZoneLabel;
 this._country = country;
 this._cities = cities;
 this._timeZoneId = timeZoneId;
 this._versionId = versionId;
}

get utcdifference():number { return this._utcdifference; }

get gmtLabel():string { return this._gmtLabel; }

get timeZoneLabel():string { return this._timeZoneLabel; }

get country():string { return this._country; }

get cities():string { return this._cities; }

get timeZoneId():string { return this._timeZoneId; }

get versionId():string { return this._versionId; }


set utcdifference(value:number) { this._utcdifference = value; }

set gmtLabel(value:string) { this._gmtLabel = value; }

set timeZoneLabel(value:string) { this._timeZoneLabel = value; }

set country(value:string) { this._country = value; }

set cities(value:string) { this._cities = value; }

set timeZoneId(value:string) { this._timeZoneId = value; }

set versionId(value:string) { this._versionId = value; }

}