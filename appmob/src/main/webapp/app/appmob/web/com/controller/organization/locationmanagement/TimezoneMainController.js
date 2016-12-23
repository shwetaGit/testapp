Ext.define('Appmob.appmob.web.com.controller.organization.locationmanagement.TimezoneMainController', {
     extend: 'Appmob.view.fw.frameworkController.FrameworkViewController',
     alias: 'controller.TimezoneMainController',
     /*********************Main Controller Functions*********************************/
     pagingSize: 50,
     init: function() {
          var but = this.view.down('#resetFormButton');
          but.up('form').reset();
          if (this.view.disableDB != null && this.view.disableDB) {
               this.view.down('#saveFormButton').setText('Update');
               this.hideDataBrowser();
          } else {
               this.view.down('#saveFormButton').setText('Save');
               this.renderTreeGridData(true, null);
          }
     },
     onTreeRefreshClick: function(event, toolEl, panelHeader) {
          this.renderTreeGridData();
     },
     onGridRefreshClick: function(event, toolEl, panelHeader) {
          this.renderTreeGridData();
     },
     refreshMainForm: function(but, data, method) {
          var formPanel = this.view.down('#TimezoneForm');
          this.updateFormUI(formPanel, 'Save', true); /** Adding data to tree and grid */
          if (data != null) {
               var grid = this.view.down('#TimezoneGrid');
               var tree = this.view.down('#TimezoneTree');
               if (method == 'PUT') {
                    if (!(data instanceof Object)) {
                         var data = {
                              'findKey': data
                         };
                    }
                    data = this.findRecordById(this.view.restURL, data);
                    grid.getSelectionModel().selected.items[0].data = data;
                    grid.reconfigure();
                    var node = this.findChild(tree.getRootNode(), 'primaryKey', data.primaryKey);
                    if (node) {
                         node.set('text', data.primaryDisplay);
                         node.bConfig = data;
                         tree.reconfigure();
                    }
               }
          } else {
               if (method == 'DELETE') {
                    this.renderTreeGridData();
               }
          }
          if (but != null) {
               this.resetForm(but);
          } else {
               var but = this.view.down('#resetFormButton');
               this.resetForm(but);
          }
     },
     hideDataBrowser: function() {
          var form = this.view.down('#TimezoneForm');
          var grid = this.view.down('#TimezoneGrid');
          var tree = this.view.down('#TimezoneTreeContainer');
          grid.setHidden(true);
          this.view.down('#addNewForm').destroy();
          tree.setHidden(true);
          if (this.view.primaryKey != null) {
               this.findById(this.view.primaryKey);
          }
     },
     renderTreeGridData: function(fromInit, responseData) {
          if (responseData == null) {
               this.setGridConfig();
          } else if (responseData.response.success) {
               var data = responseData.response.data;
               var tree = this.view.down('#TimezoneTree');
               var rootNode = tree.getRootNode();
               rootNode.removeAll();
               for (var counter = 0; counter < data.length; counter++) {
                    var childNode = {
                         text: data[counter].primaryDisplay,
                         bConfig: data[counter],
                         leaf: true,
                         icon: 'images/table_icon.png'
                    };
                    rootNode.appendChild(childNode);
               }
               tree.getStore().sort('text', 'ASC');
               this.setGridData(data);
          } else if (!sessionTimeOutFlag) {
               Ext.Msg.alert('Server Response', responseData.response.message);
          }
     },
     /********************************Tree Controller Functions**********************************/
     setTreeData: function(data) {
          var tree = this.view.down('#TimezoneTree');
          var rootNode = tree.getRootNode();
          rootNode.removeAll();
          for (var counter = 0; counter < data.length; counter++) {
               var childNode = {
                    text: data[counter].primaryDisplay,
                    bConfig: data[counter],
                    leaf: true,
                    icon: 'images/table_icon.png'
               };
               rootNode.appendChild(childNode);
          }
          tree.getStore().sort('text', 'ASC');
     },
     updateTreeWithPageData: function(records) {
          var data = [];
          var limitedRecords = [];
          if (records.length <= this.pagingSize) {
               limitedRecords = records;
          } else {
               limitedRecords = Ext.Array.slice(records, 0, this.pagingSize);
          }
          for (var counter = 0; counter < limitedRecords.length; counter++) {
               data.push(Ext.clone(limitedRecords[counter]));
          }
          this.setTreeData(data);
     },
     onFilterClick: function(but, evt) {
          var me = this;
          var currentObject = this.getView();
          var form = but.up('form');
          if (!form.isValid()) {
               return;
          }
          var searchData = this.getData(form);
          Ext.Ajax.request({
               url: 'secure' + currentObject.restURL + '/search',
               method: 'POST',
               maskEnable: true,
               maskEle: currentObject,
               maskMsg: 'Filtering data ...',
               view: currentObject,
               jsonData: Ext.JSON.encode(searchData),
               success: function(response, opts) {
                    var responseText = Ext.JSON.decode(response.responseText);
                    if (responseText.response.success) {
                         me.renderTreeGridData(false, responseText);
                    } else if (!sessionTimeOutFlag) {
                         me.responseHandler(responseText);
                    }
               },
               failure: function(response, eopts) {
                    if (!sessionTimeOutFlag) {
                         var responseText = Ext.JSON.decode(response.responseText);
                         me.responseHandler(responseText);
                    }
               }
          });
     },
     treeClick: function(me, record, item, index, e, eOpts) {
          if (record.data.leaf) {
               var primaryKey = record.data.bConfig.primaryKey;
               var gridPanel = this.view.up().down('#TimezoneGrid');
               var foundRecord = gridPanel.store.findRecord('primaryKey', primaryKey);
               if (gridPanel && foundRecord) {
                    gridPanel.setSelection(foundRecord);
               }
               var formPanel = this.getView().up().down('#TimezoneForm');
               jsonData = {
                    'findKey': primaryKey
               }
               var data = this.findRecordById(this.view.restURL, jsonData);
               this.modifyComponents(data, formPanel);
               this.updateFormUI(formPanel, 'Update', true);
          }
     },
     /********************************Grid Controller Functions***********************************/
     onGridItemClick: function(me, record, item, index, e, eOpts) {
          var primaryKey = record.data.primaryKey; /** Finding record by id */
          jsonData = {
               'findKey': primaryKey
          };
          var data = this.findRecordById(this.view.restURL, jsonData);
          var treePanel = this.view.up().up().down('#TimezoneTree');
          var foundNode = this.findChild(treePanel.getRootNode(), 'primaryKey', primaryKey);
          if (treePanel && foundNode) {
               treePanel.setSelection(foundNode);
          } else {
               var formPanel = this.getView().up().down('#TimezoneForm');
               formPanel.down('#saveFormButton').setText('Update');
               this.modifyComponents(data, formPanel);
               this.updateFormUI(formPanel, 'Update', true);
          }
     },
     updateFormUI: function(form, status, butText) {
          for (var counter = 0; counter < form.items.items.length; counter++) {
               var item = form.items.items[counter];
               if (item.xtype == 'form' || item.xtype == 'panel') {
                    this.updateFormUI(item, status);
               }
               if (item.isCompositeKey) {
                    if (status == 'Save') {
                         item.setDisabled(false);
                    } else if (status == 'Update') {
                         item.setDisabled(true);
                    }
               }
          }
          if (butText) {
               if (status == 'Save') {
                    form.down('#saveFormButton').setText('Save');
               } else if (status == 'Update') {
                    form.down('#saveFormButton').setText('Update');
               }
          }
     },
     renderFormValue: function(val, me) {
          store = this.view.up().down('#TimezoneForm').down('#' + me.column.dataIndex + '').store;
          if (store.data.length == 0) {
               return '';
          }
          var displayValue = store.findRecord('primaryKey', val).data.primaryDisplay;
          return displayValue != null ? displayValue : '';
     },
     setGridData: function(data) {
          var gridStore = this.view.down('#TimezoneGrid').store;
          gridStore.removeAll();
          gridStore.setData(data);
          gridStore.sort('primaryDisplay', 'ASC');
     },
     setGridConfig: function() {
          var url = this.getView().restURL;
          var grid = this.view.down('#TimezoneGrid');
          var currentObject = this;
          var gridStore = Ext.create('Ext.data.Store', {
               model: 'Appmob.appmob.shared.com.model.organization.locationmanagement.TimezoneModel',
               autoLoad: {
                    params: {
                         limit: this.pagingSize
                    }
               },
               pageSize: this.pagingSize,
               proxy: {
                    type: 'ajax',
                    api: {
                         'read': 'secure' + url + '/findPageWiseData',
                    },
                    actionMethods: {
                         'read': 'GET'
                    },
                    headers: {
                         'Content-Type': 'application/json',
                    },
                    reader: {
                         'type': 'json',
                         'rootProperty': 'response.data',
                         'totalProperty': 'response.totalSize'
                    },
                    enablePaging: true
               },
               sorters: [{
                    property: 'primaryDisplay',
                    direction: 'ASC'
               }],
               listeners: {
                    load: function(storeObj, records, successful, eOpts) {
                         var tree = currentObject.view.down('#TimezoneTree');
                         var searchBox = currentObject.view.down('#searchBox');
                         searchBox.reset();
                         currentObject.onTriggerfieldChange(searchBox);
                         var formattedRecords = [];
                         for (var index = 0; index < records.length; index++) {
                              formattedRecords[index] = records[index].data;
                         }
                         currentObject.updateTreeWithPageData(formattedRecords);
                    }
               }
          });
          var previousPagingBar = grid.down('pagingtoolbar');
          if (previousPagingBar) {
               grid.dockedItems.remove(previousPagingBar);
          }
          grid.addDocked({
               xtype: 'pagingtoolbar',
               store: gridStore,
               dock: 'bottom',
               itemId: 'pagingtoolbar',
               displayInfo: true,
               displayMsg: '{0}-{1} of {2}',
               doRefresh: function(refreshBtn, e) {
                    var grid = this.up('grid');
                    var gridTools = grid.tools;
                    var sortedTool = Ext.Array.filter(gridTools, function(item, index, gridTools) {
                         if (item.type == 'refresh') {
                              return true;
                         }
                    });
                    if (sortedTool.length > 0) {
                         var refreshTool = sortedTool[0];
                         refreshTool.fireEvent('click');
                    }
               }
          });
          grid.setStore(gridStore);
     },
     findChild: function(node, key, value) {
          var dNode = node;
          if (node.data.bConfig != null && node.data.bConfig[key] == value) {
               return node;
          } else if (node.childNodes) {
               for (var counter = 0; counter < node.childNodes.length; counter++) {
                    dNode = this.findChild(node.childNodes[counter], key, value);
                    if (dNode) {
                         return dNode;
                    }
               }
          } else {
               return null;
          }
     },
     onDeleteActionColumnClickMainGrid: function(grid, rowIndex) {
          var recordId = grid.store.data.items[rowIndex].data.primaryKey;
          me = this;
          Ext.MessageBox.confirm({
               title: 'Confirm',
               msg: 'Delete Record',
               buttons: Ext.MessageBox.YESNO,
               fn: function(clickedButtonId) {
                    if (clickedButtonId == 'yes') {
                         me.deleteRecord(recordId);
                    }
               },
               animateTarget: this,
               icon: Ext.MessageBox.QUESTION
          })
     },
     deleteRecord: function(recordId) {
          var me = this;
          var restURL = this.view.restURL;
          var url = 'secure' + restURL + '/' + recordId;
          var jsonData = {};
          Ext.Ajax.request({
               url: url,
               method: 'DELETE',
               jsonData: jsonData,
               success: function(response, opts) {
                    var responseText = Ext.JSON.decode(response.responseText);
                    if (responseText.response.success) {
                         Ext.Msg.alert('Server Response', responseText.response.message);
                         me.refreshMainForm(null, null, opts.method);
                    } else if (!sessionTimeOutFlag) {
                         me.responseHandler(responseText);
                    }
               },
               failure: function(response, scope) {
                    me.addCommunicationLog(response, false);
                    if (!sessionTimeOutFlag) {
                         var responseText = Ext.JSON.decode(response.responseText);
                         me.responseHandler(responseText);
                    }
               }
          });
     },
     /********************************Form Controller Functions***********************************/
     findById: function(primaryKey) {
          var me = this;
          var restURL = this.view.restURL;
          Ext.Ajax.request({
               url: 'secure' + restURL + '/findById',
               method: 'POST',
               jsonData: {
                    'findKey': primaryKey
               },
               success: function(response, opts) {
                    var responseText = Ext.JSON.decode(response.responseText);
                    if (responseText.response.success) {
                         me.loadData(responseText);
                    } else if (!sessionTimeOutFlag) {
                         me.responseHandler(responseText);
                    }
               },
               failure: function(response, opts) {
                    if (!sessionTimeOutFlag) {
                         var responseText = Ext.JSON.decode(response.responseText);
                         me.responseHandler(responseText);
                    }
               }
          });
     },
     loadData: function(responseText) {
          var formPanel = this.getView().up().down('#TimezoneForm');
          this.modifyComponents(responseText.response.data, formPanel);
     },
     renderFormValue: function(value, metaData, record, row, col, store, gridView) {
          try {
               var comboStore = this.getView().down('#' + metaData.column.dataIndex).getStore();
               var pos = comboStore.findExact('primaryKey', value);
               return comboStore.getAt(pos).data.primaryDisplay;
          } catch (e) {
               return value;
          }
     },
     resetForm: function(but) {
          var form = but.up('form');
          form.down('#saveFormButton').setText('Save');
          form.reset();
          var grid = this.view.down('#TimezoneGrid');
          var tree = this.view.down('#TimezoneTree');
          tree.setSelection();
          grid.setSelection();
     },
     saveForm: function(but, evt) {
          var form = but.up('form');
          if (!form.isValid()) {
               if (this.validateAndShowErrorMessages(but, form.items.items, true)) {
                    return;
               }
          }
          var jsonData = this.getData(form);
          var method;
          if (but.text == 'Save') {
               method = 'POST'
          } else if (but.text == 'Update') {
               method = 'PUT';
          }
          restURL = this.view.restURL;
          var me = this;
          Ext.Ajax.request({
               url: 'secure' + restURL + '/',
               but: but,
               method: method,
               maskEnable: true,
               maskEle: form,
               maskMsg: 'Data saving ...',
               jsonData: jsonData,
               success: function(response, opts) {
                    var responseText = Ext.JSON.decode(response.responseText);
                    if (responseText.response.success) {
                         Ext.Msg.alert('Server Response', responseText.response.message);
                         if (me.view.disableDB != null && me.view.disableDB) {
                              if (me.view.drillDownWindow) {
                                   me.view.drillDownWindow.close();
                              } else {
                                   me.view.close();
                              }
                         } else {
                              me.refreshMainForm(but, responseText.response.data, opts.method);
                         }
                    } else if (!sessionTimeOutFlag) {
                         me.responseHandler(responseText);
                    }
               },
               failure: function(response, scope) {
                    me.addCommunicationLog(response, false);
                    if (!sessionTimeOutFlag) {
                         var responseText = Ext.JSON.decode(response.responseText);
                         me.responseHandler(responseText);
                    }
               }
          });
     },
});