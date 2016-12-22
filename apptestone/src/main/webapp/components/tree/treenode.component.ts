import {Component, OnInit, Input, Input, Output, EventEmitter} from '@angular/core';

@Component({
    selector: 'tree-node-component',
    template: `  <ul class="collapsibleList">
            <li *ngFor="let record of localdata">
                  <label (click)="onItemClickEvent($event,record)">
                  <i (click)="toggle($event,record)" [ngClass]="{'fa fa-minus': record.expanded, 'fa fa-plus': record.children.length>0}" aria-hidden="true"
                   ></i>
                        {{record.label}}
                  </label>
                    
                 <div [ngClass]="{'collapse in': record.expanded , 'collapse':!record.expanded}" >
                   <tree-node-component 
                    (itemClickEventEmmiter) = "onItemClickEvent($event,record)"
                    [localdata]="record.children" 
                    [treeComponent] = "treeComponent"
                    [templateConfig] ="templateConfig">
                            treeui loading...
                    </tree-node-component>
                 </div>
            </li>
        </ul>`
})
export class TreeNodeComponent implements OnInit {

    @Input() templateConfig :any;
    @Input() localdata :any = [];
    @Input() treeComponent:any;
    @Output() itemClickEventEmmiter :any = new EventEmitter(<any>);

    constructor() { }

    ngOnInit() { }


    onItemClickEvent(node,record){
	
        this.treeComponent.onTreeNodeItemClick(record);
    }
  
	toggle(node:any,record : any){
	
        if(record.children.length>0){
            record.expanded = !record.expanded;
        }

    }
}
