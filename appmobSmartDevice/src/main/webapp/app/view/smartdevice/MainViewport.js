Ext.define('Appmob.view.smartdevice.MainViewport', {
		extend:'Ext.container.Viewport',
		bodyPadding : 5,
		closable : false,
		xtype : 'mainViewport',
		id:'mainViewport',
		autoDestroy : true,
		requires : ['Ext.ux.SlidingPager','Appmob.view.fw.DateRange','Appmob.view.smartdevice.login.Login'],
		layout:'fit',
		title:'Viewport',
		items:[{
			xtype:'login'
		}]
});