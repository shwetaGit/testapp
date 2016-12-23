Ext.define('Appmob.view.smartdevice.reportview.ReportMainView', {
	extend : 'Ext.panel.Panel',
	requires:['Appmob.view.smartdevice.reportview.mapview.MapView','Appmob.view.smartdevice.reportview.querycriteria.QueryCriteriaView','Appmob.view.smartdevice.reportview.ReportMainViewController','Appmob.view.smartdevice.reportview.dataview.DataView','Appmob.view.smartdevice.reportview.chartview.ChartView'],
	xtype : 'reportView',
	itemId:'reportView',
	controller:'reportView',
	refId:undefined,
	layout:'card',
	style:{
		background: '#ffffff'
	},
	items : [{
		xtype:'tabpanel',
		itemId:'reportTabPanel',
		activeTab :0,
		/**Push Dataview and Chartview here dynamically...*/
		items:[/*{
			xtype : 'report-dataview',
			title : 'Data',
			iconAlign:'top',
			icon:'images/rpticon/chart.png'
		} ,{
			xtype : 'report-chartview',
			title : 'Chart',
			iconAlign:'top',
			icon:'images/rpticon/chart.png'
		}*/],
		tabPosition : 'bottom',
		tabBar : {
			hidden:false,
			style:{
				background:"#fff"
			},
			layout : {
				type : 'hbox',
				pack : 'center'
			},
			defaults : {
				height : 38,
				flex : 1
			}
		}
		/*,tbar:[{
			xtype:'panel',
			defaults:{
				height:40,
				margin:5,
				border:0
			},
			items:[
			{
				xtype:'button',
				text:'F',
				listeners:{
					click:'onOptionBtnClick'
				}
			},{
				xtype:'button',
				text:'Data Point-1',
				style:{
					background:'#DAF7A6'
				}
			}]
		}]*/
	}
	/**Push Search criteria here dynamically...*/
	/*{
		xtype : 'report-querycriteriaview',
		region:'west',
		width:'90%',
		hidden:false
	}*/],
	
	listeners:{
		scope:'controller',
		afterrender :'onReportViewAfterRender'
	}
	
});