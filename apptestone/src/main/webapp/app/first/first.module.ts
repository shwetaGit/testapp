
import { NgModule }       from '@angular/core';
import {RouterModule} from "@angular/router";
import {FirstComponent} from "./first.component";
import {PasswordInput} from "../../components/passwordinput/password.input";
import {EmailInput} from "../../components/emailinput/email.input";
import { BrowserModule }  from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import {HttpModule} from "@angular/http";
import {FirstService} from "./first.service";

@NgModule({
    imports: [BrowserModule,RouterModule, FormsModule,HttpModule,
        RouterModule.forChild([
            { path: '', component: FirstComponent }
        ])
    ],
    providers: [FirstService],
    declarations: [FirstComponent,EmailInput,PasswordInput]
})

export class FirstModule{


}