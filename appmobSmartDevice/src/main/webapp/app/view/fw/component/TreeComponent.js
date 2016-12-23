Ext.define('Appmob.view.fw.component.TreeComponent', {
	extend : 'Ext.tree.Panel',
	requires:[],
	xtype : 'treecomponent',
	defaults:{
		margin:0
	},
	rootNodeText:"Root",
	initComponent :function(){
		
		Ext.Ajax.request({
			url:this.templateConfig.url,
			scope :this,
			method: this.templateConfig.requestMethodType,
            jsonData: {},
			success : function(response,obj){
				
				var resp = Ext.decode(response.responseText);
				var data = resp.response.data;
				var scope = obj.scope;
					
				var levelInfo = this.templateConfig.levelInfo;
				if(levelInfo.length>0){
					
					var firstLevel = levelInfo[0];
					var nodes = [];

					if(Array.isArray(data)){
						nodes = data;
					}else{
						nodes = data[firstLevel.collection];
					}
					if(nodes && nodes.length>0){
						scope.makeJsonTreeCompatible(nodes,firstLevel);
						scope.applyTreeStore(nodes);
					}
				}
			},
		    failure : function(response,obj){
		    }
		});	
		
		this.callParent();
	},
	makeJsonTreeCompatible :function(data,nodeLevel){
		if(data){
		for (var i = 0; i < data.length; i++) {
			var info = data[i];
		
			if(nodeLevel){
				
				var innerNodes= nodeLevel.innerNodes;
				
				info["text"] =  info[nodeLevel.text];
				info["children"] =  [];
				info["leaf"] = true;

				if(innerNodes.length>0){
					
					var depth = nodeLevel+1;
					info["leaf"] = false;
					
					for (var j = 0; j < innerNodes.length; j++) {
						var innerNode = innerNodes[j];
					
						info.children.push({
							text:innerNode.collection,
							children :this.makeJsonTreeCompatible(info[innerNode.collection],innerNode),
							leaf:false,
							expanded:true
						});
						
					}
				}
			}
		}
		return data;
		}else{
			return [];
		}
	},
	applyTreeStore : function(records){

			var treestore = new Ext.data.TreeStore({
					fields:["text"],
					/*sorters: [{
                    	property: "text",
                    	direction: "ASC"
               		}],*/
		            proxy: {
		            	type: 'memory',
		                reader:{ 
		                	type:'json'
		                }
		            },
		            root:{
		            	text:this.rootNodeText,
		            	children:records
		            }
		        });
		   this.setStore(treestore);
		   this.expandAll();

	},
	tbar: [{
        labelWidth: 130,
        xtype: 'triggerfield',
        fieldLabel: '',
        flex:1,
        emptyText:'Search',
        triggerCls: 'x-form-clear-trigger',
        onTriggerClick: function() {
            var store = this.up('treepanel').store;

            this.reset();
            store.clearFilter();
            this.focus();
        },
        listeners: {
            change: function() {
                var tree = this.up('treepanel'),
                    v,
                    matches = 0;

                try {
                    v = new RegExp(this.getValue(), 'i');
                    tree.store.filter({
                        filterFn: function(node) {
                            var children = node.childNodes,
                                len = children && children.length,

                                // Visibility of leaf nodes is whether they pass the test.
                                // Visibility of branch nodes depends on them having visible children.
                                visible = node.isLeaf() ? v.test(node.get('text')) : false,
                                i;

                            // We're visible if one of our child nodes is visible.
                            // No loop body here. We are looping only while the visible flag remains false.
                            // Child nodes are filtered before parents, so we can check them here.
                            // As soon as we find a visible child, this branch node must be visible.
                            for (i = 0; i < len && !(visible = children[i].get('visible')); i++);

                            if (visible && node.isLeaf()) {

                                matches++;
                            }
                            return visible;
                        },
                        id: 'titleFilter'
                    });
                    tree.down('#matches').setValue(matches);
                } catch (e) {
                    this.markInvalid('Invalid regular expression');
                }
            },
            buffer: 250
        }
    }]
	
});