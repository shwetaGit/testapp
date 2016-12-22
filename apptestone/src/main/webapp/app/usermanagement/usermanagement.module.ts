
import { NgModule }       from '@angular/core';
import {RouterModule} from "@angular/router";
import { BrowserModule }  from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import {HttpModule} from "@angular/http";
import {UserManagementComponent} from "./usermanagement.component";
import {UserManagementService} from "./usermanagement.service";
import {WidgetModule} from "../../components/widget.shared.module";

@NgModule({
    imports: [BrowserModule,RouterModule, FormsModule,HttpModule,WidgetModule,
        RouterModule.forChild([
            { path: '', component: UserManagementComponent }
        ])
    ],
    providers: [UserManagementService],
    declarations: [UserManagementComponent]
})

export class UserManagementModule{


}