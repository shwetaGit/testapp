Ext.define('Appmob.view.smartdevice.reportview.querycriteria.QueryCriteria', {
	extend : 'Ext.panel.Panel',
	requires:['Appmob.view.smartdevice.reportview.querycriteria.QueryCriteriaController'],
	xtype : 'report-querycriteriaview',
	controller:"report-querycriteriaview",
	itemId:'report-querycriteriaview',
	layout:'center',
	viewModel :{
		type:'default',
		
		formulas:{
			
		}
	},
	items:[{
		xtype:'panel',
		width:'100%',
		margin:10,
		itemId:'queryWidgetHolderPanel',
		layout:{type:'vbox',align:'stretch',pack:'center'},
		defaults:{
			margin:10,
			labelAlign:'top'
		}
		
	}],
	buttons:[{
		xtype:'button',
		text:'Apply criteria',
		flex:1,
		height:38,
		listeners:{
			click:'onApplyCriteriaBtnClick'
		}
	}],
	header : {
		titlePosition : 1,
		style:{
			background:'#ffffff'
		},
		items : [{ 
               xtype: 'button',
               style:{
            	   borderRadius:'50%',
            	   borderWidth:'0px'
               },
               scale:'medium',
               icon:'resources/appicons/ic_back.png',
               margin:'0 5 0 0',
               listeners:{
                  click:'onBackClick'
               }
			   }
               ]
	}
});