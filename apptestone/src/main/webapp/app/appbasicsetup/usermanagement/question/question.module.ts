import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { WidgetModule } from '../../../../components/widget.shared.module';
import { SharedDataService } from '../../../services/shareddata.service';
import { QuestionComponent } from './question.component';
import { QuestionService } from './question.service';

@NgModule({
imports : [ BrowserModule,FormsModule,HttpModule,WidgetModule,
RouterModule.forChild([{ path: '', component: QuestionComponent }])
 ],
declarations : [ QuestionComponent ],
providers : [ QuestionService,SharedDataService ]
})

export class QuestionModule { }