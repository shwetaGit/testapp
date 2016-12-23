Ext.define('Appmob.view.smartdevice.reportview.chartview.ChartViewController',{
	extend :'Appmob.view.smartdevice.reportview.ReportMainViewController',
	alias:'controller.report-chartview',
	chartListView : undefined,
	chartDetailView : undefined,
	clickedListRecord:undefined,
	init:function(){
		
		this.chartListView = this.view.down('#report-chartview-list');
		this.chartDetailView = this.view.down('#report-chartview-detail');
		this.chartViewGroupList = this.view.down('#report-chartview-group-list');
		this.prevBtn = this.chartDetailView.down('#prevBtn');
		this.nextBtn = this.chartDetailView.down('#nextBtn');
	},
	defineStore :function(chart_json){
		var chartviewpanel = this.view;

		this.chartListView.setStore(Ext.create('Ext.data.Store', {
		    fields: ["chartTitle"] ,
		    data: chart_json
		}));
		
		if(chartviewpanel.reportUIJson && chartviewpanel.reportUIJson.searchCriteriaWidgets.length>0){
			 this.pushFloatingButton(this.chartListView);
		}
		this.chartListView.setHidden(false);
	},
	onChartDetailViewAfterRender : function(chartdetailview){

		var scope = this;
	    
	    chartdetailview.getEl().on('swipe',  function(info,el){
					if(info.direction == "left"){
						this.onNextChartClick();
					}else if(info.direction == "right"){
						this.onPrevChartClick();
					}
		},  scope);	
	},
	onChartListViewAfterRender :function(chartView){
		var sender = this;
		var reportmainview = sender.view.up('reportView');
		
		var chartviewpanel = this.view;
		
		
		if(reportmainview.refId){
		
		
		/*	var entMask = new Ext.LoadMask({
				msg : 'Please wait...',
				target :this.view
			}).show();*/
			Ext.Ajax.request({
				url:AppRestUrl+'secure/dataEngineMobileService/getChartDataForMobile',
				method:'POST',
				jsonData : {
					id:reportmainview.refId
				},
				//entMask:entMask,
				sender : sender,
				success:function(reponse,scope){
					var sender= scope.sender;
					var responseText = Ext.decode(reponse.responseText);
					
					if(responseText.response.success){
						var result =responseText.response.data;
						//console.log('Chart Data:: '+ Ext.encode(result.charts));
						sender.defineStore(result.charts);
					}else{
						sender.defineStore([]);
					}
				   // scope.entMask.hide();
					
				},
				failure:function(reponse,scope){
					
				//	 scope.entMask.hide();
				}
			});
		}
		
	},
	onChartListItemClick :function( dataview, record, item, index, e, eOpts){
		
		this.clickedListRecord = record;
		this.chartListView.setHidden(true);
		this.chartDetailView.setTitle(record.data.chartTitle);
		this.renderChart();
  
	},
	onChartGroupListItemClick :function(dataview, record, item, index, e, eOpts){
		var fusionchart = new FusionCharts(record.data);
		 fusionchart.width = '100%';
		 fusionchart.height ='100%';
		 fusionchart.render(this.chartDetailView.body.id);
	},
	onBackClick : function(){
		
  	  	this.clickedListRecord = undefined;
  	  	this.chartListView.setHidden(false);
	},
	renderChart :function(){
		if(this.clickedListRecord){
		
		 var chartData = this.clickedListRecord.data;

		 var fusionchart = new FusionCharts(chartData);
		 fusionchart.width = '100%';
		 fusionchart.height ='100%';
		 
		 fusionchart.setChartAttribute("animate3D",0);
		 fusionchart.setChartAttribute("animation",0);
		 fusionchart.setChartAttribute("showToolTip",0);
			
		 fusionchart.render(this.chartDetailView.body.id);

		 var idx = this.chartListView.store.indexOf(this.clickedListRecord);
		 var total = this.chartListView.store.data.length;

		 if(idx == total-1 && idx ==0){
			this.prevBtn.setHidden(true);
		 	this.nextBtn.setHidden(true);
		 }else if(idx ==0){
		 	this.nextBtn.setHidden(false);
		 	this.prevBtn.setHidden(true);

		 }else if(idx == total-1){
		 	this.prevBtn.setHidden(false);
		 	this.nextBtn.setHidden(true);
		 }else{
		 	this.prevBtn.setHidden(false);
		 	this.nextBtn.setHidden(false);
		 }
		
			if(chartData.hasOwnProperty("groupCharts") && chartData.groupCharts.length>0){
				this.displayGroupedCharts(chartData.groupCharts);
			}else{
				this.displayGroupedCharts([]);
			}
		}
	},
	displayGroupedCharts : function(chartGroupData){
		this.chartViewGroupList.setStore(Ext.create('Ext.data.Store', {
		    fields: ["chartTitle"] ,
		    data: chartGroupData
		}));
	},
	onNextChartClick :function(){
		
  	  if(this.clickedListRecord){
  		  var chartListStore = this.clickedListRecord.store;
      	  var idx = chartListStore.indexOf(this.clickedListRecord);
      	  var nextRecord = chartListStore.getAt(idx+1);
      	  
      	  if(nextRecord){
      		  
      		  this.clickedListRecord = nextRecord;
      		  this.renderChart();
      	  }
      	this.chartDetailView.setTitle(this.clickedListRecord.data.chartTitle);
  	  }
    
	},
	onPrevChartClick :function(){

		if(this.clickedListRecord){
			var chartListStore = this.clickedListRecord.store;
			var idx = chartListStore.indexOf(this.clickedListRecord);

			var prevRecord = chartListStore.getAt(idx-1);
			if(prevRecord){
				
				this.clickedListRecord = prevRecord;
				this.renderChart();
			}
			this.chartDetailView.setTitle(this.clickedListRecord.data.chartTitle);
		}
	
	}
	
});