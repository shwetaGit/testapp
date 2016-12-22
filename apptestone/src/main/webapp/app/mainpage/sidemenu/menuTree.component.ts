/**
 * Created by prasad on 26/8/16.
 */

import {Component, OnInit, Input} from '@angular/core';
import {Router} from "@angular/router";

@Component({
    selector: 'menu-tree',
    template : `

<li>
    <ul class="collapsible" *ngFor="let node of menuList"> <!--[routerLink]="node.routePath" routerLinkActive="active"-->
    
     <li (click)="node.toggle()">
     <a (click)="route(node)" class="collapsible-header waves-effect arrow-r" data-toggle="collapse"  [attr.href]="node.elemenTarget"  aria-expanded="false" >{{node.menuLabel}}
            <a *ngIf="!node.leaf"><i [attr.class]="node.iconUrl"></i></a>
     </a>
     </li>  
     <div style="padding-left: 10px"  class="collapse" [attr.id]="node.elementId" [ngClass]="{'collapse in': node.isExpanded , 'collapse':!node.isExpanded}">
          
        <div *ngIf="!node.leaf">
            <menu-tree [menuList]="node.children"></menu-tree>
        </div>
        
    </div>
    </ul>
</li>                          
                        
`
})
export class MenuTreeComponent implements OnInit {

    @Input() menuList: any;

    constructor(private router : Router) {

    }

    ngOnInit() { }

    ngAfterViewInit(){

    }

   route(node){
            if(node.leaf) {
                /* after getting data from service use this code*/
                if(node.routePath!='' && node.routePath != null) {
                    try {
                        let routeLink = '/mainpage/' + node.routePath;
                        this.router.navigate([routeLink]);
                    }
                    catch (e){  }
                }
                
            }

    }



}
