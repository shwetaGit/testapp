
import { NgModule }       from '@angular/core';
import {RouterModule} from "@angular/router";
import { BrowserModule }  from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import {HttpModule} from "@angular/http";
import {WidgetModule} from "../../components/widget.shared.module";
import {ChangePasswordComponent} from "./changepassword.component";
import {ChangePasswordServices} from "./changepassword.service";

@NgModule({
    imports: [BrowserModule,RouterModule, FormsModule,HttpModule,WidgetModule,
        RouterModule.forChild([
            { path: '', component: ChangePasswordComponent}
        ])
    ],
    providers: [ChangePasswordServices],
    declarations: [ChangePasswordComponent]
})

export class ChangePasswordModule{


}