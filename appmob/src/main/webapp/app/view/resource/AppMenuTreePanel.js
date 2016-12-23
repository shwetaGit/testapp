Ext.define('Appmob.view.resource.AppMenuTreePanel', {
	extend : 'Ext.tree.Panel',
    xtype: 'appMenuTreePanel',
    requires: ['Appmob.view.resource.AppMenuTreePanelController'],    
    controller: 'appMenuTreePanelController',
    rootVisible:false,
    useArrows:true,
    lines  : false,
    cls:'appMenuTreePanelFocus',
//  autoScroll:true,
	listeners: {
         itemclick : function( currentObject, record, item, index, e, eOpts){
        	 this.getController().renderFormPanel(currentObject, record, item, index, e, eOpts);
         }
   	}     
});
