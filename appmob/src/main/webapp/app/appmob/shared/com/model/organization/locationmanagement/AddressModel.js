Ext.define('Appmob.appmob.shared.com.model.organization.locationmanagement.AddressModel', {
     "extend": "Ext.data.Model",
     "fields": [{
          "name": "primaryKey",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "addressId",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "addresstypeid",
          "reference": "AddressType",
          "defaultValue": ""
     }, {
          "name": "addressTypeAddressTypeName",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "addressLabel",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "address1",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "address2",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "address3",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "countryid",
          "reference": "Country",
          "defaultValue": ""
     }, {
          "name": "countryCountryName",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "stateid",
          "reference": "State",
          "defaultValue": ""
     }, {
          "name": "stateStateName",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "cityid",
          "reference": "City",
          "defaultValue": ""
     }, {
          "name": "cityCityName",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "zipcode",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "latitude",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "longitude",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "versionId",
          "type": "int",
          "defaultValue": ""
     }, {
          "name": "entityAudit",
          "reference": "EntityAudit"
     }, {
          "name": "primaryDisplay",
          "type": "string",
          "defaultValue": ""
     }]
});