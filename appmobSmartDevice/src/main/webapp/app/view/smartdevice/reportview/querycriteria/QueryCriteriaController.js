Ext.define('Appmob.view.smartdevice.reportview.querycriteria.QueryCriteriaController',{
	extend :'Appmob.view.smartdevice.reportview.ReportMainViewController',
	alias:'controller.report-querycriteriaview',
	onApplyCriteriaBtnClick : function(){
		var sender = this;
		var reportmainview = sender.view.up('reportView');
		if(reportmainview.refId){
	
		var queryCriteriaParam = {
			reportId:reportmainview.refId,
			queryCriteria: this.formQueryCriteriaJson()
		};
		console.log("Query Criteria JSON "+Ext.encode(queryCriteriaParam));
		var entMask = new Ext.LoadMask({
			msg : 'Please wait...',
			target :this.view
		}).show();
		
		Ext.Ajax.request({
			url:AppRestUrl+'secure/dataEngineMobileService/updateSearchCriteriaInSession',
			method:'POST',
			jsonData : queryCriteriaParam,
			reportmainview:reportmainview,
			sender : sender,
			entMask :entMask,
			success:function(reponse,scope){
				var sender= scope.sender;
				var responseText = Ext.decode(reponse.responseText);
				scope.entMask.hide();
				
				if(responseText.response.success){
					var result =responseText.response.data;

					var dataview = reportmainview.down('#report-dataview');
					var chartview = reportmainview.down('#report-chartview');
					var datapointview = reportmainview.down('#report-datapointview');
					if(dataview){
						var dataviewlist = dataview.down('#report-dataview-list');
						dataviewlist.store.reload();
					}
					if(chartview){
						chartview.controller.onChartListViewAfterRender(chartview);
					}
					if(datapointview){
						datapointview.controller.fetchDataPoint(datapointview);
					}
					
					scope.reportmainview.setActiveItem(reportmainview.down('#reportTabPanel'));

				}
			},
			failure:function(reponse,scope){
				scope.entMask.hide();
				 Ext.MessageBox.show({title: 'Error',msg: "Could not apply criteria.",icon: Ext.MessageBox.ERROR});
			}
		});
		
		}
	},
	
	formQueryCriteriaJson : function(){
		var _criteriaJson = [];
		
		var queryWidgetHolderPanel = this.view.down('#queryWidgetHolderPanel');
		for (var i = 0; i < queryWidgetHolderPanel.items.items.length; i++) {
			var _Qwidget = queryWidgetHolderPanel.items.items[i];
			
			if(_Qwidget.xtype == "daterange"){
				var dateRangeJson = _Qwidget.getDateRangeJson();
				for (var d = 0; d < dateRangeJson.length; d++) {
					var dtJson = dateRangeJson[d];
					_criteriaJson.push(dtJson);
					
				}
			}else{
			_criteriaJson.push({
		        label: _Qwidget.fieldLabel,
		        name: _Qwidget.name,
		        datatype: _Qwidget.datatype,
		        fromui: true,
		        value: _Qwidget.value,
		        index: _Qwidget.index
		      });
			}

		}
		
		return _criteriaJson;
	},
	defineQueryCriteriaUI : function(queryCwid_json){
		
		var queryCWidgetU = queryCwid_json.queryCWidgetU;
		var reportmainview = this.view.up('reportView');
		this.view.add(queryCWidgetU);
		
		reportmainview.setActiveItem(this.view);
		
	},
	onBackClick :function(backBtn){
		var reportmainview = this.view.up('reportView');
		var reportTabPanel = reportmainview.down('#reportTabPanel');
		reportmainview.setActiveItem(reportTabPanel);
	}
});