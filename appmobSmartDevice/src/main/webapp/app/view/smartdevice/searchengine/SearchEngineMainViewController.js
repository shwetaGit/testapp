Ext.define('Appmob.view.smartdevice.searchengine.SearchEngineMainViewController',{
	extend : 'Ext.app.ViewController',
	alias : 'controller.search-engine-main-view',
	
	onAfterRender :function(){
		this.searchForResult();
	},
	searchForResult : function(){
		var searchresponse = undefined;
		var scope = this;
		var searchText = Ext.getCmp("searchTxt").getValue();
		var operationType = "en";
		var entMask = new Ext.LoadMask({
			msg : 'Searching...',
			target :Ext.getCmp('mainPanel')
		}).show();
		Ext.Ajax.request({

			url : AppRestUrl+'secure/SearchEngineService/getSearchResult?searchString='
				+ encodeURIComponent(searchText)
				+ "&oprationType=" + operationType,
				method : 'GET',
				timeout : 1600000,
				scope : scope,
				entMask :entMask,
				waitMsg : 'Retriving data...',
				searchview : this.getView(),
				success : function(response,sender) {
					
					var result = Ext.decode(response.responseText);
					var searchdata = Ext.decode(result.response.data.message);
					var status = searchdata.status
				
					sender.scope.pushChartAndMap(searchdata);
					sender.scope.pushDoYouMeanLabels(searchdata);
					sender.entMask.hide();
					
				},
				failure : function(response,sender) {
					sender.entMask.hide();
					Ext.Msg.alert("Request Failed",
					"Request Failed");
					
				}

		});
		
	},
	pushChartAndMap :function(searchdata){
		
		var chartlist = this.view.down('#chart-list-view');
		var mapHolderPanel = this.view.down('#mapHolderPanel');

		var searchdata = searchdata.graphData ; 
		var chartstore = Ext.create('Ext.data.Store', {
					    fields: ["xtype","chartJson"] ,
					    data: []
					});
		
		for (var i = 0; i < searchdata.length; i++) {
			var data = searchdata[i];
			if(data.xtype=="mapPanel"){
				mapHolderPanel.add(data);
			}else{
				chartstore.add(data);
			}
		}
		chartlist.setStore(chartstore);

	},
	pushDoYouMeanLabels :function(searchdata){
		var searchTopBar = this.view.down('toolbar');
		
		searchTopBar.removeAll();

		if(searchdata.doUMean.length==0){
			searchTopBar.setHidden(true);
		}else{

	        searchTopBar.setHidden(false);
		for (var i = 0; i < searchdata.doUMean.length; i++) {
			var _doUMean = searchdata.doUMean[i];
			searchTopBar.add({
				html:_doUMean.html,
				newSearchString : _doUMean.newSearchString,
				listeners:{
					afterrender : function(label){
						this.on('click',function(sender,label){
								Ext.getCmp("searchTxt").setValue(this.newSearchString);
								Ext.getCmp("smartSearchBtn").fireEvent('click');
						});
					}
				}
			
			});
		}
	}
	}
	
});
