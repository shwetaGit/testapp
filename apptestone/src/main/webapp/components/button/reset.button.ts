import {Component ,Input, Output, EventEmitter,OnInit}      from    '@angular/core';
import {BASIC_WIDGETSTYLE_CLASS, BUTTON_BASE_CLASS, BUTTON_INPUT_PREFIX} from "../style.constants";

@Component({
    selector:'reset-button',
    template:`
          <div [class]="widgetStyleClassName" [ngClass]="{'hiddendiv': widgetConfig.hidden , '':!widgetConfig.hidden}">                 
             <button type="reset" [attr.class]="buttonStyleClassName" [attr.id]="elementId" [attr.disabled]="widgetConfig.disabled ? true : null" (click)="onButtonReset($event)">{{label}}</button>
          </div>                 
`
})

export  class ResetButton implements OnInit{

    @Input()    label       :   string;

    @Input()    column      :   string;

    @Input()    buttonStyle    : string;

    @Input()    buttonSize     : string;

    @Input()     widgetConfig    :   ResetButtonConfiguration;

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

    onButtonReset(event : Event){
        this.onClick.emit(event);
    }

}


 export class ResetButtonConfiguration{
    hidden:boolean;
    disabled:boolean;

    constructor(hidden:boolean, disabled:boolean) {
        this.hidden = hidden;
        this.disabled = disabled;
    }
 }
