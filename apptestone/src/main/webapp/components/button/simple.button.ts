/**
 * Created by ketan on 7/9/16.
 */
import {Component ,Input, Output, EventEmitter,OnInit}      from    '@angular/core';
import {BASIC_WIDGETSTYLE_CLASS, BUTTON_BASE_CLASS, BUTTON_INPUT_PREFIX} from "../style.constants";

@Component({
    selector:'simple-button',
    template:`
             <button type="button"  [hidden] = "widgetConfig.hidden" class="btn btn-primary" [attr.id]="elementId" [attr.disabled]="widgetConfig.disabled ? true : null" (click)="onButtonClick($event)">{{label}}</button>
`
})

export  class SimpleButton implements OnInit{

    @Input()    label       :   string;

    @Input()    column      :   string;

    @Input()    buttonStyle    : string;

    @Input()    buttonSize     : string;

    @Input()     widgetConfig    :   SimpleButtonConfiguration;

    @Output()    onClick    : EventEmitter<Object>  =  new EventEmitter();

    buttonStyleClassName    :   string;

    widgetStyleClassName    : string;

    elementId   :   string;



    constructor() {}


    ngOnInit(){
        this.widgetStyleClassName = BASIC_WIDGETSTYLE_CLASS+this.column;
        this.buttonStyleClassName = BUTTON_BASE_CLASS+this.buttonStyle+' '+this.buttonSize;
        this.elementId = BUTTON_INPUT_PREFIX+Math.floor(Math.random()*90000) + 10000;
    }

    onButtonClick(event : Event){
        this.onClick.emit(event);
    }

}


export class SimpleButtonConfiguration{
    hidden:boolean;
    disabled:boolean;

    constructor(hidden:boolean, disabled:boolean) {
        this.hidden = hidden;
        this.disabled = disabled;
    }
}
