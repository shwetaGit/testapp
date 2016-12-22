/**
 * Created by prasad on 7/9/16.
 */


import { Injectable } from '@angular/core';
import {Http, Headers} from "@angular/http";
import {AppUrl} from "../constant";



@Injectable()
export class FirstService {

    mainScreenMenuUrl : string;

    constructor(private _http : Http) {

        this.mainScreenMenuUrl = AppUrl+'secure/MenuService/findMainScreenMenus';
    }

    getMainScreenMenu(){

        return this._http.post(this.mainScreenMenuUrl,null,{ jsonData : {}, headers : new Headers({ 'Content-Type': 'application/json;charset=UTF-8' })  })

    }



}