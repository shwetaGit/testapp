
import { NgModule }       from '@angular/core';
import {RouterModule} from "@angular/router";


import { BrowserModule }  from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import {HttpModule} from "@angular/http";
import {AddNewRoleComponent} from "./addnewrole.component";
import {AllFeatureComponent} from "./menuTreeStructure.component";
import {RoleFeatureServices} from "./addnewrole.service";

import {WidgetModule} from "../../../components/widget.shared.module";



@NgModule({
    imports: [BrowserModule,RouterModule, FormsModule,HttpModule,WidgetModule,
        RouterModule.forChild([
            { path: '', component: AddNewRoleComponent }
        ])
    ],
    providers: [RoleFeatureServices],
    declarations: [AddNewRoleComponent,AllFeatureComponent]
})

export class AddNewRoleModule{


}