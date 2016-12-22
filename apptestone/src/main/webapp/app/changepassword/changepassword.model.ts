

export class ChangePassword{
    oldPassword: string;
    newPassword: string;
    reTypeNewPassword: string;
    changePasswordInNextLogin: string;

    constructor(){
        this.changePasswordInNextLogin = 'true';
    }
}
