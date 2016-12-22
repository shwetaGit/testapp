
import { Component, OnInit } from '@angular/core';
import {First} from "./first.model";
import {EmailInputConfiguration} from "../../components/emailinput/email.input";
import {PasswordInputConfiguration} from "../../components/passwordinput/password.input";
import {FirstService} from "./first.service";

@Component({
    selector: 'info-one',
    templateUrl :'app/first/first.html'
 /*   template: `
    
    <div class="card">
    <h4 class="card-header primary-color white-text">Sample Form</h4>
    <div class="card-block">

        <div class="row">


         <div class="col-lg-6">
              <div class="md-form">
                <input type="text" id="form1" class="form-control">
                <label for="form1" class="">First Name</label>
            </div>
            </div>

             <div class="col-lg-6">
                <div class="md-form">
                    <input type="text" id="form1" class="form-control">
                    <label for="form1" class="">Last Name</label>
                </div>
             </div>



            <div class="col-lg-6">
              <div class="md-form">
                <input type="text" id="form1" class="form-control">
                <label for="form1" class="">Email Id</label>
            </div>
            </div>

             <div class="col-lg-6">
                <div class="md-form">
                    <input type="password" id="form1" class="form-control">
                    <label for="form1" class="">Password</label>
                </div>
             </div>



             <div class="col-lg-6">
                <select class="mdb-select">
                    <option value="" disabled selected>Choose your City</option>
                    <option value="1">Pune</option>
                    <option value="2">Mumbai</option>
                    <option value="3">Kolhapur</option>
                </select>
                <label>City</label>
             </div>
               <div class="col-lg-6">
                <select class="mdb-select">
                    <option value="" disabled selected>Choose your State</option>
                    <option value="1">Maharashtra</option>
                    <option value="2">Andhra Pradesh</option>
                    <option value="3">Patna</option>
                </select>
                <label>City</label>
             </div>

            <div style="float: right;padding-right: 10px">
            <button type="button" class="btn btn-primary">Save</button>

            </div>

        </div>







    </div>
</div>
    
    
  `*/
})
export class FirstComponent implements OnInit {


    first : First;                              // Student model class Object
    firstDetails : First[];
    emailConfig : EmailInputConfiguration;
    passwordConfig : PasswordInputConfiguration;


    constructor(private FirstService : FirstService) {       // initilization of  studentService class reference
        this.firstDetails = [];

        this.first = new First();
        this.emailConfig = new EmailInputConfiguration(false,false,false,false,'5','20','');
        this.passwordConfig = new  PasswordInputConfiguration(false,false,false,false,'4','20','');



        this.FirstService.getMainScreenMenu().subscribe(
            response=> {
                let res =response.json();

                let check = JSON.parse(res.response.data);
                console.log('first json');


            },
            error=> {
                console.log('first error');
            },
            ()=> {

                console.log('first Submit');


            }
        );


    }
    ngOnInit() { }

    ngAfterViewInit(){
        $(document).ready(function() {
            $('.mdb-select').material_select();
        });

        new WOW().init();

    }




    submit()
    {
        this.firstDetails.push(this.first);
        console.log(this.firstDetails);
    }

}