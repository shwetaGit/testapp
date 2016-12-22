import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { WidgetModule } from '../../../../components/widget.shared.module';
import { SharedDataService } from '../../../services/shareddata.service';
import { StateComponent } from './state.component';
import { StateService } from './state.service';

@NgModule({
imports : [ BrowserModule,FormsModule,HttpModule,WidgetModule,
RouterModule.forChild([{ path: '', component: StateComponent }])
 ],
declarations : [ StateComponent ],
providers : [ StateService,SharedDataService ]
})

export class StateModule { }