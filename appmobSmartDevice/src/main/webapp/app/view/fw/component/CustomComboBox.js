Ext.define('Appmob.view.fw.component.CustomComboBox', {
		extend:'Ext.form.field.ComboBox',
		xtype:'customcombobox',
		
		listConfig:{
			minWidth:350
		},
		pickerPageSize: 20,
		setComboData :function(data) {
			
			var combobox = this;
			var combostore = combobox.hasOwnProperty("customStore") ?combobox.customStore:combobox.store;
			var enablePagingTool = combobox.enablePagingTool;
			var pageSize = combobox.hasOwnProperty("pickerPageSize") ?combobox.pickerPageSize : 100;
			var sorters = combostore.hasOwnProperty("sorters") ?combostore.sorters : undefined;

			var localstore = Ext.create('Ext.data.Store', {
				model:combostore.model,
				data:data,
				proxy: {
					type: 'memory',
					enablePaging: (enablePagingTool)?enablePagingTool:false,
				}
			});

			if(enablePagingTool && enablePagingTool == true){
					combobox.pageSize = pageSize;
			}

			if(this.hasOwnProperty("customStore")){

				var customStore = combobox.customStore;
				var remotestore = Ext.create('Ext.data.Store', {
					model:customStore.model,
					autoLoad: true,
					sorters: (sorters ?sorters :undefined),
					
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
			
			combobox.setStore(localstore);
		},

		listeners : {
			afterrender : function(combobox) {
				if(combobox.hasOwnProperty("customStore")) {
					this.setComboData([]);
				}
			}
		}
});