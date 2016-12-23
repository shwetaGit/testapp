Ext.define('Appmob.view.smartdevice.reportview.mapview.MapViewController',{
	extend :'Appmob.view.smartdevice.reportview.ReportMainViewController',
	alias:'controller.report-mapview',
	
	lodaGoogleMap :function(mapdata){
	
		try {
			var mapOptions = {
				center : {
					lat : 28.613939,
					lng : 77.209021
				},
				zoom : 5
			};
			
			if(this.view.body.dom){
			var map = new google.maps.Map(this.view.body.dom, mapOptions);
			this.googleMap = map;
			
			google.maps.event.trigger(this.googleMap, 'resize');

			google.maps.event.addListenerOnce(map, 'idle', function() {
				google.maps.event.trigger(map, 'resize');
			});
			
			this.showMarker(mapdata);
			
			}
		} catch (e) {}
	},
	showMarker : function(mapdata) {
		for (var i = 0; i < mapdata.length; i++) {
			var data= mapdata[i];

			var marker = new google.maps.Marker({
   						 map: this.googleMap,
    					 position: {lat: parseFloat(data.lattitude), lng: parseFloat(data.longitude)},
    					 title: data.groupvalue
  			});
  			this.attachMarkerInfo(marker, data);
		
		}
	},
	attachMarkerInfo : function(marker, data){
		
		var reportUIJson = this.view.reportUIJson;
		if(reportUIJson && reportUIJson.mapJson){

			var key = "";
			var mapJson = reportUIJson.mapJson;
		
		if (mapJson.hasOwnProperty("aggregate")) {
			var aggregateConfig = mapJson.aggregate;
			if (aggregateConfig.length > 0) {
				key = aggregateConfig[0].dfieldList;
			}
		} else {
			key = mapJson.displayName;
		}


		var infowindow = new google.maps.InfoWindow({
			content : data[key]
		});
		var googleMap = this.googleMap;

		google.maps.event.addListener(marker, 'click', function() {
			infowindow.open(googleMap, marker);
		});

		google.maps.event.addListener(googleMap, 'click', function() {
			infowindow.close();
		});

		}
	},
	
	onMapViewAfterRender :function(){

		
		var sender = this;
		var reportmainview = sender.view.up('reportView');
		
		if(reportmainview.refId){
		
			Ext.Ajax.request({
				url:AppRestUrl+'secure/dataEngineMobileService/getMapDataForMobile',
				method:'POST',
				jsonData : {
					id:reportmainview.refId
				},
				sender : sender,
				success:function(reponse,scope){
					var sender= scope.sender;
					var responseText = Ext.decode(reponse.responseText);
						
					if(responseText.response.success){
						var result =responseText.response.data;
						//console.log('Map Data:: '+ Ext.encode(result.mapdata));
						sender.lodaGoogleMap(result.mapdata);
					}else{
						sender.view.setHtml("<div align='center'><b>"+responseText.response.message+"</b></div>");
						sender.lodaGoogleMap();
					}
				 },
				failure:function(reponse,scope){
				
				}
			});
		}
		
	}


});