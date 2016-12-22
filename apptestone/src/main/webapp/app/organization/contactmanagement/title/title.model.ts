export class TitleModel
{


 private _titles : string;

 private _titleId : string;

 private _versionId : string;

constructor(titles:string,titleId:string,versionId:string) {
 this._titles = titles;
 this._titleId = titleId;
 this._versionId = versionId;
}

get titles():string { return this._titles; }

get titleId():string { return this._titleId; }

get versionId():string { return this._versionId; }


set titles(value:string) { this._titles = value; }

set titleId(value:string) { this._titleId = value; }

set versionId(value:string) { this._versionId = value; }

}