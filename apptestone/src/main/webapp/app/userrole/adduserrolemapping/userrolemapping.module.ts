

import { NgModule }       from '@angular/core';
import {RouterModule} from "@angular/router";
import { BrowserModule }  from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import {HttpModule} from "@angular/http";

import {UserRoleMappingComponent} from "./userrolemapping.component";
import {UserRoleService} from "./userrolemapping.service";
import {WidgetModule} from "../../../components/widget.shared.module";


@NgModule({
    imports: [BrowserModule,RouterModule, FormsModule,HttpModule,WidgetModule,
        RouterModule.forChild([
            { path: '', component: UserRoleMappingComponent }
        ])
    ],
    providers: [UserRoleService],
    declarations: [UserRoleMappingComponent]
})

export class UserRoleMappingModule{


}