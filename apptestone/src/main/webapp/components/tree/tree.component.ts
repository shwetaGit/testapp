import {Component, OnInit, Input, EventEmitter, Output, Output} from '@angular/core';
import {TreeService} from "./tree.service";
import {HTTP_PROVIDERS} from "@angular/http";
import {TreeNodeComponent} from "./treenode.component";

@Component({
    selector: 'tree-component',
    template: `<div>
          
		 <tree-node-component [localdata]="localdata" 
		             [treeComponent] = "this"
		            (itemClickEventEmmiter) = "onTreeNodeItemClick($event)"
                    [templateConfig] ="templateConfig">treeui loading...</tree-node-component>
               
        </div>`,
    
    directives:[TreeNodeComponent],
    providers:[HTTP_PROVIDERS,TreeService]
})

export class TreeComponent implements OnInit {

	@Input() serviceurl : string;

    @Input() methodType : string;
    
    @Input() templateConfig : any;
    
    @Input() localdata : any = [];
    
    @Output() itemClick = new EventEmitter<>();
    
    servicedata : any=[];
    constructor(private  treeService :TreeService) {

    }

    ngOnInit() {

    }
    
    ngAfterViewInit (){
        
        if(this.templateConfig){
            this.treeService.callService(this,this.templateConfig,{});
        }else{
            console.log("Tree template config is not defined.")
        }


    }
    
    loadTreeComponent(){

       this.localdata =  this.generateTreeNodes(this.servicedata,this.templateConfig.levelInfo);
		
    }
    
	/** To do : handling in tpl itself */
   generateTreeNodes(data,nodeLevel){

        if(data){
            let modelArr = new Array();

            for (var i = 0; i < data.length; i++) {
                var info = data[i];

                if(nodeLevel && nodeLevel.innerNodes){

                    var innerNodes= nodeLevel.innerNodes;
                    info["label"] =  info[nodeLevel.text];
                    info["expanded"] =  false;
                    
                    if(innerNodes.length>0 )){
 						if(!info.hasOwnProperty("children")){
         		               info["children"] =  [];
           		         }
                        for (var j = 0; j < innerNodes.length; j++) {

                            var innerNode = innerNodes[j];

                            let idx = nodeLevel.innerCollection.hasOwnProperty(j)?j:0;

                            let innData = this.generateTreeNodes(info[nodeLevel.innerCollection[idx]],innerNode);

                            info.children = info.children.concat(innData);

                        }
                    }else{
                        //this.generateTreeNodes(info.children,nodeLevel);
                    }
                    	
                    modelArr.push(info);
                }
            }
            return data;
        }else{
            return [];
        }
    }
    
    onTreeNodeItemClick(node){
            this.itemClick.emit(node);
    }

}
