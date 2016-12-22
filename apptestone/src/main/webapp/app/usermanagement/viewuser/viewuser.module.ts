
import { NgModule }       from '@angular/core';
import {RouterModule} from "@angular/router";
import { BrowserModule }  from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import {HttpModule} from "@angular/http";

import {WidgetModule} from "../../../components/widget.shared.module";
import {ViewUserComponent} from "./viewuser.component";
import {UserRoleService} from "../../userrole/adduserrolemapping/userrolemapping.service";



@NgModule({
    imports: [BrowserModule,RouterModule, FormsModule,HttpModule,WidgetModule,
        RouterModule.forChild([
            { path: '', component: ViewUserComponent}
        ])
    ],
    providers: [UserRoleService],
    declarations: [ViewUserComponent]
})

export class ViewUserModule{


}