
import { Component, OnInit } from '@angular/core';

import {AuthenticationService} from "./authentication.service";
import {Router} from "@angular/router";
import {loginCredentials} from "./login.model";

@Component({
    selector: 'login',
    templateUrl : 'app/login/login.html',
})
export class LoginComponent implements OnInit {


    fieldControl  : boolean;
    logincredentials :loginCredentials;
    checkArray : any[];


    constructor(private authObj : AuthenticationService,private router : Router) {
        this.checkArray = [];
        this.logincredentials = new loginCredentials('','','','');
    }

    ngOnInit() { }

    authenticteUser() {
            this.authObj.loginServiceAuthenticateUser(this.logincredentials).subscribe(
                response=> {
                },
                error=> {
                    document.getElementById("errorMsg").style.display = "block";
                    document.getElementById("progressBar").style.display = "none";

                },
                ()=> {
                    document.getElementById("progressBar").style.display = "block";
                    this.fieldControl = true;
                    this.router.navigate(['/mainpage']);
                }
            );
        }




}