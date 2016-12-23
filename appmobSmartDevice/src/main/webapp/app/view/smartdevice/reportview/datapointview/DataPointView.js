Ext.define('Appmob.view.smartdevice.reportview.datapointview.DataPointView', {
	extend : 'Ext.panel.Panel',
	requires:['Appmob.view.smartdevice.reportview.datapointview.DataPointViewController'],
	xtype : 'report-datapointview',
	controller : 'report-datapointview',
	
	itemId : 'report-datapointview',
	dock: 'top',
	hidden:true,
	layout:{type:'hbox',align:'stretch'},
	style:'box-shadow: 1px 1px 1px 1px #888888;',
    items: [
        {
    		xtype: 'dataview',
    		itemId:'rp-dp-view',
    		width: '100%',
    		flex:1,
    		scrollable:true,
    		store:[],
    		itemSelector: 'td.thumb-wrap',
    		style: 'overflow-y: scroll; overflow-x: hidden;',
    		emptyText: '<div align="center"><img src="resources/appicons/ic_no_result.png"/><div align="center" style="align:middle;font-size:14px;font-weight:bold;color:#838b8b;">No datapoint found!</div></div>',
    		tpl: new Ext.XTemplate(
  				   '<table border=0 cellspacing="3">',
  				  '<tr>',
  				   '<tpl for=".">',
  				   '<td >',
  				   '<div style = "color:#848484;font-size:10px;margin-left:5px;"><b>{label}</b></div>',
  				   '<div style="display: table-row;">',
  				   '<tpl for="value">',
  				   		'<div style = "border-radius:2px;min-width:120px;display: table-cell;text-align: center;white-space: nowrap;overflow: hide;text-transform: uppercase;margin:5px 5px 10px 5px;background:{[this.getHex(xindex,2)]};padding:5px;" >',
  				   		'<div style="color:#ffffff;font-size:15px;font-weight:bold;">{statistic}</div>',
  				   		'<div style="color:#ffffff;font-size:12px;font-weight:normal;">{description}</div>',
  				   		'</div>',
                         '{[this.increaseInx()]}',
  				   '</tpl>',
  				   '</div>',
  				   '</td>',
  				   
  				   '</tpl>',
  				  '</tr>',
  				   '</table>',
  				   {
  				        compiled: true,
  				        disableFormats: true,
  				        currentIndex:1,
  				        increaseInx :function(){
  				        	this.currentIndex = this.currentIndex+1;
  				        },
  				        getHex: function(value,dpType) {
  				        	var dpHexCodes = [ "#66B92F", "#E67E22", "#F47564", "#337ab7", "#d9534f", "#F0D65E", "#cc99ff", "#57BCD9", "#006600", "#FF4AFF" ];
  				            if(value>9 || (value==1 && dpType==2)){
  				            	this.currentIndex = 1;
  				            }
  				        	return dpHexCodes[this.currentIndex-1];
  				        }
  				    }
  				),
    	    listeners:{
    	    	afterrender : 'onDataPointAfterRender'
    	    }
    		
    	}
    ]
 
});