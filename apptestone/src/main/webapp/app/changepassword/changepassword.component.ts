
import { Component, OnInit } from '@angular/core';
import {PasswordInputConfiguration} from "../../components/passwordinput/password.input";
import {SimpleButtonConfiguration} from "../../components/button/simple.button";
import {ResetButtonConfiguration} from "../../components/button/reset.button";
import {ChangePassword} from "./changepassword.model";
import {ChangePasswordServices} from "./changepassword.service";

@Component({

    templateUrl: 'app/changepassword/changepassword.html'
})
export class ChangePasswordComponent implements OnInit {
    changePassword : ChangePassword;
    oldPassword;newPassword;reTypeNewPassword : PasswordInputConfiguration;
    SaveConfig: SimpleButtonConfiguration;
    ResetConfig : ResetButtonConfiguration;

    constructor(private _changePasswordServices : ChangePasswordServices) {
        this.changePassword =  new ChangePassword();
        this.oldPassword = new PasswordInputConfiguration(false,false,false,true,'','','Invalid Old Password');
        this.newPassword = new PasswordInputConfiguration(false,false,false,true,'','','Invalid New Password');
        this.reTypeNewPassword = new PasswordInputConfiguration(false,false,false,true,'','','Invalid New Password');
        this.SaveConfig = new SimpleButtonConfiguration(false,false);
        this.ResetConfig = new ResetButtonConfiguration(false,false);
    }
    ngOnInit() { }

    saveChangePassword(){

        if(this.changePassword.newPassword == this.changePassword.reTypeNewPassword) {
            let requestJson = {};
            requestJson['oldPassword'] = this.changePassword.oldPassword;
            requestJson['newPassword'] = this.changePassword.newPassword;
            requestJson['changePasswordInNextLogin'] = this.changePassword.changePasswordInNextLogin;
             this._changePasswordServices.changePassword(this, "changePasswordServiceResponse", "secure/PasswordGenerator/changePassword", "put", requestJson);
        }
        else{
            toastr.error('new password does not match');
        }
    }
    changePasswordServiceResponse(data : any){
        toastr.success(data.response.message);
        document.getElementById("changePass").reset();
    }
    reset(){
        document.getElementById("changePass").reset();
    }

}