Ext.define('Appmob.view.fw.frameworkController.FrameworkViewController', {
    extend: 'Ext.app.ViewController',
    
    /** a global value specifying number of records shown per page of a Grid with paging toolbar */
    noOfRecordsPerGridPage : 30,
    
    /********************* Main Controller Functions *********************************/
    
    /** Grid reconfiguration */
    onGridRefreshClick : function(me, eOpts, but){
        var grid = but.up()
        grid.store.reload() ;
    },
    
    /** Find record by Id */
    findRecordById : function (restURL, primaryKey){
      scope = this.view;
    	var data = Ext.Ajax.request({
             async: false,
             url: 'secure' + restURL + '/findById',
             method: 'POST',
             jsonData: primaryKey,
             sender: scope,
             success: function(response, scope) {
                  scope.sender.controller.addCommunicationLog(response, true);
             },
             failure: function(response, scope) {
                  scope.sender.controller.addCommunicationLog(response, false);
             }
        }, scope);
    	
    	return responseData = Ext.JSON.decode(data.responseText).response.data;
   },
    
   /** To add server calls in communication array */
   addCommunicationLog: function(response, success, compRef) {
	   try {
		   if (success) {
			   var responseData = Ext.JSON.decode(response.responseText);
		   }	
		   if (compRef == null) {
			   responseObj = {
					   name: '',
					   status: response.status,
					   statusText: response.statusText,
					   success: success,
					   message: success ? responseData.response.message : 'Failed'
			   };
		   } else {
			   responseObj = {
					   name: (compRef.fieldLabel != null) ? compRef.fieldLabel : compRef.title,
                       status: response.status,
                       statusText: response.statusText,
                       success: success,
                       message: success ? responseData.response.message : 'Failed'
                  	};
           }
		   this.view.communicationLog.push(responseObj);
         } catch (addCommunicationLogException) {}
    },    
    
    /** To remove the unwanted code from final json creation */
    modifyData: function(config) {
         if (!config) {
              return null;
         }
         for (i in config) {
              if (config[i]) {
                   config[i] = config[i].valueOf();
                   if (typeof config[i] == 'object') {
                        this.modifyData(config[i]);
                   }
              }
         }
    },
    
    /** To find the child in tree by property and value */
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
    
    /********************************Form Controller Functions***********************************/

    /** ## To add the store to combo */
    assignComboData: function(component, data) {
         var decodedData = Ext.JSON.decode(data);
         var storeData = decodedData.response.data;
         
         component.setValue();          
         component.store.setData(storeData);
         component.getStore().sort('primaryDisplay', 'ASC');
    },
    
    /** ## To find all the combos from container and assign the data to each */ 
    assignAllComboData: function(container, itemId, data) {
         var allForms = container.query('form');
         for (var counter = 0; counter < allForms.length; counter++) {
              if (allForms[counter].down('#' + itemId) != null) {
                   container.controller.assignComboData(allForms[counter].down('#' + itemId), data);
              }
         }
    },
    
    /** To show the window with the server call log */
    onConsoleClick: function() {
         var window = Ext.create('Appmob.view.console.ConsoleWindow');
         var store = window.down('grid').store;
         store.loadData(this.view.communicationLog);
         window.show();
    },
    
    /** To delete the record from the grid in forms */
    onDeleteActionColumnClick: function(grid, rowIndex) {
         grid.store.removeAt(rowIndex);
         grid.refresh();
    },
    
    /** To find the records from store */
    fetchDataFromStore: function(store) {
         storeItems = store.data.items;
         obj = {};
         arr = [];
         for (counter in storeItems) {
              arr.push(storeItems[counter].data);
         }
         return obj['objArr'] = arr;
    },
    
    /** To get Modified Values from form as per back end requirement */
    modifyFormValues: function(values, form) {
    	for (var key in values) {
    		var component = form.down('#' + key);
    		if (component.xtype == 'customdatetimefield') {
    			if (component.getValues() != null) {
    				values[key] = component.getValues();
    			}
    		}
    		if (typeof values[key] === 'object') {
    			values[key] = this.modifyFormValues(values[key], form);
    		}
    	}
    	return values;
    },
    
    /** To set Values getting from Back end to form */
    setModifiedFormValues: function(values, form) {
    	for (var key in values) {
    		var component = form.down('#' + key);
    		if(component && component.xtype) {
    			var componentXtype = component.xtype;
    			if(componentXtype=='customdatetimefield' || componentXtype=='datefields') {
    				component.setValues(values[key]);
    				
    			}else if(componentXtype == "datefield") {
    				var dtData = values[key];

    				var dateValue = undefined;
    				if(dtData) {
    					var dateParts = dtData.split("-");
    					if(dateParts.length == 3) {
    						var day = dateParts[0];
    						var month = dateParts[1]-1;
    						var year = dateParts[2];

    						dateValue = new Date(year, month, day);
    					}
    				} 
    				component.setValue(dateValue);

    			} else if(componentXtype == "image" ) {
    				component.setSrc(values[key]);

    			} else if(componentXtype == 'customradiogroup' || componentXtype == 'customcheckboxgroup') {
    				component.setCmpValue(values[key]);	

    			} else if(componentXtype == "label") {
    				component.setText(values[key]);	

    			} else if(componentXtype == "fileupload") {
    				component.setText(values[key]); 

    			} else {
    				component.setValue(values[key]);	
    			} 
    		}
    		if(typeof values[key] === 'object') {
    			this.setModifiedFormValues(values[key], form);
    		}
    	}
    },
    
    /** For the tree filter */ 
    onTriggerfieldChange: function(me) {
         var tree = me.up().up();
         var v;
         try {
              v = new RegExp(me.getValue(), 'i');
              tree.store.filter({
                   filterFn: function(node) {
                        var children = node.childNodes,
                             len = children && children.length,
                             visible = node.isLeaf() ? v.test(node.get('text')) : false,
                             i;
                        for (i = 0; i < len && !(visible = children[i].get('visible')); i++);
                        return visible;
                   },
                   id: 'titleFilter'
              });
         } catch (e) {
              me.markInvalid('Invalid regular expression');
         }
    },
    
    /** To get all the items in view */
    getAllItems : function(items){
   	 
   	 	var allItems = new Array();
   	 	allItems = allItems.concat(items);
   	 
   	 	for (var counter = 0; counter < items.length; counter++) {
   	 		if(items[counter].items){
   	 			var moreItems = this.getAllItems(items[counter].items.items);
   	 			allItems = allItems.concat(moreItems);
   	 		}
		}
   	 	return allItems;
    },
    
    /** Checks if Components are Valid or Not,
     * 	Requires : An Array of Components,
     *  Returns : An Array of Invalid Components */
    validateComponents : function(componentArray) {
     	var invalidComponentArray = [];
     	for(counter=0; counter<componentArray.length; counter++) {
     		var component = componentArray[counter];
     		if(!component.isValid()) {
     			invalidComponentArray.push(component);
     		}
     	}
     	return invalidComponentArray;
    },
    
    /** To validate form components & show custom error messages for invalid components */
    validateAndShowErrorMessages : function(but, allComponents, autoUI) {
	    var invalidComponentsArray = this.validateComponents(allComponents);
	    if(invalidComponentsArray.length > 0) {
	    	var combinedErrorMessage = this.getCombinedErrorMessage(invalidComponentsArray, autoUI);

	    	var toast = new Ext.ToolTip({
	    		anchor : 'top',
	    		anchorToTarget : false,
	    		html : "<span style='color:red;'>" + combinedErrorMessage + "</span>",
	    		shrinkWrap : 3,
	    		collapseDirection : 'top',
	    		targetXY : but.el.getXY(),
	    		hideDelay : 800,
	    		closable : true
	    	});
	    	toast.show();
	    	toast.el.highlight();
	    	return true;
	    }
		return false;
    },
    
    getCombinedErrorMessage : function(invalidComponentsArray, autoUI) {
    	var combinedErrorMessage = "Please validate following fields : <br><br>";
    	var invalidComponentNo = 0;
    	
    	for(var i=0; i<invalidComponentsArray.length; i++) {
    		var invalidCmp = invalidComponentsArray[i];	
    		if(!invalidCmp.hidden) {
    			if(autoUI) {
    				combinedErrorMessage += (++invalidComponentNo) + ". <b>" + invalidCmp.displayName + "</b> " + (invalidCmp.errorMessage? (": "+invalidCmp.errorMessage) : "") + "<br>";
    			} else {
    				combinedErrorMessage += (++invalidComponentNo) + ". <b>" 
    				+ (invalidCmp.fieldLabel? invalidCmp.fieldLabel :(invalidCmp.boxLabel? invalidCmp.boxLabel :invalidCmp.name)) + "</b> <br>";
    			}
    		}
    	}

    	return combinedErrorMessage;
    },
    
    /** Final jsonData creation function */    
    getData : function(view, flag) {
    	if(flag==undefined)
    		flag=false;
    	var items = view.items.items;
      	var json = {};
      	for (var counter = 0; counter < items.length; counter++) {
			var item = items[counter];
			if(!flag || item.xtype!='grids') {
				if(item.isListPanel){
					if(item.bindable){
						json[item.bindable] = item.controller.getValues();
					} else{
						return item.controller.getValues();
					}
				} else {
					var xtype = item.xtype;
		   			if(xtype == 'panel' ||  xtype == "fieldset" || xtype == "form"){
		   				/** Skipping the form-with grid components */
		   				if(item.fwgDetailForm == true){
		   					continue;
		   				}
		   				var inner = this.getData(item, flag);
		   				if(item.bindable){
		   					json[item.bindable] = inner;
		   				}else{
		   					this.addJson(json, inner);
		   				}
		   			} else if(item.bindable){
		   				if(xtype == 'datefield' || xtype == 'hidden' || 
		   						xtype == 'textfield' || xtype == 'hiddenfield' || 
		   						xtype == 'textareafield'  || xtype == 'numberfield' || 
		   						xtype == 'customtimefield' || xtype == 'datefields' || 
		   						xtype == 'combo' || xtype == 'customcombobox' || 
		   						xtype == 'customcheckboxgroup' ||
		   						xtype == "grids" || xtype == 'fileupload' ||
		   						xtype == 'checkbox' || xtype == 'customdatetimefield' ||
		   						xtype == "radiogroup" || xtype == 'customradiogroup' ||
		   						xtype == 'displayfield' || xtype == 'timefield') {
		   					this.addBindableLevelData(json, item);
		   				}
		   			}
				}
			}
      	}
      	return json;
     },
     
     /** Make Target JSON Object from provided source Object
      * This method is used locally by getData() method to create inner json */
     addJson : function(targetJSON, source) {
   	  
       	for(key in source) {
       		if(targetJSON[key]) {
       			this.addJson(targetJSON[key], source[key]);
       		} else {
       			targetJSON[key] = source[key];
       		}
       	}
     },     
     
    /** Adding data to json as per bindable level */
     addBindableLevelData : function(level, item) {

    	 var bindArr = item.bindable.split('.');
    	 for (var counter = 0; counter < bindArr.length; counter++) {
    		 var array_element = bindArr[counter];
    		 if (counter == bindArr.length - 1) {
    			 if (item.xtype == "customcheckboxgroup" || item.xtype == "customradiogroup") {
    				 level[array_element] = item.getCmpValue();
    			 } else if (item.xtype == "customdatetimefield") {
    				 if (item.getSubmitValue() != "" && item.getSubmitValue() != null) {
    					 level[array_element] = item.getValues();
    				 }
    			 } else if (item.xtype == "datefield") {
    				 if (item.getSubmitValue() != "" && item.getSubmitValue() != null) {
    					 /** Appending time for datefield *//*
						var val = item.getSubmitValue() + " 0:0:0";
						var timezone = Ext.util.Cookies.get('XA_TID');
						level[array_element] = val + " "+ timezone;*/

    					 level[array_element] = item.getSubmitValue();
    				 }
    			 } else if(item.xtype == "customtimefield" || item.xtype == "timefield") {
    				 var value = item.getValue();
    				 if(value) {
    					 level[array_element] = value.getHours() +":"+ value.getMinutes() +":"+ value.getSeconds();
    				 }
    			 } else if(item.xtype == "datefields") {
    				 level[array_element] = item.getValues();			
    			 } else if(item.xtype == "fileupload") {
    				 level[array_element] = item.getValue();      
    			 } else if (item.xtype == "radiogroup") {
    				 level[array_element] = item.getValue()[array_element];
    				 //level[item.inputValue] = item.getValue()[item.inputValue];
    			 } else if ((item.xtype=="combo" || item.xtype=="customcombobox") && item.compositeKeyArray) {

    				 /** Finding the composite key reference and changeing the bind level in final json creation */
    				 for(var comp in item.compositeKeyArray) {
    					 var compositeKey = item.compositeKeyArray[comp];
    					 for(var key in compositeKey){
    						 level[compositeKey[key]] = item.getValue()[key];
    					 }							
    				 }
    			 } else {
    				 if (item.getValue() == null || item.getValue().length == 0) {
    					 continue;
    				 } else {
    					 level[array_element] = item.getValue();
    				 }
    			 }
    		 } else {
    			 if (!level[array_element]) {
    				 level[array_element] = {};
    			 }
    			 level = level[array_element];
    		 }
    	 }
     },
	
    /** Adding tab to main tab panel */
	addTab : function (component) {
		
		var tabs =  Ext.getCmp('appMainTabPanel');
		tabs.add(component);
		tabs.setActiveTab(component);
    },
     
    /** Create window */
    createWindow : function (component, title, sentWidth, sentHeight) {

        var width = (sentWidth? sentWidth : '70%');
        var height = (sentHeight? sentHeight : '70%');

        new Ext.Window({
            title: title, 
            layout: 'fit',
            width: width,
            height: height,
            closeAction: 'close',
            autoScroll: true,
            items: [component]
        }).show();
    },
		
	/** List panel data set  */
    modifyComponents : function(configData, view) {

    	if(view){
    		var items = view.items.items;
    	} else {
    		var items = this.view.items.items;
    	}

    	for (var itemCounter =0 ; itemCounter < items.length ; itemCounter++) {

    		var xtype = items[itemCounter].xtype;
    		if(items[itemCounter].isDynamic) {
    			var name = items[itemCounter].name;
    			var inputValue = items[itemCounter].inputValue;
    			var boxLabel = items[itemCounter].boxLabel;
    			var componentDataArr = configData[items[itemCounter].groupData]; 
    			if(Array.isArray(componentDataArr)){
    				for(var createCounter = 0; createCounter < componentDataArr.length ; createCounter++) {
    					var box = {boxLabel: componentDataArr[createCounter][boxLabel],
    							name : name,
    							bindValue : inputValue,
    							inputValue: componentDataArr[createCounter][inputValue]};

    					items[itemCounter].add(box);
    				}
    			}
    		} else if(xtype == "customdatetimefield" || xtype == "datefields") {

    			var dtData = configData[items[itemCounter]['bindable']];
    			items[itemCounter].setValues(dtData);

    		} else if(xtype == "customtimefield" || xtype == "timefield") {

    			var dtData = configData[items[itemCounter]['bindable']];
    			items[itemCounter].setValue(dtData);
    		}
    		else if(xtype == "datefield") {
    			var dtData = configData[items[itemCounter]['bindable']];

    			var dateValue = undefined;
    			if(dtData) {
    				var dateParts = dtData.split("-");
    				if(dateParts.length == 3) {
    					var day = dateParts[0];
    					var month = dateParts[1]-1;
    					var year = dateParts[2];

    					dateValue = new Date(year, month, day);
    				}
    			} 
    			items[itemCounter].setValue(dateValue);

    		} else if(xtype == "image" ) {

    			var img = items[itemCounter];
    			img.setSrc(configData[img.bindable]);

    		} else if (xtype == 'combo' && items[itemCounter].compositeKeyArray) {

    			var value = {};
    			for(var comp in items[itemCounter].compositeKeyArray){

    				var compositeKey = items[itemCounter].compositeKeyArray[comp];
    				for(var key in compositeKey){
    					value.key = configData[compositeKey[key]];
    				}
    			}
    			items[itemCounter].setValue(value);

    		}  else if(xtype == 'hidden' || xtype =='displayfield' ||
    				xtype == 'textfield' || xtype == 'hiddenfield' || 
    				xtype == 'textareafield' || xtype == 'numberfield' ||
    				xtype == 'combo' || xtype == 'checkbox' ||
    				xtype == "grids" || xtype == 'customcombobox') {

    			var bindable = items[itemCounter].bindable;

    			/** Skipping if bindable not found */
    			if(!bindable) {
    				continue;						
    			}

    			var bindArr = bindable.split('.');
    			var value = configData;
    			for (var counter = 0; counter < bindArr.length; counter++) {
    				value = value[bindArr[counter]];
    			}
    			items[itemCounter].setValue(value);	

    		} else if(xtype == 'customradiogroup' || xtype == 'customcheckboxgroup') {
    			items[itemCounter].setCmpValue(value);	

    		} else if(xtype == "label") {
    			items[itemCounter].setText(configData[items[itemCounter]['bindable']]);	

    		} else if(xtype == "fileupload") {
    			items[itemCounter].setText(configData[items[itemCounter]['bindable']]); 
    		} 
    	}		

    	for(var innerCounter in items){
    		if(items[innerCounter].items){
    			this.modifyComponents(configData, items[innerCounter]);
    		}
    	}
    },	
	
    setRecordDataToForm : function(configData, view) {
        var items = undefined;

        if(view.items.get(0).items == undefined) {
            items = view.items.items;
        } else {
            items = view.items.get(0).items.get(0).items.items;
        }

        for (var itemCounter =0 ; itemCounter < items.length ; itemCounter++) {
            
            var component = items[itemCounter];
            var cmpBindable = (component.bindable? component.bindable : component.name);

            if(cmpBindable == undefined){
                continue;
            }

            var bindArr = cmpBindable.split('.');            
            if(bindArr.length>0) {
                cmpBindable = bindArr[bindArr.length-1];
            }

            var cmpXtype = component.xtype;

            /** Datatype wise methods to set values */
            if(cmpXtype == "customdatetimefield" || cmpXtype == "datefields") {

                var dtData = configData[cmpBindable];
                var dateValue = undefined;
                if(dtData) {
                    var dateParts = dtData.split(" ");
                    var date = dateParts[0].split("-");
                    var day = undefined;
                    var month = undefined;
                    var year = undefined;
                    if(date.length == 3) {
                        day = date[0];
                        month = date[1];
                        year = date[2];
                    }
                    var dt = month+"-"+day+"-"+year;
                    dateValue = new Date(dt+" "+dateParts[1]);
                    component.setValue(dateValue);
                }
            } else if(cmpXtype == "customtimefield" || cmpXtype == "timefield") {
                component.setSrc(configData[cmpBindable]);
                
            } else if(cmpXtype == "datefield") {
                var dtData = configData[cmpBindable];
                var dateValue = undefined;
                if(dtData) {
                    var dateParts = dtData.split("-");
                    if(dateParts.length == 3) {
                        var day = dateParts[0];
                        var month = dateParts[1]-1;
                        var year = dateParts[2];

                        dateValue = new Date(year, month, day);
                    }
                }
                component.setValue(dateValue);

            } else if(cmpXtype == "image") {

                component.setSrc(configData[cmpBindable]);

            } else if(cmpXtype == 'hidden' || cmpXtype =='displayfield' ||
                    cmpXtype == 'textfield' || cmpXtype == 'hiddenfield' ||
                    cmpXtype == 'textareafield' || cmpXtype == 'numberfield' ||
                    cmpXtype == 'combo' || cmpXtype == 'checkbox' ||
                    cmpXtype == "grids" || cmpXtype == 'customcombobox') {
               
                component.setValue(configData[cmpBindable]);

            } else if(cmpXtype == 'customradiogroup' || cmpXtype == 'customcheckboxgroup') {
                
                component.setCmpValue(configData[cmpBindable]);

            } else if(cmpXtype == "label") {
                
                component.setText(configData[cmpBindable]);

            } else if(cmpXtype == "fileupload") {
                
                component.setText(configData[cmpBindable]); 
            }
        }
    },

	/** New function for to set the component data by item id */
	modifyComponent : function(configData, componentId) {

		var item = this.view.down('#'+componentId);
		if(item.isDynamic){
			var name = item.name;
			var inputValue = item.inputValue;
			var boxLabel = item.boxLabel;
			var componentDataArr = configData[item.groupData]; 
			if(Array.isArray(componentDataArr)){

				for(var counter2 = 0; counter2 < componentDataArr.length ; counter2++) {
					var box = {
							boxLabel : componentDataArr[counter2][boxLabel],
							name : name,
							bindValue : inputValue,
							inputValue : componentDataArr[counter2][inputValue]
					};

					item.add(box);
				}
			}
		} else {
			item.setValue(configData[item.bindable]);
		}	
	},

	/** Set the inner grid data */
	setBindableComponentData : function(view, bindable, data) {
			
		var items = view.items.items;
		for (var counter = 0; counter < items.length; counter++) {
			var item = items[counter];
			if(item.bindable == bindable){
				
				if(item && item.xtype && item.xtype == 'grids'){
					item.setData(data[bindable]);
			    	}
	   		} else if(item.items && item.items.items.length > 0){
	   			this.setBindableComponentData(item,bindable, data);
	   		}
	   	}
	},
		
	/** Sets the data for the view*/
	setData : function(data) {
		
		this.view.getViewModel().setData(data);
		this.traversData(data);
	},

	/** Finds the inner elements(grids etc) to set the data */
	traversData : function(data) {
		
		for (var key in data) {
			if(data[key] instanceof Array){
				this.setBindableComponentData(this.view, key, data);
				}
		}
	},
		
	/** Funciton to hide the save button (will work in case of edit form */
	hideSaveButton: function() {
		
		var toolbars = this.view.query('toolbar');
		
		for (var counter = 0; counter < toolbars.length; counter++) {
			var items = toolbars[counter].items.items;
			for (var counter2 = 0; counter2 < items.length; counter2++) {
				if(items[counter2].CRUDType && items[counter2].CRUDType == "save"){
					items[counter2].setHidden(true);
					}
			}
		}
	},
		
	/** Adding New Tab */
	addToTab : function(component) {
		
		var tabs = Ext.getCmp('appMainTabPanel');
		var tab = tabs.add({
			xtype : component,
			closable : true 
			});
		tabs.setActiveTab(tab);
	},
		
	/** Function to set the data to all components */
	setResponseDataToComponents : function(responseData, componentsData) {
			
		var data = responseData.response.data;
		for (var counter = 0; counter < componentsData.length; counter++) {
			
			var bindArr = componentsData[counter].responseBindLevel.split('.');
			var value = data;
			for (var counter2 = 0; counter2 < bindArr.length; counter2++) {
				value = value[bindArr[counter2]];
			}
			this.view.down("#"+componentsData[counter]['compRefId']).setValue(value);
		} 
	},
    
	/** Function to set the data to Combobox with its Store */
	setComboComponentData : function(responseData, comboComponent, displayField, valueField) {
		comboComponent.reset();
		var store = Ext.create('Ext.data.Store', {
			fields : [ displayField, valueField ],
			data : responseData
		});
		comboComponent.setStore(store);
		comboComponent.getStore().sort(displayField, 'ASC');
	},
		
	/** Sets defaults data to components */
	setDefaultData : function(jsonData) {
		for(var counter in jsonData) {
			var component = this.view.down(jsonData[counter]['key']);
			if(component.xtype == "customcheckboxgroup" || component.xtype == 'customradiogroup')
				component.setCmpValue(jsonData[counter]['value']);
			else if(component.xtype == "datefields" || component.xtype == 'customdatetimefield')
				component.setValues(jsonData[counter]['value']);
			else
				component.setValue(jsonData[counter]['value']);
		}
	},
	    
	/** Close Current View's Parent Container */
	closeParentContainer : function(view) {
		var parentView = view.up();
		var parentsXtype = parentView.xtype;
		if(parentsXtype == "tabpanel") {
			view.close();
	    } else if(parentsXtype == "window") {
	    	parentView.close();
	    }
	},
	    
	/** Ajax response handler */
	responseHandler : function(responseText) {
		var alertText = "";
		var alarmData = responseText.alarm;
		if(alarmData.message && alarmData.message!="") {
			alertText = alertText+" <b>Message :</b>" + responseText.response.message;
	    }
		if(alarmData.diagnosis && alarmData.diagnosis!="") {
	   		alertText = alertText+"<br> <b>Dagnosis :</b> " + alarmData.diagnosis;
		}
		if(alarmData.solution && alarmData.solution!="") {
			alertText = alertText+"<br> <b>Solution :</b> " + alarmData.solution;
	    }
		Ext.Msg.alert('Server Response', alertText);
	}
});