Ext.define('Appmob.appmob.shared.com.model.appbasicsetup.userrolemanagement.UserRoleBridgeModel', {
     "extend": "Ext.data.Model",
     "fields": [{
          "name": "primaryKey",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "roleUserMapId",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "roleid",
          "reference": "Roles",
          "defaultValue": ""
     }, {
          "name": "rolesRolesName",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "userid",
          "reference": "UserDetail",
          "defaultValue": ""
     }, {
          "name": "userDetailUserDetailName",
          "type": "auto",
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