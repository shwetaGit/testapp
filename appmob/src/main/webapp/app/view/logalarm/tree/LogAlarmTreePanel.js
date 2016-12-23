/**
 * 
 */
Ext.define('Appmob.view.logalarm.tree.LogAlarmTreePanel', {
	extend : 'Ext.panel.Panel',
	xtype : 'logAlarmTreePanel',
	requires : [ 'Appmob.view.logalarm.tree.LogAlarmTreePanelController',
			'Ext.tree.*', 'Ext.data.*','Ext.Action' ],
	controller : 'logAlarmTreePanelController',
	title : 'Log Alarms',

	defaults : {
		anchor : '100% 100%',
		margin : 10,
	},
	initComponent : function() {
		var group = this.id + '-ddgroup';
		Ext.apply(this, {

			items : [ {
				xtype : 'treepanel',
				itemId : 'logModuleTree',
				useArrows : true,

				listeners : {
					itemclick : 'onItemClick'
				},
				store : {
					root : {
						text : 'Log Alarms',
						icon : 'images/folder-database-icon.png',
						expanded : true,
						children : []
					},
					
					folderSort : true,
					sorters : [ {
						property : 'text',
						direction : 'ASC'
					} ]
				},
				rootVisible : false,
				viewConfig : {
					plugins : {
						ptype : 'treeviewdragdrop',
						ddGroup : group,
						appendOnly : true,
						sortOnDrop : true,
						containerScroll : true
					}
					/*listeners : {
						itemcontextmenu : 'onRightClick'
					}*/
				}
			} ]
		});
		this.callParent();
	}
});