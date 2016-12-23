Ext.define('Appmob.view.smartdevice.login.ForgetPassword', {
	extend : 'Ext.panel.Panel',
	xtype : 'forgetPassword',
	requires : [ 'Appmob.view.smartdevice.login.ForgetPasswordController' ],
	controller : 'forgetPasswordController',
	title : '',
	layout : {
		type : 'center',
		align : 'stretch'
	},
	items : [ {
		xtype : 'panel',
		width : '80%',
		margin : 20,
		layout : {
			type : 'vbox',
			align : 'stretch'
		},
		items : [ {
			html : '<div align="center"><b>Change Password</b><br><br></div>'
		}, {
			xtype : 'hidden',
			itemId : 'loginId',
			name : 'loginId'
		}, {
			xtype : 'combo',
			itemId : 'secQuestion',
			height:40,
			emptyText : 'Select Security Question',
			store : {
				fields : [ 'question', 'passRecoveryId' ],
				data : []
			},
			editable : false,
			forceSelection : true,
			displayField : 'question',
			valueField : 'passRecoveryId'
		}, {
			xtype : 'textarea',
			name : 'answer',
			itemId : 'secAnswer',
			emptyText : 'Enter Your Answer'
		}, {
			xtype : 'panel',
			layout : {
				type : 'hbox'
			},
			defaults : {
				flex : 1

			},
			items : [ {
				xtype : 'button',
				text : 'Cancel',
				margin : '5 5 5 0',
				scale:'large',
				style:{
					background:'',
					borderWidth:'0px',
					borderRadius:'0px'
				},
				listeners : {
					click : 'onCancelClick'
				}
			}, {
				xtype : 'button',
				text : 'OK',
				scale:'large',
				margin : '5 0 5 5',
				style:{
					background:'',
					borderWidth:'0px',
					borderRadius:'0px'
				},
				listeners : {
					click : 'onSubmitClick'
				}
			} ]

		} ]

	} ]

});