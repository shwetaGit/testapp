Ext.define('Appmob.view.smartdevice.login.ForgetPasswordController',
		{
			extend : 'Ext.app.ViewController',
			alias : 'controller.forgetPasswordController',

			prepareForgetPasswordJson : function() {

				var json = {};
				json['loginId'] = this.getView().down('#loginId').getValue();
				json['passRecoveryId'] = this.getView().down('#secQuestion')
						.getValue();
				json['answer'] = this.getView().down('#secAnswer').getValue();
				return json;
			},

			onSubmitClick : function(btn, opts) {

				if (this.getView().down('#secQuestion').isValid()
						&& this.getView().down('#secAnswer').isValid()) {
					var formData = this.prepareForgetPasswordJson();
					var entMask = new Ext.LoadMask({
						msg : 'Please wait...',
						target : this.getView()
					}).show();

					Ext.Ajax.request({
						timeout : 180000,
						url : AppRestUrl+"secure/PasswordGenerator/forgetPassword",
						method : 'POST',
						headers : {
							isBeforeSession : true
						},
						waitMsg : 'Please wait...',
						entMask : entMask,
						jsonData : formData,
						me : this,
						success : function(response, sender) {

							var responseText = Ext.JSON
									.decode(response.responseText);
							if (responseText.response.success) {
								Ext.Msg.alert("Info",
										responseText.response.message);
								sender.me.onResetClick();
							} else {
								Ext.Msg.alert({
									title : 'Info',
									msg : responseText.response.message,
									icon : Ext.MessageBox.WARNING
								});
							}
							var container = this.getView().up();
							container.removeAll();
							container.add(Ext.create('Appmob.view.smartdevice.login.Login'));
							sender.entMask.hide();
						},
						failure : function(response, sender) {

							Ext.Msg.alert("ERROR", "Cannot connect to server");
							sender.entMask.hide();
						}
					});
				}
			},

			onResetClick : function(btn, opts) {
				this.getView().down('#secQuestion').reset();
				this.getView().down('#secAnswer').reset();
			},
			onCancelClick : function(btn, opts) {
				var container = this.getView().up();
				container.removeAll();
				container.add(Ext
						.create('Appmob.view.smartdevice.login.Login'));
			}
});