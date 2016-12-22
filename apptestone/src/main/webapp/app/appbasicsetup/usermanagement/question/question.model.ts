export class QuestionModel
{


 private _levelid : number;

 private _question : string;

 private _questionDetails : string;

 private _questionIcon : string;

 private _questionId : string;

 private _versionId : string;

constructor(levelid:number,question:string,questionDetails:string,questionIcon:string,questionId:string,versionId:string) {
 this._levelid = levelid;
 this._question = question;
 this._questionDetails = questionDetails;
 this._questionIcon = questionIcon;
 this._questionId = questionId;
 this._versionId = versionId;
}

get levelid():number { return this._levelid; }

get question():string { return this._question; }

get questionDetails():string { return this._questionDetails; }

get questionIcon():string { return this._questionIcon; }

get questionId():string { return this._questionId; }

get versionId():string { return this._versionId; }


set levelid(value:number) { this._levelid = value; }

set question(value:string) { this._question = value; }

set questionDetails(value:string) { this._questionDetails = value; }

set questionIcon(value:string) { this._questionIcon = value; }

set questionId(value:string) { this._questionId = value; }

set versionId(value:string) { this._versionId = value; }

}