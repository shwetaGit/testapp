import {Component, OnInit, Input} from '@angular/core';
import {ReportMapService} from "./map.service";

@Component({
    selector: 'report-map-component',
    template: `
            <div style="overflow:auto;max-height:50vh;min-height:50vh;" class="card" id="reportGoogleMap" >
                Report Map Loading...
            </div>
    `,
    providers:[ReportMapService]
})
export class ReportMapComponent implements OnInit {
    
    @Input() reportId :any;
    @Input() mapConfig :any;
    mapData :any[];
    googleMap:Object;

    constructor(private reportMapService :ReportMapService) {
    }

    ngOnInit() {


    }
    ngAfterViewInit(){
        this.loadGoogleMap();
    }

    loadcomponent() {

        this.reportMapService.getMapData(this,this.reportId);

    }

    loadGoogleMap(){
    try {
        let mapOptions = {
            center : {
                lat : 28.613939,
                lng : 77.209021
            },
            zoom : 3,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };

        let mapDiv = document.getElementById("reportGoogleMap");

        this.googleMap = new google.maps.Map(mapDiv, mapOptions);

    }
    catch (e){

        }
    }
    showMarker() {
        let mapdata = this.mapData;
        for (var i = 0; i < mapdata.length; i++) {
            var data = mapdata[i];

            var marker = new google.maps.Marker({
                map: this.googleMap,
                position: {lat: parseFloat(data.lattitude), lng: parseFloat(data.longitude)},
                title: data.groupvalue
            });
            this.attachMarkerInfo(marker, data);

        }
    }
    attachMarkerInfo(marker :Object, data:any){


        if(this.mapConfig){
            let mapConfig = this.mapConfig;

            if(mapConfig.hasOwnProperty("mapView")){

            var key = "";
            var mapJson = mapConfig.mapView;

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
    }

}