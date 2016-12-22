
import { NgModule }       from '@angular/core';
import {RouterModule} from "@angular/router";
import { BrowserModule }  from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import {HttpModule} from "@angular/http";
import {AddUserComponent} from "./adduser.component";
import {AddUserService} from "./adduser.service";
import {WidgetModule} from "../../../components/widget.shared.module";
import {UserRoleService} from "../../userrole/adduserrolemapping/userrolemapping.service";


@NgModule({
    imports: [BrowserModule,RouterModule, FormsModule,HttpModule,WidgetModule,
        RouterModule.forChild([
            { path: '', component: AddUserComponent }
        ])
    ],
    providers: [AddUserService,UserRoleService],
    declarations: [AddUserComponent]
})

export class AddUserModule{


}