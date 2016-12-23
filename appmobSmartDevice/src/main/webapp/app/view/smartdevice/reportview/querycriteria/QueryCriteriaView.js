Ext.define('Appmob.view.smartdevice.reportview.querycriteria.QueryCriteriaView',{
	extend : 'Ext.panel.Panel',
	requires : [ 'Appmob.view.smartdevice.reportview.querycriteria.QueryCriteriaViewController' ],
	xtype : 'queryCriteriaPanel',
	controller : 'queryCriteriaController',
	//bodyPadding : '10',
	//bodyStyle: 'background:#f6f6f6;',
	itemId : 'querycriteria',
	reportDataJson:null,
	reportView:null,
	layout:'fit',
	items:[{
		xtype:'panel',
		bodyPadding:30,
		shrinkWrap:3,
		margin:0,
		autoScroll : true,
		layout:{type:'vbox',align:'stretch'},
		items:[{
	        xtype:'fieldset',
	        title: 'Auto Refresh',
	        checkboxName:'auto_refersh',
	        itemId:'isAutoRef',
	        hidden:false,
	        checkboxToggle: true,
	        collapsed: true,
	        layout:'anchor',
	        bodyPadding:0,
	        layout:{type:'vbox',align:'stretch'},
	        border:0,
	        listeners:{
	        	collapse:'onUncheckAutoRefresh'
	        },
	        items :[{
	    		xtype : 'combo',
	    		itemId:'cmbrefInterval',
	    		fieldLabel : 'Refresh Interval',
	    		margin:'10 0 0 0',
	    		//labelWidth:110,
	    		store : Ext.create('Ext.data.Store', {
	    			fields : [ 'displayValue', 'value' ],
	    			data : [ {
	    				"displayValue" : "5 Seconds",
	    				"value" : 5000
	    			}, {
	    				"displayValue" : "10 Seconds",
	    				"value" : 10000
	    			}, {
	    				"displayValue" : "20 Seconds",
	    				"value" : 20000
	    			}, {
	    				"displayValue" : "30 Seconds",
	    				"value" : 30000
	    			}, {
	    				"displayValue" : "1 Minute",
	    				"value" : 60000
	    			}, {
	    				"displayValue" : "3 Minute",
	    				"value" : 180000
	    			}, {
	    				"displayValue" : "5 Minute",
	    				"value" : 300000
	    			},
	    			//...
	    			]
	    		}),
	    		name : "interval",
	    		queryMode : 'local',
	    		displayField : 'displayValue',
	    		valueField : 'value',
	    		listeners:{
	    			select:'changeTimers'
	    		}
	    	}]
	    
		},{
			xtype:'panel',
			border:false,
			bodyPadding:0,
			itemId:'qcInnerPanelId',
			margin:10,
			layout:{type:'vbox',align:'stretch'},
			defaults:{
				margin:5
			},
			items:[]
			
		}],
		buttons:{
			items:[{
			text : 'Clear',
			itemId:"btnClear",
			scale:'medium',
			width:'50%',
			//icon : 'images/closable.png',
			listeners:{
				click:'clearData'
			}
		}, {
			text : 'Apply',
			itemId:"btnSearch",
			scale:'medium',
			width:'50%',
			//icon : 'resources/css/images/search.png',
			listeners:{
				click:'onApplyCriteriaBtnClick'
			}
		}]
	  },
		plugins:'responsive',
		responsiveConfig:{
			portrait: {
                width:'100%'
            },
            landscape: {
               width:'35%'
            }
		}
	}],
	listeners:{
		scope:'controller',
		added:'afterQCPanelAdded'
	},
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