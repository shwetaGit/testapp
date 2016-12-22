/**
 * Created by prasad on 18/8/16.
 */
import { NgModule }       from '@angular/core';

import { BrowserModule }  from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import {AppComponent} from "./app.component";

import {HttpModule} from "@angular/http";
import {RouterModule} from "@angular/router";



import {routing} from "./app.route";
import {LoginComponent} from "./login/login.component";
import {AuthenticationService} from "./login/authentication.service";
import {MainPageModule} from "./mainpage/mainpage.module";



@NgModule({
    imports: [BrowserModule,RouterModule, FormsModule,routing,HttpModule,MainPageModule],
    declarations: [AppComponent,LoginComponent],
    bootstrap: [ AppComponent ],
    providers : [AuthenticationService]

})

export class AppModule{

}