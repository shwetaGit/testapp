Ext.define('Appmob.view.smartdevice.reportview.dataview.DataViewController',{
	extend :'Appmob.view.smartdevice.reportview.ReportMainViewController',
	alias:'controller.report-dataview',
	init:function(){
		this.dataListView = this.view.down('#report-dataview-list');
		this.dataDetailView = this.view.down('#report-dataview-detail');
		this.dataDrillDownView = this.view.down('#report-dataview-drilldown');
		
	},	
	defineStore : function(data_json){
		
		this.data_json = data_json;
		
		var reportgrid = this.view.down('#report-dataview-list');
		
		var reportgridstore = reportgrid.store;
		
		reportgridstore.pageSize = data_json.pageSize;
		//reportgridstore.sorters = data_json.sorters;
		reportgridstore.setFields(data_json.gridmodel);
		
		//reportgrid.setColumns(data_json.gridColumns);
		for (var c = 0; c < data_json.summaryGroups.length; c++) {
			reportgridstore.setGroupField(data_json.summaryGroups[c]);
		}
		var drillDownColumns = data_json.drillDownConfig;
		
		var hasDrillDown = (drillDownColumns &&drillDownColumns.length>0) ? true:false;
		this.configureTemplateColumn(data_json,hasDrillDown);
		
		if(hasDrillDown){
			this.configureDrillDownList(drillDownColumns);	
		}
		
		reportgrid.addDocked({
            xtype: 'pagingtoolbar',
            store: reportgridstore,
            plugins: new Ext.ux.SlidingPager(),
            dock: 'bottom',
            displayInfo: true,
            displayMsg : '{0}-{1} of {2}',
            emptyMsg :'',
            emptyMsg :'',
            firstText :"",
            lastText:"",
            nextText:"",
            prevText:"",
            refreshText:""
        });

		this.configureDetailView(data_json);
		reportgrid.setHidden(false);
	},
	configureTemplateColumn :function(data_json,hasDrillDown){
		var reportgrid = this.view.down('#report-dataview-list');
		var colTpl = "";
		for (var c = 0; c < data_json.gridColumns.length; c++) {
			var col = data_json.gridColumns[c];
			if(col.hasOwnProperty("isPrimaryColumn") && col.isPrimaryColumn){
					colTpl = colTpl +"<div>" +"<b>"+ col.header +"</b>"+" : "+"{"+col.dataIndex+"}"+"</div>"
			}
		}
		
		var _tpl =   "<br>"+"<div>"+colTpl+ "<br>"+"</div>";
		
		var cols = [];

		cols.push({
				xtype:'templatecolumn',
				tpl: _tpl,
				flex:1
		});
		if(hasDrillDown){
			cols.push({
				xtype:'widgetcolumn',
				width:55,
				height:'100%',
				shrinkWrap:1,
				widget:{
					xtype:'button',
					margin:'10 5 5 5',
					icon:"resources/appicons/ic_drill_down.png",
					height:38,
					width:38,
					style:{
	                	   borderRadius:'55%',
	                	   borderWidth:'0px'
	                   },
					listeners:{


					click: function(drilldownbtn, e){
							
							var record = drilldownbtn.getWidgetRecord();
							var repDataView = this.up('report-dataview');
							var scope = repDataView.controller;
							
							scope.dataDrillDownView.setHidden(false);
							scope.dataDrillDownView["currentRecord"] = record;
							scope.dataListView.setHidden(true);
							
							return;
						}
					}
					}
				
			});	
		}
		reportgrid.setColumns(cols);

	},
	
	configureDetailView : function(data_json){
		
		var dataDetailView = this.dataDetailView;
		this.dataDetailView.setTitle(data_json.report_name);
		
		for (var c = 0; c < data_json.gridColumns.length; c++) {
			var col = data_json.gridColumns[c];
			dataDetailView.add({
				xtype: 'displayfield',
				fieldLabel: col.header,
				style: "word-break: break-word; word-wrap: break-word;",
				bind:{
				value: '{'+col.dataIndex+'}'
				}
		})
		}
		
	},
	configureDrillDownList :function(drillDownColumns){
		
		var reportdrilldownlist = this.view.down('#drilldown-reports-combo');

		reportdrilldownlist.setStore(Ext.create('Ext.data.Store',{
			fields:["report_name","report_id","drillableColumn"],
			//groupField:'header',
			data:drillDownColumns
		})
		);

	},
	onDrillDownReportSelect : function( combo, record, eOpts ){
		
		var drillableColumn = record.data.drillableColumn;
		var columnsChkGroup = this.view.down('#columns-chk-group');
		columnsChkGroup.removeAll();
		columnsChkGroup.setFieldLabel("Drillable Columns");
		for (var c = 0; c < drillableColumn.length; c++) {
			var col = drillableColumn[c];
			columnsChkGroup.add({ boxLabel: col, name: "drillableColumn", inputValue: col, checked: true });
		}
	},
	onDrillDownBtnClick :function(){
		
		var reportdrilldownlist = this.view.down('#drilldown-reports-combo');
		var columnsChkGroup = this.view.down('#columns-chk-group');
		var drillDownView = this.view.down('#report-dataview-drilldown');
		var reportmainview = this.view.up('#reportView');
		
		var data = drillDownView.currentRecord.data; 

		var report_id = reportdrilldownlist.getValue();
		var _dcolumns = columnsChkGroup.getValue().drillableColumn;
		if(report_id && _dcolumns){
			var criteriaColumns =[];
			for (var c = 0; c < _dcolumns.length; c++) {
					var critObj = {};
					var col = _dcolumns[c];
					critObj[col] = data[col];
					criteriaColumns.push(critObj);
			}
			reportmainview.refId = report_id;
			
			
			this.configureReportViewUI(reportmainview,true,criteriaColumns);
			
			var reportRec = reportdrilldownlist.store.findRecord("report_id",report_id);
			if(reportRec){
			 var centerPanel =  Ext.getCmp('centerPanel');
			 centerPanel.setTitle(reportRec.data.report_name);
			}
			
		}else{
			Ext.Msg("Info","Please provide report details.");
		}
		
	},
	onDataListAfterRender :function(dataview){
		
		var dataviewpanel = this.view;

		if(dataviewpanel.reportUIJson && dataviewpanel.reportUIJson.searchCriteriaWidgets.length>0){
			this.pushFloatingButton(this.dataListView);
		}
	
	},
	onDataListItemClick : function( datalistview, record, item, index, e, eOpts ){
		
		this.clickedDataListItem = record;
		this.dataListView.setHidden(true);
		this.dataDetailView.getViewModel().setData(record.data);

	},
	onBackClick :function(){
		this.dataListView.setHidden(false);
		this.dataDrillDownView.setHidden(true);
	},
	onDrillDownBackClick :function(){
		this.dataDrillDownView.setHidden(true);
		this.dataListView.setHidden(false);
	},
	onDataDetailViewHide :function(dataDetailView){
		this.dataDetailView.reset();
	},
	onDrillDownViewHide :function(dataDrillDownView){
		dataDrillDownView.reset();
		var columnChk = dataDrillDownView.down('#columns-chk-group');
		columnChk.setFieldLabel("");
		columnChk.removeAll();
	}
});
