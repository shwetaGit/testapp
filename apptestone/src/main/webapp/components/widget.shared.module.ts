/**
 * Created by pratik on 23/9/16.
 */
import {NgModule} from '@angular/core';
import {TextInput} from "./textinput/text.input";
import {HttpModule} from "@angular/http";
import {BrowserModule} from "@angular/platform-browser";
import {FormsModule} from "@angular/forms";
import {NumberInput} from "./numberinput/number.input";
import {EmailInput} from "./emailinput/email.input";
import {TextAreaInput} from "./textarea/textarea.input";
import {RangeInput} from "./rangeinput/range.input";
import {ResetButton} from "./button/reset.button";
import {SimpleButton} from "./button/simple.button";
import {CheckBoxGroup} from "./checkboxgroup/checkbox.group.input";
import {CheckBoxInput} from "./checkboxinput/checkbox.input";
import {DataGrid} from "./datagrid/datagrid";
import {DateInputComponent} from "./dateinput/date.input";
import {FileUploadInput} from "./fileuploadinput/fileupload.input";
import {HiddenInput} from "./hiddeninput/hidden.input";
import {PasswordInput} from "./passwordinput/password.input";
import {RadioGroup} from "./radioinput/radio.group.input";
import {ToggleInput} from "./toggleinput/toggle.input";
import {GroupFieldService} from "./services/group.service";
import {CheckBoxGroupService} from "./checkboxgroup/checkbox.group.service";
import {GridService} from "./services/table/grid.service";
import {SelectInput} from "./selectinput/select.input";
import {SelectInputService} from "./selectinput/select.input.service";
import {CommonUtils} from "./services/CommonUtils";
import {ReportChartComponent} from "../app/../components/report/chart/chart.component";
import {ReportDataGridComponent} from "../app/../components/report/datagrid/datagrid.component";
import {ReportDataPointComponent} from "../app/../components/report/datapoint/datapoint.component";
import {ReportMapComponent} from "../app/../components/report/map/map.component";
import {DefaultDateRangeComponent} from "./report/reportwidgets/defaultdaterange.component";
import {CustomDataGrid} from "./datagrid/customdatagrid";
import {ShortcutDateRangeComponent} from "./report/reportwidgets/shortcutdaterange.component";
import {TreeComponent} from "./tree/tree.component";

@NgModule({
    imports: [HttpModule,BrowserModule,FormsModule],
    exports: [TreeComponent, CustomDataGrid,TextInput,NumberInput,EmailInput,TextAreaInput,RangeInput,ResetButton,SimpleButton,CheckBoxGroup,CheckBoxInput,DataGrid,DateInputComponent,FileUploadInput,HiddenInput,PasswordInput,RadioGroup,ToggleInput,SelectInput,ReportMapComponent,ReportDataPointComponent,ReportDataGridComponent,ReportChartComponent,DefaultDateRangeComponent,ShortcutDateRangeComponent],
    declarations: [TreeComponent, CustomDataGrid,TextInput,NumberInput,EmailInput,TextAreaInput,RangeInput,ResetButton,SimpleButton,CheckBoxGroup,CheckBoxInput,DataGrid,DateInputComponent,FileUploadInput,HiddenInput,PasswordInput,RadioGroup,ToggleInput,SelectInput,ReportMapComponent,ReportDataPointComponent,ReportDataGridComponent,ReportChartComponent,DefaultDateRangeComponent,ShortcutDateRangeComponent],
    providers: [GroupFieldService,CheckBoxGroupService,GridService,SelectInputService,CommonUtils],
})
export class WidgetModule {
}

