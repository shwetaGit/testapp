Ext.define('Appmob.view.smartdevice.reportview.chartview.ChartView', {
	extend : 'Ext.panel.Panel',
	requires:['Appmob.view.smartdevice.reportview.chartview.ChartViewController'],
	xtype : 'report-chartview',
	itemId:'report-chartview',
	controller:'report-chartview',
	layout:'border',
	bodyStyle:{
		background:'#ffffff'
	},
	margin:'5 0 0 0',
	items : [
	 	{
	 	    	xtype: 'grid',
	 	    	region:'west',
	 	    	hideHeaders:true,
	 	    	itemId:'report-chartview-list',
	 	    	width:'100%',
	 	    	bodyStyle:{
		    		background: '#ffffff'
		    	},
		    	//cls:'report-list-view-cls',
		    	rowLines:true,
	 	    	emptyText: '<div align="center"><img src="resources/appicons/ic_no_result.png"/><div align="center" style="align:middle;font-size:14px;font-weight:bold;color:#838b8b;">No chart found!</div></div>',
	 	    	columns:[{
	 				xtype:'templatecolumn',
	 				tpl: new Ext.XTemplate(
	 			    '<tpl for=".">',
	 			        '<div style="vertical-align:middle;padding:8px;font-size:14px;font-weight:bold;">',
	 			          '{chartTitle}',
	 			        '</div>',
	 			    '</tpl>'
	 				),
	 				flex:1
	 			}],
	 	    	listeners :{
	 			
	 			  itemclick :'onChartListItemClick',
	 			  afterrender:'onChartListViewAfterRender'
	 		}

	 	},
	 	{
	 		xtype:'panel',
	 		region:'center',
	 		itemId:'report-chartview-detail',
	 		bodyPadding:10,
	 		title:'',
	 		margin:5,
	 		chartJson:undefined,
	 		listeners:{
				afterrender : 'onChartDetailViewAfterRender'
			},
	 		header : {
	 			titlePosition : 1,
	 			style:{
	 				background:'#ffffff'
	 			},
	 			items : [{ 
	 	               xtype: 'button',
	 	               icon:'resources/appicons/ic_back.png',
	 	               style:{
	                 	   borderRadius:'50%',
	                 	  borderWidth:'0px'
	                    },
	 	               scale:'medium',
	 	               margin:'0 5 0 0',
	 	               listeners:{
	 	                  click:'onBackClick'
	 	               }
	 				   }
	 	               ]
	 		},
	 		lbar:[{xtype:'tbfill'},
	 		      {
	 			xtype: 'button',
	 			itemId:'prevBtn',
	 			icon:'resources/appicons/ic_prev.png',
	 			hidden:true,
	 			scale:'medium',
	            height:35,
	 			width:35,
	 			style:{
	             	   borderRadius:'55%',
	             	   background:'#f6f6f6',
	             	   borderWidth:'0px'
	            },
	 			listeners:{
	 					click:'onPrevChartClick'
	 			}
	  	  
	 		      },
	 		      {xtype:'tbfill'}
	 		],
	 		rbar:[
	 		    {xtype:'tbfill'},{ 
	             xtype: 'button',
	             itemId:'nextBtn',
	             hidden:true,
	             icon:'resources/appicons/ic_next.png',
	             scale:'medium',
	             height:35,
	 			 width:35,
	 			 style:{
	             	   borderRadius:'55%',
	             	   background:'#f6f6f6',
	             	   borderWidth:'0px'
	             },
	             listeners:{
	                click:'onNextChartClick'
	             }
	         },  {xtype:'tbfill'}],
	        bbar:[
	         {
	        	xtype: 'dataview',
 				margin:'5 0 0 0',
 				itemId:'report-chartview-group-list',
 				region:'west',
 				autoScroll:true,
 				width: '100%',
 				scrollable:true,
 				loadingText:'Loading...',
 				tpl: new Ext.XTemplate(
				   '<table border=0 cellspacing="3">',
				  '<tr>',
				   '<tpl for=".">',
				   '<td >',
				   '<div class = "thumb-wrap" style = "border-radius:2px;width:120px;display: table-cell;text-align: center;white-space: nowrap;overflow: hide;text-transform: uppercase;margin:5px 5px 10px 5px;background:#f6f6f6;padding:5px;" >',
				   '<div style = "color:#000;font-size:10px;margin-left:5px;"><b>xindex</b></div>',
				   '</div>',
				   '</td>',
				   '</tpl>',
				  '</tr>',
				   '</table>'),
 	    		itemSelector: 'div.thumb-wrap',
 	    		listeners :{
 			  		itemclick :'onChartGroupListItemClick'
 			  	}
	        }]
	 	}]
	
});