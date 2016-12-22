/**
 * Created by prasad on 19/7/16.
 */
import {Component, OnInit} from '@angular/core';

@Component({
    selector: 'app',
    template : `
        <div class="container-fluid">
        <router-outlet></router-outlet>
        </div>

`,

})
export class AppComponent implements OnInit{

    constructor(){
    }

    ngOnInit(){

    }
    ngAfterViewInit(){
        $(".button-collapse").sideNav();
    }

}







