import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { WidgetModule } from '../../../../components/widget.shared.module';
import { SharedDataService } from '../../../services/shareddata.service';
import { LanguageComponent } from './language.component';
import { LanguageService } from './language.service';

@NgModule({
imports : [ BrowserModule,FormsModule,HttpModule,WidgetModule,
RouterModule.forChild([{ path: '', component: LanguageComponent }])
 ],
declarations : [ LanguageComponent ],
providers : [ LanguageService,SharedDataService ]
})

export class LanguageModule { }