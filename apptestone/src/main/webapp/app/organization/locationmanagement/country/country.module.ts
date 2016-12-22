import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { WidgetModule } from '../../../../components/widget.shared.module';
import { SharedDataService } from '../../../services/shareddata.service';
import { CountryComponent } from './country.component';
import { CountryService } from './country.service';

@NgModule({
imports : [ BrowserModule,FormsModule,HttpModule,WidgetModule,
RouterModule.forChild([{ path: '', component: CountryComponent }])
 ],
declarations : [ CountryComponent ],
providers : [ CountryService,SharedDataService ]
})

export class CountryModule { }