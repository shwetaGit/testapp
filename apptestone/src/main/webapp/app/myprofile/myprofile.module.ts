
import { NgModule }       from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import {HttpModule} from "@angular/http";
import {RouterModule} from "@angular/router";
import {WidgetModule} from "../../components/widget.shared.module";
import {MyProfileComponent} from "./myprofile.component";
import {MyProfileServices} from "./myprofile.service";
import {AddUserService} from "../usermanagement/adduser/adduser.service";
@NgModule({
    imports: [BrowserModule,RouterModule, FormsModule,HttpModule,WidgetModule,  RouterModule.forChild([
        { path: '', component: MyProfileComponent }
    ])],
    declarations: [MyProfileComponent],
    providers : [MyProfileServices,AddUserService]

})

export class MyProfileModule{

}