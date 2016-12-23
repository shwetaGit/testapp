Ext.define('Appmob.view.smartdevice.reportview.datapointview.DataPointViewController',{
	extend :'Appmob.view.smartdevice.reportview.ReportMainViewController',
	alias:'controller.report-datapointview',
	defineStore : function(datapoint_json){
		var dpview = this.view.down('#rp-dp-view');
		
		var dpStore = Ext.create('Ext.data.Store',{
			fields:["displayName",'dpJsonObj'],
			data:datapoint_json
		});
		dpview.setStore(dpStore);
		this.view.setHidden(false);
	},
	onDataPointAfterRender :function(){
		this.fetchDataPoint();
	},
	fetchDataPoint :function(){
			
		
		var sender = this;
		var reportmainview = sender.view.reportView;//sender.view.up('#reportView');
		
		if(reportmainview.refId){

			Ext.Ajax.request({
				url: AppRestUrl+'secure/dataEngineMobileService/getChartDataForMobile',
				method:'POST',
				jsonData : {
					id:reportmainview.refId
				},
				sender : sender,
				success:function(reponse,scope){
					var sender= scope.sender;
					var responseText = Ext.decode(reponse.responseText);
					
					if(responseText.response.success){
						var result =responseText.response.data;
						sender.defineStore(result.datapoints);
					}else{
						sender.defineStore([]);
					}
				},
				failure:function(reponse,scope){
				}
			});
		}
	},
	
	scrollDataPoints :function(navBtn){
		var reportdataview = this.view.down('#rp-dp-view');
	
		switch (navBtn.direction) {
		case "right":
			
			reportdataview.scrollBy(140,100,true);
			break;
		case "left":
			reportdataview.scrollBy(-140,100,true);
			break;

		default:
			break;
		}
	}
	
});