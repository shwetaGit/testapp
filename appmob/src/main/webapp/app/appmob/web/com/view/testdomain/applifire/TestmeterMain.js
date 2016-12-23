Ext.define('Appmob.appmob.web.com.view.testdomain.applifire.TestmeterMain', {
     "xtype": "testmeterMainView",
     "extend": "Ext.tab.Panel",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "TestmeterMainController",
     "restURL": "/Testmeter",
     "defaults": {
          "split": true
     },
     "requires": ["Appmob.appmob.shared.com.model.testdomain.applifire.TestmeterModel", "Appmob.appmob.web.com.controller.testdomain.applifire.TestmeterMainController", "Appmob.appmob.shared.com.viewmodel.testdomain.applifire.TestmeterViewModel"],
     "communicationLog": [],
     "tabPosition": "bottom",
     "items": [{
          "title": "Data Browser",
          "layout": "border",
          "defaults": {
               "split": true
          },
          "autoScroll": false,
          "customWidgetType": "vdBorderLayout",
          "items": [{
               "xtype": "tabpanel",
               "customWidgetType": "vdTabLayout",
               "margin": "5 0 5 5",
               "border": 1,
               "style": {
                    "borderColor": "#f6f6f6",
                    "borderStyle": "solid",
                    "borderWidth": "1px"
               },
               "displayName": "testmeter",
               "name": "TestmeterTreeContainer",
               "itemId": "TestmeterTreeContainer",
               "restURL": "/Testmeter",
               "autoScroll": false,
               "collapsible": true,
               "titleCollapse": true,
               "collapseMode": "header",
               "collapsed": false,
               "items": [{
                    "xtype": "treepanel",
                    "customWidgetType": "vdTree",
                    "title": "Browse",
                    "name": "entityTreePanel",
                    "useArrows": true,
                    "rootVisible": false,
                    "itemId": "TestmeterTree",
                    "listeners": {
                         "select": "treeClick"
                    },
                    "tbar": [{
                         "xtype": "triggerfield",
                         "customWidgetType": "vdTriggerField",
                         "emptyText": "Search",
                         "itemId": "searchBox",
                         "triggerCls": "",
                         "listeners": {
                              "change": "onTriggerfieldChange",
                              "buffer": 250
                         }
                    }, "->", {
                         "xtype": "tool",
                         "type": "refresh",
                         "tooltip": "Refresh Tree Data",
                         "handler": "onTreeRefreshClick"
                    }]
               }, {
                    "title": "Advance Search",
                    "xtype": "form",
                    "customWidgetType": "vdFormpanel",
                    "itemId": "queryPanel",
                    "buttons": [{
                         "text": "Filter",
                         "handler": "onFilterClick",
                         "name": "filterButton"
                    }],
                    "items": []
               }],
               "region": "west",
               "width": "20%"
          }, {
               "region": "center",
               "layout": "border",
               "defaults": {
                    "split": true
               },
               "customWidgetType": "vdBorderLayout",
               "items": [{
                    "customWidgetType": "vdFormpanel",
                    "xtype": "form",
                    "displayName": "testmeter",
                    "title": "testmeter",
                    "name": "Testmeter",
                    "itemId": "TestmeterForm",
                    "bodyPadding": 10,
                    "items": [{
                         "name": "metercode",
                         "itemId": "metercode",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "metercode",
                         "margin": "5 5 5 5",
                         "fieldLabel": "metercode<font color='red'> *<\/font>",
                         "fieldId": "7702E5FC-FF01-420E-AD0B-179F99D7BAB2",
                         "minLength": "1",
                         "maxLength": "256",
                         "hidden": true,
                         "value": "",
                         "bindable": "metercode"
                    }, {
                         "name": "meterArea",
                         "itemId": "meterArea",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "Areaname",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Areaname<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "BC8F0F43-5614-436D-9A14-F9DC0DB9A76E",
                         "minLength": "1",
                         "maxLength": "256",
                         "bindable": "meterArea",
                         "columnWidth": 0.5
                    }, {
                         "name": "metertype",
                         "itemId": "metertype",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "metertype",
                         "margin": "5 5 5 5",
                         "fieldLabel": "metertype<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "A1F60968-F68D-4E5D-8D63-434324CCBEB1",
                         "minLength": "1",
                         "maxLength": "256",
                         "bindable": "metertype",
                         "columnWidth": 0.5
                    }, {
                         "name": "vendor",
                         "itemId": "vendor",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "vendor",
                         "margin": "5 5 5 5",
                         "fieldLabel": "vendor<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "AA54B7F4-B3D2-4ED2-9FC4-74D01B91762E",
                         "minLength": "1",
                         "maxLength": "256",
                         "bindable": "vendor",
                         "columnWidth": 0.5
                    }, {
                         "name": "price",
                         "itemId": "price",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "price",
                         "margin": "5 5 5 5",
                         "fieldLabel": "price<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "03418593-936D-4142-9112-A4ACF1F0CB92",
                         "minValue": "-9223372036854775000",
                         "maxValue": "9223372036854775000",
                         "bindable": "price",
                         "columnWidth": 0.5
                    }, {
                         "name": "versionId",
                         "itemId": "versionId",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "versionId",
                         "margin": "5 5 5 5",
                         "value": "-1",
                         "fieldLabel": "versionId",
                         "fieldId": "69D01B68-5094-458F-8387-8A9784319A10",
                         "bindable": "versionId",
                         "hidden": true
                    }, {
                         "name": "activeStatus",
                         "itemId": "activeStatus",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "activeStatus",
                         "margin": "5 5 5 5",
                         "value": "1",
                         "fieldLabel": "activeStatus",
                         "fieldId": "634981A4-055A-483F-B0C9-0B6CEA754995",
                         "bindable": "systemInfo.activeStatus",
                         "hidden": true
                    }],
                    "layout": "column",
                    "defaults": {
                         "columnWidth": 0.5,
                         "labelAlign": "left",
                         "labelWidth": 200
                    },
                    "autoScroll": true,
                    "dockedItems": [{
                         "xtype ": "toolbar",
                         "customWidgetType": "vdBBar",
                         "dock": "bottom",
                         "ui": "footer",
                         "isToolBar": true,
                         "isDockedItem": true,
                         "parentId": 1,
                         "customId": 27,
                         "layout": {
                              "type": "hbox"
                         },
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill",
                              "parentId": 27,
                              "customId": 560
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "saveFormButton",
                              "margin": 5,
                              "text": "Save",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "saveFormButton",
                              "parentId": 27,
                              "customId": 561,
                              "listeners": {
                                   "click": "saveForm"
                              }
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "resetFormButton",
                              "margin": 5,
                              "text": "Reset",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "resetFormButton",
                              "parentId": 27,
                              "customId": 562,
                              "listeners": {
                                   "click": "resetForm"
                              }
                         }]
                    }],
                    "tools": [{
                         "type": "help",
                         "tooltip": "Console",
                         "handler": "onConsoleClick"
                    }, {
                         "type": "refresh",
                         "tooltip": "Refresh Tab",
                         "handler": "init"
                    }],
                    "extend": "Ext.form.Panel",
                    "region": "center"
               }, {
                    "xtype": "gridpanel",
                    "customWidgetType": "vdGrid",
                    "displayName": "testmeter",
                    "title": "Details Grid",
                    "name": "TestmeterGrid",
                    "itemId": "TestmeterGrid",
                    "restURL": "/Testmeter",
                    "columns": [{
                         "header": "metercode",
                         "dataIndex": "metercode",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "primaryDisplay",
                         "dataIndex": "primaryDisplay",
                         "hidden": true
                    }, {
                         "header": "primaryKey",
                         "dataIndex": "primaryKey",
                         "hidden": true
                    }, {
                         "header": "Areaname",
                         "dataIndex": "meterArea",
                         "flex": 1
                    }, {
                         "header": "metertype",
                         "dataIndex": "metertype",
                         "flex": 1
                    }, {
                         "header": "vendor",
                         "dataIndex": "vendor",
                         "flex": 1
                    }, {
                         "header": "price",
                         "dataIndex": "price",
                         "flex": 1
                    }, {
                         "header": "createdBy",
                         "dataIndex": "createdBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "createdDate",
                         "dataIndex": "createdDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedBy",
                         "dataIndex": "updatedBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedDate",
                         "dataIndex": "updatedDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "versionId",
                         "dataIndex": "versionId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "activeStatus",
                         "dataIndex": "activeStatus",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "txnAccessCode",
                         "dataIndex": "txnAccessCode",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "xtype": "actioncolumn",
                         "customWidgetType": "vdActionColumn",
                         "sortable": false,
                         "menuDisable": true,
                         "items": [{
                              "icon": "images/delete.gif",
                              "tooltip": "Delete Record",
                              "handler": "onDeleteActionColumnClickMainGrid"
                         }]
                    }],
                    "listeners": {
                         "itemclick": "onGridItemClick"
                    },
                    "tools": [{
                         "type": "refresh",
                         "tooltip": "Refresh Grid Data",
                         "listeners": {
                              "click": "onGridRefreshClick"
                         },
                         "hidden": true
                    }],
                    "collapsible": true,
                    "titleCollapse": true,
                    "collapseMode": "header",
                    "region": "south",
                    "height": "40%"
               }]
          }]
     }, {
          "title": "Add New",
          "itemId": "addNewForm",
          "layout": "border",
          "customWidgetType": "vdBorderLayout",
          "autoScroll": false,
          "items": [{
               "customWidgetType": "vdFormpanel",
               "xtype": "form",
               "displayName": "testmeter",
               "title": "testmeter",
               "name": "Testmeter",
               "itemId": "TestmeterForm",
               "bodyPadding": 10,
               "items": [{
                    "name": "metercode",
                    "itemId": "metercode",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "metercode",
                    "margin": "5 5 5 5",
                    "fieldLabel": "metercode<font color='red'> *<\/font>",
                    "fieldId": "7702E5FC-FF01-420E-AD0B-179F99D7BAB2",
                    "minLength": "1",
                    "maxLength": "256",
                    "hidden": true,
                    "value": "",
                    "bindable": "metercode"
               }, {
                    "name": "meterArea",
                    "itemId": "meterArea",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "Areaname",
                    "margin": "5 5 5 5",
                    "fieldLabel": "Areaname<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "BC8F0F43-5614-436D-9A14-F9DC0DB9A76E",
                    "minLength": "1",
                    "maxLength": "256",
                    "bindable": "meterArea",
                    "columnWidth": 0.5
               }, {
                    "name": "metertype",
                    "itemId": "metertype",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "metertype",
                    "margin": "5 5 5 5",
                    "fieldLabel": "metertype<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "A1F60968-F68D-4E5D-8D63-434324CCBEB1",
                    "minLength": "1",
                    "maxLength": "256",
                    "bindable": "metertype",
                    "columnWidth": 0.5
               }, {
                    "name": "vendor",
                    "itemId": "vendor",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "vendor",
                    "margin": "5 5 5 5",
                    "fieldLabel": "vendor<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "AA54B7F4-B3D2-4ED2-9FC4-74D01B91762E",
                    "minLength": "1",
                    "maxLength": "256",
                    "bindable": "vendor",
                    "columnWidth": 0.5
               }, {
                    "name": "price",
                    "itemId": "price",
                    "xtype": "numberfield",
                    "customWidgetType": "vdNumberfield",
                    "displayName": "price",
                    "margin": "5 5 5 5",
                    "fieldLabel": "price<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "03418593-936D-4142-9112-A4ACF1F0CB92",
                    "minValue": "-9223372036854775000",
                    "maxValue": "9223372036854775000",
                    "bindable": "price",
                    "columnWidth": 0.5
               }, {
                    "name": "versionId",
                    "itemId": "versionId",
                    "xtype": "numberfield",
                    "customWidgetType": "vdNumberfield",
                    "displayName": "versionId",
                    "margin": "5 5 5 5",
                    "value": "-1",
                    "fieldLabel": "versionId",
                    "fieldId": "69D01B68-5094-458F-8387-8A9784319A10",
                    "bindable": "versionId",
                    "hidden": true
               }, {
                    "name": "activeStatus",
                    "itemId": "activeStatus",
                    "xtype": "numberfield",
                    "customWidgetType": "vdNumberfield",
                    "displayName": "activeStatus",
                    "margin": "5 5 5 5",
                    "value": "1",
                    "fieldLabel": "activeStatus",
                    "fieldId": "634981A4-055A-483F-B0C9-0B6CEA754995",
                    "bindable": "systemInfo.activeStatus",
                    "hidden": true
               }],
               "layout": "column",
               "defaults": {
                    "columnWidth": 0.5,
                    "labelAlign": "left",
                    "labelWidth": 200
               },
               "autoScroll": true,
               "dockedItems": [{
                    "xtype ": "toolbar",
                    "customWidgetType": "vdBBar",
                    "dock": "bottom",
                    "ui": "footer",
                    "isToolBar": true,
                    "isDockedItem": true,
                    "parentId": 1,
                    "customId": 27,
                    "layout": {
                         "type": "hbox"
                    },
                    "items": [{
                         "xtype": "tbfill",
                         "customWidgetType": "vdTbFill",
                         "parentId": 27,
                         "customId": 560
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "saveFormButton",
                         "margin": 5,
                         "text": "Save",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "saveFormButton",
                         "parentId": 27,
                         "customId": 561,
                         "listeners": {
                              "click": "saveForm"
                         }
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "resetFormButton",
                         "margin": 5,
                         "text": "Reset",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "resetFormButton",
                         "parentId": 27,
                         "customId": 562,
                         "listeners": {
                              "click": "resetForm"
                         }
                    }]
               }],
               "tools": [{
                    "type": "help",
                    "tooltip": "Console",
                    "handler": "onConsoleClick"
               }, {
                    "type": "refresh",
                    "tooltip": "Refresh Tab",
                    "handler": "init"
               }],
               "extend": "Ext.form.Panel",
               "region": "center"
          }]
     }]
});