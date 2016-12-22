import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { WidgetModule } from '../../../../components/widget.shared.module';
import { SharedDataService } from '../../../services/shareddata.service';
import { AddressTypeComponent } from './addresstype.component';
import { AddressTypeService } from './addresstype.service';

@NgModule({
imports : [ BrowserModule,FormsModule,HttpModule,WidgetModule,
RouterModule.forChild([{ path: '', component: AddressTypeComponent }])
 ],
declarations : [ AddressTypeComponent ],
providers : [ AddressTypeService,SharedDataService ]
})

export class AddressTypeModule { }