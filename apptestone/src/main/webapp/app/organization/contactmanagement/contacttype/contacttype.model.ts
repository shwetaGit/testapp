export class ContactTypeModel
{


 private _contactType : string;

 private _contactTypeDesc : string;

 private _contactTypeIcon : string;

 private _contactTypeId : string;

 private _versionId : string;

constructor(contactType:string,contactTypeDesc:string,contactTypeIcon:string,contactTypeId:string,versionId:string) {
 this._contactType = contactType;
 this._contactTypeDesc = contactTypeDesc;
 this._contactTypeIcon = contactTypeIcon;
 this._contactTypeId = contactTypeId;
 this._versionId = versionId;
}

get contactType():string { return this._contactType; }

get contactTypeDesc():string { return this._contactTypeDesc; }

get contactTypeIcon():string { return this._contactTypeIcon; }

get contactTypeId():string { return this._contactTypeId; }

get versionId():string { return this._versionId; }


set contactType(value:string) { this._contactType = value; }

set contactTypeDesc(value:string) { this._contactTypeDesc = value; }

set contactTypeIcon(value:string) { this._contactTypeIcon = value; }

set contactTypeId(value:string) { this._contactTypeId = value; }

set versionId(value:string) { this._versionId = value; }

}