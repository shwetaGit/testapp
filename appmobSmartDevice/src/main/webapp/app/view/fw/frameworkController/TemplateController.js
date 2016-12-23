Ext.define("Appmob.view.fw.frameworkController.TemplateController",{
	extend : 'Appmob.view.fw.frameworkController.FrameworkViewController',
	alias : "controller.TemplateController",
	
	onChange: function (me, newValue, oldValue, eOpts){
		var vm =this.view.getViewModel();
		vm.setData(newValue);
	},
	
	modifyComponents : function(configData){
		var items = this.view.items.items;
		for (var ind =0 ; ind < items.length ; ind++) {
			
			if(items[ind].isDynamic){
				var name = items[ind].name;
				var inputValue = items[ind].inputValue;
				var boxLabel = items[ind].boxLabel;
				var componentDataArr = configData[items[ind].groupData]; 
				if(Array.isArray(componentDataArr)){
					
					for(var index2 = 0; index2 < componentDataArr.length ; index2++){
						var box = {boxLabel: componentDataArr[index2][boxLabel],
								name : inputValue,
								bindValue : inputValue,
								inputValue: componentDataArr[index2][inputValue]};
						
						items[ind].add(box);
					}
				}
			}	
		}	
		delete configData.groupData;
		this.view.getViewModel().setData(configData);		
	}
	
});