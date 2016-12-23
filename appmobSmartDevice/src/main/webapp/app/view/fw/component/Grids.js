Ext.define('Appmob.view.fw.component.Grids',{
	extend:'Ext.grid.Panel',
	xtype:'grids',

	dockedItems: [{
		xtype: 'pagingtoolbar',
		dock: 'bottom',
		displayInfo: true,
		doRefresh : function(){
			var pageData = this.getPageData();
			var grid = this.up("grid");
			grid.fireEventArgs("afterrender",[grid]);
			grid.store.loadPage(pageData.currentPage);
		}
	}],

	getValues : function() {

		var data = [];
		var gridStoreData = this.getStore().getData().items;
		if(gridStoreData && gridStoreData.length>0)
		{
			for (var x = 0; x < gridStoreData.length; x++) {

				for(var counter in gridStoreData[x].data){

					if(gridStoreData[x].data[counter] === "" ){
						delete gridStoreData[x].data[counter];	
					}
				}

				delete gridStoreData[x].data["id"];	
				data.push(gridStoreData[x].data);
			}
		}
		return data;
	},

	getValue : function() {
		return this.getValues();
	},

	setValue: function(data) {
		this.setData(data);
	},

	setData : function(data) {
		var store = this.store;
		store.setData(data);
	},

	setGridData :function(data) {

		var gridpanel = this;
		var gridstore = gridpanel.hasOwnProperty("customStore") ?gridpanel.customStore:gridpanel.store;
		var pageSize =  gridpanel.hasOwnProperty("pageSize") ?gridpanel.pageSize : 30;

		var localstore = Ext.create('Ext.data.Store', {
			model:gridstore.model,
			data:data,
			proxy: {
				type: 'memory',
				enablePaging: true,
			},
			pageSize: pageSize
		});

		if(this.hasOwnProperty("customStore")){

			var customStore = gridpanel.customStore;
			var remotestore = Ext.create('Ext.data.Store', {
				model:customStore.model,
				autoLoad: true,
				proxy: {
					type: "ajax",
					url: customStore.proxy.url,	
					actionMethods: customStore.proxy.actionMethods,
					headers: customStore.proxy.headers,
					extraParams: customStore.proxy.extraParams,
					reader: customStore.proxy.reader
				},
				listeners: {
					load: function() {
						localstore.getProxy().setData(remotestore.getRange());
						localstore.load();
					}
				}
			});
		}

		var pagingtoolbar = gridpanel.down('pagingtoolbar');
		pagingtoolbar.setStore(localstore);
		gridpanel.setStore(localstore);

	},

	listeners : {
		afterrender : function(gridpanel) {
			if(gridpanel.hasOwnProperty("customStore")) {
				this.setGridData([]);
			}
		}
	}

});