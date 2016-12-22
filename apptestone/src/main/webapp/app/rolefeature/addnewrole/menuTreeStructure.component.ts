

import {Component, OnInit, Input} from '@angular/core';
import {Router} from "@angular/router";
import {RoleFeatureServices} from "./addnewrole.service";

@Component({
    selector: 'all-feature',
    template : `
   <ul class="collapsible" *ngFor="let node of menuList let i=index">
         <li>
           <div class="col-lg-1">
             <input type="checkbox" [attr.id]="node.menuId" class="filled-in" [(ngModel)]="node.isSelected" name="isSelected" (change)="isMenuSelected(node)">
             <label [attr.for]="node.menuId"></label>
           </div>
           <div class="col-lg-5" >
             <a (click)="node.toggle()" >
                 <a *ngIf="!node.leaf"><i [attr.class]="node.iconUrl"></i></a>
             </a>&nbsp;&nbsp;  
             <label *ngIf="!node.leaf"><h6><b>{{node.menuLabel}}</b></h6></label> 
             <label *ngIf="node.leaf"><h7><span style="padding-left: 30px">{{node.menuLabel}}</span></h7></label> 
           </div>
           <div class="col-lg-2">
             <fieldset class="form-group">
             <input type="checkbox" [attr.id]="node.menuId" class="filled-in" [(ngModel)]="node.isRead" name="isRead">
             <label [attr.for]="node.menuId"></label>
             </fieldset>
           </div>
           <div class="col-lg-2">
              <fieldset class="form-group">
                <input type="checkbox" [attr.id]="node.menuId+i" class="filled-in" [(ngModel)]="node.isWrite" name="isWrite">
                <label [attr.for]="node.menuId+i"></label>
              </fieldset>
           </div>
           <div class="col-lg-2">
              <fieldset class="form-group">
                 <input type="checkbox" [attr.id]="node.menuId+i+i" class="filled-in" [(ngModel)]="node.isExecute" name="isExecute">
                 <label [attr.for]="node.menuId+i+i"></label>
                 </fieldset>
           </div>
         </li>  
         <div  class="collapse" [attr.id]="node.elementId" [ngClass]="{'collapse in': node.isExpanded , 'collapse':!node.isExpanded}">
              <div *ngIf="!node.leaf">
                <all-feature [menuList]="node.children"></all-feature>
              </div>    
         </div>
   </ul>
                       
                        
`
})
export class AllFeatureComponent implements OnInit {

    @Input() menuList: any;
    menuData : any;
    constructor(private _roleFeatureService : RoleFeatureServices) {

    }
    ngOnInit() { }

    isMenuSelected(node : any){
        this.menuData = this._roleFeatureService.menuData;
        this.menuData.forEach((menuOption)=>{
            if(menuOption.menuId == node.menuId){
                this.addSelected(menuOption.children,menuOption);
            }
            else {
                menuOption.children.forEach((menuOp)=>{
                    if(menuOp.menuId == node.menuId){
                        menuOp.isSelected = true;
                        menuOp.isExecute = true;
                        this.addSelected(menuOp.children,menuOption,);
                    }
                })
            }
        });
    }
    addSelected(childArray,parent){
        childArray.forEach((option)=>{
            if(!option.isSelected) {
                option.isSelected = true;
                option.isExecute = true;
                parent.isSelected = true;
                parent.isExecute =  true;
            }
            else {
                parent.isSelected = false;
                option.isSelected = false;
                parent.isExecute =  false;
                option.isExecute = false;
            }
            if(!option.leaf){
                this.addSelected(option.children,option);
            }

        })
    }

    ngAfterViewInit(){
    }
}